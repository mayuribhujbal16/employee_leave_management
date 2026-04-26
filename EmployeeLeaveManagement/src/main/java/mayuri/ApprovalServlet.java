package mayuri;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get the data from the URL (e.g., ApprovalServlet?id=5&status=Approved)
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        System.out.println("Updating ID:  "+ id + " to Status: " +status);

        // 2. Update the database directly
        String sql = "UPDATE leaverequests SET status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Send the Admin back to the dashboard to see the update
        response.sendRedirect("admin/dashboard.jsp");
    }
}