package daos;

import java.util.List;

import pojos.Reviews;

public interface ReviewDao extends AutoCloseable{
	public int save(Reviews r) throws Exception;
	public int update(Reviews r) throws Exception;
	public List<Reviews> findAll() throws Exception ;
	public List<Reviews> findByUserId(int userId) throws Exception;
	public List<Reviews> getSharedWithUser(int userId) throws Exception;
	public Reviews findById(int id) throws Exception;
	public int deleteById(int reviewId) throws Exception;
	public int shareReview(int reviewId, int userId) throws Exception;
	public List<Reviews> sharedWithMe(int id) throws Exception;
}
