package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.project1.model.Reimbursement;
import com.project1.model.User;

public class EmployeeDao {
	
	private ErsDbConnection ersCon;

	public EmployeeDao() {
		this.ersCon = new ErsDbConnection();
	}
	
	//method: getUser -> return the user info from their username
	public User getUser(String username) {
		
		try (Connection con = ersCon.getDbConnection()){
			User employee = new User();
			//sql statement
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				employee = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6), rs.getInt(7));
				
			}
			return employee;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	//method: viewPastTickets
	public List<Reimbursement> viewPastTickets(User user){
		List<Reimbursement> ticketList = new ArrayList<>();
		try (Connection con = ersCon.getDbConnection()){
			//sql statement
			String sql = "select * from ers_reimbursement where reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ticketList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticketList;
	}
	
	//method: insertNewTicket
	public void inserNewTicket(User user, Reimbursement reim) {
		try(Connection con = ersCon.getDbConnection()){
			//1) find the nextval to get the reimb_int from ers_reimbursement
			String sqlId = "select nextval('serial')";
			PreparedStatement ps1 = con.prepareStatement(sqlId);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				int newReimbID = rs1.getInt(1);
				reim.setReimbID(newReimbID);
			}
			
			//2)insert into ers_reimbursement table 
			String sql2 = "insert into ers_reimbursement values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			
			ps2.setInt(1, reim.getReimbID());
			ps2.setDouble(2, reim.getReimAmount());
			ps2.setTimestamp(3, Timestamp.from(java.time.Instant.now()));
			ps2.setTimestamp(4, Timestamp.from(java.time.Instant.now()));
			ps2.setString(5,reim.getDescription());
			ps2.setInt(6, user.getUserID());
			ps2.setInt(7, reim.getResolverID());
			ps2.setInt(8, reim.getStatusID());
			ps2.setInt(9, reim.getTypeID());
			
			ps2.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//method: insertUser
		public void insertUser(User user) {
			try(Connection con = ersCon.getDbConnection()){
				String sql = "insert into ers_users values(?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setInt(1, user.getUserID());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getUserPassword());
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getLastName());
				ps.setString(6, user.getEmail());
				ps.setInt(7, user.getUserRoleID());
				
				ps.execute();
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
}
