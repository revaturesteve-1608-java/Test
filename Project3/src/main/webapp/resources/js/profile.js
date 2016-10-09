/**
 * 
 */
angular.module('routingApp')

.controller("profileCtrl", function($scope, profileService) {
	$scope.createUser = function(person) {
		console.log('About to create ' + person.first_name);
		profileService.createUser(person);
	}
	
	$scope.getPerson = profileService.getPerson(
			// pass in the callback function
			function(response) {
				console.log(response + " NO");
				$scope.person = response.data;
			}
	)

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
			console.log(response + ' YAY!');
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
