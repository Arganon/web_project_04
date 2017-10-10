package com.wb4.services;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.wb4.model.dao.jdbc.JdbcDaoFactory;
import com.wb4.model.dao.jdbc.JdbcDiscountDao;

public class DiscountService {
	protected static DiscountService instance = null;
	private final Logger logger = Logger.getLogger(DiscountService.class.getName());
	protected JdbcDaoFactory daoFactory;

	protected DiscountService(JdbcDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public static DiscountService getInstance() {
		if (instance == null) {
			instance = new DiscountService(JdbcDaoFactory.getInstance());
		}
		return instance;
	}
	
	public Optional<Double> getDiscount() {
		JdbcDiscountDao discountDao = this.daoFactory.createJdbcDiscountDao();
		Optional<Double> discount = discountDao.find(1);
		logger.info("Trying to get discount.");
		try {
			discountDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discount;
	}
	
	public boolean updateDiscount(Double d_discount) {
		JdbcDiscountDao discountDao = this.daoFactory.createJdbcDiscountDao();
		logger.info("Trying to update discount.");
		boolean result;
		result = discountDao.updateDiscount(d_discount);
		
		try {
			discountDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
