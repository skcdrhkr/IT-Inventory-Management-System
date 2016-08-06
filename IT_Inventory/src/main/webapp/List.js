angular.module('ListModule', [])
	
.controller('ListController', function($scope) {

		$scope.items=['apple' , 'banana' , 'mango', 'guava']
		

		
          
        

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
		
			//$scope.laptopView = false;
			//$scope.homeDisplay = true;
			$scope.showLaptop = function () {
			$scope.laptopView = true;
			$scope.homeDisplay = false;
		}

			$scope.showHomePage = function() {
				$scope.laptopView = false;
				$scope.homeDisplay = true;
			}
	})




