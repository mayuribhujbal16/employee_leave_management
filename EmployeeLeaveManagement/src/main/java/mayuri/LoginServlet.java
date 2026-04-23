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
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String officeUserCode = request.getParameter("officeUserCode");
		String officeUserPass = request.getParameter("officeUserPass");
		
		HttpSession session = request.getSession();
		LoginDAO dao = new LoginDAO();
		
		String role = dao.validateUser(officeUserCode, officeUserPass, session);
		
		
		if(role != null)
		{
			session.removeAttribute("LoginError");
			
			if(role.equalsIgnoreCase("Admin"))
			{
				response.sendRedirect("admin/dashboard.jsp");
			}
			else if (role.equalsIgnoreCase("Employee")) 
			{
				response.sendRedirect("views/LeaveApplication.jsp");
				
			}
			else
			{
				session.setAttribute("LoginError", 1);
				response.sendRedirect("index.jap");
			}
		}
		
	
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("index.jap");	
	}

}
