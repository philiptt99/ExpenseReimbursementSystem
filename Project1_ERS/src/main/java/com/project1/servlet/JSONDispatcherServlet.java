package com.project1.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.controller.EmployeeController;
import com.project1.controller.LogInController;
import com.project1.controller.ManagerController;
import com.project1.model.User;

public class JSONDispatcherServlet {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Project1_ERS/employee.json":
			System.out.println("in Loginasdasdas");
			LogInController.getSessionUser(req, res);
			break;
			
		case "/Project1_ERS/manager.json":
			System.out.println("in Manager reimbursement");
			LogInController.getSessionManager(req, res);
			break;
		
		case "/Project1_ERS/pastreimb.json":
			System.out.println("in Past reimbursement");
			EmployeeController.getPastReimb(req, res);
			break;
		
		case "/Project1_ERS/allReimb.json":
			System.out.println("in All reimbursement");
			ManagerController.getAll(req, res);
			break;
		
			
		default:
			System.out.println("in default JSONDispatcher");
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		}
	}

}
