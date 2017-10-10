package com.wb4.model.dao;

import java.sql.Connection;

import com.wb4.controllers.ConnectionPoolController;
import com.wb4.controllers.DataBaseConnection;
import com.wb4.model.dao.jdbc.JdbcAccommodationDao;
import com.wb4.model.dao.jdbc.JdbcDiscountDao;
import com.wb4.model.dao.jdbc.JdbcTourAcmdTransportDao;
import com.wb4.model.dao.jdbc.JdbcTourDao;
import com.wb4.model.dao.jdbc.JdbcTransportDao;
import com.wb4.model.dao.jdbc.JdbcUserDao;
import com.wb4.model.dao.jdbc.JdbcUserTourDao;

public abstract class DaoFactory {
	public abstract JdbcAccommodationDao createJdbcAccommodationDao();
	public abstract JdbcDiscountDao createJdbcDiscountDao();
	public abstract JdbcTourAcmdTransportDao createJdbcTourAcmdTransportDao();
	public abstract JdbcTourDao createJdbcTourDao();
	public abstract JdbcTransportDao createJdbcTransportDao();
	public abstract JdbcUserDao createJdbcUserDao();
	public abstract JdbcUserTourDao createJdbcUserTourDao();
	
	public Connection getConnection() {
		return ConnectionPoolController.getInstance().getConnection();
	}
}
