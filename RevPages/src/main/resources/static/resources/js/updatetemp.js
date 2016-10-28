/**
 * 
 */

angular.module('updateTemp', [])

.controller('updateTempCtrl', function($scope, updateTempDataService, $window){
	console.log('FIRST IN CONTROLLER')
	$scope.checked;
	$scope.checkingPassword = function(oldPassword){
		console.log("click event happened. password changed")
		if(oldPassword !== "12345"){
			$scope.checked = false;
		} else{
			$scope.checked =  true;
		}
	}
	
	$scope.updateInfo = function(oldPassword, newPassword, username){
		var usernamePass = [oldPassword, newPassword, username, $scope.user.username]
		updateTempDataService.update(usernamePass);
	}
	
	$scope.user;
	
	$scope.getUser = updateTempDataService.getUser(function(response){
		$scope.user = response.data;
		
	})
})

.service('updateTempDataService', function($http, $window, $q){
	
	this.update = function(usernamePass){
		$http.post("/updateTemp", usernamePass).then(function(response) {
			$window.alert(response.data);
			if(response.data == "Updated"){
				$window.location.href = 'index.html';
				
			} 
			
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	this.getUser = function(callback){
		$http.get('/user').then(callback)
	}
})