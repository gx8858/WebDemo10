package cn.itcast.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 解决全局的编码的问题
 */
public class EncodingFilter implements Filter{

	
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * 设置编码的问题
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String encoding = filterConfig.getInitParameter("encoding");
		// 解决POST乱码的问题
		request.setCharacterEncoding(encoding);
		// 响应
		response.setContentType("text/html;charset="+encoding);
		// 放行
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
