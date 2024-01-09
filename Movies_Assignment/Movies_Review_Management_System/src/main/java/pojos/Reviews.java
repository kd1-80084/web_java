package pojos;


import java.sql.Timestamp;

public class Reviews {
	private int reviewId;
	private int movieId;
	private String review;
	private int rating;
	private int user_id;
	private Timestamp modified;

	public Reviews() {

	}

	public Reviews(int reviewId, int movieId, String review, int rating, int user_id, Timestamp modified) {

		this.reviewId = reviewId;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.user_id = user_id;
		this.modified = modified;
	}

	public Reviews(int reviewId2, String movie, String rating2, String userid) {
		
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", movieId=" + movieId + ", review=" + review + ", rating=" + rating
				+ ", user_id=" + user_id + ", modified=" + modified + "]";
	}

}