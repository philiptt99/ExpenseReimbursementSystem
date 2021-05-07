package com.project1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ErsDbConnection {
	
	//url of ersdb
	private final String URL = "jdbc:postgresql://rev-can-training.czbbfxriy83g.ca-central-1.rds.amazonaws.com:5432/ersdb";
	//user of ersdb
	private final String username = "ersuser";
	//password of ersdb
	private final String password = "Passw0rd";
	
	public Connection getDbConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, username, password);
	}
	
	
	

}
