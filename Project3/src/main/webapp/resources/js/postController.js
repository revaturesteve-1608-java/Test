/**
 * 
 */
var app = angular.module('routingApp');
app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('dark-grey').backgroundPalette('red');
	})
app.controller("postCtrl", function($scope, $location, $http) {
	
	$scope.currentUrl = $location.search();
	
	console.log($scope.currentUrl.id);
	
	var info = $scope.currentUrl.id; 
	
	$scope.post;
	
    $scope.dislikesArray;
	
    $scope.likesArray;
    
	$scope.string;
	
	$scope.replys;
	
	$scope.replyToPost = function(replyContext, user) {
		
		var replyInfo = [replyContext, info, user.username];
		
		console.log(replyInfo);
		
		//postsService.createReply(replyInfo);
		$http.post("rest/createReply", replyInfo).then();
		
	}
	
	$http({method: 'POST', url: 'rest/getPostById', data: $.param({id:info}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
	.success(function(data, status, headers, config) {
		$scope.post = data;
		$scope.replys = $scope.post.replyContent;
		console.log($scope.post)
	})
	.error(function(data, status, headers, config) {
	//alert( "failure message: " + JSON.stringify({data: data}));
	});
	
	$http({method: 'POST', url: 'rest/getDislike', data: $.param({id: info}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
	.success(function(data, status, headers, config) {
		$scope.dislikesArray = data;
		console.log("size-----------------------------------------" + $scope.dislikesArray);
	})
	.error(function(data, status, headers, config) {
	alert( "failure message: " + JSON.stringify({data: data}));
	});
	
	$http({method: 'POST', url: 'rest/getLike', data: $.param({id: info}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
	.success(function(data, status, headers, config) {
		$scope.likesArray = data;
		console.log("size-----------------------------------------" + $scope.likesArray);
	})
	.error(function(data, status, headers, config) {
	alert( "failure message: " + JSON.stringify({data: data}));
	});
	
	$scope.dislike = function(user, postId) {
		console.log(user.username + "--------------------" + postId.postId);
		$http({method: 'POST', url: 'rest/dislike', data: $.param({username: user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			$scope.string = data;
			$scope.dislikesArray = $scope.dislikesArray + 1;
			console.log("-------------------------size---------------------------------------" + $scope.string)
		})
		.error(function(data, status, headers, config) {
		//alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
	
	$scope.like = function(user, postId) {
		$http({method: 'POST', url: 'rest/like', data: $.param({username:user.username, id: postId.postId}), headers: {'Content-Type': 'application/x-www-form-urlencoded'} })
		.success(function(data, status, headers, config) {
			$scope.string = data;
			$scope.likesArray = $scope.likesArray + 1;
			console.log($scope.string)
		})
		.error(function(data, status, headers, config) {
		//alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
	
});