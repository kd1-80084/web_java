package daos;

public interface ShareDao extends AutoCloseable{
	public void add(int rid,int uid) throws Exception;
	public void delete(int rid) throws Exception;
	
}
