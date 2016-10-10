/**
 * 
 */

angular.module('updateUserInfo', [])

.controller('updateUserCtrl', function($scope, updateUserData){
	console.log('FIRST IN CONTROLLER')
	$scope.updateInformation = function(oldPassword, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn){
		console.log('EVENT LISTENER WORKED')
		console.log("oldpass: " + oldPassword);
		console.log("newpass: " + newPassword);
					
		var information = [oldPassword, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn/*, $scope.user.username*/]
		console.log(information);
		updateUserData.update(information);
	}
	
//	$scope.user;
//	
//	$scope.getUser = updateUserData.getUser(function(response){
//		$scope.user = response.data;
//		console.log($scope.user);
//		
//	})
})

.service('updateUserData', function($http){
	this.update = function(information){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateInfo", information).then()
	}
	
//	this.getUser = function(callback){
//		$http.get('rest/user').then(callback)
//	}
})