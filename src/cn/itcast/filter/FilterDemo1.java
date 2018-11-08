package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 实现Filter接口，重写方法
 * 在web.xml进行配置
 * @author Administrator
 */
public class FilterDemo1 implements Filter{
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * 每次请拦截的方式都执行（通过配置来决定）
	 * 由服务器调用doFilter() -- 进去到过滤器
	 * FilterChain服务器创建，把传入进来（过滤器的信息 ）
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("站住，打劫！！");
		
		
		// 放行
		// 放行 -- 执行下一个过滤器 -- 没有访问目标资源
		chain.doFilter(request, response);
		
		// 去访问Servlet
		
		// System.out.println("小伙，再来一次吧！！");
		
	}
	
	/**
	 * 销毁
	 */
	public void destroy() {
		
	}

}
