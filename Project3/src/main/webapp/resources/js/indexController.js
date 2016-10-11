/**
 * 
 */
var app = angular.module('routingApp');
app.controller("indexCtrl", function($scope, $http, $window, $cookies, createUserService) {
	
	$scope.user;
	
	console.log($scope.user);
	
	
	
	$scope.logout = function(){
		
		
		createUserService.logout();
	//	$window.location.href = 'login.html';
		
	}
	
	$scope.getUser = createUserService.getUser(
			function(response){
				console.log(response);
				console.log(response.data) 
			//	console.log(typeof response.data[0].maker);
				$scope.user = response.data; 	
				
				if($scope.user.id == 0){
					$window.location.href = 'login.html';
				}
				
				console.log($scope.user);
				
			})

})