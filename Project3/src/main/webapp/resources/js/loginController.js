/**
 * 
 */
angular.module('loginApp').controller('loginController',
		function($scope, loginService) {

			$scope.loginUser = function(person) {
				loginService.loginUser(person);
			}

			$scope.user;

			$scope.getUser = loginService.getUser(function(response) {
				console.log(response);
				console.log(response.data)
				$scope.user = response.data;
			})

		});