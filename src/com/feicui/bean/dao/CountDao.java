package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class CountDao {
	
	DBTest dbTest;
	Connection connection;
	List<CountType>list;

	public CountDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}

	public String getNum() {
		list=new ArrayList<CountType>();
		String sql = "select * from ordertbl where isPay='0'";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				  String num = resultSet.getString("serial");
				  String table=resultSet.getString("tableId");
				  CountType countType=new CountType();
				  countType.setNum(num);
				  countType.setTable(table);
				  list.add(countType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	public boolean delNum(String numStr) {
		
		String sql3="select * from ordertbl where serial='"+numStr+"';";
		System.out.println(sql3);
		String orderId=null;
//		String sql1="delete from ordertbl where serial='"+numStr+"'";
		/*
		 * update students set stu_name = "zhangsan", stu_gender = "m" where stu_id = 5
		 */
		String sql4="update ordertbl set isPay='1' where serial='"+numStr+"';";
		try {
			Statement statement1 = connection.createStatement();
			ResultSet resultSet = statement1.executeQuery(sql3);
			while (resultSet.next()) {
				/**
				 * 获取ordertbl中orderId的值
				 */
				orderId=resultSet.getString("id");
			}
			/**
			 * 删除表ordertbl中的数据
			 */
			statement1.execute(sql4);
			/**
			 * 删除表orederdetailtbl中的数据
			 */
			String sql2="delete from orederdetailtbl where orderId='"+orderId+"'";
			statement1.execute(sql2);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
