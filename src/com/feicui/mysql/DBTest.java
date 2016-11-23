package com.feicui.mysql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBTest {
	private Connection connection;
	static DBTest dbTest;
	private DBTest() {
		// TODO Auto-generated constructor stub
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("com/feicui/mysql/db.properties");
		Properties properties=new Properties();
			try {
				properties.load(is);
				String driver=properties.getProperty("driver");
				String url=properties.getProperty("url");
				String name=properties.getProperty("user");
				String pwd=properties.getProperty("password");
				Class.forName(driver);
				this.connection=DriverManager.getConnection(url, name, pwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static DBTest getDBTest() {
		if(dbTest==null){
			dbTest=new DBTest();
		}
		return dbTest;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
