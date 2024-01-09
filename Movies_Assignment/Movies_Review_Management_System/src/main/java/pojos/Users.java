package pojos;

import java.util.Date;

public class Users {
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private long mobile;
	private String birth;

	public Users() {

	}

	public Users(int id,String first_name, String last_name, String email, String password, long mobile, String birth) {

		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.birth = birth;
	}


	public int getId() {
		return id;
	}

	public void setUserId(int id) {
		this.id = id;
	}

	public String getFirst_Name() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLast_Name() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNo() {
		return mobile;
	}

	public void setMobileNo(long mobileNo) {
		this.mobile = mobileNo;
	}

	public String getDob() {
		return birth;
	}

	public void setDob(String dob) {
		this.birth = dob;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", birth=" + birth + "]";
	}

}