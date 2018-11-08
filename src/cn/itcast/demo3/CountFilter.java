package cn.itcast.demo3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CountFilter implements Filter{

	private FilterConfig config;

	/**
	 * ���г�ʼ���Ĳ���
	 * ��ServletContext���д���map
	 */
	public void init(FilterConfig config) throws ServletException {
		// ����һ��MAP
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		ServletContext context = config.getServletContext();
		// ��
		context.setAttribute("countMap", countMap);
		this.config = config;
	}
	
	/**
	 * �÷���ִ����
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 1.��ȡmap
		 * 2.��ȡip
		 * 3.��map�к�ip�ͶԱ�
		 * 	* ���map����ip����ȡcount��+1
		 * 	* ���map��û��ip����ip��count=1���뵽map��
		 * 4.��map���뵽ServletContext��
		 * 5.����
		 */
		ServletContext context = config.getServletContext();
		// ��ȡmap
		Map<String, Integer> countMap = (Map<String, Integer>) context.getAttribute("countMap");
		// ��ȡ���ip
		String ip = request.getRemoteAddr();
		// �ж�map���Ƿ���ڸ�ip
		Integer count = countMap.get(ip);
		// �ж�countΪnull
		if(count == null){
			// ��һ����
			count = 1;
		}else{
			// �����ܶ����
			count++;
		}
		// ��ip��count���뵽Map��
		countMap.put(ip, count);
		// �����д���map
		context.setAttribute("countMap", countMap);
		// ����
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
