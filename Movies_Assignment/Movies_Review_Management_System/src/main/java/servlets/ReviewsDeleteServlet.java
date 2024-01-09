package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ReviewDao;
import daos.ReviewDaoImpl;


@WebServlet("/reviewsdel")
public class ReviewsDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewId = req.getParameter("id");

            int id = Integer.parseInt(reviewId);
            try (ReviewDao reviewDao = new ReviewDaoImpl()) {
                int cnt = reviewDao.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
            RequestDispatcher rd = req.getRequestDispatcher("review");
            rd.forward(req, resp);
    }
}


