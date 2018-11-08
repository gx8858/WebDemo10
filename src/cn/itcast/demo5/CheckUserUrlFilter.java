package cn.itcast.demo5;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.demo4.User;

/**
 * URL����ȫѡ��֤
 */
public class CheckUserUrlFilter implements Filter{

	private FilterConfig config;

	/**
	 * ��ʼ���Ĳ�������ȡ��ʼ����������ȡ��Map�����С�����ServletContext�С�
	 */
	public void init(FilterConfig config) throws ServletException {
		// ����Map������Ϣ
		Map<String, String> urlMap = new HashMap<String, String>();
		// ��ȡ��ʼ���������Ѳ��������ݱ��浽urlMap��
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			// ��ȡ��<param-name>��ֵ
			String paramName = e.nextElement();
			// ��ȡ������<param-value>��ֵ
			String paramValue = config.getInitParameter(paramName);
			// ����Map������ 	{/admin:admin}	{/user:user}
			urlMap.put(paramName, paramValue);
		}
		// ���뵽ServletContext��
		config.getServletContext().setAttribute("urlMap", urlMap);
		this.config = config;
	}

	/**
	 * Ȩ�޵���֤
	 * 	* ��������������õ����Ӻ�urlMap�е����Ӻ�type�������Ա�
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// �Ȼ�ȡ���������		����/admin/add.jsp		map {/admin:admin}	{/user:user}
		
		HttpServletRequest req = (HttpServletRequest) request;
		// /day21/admin/add.jsp
		// String uri = req.getRequestURI();
		// System.out.println(uri);
		// 
		// ��ȡ���������
		String sPath = req.getServletPath();
		// System.out.println(sPath);
		// ��Map�����жԱ�
		ServletContext context = config.getServletContext();
		// ��ȡMap�ļ�����
		Map<String, String> urlMap = (Map<String, String>) context.getAttribute("urlMap");
		
		// ���Ա� set��keyֵ /admin  /user
		Set<String> set = urlMap.keySet();
		// ѭ�� /admin
		for (String paramName : set) {
			// /admin/add.jsp��/admin��ʼ��
			if(sPath.startsWith(paramName)){
				// ȥ��ȡsession���û���Ϣ�����û�����Ϣ�л�ȡtype���Ե�ֵ����Map�е�value��ֵ���жԱȡ�
				User user = (User) req.getSession().getAttribute("existUser");
				// ���userΪnull
				if(user == null){
					// ת������½��ҳ����
					req.getRequestDispatcher("/demo4/login.jsp").forward(req, response);
					return;
				}else{
					// ȥ�ж��û���type��ֵ����Map�е�value��ֵ���Ա�
					String userType = user.getType();
					
					// ����һ��Map�д���һ��ֵ  {/admin  admin} {/user  user}
					String paramValue = urlMap.get(paramName);
					
					if(userType.equals(paramValue)){
						// ƥ��ɹ���	/admin/add.jsp  ����һ���ж��ˣ���/admin��ͷ�ģ�
						// ���û�����Ϣ�л�ȡtype ��ֵ��Map��ֵ���˶Ա�
						chain.doFilter(req, response);
						return;
					}else{
						// ���û���ʾ
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("<h3>�ף���Ȩ�޲�������</h3>");
						return;
					}
				}
			}
		}
		
		chain.doFilter(req, response);
		
	}

	public void destroy() {
		
	}

}
