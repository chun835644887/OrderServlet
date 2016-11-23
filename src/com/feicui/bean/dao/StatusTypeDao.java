package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class StatusTypeDao {

	DBTest dbTest;
	Connection connection;
	List<StatusType>list;

	public StatusTypeDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}

	public String getStatus() {
		list=new ArrayList<StatusType>();
		String num = null;
		String id=null;
		String status=null;
		String sql = "select * from ordertbl where isPay='0'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				StatusType type=new StatusType();
				num = resultSet.getString("serial");
				id=resultSet.getString("id");
				type.setId(id);
				type.setNum(num);
				list.add(type);
			}
//			for (int i = 0; i <list.size(); i++) {
//				String a=list.get(i).getId();
//				String sql1="select * from orederdetailtbl where orderId='"+a+"'";
//				ResultSet resultSet2 = statement.executeQuery(sql1);
//				while (resultSet2.next()) {
//					list.get(i).setfStatus(resultSet2.getString("state"));
//					list.get(i).setFoodNum(resultSet2.getString("menuId"));
//				}
//			}
//			for (int i = 0; i <list.size(); i++) {
//				String sql2="select * from menutbl where id='"+list.get(i).getFoodNum()+"'";
//				ResultSet resultSet3 = statement.executeQuery(sql2);
//				list.get(i).setfName(resultSet3.getString("menuname"));
//				list.get(i).setImageUrl(resultSet3.getString("pic"));
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String json=gson.toJson(list);
		return json;
	}
	
	public String getItemStatus(String id) {
		List<StatusType>typeList=new ArrayList<StatusType>();
		String sql1="select * from orederdetailtbl where orderId='"+id+"'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()) {
				StatusType type1=new StatusType();
				type1.setfStatus(resultSet.getString("state"));
				type1.setFoodNum(resultSet.getString("menuId"));
				typeList.add(type1);
			}
			for (int i = 0; i <typeList.size(); i++) {
				String sql2="select * from menutbl where id='"+typeList.get(i).getFoodNum()+"'";
				ResultSet resultSet2 = statement.executeQuery(sql2);
				while (resultSet2.next()) {
					typeList.get(i).setImageUrl(resultSet2.getString("pic"));
					typeList.get(i).setfName(resultSet2.getString("menuname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String jsonType=gson.toJson(typeList);
		return jsonType;
	}
}
