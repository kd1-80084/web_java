
package pojos;

import java.util.Date;

public class Movies {
	private int movieId;
	private String movieTitle;
	private String releasedate;

	public Movies() {
	}

	public Movies(int movieId, String movieTitle, String releasedate) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.releasedate = releasedate;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getReleaseDate() {
		return releasedate;
	}

	public void setReleaseDate(String releasedate) {
		this.releasedate = releasedate;
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", movieTitle=" + movieTitle + ", releaseDate=" + releasedate + "]";
	}

}