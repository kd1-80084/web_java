package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pojos.Movies;

public class MoviesDaoImpl extends Dao implements MoviesDao{

	public MoviesDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Movies> findAll() throws Exception {
		List<Movies> list = new ArrayList<>();
		String sql = "SELECT * FROM movies";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					java.sql.Date date = rs.getDate("releasedate");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dat = sdf.format(date);
					Movies m = new Movies(id, title, dat);
					list.add(m);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

	@Override
	public Movies findById(int id) throws Exception {
		String sql = "SELECT * FROM movies WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					id = rs.getInt("id");
					String title = rs.getString("title");
					java.sql.Date date = rs.getDate("releasedate");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dat = sdf.format(date);
					Movies m = new Movies(id, title, dat);
					return m;
				}
			} // rs.close();
		} // stmt.close();

		return null;
	}

}
