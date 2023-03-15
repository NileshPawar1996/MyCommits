package dao;

import static utils.DBUtils.getConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Category;
import pojos.User;

public class CategoryDaoImpl implements CategoryDao {

	private Connection cn;
	private PreparedStatement pst1, pst2;

	// constructor
	public CategoryDaoImpl() throws SQLException {
		// getting connection from db utils
		cn = getConn();
		// pst1
		String sql1 = "select * from categories";
		pst1 = cn.prepareStatement(sql1);
		// id | title | description
		String sql2 = "insert into categories(title,description) values(?,?)";
		pst2 = cn.prepareStatement(sql2);
		System.out.println("Category dao created!");

	}

	@Override
	public List<Category> fetchCategories() throws SQLException {
		List<Category> categories = new ArrayList<Category>();
		try (ResultSet rst = pst1.executeQuery()) {
//			 id | full_name | email           | password | phone_no   | created_time
			while (rst.next()) {
//				date = rst.getDate(6).toLocalDate();
				categories.add(new Category(rst.getInt(1), rst.getString(2), rst.getString(3)));
			}
		}
		return categories;
	}

	@Override
	public int addCategory(Category category) throws SQLException {
		pst2.setString(1, category.getTitle());
		pst2.setString(2, category.getDescription());

		return pst2.executeUpdate();
	}

}
