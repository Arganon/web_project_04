package com.wb4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.wb4.entity.Tour;
import com.wb4.model.dao.jdbc.JdbcDaoFactory;
import com.wb4.model.dao.jdbc.JdbcTourAcmdTransportDao;
import com.wb4.model.dao.jdbc.JdbcUserTourDao;

public class TourBuilderService {
	protected static Integer T_ID = 0;
	protected static Integer ACMD_ID = 1;
	protected static Integer TRANS_ID = 2;
	private final Logger logger = Logger.getLogger(TourBuilderService.class.getName());
	protected JdbcDaoFactory daoFactory;

	private static class Holder {
		private static TourBuilderService INSTANCE = new TourBuilderService(JdbcDaoFactory.getInstance());
	}
	
	private TourBuilderService(JdbcDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public static TourBuilderService getInstance() {
		return TourBuilderService.Holder.INSTANCE;
	}
	
	public List<Tour> getAllTours() {
		logger.info("Trying to get all tours");
		List<Tour> tourList = new ArrayList<Tour>();
		List<ArrayList<Integer>> tourAcmdTransList = new ArrayList<ArrayList<Integer>>();
		JdbcTourAcmdTransportDao tourAcmdTransDao = this.daoFactory.createJdbcTourAcmdTransportDao();
		
		tourAcmdTransList = tourAcmdTransDao.findAll();
		
		try {
			tourAcmdTransDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (ArrayList<Integer> arrList : tourAcmdTransList) {
			tourList.add(getTour(arrList));
		}
		return tourList;
	}
	
	public List<Tour> getUserTours(int userId) {
		logger.info("Trying to get user tours");
		JdbcUserTourDao userTourDao = this.daoFactory.createJdbcUserTourDao();
		List<Integer> tourListId = userTourDao.findAll(userId);
		
		List<Tour> tourList = new ArrayList<Tour>();
		
		try {
			userTourDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JdbcTourAcmdTransportDao tourAcmdTransDao = this.daoFactory.createJdbcTourAcmdTransportDao();
		
		for (Integer i : tourListId) {
			tourList.add(getTour(tourAcmdTransDao.find(i).get()));
		}
		try {
			tourAcmdTransDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tourList;
	}
	
	protected Tour getTour(List<Integer> arrList) {
		logger.info("Trying to get tour and add to it accommodation and transport");
		Optional<Tour> tour = Optional.empty();

		tour = TourService.getInstance().find(arrList.get(T_ID));
		tour.get().setAccommodation((AccommodationService.getInstance().find(arrList.get(ACMD_ID))).get());
		tour.get().setTransport((TransportService.getInstance().find(arrList.get(TRANS_ID))).get());
		
		return tour.get();
	}
}
