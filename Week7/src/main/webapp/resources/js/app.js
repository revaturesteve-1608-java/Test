/**
 * 
 */
// tells angular to create a project with the name passed in
// not providing the [] will tell angular one already exists
angular.module('mainApp', [])
// scope is basically an object
.controller('teamCtrl', function($scope, dataService) {
	$scope.getTeams = dataService.getTeams(
		// pass in the callback function
		function(response) {
			console.log(response.data)
			$scope.teams = response.data;
		})

	$scope.saveTeam = function(team) {
		console.log('About to save ' + team.name);
		console.log('About to save ' + team.stadium);
		dataService.saveTeam(team);
	}
})

.controller('playerCtrl', function($scope, dataService) {

	$scope.getPlayer = dataService.getPlayer(
		// pass in the callback function
		function(response) {
		console.log(response.data)
		$scope.players = response.data;
	})

	$scope.savePlayer = function(player) {
		dataService.savePlayer(player);
	}
})

.controller('childofMainCtrl', function($scope) {
	$scope.flashSmile = function() {
		console.log(" :) ");
	}
})

.controller('formCtrl', function($scope) {
	$scope.master = {
		firstName : "John",
		lastName : "Doe"
	};
	$scope.reset = function() {
		$scope.user = angular.copy($scope.master);
	};
	$scope.reset();
})

/*
 * Service
 * 
 * -Services allow you to create a reusable set of functions and values that can
 * be passed across the application. -Services are useful for getting data from
 * a database, as well as firing save, edit, and delete operations -Services can
 * share data between controllers
 */
.service('dataService', function($http, $q) {

	this.getTeams = function(callback) {
		// callback is a function that takes a response
		console.log('mo');
		$http.post('rest/getTeam').then(callback);
	}
	
	this.saveTeam = function(team) {
		$http.post('rest/saveTeam', team).then(function(response) {
			console.log(response + ' YAY!');
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.getPlayer = function(callback) {
		// callback is a function that takes a response
		$http.post('rest/getPlayer').then(callback);
	}

	this.savePlayer = function(player) {
		$http.post('rest/savePlayer', player).then(function(response) {
			console.log(response + ' YAY!');
		}, function(error) {
			console.log($q.reject(error));
		});
	}
})
