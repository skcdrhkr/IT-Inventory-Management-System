create database IT_Inventory
create table UserRole(roleID int NOT NULL, role nvarchar(20) NOT NULL,primary key(roleID));

create table Employee(empID int NOT NULL,roleID int NOT NULL,empName nvarchar(30) NOT NULL, empLocation nvarchar(20),empEmail nvarchar(60),phone nvarchar(20),primary key(empID));

ALTER TABLE Employee
ADD CONSTRAINT FK_UserRole_Employee FOREIGN KEY (roleID)     
    REFERENCES UserRole (roleID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE    
;   

create table Inventory(itemID nvarchar(20) NOT NULL,itemTypeID nvarchar(30) NOT NULL,itemName nvarchar(30) NOT NULL,serviceTag nvarchar(20) NOT NULL,dateOfPurchase nvarchar(20) NOT NULL,status nvarchar(30) NOT NULL, primary key(itemID));

create table Inventory_Emp(itemID nvarchar(20),empID int,primary key(itemID));

ALTER TABLE Inventory_Emp
ADD CONSTRAINT FK_InventoryEmp_Inventory FOREIGN KEY (itemID)     
    REFERENCES Inventory (itemID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE    
; 

ALTER TABLE Inventory_Emp
ADD CONSTRAINT FK_InventoryEmp_Employee FOREIGN KEY (empID)     
    REFERENCES Employee (empID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE    
; 

create table Inventory_log(logID int IDENTITY(1,1) NOT NULL,itemID nvarchar(20) NOT NULL, empID int NOT NULL,itemName nvarchar(30) NOT NULL,status nvarchar(30),logTime nvarchar(10) NOT NULL, primary key(logID));

create table Laptop_category(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,ram nvarchar(30),processor nvarchar(30),os nvarchar(30),warranty int NOT NULL,primary key(itemtypeID));

create table Mouse_category(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,primary key(itemtypeID));

create table Headphones_category(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,primary key(itemtypeID));

create table Router_category(itemtypeID nvarchar(30) NOT NULL, model nvarchar(30) NOT NULL,warranty int NOT NULL,primary key(itemtypeID));

create table Categories(categoryName nvarchar(50) NOT NULL, primary key(categoryName));


