/**
 * 
 */

angular.module('routingApp')

.controller('postsCtrl', function($scope, postsService, $compile, $route, $window){
	
	$scope.orightml = '<p><i><b>What would you like to ask your colleagues?</b></i></p>';
	$scope.htmlcontent = $scope.orightml;
	$scope.emptyhtml = '';
	$scope.disabled = false;
	console.log($scope.htmlcontent);
	$scope.addReply = function(userReply, postId){
		var getElem = "." + postId;
		var elem = angular.element(getElem);
		var replyInfo = [userReply, postId, $scope.user.username]
		postsService.createReply(replyInfo)
		
		var newReply = '<div id="singleReply" class="row"> <div id="username"></div> <div class="col-md-8">' + userReply + '</div>'
		
		var buttonListener = $compile(newReply)($scope);
		elem.append(buttonListener);
		angular.element('#theRepyTextBox #replyText').val("");
	}
	$scope.addPost = function(postTitle, postContent, category){
		
		var postInfo = [postTitle, postContent, $scope.user.username, category]
		postsService.createPost(postInfo, function(response){
			var postId = response.data;
			
			var elem = angular.element('#filterSearch');
			var append = '<div id="eachPost"><ul class="list-group">'
				                +'<li class="list-group-item"> <div id="titleDiv"><div class="col-md-8"><h4><a href="#post?id=' + postId + '"><b>' + postTitle + '</b></a></h4></div><div class="col-md-4"><button id="deleteBtn" type="button" class="btn btn-default" aria-label="Right Align" ng-click="deletePost(postId)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></div> </li><li class="list-group-item"><div><p id="thePost">'+ postContent + '</p></div></li><li class="list-group-item">'
				                +'<div class="'+ postId + '"><div class="replies" class="row" ></div></div></li>'
				                +'<li class="list-group-item" id="theRepyTextBox">'
				                +'<input id="post" name="post1" type="hidden" ng-init="postId='+ postId + '" ng-model="postId"/>'
				                +'<form><fieldset><div class="col-md-6"><input class="form-control input-sm" id="replyText" type="text" placeholder="reply" ng-model="userReply'+ postId + '"></div>'
				                +'<div class="col-md-6"><button id="reply" class="btn btn-primary" type="button" ng-click="addReply(userReply'+ postId + ', postId)"> reply</button></div></fieldset></form></li></ul></div>'
			var addListener = $compile(append)($scope);
			elem.after(addListener);
			angular.element('#newPost #postContent').val("");	
		});
	}
	
	$scope.user;
	$scope.getUser = postsService.getUser(function(response){
		$scope.user = response.data;
		console.log($scope.user.username);
		postsService.getAllCategories(function(response){
			$scope.allCategories = response.data;
			var newArray = $scope.allCategories.slice(0); //clone the array, or you'll end up with a new "None" option added to your "values" array on every digest cycle.
	        newArray.unshift("Show all");
	        $scope.allCategories = newArray
		})
		postsService.getPostsByUsername($scope.user.username, function(response){
			$scope.allPosts = response.data;
			$scope.length = $scope.allPosts.length;
			$scope.firstResult = $scope.allPosts.length;
		})
	})
	
	$scope.firstResult = 0;
	
	$scope.getMorePosts = function(firstResult){
		postsService.getMorePosts(firstResult, function(response){
			$scope.allPosts = response.data;
			$scope.firstResult = $scope.allPosts.length;
		})
	}
	
	$scope.deletePost = function(postId){
		postsService.deletePost(postId, function(response){
			$window.location.reload();
		});
	}
	
	$scope.getPostsByCategory = function(catName){
		if(catName === "Show all"){
			postsService.getPostsByUsername($scope.user.username, function(response){
				$scope.allPosts = response.data;
				$scope.$apply()
			})
		} else{
			var info = [catName, $scope.user.username]
			postsService.getPostsByCategory(info, function(response){
				$scope.allPosts = response.data;
				$scope.$apply()
			})
		}
	}
})

.service('postsService', function($http){
	
	this.createPost = function(postInformation, callback){
		$http.post("rest/createPost", postInformation).then(callback)
	}
	
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
	
	this.getPostsByUsername = function(username, callback){
		$http.post("rest/getPostsByUsername", username).then(callback)
	}
	
	this.getMorePosts = function(firstResult, callback){
		$http.get("rest/getMorePosts", firstResult).then(callback)
	}
	
	this.createReply = function(replyInfo){
		console.log("here i am");
		$http.post("rest/createReply", replyInfo).then();
	}
	
	this.deletePost = function(postId, callback){
		$http.post("rest/deletePost", postId).then(callback);
	}
	
	this.getPostsByCategory = function(info, callback){
		$http.post("rest/getPostsCatProf", info).then(callback);
	}
	
	this.getAllCategories = function(callback){
		$http.post("rest/getAllCat").then(callback);
	}
})