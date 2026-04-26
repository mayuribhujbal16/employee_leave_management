package mayuri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpSession;

public class LoginDAO 
{
	public String validateUser(String code, String pass, HttpSession session)
	{
		String role = null;


		String query = "SELECT eEmployeeName, eEmployeeRole FROM employeeinfo " +
		               "WHERE eEmployeeCodeNumber=? AND ePassword=? AND eEmployeeVerification=1";
		
		
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(query)) 
		{
			ps.setString(1, code);
			ps.setString(2, pass);
			
			try(ResultSet rs = ps.executeQuery())
			{
				if (rs.next()) 
				{
					String name = rs.getString("eEmployeeName");
					role = rs.getString("eEmployeeRole");
					
					session.setAttribute("userCode", code);
					session.setAttribute("userName", name);
					session.setAttribute("userRole", role);
					
				//	updateLastLogin(code, con);
				}
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return role;
	}
	private void updateLastLogin(String code, Connection con)
	{
		String updateQuery = "UPDATE employeeinfo SET eLastLogin = NOW() WHERE eEmployeeCodeNumber=?";
		
		try(PreparedStatement ps = con.prepareStatement(updateQuery))
		{
			ps.setString(1, code);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}















