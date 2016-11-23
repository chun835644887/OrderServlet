package com.feicui.bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.feicui.mysql.DBTest;

public class OrderDao {
	/*
	 * String sql1 =
	 * "insert into usertbl(account,password,name,gender,permission,remark) values("
	 * + "'" + name + "','" + password + "','" + name + "','男','3','服务员')";
	 */
	DBTest dbTest;
	Connection connection;
	List<FoodType> list;
	String orderNum;
	String id;

	// String dateStr;

	public OrderDao(List<FoodType> list) {
		// TODO Auto-generated constructor stub
		dbTest = DBTest.getDBTest();
		connection = dbTest.getConnection();
		this.list = list;
	}

	/**
	 * 往ordtertbl插入数据
	 * 
	 * @param num
	 * @param table
	 * @param list
	 * @return
	 */

	public boolean insertOrderNum(String num, String table) {
		orderNum = num;
		float allPri=0;
		for (int i = 0; i <list.size(); i++) {
			float price = list.get(i).getAllPrice();
			allPri+=price;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateStr = formatter.format(new Date());
		for (int i = 0; i < list.size(); i++) {
			/*
			 * String sql1 =
			 * "insert into usertbl(account,password,name,gender,permission,remark) values("
			 * + "'" + name + "','" + password + "','" + name +
			 * "','男','3','服务员')";
			 */
			String sql = "insert into ordertbl(idUser,serial,orderTime,tableId,isPay,startprice) values('"
					+ "1','"
					+ num
					+ "','"
					+ dateStr
					+ "','"
					+ table
					+ "','0','" + allPri + "')";
			try {
				System.out.println(list.size() + sql);
				Statement statement = connection.createStatement();
				boolean b = statement.execute(sql);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 往orederdetailtbl添加数据
	 */

	public boolean insertOrderFood() {
		/*
		 * String sql1 =
		 * "insert into usertbl(account,password,name,gender,permission,remark) values("
		 * + "'" + name + "','" + password + "','" + name + "','男','3','服务员')";
		 */
		String sql1 = "select id from ordertbl where serial='" + orderNum + "'";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()) {
				resultSet.getString("id");
				id = resultSet.getString("id");
				System.out.println(id + "=====");
			}
			for (int i = 0; i < list.size(); i++) {
				/*
				 * String sql1 =
				 * "insert into usertbl(account,password,name,gender,permission,remark) values("
				 * + "'" + name + "','" + password + "','" + name +
				 * "','男','3','服务员')";
				 */
				System.out.println(list.get(i).getCount());
				String sql2 = "insert into orederdetailtbl (orderId,menuId,num,state,remark) values('"
						+ id
						+ "','"
						+ list.get(i).getfID()
						+ "','"
						+ list.get(i).getCount()
						+ "','0','"
						+ list.get(i).getUserRemark() + "')";
				System.out.println(sql2);
				boolean b = statement.execute(sql2);
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String updatePrice(List<FoodType> list, String num) {

		String sql = "select * from ordertbl where serial='" + num
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
				String price = resultSet.getString("startprice");
				id = resultSet.getString("id");
				float pri = Float.parseFloat(price);
				allPri += pri;
				System.out.println(allPri+"======");
				/**
				 * String
				 * sql4="update ordertbl set isPay='1' where serial='"+numStr
				 * +"';";
				 */
			}
			sql2 = "update ordertbl set startprice='" + allPri
					+ "' where serial='" + num + "' and isPay='0'";
			System.out.println(sql2);
			statement.execute(sql2);
			/*
			 * INSERT INTO orederdetailtbl(orderId,menuId,num,state)
			 * VALUES('3415','11','1','0');
			 */
			for (int i = 0; i < list.size(); i++) {
				String sql3 = "insert into orederdetailtbl(orderId,menuId,num,state,remark) VALUES('"
						+ id
						+ "','"
						+ list.get(i).getfID()
						+ "','"
						+ list.get(i).getCount()
						+ "','0','"
						+ list.get(i).getUserRemark() + "')";
				System.out.println(sql3);
				statement.execute(sql3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}
}
