'use strict';

var tempApp = angular.module('tempApp');
tempApp.controller('MainCtrl', ['$scope', '$resource', function ($scope, $resource) {
        $scope.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];
        
        $resource('rest/hello').get(function(d){
        	$scope.info = d;
        });
    }]);
