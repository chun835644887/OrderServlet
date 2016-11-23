package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.feicui.mysql.DBTest;

public class AddDao {

	DBTest dbTest;
	Connection connection;
	List<FoodType> list;

	public AddDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}

	public String updatePrice(List<FoodType> list, String table) {

		String sql = "select * from ordertbl where tableId='" + table
				+ "' and isPay='0'";
		String sql2 = "";
		String serial = null;
		String id = null;
		float allPri = 0.0f;
		for (int i = 0; i < list.size(); i++) {
			allPri += list.get(i).getAllPrice();
		}
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String price = resultSet.getString("startpaice");
				id = resultSet.getString("id");
				float pri = Float.parseFloat(price);
				allPri += pri;
				/**
				 * String
				 * sql4="update ordertbl set isPay='1' where serial='"+numStr
				 * +"';";
				 */
			}
			sql2 = "update ordertbl set startpaice='" + allPri
					+ "' where where tableId='" + table + "' and isPay='0'";
			statement.execute(sql2);
			/*
			 * INSERT INTO orederdetailtbl(orderId,menuId,num,state)
			 * VALUES('3415','11','1','0');
			 */
			for (int i = 0; i < list.size(); i++) {
				String sql3 = "INTO orederdetailtbl(orderId,mennuId,num,state,remark) VALUES('"
						+ id
						+ "','"
						+ list.get(i).getfID()
						+ "','"
						+ list.get(i).getCount()
						+ "','0'"
						+ list.get(i).getUserRemark() + "')";
				statement.execute(sql3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}

	public void update() {
		String sql = "select * from ";
	}
}
