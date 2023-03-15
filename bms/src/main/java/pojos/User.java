package pojos;

import java.time.LocalDate;

public class User {
//	 id | full_name | email           | password | phone_no   | created_time

	private int userId;
	private String fullName;
	private String email;
	private String password;
	private String phone_no;
	private LocalDate createdTime;

	public User() {
		
	}


	public User(int userId, String fullName, String email, String password, String phone_no, LocalDate createdTime) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
		this.createdTime = createdTime;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public LocalDate getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDate createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phone_no=" + phone_no
				+ ", createdTime=" + createdTime + "]";
	}

}
