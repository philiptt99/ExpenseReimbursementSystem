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

public class ManagerDao {

	private ErsDbConnection ersCon;

	public ManagerDao() {
		this.ersCon = new ErsDbConnection();
	}

	// method: getManagerInfo -> return the user info from their username
	public User getManagerInfo(String username) {

		try (Connection con = ersCon.getDbConnection()) {
			User employee = new User();
			// sql statement
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				employee = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));

			}
			return employee;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// method: viewAllTickets by status
	public List<Reimbursement> viewByStatus(int status) {
		List<Reimbursement> statusList = new ArrayList<>();
		try (Connection con = ersCon.getDbConnection()) {
			String sql = "select * from ers_reimbursement where reimb_status_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, status);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				statusList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statusList;
	}

	// method: viewAllTickets by employeeID
	public List<Reimbursement> viewByEmployeeID(int employeeID) {
		List<Reimbursement> statusList = new ArrayList<>();
		try (Connection con = ersCon.getDbConnection()) {
			String sql = "select * from ers_reimbursement where reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employeeID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				statusList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statusList;
	}

	// method: viewAllTickets by managerID
	public List<Reimbursement> viewAllbyManID(int managerID) {
		List<Reimbursement> statusList = new ArrayList<>();
		try (Connection con = ersCon.getDbConnection()) {
			String sql = "select * from ers_reimbursement where reimb_resolver=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, managerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				statusList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statusList;
	}
	
	public List<Reimbursement> viewAll() {
		List<Reimbursement> statusList = new ArrayList<>();
		try (Connection con = ersCon.getDbConnection()) {
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				statusList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statusList;
	}

	// method: updateTicketStatus by reimb_id
	public void updateTicketStatus(int reimbID, int status, User manager) {
		try (Connection con = ersCon.getDbConnection()) {
			String sql = "update ers_reimbursement set reimb_resolved=?, reimb_resolver=?, reimb_status_id=? where reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setTimestamp(1, Timestamp.from(java.time.Instant.now()));
			ps.setInt(2, manager.getUserID());
			ps.setInt(3, status);
			ps.setInt(4, reimbID);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
