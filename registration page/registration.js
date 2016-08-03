var registrationModule = angular.module('registrationModule', []);
	
	registrationModule.controller("registrationController", function($scope, $http) {
		
			$scope.employee ={
				empid : '',
				empname : '',
				empemail : '',
				emplocation : '',
				empmobile : ''
			};

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.detailSubmit = function(){
				var dataObj = $scope.employee;		

				var response = $http.post('localhost://8080/IT_Inventory/register', dataObj);
				response.success(function(data, status, headers, config) {
					$scope.responseData = data;
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
			}
		
	});
	

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