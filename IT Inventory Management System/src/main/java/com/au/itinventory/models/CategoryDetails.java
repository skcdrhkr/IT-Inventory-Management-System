package com.au.itinventory.models;

public class CategoryDetails {
	private String itemTypeID;
	private String model;
	private String serviceTag;
	
	public CategoryDetails() {
		super();
	}

	public CategoryDetails(String itemTypeID, String model, String serviceTag) {
		super();
		this.itemTypeID = itemTypeID;
		this.model = model;
		this.serviceTag = serviceTag;
	}

	public String getItemTypeID() {
		return itemTypeID;
	}

	public void setItemTypeID(String itemTypeID) {
		this.itemTypeID = itemTypeID;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getServiceTag() {
		return serviceTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}
	
	
}
