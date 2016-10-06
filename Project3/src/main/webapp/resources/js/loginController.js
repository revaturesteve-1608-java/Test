/**
 * 
 */
angular.module('loginApp').controller('loginController',
		function($scope, loginService) {

			$scope.loginUser = function(person) {
				loginService.loginUser(person);
			}

			
			

		});