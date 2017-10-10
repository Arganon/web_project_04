package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.controllers.ConnectionPoolController;
import com.wb4.entity.Accommodation;
import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcAccommodationDao implements GenericDao<Accommodation> {
	protected static String SELECT_ALL_ACMD = "SELECT * FROM accommodation";
	protected static String SELECT_ACMD_BY_ID = "SELECT * FROM accommodation WHERE acmd_id = ?";
	protected static final String CREATE_ACMD = "INSERT INTO accommodation " + 
			"(acmd_name, acmd_type, vac_quantity, price_per_night) " +
			"VALUES (?, ?, ?, ?)";
	protected static String UPDATE_ACMD = "UPDATE accommodation SET " +
			   "acmd_name = ?, acmd_type = ?, vac_quantity = ?, price_per_night = ? " + 
			   "WHERE acmd_id = ?";
	protected static final String DELETE_ACMD_BY_ID = "DELETE FROM accommodation WHERE acmd_id = ?";
	
	protected Connection connection;
	
	public JdbcAccommodationDao(Connection connection) {
		this.connection = connection;
	}

	public Optional<Accommodation> find(Integer id) {
		Optional<Accommodation> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ACMD_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = Optional.ofNullable(EntityBuilder.getAccommodationObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Accommodation> findAll() {
		List<Accommodation> resultList = new ArrayList<Accommodation>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_ACMD);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				resultList.add(EntityBuilder.getAccommodationObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public boolean create(Accommodation accommodation) {
		return makeChanges(accommodation, CREATE_ACMD, true);
	}

	public boolean update(Accommodation accommodation) {
		return makeChanges(accommodation, CREATE_ACMD, false);
	}
	
	private boolean makeChanges(Accommodation accommodation, String sqlRequest, Boolean isNew) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(sqlRequest);
			preparedStatement.setString(++counter, accommodation.getAccommodationName());
			preparedStatement.setString(++counter, accommodation.getAccommodationType().toString());
			preparedStatement.setInt(++counter, accommodation.getVacationistQuantity());
			preparedStatement.setDouble(++counter, accommodation.getPricePerNight());
			
			if (!isNew) {
				preparedStatement.setInt(++counter, accommodation.getId());
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
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_ACMD_BY_ID);
			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void close() throws Exception {
		ConnectionPoolController.getInstance().release(this.connection);
	}
}
