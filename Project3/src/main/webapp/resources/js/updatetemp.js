/**
 * 
 */

angular.module('routingApp')

.controller('updateTempCtrl', function($scope, updateTempDataService, $window){
	console.log('FIRST IN CONTROLLER')
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

.service('updateTempDataService', function($http, $window, $q){
	
	this.update = function(usernamePass){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateTemp", usernamePass).then(function(response) {
			console.log(' YAY!');
			console.log(response + ' YAY!');
			$window.alert(response.data);
			if(response.data == "Updated"){
				$window.location.href = 'login.html';
			} 
			
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
	
//	 this.showAlert = function(ev) {
//		    // Appending dialog to document.body to cover sidenav in docs app
//		    // Modal dialogs should fully cover application
//		    // to prevent interaction outside of dialog
//		    $mdDialog.show(
//		      $mdDialog.alert()
//		        .parent(angular.element(document.querySelector('#popupContainer')))
//		        .clickOutsideToClose(true)
//		        .title('Incorrect Temporary Password')
//		        .textContent('Update failed. You have put in the incorrect Temporary Password')
//		        .ariaLabel('Alert Dialog Demo')
//		        .ok('Got it!')
//		        .targetEvent(ev)
//		    );
//		  };
})