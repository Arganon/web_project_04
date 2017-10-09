package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcDiscountDao implements GenericDao<Double> {
	protected Connection connection;
	
	protected final static String GET_DISCOUNT_BU_ID = "SELECT d_discount FROM discount WHERE d_id = ?";
	protected final static String UPDATE_DISCOUNT = "UPDATE discount SET d_discount = ? WHERE d_id = ?";
	
	public JdbcDiscountDao(Connection connection) {
		this.connection = connection;
	}
	
	public Optional<Double> find(Integer d_id) {
		Optional<Double> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(GET_DISCOUNT_BU_ID);
			preparedStatement.setInt(1, d_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = Optional.ofNullable(EntityBuilder.makeDiscount(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateDiscount(Double d_discount) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(UPDATE_DISCOUNT);
			preparedStatement.setDouble(++counter, d_discount);
			preparedStatement.setInt(++counter, 1);
			
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			this.connection.setAutoCommit(true);
			
			return true;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Double> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Double e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Double e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws Exception {
		this.connection.close();
	}
}
