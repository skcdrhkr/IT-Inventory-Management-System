var registrationModule = angular.module('registrationModule', ['loginmodule']);
	
	registrationModule.controller("registrationController", function($scope, $http) {
		//var email = googleUser.getBasicProfile().getEmail();
			$scope.employee ={
				empID : '',
				roleID:'2',
				empName : '',
				empEmail : '',
				empLocation : '',
				phone : ''
			};

			//$scope.variable = employee;

			//$scope.variable = "AngularJS POST Spring MVC Example:";	

			$scope.detailSubmit = function(){
				var dataObj = $scope.employee;		

				var temp;
				//http://localhost:8080/IT_Inventory/registration.html
				$http.post('http://localhost:8080/IT_Inventory/register', dataObj).then(function successCallback(response) {
			            //temp = response.data;
			            alert("Registration Success");
			            window.location.href = "/IT_Inventory/employee.html";
			            	
			            
			            
			        }, function errorCallback(response) {
			           console.log(response.statusText);
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