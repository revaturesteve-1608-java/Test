/**
 * 
 */

angular.module('posts', ['textAngular', 'infinite-scroll'])

.controller('postsCtrl', function($scope, postsService, $compile){
	
	$scope.orightml = '<p><i><b>What would you like to ask your colleagues?</b></i></p>';
	$scope.htmlcontent = $scope.orightml;
	$scope.emptyhtml = '';
	$scope.disabled = false;
	console.log($scope.htmlcontent);
	
	$scope.count=1;
	$scope.addReply = function(userReply, postId){
		console.log("the postId: " + postId)
		console.log("reply: " + userReply)
		var getElem = "." + postId;
		var elem = angular.element(getElem);
		console.log("elements: " + elem)
		var replyInfo = [userReply, postId]
		postsService.createReply(replyInfo)
		
		var newReply = '<div id="singleReply" class="row"> <div class="col-md-8">' + userReply + '</div><div class="col-md-4">'
		+ '<button type="button" aria-label="Right Align"> <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> '
		+ '</button></div></div>'
		var buttonListener = $compile(newReply)($scope);
		elem.append(buttonListener);
		angular.element('#theRepyTextBox #replyText').val("");
	}
	$scope.addPost = function(postTitle, postContent){
		
		var postInfo = [postTitle, postContent/*, $scope.user.username*/]
		console.log(postContent)
		postsService.createPost(postInfo, function(response){
			var postId = response.data;
			console.log("postId in the controller: " + postId);
			
			var elem = angular.element('#newPost');
			var append = '<div id="eachPost"><ul class="list-group"><li class="list-group-item"><div><p id="theUsername">'+'TEMPUSERNAME'+'</p>'
				+'</div></li><li class="list-group-item"><div><p id="thePost">'+ postContent + '</p></div></li><li class="list-group-item">'
				+'<div class="'+ postId + '"><div class="replies" class="row" ><div class="col-md-8">Morbi leo risus</div>'
				+'<div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align">'
				+'<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>'
				+'</button><button type="button" class="btn btn-default" aria-label="Right Align">'
				+'<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div>'
				+'<div id="singleReply" class="row"><div class="col-md-8">Porta ac consectetur ac</div><div class="col-md-4">'
				+'<button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">'
				+'</span></button><button type="button" class="btn btn-default" aria-label="Right Align">'
				+'<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div></div></li>'
				+'<li class="list-group-item" id="theRepyTextBox">'
				+'<input id="post" name="post1" type="hidden" ng-init="postId='+ postId + '" ng-model="postId"/>'
				+'<input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply'+ postId + '">'
				+'<button id="reply" type="button" ng-click="addReply(userReply'+ postId + ', postId)"> reply</button></li></ul></div>'
			var addListener = $compile(append)($scope);
			elem.after(addListener);
			angular.element('#newPost #postContent').val("");	
		});
	}
	
	$scope.user;
	$scope.getUser = postsService.getUser(function(response){
		$scope.user = response.data;
		console.log($scope.user);
		
	})
	
	$scope.getPosts = postsService.getPosts(function(response){
		$scope.allPosts = response.data;
	})
})

.service('postsService', function($http){
	
	this.createPost = function(postInformation, callback){
		console.log('GOT INTO SERVICE')
		$http.post("rest/createPost", postInformation).then(callback)
	}
	
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
	
	this.getPosts = function(callback){
		$http.get("rest/getPosts").then(callback)
	}
	
	this.createReply = function(replyInfo){
		$http.post("rest/createReply", replyInfo).then()
	}
})