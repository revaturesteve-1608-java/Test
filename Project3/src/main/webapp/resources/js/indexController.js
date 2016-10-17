/**
 * 
 */
var app = angular.module('routingApp');
app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('custom').backgroundPalette('blue');
	})
app.controller("indexCtrl", function($scope, $http, $window, $cookies, createUserService, $mdDialog) {
	
	$scope.user;
	
	console.log($scope.user);
	
	
	
	$scope.logout = function(){
		
		
		createUserService.logout();
	//	$window.location.href = 'login.html';
		
	}
	
	$scope.getUser = createUserService.getUser(
			function(response){
				
				
				$scope.user = response.data; 	
				
				if($scope.user.id == 0){
					
					$window.location.href = 'index.jsp';
					
					
				}
				
				console.log($scope.user);
				
			})
	
	
	$scope.allPosts;
			
	$scope.getPost = createUserService.getPosts(function(response){
		$scope.allPosts = response.data;
		console.log($scope.allPosts);
	})
			
			
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

})