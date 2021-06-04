package com.project1.controller;

import java.io.IOException;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.EmployeeService;

public class EmployeeController {

	static EmployeeService empServ = new EmployeeService();
	public final static Logger log2 = Logger.getLogger(EmployeeController.class);

	public static void getPastReimb(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		User emp = (User) req.getSession().getAttribute("currentEmployee");
		List<Reimbursement> listReimb = new ArrayList<>();

		listReimb = empServ.viewPastTickets(emp);
		// log info:
		log2.info("View past reimbursements");
		res.getWriter().write(new ObjectMapper().writeValueAsString(listReimb));
	}

	public static String register(HttpServletRequest req) {
		System.out.println("in EmployeeController");
		if (!req.getMethod().equals("POST")) {
			System.out.println("error1");
			return "html/login.html";
		}

		// get information from new register:
		int userID = Integer.parseInt(req.getParameter("employeeID"));
		System.out.println("employeeID:" +userID);
		String userName = req.getParameter("userName");
		System.out.println("username: "+userName);
		String pWord = req.getParameter("passWord");
		System.out.println("password: " +pWord);
		String firstN = req.getParameter("firstName");
		System.out.println("firstName: " + firstN);
		String lastN = req.getParameter("lastName");
		System.out.println("lastName: " + lastN);
		String email = req.getParameter("email");
		System.out.println("email: "+ email);
		String empID = req.getParameter("employment_type");
		System.out.println("employee type: "+ empID);
		int employeeID =0;
		if (empID.equalsIgnoreCase("employee")) {
			employeeID = 101;
		} else {
			employeeID = 102;
		}

		User newUser = new User(userID, userName, pWord, firstN, lastN, email, employeeID);

		empServ.insertUser(newUser);
		sendEmail(newUser, "annon@gmail.com", "1234");

		return "html/login.html";
	}

	public static void sendEmail(User user, String fromEmail, String senderPass) {
		String host = "smtp.gmail.com";
		String toEmail = user.getEmail();
		
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(fromEmail, senderPass);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmail));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			// Set Subject: header field
			message.setSubject("Your Registration Information from ERS");

			// Now set the actual message
			message.setText("Hello, "+ user.getFirstName()+" "+user.getLastName()+",\n"+
							"Your username is "+ user.getUserName() +" and your password is "+ user.getUserPassword() + ".\n"+
							"You can now log in to the ERS system.");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
