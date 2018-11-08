package cn.itcast.demo4;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自动登陆的功能
 */
public class AutoLoginFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 *  在过滤器中可以直接获取session中的用户信息，如果user不为空，说明浏览器没关。放行。
		  * 从session中获取不到user的信息
		  * 先获取cookie，获取指定名称的cookie，
		  * 如果cookie为空，放行。
		  * 如果cookie不为空，获取用户名和密码。去数据库查询。
		  * 如果查询不到，cookie的信息不正确，放行（没有存入session中）。
		  * 如果查询到了，cookie中的信息是正确，把用户的信息保存到session中。放行。
		 */
		// 从session中获取用户的信息
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		// 从session获取用户的信息
		User user = (User) session.getAttribute("existUser");
		// 如果浏览器没关闭，session中的用户信息不为null的
		if(user != null){
			chain.doFilter(req, response);
		}else{
			// session中没有用户的信息
			// 获取指定名称的cookie
			Cookie [] cookies = req.getCookies();
			// 获取到cookie，就可以进行判断了
			Cookie cookie = getCookieByName(cookies,"autologin");
			// 如果cookie为null
			// 在你的浏览器中，根本就没有autologin的cookie
			if(cookie == null){
				// 直接放行	自己访问suc.jsp（因为suc.jsp已经做过处理了，没有session默认让你去登陆）了。
				chain.doFilter(req, response);
			}else{
				// 从cookie中获取用户名和密码，去数据中查询
				String username = cookie.getValue().split("#itcast#")[0];
				String password = cookie.getValue().split("#itcast#")[1];
				// 你需要去数据库中进行查询
				UserDao dao = new UserDao();
				// 去数据库中查询指定名称和密码的用户
				User existUser = dao.findUser(username, password);
				// 查询出的用户为null
				if(existUser == null){
					// 放行
					chain.doFilter(req, response);
				}else{
					// 存入到session中
					session.setAttribute("existUser", existUser);
					// 放行
					chain.doFilter(req, response);
				}
			}
		}
	}
	
	
	public Cookie getCookieByName(Cookie [] cookies,String cookieName){
		if(cookies == null){
			return null;
		}else{
			for (Cookie cookie : cookies) {
				// 判断
				if(cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
			return null;
		}
	}
	

	public void destroy() {
		
	}

}







