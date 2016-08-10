package com.au.itinventory.models;

public class Item {
	
	private String itemID;
	private String itemtypeID;
	private String itemName;
	private String serviceTag;
	private String model;
	private int warranty;
	private String dateOfPurchase;
	private String ram;
	private String processor;
	private String os;
	
	public Item() {
		super();
	}


	public Item(String itemID, String itemtypeID, String itemName, String serviceTag, String model, int warranty,
			String dateOfPurchase, String ram, String processor, String os) {
		super();
		this.itemID = itemID;
		this.itemtypeID = itemtypeID;
		this.itemName = itemName;
		this.serviceTag = serviceTag;
		this.model = model;
		this.warranty = warranty;
		this.dateOfPurchase = dateOfPurchase;
		this.ram = ram;
		this.processor = processor;
		this.os = os;
	}


	public String getItemID() {
		return itemID;
	}


	public void setItemID(String itemID) {
		this.itemID = itemID;
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


	public String getItemtypeID() {
		return itemtypeID;
	}

	public void setItemtypeID(String itemtypeID) {
		this.itemtypeID = itemtypeID;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	
	
}
