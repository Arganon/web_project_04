package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.controllers.ConnectionPoolController;
import com.wb4.entity.User;
import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcUserDao implements GenericDao<User> {
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE u_id = ?";
	private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE u_login = ?";
	private static final String CREATE_USER = "INSERT INTO user " + 
								"(u_firstname, u_middlename, u_lastname, u_login, " + 
								"u_password, u_email) " +
								"VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE user SET " +
							   "u_firstname = ?, u_middlename = ?, u_lastname = ?, u_login = ?, " + 
							   "u_password = ?, u_email = ?, u_role = ? WHERE u_id = ?";
	private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE u_id = ?";
	private static final String DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE u_login = ?";
	
	private Connection connection;
	
	public JdbcUserDao(Connection connection) {
		this.connection = connection;
	}

	public Optional<User> find(Integer id) {
		Optional<User> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_USER_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = Optional.ofNullable(EntityBuilder.getUserObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public User findByLogin(String login) {
		User result = null;
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = EntityBuilder.getUserObject(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<User> findAll() {
		List<User> userResultList = new ArrayList<User>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				userResultList.add(EntityBuilder.getUserObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userResultList;
	}

	public boolean create(User user) {
		return makeChanges(user, CREATE_USER, true);
	}

	public boolean update(User user) {
		return makeChanges(user, UPDATE_USER, false);
	}
	
	private boolean makeChanges(User user, String sqlRequest, Boolean isNew) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(sqlRequest);
			preparedStatement.setString(++counter, user.getFirstName());
			preparedStatement.setString(++counter, user.getMiddleName());
			preparedStatement.setString(++counter, user.getLastName());
			preparedStatement.setString(++counter, user.getLogin());
			preparedStatement.setString(++counter, user.getPassword());
			preparedStatement.setString(++counter, user.getEmail());
			
			if (!isNew) {
				preparedStatement.setInt(++counter, user.getId());
			}
			
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			this.connection.setAutoCommit(true);
			
			return true;
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	public boolean delete(Integer id) {
		boolean result = false;
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_USER_BY_ID);
			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean delete(String login) {
		boolean result = false;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(DELETE_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void close() throws Exception {
		ConnectionPoolController.getInstance().release(this.connection);
	}
}
