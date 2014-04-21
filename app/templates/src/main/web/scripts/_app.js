'use strict';

angular.module('<%= _.camelize(baseName) %>App', [
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngRoute',
        'ui.bootstrap',
        'snap'
    ])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/dashboard', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/users', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .otherwise({
                redirectTo: '/dashboard'
            });
    }]);
