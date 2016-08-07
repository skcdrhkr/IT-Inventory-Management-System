package com.au.itinventory.models;

public class CountSummary {
	private int allocated;
	private int defective;
	private int inStock;
	private int total;
	public CountSummary() {
		super();
	}
	public CountSummary(int allocated, int defective, int inStock, int total) {
		super();
		this.allocated = allocated;
		this.defective = defective;
		this.inStock = inStock;
		this.total = total;
	}
	public int getAllocated() {
		return allocated;
	}
	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}
	public int getDefective() {
		return defective;
	}
	public void setDefective(int defective) {
		this.defective = defective;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
