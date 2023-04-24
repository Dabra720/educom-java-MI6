package nu.educom.MI6;

import org.hibernate.Session;

import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseRepository {
    protected Connection conn;

//    public PreparedStatement prepareAndBind(String sql, List params){
//
//
//    }

    public void insertLoginAttempt(LoginAttempt login){
        String sql = "INSERT INTO login_attempts (service_nr, success)" +
                        "VALUES(?, ?)";

        try(Connection conn = MySQLJDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, login.getServiceNumber());
            pstmt.setBoolean(2, login.getSuccess());

            pstmt.executeUpdate();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public List<Agent> readAllAgents(){
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        List<Agent> allAgents = session.createQuery("select * from agents", Agent.class)
                .getResultList();
        for (Agent agent:allAgents ) {
            System.out.println("Service-nr: " + agent.getFormattedServiceNumber());
            System.out.println("Service-nr: " + agent.getPassPhrase());
            System.out.println("Service-nr: " + agent.getActive());
        }
        session.getTransaction().commit();
        session.close();
        return allAgents;
    }
    public Agent readHibernate(int serviceNr){
        Session session = HibernateUtil.openSession();

        Agent agent = session.createQuery("from Agent WHERE serviceNumber = :serviceNr", Agent.class)
                .setParameter("serviceNr", serviceNr)
                .uniqueResult();
        return agent;
    }
    public Agent readAgentByServiceNumber(int serviceNr){
        String sql = "SELECT * FROM agents WHERE service_nr=?";
        Agent agent = null;
        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Getting agent from database");
            pstmt.setInt(1, serviceNr);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                agent = new Agent(rs.getInt("service_nr"));
                agent.setId(rs.getInt("id"));
                agent.setPassPhrase(rs.getString("pass_phrase"));
                if(rs.getDate("license_to_kill_date")!= null) {
//                    agent.setLicenseToKill(rs.getDate("license_to_kill_date").toLocalDate());
                }
                agent.setActive(rs.getBoolean("active"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return agent;
    }

    public ArrayList readFailedLoginAttempts(Agent agent) {
        ArrayList<LoginAttempt> loginAttempts = new ArrayList<>();
        String sql = "select l.id, l.service_nr, l.timestamp, l.success from login_attempts l\n" +
                "left join (\n" +
                "select service_nr, timestamp as last_success \n" +
                "from login_attempts \n" +
                "where success=1 AND service_nr=?\n" +
                "order by id desc\n" +
                "limit 1\n" +
                ") max on max.service_nr=l.service_nr\n" +
                "where timediff(l.timestamp, max.last_success) > 0 AND l.service_nr=?;";


        int serviceNr = agent.getServiceNumber();
        try (Connection conn = MySQLJDBCUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println("ServiceNr: " + serviceNr);
            pstmt.setInt(1, serviceNr);
            pstmt.setInt(2, serviceNr);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                LoginAttempt login = new LoginAttempt(serviceNr);
                login.setId(rs.getInt("id"));
                login.setLoginDateTime(rs.getObject("timestamp", LocalDateTime.class));
                loginAttempts.add(login);
//                System.out.println("Print login attempts: " + login.getLoginDateTime().getLong());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return loginAttempts;


    }
}
