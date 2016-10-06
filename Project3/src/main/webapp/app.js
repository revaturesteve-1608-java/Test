var app = angular.module('routingApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    
    .when('/', {
        templateUrl : 'views/dashboard.html'
        // ,controller : 'homeController'
    })
    
    .when('/userProfile', {
        templateUrl : 'views/userProfile.html',
     //   controller : 'demoCtrl'
    })
    
    .when('/settings', {
        templateUrl : 'views/settings.html'
        // ,controller : 'contactContoller'
    })
    
    .otherwise({
        redirectTo : '/'
    });
})