package com.au.itinventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.au.itinventory.Dao.EmployeeDao;
import com.au.itinventory.Model.Employee;
import com.au.itinventory.Model.User;
import com.au.itinventory.Service.UserService;



@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "viewDetails", method = RequestMethod.GET)
	public ModelAndView viewDetails(@RequestParam("emailID") String emailID,@RequestParam("empID") String password){
		ModelAndView model = new ModelAndView("employee");
		Employee emp= new Employee();
	//	emp.setEmpID(emailID);
		Employee dbemp=userService.getEmpDetails(emp);
		return model;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(){
		System.out.println("logggedin");
		ModelAndView model=new ModelAndView("index.jsp");
//		User user= new User();
//		user.setEmailID(emailID);
//		System.out.println(emailID);
//		String userRole=userService.getUserDetails(user);
//		if(userRole.equals("ITAdmin"))
//		{
//			model=new ModelAndView("admin");
//		}
//		else if(userRole.equals("Employee"))
//		{
//			model=new ModelAndView("employee");
//		}
//		else
//		{
//			model=new ModelAndView("register");
//		}
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
