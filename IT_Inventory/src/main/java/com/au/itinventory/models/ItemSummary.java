package com.au.itinventory.models;

public class ItemSummary {
	private String itemID;
	private int empID;
	private String status;
	private String location;
	private String model;
	private String yearOfPurchase;
	
	public ItemSummary() {
		super();
	}
	
	public ItemSummary(String itemID, int empID, String status, String location, String model, String yearOfPurchase) {
		super();
		this.itemID = itemID;
		this.empID = empID;
		this.status = status;
		this.location = location;
		this.model = model;
		this.yearOfPurchase = yearOfPurchase;
		
		
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYearOfPurchase() {
		return yearOfPurchase;
	}
	public void setYearOfPurchase(String yearOfPurchase) {
		this.yearOfPurchase = yearOfPurchase;
	}
	
	
	
}
