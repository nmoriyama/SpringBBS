package jp.co.bbs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.bbs.entity.User;


@WebFilter(urlPatterns = {"/management", "/setting", "/signup"})
public class AccessCheckFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		List<String> messages = new ArrayList<String>();
		System.out.println(((HttpServletRequest) request).getSession().getAttribute("loginUser"));
		User users = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");	
		
		if (users.equals(null)) {
			messages.add("ログインしていません");
			session.setAttribute("messages", messages);
			((HttpServletResponse) response).sendRedirect("login");
		} else {
			User user = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");	
			if (user.getPositionId() == 1 && user.getBranchId() == 1) {	
				chain.doFilter(request, response);
			} else{
				messages.add("アクセスできません");
				session.setAttribute("messages", messages);
				((HttpServletResponse) response).sendRedirect("home");
			}
		}

	}
			
	public void init(FilterConfig config) {

	}
			
	public void destroy() {
	}
}