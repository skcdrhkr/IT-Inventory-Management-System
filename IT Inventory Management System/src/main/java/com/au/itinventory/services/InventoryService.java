package com.au.itinventory.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.au.itinventory.dao.InventoryDao;
import com.au.itinventory.models.CategoryDetails;
import com.au.itinventory.models.Employee;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.InventoryLog;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemStatus;
import com.au.itinventory.models.ItemStockCount;
import com.au.itinventory.models.ItemSummary;

import au.com.bytecode.opencsv.CSVReader;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryService.
 */
@Service
public class InventoryService {

	/** The inventory dao. */
	@Autowired
	InventoryDao inventoryDao;

	/** The context. */
	ApplicationContext context;

	/**
	 * Gets the emp item details.
	 *
	 * @param itemName the item name
	 * @param empID the emp ID
	 * @return the emp item details
	 */
	public List<CategoryDetails> getEmpItemDetails(String itemName, String empID) {
		  // TODO Auto-generated method stub
		  List<CategoryDetails> categoryDetails=inventoryDao.getEmpItemDetails(itemName,empID);
		  return categoryDetails;
		 }
	
	/**
	 * Allocate item.
	 *
	 * @param empInventory the emp inventory
	 * @return the int
	 */
	public int allocateItem(EmployeeInventory empInventory) {
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

	/**
	 * De allocate item.
	 *
	 * @param empInventory the emp inventory
	 * @return the int
	 */
	public int deAllocateItem(EmployeeInventory empInventory) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.deAllocateItem(empInventory);

		return flag;
	}

