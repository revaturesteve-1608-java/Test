/**
 * 
 */

angular.module('routingApp')

.controller('updateUserCtrl', function($scope, updateUserData, $window, createUserService){
	console.log('FIRST IN CONTROLLER')
	$scope.updateInformation = function(oldPassword, newPassword, username, newEmail, newPhone, 
			complex, newUniversity, newLinkedIn){
		console.log('EVENT LISTENER WORKED')
		console.log("oldpass: " + oldPassword);
		console.log("newpass: " + newPassword);
		var complexN = "";
		if(complex.complexName != null) {
			complexN = complex.complexName.complexName;
		}
		var information = [oldPassword, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn, 
		                   complexN]
		console.log(information);
		updateUserData.update(information, function(response){
			$window.alert(response.data);
			createUserService.getUser(
					function(response){
//						console.log(response);
//						console.log(response.data) 
					//	console.log(typeof response.data[0].maker);
						$scope.user = response.data; 	
//						console.log($scope.user);
					})
			
		});
	}
	
	$scope.getComplex = updateUserData.getComplex(
			// pass in the callback function
			function(response) {
				$scope.complex = response.data;
			})
	
//	$scope.user;
//	
//	$scope.getUser = updateUserData.getUser(function(response){
//		$scope.user = response.data;
//		console.log($scope.user);
//		
//	})
})

.directive('ngFiles', ['$parse', function ($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };

    return {
        link: fn_link
    }
} ])

.controller('fupController', function ($scope, $http, $window, updateUserData) {
	
    var formdata = new FormData();
    $scope.getTheFiles = function ($files) {
    	console.log($files);
    	console.log($files[0].type.includes("image"));
    	if($files[0].type.includes("image")) {
	        angular.forEach($files, function (value, key) {
	        	formdata.append(key, value);
	            console.log(key + ' ' + value.name);
	            console.log(formdata.get(0));
	        });
    	} else {
    		$window.alert("Only image");
    	}
    	console.log(formdata.get(0));
    };

    // NOW UPLOAD THE FILES.
    $scope.uploadFiles = function () {
//    	updateUserData.updatePics(formdata);
    	console.log(formdata.get(0));
        var request = {
            method: 'POST',
            url: 'rest/fileupload',
            data: formdata,
            fileElementId : 'file',
            headers: {
                'Content-Type': undefined,
                contentType: false,
				processData: false,
            }
        };

        // SEND THE FILES.
        $http(request)
            .success(function (d) {
                alert(d);
            })
            .error(function () {
            });
    }
})

.service('updateUserData', function($http, $window, $q, $route){
	this.update = function(information, callback){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateInfo", information).then(callback);
	}
	
	this.updatePics = function(formdata){
		console.log('GOT INTO SERVICE')
		$http.post("rest/fileupload", formdata).then(function(response) {
//			$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.getComplex = function(callback) {
		// callback is a function that takes a response
		$http.post('rest/getComplex').then(callback);
	}
})

