package nu.educom.MI6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseRepository {


    public void connectToDatabase(){
        // db parameters
        String url       = "jdbc:mysql://localhost:3306/mysqljdbc";
        String user      = "root";
        String password  = "secret";

        Connection conn = null;

        try{conn = DriverManager.getConnection(url, user, password);
            // processing here
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
