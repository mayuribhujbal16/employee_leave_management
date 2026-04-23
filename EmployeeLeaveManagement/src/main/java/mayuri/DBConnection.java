package mayuri;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			String url = "jdbc:mysql://localhost:3306/empLeave";
			String user = "root";
			String pass = "mayuri16";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			
		}
		catch(Exception e) {
			System.out.println("Connection Failed! Check your SQL credentials");
			e.printStackTrace();
		}
		return con;
	}

    
}