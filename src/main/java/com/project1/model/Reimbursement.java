package com.project1.model;

public class Reimbursement {
	
	//private attribute
	private int reimbID;
	private double reimAmount;
	private String reimSubmit;
	private String reimResolve;
	private String description;
	private int authorID;
	private int resolverID;
	private int statusID;
	private int typeID;
	
	
	
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}


	
	//constructor without reimbID
	public Reimbursement(double reimAmount, String reimSubmit, String reimResolve, String description, int authorID,
			int resolverID, int statusID, int typeID) {
		super();
		this.reimbID = 0;
		this.reimAmount = reimAmount;
		this.reimSubmit = reimSubmit;
		this.reimResolve = reimResolve;
		this.description = description;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}




	public Reimbursement(int reimbID, double reimAmount, String reimSubmit, String reimResolve, String description,
			int authorID, int resolverID, int statusID, int typeID) {
		super();
		this.reimbID = reimbID;
		this.reimAmount = reimAmount;
		this.reimSubmit = reimSubmit;
		this.reimResolve = reimResolve;
		this.description = description;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}




	public int getReimbID() {
		return reimbID;
	}




	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}




	public double getReimAmount() {
		return reimAmount;
	}




	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}




	public String getReimSubmit() {
		return reimSubmit;
	}




	public void setReimSubmit(String reimSubmit) {
		this.reimSubmit = reimSubmit;
	}




	public String getReimResolve() {
		return reimResolve;
	}




	public void setReimResolve(String reimResolve) {
		this.reimResolve = reimResolve;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public int getAuthorID() {
		return authorID;
	}




	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}




	public int getResolverID() {
		return resolverID;
	}




	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}




	public int getStatusID() {
		return statusID;
	}




	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}




	public int getTypeID() {
		return typeID;
	}




	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}




	@Override
	public String toString() {
		return "Reimbursement [reimbID=" + reimbID + ", reimAmount=" + reimAmount + ", reimSubmit=" + reimSubmit
				+ ", reimResolve=" + reimResolve + ", description=" + description + ", authorID=" + authorID
				+ ", resolverID=" + resolverID + ", statusID=" + statusID + ", typeID=" + typeID + "]";
	}
	
	
	
}
