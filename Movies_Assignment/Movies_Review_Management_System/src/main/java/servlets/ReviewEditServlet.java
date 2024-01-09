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

import daos.MoviesDao;
import daos.MoviesDaoImpl;
import daos.ReviewDao;
import daos.ReviewDaoImpl;
import pojos.Movies;
import pojos.Reviews;

@WebServlet("/reviewsedit")
public class ReviewEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reviewId =req.getParameter("id");
		int id =Integer.parseInt(reviewId);
		Reviews r =null;
		try(ReviewDao reviewDao=new ReviewDaoImpl())
		{
			r=reviewDao.findById(id);
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Review</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Edit Review</h1>");
		out.println("<head>");
		out.println("<h5>Edit Candidate</h5>");
	    out.println("<form method='post' action='reviewsedit'>");
	    out.printf("ID:<input type='text' name='id' value='%s' readonly><br/>", r.getReviewId());
	    out.println("<select name='movies'>");
		try(MoviesDao moviesdao = new MoviesDaoImpl()){
			
			List<Movies> movielist= moviesdao.findAll();
			
			for (Movies movies : movielist) {
				out.printf("<option value="+movies.getMovieId()+">%s</option>",movies.getMovieTitle());
				
			}
			out.println("</select><br>");
	    out.printf("Rating: <input type='text' name='rating' value='%s'>\r\n<br/>", r.getRating());
	    out.printf("User Id: <input type='text' name='userid' value='%s' readonly>\r\n<br/>", r.getUser_id());
	    out.println("Review:<textarea name='review'></textarea><br>");
	    out.println("<input type='submit' value='Update'/>");
	    out.println("</form>");
	    out.println("</body>");
	    out.println("</html>");
	} catch (Exception e) {
		
		e.printStackTrace();
		throw new ServletException(e);
	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Id=req.getParameter("id");
		int reviewId=Integer.parseInt(Id);
		String movie=req.getParameter("movies");
		String rating=req.getParameter("rating");
		String userid=req.getParameter("userid");
		Reviews r=new Reviews(reviewId,movie,rating,userid);
		int cnt=0;
		try(ReviewDao reviewDao = new ReviewDaoImpl()) {
					cnt = reviewDao.update(r);
			} catch (Exception e) {
					e.printStackTrace();
						throw new ServletException(e);
					}
					RequestDispatcher rd = req.getRequestDispatcher("review"); 
					rd.forward(req, resp);
				}
	}


		
		

