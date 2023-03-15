package beans;

import java.sql.SQLException;
import java.util.List;

import dao.CategoryDaoImpl;
import pojos.Category;

public class CategoryBean {
	private int id;
	private String title;
	private String description;
	private CategoryDaoImpl catDao;

	public CategoryBean() throws SQLException {
		this.catDao = new CategoryDaoImpl();
		System.out.println("CategoryBean instance created");
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDaoImpl getCatDao() {
		return catDao;
	}

	public void setCatDao(CategoryDaoImpl catDao) {
		this.catDao = catDao;
	}

	public List<Category> getAllCategories() throws SQLException {
		return catDao.fetchCategories();
	}

	public String putCategory() throws SQLException {
		int cnt = catDao.addCategory(new Category(title, description));
		if (cnt != 0)
			return "addCategory";
		else
			return "";

	}
}