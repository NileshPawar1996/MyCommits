package beans;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.BlogDaoImpl;
import pojos.Blog;

public class BlogBean {
	private int id;
	private String title;
	private String contents;
	private LocalDate createdTime;
	private String userId;
	private String categoryId;
	private BlogDaoImpl blogDao;

	public BlogBean() throws SQLException {
		blogDao = new BlogDaoImpl();
		System.out.println("BlogDaoImpl instance created!!!");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDate getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDate createdTime) {
		this.createdTime = createdTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BlogDaoImpl getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDaoImpl blogDao) {
		this.blogDao = blogDao;
	}

	public String saveMyBlog() throws SQLException {
		int cnt = blogDao.addBlog(new Blog(title, contents), Integer.parseInt(userId), Integer.parseInt(categoryId));
		return "addBlog";
	}

	public List<Blog> getAllBlogs() throws SQLException {
		return blogDao.fetchBlogs();
	}

}
