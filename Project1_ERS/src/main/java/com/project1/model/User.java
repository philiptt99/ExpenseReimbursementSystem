package com.project1.model;

public class User {
		
	//private attributes
	private int userID;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String email;
	private int userRoleID;
	
	//constructors
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}




	public User(int userID, String userName, String userPassword, String firstName, String lastName, String email,
			int userRoleID) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleID = userRoleID;
	}


	//getters and setters

	public int getUserID() {
		return userID;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getUserPassword() {
		return userPassword;
	}




	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public int getUserRoleID() {
		return userRoleID;
	}




	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}


	//toString method

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRoleID=" + userRoleID + "]";
	}
	
	
	
	
	
	
	
}
