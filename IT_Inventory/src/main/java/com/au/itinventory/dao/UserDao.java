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
		System.out.println(emp.getRoleID() +" "+emp.getEmpName());
		String SQL = "insert into Employee (empID,roleID,empName,empLocation,empEmail,phone) values (?, ?, ?, ?, ?, ?)";
		//jdbcTemplate.update(SQL1,emp.getEmpEmail(),"Employee");
		jdbcTemplate.update( SQL,emp.getEmpID(),emp.getRoleID(),emp.getEmpName(),emp.getEmpLocation(),emp.getEmpEmail(),emp.getPhone());
		System.out.println("Created Record Name = " + emp.getEmpName()+" "+emp.getEmpEmail());
		return "success";
	}

	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		String query="select * from Employee where empEmail='"+emp.getEmpEmail()+"';";
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

	public String getUserDetails(String emailID) {
		// TODO Auto-generated method stub
		//userEmail=userRole.getRoleID();
		String query="select role from UserRole,Employee where UserRole.roleID=Employee.roleID and Employee.empEmail='"+emailID+"';";
		  return jdbcTemplate.query(query, new ResultSetExtractor<String>(){
		   public String extractData(ResultSet rs) throws SQLException
		   {
		    if(rs.next()==true){		    
		    	return rs.getString(1);
		   }
		    else{
		    	return null;
		    }
		   }});
	}

}
