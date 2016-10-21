/**
 * 
 */
angular.module('routingApp')
.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('dark-grey').backgroundPalette('blue').dark();
})
.controller("profileCtrl", function($scope, createUserService) {
	$scope.createUser = function(person) {
		console.log('About to create ' + person.first_name);
		profileService.createUser(person);
	}
	
	$scope.user;
	
	$scope.getRoles = createUserService.getRoles(
			// pass in the callback function
			function(response) {
				$scope.roles = response.data;
			})
			
	$scope.getUser = createUserService.getUser(
			function(response){ 		
				console.log(response);
				console.log(response.data); 
			//	console.log(typeof response.data[0].maker);
				$scope.user = response.data; 	
				
				console.log($scope.user);
				
			})

});

/*
 * Service
 * 
 * -Services allow you to create a reusable set of functions and values that can
 * be passed across the application. 
 * -Services are useful for getting data from
 * a database, as well as firing save, edit, and delete operations 
 * -Services can share data between controllers
 */
app.service('profileService', function($http, $q, $window) {
	
	this.createUser = function(person) {
		$http.post('rest/createUser', person).then(function(response) {
			$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.getPerson = function(callback) {
		// callback is a function that takes a response
		$http.post('rest/getPerson').then(callback);
	}
	
	
	
	
})
