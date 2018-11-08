package cn.itcast.demo4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��½�Ĺ���
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8579027867816374707L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		User existUser = dao.findUser(username, password);
		if(existUser == null){
			// ����ʾ
			request.getRequestDispatcher("/demo4/login.jsp").forward(request, response);
		}else{
			
			// ���û��������뱣�浽cookie�У���д���������
			String autologin = request.getParameter("autologin");
			// ��һ���Զ���½��������û��������뱣������
			if("auto_ok".equals(autologin)){
				// ����cookie����д
				Cookie cookie = new Cookie("autologin",username+"#itcast#"+password);
				// ������Чʱ��
				cookie.setMaxAge(60*60);
				// ��д
				response.addCookie(cookie);
			}
			// ���û�����Ϣ���浽session��
			request.getSession().setAttribute("existUser", existUser);
			response.sendRedirect(request.getContextPath()+"/demo4/suc.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}






