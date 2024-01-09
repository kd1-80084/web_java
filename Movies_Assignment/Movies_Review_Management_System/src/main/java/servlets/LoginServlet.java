package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import daos.UsersDaoImpl;
import pojos.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("passwd");
		System.out.println("Email:"+email+"and password:"+password);
		try(UserDao userDao=new UsersDaoImpl())
		{
			Users user=userDao.findByEmail(email);
			System.out.println("Found User:"+user);
			if(user!=null)
			{
				HttpSession session = req.getSession();
				session.setAttribute("curUser", user);
				resp.sendRedirect("review");
		
			}
			else
			{
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Failed</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h5>Invalid email or password</h5>");
				out.println("<a href='index.html'>Login Again</a>");
				out.println("</body>");
				out.println("</html>");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
