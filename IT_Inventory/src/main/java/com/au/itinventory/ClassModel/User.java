package com.au.itinventory.ClassModel;

public class User {
	
	private String emailID;
	private String role;
	
	public User() {
		this.emailID = null;
		this.role = null;
	}

	public User(String emailID, String role) {
		super();
		this.emailID = emailID;
		this.role = role;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	
}
