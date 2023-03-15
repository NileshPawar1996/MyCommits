package pojos;

import java.time.LocalDate;

public class Blog {
	private int blogId;
	private String title;
	private String contents;
	private LocalDate createdTime;
	private int userId;
	private int categoryId;

	public Blog() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Blog(int blogId, String title, String contents, LocalDate createdTime, int userId, int categoryId) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.contents = contents;
		this.createdTime = createdTime;
		this.userId = userId;
		this.categoryId = categoryId;
	}



	public Blog(String title, String contents) {
		super();
		this.title = title;
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getblogId() {
		return blogId;
	}

	public void setblogId(int blogId) {
		this.blogId = blogId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", contents=" + contents + ", createdTime=" + createdTime + ", userId="
				+ userId + ", categoryId=" + categoryId + "]";
	}

}
