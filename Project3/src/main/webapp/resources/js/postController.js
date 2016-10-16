/**
 * 
 */
var app = angular.module('routingApp');
app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('dark-grey').backgroundPalette('red');
	})
app.controller("postCtrl", function($scope, $location, $http, createUserService, $filter) {
	
	$scope.currentUrl = $location.search();
	
	console.log($scope.currentUrl.id);
	
	var info = $scope.currentUrl.id; 
	
	$scope.post;
	
    $scope.dislikesArray;
	
    $scope.likesArray;
    
	$scope.string;
	
	$scope.replys;
	
	$scope.startReply = function() {
		var currentElement = angular.element('#replyContainer');
		
		$scope.myReply = !$scope.myReply;
		
		/*var newReply = '<label>Enter your reply</label><input type="text" ng-model="replyContext"></input>';
	//	var submitReply = '<button  ng-show="myReply" ng-click="replyToPost(replyContext, user)">reply</button>';
		currentElement.append(newReply);
	//	currentElement.append(submitReply);
*/	}
	
	$scope.replyToPost = function(replyContext, user) {
		
		console.log("here!")
		$scope.myReply = !$scope.myReply;
		
		var replyInfo = [replyContext, info, user.username];
		
		
		
		console.log(replyInfo);
		
		var day = $filter('date')(new Date(), "MMM d, y");
		
		//postsService.createReply(replyInfo);
		$http.post("rest/createReply", replyInfo).then(function(response) {
			var container = angular.element("#replys");
			
			var context = '<md-card><md-card-title><md-card-title-text><span class="md-headline">' + replyContext +
			'</span><span class="md-subhead">' + user.username + ' ' + day + '</span></md-card-title-text></md-card-title></md-card';
			
			container.append(context);
		});
		
		$scope.replyContext="";
		
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
	
	createUserService.getDislike(info, function(response) {
		$scope.dislikesArray = response.data;
		console.log("size-----------------------------------------" + $scope.dislikesArray);
	});
	
	
	createUserService.getLike(info, function(response) {
		$scope.likesArray = response.data;
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
		//alert( "failure message: " + JSON.stringify({data: data}));
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
		//alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
	
});
