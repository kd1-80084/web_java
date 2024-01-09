package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import pojos.Reviews;
import pojos.Users;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           
            HttpSession session = req.getSession();
            Users user = (Users) session.getAttribute("curUser");

            
            ReviewDao reviewDao = new ReviewDaoImpl(); 
            
            String type = req.getParameter("type");
            List<Reviews> reviewsToDisplay = new ArrayList<>();

            if (type == null || type.equals("all"))
            {
            	List<Reviews> allReviews = reviewDao.findAll(); 
                reviewsToDisplay = allReviews;
            } else if (type.equals("my")) 
            {
            	List<Reviews> myReviews = reviewDao.findByUserId(user.getId());
                reviewsToDisplay = myReviews;
            } else if (type.equals("share"))
            {
            	List<Reviews> sharedReviews = reviewDao.getSharedWithUser(user.getId());
                reviewsToDisplay = sharedReviews;
            }

            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Review</title>");
            out.println("</head>");
            out.println("<body>");

            out.printf("Hello, %s %s <hr>\r\n", user.getFirst_Name(), user.getLast_Name());
            out.println("<a href='review?type=all'>All Reviews</a>");
            out.println("<a href='review?type=my'>My Reviews</a>");
            out.println("<a href='review?type=share'>Shared Reviews</a>");

            out.println("<h2>Reviews</h2>");
            out.println("<table border=1>");
            out.println("<thead>");
            out.println("<th>ID</th>");
            out.println("<th>Movie</th>");
            out.println("<th>Ratings</th>");
            out.println("<th>User Id</th>");
            out.println("<th>Action</th>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Reviews reviews : reviewsToDisplay) {
              out.println("<tr>");
              out.printf("<td>%s</td>\r\n", reviews.getReviewId());
              try (MoviesDao moviesDao = new MoviesDaoImpl()) {
                  Movies m = moviesDao.findById(reviews.getReviewId());
                  out.printf("<td>%s</td>\r\n", m.getMovieTitle());

              }
              out.printf("<td>%s</td>\r\n", reviews.getReview());
              out.printf("<td>%s</td>\r\n", reviews.getUser_id());
              out.printf(
						"<td><a href='reviewsedit?id=%s'><img width=24 height=24 src='edit.png' alt='edit'></a><span></span>"
						+ "<a href='reviewsdel?id=%s'><img width=24 height=24 src='delete.png' alt='delete'></a><span></span>"
						+ "<a href='sharereview?id=%s'><img width=24 height=24 src='share.png' alt='share'></a><span></span>"
						+ "</td>",
						reviews.getReviewId(),reviews.getReviewId(),reviews.getReviewId());
              out.println("</tr>");
          }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<a href='addreview'>Add Review</a>");
            out.println("<a href='logout'>Sign Out</a>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
