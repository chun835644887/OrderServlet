package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class WithTableDao {

	Connection connection;
	DBTest dbTest;
	String swiftNum;
	int table;

	public WithTableDao() {
		dbTest = DBTest.getDBTest();
		this.connection = dbTest.getConnection();
	}

	public String getUserTable() {
		List<Integer>useList=new ArrayList<Integer>();
		String getUTable = "select * from ordertbl where isPay='0'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getUTable);
			while (resultSet.next()) {
				useList.add(resultSet.getInt("tableId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String useTStr = gson.toJson(useList);
		return useTStr;
	}
	/**
	 * 
	 * @param swiftNum1
	 * @param swiftNum2
	 * @param table2
	 */
	public void updateWithTable(String swiftNum1,String swiftNum2,int table2) {
		table=table2;
		String sql1="select * from ordertbl where serial='"+swiftNum1+"'";
		String sql2="select * from ordertbl where serial='"+swiftNum2+"'";
		float newAllPri=0.0f;
		int orderId1=0;
		int orderId2=0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()) {
				orderId1=resultSet.getInt("id");
				newAllPri+=resultSet.getFloat("startprice");
			}
			ResultSet resultSet2 = statement.executeQuery(sql2);
			while (resultSet2.next()) {
				newAllPri+=resultSet.getFloat("startprice");
				orderId2=resultSet2.getInt("id");
			}
			String sql3="select * from orederdetailtbl where orderId='"+orderId1+"'";
			String sql4="select * from orederdetailtbl where orderId='"+orderId2+"'";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取流水号
	 */
	public void getSwiftNum() {
			Random random=new Random();
			int a=random.nextInt(900)+100;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			swiftNum = formatter.format(new Date()) + "0000" + table+a;
	}
}
