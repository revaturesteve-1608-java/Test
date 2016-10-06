/**
 * 
 */

angular.module('updateTempInfo', [])

.controller('updateTempCtrl', function($scope, updateTempDataService){
	console.log('FIRST IN CONTROLLER')
	$scope.updateInfo = function(oldPassword, newPassword, username){
		console.log('EVENT LISTENER WORKED')
		console.log("oldpass: " + oldPassword);
		console.log("newpass: " + newPassword);
		var usernamePass = [oldPassword, newPassword, username]
		updateTempDataService.update(usernamePass);
	}
	
	$scope.getUser = updateTempDataService.getUser(function(response){
		console.log(response.data)
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