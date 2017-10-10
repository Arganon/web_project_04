package com.wb4.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wb4.controllers.ConnectionPoolController;
import com.wb4.model.dao.GenericDao;
import com.wb4.services.EntityBuilder;

public class JdbcTourAcmdTransportDao implements GenericDao<ArrayList<Integer>>{
	protected Connection connection;
	protected final static String FIND_ALL = "SELECT * FROM m2m_tour_acmd_transport";
	protected final static String FIND_TOUR_ACMD_TRANS = "SELECT * FROM m2m_tour_acmd_transport WHERE t_id = ?";
	
	
	public JdbcTourAcmdTransportDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public Optional<ArrayList<Integer>> find(Integer t_id) {
		Optional<ArrayList<Integer>> tourAcmdTrans = Optional.empty();		
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TOUR_ACMD_TRANS);
			preparedStatement.setInt(1, t_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				tourAcmdTrans = Optional.ofNullable(EntityBuilder.getTourAcmdTrans(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tourAcmdTrans;
	}

	@Override
	public List<ArrayList<Integer>> findAll() {
		List<ArrayList<Integer>> allToursAcmdTrans = new ArrayList<ArrayList<Integer>>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				allToursAcmdTrans.add((ArrayList<Integer>) EntityBuilder.getTourAcmdTrans(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allToursAcmdTrans;
	}

	@Override
	public boolean create(ArrayList<Integer> e) {

		return false;
	}

	@Override
	public boolean update(ArrayList<Integer> e) {

		return false;
	}
	
	@Override
	public void close() throws Exception {
		ConnectionPoolController.getInstance().release(this.connection);

	}
}
