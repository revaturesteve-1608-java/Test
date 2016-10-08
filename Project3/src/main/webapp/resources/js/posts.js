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
		
		var newReply = '<div id="singleReply" class="row"> <div class="col-md-8">' + userReply + '</div><div class="col-md-4"><button type="button" aria-label="Right Align"> <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> </button></div></div>'
		var buttonListener = $compile(newReply)($scope);
		elem.append(buttonListener);
		angular.element('#theRepyTextBox #replyText').val("");
	}
	$scope.addPost = function(newPost){
		var elem = angular.element('#newPost');
		$scope.count = $scope.count+1;
		
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
		
		var append = '<div id="eachPost"><ul class="list-group"> <li class="list-group-item"><div><p id="theUsername">Username</p></div> </li>  <li class="list-group-item"><div><p id="thePost">' + newPost + '</p></div></li> <li class="list-group-item" id="replies' + $scope.count + '"></li> <li class="list-group-item" id="theRepyTextBox"><input id="post" name="post1" type="hidden" ng-init="postId' + $scope.count + '=' + $scope.count + '" ng-model="postId' + $scope.count + '"/> <input class="form-group" id="replyText" type="text" placeholder="reply" ng-model="userReply' + $scope.count + '"> <button id="reply" type="button" ng-click="addReply(userReply' + $scope.count + ', postId' + $scope.count + ')"> reply</button>  </ul></div>'
		var addListener = $compile(append)($scope);
		elem.after(addListener);
		angular.element('#newPost #postContent').val("");
	}
})

.service('postsService', function($http){
	
})