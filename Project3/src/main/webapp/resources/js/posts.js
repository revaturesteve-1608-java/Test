/**
 * 
 */

angular.module('posts', [])

.controller('postsCtrl', function($scope, postsService, $compile){
	$scope.count=1;
	$scope.addReply = function(userReply, postId){
		console.log("the postId: " + postId)
		console.log("reply: " + userReply)
		var getElem = "." + postId;
		var elem = angular.element(getElem);
		console.log("elements: " + elem)
//		var correctPost = angular.element('#replies #post1')
		
		
//	<div class="row">
//		<div class="col-md-8">
//			Porta ac consectetur ac
//		</div>
//		<div class="col-md-4">
//			<button type="button" class="btn btn-default" aria-label="Right Align">
//					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
//			</button>
//		</div>
//	</div>
		
		var newReply = '<div id="singleReply" class="row"> <div class="col-md-8">' + userReply + '</div><div class="col-md-4">'
		+ '<button type="button" aria-label="Right Align"> <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> '
		+ '</button></div></div>'
		var buttonListener = $compile(newReply)($scope);
		elem.append(buttonListener);
		angular.element('#theRepyTextBox #replyText').val("");
	}
	$scope.count = 50;
	$scope.addPost = function(postTitle, postContent){
		$scope.count = $scope.count+1;
//<div id="eachPost"><ul class="list-group"><li class="list-group-item"><div><p id="theUsername">{{postContainer.authorName}}</p></div></li><li class="list-group-item"><div><p id="thePost">{{postContainer.postContent}}</p></div></li><li class="list-group-item"><div class="{{postContainer.postId}}"><div class="replies" class="row" ><div class="col-md-8">Morbi leo risus</div><div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></button><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div><div id="singleReply" class="row"><div class="col-md-8">Porta ac consectetur ac</div><div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></button><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div></div></li><li class="list-group-item" id="theRepyTextBox"><input id="post" name="post1" type="hidden" ng-init="postId=postContainer.postId;" ng-model="postId"/><input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply1"><button id="reply" type="button" ng-click="addReply(userReply1, postId)"> reply</button></li></ul></div>
	
		
		
		
		var postInfo = [postTitle, postContent/*, $scope.user.username*/]
		postsService.createPost(postInfo);
		
		var elem = angular.element('#newPost');
		var append = '<div id="eachPost"><ul class="list-group"><li class="list-group-item"><div><p id="theUsername">'+'TEMPUSERNAME'+'</p>'
			+'</div></li><li class="list-group-item"><div><p id="thePost">'+ postContent + '</p></div></li><li class="list-group-item">'
			+'<div class="'+ $scope.count + '"><div class="replies" class="row" ><div class="col-md-8">Morbi leo risus</div>'
			+'<div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align">'
			+'<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>'
			+'</button><button type="button" class="btn btn-default" aria-label="Right Align">'
			+'<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div>'
			+'<div id="singleReply" class="row"><div class="col-md-8">Porta ac consectetur ac</div><div class="col-md-4">'
			+'<button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">'
			+'</span></button><button type="button" class="btn btn-default" aria-label="Right Align">'
			+'<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></button></div></div></div></li>'
			+'<li class="list-group-item" id="theRepyTextBox">'
			+'<input id="post" name="post1" type="hidden" ng-init="postId='+ $scope.count + '" ng-model="postId"/>'
			+'<input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply'+ $scope.count + '">'
			+'<button id="reply" type="button" ng-click="addReply(userReply'+ $scope.count + ', postId)"> reply</button></li></ul></div>'
		var addListener = $compile(append)($scope);
		elem.after(addListener);
		angular.element('#newPost #postContent').val("");
	}
	
	
//	$scope.user;
//	$scope.getUser = postsService.getUser(function(response){
//		$scope.user = response.data;
//		console.log($scope.user);
//		
//	})
	
	$scope.getPosts = postsService.getPosts(function(response){
		$scope.allPosts = response.data;
	})
})

.service('postsService', function($http){
	
	this.createPost = function(postInformation){
		console.log('GOT INTO SERVICE')
		$http.post("rest/createPost", postInformation).then()
	}
	
//	this.getUser = function(callback){
//		$http.get('rest/user').then(callback)
//	}
	
	this.getPosts = function(callback){
		$http.get("rest/getPosts").then(callback)
	}
	
})