//Angular code

'use strict';

/* App Module */

var onsApp = angular.module('onsApp', [
  'ngRoute',
  'onsControllers'
]);

onsApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/', {
      redirectTo: '/home'
    }).
    when('/searchresults', {
      templateUrl: 'templates/searchresults.html'
    }).
    when('/release', {
        templateUrl: 'templates/release.html'
      }).    
    otherwise({
      templateUrl: 'templates/template.html'
    });
  }
]);

onsApp.filter('slice', function() {
  return function(arr, start, end) {
      return arr.slice(start, end);
  };
});



onsApp.factory('Page', function() {
  var title = 'default';
  return {
    title: function() {
      return title;
    },
    setTitle: function(newTitle) {
      title = newTitle
    }
  };
});
