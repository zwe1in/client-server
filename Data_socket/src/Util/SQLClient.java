package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class SQLClient {
	static {
		ClassLoader classLoader = SQLClient.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("db.propertices");
		Properties properties = new Properties();
		try {
			properties.load(resourceAsStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}
	private static String driver /*= "com.mysql.jdbc.Driver"*/ ;
	private static String url /*= "jdbc:mysql://localhost:3306/data3"*/;
	private static String username /*= "zw"*/ ;
	private static String password /*= "lovxzy"*/ ;
	
	public static Connection getConnection() {
		Connection connection =null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url,username,password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void release(Connection connection , Statement stm , ResultSet rs) {
		if(rs != null)try {
			rs.close();
		}catch(SQLException e){
				e.printStackTrace();
			}
		if(stm != null)try {
			stm.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(connection != null)try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void release(Connection connection , Statement stm ) {
		if(stm != null)try {
			stm.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(connection != null)try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
