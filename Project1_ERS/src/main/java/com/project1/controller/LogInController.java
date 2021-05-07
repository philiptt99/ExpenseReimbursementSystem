package com.project1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.EmployeeService;
import com.project1.service.LogInService;

public class LogInController {

	static LogInService logServ = new LogInService();
	static EmployeeService empServ = new EmployeeService();
	public final static Logger log2 = Logger.getLogger(LogInController.class);

	public static String login(HttpServletRequest req) {

		System.out.println("in LogInController");
		if (!req.getMethod().equals("POST")) {
			System.out.println("error1");
			return "html/login.html";
		}

		User user = logServ.getUser(req.getParameter("userName"), req.getParameter("passWord"));
		// System.out.println(user.getUserID()==111);
		if (user == null) {
			return "wrongcreds.change";
		} else {
			if (user.getUserRoleID() == 101) {
				System.out.println("error3");
				req.getSession().setAttribute("currentEmployee", user);
				log2.info("Cast a new employee User from form");
				return "/html/empTest.html";
			} else {
				System.out.println("erro4");
				req.getSession().setAttribute("currentManager", user);
				log2.info("Cast a new manager User from form");
				return "/html/managerHome.html";
			}
		}

	}

	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		User emp = (User) req.getSession().getAttribute("currentEmployee");
		res.getWriter().write(new ObjectMapper().writeValueAsString(emp));
		log2.info("Cast the employee User into JSON");
	}
	
	public static void getSessionManager(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		User manager = (User) req.getSession().getAttribute("currentManager");
		res.getWriter().write(new ObjectMapper().writeValueAsString(manager));
		log2.info("Cast the manager User into JSON");
	}
	

	public static String insertNewReimb(HttpServletRequest req) throws JsonProcessingException, IOException {

		if (!req.getMethod().equals("POST")) {
			System.out.println("error1");
			return "html/login.html";
		}

		User emp = (User) req.getSession().getAttribute("currentEmployee");
		double amount = Double.parseDouble(req.getParameter("addAmount"));
		String desc = req.getParameter("description");
		String type = req.getParameter("reimb_type");

		int reimbType;
		if (type.equalsIgnoreCase("lodging")) {
			reimbType = 10;
		} else if (type.equalsIgnoreCase("travel")) {
			reimbType = 20;
		} else if (type.equalsIgnoreCase("food")) {
			reimbType = 30;
		} else { // other
			reimbType = 40;
		}

		// set the newReimbursment
		Reimbursement newRe = new Reimbursement();
		newRe.setAuthorID(emp.getUserID());
		newRe.setDescription(desc);
		newRe.setResolverID(112);
		newRe.setReimAmount(amount);
		newRe.setTypeID(reimbType);
		newRe.setStatusID(1);
		empServ.inserNewTicket(emp, newRe);
		log2.info("Insert into ersdb with new Reimb");
		req.getSession().setAttribute("currentEmployee", emp);
		return "html/empTest.html";

	}

}
