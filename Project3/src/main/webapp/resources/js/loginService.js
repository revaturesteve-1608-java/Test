/**
 * 
 */

angular.module('loginApp').service('loginService', function($http, $window) {
	
	this.loginUser = function(person) {
		/*var parameter = JSON.stringify({username: username, password: password});
		console.log("here");
		console.log(username);
		console.log(password);*/
		
		$http.post('rest/login', person).then(function(response) {
			
			console.log(typeof response.data);
			console.log(response.data);
			var person = response.data;
			
			$window.location.href = 'index.html';
		/*	if(person.vaildated) {
				$window.location.href = 'index.html';
			} else {
				$window.location.href = 'updateTempinfo.html';
			}
			*/
			console.log("logged in");
			
			
		}, function(error) {
			console.log('error');
		})
	}
	
	this.getUser = function(callback) {
		$http.get('rest/user').then(callback);
	}
	
	
})