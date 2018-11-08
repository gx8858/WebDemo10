package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ����������������
 * @author Administrator
 *
 */
public class FilterDemo2 implements Filter{
	
	/**
	 * ��ʼ���Ĳ���
	 * ������ʲô�����أ�	������������ʱ�򴴽���������ʵ����
	 * ���ü��Σ�	����һ��	
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init...");
	}
	
	/**
	 * ʲôʱ��ִ��
	 * ÿ�������ʱ�򣬹���������������˵���������һ������ͻ�ִ��һ�Ρ�
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter....");
		chain.doFilter(request, response);
	}

	/**
	 * ������ʲôʱ������  �������رգ��Ƴ���Ŀ
	 * ���ü��� һ�Ρ�
	 */
	public void destroy() {
		System.out.println("destroy....");
	}

}
