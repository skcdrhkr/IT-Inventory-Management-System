package com.au.itinventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.dao.UserDao;
import com.au.itinventory.models.Employee;
import com.au.itinventory.models.UserRole;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	ApplicationContext context;

	public UserService() {
		super();
	}
	
	public String registerEmployee(Employee emp) {
		// TODO Auto-generated method stub
		System.out.println("Inside Employee Service");
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
	    return userDao.addEmployee(emp);
	}

	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
	    Employee dbEmp=userDao.getEmpDetails(emp);
	    return dbEmp;
		
	}

	public String getUserDetails(UserRole userRole) {
		// TODO Auto-generated method stub
		String role;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDAO");
	    UserRole dbUser=userDao.getUserDetails(userRole);
	    role=dbUser.getRole();
	    return role;
	}
}
