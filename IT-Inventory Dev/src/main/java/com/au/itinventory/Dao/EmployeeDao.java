package com.au.itinventory.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.Model.Employee;
import com.au.itinventory.Model.User;

@Repository
public class EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public Employee getEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		
		String query="select * from Employee where empID='"+emp.getEmpID()+"';";
		  return jdbcTemplate.query(query, new ResultSetExtractor<Employee>(){
		   public Employee extractData(ResultSet rs) throws SQLException
		   {
		    Employee dbEmp = new Employee();
		    if(rs.next()==true){
		    	
		    	dbEmp.setEmpID(rs.getInt(1));
		    	dbEmp.setEmpName(rs.getString(2));
		    	dbEmp.setEmpLocation(rs.getString(3));
		    	dbEmp.setEmpEmail(rs.getString(4));
		    	dbEmp.setPhone(rs.getString(5));
		    	
		    	return dbEmp;
		   }
		    else{
		    	System.out.println("NO user with that EMp ID");
		    	return null;
		    }
		   }});
		}
	
	public String addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside empDao");
		//insert into Employee values(1772,'GouthamiMogili','Banglore','gouthami.mogili@accoliteindia.com','7842433149');
		//String query="use ITInventory insert into Employee values("+emp.getEmpID()+",'"+emp.getEmpName()+"','"+emp.getEmpLocation()+"','"+emp.getEmpEmail()+"','"+emp.getPhone()+"');";
		//create table Employee(empID int NOT NULL, empName nvarchar(30) NOT NULL, empLocation nvarchar(20),empEmail nvarchar(60),phone nvarchar(20),primary key(empID));

		String SQL = "use ITInventory insert into Employee (empID,empName,empLocation,empEmail,phone) values (?, ?, ?, ?, ?)";
		String SQL1 = "use ITInventory insert into Users (emailID,role) values (?, ?)"; 
		jdbcTemplate.update(SQL1,emp.getEmpEmail(),"Employee");
	    jdbcTemplate.update( SQL,emp.getEmpID(),emp.getEmpName(),emp.getEmpLocation(),emp.getEmpEmail(),emp.getPhone());
	      System.out.println("Created Record Name = " + emp.getEmpName()+" "+emp.getEmpEmail());
	      return "success";
		
		
		}
}
	
