/**
 * 
 */
angular.module('loginApp').controller('loginController',
		function($scope, loginService) {

			$scope.loginUser = function(person) {
				console.log(person);
				loginService.loginUser(person);
			}
		});