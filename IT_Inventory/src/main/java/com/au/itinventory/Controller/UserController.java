package com.au.itinventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.au.itinventory.ClassModel.Employee;
import com.au.itinventory.ClassModel.User;
import com.au.itinventory.Service.EmployeeService;
import com.au.itinventory.Service.UserService;


@Controller
@RequestMapping(value="/")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(value = "viewDetails", method = RequestMethod.GET)
	public ModelAndView viewDetails(@RequestParam("emailID") String emailID,@RequestParam("empID") String password){
		ModelAndView model = new ModelAndView("employee");
		Employee emp= new Employee();
	//	emp.setEmpID(emailID);
		Employee dbemp=userService.getEmpDetails(emp);
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("emailID") String emailID){
		System.out.println("logggedin");
		ModelAndView model = null;
		User user= new User();
		user.setEmailID(emailID);
		System.out.println(emailID);
		String userRole=userService.getUserDetails(user);
		if(userRole==null)
		{		
			model=new ModelAndView("register");
			System.out.println(userRole);
	}
	
		else if(userRole.equals("ITAdmin"))
		{
			model=new ModelAndView("admin");
			System.out.println(userRole);
		}
		else if(userRole.equals("Employee"))
		{
			model=new ModelAndView("employee");
			System.out.println(userRole);
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
	public ModelAndView viewItem(@RequestParam("itemType") String itemName){
		
		return null;
	}
	
	@RequestMapping(value = "allocateItem", method = RequestMethod.POST)
	public ModelAndView allocateItem(@RequestParam("empID") String empID,@RequestParam("itemID") String itemID){
		
		return null;
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
