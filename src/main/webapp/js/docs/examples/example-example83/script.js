(function(angular) {
  'use strict';
angular.module('includeExample', ['ngAnimate'])
  .controller('ExampleController', ['$scope', function($scope) {
    $scope.templates =
      [ { name: 'template1.jsp', url: 'template1.jsp'},
        { name: 'template2.jsp', url: 'template2.jsp'} ];
    $scope.template = $scope.templates[0];
  }]);
})(window.angular);