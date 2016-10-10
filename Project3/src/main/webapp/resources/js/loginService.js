/**
 * 
 */

angular.module('routingApp').service('loginService', function($http, $window, $mdDialog) {
	
	this.loginUser = function(person) {
		/*var parameter = JSON.stringify({username: username, password: password});
		console.log("here");
		console.log(username);
		console.log(password);*/
		
		$http.post('rest/login', person).then(function(response) {
			
		//	console.log(typeof response.data);
		//	console.log(response.data);
			var person = response.data;
			
			console.log(person);
			if(person.id = 0) {
				if(person.vaildated) {
					$window.location.href = 'index.html';
				} else {
					$window.location.href = 'updateTempInfo.html';
				}
				
				console.log("logged in");
			} else {
				console.log("where");
			}
			
		}, function(error) {
			//console.log('error');
			showAlert();
		})
	}
	
	 this.showAlert = function(ev) {
		    // Appending dialog to document.body to cover sidenav in docs app
		    // Modal dialogs should fully cover application
		    // to prevent interaction outside of dialog
		    $mdDialog.show(
		      $mdDialog.alert()
		        .parent(angular.element(document.querySelector('#popupContainer')))
		        .clickOutsideToClose(true)
		        .title('This is an alert title')
		        .textContent('You can specify some description text in here.')
		        .ariaLabel('Alert Dialog Demo')
		        .ok('Got it!')
		        .targetEvent(ev)
		    );
		  };
	
	
})