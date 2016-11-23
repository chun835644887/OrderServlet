package com.feicui.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.bean.dao.UserDao;

public class Launch extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Launch() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("GBK");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		String userName=request.getParameter("name");
		String pwd=request.getParameter("password");
		UserDao userDao=new UserDao();
		boolean is=userDao.register(userName, pwd);
		if(!is){
			out.print("1");
		}else{
			out.print("0");
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
        request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");
        String name=request.getParameter("name");
        String pwd=request.getParameter("password");
        UserDao userDao=new UserDao();
        boolean isTrue=userDao.launch(name, pwd);
        System.out.println(isTrue);
        if(isTrue){
//        	 new String("成功".getBytes("gb2312"), "utf-8");
        	out.print(new String("成功".getBytes(), "utf-8"));
        }else{
        	out.print(new String("失败".getBytes(), "utf-8"));
        }
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
