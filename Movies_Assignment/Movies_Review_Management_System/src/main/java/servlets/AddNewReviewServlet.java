package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.MoviesDao;
import daos.MoviesDaoImpl;
import daos.ReviewDao;
import daos.ReviewDaoImpl;
import pojos.Movies;
import pojos.Users;

@WebServlet("/addreview")
public class AddNewReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ReviewDao reviewdao= new ReviewDaoImpl())
		{
			PrintWriter out=resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Add Reviews</title>");
			out.println("</head>");
			out.println("<body>");
			
			HttpSession session = req.getSession();
			Users user =(Users) session.getAttribute("curUser");
			out.println("<h3>Hello "+user.getFirst_Name()+" "+user.getLast_Name()+"!</h3>");
			out.println("<form method='POST' action='addReviewServlet'>");
			out.println("<label>Movie</label>");
			out.println("<select name='movies'>");
			try(MoviesDao moviesdao = new MoviesDaoImpl()){
				
				List<Movies> movielist= moviesdao.findAll();
				
				for (Movies movies : movielist) {
					out.printf("<option value="+movies.getMovieId()+">%s</option>",movies.getMovieTitle());
					
				}
				out.println("</select><br>");
				
			
			out.println("<label>Rating</label>");
			out.println("<input name='rating' type='text'></input><br>"); 
			out.println("<label>Review</label>");
			out.println("<textarea name='review'></textarea><br>");
			out.println("<input type='submit' value='Save'></input><br>");
			
			out.println("</form>");
			}
			out.println("</body>");
			out.println("</html>");	
			
		} catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
