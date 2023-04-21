package nu.educom.MI6;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        IView view = new JPanelView();
        DatabaseRepository repo = new DatabaseRepository();
        AgentModel model = new AgentModel(repo);
        Presentor presentor = new Presentor(view, model);
        presentor.run();
//        presentor.test();

//        // create a new connection from MySQLJDBCUtil
//        try (Connection conn = MySQLJDBCUtil.getConnection()) {
//
//            // print out a message
//            System.out.println(String.format("Connected to database %s "
//                    + "successfully.", conn.getCatalog()));
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }


    }




}

