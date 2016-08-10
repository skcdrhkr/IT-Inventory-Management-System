angular.module('EmployeeModule',[])
.controller('EmployeeController', function($scope,$http)
{

	
			
			$scope.showLaptop = function () {
			$scope.laptopView = true;
			$scope.homeDisplay = false;
			
			}

			$scope.showHomePage = function() {
				$scope.homeDisplay = true;
				$scope.laptopView = false;
			}
			
			$scope.management = function(){
			    $scope.laptopView = false;
			    $scope.homeDisplay = false;
			    $scope.showManagement=true;
			    $scope.askQuery=false;
			   }
			   
			   $scope.query=function(){
			    $scope.laptopView = false;
			    $scope.homeDisplay = false;
			    $scope.showManagement=false;
			    $scope.askQuery=true;
			   }
		
      		$scope.employee ={
    				empID : '',
    				roleID:'',
    				empName : '',
    				empEmail : localStorage.getItem("emailID"),
    				empLocation : '',
    				phone : ''
    			};
      		$scope.items=[
        	              {
        	            	  
        	            	 itemCategory: '',
        	            	 inStockCount: ''
        	              }
        	              ];
        	
    			
      		$scope.signOut = function(){
    	          var auth2 = gapi.auth2.getAuthInstance();
    	          auth2.signOut().then(function () {
    	            console.log('User signed out.');
    	          window.location.href = "/IT_Inventory";
    	          });
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
    	  
    		$scope.employeeItems=[{
    			
    		}];
    		
    		$scope.item ={
          			itemtypeID :'',
          			serviceTag :'',
          			model :'',
          			
    			}
    		
    		$scope.loadDetails = function(itemName){
			
			$scope.categoryName=itemName.item.itemCategory;
			$http.get('http://localhost:8080/IT_Inventory/empItems?categoryName='+$scope.categoryName+"&empID="+$scope.employee.empID).then(function successCallback(response) {
	            $scope.employeeItems = response.data;
	            console.log($scope.employeeItems);
			}, function errorCallback(response) {
	            console.log(response.statusText);
	        })
			
		};
		
		$scope.subject="Sub";
		$scope.message="Message";
		
		$scope.postQuery = function(){
		    $http.get('http://localhost:8080/IT_Inventory/query?empID='+$scope.employee.empID+"&subject="+$scope.subject+"&query="+$scope.message).then(function successCallback(response) {
		     var result=response.data;
		     alert(result);
		    }, function errorCallback(response) {
		              alert("Mail Sent");
		          });
		        }
})


.directive("flip", function(){
  
  function setDim(element, width, height){
    element.style.width = width;
    element.style.height = height;
  }
  
  var cssString =
    "<style> \
    .flip {float: left; overflow: hidden} \
    .flipBasic { \
    position: absolute; \
    -webkit-backface-visibility: hidden; \
    backface-visibility: hidden; \
    transition: -webkit-transform .5s; \
    transition: transform .5s; \
    -webkit-transform: perspective( 800px ) rotateY( 0deg ); \
    transform: perspective( 800px ) rotateY( 0deg ); \
    } \
    .flipHideBack { \
    -webkit-transform:  perspective(800px) rotateY( 180deg ); \
    transform:  perspective(800px) rotateY( -180deg ); \
    } \
    .flipHideFront { \
    -webkit-transform:  perspective(800px) rotateY( -180deg ); \
    transform:  perspective(800px) rotateY( -180deg ); \
    } \
    </style> \
    ";
    
  document.head.insertAdjacentHTML("beforeend", cssString);
  
  
  return {
    restrict : "E",
    controller: function($scope, $element, $attrs){
      
      var self = this;
      self.front = null,
      self.back = null;
      
      
      function showFront(){
        self.front.removeClass("flipHideFront");
        self.back.addClass("flipHideBack");
      }
      
      function showBack(){
        self.back.removeClass("flipHideBack");
        self.front.addClass("flipHideFront");
      }
      
      self.init = function(){
        self.front.addClass("flipBasic");
        self.back.addClass("flipBasic");
        
        showFront();
        self.front.on("click", showBack);
        self.back.on("click", showFront);
      }
    
    },
    
    link : function(scope,element,attrs, ctrl){
      
      var width = attrs.flipWidth || "100px",
        height =  attrs.flipHeight || "100px";
      
      element.addClass("flip");
      
      if(ctrl.front && ctrl.back){
        [element, ctrl.front, ctrl.back].forEach(function(el){
          setDim(el[0], width, height);
        });
        ctrl.init();
      }
      else {
        console.error("FLIP: 2 panels required.");
      }
      
    }
  }
  
})

.directive("flipPanel", function(){
  return {
    restrict : "E",
    require : "^flip",
    //transclusion : true,
    link: function(scope, element, attrs, flipCtr){
      if(!flipCtr.front) {flipCtr.front = element;}
      else if(!flipCtr.back) {flipCtr.back = element;}
      else {
        console.error("FLIP: Too many panels.");
      }
    }
  }
});


