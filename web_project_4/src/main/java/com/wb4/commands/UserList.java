package com.wb4.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.User;
import com.wb4.model.dao.jdbc.JdbcUserDao;
import com.wb4.services.ConstantValues;

public class UserList implements Commands {	
	protected UserList () {}

	private static class Holder {
		private static UserList INSTANCE = new UserList();
	}
	
	public static UserList getInstance() {
		return UserList.Holder.INSTANCE;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = new ArrayList<User>();
		
		JdbcUserDao userDao = new JdbcUserDao(DataBaseConnection.getInstance().getConnection());
		userList = userDao.findAll();
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userList", userList);
		
		return ConstantValues.USER_LIST;
	}

}
