/**
 * 
 */

angular.module('updateTempInfo', [])

.controller('updateTempCtrl', function($scope, updateTempDataService){
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
		
		console.log('EVENT LISTENER WORKED')
		console.log("oldpass: " + oldPassword);
		console.log("newpass: " + newPassword);
		var usernamePass = [oldPassword, newPassword, username, $scope.user.username]
		updateTempDataService.update(usernamePass);
	}
	
	$scope.user;
	
	$scope.getUser = updateTempDataService.getUser(function(response){
		$scope.user = response.data;
		console.log($scope.user);
		
	})
})

.service('updateTempDataService', function($http){
	
	this.update = function(usernamePass){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateTemp", usernamePass).then()
	}
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
})