package com.au.itinventory.controller;

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

import com.au.itinventory.models.Employee;
import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemStatus;
import com.au.itinventory.models.ItemSummary;
import com.au.itinventory.models.UserRole;
import com.au.itinventory.services.InventoryService;
import com.au.itinventory.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class InventoryController {

		@Autowired
		UserService userService;
		
		@Autowired
		InventoryService inventoryService;
		
		
		@RequestMapping(value = "/employeeDetails", method = RequestMethod.GET)
		@ResponseBody
		public Employee viewDetails(@RequestParam("empEmail") String empEmail){
			Employee emp= new Employee();
			emp.setEmpEmail(empEmail);
			System.out.println(empEmail);
			Employee dbemp=userService.getEmpDetails(emp);
			//model.addObject("Employee", dbemp);
			return dbemp;
			
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
	 	public ItemStatus itemCategoryStatus(@RequestParam("itemCategoryName") String itemCategoryName) {
			System.out.println("Item Status");
		
			ItemStatus itemStatus=inventoryService.getItemStatusSummary(itemCategoryName);
			System.out.println("Allocated"+itemStatus.getAllocated());
			System.out.println("Defective"+itemStatus.getDefective());
			System.out.println("Instock"+itemStatus.getInstock());
			return itemStatus;
			
		}
		
				
		@RequestMapping(value = "/viewItemList", method = RequestMethod.GET)
		@ResponseBody
		public List<String> viewItemList(){
			List<String> list=inventoryService.getItemList();
//			list.add("Laptop");
//			list.add("Mouse");
			return list;
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
		@RequestMapping(value = "/allocateItem", method = RequestMethod.POST, produces="application/text")
		@ResponseBody
		public String allocateItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			/*Inventory inventory=new Inventory();
			inventory.setItemID(itemID);
			//inventory.setEmpID(empID);
			inventory.setItemName(itemName);*/
			String result;
			int flag=inventoryService.allocateItem(empInventory);
			//int flag=inventoryService.allocateItem(inventory);
			if(flag==1)
			{
				result="Allocation Success";
				//model=new ModelAndView("insertSuccess");
				/*model.addObject("ItemID", itemID);
				model.addObject("EmpID", empID);*/
			}
			else if(flag==2)
			{
				result="Already Allocated";
				//model=new ModelAndView("already allocated");
			}
			else
			{
				result="Defective Item";
				//model=new ModelAndView("defective");
			}
			return result;
		}
		
		@RequestMapping(value = "/deallocateItem", method = RequestMethod.POST)
		public String reallocateItem(@RequestBody EmployeeInventory empInventory){
			
			ModelAndView model=null;
			int flag=inventoryService.deAllocateItem(empInventory);
			String result;
			if(flag==1)
			{
				//success in dealloacting
				result="Deallocation Success";
			}
			else
			{
				//error in deallocating
				result="Deallocation Failed";
			}
			
			return result;
		
		}

		@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
		public void fileUpload(@RequestParam(value="file", required=false) MultipartFile file,
                @RequestParam(value="data") Object data)
		{
			//ModelAndView model=null;
			System.out.println(file);
			System.out.println(data);
			//int flag=inventoryService.fileUpload(filepath);
			//if(flag==1)
				System.out.println("Success");
			//return null;
			
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
		
		@RequestMapping(value = "/summary", method = RequestMethod.GET,produces="application/json")
		@ResponseBody
		public List<ItemSummary> viewSummary(@RequestParam("itemCategoryName") String itemName){
			//need to add required request params9
			List<ItemSummary> itemsList=inventoryService.getItemSummary(itemName);
			System.out.println("Summary");
			return itemsList;
		}
		

}
