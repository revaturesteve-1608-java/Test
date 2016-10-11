/**
 * 
 */
angular.module('routingApp').controller('loginController', function($scope, $mdDialog, $http, $window, $cookies) {

//			$scope.loginUser = function(person) {
//				console.log(person);
//				loginService.loginUser(person);
//			}

			$scope.status = '  ';
			$scope.customFullscreen = false;
			
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
							
						
							if(person.role.roleName == "Moderate") {
								$window.location.href = 'moderate-view.html';
							} else {
								$window.location.href = 'index.html';
							}
						} else {
							$window.location.href = 'updateTempInfo.html';
						}
					} else {
						console.log("where2");
						customAlert();
					}
				}, function(error) {
					customAlert();
				})
			
		}
			
			 
			 
			 
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