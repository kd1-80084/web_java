package daos;

import java.sql.Connection;
import java.sql.SQLException;

import utils.DbUtil;

public class Dao implements AutoCloseable {
	public Connection con;

	public Dao() throws Exception {
		con = DbUtil.getConnection();
	}

	@Override
	public void close() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

}
