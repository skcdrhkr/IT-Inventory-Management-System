package com.au.itinventory.models;

public class UserRole {
	private int roleID;
	private String role;
	
	public UserRole() {
		super();
	}

	public UserRole(int roleID, String role) {
		super();
		this.roleID = roleID;
		this.role = role;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
