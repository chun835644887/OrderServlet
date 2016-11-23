package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feicui.bean.dao.idao.IUserDao;
import com.feicui.mysql.DBTest;
import com.google.gson.Gson;

public class FoodTypeDao {

	List<FoodType>list;
	DBTest dbTest;
	Connection connection;
	
	public FoodTypeDao() {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
	}
	
	public List<FoodType> getData() {
		String sql="SELECT * FROM menutbl";
		list=new ArrayList<FoodType>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				FoodType fd=new FoodType();
				fd.setfType(resultSet.getString(IUserDao.CATEGORY));
				fd.setfName(resultSet.getString(IUserDao.MENUNAME));
				fd.setfPrice(resultSet.getString(IUserDao.PRICE));
				fd.setfRemark(resultSet.getString(IUserDao.REMARK));
				fd.setfUnits(resultSet.getString(IUserDao.UNITS));
				fd.setfVersion(resultSet.getString(IUserDao.VERSION));
				fd.setfPic(resultSet.getString(IUserDao.PIC));
				fd.setfID(resultSet.getString(IUserDao.ID));
				list.add(fd);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
