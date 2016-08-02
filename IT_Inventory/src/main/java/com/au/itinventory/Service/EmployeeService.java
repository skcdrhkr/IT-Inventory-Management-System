package com.au.itinventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.ClassModel.Employee;
import com.au.itinventory.ClassModel.User;
import com.au.itinventory.Dao.EmployeeDao;
import com.au.itinventory.Dao.UserDao;

@Service
public class EmployeeService {

	
	@Autowired
	EmployeeDao empDao;
	@Autowired
	UserDao userDao;
	
	ApplicationContext context;

	public EmployeeService() {
		super();
	}
	
	public String registerEmployee(Employee emp) {
		// TODO Auto-generated method stub
		String userRole;
		System.out.println("Inside Employee Service");
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		empDao = (EmployeeDao) context.getBean("empDAO");
	    return empDao.addEmployee(emp);
	    	
	}

	
	
}
