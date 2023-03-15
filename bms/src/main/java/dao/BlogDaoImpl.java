package dao;

import static utils.DBUtils.getConn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pojos.Blog;

public class BlogDaoImpl implements BlogDao {

	private Connection cn;
	private PreparedStatement pst1, pst2;

	// constructor
	public BlogDaoImpl() throws SQLException {
		// getting connection from db utils
		cn = getConn();
		// pst1
		String sql1 = "insert into blogs(title,contents,created_time,user_id,category_id) values(?,?,?,?,?)";
		pst1 = cn.prepareStatement(sql1);
		String sql2 = "select * from blogs";
		pst2 = cn.prepareStatement(sql2);
		System.out.println("Category dao created!");

	}

	@Override
	public int addBlog(Blog blog, int userId, int categoryId) throws SQLException {
		LocalDate now = LocalDate.now();
		pst1.setString(1, blog.getTitle());
		pst1.setString(2, blog.getContents());
		pst1.setDate(3, Date.valueOf(now));
		pst1.setInt(4, userId);
		pst1.setInt(5, categoryId);
		return pst1.executeUpdate();
	}

	@Override
	public List<Blog> fetchBlogs() throws SQLException {
		List<Blog> blogs = new ArrayList<Blog>();
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
//				(int blogId, String title, String contents, LocalDate createdTime, int userId, int categoryId
				blogs.add(new Blog(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4).toLocalDate(),
						rst.getInt(5), rst.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in BlogDaoImpl's fetchBlogs method");
		}
		System.out.println("Inside BlogDaoImpl fetchAll");
		return blogs;
	}

}
