package cn.itcast.demo1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingServlet extends HttpServlet {

	/**
	 * ��̨���ñ���ĸ�д
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ��ȡ����
		// request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		
		System.out.println("�û�����"+username);
		
		// ��ҳ�����
		// response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().write("<h3>"+username+"</h3>");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
