package mayuri;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 * Handles authentication and role-based redirection.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get parameters from index.jsp
        String officeUserCode = request.getParameter("officeUserCode");
        String officeUserPass = request.getParameter("officeUserPass");

        // 2. Initialize Session and DAO
        HttpSession session = request.getSession();
        LoginDAO dao = new LoginDAO();

        // 3. Validate user and get their role
        String role = dao.validateUser(officeUserCode, officeUserPass, session);

        // 4. Decision Logic based on Role
        if (role != null) {
            // Success: Remove any previous login errors
            session.removeAttribute("LoginError");

            if (role.equalsIgnoreCase("Admin")) {
                // Redirect to Admin Dashboard
                response.sendRedirect("admin/dashboard.jsp");
            } else if (role.equalsIgnoreCase("Employee")) {
                // Redirect to Employee Leave Page
                response.sendRedirect("views/LeaveApplication.jsp");
            } else {
                // Fallback for undefined roles
                response.sendRedirect("index.jsp");
            }
        } else {
            // Failure: Set error attribute and send back to login
            session.setAttribute("LoginError", 1);
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests back to the login page
        response.sendRedirect("index.jsp");
    }
}