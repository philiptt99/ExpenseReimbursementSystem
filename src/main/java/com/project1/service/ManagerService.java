package com.project1.service;

import java.util.List;

import com.project1.dao.ManagerDao;
import com.project1.model.Reimbursement;
import com.project1.model.User;

public class ManagerService {
	
	private ManagerDao manDao;
	
	public ManagerService() {
		this.manDao = new ManagerDao();
	}
	
	public User getManagerInfo(String username) {
		return manDao.getManagerInfo(username);
	}
	
	public List<Reimbursement> viewByStatus(int status){
		return manDao.viewByStatus(status);
	}
	
	public List<Reimbursement> viewByEmployeeID(int employeeID){
		return manDao.viewByEmployeeID(employeeID);
	}
	
	public List<Reimbursement> viewAllbyManID(int managerID) {
		return manDao.viewAllbyManID(managerID);
	}
	
	public List<Reimbursement> viewAll() {
		return manDao.viewAll();
	}
	public void updateTicketStatus(int reimbID, int status, User manager) {
		manDao.updateTicketStatus(reimbID, status, manager);
	}

}
