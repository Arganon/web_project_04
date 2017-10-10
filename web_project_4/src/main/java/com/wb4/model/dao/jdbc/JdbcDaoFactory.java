package com.wb4.model.dao.jdbc;

import com.wb4.model.dao.DaoFactory;

public class JdbcDaoFactory extends DaoFactory {
	protected static JdbcDaoFactory instance = null;
	
	protected JdbcDaoFactory() {}
	
	public static JdbcDaoFactory getInstance() {
		if (instance == null) {
			instance = new JdbcDaoFactory();
		}
		return instance;
	}
	
	@Override
	public JdbcAccommodationDao createJdbcAccommodationDao() {
		return new JdbcAccommodationDao(getConnection());
	}

	@Override
	public JdbcDiscountDao createJdbcDiscountDao() {
		return new JdbcDiscountDao(getConnection());
	}

	@Override
	public JdbcTourAcmdTransportDao createJdbcTourAcmdTransportDao() {
		return new JdbcTourAcmdTransportDao(getConnection());
	}

	@Override
	public JdbcTourDao createJdbcTourDao() {
		return new JdbcTourDao(getConnection());
	}

	@Override
	public JdbcTransportDao createJdbcTransportDao() {
		return new JdbcTransportDao(getConnection());
	}

	@Override
	public JdbcUserDao createJdbcUserDao() {
		return new JdbcUserDao(getConnection());
	}

	@Override
	public JdbcUserTourDao createJdbcUserTourDao() {
		return new JdbcUserTourDao(getConnection());
	}

}
