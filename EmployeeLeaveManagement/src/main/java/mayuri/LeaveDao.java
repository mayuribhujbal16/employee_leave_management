package mayuri;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LeaveDao 
{
	public boolean applyLeave(String empCode, String leaveType, String startDate, String endDate, String reason)
	{
		boolean status = false;
		
		try 
		{
			Connection con = DBConnection.getConnection();
			
			String query = "INSERT INTO leave_requests (emploeeCode, leaveType, startDate, endDate, reason, status) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, empCode);
			ps.setString(2, leaveType);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.setString(5, reason);
			ps.setString(6, "pending");
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				status = true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
		
	}
		
	

}
