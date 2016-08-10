package com.au.itinventory.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.au.itinventory.models.CategoryDetails;
import com.au.itinventory.models.Employee;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemStatus;
import com.au.itinventory.models.ItemStockCount;
import com.au.itinventory.models.ItemSummary;
import com.au.itinventory.services.InventoryService;
import com.au.itinventory.services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryController.
 */
@Controller
@RequestMapping("/")
public class InventoryController {

		/** The user service. */
		@Autowired
		UserService userService;
		
		/** The inventory service. */
		@Autowired
		InventoryService inventoryService;
		
		
		/**
		 * View details.
		 *
		 * @param empEmail the emp email
		 * @return the employee
		 */
		@RequestMapping(value = "/employeeDetails", method = RequestMethod.GET)
		@ResponseBody
		public Employee viewDetails(@RequestParam("empEmail") String empEmail){
			Employee emp= new Employee();
			emp.setEmpEmail(empEmail);
			System.out.println(empEmail);
			Employee dbemp=userService.getEmpDetails(emp);
			return dbemp;
			
			//return emp obj to UI to render it
		}
		
		
		/**
		 * Emp query.
		 *
		 * @param empID the emp ID
		 * @param subject the subject
		 * @param query the query
		 * @return the string
		 */
		@RequestMapping(value = "/query", method = RequestMethod.GET)
		  public String empQuery(@RequestParam("empID") int empID,@RequestParam("subject") String subject,@RequestParam("query") String query){
		   String result=inventoryService.sendMail(empID+"Query - "+subject, query, "text/plain");
		   return result;
		  }
		
		
		/**
		 * Gets the emp item details.
		 *
		 * @param itemName the item name
		 * @param empID the emp ID
		 * @return the emp item details
		 */
		@RequestMapping(value = "/empItems", method = RequestMethod.GET,produces="application/json")
		  @ResponseBody
		  public List<CategoryDetails> getEmpItemDetails(@RequestParam("categoryName") String itemName,@RequestParam("empID") String empID){
		   //need to add required request params9
		   List<CategoryDetails> categoryDetails=inventoryService.getEmpItemDetails(itemName,empID);
		   return categoryDetails;
		  }
		
