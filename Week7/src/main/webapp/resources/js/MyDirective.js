/**
 * 
 */

angular.module('mainApp')
	//an attribute or element
	.directive('welcome', function() {
		return {
			template: "<h1>Welcome to your very own Soccer Database</h1>",
			//limit it to only element
			restrict: "E"
		};
		
	})