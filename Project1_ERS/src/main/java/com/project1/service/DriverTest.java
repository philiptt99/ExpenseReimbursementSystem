package com.project1.service;

import java.sql.Timestamp;

import com.project1.dao.EmployeeDao;
import com.project1.model.Reimbursement;
import com.project1.model.User;

public class DriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeDao eDao = new EmployeeDao();
		
		//test getUser method
		User newUser = eDao.getUser("jdoe1");
		
		System.out.println(newUser.getFirstName()+ newUser.getEmail());
		
		//test inserNewTicket method
		Reimbursement newRe = new Reimbursement();
		newRe.setAuthorID(newUser.getUserID());
		newRe.setDescription("lodging in cali");
		newRe.setReimAmount(1000.50);
		newRe.setStatusID(2);
		newRe.setTypeID(10);
		newRe.setResolverID(112);
		eDao.inserNewTicket(newUser, newRe);
		
		//test insertUser method:
		User newUser2 = new User(1234,"ttran73","9876","tam","tran","tamtran7236@gmail.com",101);
		eDao.insertUser(newUser2);
	}

}
