package com.wb4.services;

import java.util.Optional;

import com.wb4.entity.Transport;
import com.wb4.model.dao.jdbc.JdbcDaoFactory;
import com.wb4.model.dao.jdbc.JdbcTransportDao;

public class TransportService {
	protected JdbcDaoFactory daoFactory;
	
	private static class Holder {
		private static TransportService INSTANCE = new TransportService(JdbcDaoFactory.getInstance());
	}
	
	private TransportService(JdbcDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public static TransportService getInstance() {
		return TransportService.Holder.INSTANCE;
	}
	
	public Optional<Transport> find(Integer acmd_id) {
		JdbcTransportDao transDao = this.daoFactory.createJdbcTransportDao();
		Optional<Transport> result = transDao.find(acmd_id);
		
		try {
			transDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
