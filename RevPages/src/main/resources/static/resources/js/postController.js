/**
 * 
 */
var app = angular.module('routingApp');
app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('dark-grey').backgroundPalette('red');
	})
app.controller("postCtrl", function($scope, $location, $http, createUserService, $filter, $route) {
	
	$scope.currentUrl = $location.search();
	
	console.log($scope.currentUrl.id);
	
	var info = $scope.currentUrl.id; 
	
	$scope.post;
	
    $scope.dislikesArray;
	
    $scope.likesArray;
    
	$scope.string;
	
	$scope.replys;
	
	$scope.dislikesReplysContainer;
	
	$scope.likesReplysContainer;
	
	$scope.person;
	
	createUserService.getUser(function(response){
		$scope.person = response.data
	})
	
	$scope.likeReply = function(user, id, index) {
		console.log(id);
		$http({method: 'POST', url: '/likeReply', data: $.param({username: user.username, id: id}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			
			$scope.likesReplysContainer[index] = data;
			
			createUserService.getAllReplyDislikes(info, function(response){
				$scope.dislikesReplysContainer = response.data;
			});
		})
		.error(function(data, status, headers, config) {
		});
		
	}
	
	$scope.dislikeReply = function(user, id, index) {
		console.log(id);
		$http({method: 'POST', url: '/dislikeReply', data: $.param({username: user.username, id: id}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			
			$scope.dislikesReplysContainer[index] = data;
			
			createUserService.getAllReplyLikes(info, function(response){
				$scope.likesReplysContainer = response.data;
			});
			
		})
		.error(function(data, status, headers, config) {
		
		});
	}
	
	
	$scope.startReply = function() {
		var currentElement = angular.element('#replyContainer');
		$scope.myReply = !$scope.myReply;
		
	}
	
	$scope.deletePost = function(id) {
		createUserService.deletePost(id);
	}
	
	$scope.replyToPost = function(replyContext, user) {
		
		
		$scope.myReply = !$scope.myReply;
		
		var replyInfo = [replyContext, info, user.username];
		
		
		
		console.log(replyInfo);
		
		var day = $filter('date')(new Date(), "MMM d, y");
		$http.post("/createReply", replyInfo).then(function(response) {
			$route.reload();
		});
		
		$scope.replyContext="";
		
	}
	
	
	$http({method: 'POST', url: '/getPostById', data: $.param({id:info}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
	.success(function(data, status, headers, config) {
		$scope.post = data;
		$scope.replys = $scope.post.replyContent;
		console.log($scope.post)
		if(($scope.user.username === $scope.post.authorName) || $scope.user.role.id == 1) {
			$scope.userRole = !$scope.userRole;
		}
	})
	.error(function(data, status, headers, config) {
	});
	
	createUserService.getDislike(info, function(response) {
		$scope.dislikesArray = response.data;
	});
	
	createUserService.getAllReplyDislikes(info, function(response){
		$scope.dislikesReplysContainer = response.data;
	});
	
	
	createUserService.getLike(info, function(response) {
		$scope.likesArray = response.data;
	});
	
	createUserService.getAllReplyLikes(info, function(response){
		$scope.likesReplysContainer = response.data;
	});
	
	$scope.dislike = function(user, postId) {
		$http({method: 'POST', url: '/dislike', data: $.param({username: user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			
			$scope.dislikesArray = data;
			
			createUserService.getLike(info, function(response) {
				$scope.likesArray = response.data;
			});
		})
		.error(function(data, status, headers, config) {
		
		});
	}
	
	$scope.like = function(user, postId) {
		$http({method: 'POST', url: '/like', data: $.param({username:user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			$scope.likesArray = data;
			
			createUserService.getDislike(info, function(response) {
				$scope.dislikesArray = response.data;
			});
		})
		.error(function(data, status, headers, config) {
		
		});
	}
});
