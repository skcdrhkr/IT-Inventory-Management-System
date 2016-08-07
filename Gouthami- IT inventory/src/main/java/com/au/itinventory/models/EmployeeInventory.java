package com.au.itinventory.models;

public class EmployeeInventory {
	private String itemID;
	private int empID;
	private String itemCategory;
	private String status;
	
	public EmployeeInventory() {
		super();
	}

	public EmployeeInventory(String itemID, int empID, String itemCategory,String status) {
		super();
		this.itemID = itemID;
		this.empID = empID;
		this.itemCategory = itemCategory;
		this.status=status;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
