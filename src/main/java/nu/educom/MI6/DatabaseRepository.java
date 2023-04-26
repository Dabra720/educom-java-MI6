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
    protected Session session = HibernateUtil.openSession();

//    public PreparedStatement prepareAndBind(String sql, List params){
//
//
//    }

    public void insertLoginAttempt(LoginAttempt login){
        System.out.println("Trying to insert login attempt..");
//        try {
//
        session.beginTransaction();
//        session.createQuery("insert into LoginAttempt(agent_id, successful) values(?, ?)")
//                .setParameter(1, login.getServiceNumber())
//                .setParameter(2, login.getSuccess())
//                .executeUpdate();
        session.persist(login);
        session.getTransaction().commit();
//        session.persist(login);
//        }catch(Exception e){

//            System.out.println(e);
//        }
    }
    public List<Agent> readAllAgents(){
//        Session session = HibernateUtil.openSession();
//        session.beginTransaction();
        List<Agent> allAgents = session.createQuery("from agents", Agent.class)
                .getResultList();
//        for (Agent agent:allAgents ) {
//            System.out.println("Service-nr: " + agent.getServiceNumber());
//            System.out.println("Pass_phrase: " + agent.getPassPhrase());
//            System.out.println("Active: " + agent.getActive());
//        }
//        session.getTransaction().commit();
//        session.close();
        return allAgents;
    }
    public Agent readAgentByServiceNumber(int serviceNr){
        Session session = HibernateUtil.openSession();

        Agent agent = session.createQuery("from Agent WHERE serviceNumber = :serviceNr", Agent.class)
                .setParameter("serviceNr", serviceNr)
                .uniqueResultOptional().orElse(null);
        return agent;
    }

    public List readFailedLoginAttempts(Agent agent) {
        List<LoginAttempt> loginAttempts = new ArrayList<>();
//        String subQuery = "SELECT max(timestamp) FROM LoginAttempt l WHERE l.get";
//        String sql = String.format("select new list(LoginAttempt) from login_attempts l "+
//                "left join (select ", subQuery);

        // TIMEDIFF() bestaat niet in HQL, dus voor nu nog even nativequery
        loginAttempts = session.createNativeQuery(
                "select l.id, l.service_nr, l.timestamp, l.success from login_attempts l " +
                "left join (" +
                "select service_nr, timestamp as last_success " +
                "from login_attempts " +
                "where success=1 AND service_nr=? " +
                "order by id desc " +
                "limit 1" +
                ") max on max.service_nr=l.service_nr " +
                "where timediff(l.timestamp, max.last_success) > 0 AND l.service_nr=?", LoginAttempt.class)
                .setParameter(1, agent.getServiceNumber())
                .setParameter(2, agent.getServiceNumber())
                .getResultList();
        System.out.println("Login attempts: " + loginAttempts.size());
        for(LoginAttempt login:loginAttempts){
            System.out.println("Attempt ID: " + login.getId());
            System.out.println("Attempt time: " + login.getLoginDateTime());
        }
        return loginAttempts;
    }

    public ArrayList readFailedLoginAttempts2(Agent agent) {
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
