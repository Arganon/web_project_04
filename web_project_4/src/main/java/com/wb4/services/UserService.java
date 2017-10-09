package com.wb4.services;

import java.util.Optional;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.User;
import com.wb4.model.dao.jdbc.JdbcUserDao;

public class UserService {
	protected static UserService instance = null;
	
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	public Optional<User> userLogin(String login, String password) {
		JdbcUserDao userDao = new JdbcUserDao(DataBaseConnection.getInstance().getConnection());
		Optional<User> usr = Optional.ofNullable(userDao.findByLogin(login))
				.filter(user -> password.equals(user.getPassword()));
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usr;
	}
	
	public Optional<User> findUser(String login) {
		JdbcUserDao userDao = new JdbcUserDao(DataBaseConnection.getInstance().getConnection());
		Optional<User> usr = Optional.ofNullable(userDao.findByLogin(login));
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usr;
	}
	
	public void createUser(User user) {
		JdbcUserDao userDao = new JdbcUserDao(DataBaseConnection.getInstance().getConnection());
		userDao.create(user);
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
