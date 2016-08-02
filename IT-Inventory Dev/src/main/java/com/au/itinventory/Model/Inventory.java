package com.au.itinventory.Model;

public class Inventory {
	
	private String itemID;
	private int empID;
	private String itemTypeID;
	private String itemName;
	private String serviceTag;
	private String status;
	
	public Inventory() {
		super();
	}

	public Inventory(String itemID, int empID, String itemTypeID, String itemName, String serviceTag, String status) {
		super();
		this.itemID = itemID;
		this.empID = empID;
		this.itemTypeID = itemTypeID;
		this.itemName = itemName;
		this.serviceTag = serviceTag;
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

	public String getItemTypeID() {
		return itemTypeID;
	}

	public void setItemTypeID(String itemTypeID) {
		this.itemTypeID = itemTypeID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getServiceTag() {
		return serviceTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
