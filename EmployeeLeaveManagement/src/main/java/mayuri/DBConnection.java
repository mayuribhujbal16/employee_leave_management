package mayuri;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    // Load the driver once when the application starts
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            // URL with common flags to avoid connection handshake errors
            String url = "jdbc:mysql://localhost:3306/empLeave?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String pass = "mayuri16";
            
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Connection Failed! Check if MySQL is running and credentials are correct.");
            e.printStackTrace();
        }
        return con;
    }
}