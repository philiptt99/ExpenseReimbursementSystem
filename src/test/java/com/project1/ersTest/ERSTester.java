package com.project1.ersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project1.dao.EmployeeDao;
import com.project1.dao.LogInDao;
import com.project1.model.User;
import com.project1.service.EmployeeService;
import com.project1.service.LogInService;
import com.project1.service.ManagerService;


public class ERSTester {
	
	@Mock
	private LogInService logServ;
	private EmployeeService empServ;
	private ManagerService manServ;
	private LogInDao fakeLogDao;
	private EmployeeDao fakeEmpDao;
	
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); //initialize Mockito to create an instance of field member 
		logServ = new LogInService();
		empServ = new EmployeeService();
		manServ = new ManagerService();
//		public User(int userID, String userName, String userPassword, String firstName, String lastName, String email,
//				int userRoleID) {
//		User emp = new User(111,"jdoe1","123","john","doe","jdoe1@yahoo.com",101);
		
		//some methods to test
//		when(fakeLogDao.verifyLogin("tsmith123", "abc")).thenReturn(false);
//		when(fakeEmpDao.getUser("jdoe1")).thenReturn(emp);
		
	}
	
	@Test
	public void verifyLogInTest() {
		assertEquals(false, logServ.verifyLogin("jdoe1", "123"),"verifyLogIn method works!");
	}
	
	@Test
	public void getUserTest() {
		assertEquals("john",empServ.getUser("jdoe1").getFirstName(),"getUser method works!");
	}
	
	@Test
	public void getUserEmailTest() {
		assertEquals("jdoe1@yahoo.com",empServ.getUser("jdoe1").getEmail(),"getUser method works!");
	}
	
	@Test
	public void getUserRoleTest() {
		assertEquals(101,empServ.getUser("jdoe1").getUserRoleID(),"getUser method works!");
	}
	
	@Test
	public void getManagerInfoTest() {
		assertEquals("Nathan", manServ.getManagerInfo("nate99").getFirstName(),"getManagerInfo method works!");
	}
	
	
	
	

}
