create table Users(emailID nvarchar(60) NOT NULL, role nvarchar(20) NOT NULL,primary key(emailID));
drop table Users

create table Employee(empID int NOT NULL, empName nvarchar(30) NOT NULL, empLocation nvarchar(20),empEmail nvarchar(60),phone nvarchar(20),primary key(empID));


ALTER TABLE Employee
ADD CONSTRAINT FK_Users_Employee FOREIGN KEY (empEmail)     
    REFERENCES Users (emailID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE    
;    


create table Inventory(itemID nvarchar(20) NOT NULL, empID int NOT NULL, itemTypeID nvarchar(30) NOT NULL,itemName nvarchar(30) NOT NULL,serviceTag nvarchar(20) NOT NULL,status nvarchar(30) NOT NULL, primary key(itemID));

ALTER TABLE Inventory
ADD CONSTRAINT FK_Inventory_Employee FOREIGN KEY (empID)     
    REFERENCES Employee (empID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE    
;  

create table InventoryLog(logID int IDENTITY(1,1) NOT NULL,itemID nvarchar(20) NOT NULL, empID int NOT NULL,itemName nvarchar(30) NOT NULL,status nvarchar(30) NOT NULL, primary key(logID));


create table Laptop(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,ram nvarchar(30),processor nvarchar(30),os nvarchar(30),warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


create table Router(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


create table Mouse(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


create table Headphones(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


create table Bag(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


create table TV(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,dateofpurcharse nvarchar(40),primary key(itemtypeID));


