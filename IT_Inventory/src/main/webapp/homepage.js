var loginmodule = angular.module('loginmodule',[]);

loginmodule.controller("logincontroller",function($scope,$http){
     function onSignIn(googleUser){
       // Useful data for your client-side scripts:
       var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());
        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        loginmodule.value (profile);
        window.location.href = 'registration.html'
      };

      window.onSignIn = onSignIn
     $scope.signOut = function(){
          var auth2 = gapi.auth2.getAuthInstance();
          auth2.signOut().then(function () {
            console.log('User signed out.');
          });
        }

})




// var registrationModule = angular.module('registrationModule', []);
  
//   registrationModule.controller("registrationController", function($scope, $http) {
//       $scope.detailSubmit = function(){
//         var dataObj = $scope.employee;    

//         var response = $http.post('localhost://8080/IT_Inventory/register', dataObj);
//         response.success(function(data, status, headers, config) {
//           $scope.responseData = data;
//         });
//         response.error(function(data, status, headers, config) {
//           alert( "Exception details: " + JSON.stringify({data: data}));
//         });
//       }
    
//   });
  
