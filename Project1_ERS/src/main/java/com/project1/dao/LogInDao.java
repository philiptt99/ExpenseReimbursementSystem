package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInDao {
	
	private ErsDbConnection ersCon;

	public LogInDao() {
		this.ersCon = new ErsDbConnection();
	}
	
	//method: verifyLogin
	public boolean verifyLogin (String username, String pass) {
		boolean valid = false;
		try (Connection con1 = ersCon.getDbConnection()) {
			// sql statement
			String sql = "select 1 from ers_users where ers_first_name=? and ers_last_name=?";
			PreparedStatement ps = con1.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				valid = rs.next();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		return valid;
	}
	
	

}
