package com.project1.service;

import com.project1.dao.EmployeeDao;
import com.project1.dao.LogInDao;
import com.project1.model.User;

public class LogInService {

	private LogInDao logDao;
	private EmployeeDao empDao;

	public LogInService() {
		// TODO Auto-generated constructor stub
		this.logDao = new LogInDao();
		this.empDao = new EmployeeDao();
	}

	public boolean verifyLogin(String username, String pass) {
		return logDao.verifyLogin(username, pass);
	}

	public User getUser(String username, String pass) {
		if (username.length() == 0 || pass.length() == 0) {
			return null;
		} else {
			User user = empDao.getUser(username);
			if (user != null) {
				if (user.getUserPassword().equals(pass)) {
					return user;
				}
			}

		}
		return null;
	}

}
