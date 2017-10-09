package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.entity.Transport;
import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcTransportDao implements GenericDao<Transport> {
	protected static final String SELECT_TRANS_BY_ID = "SELECT * FROM transport WHERE trans_id = ?";
	protected static final String SELECT_ALL_TRANS = "SELECT * FROM transport";
	protected static String UPDATE_TRANS = "UPDATE transport SET " +
			   "trans_type = ?, trans_price = ? " + 
			   "WHERE trans_id = ?";
	private static final String DELETE_TRANS_BY_ID = "DELETE FROM transport WHERE trans_id = ?";
	
	protected Connection connection;
	
	public JdbcTransportDao(Connection connection) {
		this.connection = connection;
	}

	public Optional<Transport> find(Integer id) {
		Optional<Transport> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_TRANS_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = Optional.ofNullable(EntityBuilder.getTransportObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Transport> findAll() {
		List<Transport> transportList = new ArrayList<Transport>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_TRANS);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				transportList.add(EntityBuilder.getTransportObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transportList;
	}

	public boolean create(Transport transport) {
		return makeChanges(transport, UPDATE_TRANS, true);
	}

	public boolean update(Transport transport) {
		return makeChanges(transport, UPDATE_TRANS, false);
	}

	public boolean delete(Integer id) {
		boolean result = false;
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_TRANS_BY_ID);
			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void close() throws Exception {
		this.connection.close();
	}
	
	private boolean makeChanges(Transport transport, String sqlRequest, Boolean isNew) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(sqlRequest);
			preparedStatement.setString(++counter, transport.getGeneralTransportType().toString());
			preparedStatement.setDouble(++counter, transport.getGeneralTransportPrice());
			
			if (!isNew) {
				preparedStatement.setInt(++counter, transport.getId());
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
}
