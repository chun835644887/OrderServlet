package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feicui.bean.dao.idao.IUserDao;
import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class MoreCheckDao {
	

	List<FoodType>list;
	DBTest dbTest;
	Connection connection;
	
	public MoreCheckDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}

	public String getOrderInfo(String swiftNum) {
		String sqlGetId="select id from ordertbl where serial='"+swiftNum+"'";
		String orderId=null;
		List<FoodType>list=new ArrayList<FoodType>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlGetId);
			while (resultSet.next()) {
				orderId=resultSet.getString("id");
			}
			String sqlGetOrder="select * from orederdetailtbl where orderId='"+orderId+"'";
			ResultSet resultSet2 = statement.executeQuery(sqlGetOrder);
			while (resultSet2.next()) {
				FoodType type=new FoodType();
				type.setfID(resultSet2.getString("menuId"));
				type.setCount(Integer.parseInt(resultSet2.getString("num")));
				type.setUserRemark(resultSet2.getString("remark"));
				list.add(type);
			}
			for (int i = 0; i <list.size(); i++) {
				String sql="select * from menutbl where id='"+list.get(i).getfID()+"'";
				ResultSet resultSet3 = statement.executeQuery(sql);
				while (resultSet3.next()) {
					list.get(i).setfType(resultSet3.getString(IUserDao.CATEGORY));
					list.get(i).setfName(resultSet3.getString(IUserDao.MENUNAME));
					list.get(i).setfPrice(resultSet3.getString(IUserDao.PRICE));
					list.get(i).setfRemark(resultSet3.getString(IUserDao.REMARK));
					list.get(i).setfPic(resultSet3.getString(IUserDao.PIC));
					list.get(i).setfID(resultSet3.getString(IUserDao.ID));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		return gson.toJson(list);
	}
}
