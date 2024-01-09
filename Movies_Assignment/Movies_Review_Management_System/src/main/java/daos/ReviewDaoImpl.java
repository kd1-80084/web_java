package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pojos.Reviews;

public class ReviewDaoImpl extends Dao implements ReviewDao{

	public ReviewDaoImpl() throws Exception {
		super();
		
	}

	@Override
	public int save(Reviews r) throws Exception {
		String sql = "INSERT INTO reviews(id, movie_id, review, rating, user_id, modified) VALUES(default, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getMovieId());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, r.getUser_id());
			stmt.setTimestamp(5,r.getModified());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();	
	}

	@Override
	public int update(Reviews r) throws Exception {
		String sql = "UPDATE reviews SET movie_id=?, review=?, rating=?, user_id=?, modified=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getMovieId());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, r.getUser_id());
			stmt.setTimestamp(5, r.getModified());
			stmt.setInt(6, r.getReviewId());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	@Override
	public List<Reviews> findAll() throws Exception {
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "SELECT * FROM reviews ORDER BY modified DESC";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int userId = rs.getInt("user_id");
					Timestamp modified =rs.getTimestamp("modified");
					Reviews r = new Reviews(id, movieId, review ,rating,userId, modified);
					list.add(r);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

	@Override
	public List<Reviews> findByUserId(int userId) throws Exception {
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "SELECT * FROM reviews WHERE user_id=? ORDER BY modified DESC";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int usrId = rs.getInt("user_id");
					Timestamp modified =rs.getTimestamp("modified");
					Reviews r = new Reviews(id, movieId, review ,rating,userId, modified);
					list.add(r);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

	@Override
	public List<Reviews> getSharedWithUser(int userId) throws Exception {
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "SELECT r.* FROM reviews r INNER JOIN shares s ON r.id=s.review_id WHERE s.user_id=? ORDER BY modified DESC";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int usrId = rs.getInt("user_id");
					Timestamp modified =rs.getTimestamp("modified");
					Reviews r = new Reviews(id, movieId, review ,rating,userId, modified);
					list.add(r);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

	@Override
	public Reviews findById(int id) throws Exception {
		String sql = "SELECT * FROM reviews WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int usrId = rs.getInt("user_id");
					Timestamp modified =rs.getTimestamp("modified");
					Reviews r = new Reviews(id, movieId, review, usrId, rating, modified);
					return r;
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}
	@Override
	public int deleteById(int reviewId) throws Exception {
        String sql = "DELETE FROM reviews WHERE id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            int count = stmt.executeUpdate();
            return count;
        }
    }

	@Override
	public int shareReview(int reviewId, int userId) throws Exception {
		String sql = "INSERT INTO shares(review_id, user_id) VALUES (?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, reviewId);
			stmt.setInt(2, userId);
			int count = stmt.executeUpdate();
			return count;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			// do nothing
		}
		return 0;
	}

	@Override

public List<Reviews> sharedWithMe(int userId) throws Exception {

	List<Reviews> sharedReviews = new ArrayList<Reviews>();
	String sql = "SELECT r.* FROM reviews r JOIN shares s ON r.id = s.review_id WHERE s.user_id = ?";
	try (PreparedStatement stmt = con.prepareStatement(sql)) {
		stmt.setInt(1, userId);
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int reviewId = rs.getInt("id");
				int movieId = rs.getInt("movie_id");
				String reviewText = rs.getString("review");
				int rating = rs.getInt("rating");
				Timestamp modified = rs.getTimestamp("modified");
				Reviews review = new Reviews(reviewId, movieId, reviewText, rating, userId, modified);
				review.setModified(modified);
				sharedReviews.add(review);
			}
		}
	}
	return sharedReviews;
}
}
