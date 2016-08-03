package com.au.itinventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.au.itinventory.Model.Employee;
import com.au.itinventory.Model.Inventory;
import com.au.itinventory.Model.Item;
import com.au.itinventory.Model.User;
import com.au.itinventory.Service.EmployeeService;
import com.au.itinventory.Service.UserService;

import jdk.nashorn.internal.ir.annotations.Ignore;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmployeeService empService;
	
	
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
		User user= new User();
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
		}
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
		String result=empService.registerEmployee(emp);
		if(result.equals("success"))
		{		
			model=new ModelAndView("employees");
			System.out.println(result);
		}
		return model;
	}
	
	@RequestMapping(value = "viewItem", method = RequestMethod.GET)
	public ModelAndView viewItem(@RequestParam("itemName") String itemName){
		ModelAndView model=null;
		
		return null;
	}
	
	//allocate and add item same
	@RequestMapping(value = "allocateItem", method = RequestMethod.POST)
	public ModelAndView allocateItem(@RequestParam("itemID") String itemID,@RequestParam("empID") int empID,@RequestParam("itemName") String itemName){
		
		ModelAndView model=null;
		Inventory inventory=new Inventory();
		inventory.setItemID(itemID);
		inventory.setEmpID(empID);
		inventory.setItemName(itemName);
		
		int flag=userService.allocateItem(inventory);
		if(flag==1)
		{
			model=new ModelAndView("insertSuccess");
			model.addObject("ItemID", itemID);
			model.addObject("EmpID", empID);
		}
		else if(flag==2)
		{
			model=new ModelAndView("insertFail");
		}
		else
		{
			model=new ModelAndView("failed");
		}
		return model;
	}
	
	@RequestMapping(value = "deallocateItem", method = RequestMethod.POST)
	public ModelAndView reallocateItem(@RequestParam("itemID") String itemID,@RequestParam("empID") int empID,@RequestParam("itemName") String itemName,@RequestParam("reason") String reason){
		
		ModelAndView model=null;
		Inventory inventory=new Inventory();
		inventory.setItemID(itemID);
		inventory.setEmpID(empID);
		inventory.setItemName(itemName);
		inventory.setStatus(reason);
		int flag=userService.deAllocateItem(inventory);
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
	    itemID:'T3',
	    itemTypeID:'Latitude36',
	    itemName:'Laptop',
	    serviceTag:'LP000003',
	    model:'Dell',
	    warranty:4,
	    dateOfPurchase:'04072016',
	    ram:'16GB',
	    processor:'i5',
	    os:'windows'
	}
*/

	@RequestMapping(value = "/addItems", method = RequestMethod.POST,produces="application/json")
	public ModelAndView addItem(@RequestBody  Item item ){
		//all required request params
		ModelAndView model=null;
		int flag=userService.addItem(item);
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
	
	
	@RequestMapping(value = "removeItem", method = RequestMethod.POST)
	public ModelAndView removeItem(@RequestParam("itemID") String itemID,@RequestParam("empID") int empID,@RequestParam("itemName") String itemName){
		
		//item ID entry itself has to be removed
		ModelAndView model=null;
		Inventory inventory=new Inventory();
		inventory.setItemID(itemID);
		inventory.setEmpID(empID);
		inventory.setItemName(itemName);
		int flag=userService.removeItem(inventory);
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
	
	@RequestMapping(value = "addItemType", method = RequestMethod.POST)
	public ModelAndView addItemType(@RequestParam("newItem") String newItem){
		//all required request params
		int flag=userService.createNewItemType(newItem);
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
	
	@RequestMapping(value = "summary", method = RequestMethod.GET)
	public ModelAndView viewSummary(@RequestParam("itemName") String itemName){
		//need to add required request params9
		
		return null;
	}
	
}
