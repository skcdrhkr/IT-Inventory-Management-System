package com.au.itinventory.models;

public class Inventory {
	private String itemID;
	private String itemTypeID;
	private String itemName;
	private String serviceTag;
	private String dateOfPurchase;
	private String status;
	public Inventory() {
		super();
	}
	public Inventory(String itemID, String itemTypeID, String itemName, String serviceTag, String dateOfPurchase,
			String status) {
		super();
		this.itemID = itemID;
		this.itemTypeID = itemTypeID;
		this.itemName = itemName;
		this.serviceTag = serviceTag;
		this.dateOfPurchase = dateOfPurchase;
		this.status = status;
	}
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
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
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
