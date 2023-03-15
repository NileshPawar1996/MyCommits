package utils;

import java.sql.*;

public class DBUtils {
	private static Connection conn;

	// open db connection
	public static void openConnection(String url,String userName,String password) throws SQLException{
//		String url = "jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		conn = DriverManager.getConnection(url, userName,password);
		System.out.println("db cn established !");
	}
	//get cn

	public static Connection getConn() {
		return conn;
	}
	//close cn
	public static void closeConnection()  throws SQLException
	{
		if(conn != null)
			conn.close();
		System.out.println("db cn closed !");
	}

}
