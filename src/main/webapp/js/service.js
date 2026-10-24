var services = angular.module('AllServices', ['ngResource']);

services.factory('httpErrorResponseInterceptor', ['$q',
    function ($q) {
        return {
            responseError: function error(response) {
                switch (response.staprofiletus) {
                    case 400:
                        var elem = angular.element('#error');
                        elem.find('#error-content').text(response.data.message);
                        elem.modal('toggle');
                        break;
                }

                return $q.reject(response);
            }
        };
    }
]);

services.factory('NewsService', ['$resource',
    function ($resource) {
        return $resource('/new/:action/:id', {action: '@action', id: '@id'}, {
            getAll: {method: 'GET', params: {'action': 'getall'}, isArray: false},
            get: {method: 'GET', params: {'action': 'get', 'id': 'id'}, isArray: false}
        });
    }]);

services.factory('GameService', ['$resource',
    function ($resource) {
        return $resource('/game/:user', {action: '@user'}, {
            getGameByUsername: {method: 'GET', params: {'action': 'user'}, isArray: true}
        });
    }]);

services.factory('MessageService', ['$resource',
    function ($resource) {
        return $resource('/message/:operation/:param', {operation: '@operation', param: '@param'}, {
            sendInvite: {method: 'GET', params: {'operation': 'invite'}},
            setAccession: {method: 'GET', params: {'operation': 'accession'}},
            acceptInvite: {method: 'GET', params: {'operation': 'accept'}},
            declineTeam: {method: 'GET', params: {'operation': 'decline'}}
        });
    }]);


services.factory('TeamService', ['$resource',
    function ($resource) {
        return $resource('/team/:operation/:identifier', {operation: '@operation'}, {
            getLookingGames: {method: 'GET', params: {'operation': 'lookingteam', 'game': 'gameName'}, isArray: true},
            save: {
                method: 'POST',
                params: {'operation': 'save'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },

            getTeams: {method: 'GET', params: {'operation': 'getteams', 'identifier': 'identifier'}, isArray: true},
            getTeam: {method: 'GET', params: {'operation': 'get', 'identifier': 'identifier'}, isArray: false},
            getLookingTeams: {method: 'GET', params: {'operation': 'lookingteams'}},
            leaveTeam: {
                method: 'POST',
                params: {'operation': 'leaveteam'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            addGameToTeam: {
                method: 'POST',
                params: {'operation': 'addgametoteam'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            removeGameFromTeam: {
                method: 'POST',
                params: {'operation': 'removegamefromteam'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        });
    }]);

services.factory('WS', function ($websocket, $rootScope) {
    // Open a WebSocket connection
    return {
        ws: null,
        connect: function () {

            if ($rootScope.connection) {
                $rootScope.connection.close();
            }
            ws = $websocket("ws://" + document.location.host + "/message/" + $rootScope.chat);

            $rootScope.connection = ws;


            ws.onMessage(function (event) {

                if ($rootScope.chatMessages == undefined) {
                    $rootScope.chatMessages = {};
                }

                if($rootScope.chatMessages[$rootScope.chat] == undefined){
                    $rootScope.chatMessages[$rootScope.chat] = [];
                }

                var obj = angular.fromJson(event.data);
                obj.received = Date.now();
                $rootScope.chatMessages[$rootScope.chat].push(obj);


                setTimeout(function () {
                    var objDiv = document.getElementById("chat-div");
                    if(objDiv) {
                        objDiv.scrollTop = objDiv.scrollHeight;
                    }
                }, 100);

            });
            ws.onError(function (event) {
                console.log('connection Error', event);
            });
            ws.onClose(function (event) {
                console.log('connection closed', event);
            });
            ws.onOpen(function () {
                console.log('connection open');
            });

        },
        send: function (message) {
            var messageObj = '{"message":"' + message + '", "sender":"'
                + $rootScope.user.name + '", "received":""}';
            if (angular.isString(messageObj)) {
                ws.send(messageObj);
            }
            else if (angular.isObject(messageObj)) {
                ws.send(JSON.stringify(messageObj));
            }
        },

        close: function () {
            ws.close();
        }
    };
});

services.factory('UserService', ['$resource', function ($resource) {

    return $resource('/user/:action/:name', {action: '@action', name: '@name'},
        {
            changePasswordToServer: {
                method: 'POST',
                params: {'action': 'changepasstoserver'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            getUserByUsername: {
                method: 'GET',
                params: {'action': 'get', 'name': 'name'}
            },
            sendEmailPassword: {
                method: 'GET',
                params: {'action': 'sendpasswordtomail'}
            },
            authenticate: {
                method: 'POST',
                params: {'action': 'loginuser'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },

            registration: {
                method: 'POST',
                params: {'action': 'registration'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },

            info: {
                method: 'GET',
                params: {'action': 'info', 'name': 'name'}
            },

            save: {
                method: 'POST',
                params: {'action': 'save'},
                headers: {'Content-Type': 'application/json'}
            },
            getLookingPlayers: {method: 'GET', params: {'action': 'get', 'name': 'lookingplayers'}}
        }
    );
}]);
