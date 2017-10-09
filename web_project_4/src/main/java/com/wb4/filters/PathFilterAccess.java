package com.wb4.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.User;
import com.wb4.services.ConstantValues;

@WebFilter(filterName = "PathFilter", urlPatterns = "/rest/*")
public class PathFilterAccess implements Filter {
	protected List<Rule> accessRuleList = new ArrayList<Rule>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		Properties properties = new Properties();
		InputStream input = null;
		String[] roles;
		
		try {
			input = DataBaseConnection.class.getClassLoader().getResourceAsStream("user_access_rules.properties");
			properties.load(input);
			
			roles = properties.getProperty("ROLES").split("; ");
			
			for(String role : roles) {
				Rule rule = new Rule();
				rule.setRole(role);
				accessRuleList.add(rule);
			}
			
			for (Rule rule : accessRuleList) {
				String[] urls = properties.getProperty(rule.getRole()).split("; ");
				rule.setUrls(urls);
			}
			
			input.close();
			input = null;
			properties = null;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		Rule rule = isContainRole("EXCEPTIONS");
		if (isContainUrl(httpRequest.getRequestURI(), rule)) {
			chain.doFilter(request, response);
		} else {
			doProcess(httpRequest, httpResponse, chain);
		}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher(ConstantValues.ERROR_PAGE);
		
		if (session.getAttribute("USER") == null) {
			rd.forward(request, response);
		}

		User user = (User)request.getSession().getAttribute("USER");
		String userRole = user.getUserRole().toString();
		String url = request.getRequestURI();
		Rule rule = isContainRole(userRole);
		
		if (rule == null ) {
			rd.forward(request, response);
		}
		if (!isContainUrl(url, rule)) {
			rd.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	protected boolean isContainUrl(String url, Rule rule) {
		for (int i = 0; i < rule.getUrls().length; ++i) {
			if (url.contains(rule.getUrls()[i])) {
				return true;
			}
		}
		return false;
	}
	
	protected Rule isContainRole(String role) {
		for (int i = 0; i < accessRuleList.size(); ++i) {
			if (accessRuleList.get(i).getRole().equals(role)) {
				return accessRuleList.get(i);
			}
		}
		return null;
	}
	
	protected class Rule {
		protected String role;
		protected String[] urls;
		
		public void setRole(String role) {
			this.role = role;
		}

		public String[] getUrls() {
			return urls;
		}

		public void setUrls(String[] urls) {
			this.urls = urls;
		}

		public String getRole() {
			return role;
		}
	}
	
	public void destroy() {}
}
