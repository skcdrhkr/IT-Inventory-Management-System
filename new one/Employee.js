angular.module('AdminModule', [])
.controller('AdminController', function($scope) {


		$scope.loadDetails= function(item){	
          var data = [
            'Description 1',
            'Description 2',
            'Description 3',
            'Description 4',
            'Description 5',
            'Description 6',
            'Description 7',
            'Description 8',
          ];
          
          $scope.descriptions = data.join('\n');
          $scope.rowCount = data.length;
      }


      $scope.signOut = function(){
          var auth2 = gapi.auth2.getAuthInstance();
          auth2.signOut().then(function () {
            console.log('User signed out.');
          });
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
      			dateOfPurchase : '',
      			ram : '',
      			processor :'',
      			os:''
			};
      		

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.NewItem = function(){
				var dataObj = $scope.item;		

				var response = $http.post('localhost://8080/IT_Inventory/summary', dataObj);
				response.success(function(data, status, headers, config) {
					$scope.responseData = data;
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
			}




	})
		

      


      .controller('ListController', function($scope) {

      	$scope.items=['Laptop' , 'Mouse' , 'HeadPhones', 'Bag']

      })

      .controller ('Allocatecontroller' , function($scope){


      		$scope.employee ={
      			itemID : '',
      			itemtypeID :'',
      			empID : '',
      			itemCateogary :'',
      			status:''
			};

      		

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.detailSubmit = function(){
				var dataObj = $scope.employee;		

				var response = $http.post('localhost://8080/IT_Inventory/summary', dataObj);
				response.success(function(data, status, headers, config) {
					$scope.responseData = data;
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
			}

      })


          
        

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			// $scope.loadDetails= function(){		
			// 	$http({
   //     				 method : 'GET',
   //       			 url : 'http://localhost:8080/IT_Inventory/summary'
   //  				 }).then(function successCallback(response) {
   //  				 	$scope.laptop = response.data;
   //  				 	$scope.descriptions = $scope.laptop.join('\n');
   //  				 	$scope.rowCount = $scope.laptop.length;
         
   //  				 }, function errorCallback(response) {
   //      			 console.log(response.statusText);
   //  				 });
			// }
		
			




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