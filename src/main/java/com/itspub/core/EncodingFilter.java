package com.itspub.core;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName="EncodingFilter", urlPatterns={"/*"},initParams={@WebInitParam(name="encoding", value="UTF-8")})
public class EncodingFilter implements Filter {

	protected FilterConfig config;
	protected String encoding;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encoding = config.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html; charset=" + encoding);
		if (null == request.getCharacterEncoding()) {
			if (null != encoding) {
				request.setCharacterEncoding(encoding);
				response.setCharacterEncoding(encoding);
			}
		}

		request.setAttribute("contextPath", request.getServletContext().getContextPath());
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}