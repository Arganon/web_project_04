package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.controllers.ConnectionPoolController;
import com.wb4.entity.Tour;
import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcTourDao implements GenericDao<Tour> {
	Connection connection;
	
	protected static final String SELECT_ALL_TOUR = "SELECT * FROM tour";
	protected static final String SELECT_TOUR_BY_ID = "SELECT * FROM tour WHERE t_id = ?";
	protected static final String DELETE_TOUR_BY_ID = "DELETE FROM tour WHERE t_id = ?";
	protected static final String UPDATE_TOUR = "UPDATE tour SET " +
											   "country_name = ?, city_name = ?, t_duration = ?, t_state = ?, t_type = ? " +
											    "WHERE t_id = ?";
	protected static final String UPDATE_TOUR_STATE = "UPDATE tour SET t_state = ? WHERE t_id = ?";
	protected static final String CREATE_TOUR = "INSERT INTO tour " + 
												"(country_name, city_name, t_duration, " + 
												"t_state, t_type) VALUES (?, ?, ?, ?, ?, ?)";
	
	public JdbcTourDao(Connection connection) {
		this.connection = connection;
	}
	
	public boolean updateTourState(Integer t_id, String state) {
		try {
			this.connection.setAutoCommit(false);
			
			PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_TOUR_STATE);
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, t_id);
			
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			this.connection.setAutoCommit(true);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public Optional<Tour> find(Integer id) {
	Optional<Tour> result = Optional.empty();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_TOUR_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = Optional.ofNullable(EntityBuilder.getTourObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Tour> findAll() {
	List<Tour> tourResultList = new ArrayList<Tour>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_TOUR);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				tourResultList.add(EntityBuilder.getTourObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tourResultList;
	}

	public boolean create(Tour tour) {
		makeChanges(tour, CREATE_TOUR, true);
		return false;
	}

	public boolean update(Tour tour) {
		makeChanges(tour, UPDATE_TOUR, false);
		return false;
	}
	
	private boolean makeChanges(Tour tour, String sqlRequest, Boolean isNew) {
		PreparedStatement preparedStatement = null;
		int counter = 0;
		
		try {
			this.connection.setAutoCommit(false);
			preparedStatement = this.connection.prepareStatement(UPDATE_TOUR);
			preparedStatement.setString(++counter, tour.getCountry().toString());
			preparedStatement.setString(++counter, tour.getCity().toString());
			preparedStatement.setInt(++counter, tour.getTourDuration());
			preparedStatement.setString(++counter, tour.getTourState().toString());
			preparedStatement.setString(++counter, tour.getTourType().toString());
			
			if(!isNew) {
				preparedStatement.setInt(++counter, tour.getId());
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
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_TOUR_BY_ID);
			preparedStatement.setInt(1, id);
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
