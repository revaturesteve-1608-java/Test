/**
 * 
 */

angular.module('routingApp')

.controller('postsCtrl', function($scope, postsService, $compile){
	
	$scope.orightml = '<p><i><b>What would you like to ask your colleagues?</b></i></p>';
	$scope.htmlcontent = $scope.orightml;
	$scope.emptyhtml = '';
	$scope.disabled = false;
	console.log($scope.htmlcontent);
	$scope.addReply = function(userReply, postId){
		console.log("the postId: " + postId)
		console.log("reply: " + userReply)
		var getElem = "." + postId;
		var elem = angular.element(getElem);
		console.log("elements: " + elem)
		var replyInfo = [userReply, postId, $scope.user.username]
		postsService.createReply(replyInfo)
		
		var newReply = '<div id="singleReply" class="row"> <div id="username"></div> <div class="col-md-8">' + userReply + '</div><div class="col-md-4">'
        + '<button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></button>'
        +'<button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button>'
		
		var buttonListener = $compile(newReply)($scope);
		elem.append(buttonListener);
		angular.element('#theRepyTextBox #replyText').val("");
	}
	$scope.addPost = function(postTitle, postContent){
		
		var postInfo = [postTitle, postContent, $scope.user.username]
		console.log(postContent)
		console.log("username: " + $scope.user.username)
		postsService.createPost(postInfo, function(response){
			var postId = response.data;
			console.log("postId in the controller: " + postId);
			
			
//			<div id="titleDiv"><div class="col-md-8"><h4><b>{{postContainer.postTitle}}</b></h4></div><div class="col-md-4"><button id="deleteBtn" type="button" class="btn btn-default" aria-label="Right Align" ng-click="deletePost(postId)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></div></div>
		
			
			
			var elem = angular.element('#newPost');
			var append = '<div id="eachPost"><ul class="list-group">'
				                +'<li class="list-group-item"> <div id="titleDiv"><div class="col-md-8"><h4><b>' + postTitle + '</b></h4></div><div class="col-md-4"><button id="deleteBtn" type="button" class="btn btn-default" aria-label="Right Align" ng-click="deletePost(postId)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></div> </li><li class="list-group-item"><div><p id="thePost">'+ postContent + '</p></div></li><li class="list-group-item">'
				                +'<div class="'+ postId + '"><div class="replies" class="row" ></div></div></li>'
				                +'<li class="list-group-item" id="theRepyTextBox">'
				                +'<input id="post" name="post1" type="hidden" ng-init="postId='+ postId + '" ng-model="postId"/>'
				                +'<input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply'+ postId + '">   '
				                +'<button id="reply" class="btn btn-primary" type="button" ng-click="addReply(userReply'+ postId + ', postId)"> reply</button></li></ul></div>'
			var addListener = $compile(append)($scope);
			elem.after(addListener);
			angular.element('#newPost #postContent').val("");	
		});
	}
	
	$scope.user;
	$scope.getUser = postsService.getUser(function(response){
		$scope.user = response.data;
		console.log($scope.user.username);
		console.log("got back from getting a user!!!!!!!!!!!!!!!!!!!!!!!!!1")
		postsService.getPostsByUsername($scope.user.username, function(response){
			console.log("username in the beginning: " + $scope.user.username)
			$scope.allPosts = response.data;
			$scope.length = $scope.allPosts.length;
			$scope.firstResult = $scope.allPosts.length;
			console.log("length: " + $scope.length)
		})
		
		
	})
	
	$scope.firstResult = 0;
	
//	$scope.getPosts = postsService.getPosts(function(response){
//		$scope.allPosts = response.data;
//		$scope.length = $scope.allPosts.length;
//		$scope.firstResult = $scope.allPosts.length;
//		console.log("length: " + $scope.length)
//	})
	
	$scope.getMorePosts = function(firstResult){
		console.log("event hit!!!!!!!!!!!!")
		postsService.getMorePosts(firstResult, function(response){
			$scope.allPosts = response.data;
			$scope.firstResult = $scope.allPosts.length;
		})
	}
	
	$scope.deletePost = function(postId){
//		console.log("GETTING IN HERERERERERE: " + authorName)
		console.log("post id in the delete: " + postId)
		postsService.deletePost(postId);
	}
})

.service('postsService', function($http){
	
	this.createPost = function(postInformation, callback){
		
		$http.post("rest/createPost", postInformation).then(callback)
	}
	
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
	
//	this.getPosts = function(callback){
//		$http.get("rest/getPosts").then(callback)
//	}
	
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
	
	this.deletePost = function(postId){
		$http.post("rest/deletePost", postId).then();
	}
})