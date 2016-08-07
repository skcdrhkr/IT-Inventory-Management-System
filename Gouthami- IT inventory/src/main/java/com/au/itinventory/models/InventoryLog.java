package com.au.itinventory.models;

public class InventoryLog {
	private String itemID;
	private int empID;
	private String itemName;
	private String status;
	
	public InventoryLog() {
		super();
	}

	public InventoryLog(String itemID, int empID, String itemName, String status) {
		super();
		this.itemID = itemID;
		this.empID = empID;
		this.itemName = itemName;
		this.status = status;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
