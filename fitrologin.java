package br.com.login.filter;

import java.io.IOException;
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

/**
 * Servlet Filter implementation class fitrologin
 */
@WebFilter(filterName = "/filterLoggin", urlPatterns = "/index.jsp")
public class fitrologin implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		String loginURI = httpRequest.getContextPath() + "/login.jsp";

		boolean loggedIn = session != null && session.getAttribute("user") != null;
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);

		if (loggedIn || loginRequest) {
			chain.doFilter(httpRequest, httpResponse);

		} else {
			httpResponse.sendRedirect(loginURI);
		}
	}

}


