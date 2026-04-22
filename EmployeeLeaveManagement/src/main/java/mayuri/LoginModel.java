package mayuri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel 
{
	public boolean checkLogin(String code, String pass)
	{
		boolean isFound = false;
		
		try {
			Connection con = DBConnection.getConnection();
			
			String query = "Select * FROM employeeinfo WHERE eEmployeeCodeNumber=? AND ePassword=? AND eEmployeeVerification=1";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				isFound = true;
			}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return isFound;
		
	}

}
