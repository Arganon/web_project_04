 package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcUserTourDao implements GenericDao<Integer> {
	protected static final String SELECT_ALL_USER_TOURS = "SELECT t_id FROM m2m_user_tour WHERE u_id = ?";
	protected static final String CREATE_USER_TOURS = "INSERT INTO m2m_user_tour " +
												"(u_id, t_id) VALUES(?, ?)";
	protected static final String CHECK_USER_TOUR = "SELECT t_id FROM m2m_user_tour WHERE u_id = ? AND t_id = ?";
	
	protected Connection connection;
	
	public JdbcUserTourDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void close() throws Exception {
		this.connection.close();
	}

	// Find concrete user tour  
	public Optional<Integer> find(Integer u_id, Integer t_id) {
		Optional<Integer> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(CHECK_USER_TOUR);
			preparedStatement.setInt(1, u_id);
			preparedStatement.setInt(2, t_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = Optional.ofNullable(resultSet.getInt("t_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//Find all ID of users tours
	public List<Integer> findAll(Integer userId) {
		List<Integer> tuorListId = new ArrayList<Integer>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_USER_TOURS);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				tuorListId.add(EntityBuilder.getTourId(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tuorListId;
	}

	@Override
	public List<Integer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean create(Integer u_id, Integer t_id) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(CREATE_USER_TOURS);
			preparedStatement.setInt(++counter, u_id);
			preparedStatement.setInt(++counter, t_id);
			
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			this.connection.setAutoCommit(true);
			
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Integer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Integer> find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
