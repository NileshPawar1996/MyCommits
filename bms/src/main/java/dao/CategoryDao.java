package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Category;

public interface CategoryDao {
	List<Category> fetchCategories() throws SQLException;

	int addCategory(Category category) throws SQLException;
}
