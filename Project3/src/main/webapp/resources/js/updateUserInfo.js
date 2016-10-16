/**
 * 
 */

angular.module('routingApp')

.controller('updateUserCtrl', function($scope, updateUserData){
	console.log('FIRST IN CONTROLLER')
	$scope.updateInformation = function(oldPassword, newPassword, username, newEmail, newPhone, 
			newUniversity, newLinkedIn){
		console.log('EVENT LISTENER WORKED')
		console.log("oldpass: " + oldPassword);
		console.log("newpass: " + newPassword);
					
		var information = [oldPassword, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn]
		console.log(information);
		updateUserData.update(information);
	}
	
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

.service('updateUserData', function($http, $window, $q){
	this.update = function(information){
		console.log('GOT INTO SERVICE')
		$http.post("rest/updateInfo", information).then(function(response) {
			$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
	
	this.updatePics = function(formdata){
		console.log('GOT INTO SERVICE')
		$http.post("rest/fileupload", formdata).then(function(response) {
//			$window.alert(response.data);
		}, function(error) {
			console.log($q.reject(error));
		});
	}
})

