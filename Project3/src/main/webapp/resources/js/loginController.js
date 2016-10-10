/**
 * 
 */
angular.module('loginApp').controller('loginController',function($scope,  $mdDialog, $http, $window) {

			/*$scope.loginUser = function(person) {
				loginService.loginUser(person);
			}*/

			$scope.status = '  ';
			$scope.customFullscreen = false;
			
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
			
			$scope.signIn = function(person) {
				
				$http.post('rest/login', person).then(function(response) {
					
					//	console.log(typeof response.data);
					//	console.log(response.data);
						var person = response.data;
						
						console.log(person);
						
						customAlert();
						
						
						if(person.vaildated) {
							$window.location.href = 'index.html';
						} else {
							$window.location.href = 'updateTempInfo.html';
						}
						
						
					}, function(error) {
						
					})
			
		}
			
			 function showAlert() {
			    
				 alert = $mdDialog.alert({
				        title: 'User is not found',
				        textContent: 'Please try again',
				        ok: 'Ok'
				      });

				      $mdDialog
				        .show( alert )
				        .finally(function() {
				          alert = undefined;
				        });
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