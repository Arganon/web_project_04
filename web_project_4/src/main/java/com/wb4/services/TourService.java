package com.wb4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.Tour;
import com.wb4.model.dao.jdbc.JdbcTourDao;
import com.wb4.model.dao.jdbc.JdbcUserTourDao;

public class TourService {
	protected static TourService instance = null;
	private final Logger logger = Logger.getLogger(TourService.class.getName());

	protected TourService() {}
	
	public static TourService getInstance() {
		if (instance == null) {
			instance = new TourService();
		}
		return instance;
	}
	
	public boolean changeTourState(Integer t_id, String t_state) {
		logger.info("Trying to change tour state with t_id = " + t_id);
		JdbcTourDao tourDao = new JdbcTourDao(DataBaseConnection.getInstance().getConnection());
		boolean result = tourDao.updateTourState(t_id, t_state);
		
		try {
			tourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Optional<Integer> checkTourInUserList(Integer u_id, Integer t_id) {
		logger.info("Trying to check tour in user list. User id = " + u_id + " Tour id = " + t_id);
		JdbcUserTourDao userTourDao = new JdbcUserTourDao(DataBaseConnection.getInstance().getConnection());
		Optional<Integer> result = userTourDao.find(u_id, t_id);
		
		try {
			userTourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Optional<Tour> find(Integer t_id) {
		logger.info("Trying to find tour with id = " + t_id);
		JdbcTourDao tourDao = new JdbcTourDao(DataBaseConnection.getInstance().getConnection());
		Optional<Tour> tour = tourDao.find(t_id);
		
		try {
			tourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tour;
	}
	
	public List<Tour> getAllTours() {
		logger.info("Trying to get all tours");
		List<Tour> tourList = new ArrayList<Tour>();
		JdbcTourDao tourDao = new JdbcTourDao(DataBaseConnection.getInstance().getConnection());
		tourList = tourDao.findAll();
		
		try {
			tourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tourList;
	}
	
	public List<Tour> getUserTours(int userId) {
		JdbcUserTourDao userTourDao = new JdbcUserTourDao(DataBaseConnection.getInstance().getConnection());
		
		List<Integer> tourListId = userTourDao.findAll(userId);
		List<Tour> tourList = new ArrayList<Tour>();
		
		try {
			userTourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JdbcTourDao tourDao = new JdbcTourDao(DataBaseConnection.getInstance().getConnection());
		
		for (Integer i : tourListId) {
			Optional<Tour> tour = tourDao.find(i);
			tourList.add(tour.get());
		}

		try {
			tourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tourList;
	}
	
	public boolean createUserTour(Integer u_id, Integer t_id) {
		logger.info("Trying to add tour to user tour list");
		JdbcUserTourDao userTourDao = new JdbcUserTourDao(DataBaseConnection.getInstance().getConnection());
		boolean result = false;
		if (userTourDao.create(u_id, t_id)) {
			result = true;
		}
		
		try {
			userTourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
