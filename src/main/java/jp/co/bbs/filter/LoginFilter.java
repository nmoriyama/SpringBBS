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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.bbs.entity.User;

//@WebFilter(urlPatterns = {"/home", "/management", "/setting", "/signup", "/posting"})

public class LoginFilter implements Filter {
 
    public void init(FilterConfig config) throws ServletException {
    }
 
    public void doFilter(ServletRequest req, ServletResponse res,
           FilterChain chain) throws IOException, ServletException {
        
		HttpSession session = ((HttpServletRequest) req).getSession();
		List<String> messages = new ArrayList<String>();

		User user = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");
		
		String check = user.getAccount();
		
		if (check == null) {
			messages.add("ログインできません");
			session.setAttribute("messages", messages);
			((HttpServletResponse) res).sendRedirect("login");
		} else if (Integer.parseInt(user.getStatus()) == 1) {
			messages.add("ログインできません");
			session.setAttribute("messages", messages);
			((HttpServletResponse) res).sendRedirect("login");
		} else {
			chain.doFilter(req, res);
		}
    }
 
    public void destroy() {
    }
}