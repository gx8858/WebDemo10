package cn.itcast.demo4;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * �Զ���½�Ĺ���
 */
public class AutoLoginFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 *  �ڹ������п���ֱ�ӻ�ȡsession�е��û���Ϣ�����user��Ϊ�գ�˵�������û�ء����С�
		  * ��session�л�ȡ����user����Ϣ
		  * �Ȼ�ȡcookie����ȡָ�����Ƶ�cookie��
		  * ���cookieΪ�գ����С�
		  * ���cookie��Ϊ�գ���ȡ�û��������롣ȥ���ݿ��ѯ��
		  * �����ѯ������cookie����Ϣ����ȷ�����У�û�д���session�У���
		  * �����ѯ���ˣ�cookie�е���Ϣ����ȷ�����û�����Ϣ���浽session�С����С�
		 */
		// ��session�л�ȡ�û�����Ϣ
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		// ��session��ȡ�û�����Ϣ
		User user = (User) session.getAttribute("existUser");
		// ��������û�رգ�session�е��û���Ϣ��Ϊnull��
		if(user != null){
			chain.doFilter(req, response);
		}else{
			// session��û���û�����Ϣ
			// ��ȡָ�����Ƶ�cookie
			Cookie [] cookies = req.getCookies();
			// ��ȡ��cookie���Ϳ��Խ����ж���
			Cookie cookie = getCookieByName(cookies,"autologin");
			// ���cookieΪnull
			// �����������У�������û��autologin��cookie
			if(cookie == null){
				// ֱ�ӷ���	�Լ�����suc.jsp����Ϊsuc.jsp�Ѿ����������ˣ�û��sessionĬ������ȥ��½���ˡ�
				chain.doFilter(req, response);
			}else{
				// ��cookie�л�ȡ�û��������룬ȥ�����в�ѯ
				String username = cookie.getValue().split("#itcast#")[0];
				String password = cookie.getValue().split("#itcast#")[1];
				// ����Ҫȥ���ݿ��н��в�ѯ
				UserDao dao = new UserDao();
				// ȥ���ݿ��в�ѯָ�����ƺ�������û�
				User existUser = dao.findUser(username, password);
				// ��ѯ�����û�Ϊnull
				if(existUser == null){
					// ����
					chain.doFilter(req, response);
				}else{
					// ���뵽session��
					session.setAttribute("existUser", existUser);
					// ����
					chain.doFilter(req, response);
				}
			}
		}
	}
	
	
	public Cookie getCookieByName(Cookie [] cookies,String cookieName){
		if(cookies == null){
			return null;
		}else{
			for (Cookie cookie : cookies) {
				// �ж�
				if(cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
			return null;
		}
	}
	

	public void destroy() {
		
	}

}







