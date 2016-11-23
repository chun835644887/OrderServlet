package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.feicui.bean.dao.idao.IUserDao;
import com.feicui.mysql.DBTest;

public class UserDao {

	DBTest dbTest;
	Connection connection;

	public UserDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}

	/*
	 * 判断账号密码是否正确
	 */
	public Boolean launch(String name, String password) {
		String sql1 = "select * from usertbl" + " where account='"
				+ name + "' and password='" + password + "'";
		System.out.println(sql1);
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql1);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 判断注册账号是否已存在
	 */
	public Boolean register(String name, String password) {
		// TODO Auto-generated method stub
		// insert into 表名(列名，...) values(列的值，...);
		String sql = "select * from " + IUserDao.CUSTOM + " where accout='"
				+ name + "'";
		/*
		 * String sql1 =
		 * "insert into usertbl(account,password,name,gender,permission,remark) values("
		 * + "'" + name + "','" + password + "','" + name + "','男','3','服务员')";
		 */
		String sqlRegister = "insert into " + IUserDao.CUSTOM
				+ "(accout,pwd,name,phonenumber,gender,date,level,consumption) values('" + name + "','" + password
				+ "','','','','','','')";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			} else {
				boolean s = st.execute(sqlRegister);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void getTable() {
		
	}
}
