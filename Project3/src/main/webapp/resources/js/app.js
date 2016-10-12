var app = angular.module('routingApp', ['ngRoute','ngMaterial','ngMessages','material.svgAssetsCache', 'ngCookies']);

app.config(function($routeProvider) {
	
	
	
    $routeProvider
    .when('/', {
        templateUrl : 'views/dashboard.html',
        controller : 'indexCtrl'
    })
    
    .when('/userProfile', {
        templateUrl : 'views/userProfile.html',
        controller : 'profileCtrl'
    })
    
    .when('/settings', {
        templateUrl : 'views/settings.html'
        // ,controller : 'contactContoller'
    })
    
    .when('/createNewUser', {
        templateUrl : 'views/createNewUser.html',
        controller : "newUserCtrl"
    })
    
    .otherwise({
        redirectTo : '/'
    });
})

app.controller("newUserCtrl", function($scope, createUserService) {
	$scope.createUser = function(person) {
		console.log('About to create ' + person.first_name);
		createUserService.createUser(person);
	}
	
	$scope.getRoles = createUserService.getRoles(
			// pass in the callback function
			function(response) {
				$scope.roles = response.data;
			})
	
	

})
app.controller("frontCtrl", function($scope, $http, $window, $cookies, createUserService) {
	
	$scope.user;
	
	$scope.homePage = function() {
//		console.log($cookies.user)
		
		if($scope.user.id == 0){
			$window.location.href = 'login.html';
		} else {
			$window.location.href = 'moderate-view.html';
		}
	}
	
	$scope.getUser = createUserService.getUser(
			function(response){
				console.log(response);
				console.log(response.data) 
			//	console.log(typeof response.data[0].maker);
				$scope.user = response.data; 	
				
				
				
				console.log($scope.user);
				
			})

})
/*
 * Service
 * 
 * -Services allow you to create a reusable set of functions and values that can
 * be passed across the application. 
 * -Services are useful for getting data from
 * a database, as well as firing save, edit, and delete operations 
 * -Services can share data between controllers
 */
app.service('createUserService', function($http, $q, $window) {
	
	this.createUser = function(person) {
		$http.post('rest/createUser', person).then(function(response) {
			console.log(response + ' YAY!');
			$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.getRoles = function(callback) {
		// callback is a function that takes a response
		$http.post('rest/getRoles').then(callback);
	}
	
	this.getUser = function(callback) {
		$http.get('rest/user').then(callback);
	}
	
	this.logout = function() {
		$http.post('rest/logout').then(function(response) {
			
			console.log("here");
			$window.location.href = 'login.html';
			
			//$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	
})