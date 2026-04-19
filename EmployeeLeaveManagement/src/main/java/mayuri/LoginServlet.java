package mayuri;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String officeUserCode = request.getParameter("officeUserCode");
        String officeUserPass = request.getParameter("officeUserPass");

        HttpSession session = request.getSession();

        // 🔥 TEMP LOGIC (later we connect DB like LoginModel)
        if ("admin".equals(officeUserCode) && "123".equals(officeUserPass)) {

            // ✅ SUCCESS → redirect to LeaveApplication.jsp
            response.sendRedirect("view/LeaveApplication.jsp");

        } else {

            // ❌ FAILED LOGIN
            session.setAttribute("LoginError", 1);
            response.sendRedirect("index.jsp");
        }
    }
}