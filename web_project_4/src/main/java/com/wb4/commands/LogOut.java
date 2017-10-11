package com.wb4.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.services.ConstantValues;

public class LogOut implements Commands {	
	private LogOut() {}
	
	private static class Holder {
		private static LogOut INSTANCE = new LogOut();
	}
	
	public static LogOut getInstance() {
		return LogOut.Holder.INSTANCE;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		
		return ConstantValues.LOGIN_PAGE;
	}

}
