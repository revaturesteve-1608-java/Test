/**
 * 
 */
angular.module('routingApp').controller('loginController', function($scope, $mdDialog, $http, $window, $cookies, createUserService) {

//			$scope.loginUser = function(person) {
//				console.log(person);
//				loginService.loginUser(person);
//			}

			$scope.status = '  ';
			$scope.customFullscreen = false;
			
			$scope.toIndex = function() {
				$window.location.href = 'index.jsp';
			}
			
			$scope.signIn = function(person) {
				console.log("here");
				$http.post('rest/login', person).then(function(response) {
					//	console.log(typeof response.data);
					//	console.log(response.data);
					var person = response.data;
					
					console.log(person);
					console.log(person.id);
					
					
					if(person.id !== 0){
						$cookies.user = person;
						if(person.vaildated) {
							
						
							if(person.role.roleName == "Moderator") {
								$window.location.href = 'moderate-view.html';
							} else {
								$window.location.href = 'associate-view.html';
							}
						} else {
							$window.location.href = 'updateTempInfo.html';
						}
					} else {
						console.log("where2");
						alert("Wrong Username/Password");
//						customAlert();
					}
				}, function(error) {
					customAlert();
				})
			
		}
			
			 function showAlert(ev) {
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
			 
			 
			 
			 function DialogController($scope, $mdDialog) {
				    $scope.hide = function() {
				      $mdDialog.hide();
				    };

				    $scope.cancel = function() {
				      $mdDialog.cancel();
				    };

				    $scope.answer = function(answer) {
				      $mdDialog.hide(answer);
				    };
				  }
			 
			 function customAlert(ev) {
				    $mdDialog.show({
				      controller: DialogController,
				      templateUrl: 'dialog1.tmpl.html',
				      parent: angular.element(document.body),
				      targetEvent: ev,
				      clickOutsideToClose:true,
				      fullscreen: $scope.customFullscreen
				    });
			};
				
		});