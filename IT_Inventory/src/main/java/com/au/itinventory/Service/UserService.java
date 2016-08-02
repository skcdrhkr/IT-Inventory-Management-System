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
public class UserService {

	@Autowired
	EmployeeDao empDao;
	@Autowired
	UserDao userDao;
	
	ApplicationContext context;
	
	public UserService() {
		super();
	}

	
	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		
		empDao = (EmployeeDao) context.getBean("employeeDAO");
	    Employee dbEmp=empDao.getEmpDetails(emp);
		return dbEmp;
	    
		/*boolean flag=true;
		if(dbUser.getUsername().equals(pageUser.getUsername()))
		{
			if(dbUser.getPassword().equals(pageUser.getPassword())){
				System.out.println("Successful Login");
				userDao.setUserToActive(dbUser);
				dbUser.setStatus("active");
			}
			else{
				System.out.println("Invalid password");
				flag=false;
			}
		}
		else {
			
			System.out.println("Not a registered user");
			flag=false;
		}
		return flag;*/
	}

	public String getUserDetails(User user) {
		// TODO Auto-generated method stub
		String userRole;
		System.out.println("Inside Service");
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDAO");
	    User dbUser=userDao.getUserDetails(user);
	    userRole=dbUser.getRole();
	    return userRole;
	    	
	}

}
