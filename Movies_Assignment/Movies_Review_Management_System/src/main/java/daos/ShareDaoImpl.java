package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pojos.Reviews;

public class ShareDaoImpl extends Dao implements ShareDao{

	public ShareDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(int rid, int uid) throws Exception {
		String sql = "insert into shares values(?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, rid);
			stmt.setInt(2, uid);
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void delete(int rid) throws Exception {

		String sql = "delete from shares where review_id = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, rid);
		
			stmt.executeUpdate();
		}		
	}


//
//public List<Reviews> sharedWithMe(int userId) throws Exception {
//
//	List<Reviews> sharedReviews = new ArrayList<Reviews>();
//	String sql = "SELECT r.* FROM reviews r JOIN shares s ON r.id = s.review_id WHERE s.user_id = ?";
//	try (PreparedStatement stmt = con.prepareStatement(sql)) {
//		stmt.setInt(1, userId);
//		try (ResultSet rs = stmt.executeQuery()) {
//			while (rs.next()) {
//				int reviewId = rs.getInt("id");
//				int movieId = rs.getInt("movie_id");
//				String reviewText = rs.getString("review");
//				int rating = rs.getInt("rating");
//				Timestamp modified = rs.getTimestamp("modified");
//				Reviews review = new Reviews(reviewId, movieId, reviewText, rating, userId, modified);
//				review.setModified(modified);
//				sharedReviews.add(review);
//			}
//		}
//	}
//	return sharedReviews;
//}
}