package com.wb4.services;

import java.util.Optional;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.Accommodation;
import com.wb4.model.dao.jdbc.JdbcAccommodationDao;
import com.wb4.model.dao.jdbc.JdbcDaoFactory;

import org.apache.log4j.Logger;

public class AccommodationService {
	protected static AccommodationService instance = null;
	private final Logger logger = Logger.getLogger(AccommodationService.class.getName());
	protected JdbcDaoFactory daoFactory;
	
	protected AccommodationService(JdbcDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
		
	public static AccommodationService getInstance() {
		if (instance == null) {
			instance = new AccommodationService(JdbcDaoFactory.getInstance());
		}
		return instance;
	}
	
	public Optional<Accommodation> find(Integer acmd_id) {
		logger.info(" Trying to find Accommodation with " + acmd_id + " ID");
		JdbcAccommodationDao acmdDao = new JdbcAccommodationDao(DataBaseConnection.getInstance().getConnection());
		Optional<Accommodation> result = acmdDao.find(acmd_id);
		
		try {
			acmdDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
