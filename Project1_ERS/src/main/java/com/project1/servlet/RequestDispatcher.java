package com.project1.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project1.controller.EmployeeController;
import com.project1.controller.LogInController;
import com.project1.controller.ManagerController;

public class RequestDispatcher {

	public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {

		switch (req.getRequestURI()) {

			case "/Project1_ERS/login.change":
				System.out.println("In login.change dispatcher");
				return LogInController.login(req);
			
			case "/Project1_ERS/register.change":
				System.out.println("In register.change dispatcher");
				return EmployeeController.register(req);
			
				
			case "/Project1_ERS/logout.change":
				System.out.println("In logout.change dispatcher");
				HttpSession sess = req.getSession();
				sess.removeAttribute("currentEmployee");
				sess.invalidate();
				req.setAttribute("Error", "Session has ended.  Please login.");
				res.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
				res.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
				res.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
				res.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
				
				return "/html/login.html";
				
			case "/Project1_ERS/addReimb.change":
				System.out.println("in Add new Reimbursement");
				return LogInController.insertNewReimb(req);
				
			case "/Project1_ERS/processReimb.change":
				System.out.println("in Process reimbursement");
				return ManagerController.insertNewReimb(req);
				
			default:
				System.out.println("in default");
				return "/html/unsuccessfulLogin.html";
		}
		
	}

}
