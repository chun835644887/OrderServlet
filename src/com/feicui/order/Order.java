package com.feicui.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.bean.dao.FoodType;
import com.feicui.bean.dao.OrderDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Order extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("gbk");
		request.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
//		Gson gson=new Gson();
//		String strList = request.getParameter("list");
//		String strNum = request.getParameter("num");
//		String table=request.getParameter("table");
//		List<FoodType>list=gson.fromJson(strList, new TypeToken<List<FoodType>>() {
//		}.getType());
//		OrderDao orderDao=new OrderDao(list);
//		boolean b = orderDao.insertOrderNum(strNum, table);
//		boolean c = orderDao.insertOrderFood();
//		System.out.println(b+""+c+"");
//		if(b==true&&c==true){
//			out.print("1");
//		}else{
//			out.print("0");
//		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gbk");
		request.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		Gson gson=new Gson();
		String strList = request.getParameter("list");
		String strNum = request.getParameter("num");
		String table=request.getParameter("table");
		String add=request.getParameter("add");
		List<FoodType>list=gson.fromJson(strList, new TypeToken<List<FoodType>>() {
		}.getType());
		OrderDao orderDao=new OrderDao(list);
//		System.out.println(list+"----"+strNum+"======"+table);
		if("1".equals(add)){
			orderDao.updatePrice(list, strNum);
		}else{
			boolean b = orderDao.insertOrderNum(strNum, table);
			boolean c = orderDao.insertOrderFood();
		}
//		System.out.println(b+""+c+"-------");
//		if(b==true&&c==true){
//			out.print("1");
//		}else{
//			out.print("0");
//		}
		out.flush();
		out.close();
	}

}
