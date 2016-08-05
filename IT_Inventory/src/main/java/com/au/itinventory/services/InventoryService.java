package com.au.itinventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.dao.InventoryDao;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemSummary;

@Service
public class InventoryService {

	@Autowired
	InventoryDao inventoryDao;
	
	ApplicationContext context;
	
	public int allocateItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		String itemID=empInventory.getItemID();
		Integer empID=inventoryDao.isAllotedToEmp(itemID);
		int flag=0;
		String status=inventoryDao.getStatus(itemID);
		if(empID!=null && empID==1772)
		{
			//check for status either returned or in stock
			//in stock and can be alloted to emp
			//entry to log and inventory table
			
			if(status!=null)
			{
				/*if(status.equals("in-stock"))
				{
					empInventory.setStatus("allocated");
					flag=inventoryDao.allocateItemToEmp(empInventory);
				}*/
				if(status.equals("returned"))
				{
					flag=inventoryDao.allocateItemToEmp(empInventory,status);
				}
				else if(status.equals("defective"))
				{
					//cannot allocate defective piece
					flag=3;
				}
			}
			
		}
		else if(empID!=null){
			flag=2;
			System.out.println("cannot allocate, already alloted (or) enter correct ID");
		}
		else
		{
			if(status.equals("in-stock"))
			{
				flag=inventoryDao.allocateItemToEmp(empInventory,status);
			}
		}
		return flag;
	}

	public int deAllocateItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag=inventoryDao.deAllocateItem(empInventory);
		
		return flag;
	}

	public int addItem(Item item) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag=inventoryDao.addItem(item);
		return flag;
	}

	public int removeItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		empInventory.setStatus("removed");
		int flag=inventoryDao.deAllocateItem(empInventory);
		
		return flag;
	}

	public int createNewItemType(String newItem) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag=inventoryDao.createNewItemType(newItem);
		return flag;
	}

	public List<ItemSummary> getItemSummary(String itemName) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		List<ItemSummary> itemsList=inventoryDao.getItemSummary(itemName);
		return itemsList;
	}
	
}
