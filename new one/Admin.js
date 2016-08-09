angular.module('AdminModule', ['ngFileUpload'])
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

            $scope.count ={
				allocated : '',
				defective : '',
				inStock : '',
				total : '',
			};

      $scope.countItems = function() {
 
    		    $http({method: 'GET', url: 'populatePersonDataFromServer.web'}).
		        success(function(data, status, headers, config) {
		        	$scope.count = data;	        	
		        }).
		        error(function(data, status, headers, config) {
		          // called asynchronously if an error occurs
		          // or server returns response with an error status.
		        });		    
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

			$scope.employeeDetails = function(){


			}




	})
		
.controller('upload', ['$scope', 'Upload','$q', '$timeout', function ($scope, Upload,$q, $timeout) {
// function handleFiles(file) {
//   var reader = new FileReader();
//   reader.onload = function(e) {
//   $scope.rightFileText = e.target.result; }
//   reader.onload = function(e) {  
//   bfile = e.target.result 
//   alert(bfile);   // this shows bfile
// }
//   var result =  reader.readAsText(file);
//   return result;
// }
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
         
                var file = files[i];
                 var fd = new FormData();
    fd.append('data', angular.toJson(test));
    fd.append("file", file);
    $http({
        method: 'POST',
        url: 'localhost:8080/IT_Inventory/fileUpload',
        headers: {'Content-Type': undefined},
        data: fd,
        transformRequest: angular.identity
        })
       .success(function(data, status) {
             alert("success");
        });
            }
    };
}])
		

      


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


          
        

			// $scope.variable = employee;

			// $scope.variable = "AngularJS POST Spring MVC Example:";	

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