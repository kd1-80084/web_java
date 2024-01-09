package daos;

import java.util.List;

import pojos.Movies;

public interface MoviesDao extends AutoCloseable{
	public List<Movies> findAll() throws Exception;
	public Movies findById(int id) throws Exception;
}
