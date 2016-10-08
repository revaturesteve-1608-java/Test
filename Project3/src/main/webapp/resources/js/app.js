var app = angular.module('routingApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    
    .when('/', {
        templateUrl : 'views/dashboard.html'
        // ,controller : 'homeController'
    })
    
    .when('/userProfile', {
        templateUrl : 'views/userProfile.html',
     //   controller : 'demoCtrl'
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
				console.log(response.data)
				$scope.roles = response.data;
			})

})

/*
 * Service
 * 
 * -Services allow you to create a reusable set of functions and values that can
 * be passed across the application. -Services are useful for getting data from
 * a database, as well as firing save, edit, and delete operations -Services can
 * share data between controllers
 */
app.service('createUserService', function($http, $q) {
	
	this.createUser = function(person) {
		$http.post('rest/createUser', person).then(function(response) {
			console.log(response + ' YAY!');
			
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
//	this.showAlert = function(ev) {
//	    // Appending dialog to document.body to cover sidenav in docs app
//	    // Modal dialogs should fully cover application
//	    // to prevent interaction outside of dialog
//	    $mdDialog.show(
//	      $mdDialog.alert()
//	        .parent(angular.element(document.querySelector('#popupContainer')))
//	        .clickOutsideToClose(true)
//	        .title('This is an alert title')
//	        .textContent('You can specify some description text in here.')
//	        .ariaLabel('Alert Dialog Demo')
//	        .ok('Got it!')
//	        .targetEvent(ev)
//	    );
//	  };
	
	this.getRoles = function(callback) {
		// callback is a function that takes a response
		$http.post('rest/getRoles').then(callback);
	}
})

angular.module('loginApp', ['ngMaterial']);
