package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import static utils.DBUtils.*;

import java.sql.SQLException;

@WebListener
public class DBListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext closed!!!");
		try {
			closeConnection();
			System.out.println("DB connection closed!!!");
		} catch (SQLException e) {
			System.out.println("Error while closing the connection");
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext cntx = sce.getServletContext();
		System.out.println("ServletContext initialized");
		try {
			openConnection(cntx.getInitParameter("url"), cntx.getInitParameter("user"),
					cntx.getInitParameter("password"));
			System.out.println("DB Connection success!!!");
		} catch (SQLException e) {
			System.out.println("Error in opening connectoin!!! at class " + getClass());
		}
	}
}