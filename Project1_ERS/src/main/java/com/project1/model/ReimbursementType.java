package com.project1.model;

public class ReimbursementType {
	
	//private attributes
	private int typeID;
	private String type;
	
	
	
	
	public ReimbursementType() {
		// TODO Auto-generated constructor stub
	}




	public ReimbursementType(int typeID, String type) {
		super();
		this.typeID = typeID;
		this.type = type;
	}




	@Override
	public String toString() {
		return "ReimbursementType [typeID=" + typeID + ", type=" + type + "]";
	}
	
	
	
	
}	
