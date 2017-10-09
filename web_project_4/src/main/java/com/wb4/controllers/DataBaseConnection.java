package com.wb4.controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBaseConnection {
	protected Connection connection = null;
	protected static DataBaseConnection instance;
	
	protected String dbHost;
	protected String dbName;
	protected String dbDriver;
	protected String dbUser;
	protected String dbPassword;
	protected String useSSL;
	
	protected DataBaseConnection() {}
	
	public static DataBaseConnection getInstance() {
		if (instance == null) {
			instance = new DataBaseConnection();
		}
		return instance;
	}
	
	public void getDBInfo() {
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
	
	public Connection getConnection() {
		String dbHostURL = null;
		
		try {
			getDBInfo();
			dbHostURL = this.dbHost + this.dbName + this.useSSL;
			Class.forName(this.dbDriver).newInstance();
			this.connection = DriverManager.getConnection(dbHostURL, dbUser, dbPassword);
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

	public ResultSet getResultSet(String sql) {
		
		try {
			if (this.connection == null || this.connection.isClosed()) {
				connection = getConnection();
			}
			Statement statement = connection.createStatement();
			
			return statement.executeQuery(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void executeSql(String sql) {
		
		try {
			if (this.connection == null || this.connection.isClosed()) {
				connection = getConnection();
			}
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			
			statement.close();
			statement = null;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
