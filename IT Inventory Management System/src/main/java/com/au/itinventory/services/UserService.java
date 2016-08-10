package com.au.itinventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.dao.UserDao;
import com.au.itinventory.models.Employee;
import com.au.itinventory.models.UserRole;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {
	
	/** The user dao. */
	@Autowired
	UserDao userDao;
	
	/** The context. */
	ApplicationContext context;

	/**
	 * Instantiates a new user service.
	 */
	public UserService() {
		super();
	}
	
	/**
	 * Register employee.
	 *
	 * @param emp the emp
	 * @return the string
	 */
	public String registerEmployee(Employee emp) {
		// TODO Auto-generated method stub
		System.out.println("Inside Employee Service");
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
	    return userDao.addEmployee(emp);
	}

	/**
	 * Gets the emp details.
	 *
	 * @param emp the emp
	 * @return the emp details
	 */
	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
	    Employee dbEmp=userDao.getEmpDetails(emp);
	    return dbEmp;
		
	}

	/**
	 * Gets the user details.
	 *
	 * @param emailID the email ID
	 * @return the user details
	 */
	public String getUserDetails(String emailID) {
		// TODO Auto-generated method stub
		String role;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
		role=userDao.getUserDetails(emailID);
	    return role;
	}
}