	/**
	 * Adds the item.
	 *
	 * @param item the item
	 * @return the int
	 */
	public int addItem(Item item) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.addItem(item);
		return flag;
	}

	/**
	 * Removes the item.
	 *
	 * @param empInventory the emp inventory
	 * @return the int
	 */
	public int removeItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		empInventory.setStatus("removed");
		int flag = inventoryDao.deAllocateItem(empInventory);

		return flag;
	}

	/**
	 * Creates the new item type.
	 *
	 * @param newItem the new item
	 * @return the int
	 */
	public int createNewItemType(String newItem) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		int flag = inventoryDao.createNewItemType(newItem);
		return flag;
	}

	/**
	 * Gets the item summary.
	 *
	 * @param itemName the item name
	 * @return the item summary
	 */
	public List<ItemSummary> getItemSummary(String itemName) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		inventoryDao = (InventoryDao) context.getBean("inventoryDao");
		List<ItemSummary> itemsList = inventoryDao.getItemSummary(itemName);
		return itemsList;
	}
	
	
	/**
	 * Gets the items summary 1.
	 *
	 * @return the items summary 1
	 */
	public List<ItemStockCount> getItemsSummary1() {
		// TODO Auto-generated method stub
		List<ItemStockCount> itemStockCountList=new ArrayList<ItemStockCount>();
		List<String> categories=inventoryDao.getItemList();
		ItemStockCount itemStock=null;
		for(String itemName:categories)
		{
			itemStock=new ItemStockCount();
			int instockCount=inventoryDao.getItemsSummary1(itemName);
			itemStock.setItemCategory(itemName);
			itemStock.setInStockCount(instockCount);
			itemStockCountList.add(itemStock);
			if(instockCount<=2)
			   {
			    String subject="Stock almost empty";
			    String content="\tPlease find the details below --- \n\t Category : "+itemName+"\t In-Stock count : "+instockCount+""+"\n\nRegards,\nIT Inventory\nAccolite.";
			    String contentType="text/plain";
			    sendMail(subject, content, contentType);
			   }
		}
		return itemStockCountList;
	}
	
	
	/**
	 * Gets the item list.
	 *
	 * @return the item list
	 */
	public List<String> getItemList() {
		return inventoryDao.getItemList();
	}

	/**
	 * File upload.
	 *
	 * @param file the file
	 * @return the int
	 */
	@SuppressWarnings("resource")
	public int fileUpload(MultipartFile file) {
		int count = 0;
		try {
			int size = 0;
			InputStream inputStream = file.getInputStream();
			Reader reader = (Reader) new InputStreamReader(inputStream);
			CSVReader csvreader = new CSVReader(reader, ';', '\'');
			String[] header;
			List<String> attributes = new ArrayList<String>();

			if ((header = csvreader.readNext()) != null) {
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
			while ((nextLine = csvreader.readNext()) != null) {
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

	/**
	 * Gets the item status summary.
	 *
	 * @param itemCategoryName the item category name
	 * @return the item status summary
	 */
	public ItemStatus getItemStatusSummary(String itemCategoryName) {
		// TODO Auto-generated method stub
		ItemStatus itemStatus = null;
		itemStatus = inventoryDao.getItemStatus(itemCategoryName);
		return itemStatus;
	}
	
	/**
	 * Gets the inventory details.
	 *
	 * @return the inventory details
	 */
	public List<Inventory> getInventoryDetails() {
		  // TODO Auto-generated method stub
		  List<Inventory> inventoryDetails=inventoryDao.getInventoryDetails();
		  return inventoryDetails;
		 }

	/**
	 * Check warranty of item.
	 */
	@Scheduled(fixedRate = 86400000)
    public void checkWarrantyOfItem() {
        System.out.println("Expiry check ");
        checkWarranty();
    }
	
	/**
	 * Check warranty.
	 *
	 * @return the list
	 */
	public List<List<InventoryLog>> checkWarranty() {
		  // TODO Auto-generated method stub
		  List<String> categories=inventoryDao.getItemList();
		  List<List<InventoryLog>> totalExpiredItemsList=new ArrayList<List<InventoryLog>>();
		  List<InventoryLog> expiredItemsList=null;
		  String row="\n\tItemID \t Category \tStatus\n\n";
		  for(String category:categories)
		  {
		      expiredItemsList=inventoryDao.getExpiredItemList(category);
		      for(InventoryLog log:expiredItemsList)
		      {
		       row+="\t "+log.getItemID()+" \t    "+log.getItemName()+" \t  "+log.getStatus()+"\n";
		      }
		     
		      if(expiredItemsList.size()!=0)
		       totalExpiredItemsList.add(expiredItemsList);
		  }
		  
		  if(totalExpiredItemsList.size()!=0)
		  {
		   String subject="Items Expired today!";
		   String content="\tList of items expired as of today --- \n"+row;
		   String contentType="text/plain";
		   sendMail(subject,content+"\n\nRegards,\nIT Inventory\nAccolite.",contentType);
		  }
		  
		  return totalExpiredItemsList;
		 }
	
	/**
	 * Send mail.
	 *
	 * @param subject the subject
	 * @param content the content
	 * @param contentType the content type
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	 public String sendMail(String subject,String content,String contentType)
	 {
	   @SuppressWarnings("rawtypes")
	   Hashtable env=new Hashtable();
	   env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	   
	   try {  
	    Session session = null;
	   
	    try {
	     Context initCtx = new InitialContext(env);
	       Context envCtx = (Context) initCtx.lookup("java:comp/env");
	       session = (Session) envCtx.lookup("mail/Session");
	       Message message = new MimeMessage(session);
	       message.setFrom(new InternetAddress("gouthami.mogili@gmail.com"));
	       InternetAddress to[] = new InternetAddress[1];
	       to[0] = new InternetAddress("gouthami.mogili@gmail.com");
	       message.setRecipients(Message.RecipientType.TO, to);
	       
	       message.setSubject(subject);  
	       message.setContent("Hi,\n"+content+"\n\nRegards,\nIT Inventory\nAccolite.", contentType);
	    Transport.send(message);  
	    String result="Mail sent successfully!";
	     return result;
     
	    } catch (MessagingException e) {  
	       throw new RuntimeException(e);  
	    }  
	  }
	  catch(Exception e)
	  {
	   e.printStackTrace();
	  }
	   return null;
	 }

	public List<Employee> getEmployeeDetails() {
		// TODO Auto-generated method stub
		 List<Employee> inventoryDetails=inventoryDao.getEmployeeDetails();
		  return inventoryDetails;
	}
	
}
