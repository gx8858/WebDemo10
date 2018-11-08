package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器的生命周期
 * @author Administrator
 *
 */
public class FilterDemo2 implements Filter{
	
	/**
	 * 初始化的操作
	 * 过滤器什么创建呢？	服务器启动的时候创建过滤器的实例。
	 * 调用几次？	调用一次	
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init...");
	}
	
	/**
	 * 什么时候执行
	 * 每次请求的时候，过滤器配置满足过滤的条件，有一次请求就会执行一次。
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter....");
		chain.doFilter(request, response);
	}

	/**
	 * 过滤器什么时候销毁  服务器关闭，移除项目
	 * 调用几次 一次。
	 */
	public void destroy() {
		System.out.println("destroy....");
	}

}
