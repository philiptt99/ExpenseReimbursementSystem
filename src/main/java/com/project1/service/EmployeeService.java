package com.project1.service;

import java.util.List;

import com.project1.dao.EmployeeDao;
import com.project1.model.Reimbursement;
import com.project1.model.User;

public class EmployeeService {
	
	private EmployeeDao empDao;

	public EmployeeService() {
		super();
		this.empDao = new EmployeeDao();
	}
	
	public User getUser(String username) {
		return empDao.getUser(username);
	}
	
	public List<Reimbursement> viewPastTickets(User user){
		return empDao.viewPastTickets(user);
	}
	
	public void inserNewTicket(User user, Reimbursement reim) {
		 empDao.inserNewTicket(user, reim);
	}
	
	public void insertUser(User user) {
		empDao.insertUser(user);
	}
	
	

}
