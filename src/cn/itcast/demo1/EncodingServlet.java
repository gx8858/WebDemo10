package cn.itcast.demo1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingServlet extends HttpServlet {

	/**
	 * 后台设置编码的改写
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取数据
		// request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		
		System.out.println("用户名："+username);
		
		// 向页面输出
		// response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().write("<h3>"+username+"</h3>");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
