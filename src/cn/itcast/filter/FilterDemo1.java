package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ʵ��Filter�ӿڣ���д����
 * ��web.xml��������
 * @author Administrator
 */
public class FilterDemo1 implements Filter{
	
	/**
	 * ��ʼ��
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * ÿ�������صķ�ʽ��ִ�У�ͨ��������������
	 * �ɷ���������doFilter() -- ��ȥ��������
	 * FilterChain�������������Ѵ������������������Ϣ ��
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("վס����٣���");
		
		
		// ����
		// ���� -- ִ����һ�������� -- û�з���Ŀ����Դ
		chain.doFilter(request, response);
		
		// ȥ����Servlet
		
		// System.out.println("С�����һ�ΰɣ���");
		
	}
	
	/**
	 * ����
	 */
	public void destroy() {
		
	}

}
