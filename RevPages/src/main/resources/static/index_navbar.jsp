<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<style>

</style>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#index-navbar"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Revature</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="index-navbar" 
			data-ng-controller="frontCtrl">
			<ul class="nav navbar-nav navbar-right">
				<li>

				<button type="button" class="btn btn-danger"

				
					data-target="#myModal" 

					data-ng-click="homePage()">Login</button>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<!-- The Modal -->
<div id="myModal" class="modal" data-ng-controller="loginController">

  <!-- Modal Content -->

    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="resources/imgs/favicon_192.png" />
            <form class="form-signin">
                <input type="text" id="inputUsername" class="form-control" placeholder="Username" 
                	data-ng-model="person.username" required autofocus>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" 
                	data-ng-model="person.password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"
                	data-ng-click="signIn(person)">Sign in</button>
            </form><!-- /form -->
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div><!-- /card-container -->
    </div><!-- /container -->
    <script type="text/ng-template" id="dialog1.tmpl.html">
		<md-dialog aria-label="">
    		<md-toolbar>
      			<div class="md-toolbar-tools">
        			<h2>User not found</h2>
        			<span flex></span>
        			<md-button class="md-icon-button" ng-click="cancel()">
          				Ok
        			</md-button>
      			</div>
    		</md-toolbar>
	 		<md-dialog-content>
	 			<h5>Please try again</h5>
			</md-dialog-content>
		</md-dialog>
	</script>
</div>
