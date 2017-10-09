package com.wb4.services;

import java.util.Optional;

import com.wb4.controllers.DataBaseConnection;
import com.wb4.entity.Transport;
import com.wb4.model.dao.jdbc.JdbcTransportDao;

public class TransportService {
	protected static TransportService instance = null;
	
	protected TransportService() {}
	
	public static TransportService getInstance() {
		if (instance == null) {
			instance = new TransportService();
		}
		return instance;
	}
	
	public Optional<Transport> find(Integer acmd_id) {
		JdbcTransportDao transDao = new JdbcTransportDao(DataBaseConnection.getInstance().getConnection());
		Optional<Transport> result = transDao.find(acmd_id);
		
		try {
			transDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
