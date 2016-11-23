package com.feicui.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.bean.dao.MoreChangeDao;

public class MoreChangeServlet extends HttpServlet {

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
		MoreChangeDao changeDao = new MoreChangeDao();
			String tableNum = request.getParameter("tableNum");
			changeDao.changeTable(tableNum);
			out.print("»»×À³É¹¦");

		out.flush();
		out.close();
	}

}
