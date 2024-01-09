package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ReviewDao;
import daos.ReviewDaoImpl;
import daos.ShareDao;
import daos.ShareDaoImpl;
import daos.UserDao;
import daos.UsersDaoImpl;
import pojos.Reviews;
import pojos.Users;

@WebServlet("/sharereview")
public class ShareWithMeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>share Review</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='sharereview'>");
		
		int id =Integer.parseInt(req.getParameter("id"));
		
		try(ReviewDao reviewDao= new ReviewDaoImpl())
		{
			Reviews review=reviewDao.findById(id);
			out.printf("ID:<input type='number' name='reviewid' readonly value=%d><br>",review.getReviewId());
			out.printf("User:<select name='user'>");
			try(UserDao userdao = new UsersDaoImpl()){
				List<Users> list = userdao.findAll();
				for(Users user:list) {
					out.printf("<option value=%s>%s</option>",user.getId(),user.getFirst_Name());
				}
				
			}
			out.printf("</select>");
			out.printf("<input type='submit' value='Share'>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

	
		int reviewid = Integer.parseInt( req.getParameter("reviewid"));
		int userid = Integer.parseInt(req.getParameter("user")); 
		System.out.println(reviewid);
		System.out.println(userid);
		try(ShareDao sharedao = new ShareDaoImpl()){
		
			sharedao.add(reviewid, userid);
			
			String title = "Shared Reviews";
			req.setAttribute("title", title);
			
			RequestDispatcher rd = req.getRequestDispatcher("review?site=shared");
			rd.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}

}
