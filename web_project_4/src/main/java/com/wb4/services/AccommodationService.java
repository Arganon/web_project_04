package com.wb4.services;

import java.util.Optional;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.Accommodation;
import com.wb4.model.dao.jdbc.JdbcAccommodationDao;

public class AccommodationService {
	protected static AccommodationService instance = null;
	
	protected AccommodationService() {}
	
	public static AccommodationService getInstance() {
		if (instance == null) {
			instance = new AccommodationService();
		}
		return instance;
	}
	
	public Optional<Accommodation> find(Integer acmd_id) {
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
