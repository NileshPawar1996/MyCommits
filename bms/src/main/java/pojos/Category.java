package pojos;

public class Category {
//	id | title      | description
	private int categoryId;
	private String title;
	private String description;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	
	public Category(int categoryId, String title, String description) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
	}


	public Category(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setcategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", title=" + title + ", description=" + description + "]";
	}
}
