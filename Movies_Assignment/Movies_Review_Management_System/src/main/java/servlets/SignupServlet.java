package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDao;
import daos.UsersDaoImpl;
import pojos.Users;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id=req.getContentLength();
    	String first_Name = req.getParameter("fname");
        String last_Name = req.getParameter("lname");
        String email = req.getParameter("email");
        String password = req.getParameter("passwd");
        String mob=req.getParameter("mobile");
        long mobile = Long.parseLong(mob);
        String birthDate = req.getParameter("date");

        Users user = new Users(id,first_Name,last_Name,email,password,mobile,birthDate);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setMobileNo(Long.parseLong(mobile));
//        user.setDob(birthDate);
        try (UserDao userDao = new UsersDaoImpl()) {
        	int cnt=userDao.save(user);
                if(cnt==1)
                {
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>register</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>user Added Succesfully!</h2>");
                out.println("<h3>Click here to <a href='index.html'>Login</a></h3>");
                out.println("</body>");
                out.println("</html>");
                }
                else
                {
                	
                }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
