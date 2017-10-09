package com.wb4.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.services.ConstantValues;

public class LogOut implements Commands {
	protected static LogOut instance = null;
	
	protected LogOut() {}
	
	public static LogOut getInstance() {
		if (instance == null) {
			instance = new LogOut();
		}
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		
		return ConstantValues.LOGIN_PAGE;
	}

}
