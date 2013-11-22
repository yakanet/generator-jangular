'use strict';

angular.module('<%= _.camelize(baseName) %>App')

    .controller('MainCtrl', function ($scope, $resource) {
        $scope.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];

        $resource('rest/hello').get(function (d) {
            $scope.info = d;
        });
    });
