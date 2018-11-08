package cn.itcast.demo5;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.demo4.User;

/**
 * URL级别全选验证
 */
public class CheckUserUrlFilter implements Filter{

	private FilterConfig config;

	/**
	 * 初始化的操作，获取初始化参数，读取到Map集合中。存入ServletContext中。
	 */
	public void init(FilterConfig config) throws ServletException {
		// 创建Map保存信息
		Map<String, String> urlMap = new HashMap<String, String>();
		// 获取初始化参数，把参数的内容保存到urlMap中
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			// 获取到<param-name>的值
			String paramName = e.nextElement();
			// 获取到的是<param-value>的值
			String paramValue = config.getInitParameter(paramName);
			// 存入Map集合中 	{/admin:admin}	{/user:user}
			urlMap.put(paramName, paramValue);
		}
		// 存入到ServletContext中
		config.getServletContext().setAttribute("urlMap", urlMap);
		this.config = config;
	}

	/**
	 * 权限的验证
	 * 	* 从请求的链接中拿到链接和urlMap中的链接和type类型做对比
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 先获取请求的链接		请求：/admin/add.jsp		map {/admin:admin}	{/user:user}
		
		HttpServletRequest req = (HttpServletRequest) request;
		// /day21/admin/add.jsp
		// String uri = req.getRequestURI();
		// System.out.println(uri);
		// 
		// 获取请求的链接
		String sPath = req.getServletPath();
		// System.out.println(sPath);
		// 和Map来进行对比
		ServletContext context = config.getServletContext();
		// 获取Map的集合了
		Map<String, String> urlMap = (Map<String, String>) context.getAttribute("urlMap");
		
		// 做对比 set的key值 /admin  /user
		Set<String> set = urlMap.keySet();
		// 循环 /admin
		for (String paramName : set) {
			// /admin/add.jsp从/admin开始的
			if(sPath.startsWith(paramName)){
				// 去获取session的用户信息，从用户的信息中获取type属性的值，和Map中的value的值进行对比。
				User user = (User) req.getSession().getAttribute("existUser");
				// 如果user为null
				if(user == null){
					// 转发到登陆的页面上
					req.getRequestDispatcher("/demo4/login.jsp").forward(req, response);
					return;
				}else{
					// 去判断用户的type的值和我Map中的value的值做对比
					String userType = user.getType();
					
					// 还有一个Map中存了一个值  {/admin  admin} {/user  user}
					String paramValue = urlMap.get(paramName);
					
					if(userType.equals(paramValue)){
						// 匹配成功了	/admin/add.jsp  做过一次判断了，已/admin开头的，
						// 从用户的信息中获取type 的值和Map的值做了对比
						chain.doFilter(req, response);
						return;
					}else{
						// 向用户提示
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("<h3>亲，您权限不够！！</h3>");
						return;
					}
				}
			}
		}
		
		chain.doFilter(req, response);
		
	}

	public void destroy() {
		
	}

}
