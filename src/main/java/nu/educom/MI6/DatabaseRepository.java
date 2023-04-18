package nu.educom.MI6;

import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;


public class DatabaseRepository {
    protected Connection conn;

//    public PreparedStatement prepareAndBind(String sql, List params){
//
//
//    }

    public void insertRow(){

    }
    public Agent readAgentByServiceNumber(int serviceNr){
        String sql = "SELECT * FROM agents WHERE service_nr=?";
        Agent agent;
        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, serviceNr);

            ResultSet rs = pstmt.executeQuery();

            agent = new Agent(rs.getInt("service_nr"));
            agent.setId(rs.getInt("id"));
            agent.setPassPhrase(rs.getString("pass_phrase"));
            agent.setLicenseToKill(rs.getDate("license_to_kill_date").toLocalDate());
            agent.setActive(rs.getBoolean("active"));

            return agent;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public LinkedList<LoginAttempt> readAllFailedLoginAttempts(Agent agent){
        LinkedList<LoginAttempt> loginAttempts = new LinkedList<>();
        String sql = "SELECT * FROM login_attempts WHERE service_nr=? AND success=0";
        int serviceNr = agent.getServiceNumber();
        try (Connection conn = MySQLJDBCUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, serviceNr);
            ResultSet rs = pstmt.executeQuery(sql);

            while(rs.next()){
                LoginAttempt login = new LoginAttempt(serviceNr);
                login.setId(rs.getInt("id"));
                login.setLoginDateTime(rs.getObject("timestamp", LocalDateTime.class));
                loginAttempts.add(login);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return loginAttempts;
    }



}
