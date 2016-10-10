/**
 * 
 */

angular.module('posts', [])

.controller('postsCtrl', function($scope, postsService, $compile){
	$scope.count=1;
	$scope.addReply = function(userReply, postId){
		var getElem = "#replies" + postId;
		var elem = angular.element(getElem);
//		var correctPost = angular.element('#replies #post1')
		console.log("the postId: " + postId)
		console.log("reply: " + userReply)
		
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
	$scope.addPost = function(postTitle, postContent){
		
//		<div id="eachPost">
//		<input id="post1" name="post1" type="hidden" value="Joey" />
//		<ul class="list-group">
//			<li class="list-group-item"><div><p id="theUsername">Username</p></div> </li>
//			<li class="list-group-item"><div><p id="thePost">HOWOWOWO</p></div></li>
//			<li class="list-group-item">
//				<button type="button" class="btn btn-default" aria-label="Right Align">
//  					<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
//				</button>
//				<button type="button" class="btn btn-default" aria-label="Right Align">
//  					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
//				</button>
//			</li>
//			
//			<li class="list-group-item">Morbi leo risus</li>
//			<li class="list-group-item" id="last">Porta ac consectetur ac</li>
//			
//			
//			<li class="list-group-item" id="theRepyTextBox"><input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply"> <button id="reply" type="button" ng-click="addReply(userReply)"> reply</button></li>
//		</ul>
//	</div>
		
		
//		<li class="list-group-item" id="replies">
//		</li>
		
		
//		<div class="row"><div class="col-md-8"><p id="theUsername">Username</p></div><div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button><button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></div></div>
		
		var postInfo = [postTitle, postContent, $scope.user.username]
		postsService.createPost(postInfo);
		
		var elem = angular.element('#newPost');
		$scope.count = $scope.count+1;
		var append = '<div id="eachPost"><ul class="list-group"> <li class="list-group-item"><div class="row"><div class="col-md-8">'
			+ '<p id="theUsername">Username</p></div><div class="col-md-4"><button type="button" class="btn btn-default" aria-label="Right Align">'
			+ '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>'
			+ '<button type="button" class="btn btn-default" aria-label="Right Align"><span class="glyphicon glyphicon-remove" aria-hidden="true">'
			+ '</span></button></div></div> </li>  <li class="list-group-item"><div><p id="thePost">' + postContent + '</p></div></li> '
			+ '<li class="list-group-item" id="replies' + $scope.count + '"></li> <li class="list-group-item" id="theRepyTextBox">'
			+ '<input id="post" name="post1" type="hidden" ng-init="postId' + $scope.count + '=' + $scope.count + '" ng-model="postId' + $scope.count + '"/>'
			+ '<input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply' + $scope.count + '"> '
			+ '<button id="reply" type="button" ng-click="addReply(userReply' + $scope.count + ', postId' + $scope.count + ')"> reply</button>  </ul></div>'
		var addListener = $compile(append)($scope);
		elem.after(addListener);
		angular.element('#newPost #postContent').val("");
	}
	
	
	$scope.user;
	$scope.getUser = postsService.getUser(function(response){
		$scope.user = response.data;
		console.log($scope.user);
		
	})
	
	$scope.getPosts = postsService.getPosts(function(response){
		console.log("came all the way back")
	})
})

.service('postsService', function($http){
	
	this.createPost = function(postInformation){
		console.log('GOT INTO SERVICE')
		$http.post("rest/createPost", postInformation).then()
	}
	
	this.getUser = function(callback){
		$http.get('rest/user').then(callback)
	}
	
	this.getPosts = function(callback){
		$http.get('/rest/getPosts').then(callback)
	}
	
})