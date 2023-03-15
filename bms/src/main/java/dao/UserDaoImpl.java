package dao;

import static utils.DBUtils.getConn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import pojos.User;

public class UserDaoImpl implements UserDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	// constructor
	public UserDaoImpl() throws SQLException {
		// getting connection from db utils
		cn = getConn();
		// pst1
		String sql1 = "select * from users where email=? and password=?";
		pst1 = cn.prepareStatement(sql1);
		String sql2 = "insert into users(full_name,email,password,phone_no,created_time) values(?,?,?,?,?)";
		pst2 = cn.prepareStatement(sql2);
		System.out.println("user dao created!");

	}

	@Override
	public User authenticateUser(String email, String pwd) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, pwd);
		LocalDate date = null;
		try (ResultSet rst = pst1.executeQuery()) {
//			 id | full_name | email           | password | phone_no   | created_time
			if (rst.next()) {
//				date = rst.getDate(6).toLocalDate();
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						rst.getDate(6).toLocalDate());
			}
			return null;
		}
	}

	@Override
	public int saveUser(User user) throws SQLException {
//		String sql2 = "insert into users(full_name,email,password,phone_no) values(?,?,?,?)";
		LocalDate now = LocalDate.now();
		pst2.setString(1, user.getFullName());
		pst2.setString(2, user.getEmail());
		pst2.setString(3, user.getPassword());
		pst2.setString(4, user.getPhone_no());
		pst2.setDate(5, Date.valueOf(now));
		return pst2.executeUpdate();

	}

	// clean up dao
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		System.out.println("user dao cleaned up!");
	}
}
