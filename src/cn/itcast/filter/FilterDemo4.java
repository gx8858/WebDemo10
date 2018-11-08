package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo4 implements Filter{

	/**
	 * 过滤器是双向的
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("我是demo4...");
		chain.doFilter(request, response);
		System.out.println("我是回来的demo4...");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
