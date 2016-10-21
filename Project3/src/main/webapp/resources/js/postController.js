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
	
	
	
	$scope.likeReply = function(user, id, index) {
		console.log(id);
		$http({method: 'POST', url: 'rest/likeReply', data: $.param({username: user.username, id: id}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			
			$scope.likesReplysContainer[index] = data;
			
			createUserService.getAllReplyDislikes(info, function(response){
				$scope.dislikesReplysContainer = response.data;
				console.log($scope.dislikesReplysContainer);
			});
		})
		.error(function(data, status, headers, config) {
		});
		
	}
	
	$scope.dislikeReply = function(user, id, index) {
		console.log(id);
		$http({method: 'POST', url: 'rest/dislikeReply', data: $.param({username: user.username, id: id}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			
			$scope.dislikesReplysContainer[index] = data;
			
			createUserService.getAllReplyLikes(info, function(response){
				$scope.likesReplysContainer = response.data;
				console.log($scope.likesReplysContainer);
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
		
		//postsService.createReply(replyInfo);
		$http.post("rest/createReply", replyInfo).then(function(response) {
			//var container = angular.element("#theReplies");
			$route.reload();
			
		});
		
		$scope.replyContext="";
		
	}
	
	
	$http({method: 'POST', url: 'rest/getPostById', data: $.param({id:info}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
	.success(function(data, status, headers, config) {
		$scope.post = data;
		console.log('the post id: ' + $scope.post.postContent)
		$scope.replys = $scope.post.replyContent;
		console.log($scope.post)
		console.log($scope.user.role.id)
		if(($scope.user.username === $scope.post.authorName) || $scope.user.role.id == 1) {
			$scope.userRole = !$scope.userRole;
		}
	})
	.error(function(data, status, headers, config) {
	//alert( "failure message: " + JSON.stringify({data: data}));
	});
	
	createUserService.getDislike(info, function(response) {
		$scope.dislikesArray = response.data;
		console.log("size-----------------------------------------" + $scope.dislikesArray);
	});
	
	createUserService.getAllReplyDislikes(info, function(response){
		$scope.dislikesReplysContainer = response.data;
		console.log($scope.dislikesReplysContainer);
	});
	
	
	createUserService.getLike(info, function(response) {
		$scope.likesArray = response.data;
	});
	
	createUserService.getAllReplyLikes(info, function(response){
		$scope.likesReplysContainer = response.data;
		console.log($scope.likesReplysContainer);
	});
	
	$scope.dislike = function(user, postId) {
		console.log(user.username + "--------------------" + postId.postId);
		$http({method: 'POST', url: 'rest/dislike', data: $.param({username: user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
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
		$http({method: 'POST', url: 'rest/like', data: $.param({username:user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			$scope.likesArray = data;
			console.log("size-----------------------------------------" + $scope.likesArray);
			
			createUserService.getDislike(info, function(response) {
				$scope.dislikesArray = response.data;
				console.log("size-----------------------------------------" + $scope.dislikesArray);
			});
		})
		.error(function(data, status, headers, config) {
		
		});
	}
});
