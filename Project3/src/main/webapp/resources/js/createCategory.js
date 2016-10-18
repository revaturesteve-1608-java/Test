/**
 * 
 */


angular.module('createCategory', [])

.controller("createCatCtrl", function($scope, catService){
	
	$scope.createCat = function(catName){
		catService.createCat(catName, function(response){
			console.log("got back here!!!")
		})
	}
	
})

.service('catService', function($http){
	
	this.createCat = function(catName, callback){
		console.log("got into the service!!!")
		$http.post("rest/createCategory", catName).then(callback)
	}
})