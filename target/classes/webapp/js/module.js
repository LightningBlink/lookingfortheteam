var app = angular.module('myApp', ['ngWebSocket', 'projectFilters', 'flow', 'ui-notification', 'ui.bootstrap', 'ngRoute', 'ngCookies', 'ngSanitize',
    //Controllers
    'newsControllers',
    'lookingTeamControllers',
    'profileControllers',
    'messagesController',
    //Services
    'AllServices'
])
    .config(['flowFactoryProvider', '$httpProvider', function (flowFactoryProvider, $httpProvider) {

        $httpProvider.interceptors.push(function($q, $injector) {
            return {
                'responseError': function(rejection) {
                    if(rejection.data && rejection.data.ex) {
                        $injector.get('Notification').error(rejection.data.ex);
                    }
                    return $q.reject(rejection);
                }
            };
        });

        $httpProvider.interceptors.push('httpErrorResponseInterceptor');

        flowFactoryProvider.defaults = {
            target: '/user/uploadavatar',
            testChunks: false,
            chunkSize: 1024 * 32,
            permanentErrors: [404, 500, 501],
            maxChunkRetries: 3,
            chunkRetryInterval: 5000,
            simultaneousUploads: 3,
            singleFile: true
        };
        flowFactoryProvider.on('catchAll', function (event) {
            console.log('catchAll', arguments);
        });

    }]);

app.filter('unique', function () {
    return function ( collection, keyname) {
        var output = [],
            keys = [];
        var found = [];

        if (!keyname) {

            angular.forEach(collection, function (row) {
                var is_found = false;
                angular.forEach(found, function (foundRow) {

                    if (foundRow == row) {
                        is_found = true;
                    }
                });

                if (is_found) { return; }
                found.push(row);
                output.push(row);

            });
        }
        else {

            angular.forEach(collection, function (row) {
                var item = row[keyname];
                if (item === null || item === undefined) return;
                if (keys.indexOf(item) === -1) {
                    keys.push(item);
                    output.push(row);
                }
            });
        }

        return output;
    };
});
