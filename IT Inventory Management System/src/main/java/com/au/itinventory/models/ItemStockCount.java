package com.au.itinventory.models;

public class ItemStockCount {
	private String itemCategory;
	private int inStockCount;
	public ItemStockCount() {
		super();
	}
	public ItemStockCount(String itemCategory, int inStockCount) {
		super();
		this.itemCategory = itemCategory;
		this.inStockCount = inStockCount;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public int getInStockCount() {
		return inStockCount;
	}
	public void setInStockCount(int inStockCount) {
		this.inStockCount = inStockCount;
	}
	
	
		
}
