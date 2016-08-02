package com.au.itinventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.au.itinventory.Model.Employee;
import com.au.itinventory.Model.Inventory;
import com.au.itinventory.Model.User;
import com.au.itinventory.Service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "viewDetails", method = RequestMethod.GET)
	public ModelAndView viewDetails(@RequestParam("empID") int empID){
		ModelAndView model = new ModelAndView("employee");
		Employee emp= new Employee();
		emp.setEmpID(empID);
		Employee dbemp=userService.getEmpDetails(emp);
		model.addObject("Employee", dbemp);
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("emailID") String emailID){
		/*System.out.println("logggedin");
		ModelAndView model=new ModelAndView("index");*/
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
		
		return null;
	}
	
	@RequestMapping(value = "allocateItem", method = RequestMethod.POST)
	public ModelAndView allocateItem(@RequestParam("itemID") String itemID,@RequestParam("empID") int empID,@RequestParam("itemTypeID") String itemTypeID,@RequestParam("itemName") String itemName,@RequestParam("serviceTag") String serviceTag,@RequestParam("status") String status){
		
		ModelAndView model=null;
		Inventory inventory=new Inventory();
		inventory.setItemID(itemID);
		inventory.setEmpID(empID);
		inventory.setItemTypeID(itemTypeID);
		inventory.setItemName(itemName);
		inventory.setServiceTag(serviceTag);
		inventory.setStatus(status);
		
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
	
	@RequestMapping(value = "addItem", method = RequestMethod.POST)
	public ModelAndView addItem(@RequestParam("") String x,@RequestParam("") String y){
		//all required request params
		return null;
	}
	
	@RequestMapping(value = "removeItem", method = RequestMethod.POST)
	public ModelAndView removeItem(@RequestParam("itemID") String itemID){
		
		return null;
	}
	
	@RequestMapping(value = "addItemType", method = RequestMethod.POST)
	public ModelAndView addItemType(@RequestParam("") String x){
		//all required request params
		return null;
	}
	
	@RequestMapping(value = "summary", method = RequestMethod.GET)
	public ModelAndView viewSummary(@RequestParam("") String x){
		//need to add required request params
		return null;
	}
	
}
