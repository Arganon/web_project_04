package com.wb4.controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;

public class ConnectionPoolController {
	protected static ConnectionPoolController instance;
	protected int poolSize;
	protected Deque<Connection> availableConnections;
	protected String dbHost;
	protected String dbName;
	protected String dbDriver;
	protected String dbUser;
	protected String dbPassword;
	protected String useSSL;
	
	protected ConnectionPoolController() {
		this.poolSize = 50;
		this.availableConnections = new ArrayDeque<Connection>();
		getDBInfo();
		fillConnectionPool(this.poolSize);
	}
	
	public static ConnectionPoolController getInstance() {
		if (instance == null) {
			instance = new ConnectionPoolController();
		}
		return instance;
	}
	
	protected void getDBInfo() {
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = DataBaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(input);
			
			this.dbHost = properties.getProperty("DB_HOST");
			this.dbName = properties.getProperty("DB_NAME");
			this.dbDriver = properties.getProperty("DB_DRIVER");
			this.dbUser = properties.getProperty("DB_USER");
			this.dbPassword = properties.getProperty("DB_PASSWORD");
			this.useSSL = properties.getProperty("USE_SSL");
			input.close();
			input = null;
			properties = null;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected Connection setConnection() {
		String dbHostURL = null;
		Connection connection = null;
		
		try {
			dbHostURL = this.dbHost + this.dbName + this.useSSL;
			Class.forName(this.dbDriver).newInstance();
			connection = DriverManager.getConnection(dbHostURL, dbUser, dbPassword);
			return connection;
			
		} catch (Exception ex) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	protected void fillConnectionPool(int size) {
		for (int i = 0; i < size; ++i) {
			this.availableConnections.add(setConnection());
		}
	}
	
	public synchronized Connection getConnection() throws NullPointerException  {
		if (this.availableConnections.isEmpty()) {
			return null;
		}
		return this.availableConnections.pollFirst();
	}
	
	public synchronized boolean release(Connection conn) {
		return this.availableConnections.add(conn);
	}
	
	public int getSize() {
		return this.availableConnections.size();
	}
	
	public synchronized boolean increaseSize(int newSize) {
		if (this.poolSize > newSize) {
			return false;
		}
		for ( ; this.poolSize != newSize; ++this.poolSize) {
			release(getConnection());
		}
		return true;
	}
	
	public synchronized boolean reduceSize(int newSize) {
		if (getSize() <  this.poolSize - newSize) {
			return false;
		}
		for ( ; this.poolSize != newSize; --this.poolSize) {
			getConnection();
		}
		
		return true;
	}
}
