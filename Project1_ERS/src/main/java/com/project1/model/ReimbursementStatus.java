package com.project1.model;

public class ReimbursementStatus {
	
	private int statusID;
	private String status;
	
	
	public ReimbursementStatus() {
		// TODO Auto-generated constructor stub
	}


	public ReimbursementStatus(int statusID, String status) {
		super();
		this.statusID = statusID;
		this.status = status;
	}


	public int getStatusID() {
		return statusID;
	}


	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ReimbursementStatus [statusID=" + statusID + ", status=" + status + "]";
	}
	
	
	

}
