package com.au.itinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.au.itinventory.models.Employee;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemStatus;
import com.au.itinventory.models.ItemSummary;
import com.au.itinventory.models.UserRole;
import com.au.itinventory.services.InventoryService;
import com.au.itinventory.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class InventoryController {

		@Autowired
		UserService userService;
		
		@Autowired
		InventoryService inventoryService;
		
		
		@RequestMapping(value = "/details", method = RequestMethod.GET)
		public ModelAndView viewDetails(@RequestParam("empID") int empID){
			ModelAndView model = new ModelAndView("employee");
			Employee emp= new Employee();
			emp.setEmpID(empID);
			Employee dbemp=userService.getEmpDetails(emp);
			model.addObject("Employee", dbemp);
			return model;
			
			//return emp obj to UI to render it
		}
		
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
		
		@RequestMapping(value = "/register", method = RequestMethod.POST, consumes= "application/json")
	 	public ModelAndView Registration(@RequestBody Employee emp) {
			System.out.println("Register New User");
			System.out.println(emp.getRoleID() +" "+emp.getEmpName());
			ModelAndView model = null;
			String result=userService.registerEmployee(emp);
			model=new ModelAndView(new RedirectView("employee.html"));
			return model;
			
		}
		
		
		@RequestMapping(value = "/itemStatus", method = RequestMethod.GET, produces= "application/json")
		@ResponseBody
	 	public ItemStatus itemCategoryStatus() {
			System.out.println("Item Status");
			//System.out.println(emp.getRoleID() +" "+emp.getEmpName());
			//ModelAndView model = null;
			//String result=userService.registerEmployee(emp);
			//model=new ModelAndView(new RedirectView("employee.html"));
			//return model;
			
			ItemStatus itemStatus=new ItemStatus();
			itemStatus.setTotal(10);
			itemStatus.setInstock(5);
			itemStatus.setAllocated(4);
			itemStatus.setDefective(1);
			return itemStatus;
			
		}
		
		@RequestMapping(value = "/viewItem", method = RequestMethod.GET)
		public ModelAndView viewItem(@RequestParam("itemName") String itemName){
			ModelAndView model=null;
			
			return null;
		}
		
		
		/*{
		    "itemID":"T3",
		    "empID":1774,
		    "itemCategory":"Laptop",
		    "status":""
}*/
		
		//allocate and add item same
		@RequestMapping(value = "/allocateItem", method = RequestMethod.POST)
		public ModelAndView allocateItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			/*Inventory inventory=new Inventory();
			inventory.setItemID(itemID);
			//inventory.setEmpID(empID);
			inventory.setItemName(itemName);*/
			
			int flag=inventoryService.allocateItem(empInventory);
			//int flag=inventoryService.allocateItem(inventory);
//			if(flag==1)
//			{
//				model=new ModelAndView("insertSuccess");
//				/*model.addObject("ItemID", itemID);
//				model.addObject("EmpID", empID);*/
//			}
//			else if(flag==2)
//			{
//				model=new ModelAndView("already allocated");
//			}
//			else
//			{
//				model=new ModelAndView("defective");
//			}
			return model;
		}
		
		@RequestMapping(value = "/deallocateItem", method = RequestMethod.POST)
		public ModelAndView reallocateItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			int flag=inventoryService.deAllocateItem(empInventory);
			if(flag==1)
			{
				//success in dealloacting
			}
			else
			{
				//error in deallocating
			}
			
			return model;
		
		}

		
		
		/*{
		    "itemID":"T2",
		    "itemtypeID":"Latitude36",
		    "itemName":"Laptop",
		    "serviceTag":"LP000002",
		    "model":"Dell",
		    "warranty":4,
		    "dateOfPurchase":"05072016",
		    "ram":"16GB",
		    "processor":"i5",
		    "os":"windows"
		}	*/

		@RequestMapping(value = "/addItems", method = RequestMethod.POST,produces="application/json")
		public ModelAndView addItem(@RequestBody  Item item ){
			//all required request params
			//ModelAndView model=null;
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
		
		@RequestMapping(value = "/summary", method = RequestMethod.GET)
		public List<ItemSummary> viewSummary(@RequestParam("itemName") String itemName){
			//need to add required request params9
			List<ItemSummary> itemsList=inventoryService.getItemSummary(itemName);
			return itemsList;
		}
		

}
