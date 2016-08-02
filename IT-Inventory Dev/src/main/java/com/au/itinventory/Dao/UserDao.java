package com.au.itinventory.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.Model.Inventory;
import com.au.itinventory.Model.User;

@Repository("userDAO")
public class UserDao {

	String useremail;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getUserDetails(User user) {
		// TODO Auto-generated method stub
		useremail=user.getEmailID();
		String query="select emailID,role from Users where emailID='"+user.getEmailID()+"';";
		  return jdbcTemplate.query(query, new ResultSetExtractor<User>(){
		   public User extractData(ResultSet rs) throws SQLException
		   {
		    User dbuser = new User();
		    if(rs.next()==true){		    
		    	dbuser.setEmailID(rs.getString(1));
		    	dbuser.setRole(rs.getString(2));
		    	return dbuser;
		   }
		    else{
		    	dbuser.setEmailID(useremail);
		    	return dbuser;
		    }
		   }});
		}

	public Integer isAllotedToEmp(String itemID) {
		// TODO Auto-generated method stub
		String query="select empID from Inventory where itemID='"+itemID+"';";
		return jdbcTemplate.query(query, new ResultSetExtractor<Integer>(){
			   public Integer extractData(ResultSet rs) throws SQLException
			   {
				   int empID;
			    if(rs.next()==true){		    
			    	empID=rs.getInt(1);
			    	return empID;
			   }
			    else{
			    	return null;
			    }
			   }});
	}

	public int allocateItemToEmp(Inventory inventory) {
		// TODO Auto-generated method stub
		String itemID=inventory.getItemID();
		int empID=inventory.getEmpID();
		String itemTypeID=inventory.getItemTypeID();
		String itemName=inventory.getItemName();
		String serviceTag=inventory.getServiceTag();
		String status=inventory.getStatus();
		String query="insert into Inventory values('"+itemID+"',"+empID+",'"+itemTypeID+"','"+itemName+"','"+serviceTag+"','"+status+"');";
		int flag=jdbcTemplate.update(query);
		if(flag==1)
		{
			query="insert into InventoryLog values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"');";
			flag=jdbcTemplate.update(query);
		}
		return flag;
	}

	public String getStatus(String itemID) {
		// TODO Auto-generated method stub
		String query="select status from Inventory where itemID='"+itemID+"';";
		return jdbcTemplate.query(query, new ResultSetExtractor<String>(){
			   public String extractData(ResultSet rs) throws SQLException
			   {
				   String status;
			    if(rs.next()==true){		    
			    	status=rs.getString(1);
			    	return status;
			   }
			    else{
			    	return null;
			    }
			   }});
	}

}
