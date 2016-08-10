package com.au.itinventory.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.models.Employee;
import com.au.itinventory.models.UserRole;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDao.
 */
@Repository("userDao")
public class UserDao {
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The user email. */
	String userEmail;

	/**
	 * Adds the employee.
	 *
	 * @param emp the emp
	 * @return the string
	 */
	public String addEmployee(Employee emp) {
		System.out.println(emp.getRoleID() +" "+emp.getEmpName());
		String SQL = "insert into Employee (empID,roleID,empName,empLocation,empEmail,phone) values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update( SQL,emp.getEmpID(),emp.getRoleID(),emp.getEmpName(),emp.getEmpLocation(),emp.getEmpEmail(),emp.getPhone());
		System.out.println("Created Record Name = " + emp.getEmpName()+" "+emp.getEmpEmail());
		return "success";
	}

	/**
	 * Gets the emp details.
	 *
	 * @param emp the emp
	 * @return the emp details
	 */
	public Employee getEmpDetails(Employee emp) {
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

	/**
	 * Gets the user details.
	 *
	 * @param emailID the email ID
	 * @return the user details
	 */
	public String getUserDetails(String emailID) {
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
