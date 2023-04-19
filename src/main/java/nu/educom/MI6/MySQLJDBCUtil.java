package nu.educom.MI6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {

    /**
     * Get database connection
     *
     * @return a Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        // filepath verschilt tussen mijn apparaten.
        String filePath = "D:\\Users\\Daan\\IntelliJ\\educom-java-MI6\\src\\main\\java\\nu\\educom\\MI6\\db.properties";
//        String filePath = "C:\\Users\\daan_\\IdeaProjects\\educom-java-MI6\\src\\main\\java\\nu\\educom\\MI6\\db.properties";

        try (FileInputStream f = new FileInputStream(filePath)) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url = pros.getProperty("url");
//            String user = pros.getProperty("user");
//            String password = pros.getProperty("password");

            // create a connection to the database
            conn = DriverManager.getConnection(url, pros);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}