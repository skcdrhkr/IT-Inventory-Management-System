<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>FrontPage</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Bootstrap JS -->
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="996148857811-v0sqhp5928ovgj70jb380pbkdkkgfatj.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <style type="text/css">
    .bs{
    	margin: 20px;
    }
    </style>
  </head>
  <body>
  <h1 align="center">IT Inventory Management System</h1>
<div class="bs">
<div class="container-fluid">
		
<!-- Carousel container -->
<div id="my-pics" class="carousel slide" data-ride="carousel">

<!-- Indicators -->
<ol class="carousel-indicators">
<li data-target="#my-pics" data-slide-to="0" class="active"></li>
<li data-target="#my-pics" data-slide-to="1"></li>
<li data-target="#my-pics" data-slide-to="2"></li>
</ol>

<!-- Content -->
<div class="carousel-inner" role="listbox">

<!-- Slide 1 -->
<div class="item active">
<img src="Images/img1.jpg" alt="Picture 1">
</div>

<!-- Slide 2 -->
<div class="item">
<img src="Images/img2.jpg" alt="Picture 2">
</div>

<!-- Slide 3 -->
<div class="item">
<img src="Images/img3.jpg" alt="Picture 3">
</div>

</div>

<!-- Previous/Next controls -->
<a class="left carousel-control" href="#my-pics" role="button" data-slide="prev">
<span class="icon-prev" aria-hidden="true"></span>
<span class="sr-only">Previous</span>
</a>
<a class="right carousel-control" href="#my-pics" role="button" data-slide="next">
<span class="icon-next" aria-hidden="true"></span>
<span class="sr-only">Next</span>
</a>

</div>

<!-- Center the image -->
<style scoped>
.item img{
    margin: 0 auto;
}
</style>

</div>
</div>
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    <script>
      function onSignIn(googleUser) {
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
        window.location="http://localhost:8080/IT_Inventory/login?emailID="+profile.getEmail();
      }
      </script>
        <a href="#" onclick="signOut();">Sign out</a>
      <script>
        function signOut() {
          var auth2 = gapi.auth2.getAuthInstance();
          auth2.signOut().then(function () {
            console.log('User signed out.');
          });
        }
      </script>
  </body>
</html>