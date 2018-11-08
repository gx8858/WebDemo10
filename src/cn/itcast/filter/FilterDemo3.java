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
	 * ʹ��FilterConfig����ȡһЩ��Ϣ
	 */
	public void init(FilterConfig config) throws ServletException {
		// ��ȡfilter���õ�����<filter-name>
		String filterName = config.getFilterName();
		System.out.println("���������������ƣ�"+filterName);
		// ��ȡ��ʼ������
		String username = config.getInitParameter("username");
		String password = config.getInitParameter("password");
		System.out.println(username+" : "+password);
		
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			// username��password
			String name = e.nextElement();
			// ��ȡusername��ֵ
			String value = config.getInitParameter(name);
			System.out.println(name+" : "+value);
		}
		
		// ��ȡServeltContext����
		ServletContext c = config.getServletContext();
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("����demo3...");
		chain.doFilter(request, response);
		System.out.println("���ǻ�����demo3...");
	}

	public void destroy() {
		
	}

}
