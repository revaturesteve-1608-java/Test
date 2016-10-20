/**
 * 
 */
// tells angular to create a project with the name passed in
// not providing the [] will tell angular one already exists
angular.module('routingApp')

// scope is basically an object
.controller('newUserCtrl', function($scope, createUserService) {
	$scope.createUser = function(person) {
		console.log('About to create ' + person.firsName);
		createUserService.createUser(person);
	}

})


/*
 * Service
 * 
 * -Services allow you to create a reusable set of functions and values that can
 * be passed across the application. -Services are useful for getting data from
 * a database, as well as firing save, edit, and delete operations -Services can
 * share data between controllers
 */
.service('createUserService', function($http, $q) {
	
	this.createUser = function(person) {
		$http.post('rest/createUser', person).then(function(response) {
			console.log(response + ' YAY!');
		}, function(error) {
			console.log($q.reject(error));
		});
	}
})