package mayuri;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String officeUserCode = request.getParameter("officeUserCode");
        String officeUserPass = request.getParameter("officeUserPass");

        HttpSession session = request.getSession();

        // TEMP LOGIN
        LoginModel model = new LoginModel();
        
        if(model.checkLogin(officeUserCode, officeUserPass))
        {
        	response.sendRedirect("view/LeaveApplication.jsp");
        }
        else
        {
        	session.setAttribute("LoginError", 1);
        	response.sendRedirect("index.jsp");
        }
    }
}