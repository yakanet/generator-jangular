/**
 * Created by yakanet on 21/04/14.
 */
'use strict';

angular.module('<%= _.camelize(baseName) %>App')
    .controller('ApplicationCtrl', function ($scope, $location) {
        $scope.$location = $location;
        $scope.menus = [
            {'title': 'Dashboard', link:'/dashboard'},
            {'title': 'Users', link:'/users'}
        ]
    });