package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pojos.Users;

public class UsersDaoImpl extends Dao implements UserDao{

	public UsersDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int save(Users u) throws Exception {
		String sql = "INSERT INTO users(id, first_name, last_name, email, mobile, birth, password) VALUES(default, ?, ?, ?, ?, ?, ?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate = sdf.parse(u.getDob());
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_Name());
			stmt.setString(2, u.getLast_Name());
			stmt.setString(3, u.getEmail());
			stmt.setLong(4, u.getMobileNo());
			stmt.setDate(5, sDate);
			stmt.setString(6, u.getPassword());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	@Override
	public int update(Users u) throws Exception {
		String sql = "UPDATE users SET first_name=?, last_name=?, mobile=?, birth=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_Name());
			stmt.setString(2, u.getLast_Name());
			stmt.setString(3, u.getEmail());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date uDate = sdf.parse(u.getDob());
			java.sql.Date sDate = new java.sql.Date(uDate.getTime());
			stmt.setInt(5, u.getId());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	@Override
	public int updatePassword(int userId, String newPassword) throws Exception {
		String sql = "UPDATE users SET password=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, newPassword);
			stmt.setInt(2, userId);
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	@Override
	public Users findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=? ";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					email = rs.getString("email");
					String mob = rs.getString("mobile");
					long mobile = Long.parseLong(mob);
					java.sql.Date bdate = rs.getDate("birth");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					String date = sdf.format(bdate);
					return new Users(id,fname,lname,email,password,mobile,date);
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}

	

	@Override
	public List<Users> findAll() throws Exception {
		List<Users> list = new ArrayList<Users>();
		String sql = "SELECT * FROM users";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String mob = rs.getString("mobile");
					long mobile = Long.parseLong(mob);
					java.sql.Date bdate = rs.getDate("birth");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					String date = sdf.format(bdate);
					Users u = new Users(id,fname,lname,email,password,mobile,date);
					list.add(u);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

}
