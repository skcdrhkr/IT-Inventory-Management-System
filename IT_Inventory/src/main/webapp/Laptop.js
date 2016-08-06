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

	$scope.itemStatus ={
  			Total : '',
  			InStock :'',
  			Allocated :'',
  			Defective :'',
  			};
  		
	
	
         // $scope.laptop=[];

//          			$scope.loadDetails= function(){		
//				$http({
//       				 method : 'GET',
//         			 url : 'http://localhost:8080/IT_Inventory/summary'
//    				 }).then(function successCallback(response) {
//    				 	$scope.laptop = response.data;
//    				 	$scope.descriptions = $scope.laptop.join('\n');
//    				 	$scope.rowCount = $scope.laptop.length;
//         
//    				 }, function errorCallback(response) {
//        			 console.log(response.statusText);
//    				 });
//			}
//      
		$scope.loadDetails = function(itemname){
		//var dataObj = $scope.employee;	
		console.log(itemname.name);
		//var temp;
		//http://localhost:8080/IT_Inventory/registration.html
		 $http.get('http://localhost:8080/IT_Inventory/itemStatus').then(function successCallback(response) {
	            itemStatus = response.data;
	            alert(itemStatus);
	            
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        })
		}
	

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

      	$scope.items=['Laptop' , 'Mouse' , 'HeadPhones', 'Bag']

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



      })

      .controller ('Allocatecontroller' , function($scope,$http){


      		$scope.employee ={
      			itemID : '',
      			empID : '',
      			itemCategory :'',
      			status:''
			};

      		

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.detailSubmit = function(){
				var dataObj = $scope.employee;		

				$http.post('http://localhost:8080/IT_Inventory/allocateItem', dataObj).then(function successCallback(response) {
		            //temp = response.data;
		            //alert("Registration Success");
					//$scope.responseData = data;
					alert("Allocation Success");
				});
				//response.error(function(data, status, headers, config) {
				//	alert( "Exception details: " + JSON.stringify({data: data}));
				//});
			}

     

      			$scope.deallocateItem = function(){
				var dataObj = $scope.employee;		

				$http.post('http://localhost:8080/IT_Inventory/deallocateItem', dataObj).then(function successCallback(response) {
		            //temp = response.data;
		            //alert("Registration Success");
					//$scope.responseData = data;
					alert("Deallocation Success");
				});
				
			}

      })

          
        

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	


		
			




// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
 
 
// @Controller
// public class SpringMVCController {
	
// 	@RequestMapping(value = "/Registration", method = RequestMethod.POST)
// 	public @ResponseBody String Registration(@RequestBody Employee emp) {
		


// 		return reponseData.toString();
// 	}
 
// }