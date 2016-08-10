package com.au.itinventory.models;

public class ItemStatus {

	private int Total;
	private int Allocated;
	private int Instock;
	private int Defective;
	
	
	
	public ItemStatus() {
		super();
	}



	public int getTotal() {
		return Total;
	}



	public void setTotal(int total) {
		Total = total;
	}



	public int getAllocated() {
		return Allocated;
	}



	public void setAllocated(int allocated) {
		Allocated = allocated;
	}



	public int getInstock() {
		return Instock;
	}



	public void setInstock(int instock) {
		Instock = instock;
	}



	public int getDefective() {
		return Defective;
	}



	public void setDefective(int defective) {
		Defective = defective;
	}
		
	
	
}
