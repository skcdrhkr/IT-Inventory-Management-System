package com.au.itinventory.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.dao.InventoryDao;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemStatus;
import com.au.itinventory.models.ItemSummary;

import au.com.bytecode.opencsv.CSVReader;

@Service
public class InventoryService {

	@Autowired
	InventoryDao inventoryDao;

	ApplicationContext context;

	public int allocateItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		String itemID = empInventory.getItemID();
		Integer empID = inventoryDao.isAllotedToEmp(itemID);
		int flag = 0;
		String status = inventoryDao.getStatus(itemID);
		if (empID != null && empID == 1772) {
			// check for status either returned or in stock
			// in stock and can be alloted to emp
			// entry to log and inventory table

			if (status != null) {
				/*
				 * if(status.equals("in-stock")) {
				 * empInventory.setStatus("allocated");
				 * flag=inventoryDao.allocateItemToEmp(empInventory); }
				 */
				if (status.equalsIgnoreCase("returned")) {
					flag = inventoryDao.allocateItemToEmp(empInventory, status);
				} else if (status.equalsIgnoreCase("defective")) {
					// cannot allocate defective piece
					flag = 3;
				}
			}

		} else if (empID != null) {
			flag = 2;
			System.out.println("cannot allocate, already alloted (or) enter correct ID");
		} else {
			if (status.equalsIgnoreCase("in-stock")) {
				flag = inventoryDao.allocateItemToEmp(empInventory, status);
			}
		}
		return flag;
	}

	public int deAllocateItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.deAllocateItem(empInventory);

		return flag;
	}

	public int addItem(Item item) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.addItem(item);
		return flag;
	}

	public int removeItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		empInventory.setStatus("removed");
		int flag = inventoryDao.deAllocateItem(empInventory);

		return flag;
	}

	public int createNewItemType(String newItem) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.createNewItemType(newItem);
		return flag;
	}

	public List<ItemSummary> getItemSummary(String itemName) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		List<ItemSummary> itemsList = inventoryDao.getItemSummary(itemName);
		return itemsList;
	}

	public List<String> getItemList() {
		// TODO Auto-generated method stub
		return inventoryDao.getItemList();
	}

	@SuppressWarnings("resource")
	public int fileUpload(String filepath) {
		// TODO Auto-generated method stub
		// int count=inventoryDao.fileUploadDao(filepath);
		int count = 0;
		try {
			int size = 0;
			FileReader fileReader = new FileReader(filepath);
			CSVReader reader = new CSVReader(fileReader, ';', '\'');
			String[] header;
			List<String> attributes = new ArrayList<String>();

			if ((header = reader.readNext()) != null) {
				size = header.length;
				// attributes.add(header[1]);
				for (int i = 5; i < size; i++) {
					attributes.add(header[i]);
					System.out.println(header[i]);
				}
			}
			String[] nextLine;
			int flag = 0;
			String newCategoryName = null;
			while ((nextLine = reader.readNext()) != null) {
				/*
				 * for(int i=0;i<nextLine.length;i++){
				 * System.out.println(nextLine[i]); }
				 */
				size = nextLine.length;
				if (flag == 0) {
					newCategoryName = nextLine[2];
					boolean result = inventoryDao.isCategoryExists(newCategoryName);
					if (!result) {
						inventoryDao.createNewCategory(newCategoryName, attributes);
					}
					flag = 1;
				}

				List<String> inventoryList = new ArrayList<String>();
				List<String> categoryDetails = new ArrayList<String>();

				for (int i = 0; i < size; i++) {
					if (i <= 4)
						inventoryList.add(nextLine[i]);
					else
						categoryDetails.add(nextLine[i]);
				}

				inventoryList.add("in-stock");

				count = inventoryDao.insertFileContent(inventoryList, categoryDetails);

			}
		} catch (IOException e) {
			System.out.println("error while reading csv and put to db : " + e.getMessage());
		}
		return count;
	}

	public ItemStatus getItemStatusSummary(String itemCategoryName) {
		// TODO Auto-generated method stub
		ItemStatus itemStatus = null;
		itemStatus = inventoryDao.getItemStatus(itemCategoryName);
		return itemStatus;
	}

}
