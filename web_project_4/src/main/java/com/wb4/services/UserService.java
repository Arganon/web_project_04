package com.wb4.services;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.wb4.entity.User;
import com.wb4.model.dao.jdbc.JdbcDaoFactory;
import com.wb4.model.dao.jdbc.JdbcUserDao;

public class UserService {
	protected final Logger logger = Logger.getLogger(UserService.class.getName());
	protected JdbcDaoFactory daoFactory;

	private static class Holder {
		private static UserService INSTANCE = new UserService(JdbcDaoFactory.getInstance());
	}
	
	private UserService(JdbcDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public static UserService getInstance() {
		return UserService.Holder.INSTANCE;
	}
	
	public Optional<User> userLogin(String login, String password) {
		logger.info("Trying to log in. User login: " + login);
		JdbcUserDao userDao = this.daoFactory.createJdbcUserDao();
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
		
		JdbcUserDao userDao = this.daoFactory.createJdbcUserDao();
		Optional<User> usr = Optional.ofNullable(userDao.findByLogin(login));
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usr;
	}
	
	public void createUser(User user) {
		logger.info("Trying to create user profile");
		JdbcUserDao userDao = this.daoFactory.createJdbcUserDao();
		userDao.create(user);
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
