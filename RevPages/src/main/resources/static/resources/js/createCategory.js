/**
 * 
 */


angular.module('createCategory', [])

.controller("createCatCtrl", function($scope, catService){
	
	$scope.createCat = function(catName){
		catService.createCat(catName, function(response){
		})
	}
	
})

.service('catService', function($http){
	
	this.createCat = function(catName, callback){
		$http.post("/createCategory", catName).then(callback)
	}
})