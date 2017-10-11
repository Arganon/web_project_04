package com.wb4.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.User;
import com.wb4.services.ConstantValues;

public class UsersPageRedirect implements Commands {
	protected Map<String, String> usersPath = new HashMap<String, String>();
	
	protected UsersPageRedirect() {
		super();
		this.usersPath.put(ConstantValues.USER_ROLE, ConstantValues.USER_PAGE);
		this.usersPath.put(ConstantValues.ADMIN_ROLE, ConstantValues.ADMIN_PAGE);
		this.usersPath.put(ConstantValues.MANAGER_ROLE, ConstantValues.MANAGER_PAGE);
	}
	
	private static class Holder {
		private static UsersPageRedirect INSTANCE = new UsersPageRedirect();
	}
	
	public static UsersPageRedirect getInstance() {
		return UsersPageRedirect.Holder.INSTANCE;
	}
	
	public String getUserPage(String role) {
		return this.usersPath.get(role);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("USER");
		
		return getUserPage(user.getUserRole().toString());
	}

}
