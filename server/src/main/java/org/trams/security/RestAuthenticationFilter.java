package org.trams.security;

import org.trams.bean.UserItem;
import org.trams.rest.common.AuthorizationToken;
import org.trams.web.common.utils.ServletUtils;
import org.springframework.stereotype.Service;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.trams.web.common.Login;
import java.io.IOException;

/**
 * Created by phong on 4/8/16.
 */
@Service
public class RestAuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest http_request = ((HttpServletRequest) request);
		HttpServletResponse http_response = ((HttpServletResponse) response);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		String path = ((HttpServletRequest) request).getRequestURI();
		if (path.startsWith("/api")) {
			String token = http_request.getHeader("token");
			//System.out.println("Token: " + token);
			UserItem user = AuthorizationToken.convertToObject(token);
			if (user == null || user.getId() == null) {
				ServletUtils.response(http_response, HttpServletResponse.SC_FORBIDDEN, "You have to login !");
				return;
			}
		} else if( path.equals("") || path.startsWith("/admin")) {
			if (Login.checkUserLogin(session) == "0"){
				((HttpServletResponse) response).sendRedirect("/admin/login/");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
