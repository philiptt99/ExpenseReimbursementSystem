package com.project1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.ManagerService;

public class ManagerController {
	
	static ManagerService manServ = new ManagerService();
	public final static Logger log2 = Logger.getLogger(ManagerController.class);
	
	public static void getAll(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User manager = (User) req.getSession().getAttribute("currentManager");
		List<Reimbursement> listReimb = new ArrayList<>();
		listReimb = manServ.viewAll();
		log2.info("Cast all reimb list into JSON list");
		res.getWriter().write(new ObjectMapper().writeValueAsString(listReimb));

	}
	
	public static String insertNewReimb(HttpServletRequest req) throws JsonProcessingException, IOException {
		
		if (!req.getMethod().equals("POST")) {
			System.out.println("error in POST method");
			return "html/managerHome.html";
		}
		
		User manager = (User) req.getSession().getAttribute("currentManager");
		//get info from form
		int reimbID = Integer.parseInt(req.getParameter("reimbID"));
		String status = req.getParameter("status_type");
		int statusNo;
		if (status.equalsIgnoreCase("approved")) {
			statusNo = 2;
		}
		else {
			statusNo = 3;
		}
		
		//updateTicketStatus function
		manServ.updateTicketStatus(reimbID, statusNo, manager);
		log2.info("Update ticket status");
		return "html/managerHome.html";
		
		
	}

}
