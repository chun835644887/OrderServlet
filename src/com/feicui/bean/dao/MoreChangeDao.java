package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.UserTransaction;

import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class MoreChangeDao {

	DBTest dbTest;
	Connection connection;

	public MoreChangeDao() {
		// TODO Auto-generated constructor stub
		DBTest dbTest = DBTest.getDBTest();
		this.connection = dbTest.getConnection();
	}

	public String getFreeTable() {
		String getFree = "select num from tabletbl";
		String sql = "select * from ordertbl where isPay='0'";
		List<Integer> allTable = new ArrayList<Integer>();
		List<Integer> useTable = new ArrayList<Integer>();
		List<Integer>freeTable=new ArrayList<Integer>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getFree);
			while (resultSet.next()) {
				allTable.add(resultSet.getInt("num"));
			}
			ResultSet resultSet1 = statement.executeQuery(sql);
			while (resultSet1.next()) {
				useTable.add(resultSet1.getInt("tableId"));
			}
			for (int i = 0; i <useTable.size(); i++) {
				for (int j = 0; j <allTable.size(); j++) {
					if(useTable.get(i)==allTable.get(j)){
						allTable.remove(j);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String table=gson.toJson(allTable);
		return table;
	}

	public void changeTable(String tableNum) {
		/**
		 * String
		 * sql4="update ordertbl set isPay='1' where serial='"+numStr+"';";
		 */
		int tableId = Integer.parseInt(tableNum);
		String updateTable = "update ordertbl set tableId='" + tableId + "'";
		List<Integer> table = new ArrayList<Integer>();
		try {
			Statement statement = connection.createStatement();
			statement.execute(updateTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
