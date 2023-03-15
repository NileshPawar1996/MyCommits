package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Blog;

public interface BlogDao {
	int addBlog(Blog blog, int userId, int categoryId) throws SQLException;
	
	List<Blog> fetchBlogs() throws SQLException;
	
}