		/**
		 * Login.
		 *
		 * @param emailID the email ID
		 * @return the model and view
		 */
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam("emailID") String emailID){
			
			ModelAndView model=null;
			System.out.println("logggedin");
			//User user= new User();
			//user.setEmailID(emailID);
			System.out.println(emailID);
			String userRole=userService.getUserDetails(emailID);
			System.out.println(userRole);
			if(userRole==null)
			{		
				model=new ModelAndView(new RedirectView("registration.html"));
				//System.out.println(userRole);
		}
		
			else if(userRole.equals("ITAdmin"))
			{
				model=new ModelAndView(new RedirectView("admin.html"));
				//System.out.println(userRole);
			}
			else if(userRole.equals("Employee"))
			{
				model=new ModelAndView(new RedirectView("employee.html"));
				//System.out.println(userRole);
			}
			return model;
		}
		
		/**
		 * Registration.
		 *
		 * @param emp the emp
		 * @return the model and view
		 */
		@RequestMapping(value = "/register", method = RequestMethod.POST, consumes= "application/json")
	 	public ModelAndView Registration(@RequestBody Employee emp) {
			System.out.println("Register New User");
			System.out.println(emp.getRoleID() +" "+emp.getEmpName());
			ModelAndView model = null;
			String result=userService.registerEmployee(emp);
			model=new ModelAndView(new RedirectView("employee.html"));
			return model;
			
		}
		
		
		/**
		 * Item category status.
		 *
		 * @param itemCategoryName the item category name
		 * @return the item status
		 */
		@RequestMapping(value = "/itemStatus", method = RequestMethod.GET, produces= "application/json")
		@ResponseBody
	 	public ItemStatus itemCategoryStatus(@RequestParam("itemCategoryName") String itemCategoryName) {
			System.out.println("Item Status");
		
			ItemStatus itemStatus=inventoryService.getItemStatusSummary(itemCategoryName);
			System.out.println("Allocated"+itemStatus.getAllocated());
			System.out.println("Defective"+itemStatus.getDefective());
			System.out.println("Instock"+itemStatus.getInstock());
			return itemStatus;
			
		}
		/**
		* View items list.
		*
		* @return the list
		*/
		@RequestMapping(value = "/viewItemList", method = RequestMethod.GET,produces="application/json")
		@ResponseBody
		public List<ItemStockCount> viewItemsList(){
			//need to add required request params9
			List<ItemStockCount> itemStockCountList=inventoryService.getItemsSummary1();
			return itemStockCountList;
		}
		
		
		
		/**
		 * View item.
		 *
		 * @param itemName the item name
		 * @return the model and view
		 */
		@RequestMapping(value = "/viewItem", method = RequestMethod.GET)
		public ModelAndView viewItem(@RequestParam("itemName") String itemName){
			ModelAndView model=null;
			
			return null;
		}
		
		
		/**
		 * Allocate item.
		 *
		 * @param empInventory the emp inventory
		 * @return the string
		 */
		//allocate and add item same
		@RequestMapping(value = "/allocateItem", method = RequestMethod.POST, produces="application/text")
		@ResponseBody
		public String allocateItem(@RequestBody EmployeeInventory empInventory){
			
			String result;
			int flag=inventoryService.allocateItem(empInventory);
			if(flag==1)
			{
				result="Allocation Success";
			}
			else if(flag==2)
			{
				result="Already Allocated";
			}
			else
			{
				result="Defective Item";
			}
			return result;
		}
		
		/**
		 * Reallocate item.
		 *
		 * @param empInventory the emp inventory
		 * @return the string
		 */
		@RequestMapping(value = "/deallocateItem", method = RequestMethod.POST)
		public String reallocateItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			int flag=inventoryService.deAllocateItem(empInventory);
			String result;
			if(flag==1)
			{
				result="Deallocation Success";
			}
			else
			{
				result="Deallocation Failed";
			}
			
			return result;
		
		}

		/**
		 * File upload.
		 *
		 * @param file the file
		 * @return the string
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
		@ResponseBody
		public String fileUpload(@RequestParam(value="file", required=false) MultipartFile file) throws IOException
		{
	
			int count=inventoryService.fileUpload(file);
			if(count>0)
				return "File Upload Success";
			else
				return "File Upload Failed";
		}
		
		/**
		 * Adds the item.
		 *
		 * @param item the item
		 * @return the model and view
		 */
		@RequestMapping(value = "/addItems", method = RequestMethod.POST,produces="application/json")
		public ModelAndView addItem(@RequestBody  Item item ){
			System.out.println("item type id in controller:"+item.getItemtypeID());
			int flag=inventoryService.addItem(item);
			if(flag==1)
			{
				System.out.println("Success");
			}
			else
			{
				System.out.println("Insert failed");
			}
			return null;
		}
		
		
		/**
		 * Removes the item.
		 *
		 * @param empInventory the emp inventory
		 * @return the model and view
		 */
		@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
		public ModelAndView removeItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			int flag=inventoryService.removeItem(empInventory);
			if(flag==1)
			{
				//success in dealloacting
				System.out.println("Success");
			}
			else
			{
				//error in deallocating
				System.out.println("Failed");
			}
			
			return model;
		}
		
		/**
		 * Adds the item type.
		 *
		 * @param newItem the new item
		 * @return the model and view
		 */
		@RequestMapping(value = "/addItemType", method = RequestMethod.POST)
		public ModelAndView addItemType(@RequestParam("newItem") String newItem){
			//all required request params
			int flag=inventoryService.createNewItemType(newItem);
			//create a table for that itemtype in db 
			if(flag==1)
			{
				System.out.println("Success");
			}
			else
			{
				System.out.println("Failed");
			}
			return null;
		}
		
		/**
		 * View summary.
		 *
		 * @param itemName the item name
		 * @return the list
		 */
		@RequestMapping(value = "/summary", method = RequestMethod.GET,produces="application/json")
		@ResponseBody
		public List<ItemSummary> viewSummary(@RequestParam("itemCategoryName") String itemName){
			//need to add required request params9
			List<ItemSummary> itemsList=inventoryService.getItemSummary(itemName);
			System.out.println("Summary");
			return itemsList;
		}
		
		/**
		 * View inventory.
		 *
		 * @return the list
		 */
		@RequestMapping(value = "/inventory", method = RequestMethod.GET,produces="application/json")
		  @ResponseBody
		  public List<Inventory> viewInventory(){
		   //need to add required request params9
		   List<Inventory> inventoryDetailList=inventoryService.getInventoryDetails();
		   return inventoryDetailList;
		  }
		
		@RequestMapping(value = "/emplist", method = RequestMethod.GET,produces="application/json")
		  @ResponseBody
		  public List<Employee> viewEmployees(){
		   //need to add required request params9
		   List<Employee> empployeeDetailList=inventoryService.getEmployeeDetails();
		   return empployeeDetailList;
		  }
		
		

}
