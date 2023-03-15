package beans;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.UserDaoImpl;
import pojos.Blog;
import pojos.User;

public class UserBean {
	private String email;
	private String password;
	private String message;
	private int id;
	private String fullName;
	private String phoneNo;
	private LocalDate createdTime;
	private UserDaoImpl userDao;
	private User locatedUser;

	public UserBean() throws SQLException {
		userDao = new UserDaoImpl();
		System.out.println("userDaoImpl instance is created!");
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public LocalDate getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDate createdTime) {
		this.createdTime = createdTime;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getLocatedUser() {
		return locatedUser;
	}

	public void setLocatedUser(User locatedUser) {
		this.locatedUser = locatedUser;
	}

	public String validateUser() throws SQLException {
		locatedUser = userDao.authenticateUser(email, password);
		if (locatedUser != null) {
			return "blogs";
		} else
			return "signup";
	}

	public String registerUser() throws SQLException {
		User user = new User(0, fullName, email, password, phoneNo, null);
		int cnt = userDao.saveUser(user);
		if (cnt != 0) {
			System.out.println("User inserted");
			return "signin";
		} else {
			return "signup";
		}
	}

	public List<Blog> getAllBlogs() {
		List<Blog> blogs =null;
		
		return null;
	}
	
}
