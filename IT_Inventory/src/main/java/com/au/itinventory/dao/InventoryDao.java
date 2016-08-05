package com.au.itinventory.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.au.itinventory.models.EmployeeInventory;
import com.au.itinventory.models.Inventory;
import com.au.itinventory.models.Item;
import com.au.itinventory.models.ItemSummary;

@Repository("inventoryDao")
public class InventoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer isAllotedToEmp(String itemID) {
		// TODO Auto-generated method stub
		String query="select empID from Inventory_Emp where itemID='"+itemID+"';";
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

	public int allocateItemToEmp(EmployeeInventory empInventory, String status) {
		// TODO Auto-generated method stub
		String itemID=empInventory.getItemID();
		int empID=empInventory.getEmpID();
		String itemName=empInventory.getItemCategory();
		int flag=0;
		String query;
		if(status.equals("returned"))
		{
			query="update Inventory_Emp set empID="+empID+"where itemID='"+itemID+"';";
			flag=jdbcTemplate.update(query);
			status="returned_reallocated";
		}
		else
		{
			query="insert into Inventory_Emp values('"+itemID+"',"+empID+");";
			flag=jdbcTemplate.update(query);
			status="allocated";
		}
		if(flag==1)
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			query="update Inventory set status='"+status+"' where itemID='"+itemID+"';";
			flag=jdbcTemplate.update(query);
				
			query="insert into Inventory_log values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"','"+dateFormat.format(date)+"');";
			flag=jdbcTemplate.update(query);
		}
		
		
		return flag;
	}

	public int deAllocateItem(EmployeeInventory empInventory) {
		// TODO Auto-generated method stub
		String itemID=empInventory.getItemID();
		int empID=empInventory.getEmpID();
		String itemName=empInventory.getItemCategory();
		String status=empInventory.getStatus();
		
		String query="update Inventory_Emp set empID="+1772+"where itemID='"+itemID+"';";
		int flag=jdbcTemplate.update(query);
		
		if(flag==1)
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			query="update Inventory set status='"+status+"' where itemID='"+itemID+"';";
			flag=jdbcTemplate.update(query);
			
			query="insert into Inventory_log values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"','"+dateFormat.format(date)+"');";
			flag=jdbcTemplate.update(query);
		}
		return flag;
	}

	public int addItem(Item item) {
		// TODO Auto-generated method stub
		String itemID=item.getItemID();
		String itemTypeID=item.getItemtypeID();
		String itemName=item.getItemName();
		String serviceTag=item.getServiceTag();
		String dateOfPurchase=item.getDateOfPurchase();
		String status="in-stock";
		
		System.out.println("item type id"+itemTypeID);
		
		String sql="select count(itemtypeID) from "+itemName+"_Category where itemtypeID='"+itemTypeID+"'";
		Integer count=jdbcTemplate.query(sql, new ResultSetExtractor<Integer>(){
			   public Integer extractData(ResultSet rs) throws SQLException
			   {
				   int count;
			    if(rs.next()==true){		    
			    	count=rs.getInt(1);
			    	return count;
			   }
			    else{
			    	return null;
			    }
			   }});
		
		if(count==0)
		{
			System.out.println("item type id:"+itemTypeID);
			String model=item.getModel();
			int warranty=item.getWarranty();
			String query=null;
			if(itemName.equalsIgnoreCase("Laptop"))
			{
				System.out.println("Item type id:"+itemTypeID);
				String ram=item.getRam();
				String processor=item.getProcessor();
				String os=item.getOs();
				query="insert into "+itemName+"_Category values('"+itemTypeID+"','"+model+"','"+ram+"','"+processor+"','"+os+"',"+warranty+");";
				jdbcTemplate.update(query);
			}
			else
			{
				query="insert into "+itemName+"_Category values('"+itemTypeID+"','"+model+"',"+warranty+");";
				jdbcTemplate.update(query);
			}
		}
		
		String query="insert into Inventory values('"+itemID+"','"+itemTypeID+"','"+itemName+"','"+serviceTag+"','"+dateOfPurchase+"','"+status+"');";
		int flag=jdbcTemplate.update(query);
		/*if(flag==1)
		{
			query="insert into Inventory_log values('"+itemID+"',"+empID+",'"+itemName+"','"+status+"');";
			flag=jdbcTemplate.update(query);
		}*/
		return flag;
	}

	public int createNewItemType(String newItem) {
		// TODO Auto-generated method stub
		String query="create table "+newItem+"_Category(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));";
		int flag=jdbcTemplate.update(query);
		return flag;
	}

	public List<ItemSummary> getItemSummary(String itemName) {
		// TODO Auto-generated method stub
		String query="select inventory.itemID,empInventory.empID,inventory.status,employee.empLocation,item.model,inventory.dateOfPurchase from Inventory inventory,Inventory_Emp empInventory,Employee employee,+"+itemName+"_Category item where empInventory.itemID=inventory.itemID and inventory.itemTypeID=item.itemtypeID and empInventory.empID=employee.empID;";
		return jdbcTemplate.query(query, new ResultSetExtractor<List<ItemSummary>>(){
			List<ItemSummary> list=new ArrayList<ItemSummary>();
			   public List<ItemSummary> extractData(ResultSet rs) throws SQLException
			   {
			    
			    ItemSummary item=null;
	            while (rs.next()) {   
	            	item =new ItemSummary();
	            	item.setItemID(rs.getString(1));
	            	item.setEmpID(rs.getInt(2));
	            	item.setStatus(rs.getString(3));
	            	item.setLocation(rs.getString(4));
	            	item.setModel(rs.getString(5));
	            	item.setYearOfPurchase(rs.getString(6));
	                list.add(item);
	            }

	            return list;
			   }});
	}

	

}
