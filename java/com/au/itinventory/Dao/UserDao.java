package com.au.itinventory.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.Model.Inventory;
import com.au.itinventory.Model.Item;
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
		String itemName=inventory.getItemName();
		String status=inventory.getStatus();
		/*String sql="select count(itemtypeID) from "+itemName+" where itemtypeID='"+itemTypeID+"'";
		Integer count=jdbcTemplate.query(sql, new ResultSetExtractor<Integer>(){
			   public Integer extractData(ResultSet rs) throws SQLException
			   {
				   int status;
			    if(rs.next()==true){		    
			    	status=rs.getInt(1);
			    	return status;
			   }
			    else{
			    	return null;
			    }
			   }});
		if(count==0)
		{
			String model=inventory.getModel();
			int warranty=inventory.getWarranty();
			String dateOfPurchase=inventory.getDateOfPurchase();
			String query=null;
			if(itemName.equalsIgnoreCase("laptop"))
			{
				String ram=inventory.getRam();
				String processor=inventory.getProcessor();
				String os=inventory.getOs();
				query="insert into "+itemName+" values('"+itemTypeID+"','"+model+"','"+ram+"','"+processor+"','"+os+"',"+warranty+",'"+dateOfPurchase+"');";
				jdbcTemplate.update(query);
			}
			else
			{
				query="insert into "+itemName+" values('"+itemTypeID+"','"+model+"',"+warranty+",'"+dateOfPurchase+"');";
				jdbcTemplate.update(query);
			}
		}
		*/
		String query="update Inventory set status='"+status+"',empID="+empID+" where itemID='"+itemID+"';";
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

	public int deAllocateItem(Inventory inventory) {
		// TODO Auto-generated method stub
		String itemID=inventory.getItemID();
		int empID=inventory.getEmpID();
		String itemName=inventory.getItemName();
		String status=inventory.getStatus();
		String query="update Inventory set status='"+status+"',empID="+1772+"where itemID='"+itemID+"'and empID='"+empID+"';";
		int flag=jdbcTemplate.update(query);
		if(flag==1)
		{
			query="insert into InventoryLog values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"');";
			flag=jdbcTemplate.update(query);
		}
		return flag;
	}

	public int addItem(Item item) {
		// TODO Auto-generated method stub
		String itemID=item.getItemID();
		int empID=1772;
		String itemTypeID=item.getItemtypeID();
		String itemName=item.getItemName();
		String serviceTag=item.getServiceTag();
		String status="in-stock";
		
		String sql="select count(itemtypeID) from "+itemName+" where itemtypeID='"+itemTypeID+"'";
		Integer count=jdbcTemplate.query(sql, new ResultSetExtractor<Integer>(){
			   public Integer extractData(ResultSet rs) throws SQLException
			   {
				   int status;
			    if(rs.next()==true){		    
			    	status=rs.getInt(1);
			    	return status;
			   }
			    else{
			    	return null;
			    }
			   }});
		
		if(count==0)
		{
			String model=item.getModel();
			int warranty=item.getWarranty();
			String dateOfPurchase=item.getDateOfPurchase();
			String query=null;
			if(itemName.equalsIgnoreCase("laptop"))
			{
				String ram=item.getRam();
				String processor=item.getProcessor();
				String os=item.getOs();
				query="insert into "+itemName+" values('"+itemTypeID+"','"+model+"','"+ram+"','"+processor+"','"+os+"',"+warranty+",'"+dateOfPurchase+"');";
				jdbcTemplate.update(query);
			}
			else
			{
				query="insert into "+itemName+" values('"+itemTypeID+"','"+model+"',"+warranty+",'"+dateOfPurchase+"');";
				jdbcTemplate.update(query);
			}
		}
		
		String query="insert into Inventory values('"+itemID+"',"+empID+",'"+itemTypeID+"','"+itemName+"','"+serviceTag+"','"+status+"');";
		int flag=jdbcTemplate.update(query);
		if(flag==1)
		{
			query="insert into InventoryLog values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"');";
			flag=jdbcTemplate.update(query);
		}
		return flag;
	}

	public int createNewItemType(String newItem) {
		// TODO Auto-generated method stub
		String query="create table "+newItem+"(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));";
		int flag=jdbcTemplate.update(query);
		return flag;
	}

	public void getItemDetails(String itemName) {
		// TODO Auto-generated method stub
		
		//get table details
	}

	/*public int removeItem(Inventory inventory) {
		// TODO Auto-generated method stub
		String itemID=inventory.getItemID();
		int empID=inventory.getEmpID();
		String itemName=inventory.getItemName();
		String status=inventory.getStatus();
		String query="update Inventory set status='"+status+"' and empID="+1772+"where itemID='"+itemID+"' and empID='"+empID+"';";
		int flag=jdbcTemplate.update(query);
		if(flag==1)
		{
			query="insert into InventoryLog values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"');";
			flag=jdbcTemplate.update(query);
		}
		return flag;
	}*/

}
