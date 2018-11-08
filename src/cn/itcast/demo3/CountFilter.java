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
	 * 进行初始化的操作
	 * 在ServletContext域中存入map
	 */
	public void init(FilterConfig config) throws ServletException {
		// 先有一个MAP
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		ServletContext context = config.getServletContext();
		// 存
		context.setAttribute("countMap", countMap);
		this.config = config;
	}
	
	/**
	 * 该方法执行了
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 1.获取map
		 * 2.获取ip
		 * 3.在map中和ip就对比
		 * 	* 如果map中有ip，获取count，+1
		 * 	* 如果map中没有ip，把ip和count=1存入到map中
		 * 4.把map存入到ServletContext中
		 * 5.放行
		 */
		ServletContext context = config.getServletContext();
		// 获取map
		Map<String, Integer> countMap = (Map<String, Integer>) context.getAttribute("countMap");
		// 获取你的ip
		String ip = request.getRemoteAddr();
		// 判断map中是否存在该ip
		Integer count = countMap.get(ip);
		// 判断count为null
		if(count == null){
			// 第一次来
			count = 1;
		}else{
			// 来过很多次了
			count++;
		}
		// 把ip和count存入到Map中
		countMap.put(ip, count);
		// 向域中存入map
		context.setAttribute("countMap", countMap);
		// 放行
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
