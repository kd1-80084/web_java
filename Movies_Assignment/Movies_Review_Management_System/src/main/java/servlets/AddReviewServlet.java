package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ReviewDao;
import daos.ReviewDaoImpl;
import pojos.Reviews;
import pojos.Users;

@WebServlet("/addReviewServlet")
public class AddReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ReviewDao reviewDao=new ReviewDaoImpl())
		{
			HttpSession session = req.getSession();
			Users user =  (Users) session.getAttribute("curUser");
			
			System.out.println("Add review");
			String movies=req.getParameter("movies");
			int movie_id=Integer.parseInt(movies);
			int rating =Integer.parseInt(req.getParameter("rating"));
			String review=req.getParameter("review");
			Reviews r=new Reviews();
			r.setRating(rating);
			r.setReview(review);
			r.setMovieId(movie_id);
			r.setUser_id(user.getId());
			System.out.println(r);
			reviewDao.save(r);
			resp.sendRedirect("review");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
