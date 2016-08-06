angular.module('LaptopModule', [])
	
.controller('LaptopController', function($scope,$http) {


		// $scope.loadDetails= function(item){	
  //         var data = [
  //           'Description 1',
  //           'Description 2',
  //           'Description 3',
  //           'Description 4',
  //           'Description 5',
  //           'Description 6',
  //           'Description 7',
  //           'Description 8',
  //         ];
          
  //         $scope.descriptions = data.join('\n');
  //         $scope.rowCount = data.length;


	

	
	
//	$window.onload = function(e) {
//		  //your magic here
//		console.log("Function loaded automatically");
//	}

	
            $scope.laptopView = false;
			$scope.homeDisplay = true;
			$scope.AllocateForm = false;
			$scope.AddNewItem = false;
			
			$scope.showLaptop = function () {
			$scope.laptopView = true;
			$scope.homeDisplay = false;
			$scope.AllocateForm = false;
			$scope.AddNewItem = false;
		}

			$scope.showHomePage = function() {
				$scope.laptopView = false;
				$scope.homeDisplay = true;
				$scope.AllocateForm = false;
				$scope.AddNewItem = false;
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
			}


			$scope.AddNewItem1 =function() {
				$scope.laptopView = false;
				$scope.homeDisplay = false;
				$scope.AllocateForm = false;
				$scope.AddNewItem = true;
			}


			$scope.itemStatus ={
		  			Total : '',
		  			InStock :'',
		  			Allocated :'',
		  			Defective :'',
		  			};
		  		
	
//			$scope.loadSummary=function(itemname){
//				console.log(itemname.item);
//				var itemName=itemname.item;
//				 $http.get('http://localhost:8080/IT_Inventory/summary?itemCategoryName='+itemName).then(function successCallback(response) {
//			            itemSummary = response.data;
//			            alert(itemSummary);
//			            
//			        }, function errorCallback(response) {
//			            console.log(response.statusText);
//			        })
//				}
			
			
			$scope.loadDetails = function(itemname){
				console.log(itemname.item);
				var itemName=itemname.item;
				 $http.get('http://localhost:8080/IT_Inventory/itemStatus?itemCategoryName='+itemName).then(function successCallback(response) {
			            itemStatus = response.data;
			            //alert(itemStatus);
			            
			        }, function errorCallback(response) {
			            console.log(response.statusText);
			        })
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
			};
      		

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.NewItem = function(){
				var dataObj = $scope.item;		

				$http.post('http://localhost:8080/IT_Inventory/addItems', dataObj).then(function successCallback(response) {
		            //temp = response.data;
		            alert("Success");
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
			}

	})

      


      .controller('ListController', function($scope,$http) {

      	//$scope.items=['Laptop' , 'Mouse' , 'HeadPhones', 'Bag']
      	$scope.items=[];
    	$scope.fn_load = function () {
  		  console.log("page load List Controller")
  		  $http({
       				 method : 'GET',
         			 url : 'http://localhost:8080/IT_Inventory/viewItemList'
    				 }).then(function successCallback(response) {
    				 	$scope.items = response.data;
    				 	console.log(items);
         
    				 }, function errorCallback(response) {
        			 console.log(response.statusText);
    			});
			}

//    	$scope.loadSummary=function(itemName){
//    		
//    		console.log(itemName.item);
//    	}
//    	    
    	$scope.loadSummary=function(itemname){
			console.log(itemname.item);
			var itemName=itemname.item;
			var itemSummary=[];
			 $http.get('http://localhost:8080/IT_Inventory/summary?itemCategoryName='+itemName).then(function successCallback(response) {
		            itemSummary = response.data;
		            alert(itemSummary);
		            
		        }, function errorCallback(response) {
		            console.log(response.statusText);
		        })
			}
		
    	
    	
  		})


//      	 $scope.items=[];
//
//          			$scope.loadDetails= function(){		
//				$http({
//       				 method : 'GET',
//         			 url : 'http://localhost:8080/IT_Inventory/summary'
//    				 }).then(function successCallback(response) {
//    				 	$scope.items = response.data;
//         
//    				 }, function errorCallback(response) {
//        			 console.log(response.statusText);
//    				 });
//			}




.controller ('Allocatecontroller' , function($scope,$http){


      		$scope.employee ={
      			itemID : '',
      			empID : '',
      			itemCategoryName :'',
      			status:''
			};
      		
			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.detailSubmit = function(){
				var dataObj = $scope.employee;		
				
				$http.post('http://localhost:8080/IT_Inventory/allocateItem', dataObj).then(function successCallback(response) {
					alert("Allocation Success");
				}, function errorCallback(response) {
		            console.log(response.statusText);
		        });
			}

     

      			$scope.deallocateItem = function(){
				var dataObj = $scope.employee;		

				$http.post('http://localhost:8080/IT_Inventory/deallocateItem', dataObj).then(function successCallback(response) {
					alert("Deallocation Success");
				}, function errorCallback(response) {
		            console.log(response.statusText);
		        });
				
			}

      })
         
   