angular.module('AdminModule', ['ngFileUpload'])

.controller('AdminController', function($scope,$http) {

			$scope.sortType = 'Model';
			/*$scope.sortType2 = 'Model';
			$scope.sortType3 = 'DateofPurchase';*/
			
			
			
            $scope.laptopView = false;
			$scope.homeDisplay = true;
			$scope.AllocateForm = false;
			$scope.AddNewItem = false;
			$scope.addSummary = false;
			$scope.inventory=false;
			$scope.empdetails=false;
			
			$scope.showLaptop = function () {
			$scope.laptopView = true;
			$scope.homeDisplay = false;
			$scope.AllocateForm = false;
			$scope.AddNewItem = false;
			$scope.addSummary = false;
			$scope.inventory=false;
			$scope.empdetails=false;
		}

			$scope.showHomePage = function() {
				$scope.laptopView = false;
				$scope.homeDisplay = true;
				$scope.AllocateForm = false;
				$scope.AddNewItem = false;
				$scope.addSummary = false;
				$scope.inventory=false;
			}

			$scope.allocateForm = function() {
				$scope.AllocateForm = true;
				$scope.laptopView = false;
				$scope.homeDisplay = false;
				$scope.AddNewItem = false;
				$scope.header=true;
				$scope.Deallocateheader=false;
				$scope.AllocateStatus = true;
				$scope.DeAllocateStatus = false;
				$scope.addSummary = false;
				$scope.inventory=false;
				$scope.empdetails=false;
			}

			$scope.DeallocateForm = function() {
				$scope.AllocateForm = true;
				$scope.laptopView = false;
				$scope.homeDisplay = false;
				$scope.AddNewItem = false;
				$scope.header=false;
				$scope.Deallocateheader=true;
				$scope.AllocateStatus = false;
				$scope.DeAllocateStatus = true;
				$scope.addSummary = false;
				$scope.inventory=false;
				$scope.empdetails=false;
			}

           $scope.AddNewItem1 =function() {
				$scope.laptopView = false;
				$scope.homeDisplay = false;
				$scope.AllocateForm = false;
				$scope.AddNewItem = true;
				$scope.addSummary = false;
				$scope.inventory=false;
				$scope.empdetails=false;
			}

			$scope.Summary =function() {
				$scope.laptopView = false;
				$scope.homeDisplay = false;
				$scope.AllocateForm = false;
				$scope.AddNewItem = false;
				$scope.addSummary=true;
				$scope.inventory=false;
				$scope.empdetails=false;
			}

			$scope.inventorySummary = function(){
			    $scope.laptopView = false;
			    $scope.homeDisplay = false;
			    $scope.AllocateForm = false;
			    $scope.AddNewItem = false;
			    $scope.addSummary=false;
			    $scope.inventory=true;
			    $scope.empdetails=false;
			   }
		  		

			$scope.EmployeeDetails = function(){
			    $scope.laptopView = false;
			    $scope.homeDisplay = false;
			    $scope.AllocateForm = false;
			    $scope.AddNewItem = false;
			    $scope.addSummary=false;
			    $scope.inventory=false;
			    $scope.empdetails=true;
			   }
	
      		$scope.item ={
      			itemID : '',
      			itemtypeID :'',
      			itemName :'',
      			serviceTag :'',
      			model :'',
      			warranty:'',
      			dateOfPurchase : '',
      			ram : '',
      			processor :'',
      			os:''
			}
      		
      		
      		$scope.employee ={
    				empID : '',
    				roleID:'',
    				empName : '',
    				empEmail : localStorage.getItem("emailID"),
    				empLocation : '',
    				phone : ''
    			};

      		 $scope.signOut = function(){
      	          var auth2 = gapi.auth2.getAuthInstance();
      	          auth2.signOut().then(function () {
      	            console.log('User signed out.');
      	          window.location.href = "/IT_Inventory";
      	          });
      	        }
      		 
      		

			$scope.NewItem = function(){
				var dataObj = $scope.item;		

				$http.post('http://localhost:8080/IT_Inventory/addItems', dataObj).then(function successCallback(response) {
		            //temp = response.data;
		            alert("Success");
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
			};

      
/*			$scope.employeeDetails = function () {
		  		  console.log("page load List Controller")
			var empEmail=localStorage.getItem("emailID")
			  $http({
	     				 method : 'GET',
	       			 url : 'http://localhost:8080/IT_Inventory/employeeDetails?empEmail='+empEmail}).then(function successCallback(response) {
	  				 	$scope.employee = response.data;
	  				 	console.log($scope.employee);
	  				 }, function errorCallback(response) {
	      			 console.log(response.statusText);
	  			});

			}
*/

		$scope.itemStatus = {};
    	$scope.items=[
    	              {
    	            	  
    	            	 itemCategory: '',
    	            	 inStockCount: ''
    	              }
    	              ];
    	$scope.state  = ['green','red'];
    	
    	$scope.checkStatus = function(ind){
    		if($scope.items[ind].inStockCount<1)
    			return false;
    		else
    			return true;
    	}
    	
    	$scope.fn_load = function () {
  		  console.log("page load List Controller")
  		  $http({
       				 method : 'GET',
         			 url : 'http://localhost:8080/IT_Inventory/viewItemList'
    				 }).then(function successCallback(response) {
    				 	$scope.items = response.data;
    				 	console.log($scope.items);
    				 }, function errorCallback(response) {
        			 console.log(response.statusText);
    			});
  		  
  		var empEmail=localStorage.getItem("emailID")
		  $http({
     				 method : 'GET',
       			 url : 'http://localhost:8080/IT_Inventory/employeeDetails?empEmail='+empEmail}).then(function successCallback(response) {
  				 	$scope.employee = response.data;
  				 	console.log($scope.employee);
  				 }, function errorCallback(response) {
      			 console.log(response.statusText);
  			});
			
  		  }

    	

//		$scope.call = function(val){
//			$scope.itemStatus.total = 92348;
//			alert($scope.itemStatus.total);
//		};
		
    	
		$scope.loadDetails = function(itemName){
			
			$scope.categoryName=itemName.item.itemCategory;
			$http.get('http://localhost:8080/IT_Inventory/itemStatus?itemCategoryName='+$scope.categoryName).then(function successCallback(response) {
	            $scope.itemStatus = response.data;
	            //console.log($scope.itemStatus);
			}, function errorCallback(response) {
	            console.log(response.statusText);
	        })
			
		};
		
		
		$scope.employeeList = {};
		$scope.details = function(){
			$http.get('http://localhost:8080/IT_Inventory/emplist').then(function successCallback(response) {
	            $scope.employeeList = response.data;
	            //console.log($scope.employeeList);
			}, function errorCallback(response) {
	            console.log(response.statusText);
	        })
			
		};
		
//		$scope.itemSummary=[
//		                    {
//		                    	itemID : '1001',
//		            		    empID : 1774,
//		            		    status : 'allocated',
//		            		    location : 'Banglore',
//		            		    model : 'Dell',
//		            		    yearOfPurchase : '16/05/16'
//		                    },
//		                    {
//		                    	itemID : '1002',
//		            		    empID : 1774,
//		            		    status : 'allocated',
//		            		    location : 'Banglore',
//		            		    model : 'Dell',
//		            		    yearOfPurchase : '16/05/16'
//		                    }];
//		
		$scope.itemSummary=[];
    	$scope.loadSummary=function(){
			//console.log(itemname.item);
    	
			//var itemname=localStorage.getItem("itemCategoryName");
			//console.log(localStorage.getItem("itemCategoryName"));
			 $http.get('http://localhost:8080/IT_Inventory/summary?itemCategoryName='+$scope.categoryName).then(function successCallback(response) {
		            $scope.itemSummary = response.data;
		           console.log($scope.itemSummary);
		            
		        }, function errorCallback(response) {
		            console.log(response.statusText);
		        })
    	}; 
    	
    	$scope.inventoryDetails=[];
        $scope.loadInventory=function(){
      
       $http.get('http://localhost:8080/IT_Inventory/inventory').then(function successCallback(response) {
                 $scope.inventoryDetails = response.data;
                console.log($scope.inventoryDetails);
                 
             }, function errorCallback(response) {
                 console.log(response.statusText);
             })
       };
       
       $scope.totalItems = $scope.inventoryDetails.length;
       $scope.currentPage = 1;
       $scope.numPerPage = 10;
       
       $scope.paginate = function(value) {
         var begin, end, index;
         begin = ($scope.currentPage - 1) * $scope.numPerPage;
         end = begin + $scope.numPerPage;
         index = $scope.objects.indexOf(value);
         return (begin <= index && index < end);
       };
       
       $scope.allocateEmployee ={
     			itemID : '',
     			empID : '',
     			itemCategoryName :'',
     			status:''
			};
     		

			$scope.detailSubmit = function(){
				var dataObj = $scope.allocateEmployee;		
				
				$http.post('http://localhost:8080/IT_Inventory/allocateItem', dataObj).then(function successCallback(response) {
					//alert("Allocation Success");
					var result=response.data;
					alert(result);
				}, function errorCallback(response) {
		            console.log(response.statusText);
		        });
			};
     		
			$scope.deallocateItem = function(){
				var dataObj = $scope.allocateEmployee;		

				$http.post('http://localhost:8080/IT_Inventory/deallocateItem', dataObj).then(function successCallback(response) {
					var result=response.data;
					alert(result);
				}, function errorCallback(response) {
		            console.log(response.statusText);
		        });
				
			};
			
			$scope.getItemid = function(itemID){
			    
			    $scope.allocateEmployee.itemID=itemID.x.itemID;
			    //console.log($scope.allocateEmployee.itemID);
		   };
		   
//		   $scope.getEmpid = function(empID){
//			    $scope.allocateEmployee.empID=empID.x.empID;
//			    //console.log($scope.allocateEmployee.empID);
//		   };
       

    	})

/*.controller ('Allocatecontroller' , function($scope,$http){


      		

      })*/
      
.controller('upload', ['$scope', 'Upload','$q', '$timeout','$http', function ($scope, Upload,$q, $timeout,$http) {
    $scope.$watch('files', function () {
        $scope.upload($scope.files);
    });
    $scope.$watch('file', function () {
        if ($scope.file != null) {
            $scope.upload([$scope.file]);
        }
    });
    $scope.log = '';

    $scope.upload = function (files) {
        if (files && files.length) {
         
                var file = files[0];
    	Upload.upload({
    		url: 'http://localhost:8080/IT_Inventory/fileUpload',
            file: file
     
    	}).success(function(data, status) {
    		alert("File Upload Success");
            });
    }
    };
}])
		
      
         