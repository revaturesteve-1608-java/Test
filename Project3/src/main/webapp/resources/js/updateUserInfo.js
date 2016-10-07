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
		if((typeof oldPassword) === "undefined" || (typeof newPassword) === "undefined") {
			newPassword = "N/A"
		}
		if((typeof newEmail) === "undefined" || newEmail === "") {
			newEmail = "N/A"
		}
		if((typeof username) === "undefined" || username === ""){
			username = "N/A"
		}
		if((typeof newPhone) === "undefined" || newPhone === ""){
			newPhone = "N/A"
		}
		if((typeof newUniversity) === "undefined" || newUniversity === ""){
			newUniversity = "N/A"
		}
		if((typeof newLinkedIn) === "undefined" || newLinkedIn === ""){
			newLinkedIn = "N/A"
		}
					
		var information = [oldPassword, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn]
		console.log(information);
		updateUserData.update(information);
	}
})

.service('updateUserData', function($http){
	this.update = function(information){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateInfo", information).then()
	}
})