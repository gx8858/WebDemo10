package cn.itcast.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo3 implements Filter{

	/**
	 * 使用FilterConfig来获取一些信息
	 */
	public void init(FilterConfig config) throws ServletException {
		// 获取filter配置的名称<filter-name>
		String filterName = config.getFilterName();
		System.out.println("过滤器的配置名称："+filterName);
		// 获取初始化参数
		String username = config.getInitParameter("username");
		String password = config.getInitParameter("password");
		System.out.println(username+" : "+password);
		
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			// username和password
			String name = e.nextElement();
			// 获取username的值
			String value = config.getInitParameter(name);
			System.out.println(name+" : "+value);
		}
		
		// 获取ServeltContext对象
		ServletContext c = config.getServletContext();
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("我是demo3...");
		chain.doFilter(request, response);
		System.out.println("我是回来的demo3...");
	}

	public void destroy() {
		
	}

}
