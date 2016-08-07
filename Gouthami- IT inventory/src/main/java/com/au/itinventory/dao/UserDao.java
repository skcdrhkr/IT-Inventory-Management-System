package com.au.itinventory.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.models.Employee;
import com.au.itinventory.models.UserRole;

@Repository("userDao")
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String userEmail;

	public String addEmployee(Employee emp) {
		// TODO Auto-generated method stub
			
		System.out.println("Inside userDao");
			//insert into Employee values(1772,'GouthamiMogili','Banglore','gouthami.mogili@accoliteindia.com','7842433149');
			//String query="use ITInventory insert into Employee values("+emp.getEmpID()+",'"+emp.getEmpName()+"','"+emp.getEmpLocation()+"','"+emp.getEmpEmail()+"','"+emp.getPhone()+"');";
			//create table Employee(empID int NOT NULL, empName nvarchar(30) NOT NULL, empLocation nvarchar(20),empEmail nvarchar(60),phone nvarchar(20),primary key(empID));

		String SQL = "insert into Employee (empID,empName,empLocation,empEmail,phone) values (?, ?, ?, ?, ?)";
		String SQL1 = "insert into Users (emailID,role) values (?, ?)"; 
		jdbcTemplate.update(SQL1,emp.getEmpEmail(),"Employee");
		jdbcTemplate.update( SQL,emp.getEmpID(),emp.getEmpName(),emp.getEmpLocation(),emp.getEmpEmail(),emp.getPhone());
		System.out.println("Created Record Name = " + emp.getEmpName()+" "+emp.getEmpEmail());
		return "success";
	}

	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		String query="select * from Employee where empID='"+emp.getEmpID()+"';";
		  return jdbcTemplate.query(query, new ResultSetExtractor<Employee>(){
		   public Employee extractData(ResultSet rs) throws SQLException
		   {
		    Employee dbEmp = new Employee();
		    if(rs.next()==true){
		    	
		    	dbEmp.setEmpID(rs.getInt(1));
		    	dbEmp.setRoleID(rs.getInt(2));
		    	dbEmp.setEmpName(rs.getString(3));
		    	dbEmp.setEmpLocation(rs.getString(4));
		    	dbEmp.setEmpEmail(rs.getString(5));
		    	dbEmp.setPhone(rs.getString(6));
		    	
		    	return dbEmp;
		   }
		    else{
		    	System.out.println("NO user with that EMp ID");
		    	return null;
		    }
		   }});
	}

	public UserRole getUserDetails(UserRole userRole) {
		// TODO Auto-generated method stub
		/*userEmail=userRole.getRoleID();
		String query="select emailID,role from Users where emailID='"+userRole.getEmailID()+"';";
		  return jdbcTemplate.query(query, new ResultSetExtractor<UserRole>(){
		   public UserRole extractData(ResultSet rs) throws SQLException
		   {
		    UserRole user = new UserRole();
		    if(rs.next()==true){		    
		    	user.setEmailID(rs.getString(1));
		    	user.setRole(rs.getString(2));
		    	return user;
		   }
		    else{
		    	user.setEmailID(userEmail);
		    	return user;
		    }
		   }});
	}*/
		return null;
	}

}
