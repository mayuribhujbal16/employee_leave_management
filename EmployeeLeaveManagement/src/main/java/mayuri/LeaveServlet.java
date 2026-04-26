package mayuri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class LeaveServlet
 */
@WebServlet("/LeaveServlet")
public class LeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// 1. Get form data
        String leaveType = request.getParameter("leaveType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        
        // 2. Get employee code from Session
        HttpSession session = request.getSession();
        String empCode = (String) session.getAttribute("userCode");

        // 3. Save to Database
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO leaverequests (empCode, leaveType, startDate, endDate, reason) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, empCode);
            ps.setString(2, leaveType);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            ps.setString(5, reason);
            
            ps.executeUpdate();
            
            // 4. Redirect back with success
            response.sendRedirect(request.getContextPath() + "/views/LeaveApplication.jsp?msg=success");
            
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/LeaveApplication.jsp?msg=success");
        }
        
        
	}

}
