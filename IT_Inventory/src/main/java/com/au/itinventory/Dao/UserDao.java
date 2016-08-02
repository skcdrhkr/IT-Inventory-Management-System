package com.au.itinventory.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.ClassModel.User;

@Repository("userDAO")
public class UserDao {

	String useremail;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getUserDetails(User user) {
		// TODO Auto-generated method stub
		useremail=user.getEmailID();
		System.out.println("Inside userDao");
		String query="use ITInventory select emailID,role from Users where emailID='"+user.getEmailID()+"';";
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
		    	//dbuser.setRole(rs.getString(2));
		    	return dbuser;
		    }
		   }});
		}

}
