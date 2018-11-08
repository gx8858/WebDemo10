package cn.itcast.demo4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆的功能
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8579027867816374707L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// 获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		User existUser = dao.findUser(username, password);
		if(existUser == null){
			// 给提示
			request.getRequestDispatcher("/demo4/login.jsp").forward(request, response);
		}else{
			
			// 把用户名和密码保存到cookie中，回写到浏览器。
			String autologin = request.getParameter("autologin");
			// 下一次自动登陆，把你的用户名和密码保存起来
			if("auto_ok".equals(autologin)){
				// 创建cookie，回写
				Cookie cookie = new Cookie("autologin",username+"#itcast#"+password);
				// 设置有效时间
				cookie.setMaxAge(60*60);
				// 回写
				response.addCookie(cookie);
			}
			// 把用户的信息保存到session中
			request.getSession().setAttribute("existUser", existUser);
			response.sendRedirect(request.getContextPath()+"/demo4/suc.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}






