package com.au.itinventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.au.itinventory.Dao.EmployeeDao;
import com.au.itinventory.Dao.UserDao;
import com.au.itinventory.Model.Employee;
import com.au.itinventory.Model.Inventory;
import com.au.itinventory.Model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	EmployeeDao empDao;
	

	
	
	public UserService() {
		super();
	}

	
	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		empDao = (EmployeeDao) context.getBean("employeeDAO");
	    Employee dbEmp=empDao.getEmpDetails(emp);
	    return dbEmp;
		
	}

	public String getUserDetails(User user) {
		// TODO Auto-generated method stub
		String userRole;
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDAO");
	    User dbUser=userDao.getUserDetails(user);
	    userRole=dbUser.getRole();
	    return userRole;
	    	
	}


	public int allocateItem(Inventory inventory) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDAO");
		String itemID=inventory.getItemID();
		Integer empID=userDao.isAllotedToEmp(itemID);
		int flag=0;
		if(empID==null)
		{
			//no item entry in inventory so can be alloted to emp
			
			//entry into log and inventory table
			
			inventory.setStatus("allocated");
			flag=userDao.allocateItemToEmp(inventory);
			return flag;
			
		}
		else if(empID==1772)
		{
			//check for status either returned or in stock
			//in stock and can be alloted to emp
			//entry to log and inventory table
			String status=userDao.getStatus(itemID);
			if(status!=null)
			{
				if(status.equals("in-stock"))
				{
					inventory.setStatus("allocated");
					flag=userDao.allocateItemToEmp(inventory);
				}
				else if(status.equals("returned"))
				{
					inventory.setStatus("returned-reallocated");
					flag=userDao.allocateItemToEmp(inventory);
				}
				else if(status.equals("defective"))
				{
					//cannot allocate defective piece
					flag=2;
				}
			}
			
		}
		else{
			//already alloted to some employee ; cannot insert
		}
		return flag;
	}

}
