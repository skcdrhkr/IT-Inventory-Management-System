package com.au.itinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.au.itinventory.models.Employee;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemSummary;
import com.au.itinventory.models.UserRole;
import com.au.itinventory.services.InventoryService;
import com.au.itinventory.services.UserService;

import java.util.List;

@Controller
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
			/*UserRole user= new UserRole();
			user.setEmailID(emailID);
			System.out.println(emailID);
			String userRole=userService.getUserDetails(user);
			if(userRole.equals("ITAdmin"))
			{
				model=new ModelAndView("admin");
			}
			else if(userRole.equals("Employee"))
			{
				model=new ModelAndView("employee");
			}
			else
			{
				model=new ModelAndView("register");
			}*/
			return model;
		}
		
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView Register(@RequestParam("EmployeeID") int employeeID,
				@RequestParam("EmployeeName") String employeeName,
				@RequestParam("EmployeeLocation") String employeeLocation,
				@RequestParam("MobileNumber") String mobileNumber,
				@RequestParam("EmployeeEmail") String employeeEmail){
			System.out.println("Register New User");
			ModelAndView model = null;
			Employee emp=new Employee();
			emp.setEmpName(employeeName);
			emp.setEmpID(employeeID);
			emp.setEmpLocation(employeeLocation);
			emp.setPhone(mobileNumber);
			emp.setEmpEmail(employeeEmail);
			System.out.println(employeeID+" "+employeeName);
			String result=userService.registerEmployee(emp);
			if(result.equals("success"))
			{		
				model=new ModelAndView("employees");
				System.out.println(result);
			}
			return model;
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
			if(flag==1)
			{
				model=new ModelAndView("insertSuccess");
				/*model.addObject("ItemID", itemID);
				model.addObject("EmpID", empID);*/
			}
			else if(flag==2)
			{
				model=new ModelAndView("already allocated");
			}
			else
			{
				model=new ModelAndView("defective");
			}
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
