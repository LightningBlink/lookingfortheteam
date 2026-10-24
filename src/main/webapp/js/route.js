app.config(['$locationProvider', '$routeProvider',
        function ($locationProvider, $routeProvider) {
            $locationProvider.html5Mode(true).hashPrefix('!');
            $routeProvider.
                when('/news', {
                    templateUrl: '/news.html',
                    controller: 'MainNewsController'
                }).
                when('/posts', {
                    templateUrl: '/posts.html',
                    controller: 'PostsController'
                }).
                when('/mymessages', {
                    templateUrl: '/mymessages.html',
                    controller: 'MessagesController'
                }).
                when('/looking', {
                    templateUrl: '/looking.html',
                    controller: 'MainLookingTeamController'
                }).
                when('/addnew', {
                    templateUrl: '/admin/addnew.html',
                    controller: 'AddNewController'
                }).
                when('/item/:id', {
                    templateUrl: '/new.html',
                    controller: 'NewController'
                }).
                when('/profile/:username', {
                    templateUrl: '/profile.html',
                    controller: 'MainProfileController'
                }).
                when('/team/:id', {
                    templateUrl: '/team.html',
                    controller: 'TeamController'
                }).
                when('/looking/team/:game', {
                    templateUrl: '/looking/teams.html',
                    controller: 'LookingTeamController'
                }).
                when('/looking/teammate/:game', {
                    templateUrl: '/looking/teammates.html',
                    controller: 'LookingTeammateController'
                }).
                when('/looking/createteam/:id', {
                    templateUrl: '/looking/createteam.html',
                    controller: 'CreateTeamController'
                }).
                when('/error', {
                    templateUrl: 'error.html'

                }).
                when('/about', {
                    templateUrl: 'about.html'

                }).
                when('/confirmation/:token', {
                    templateUrl: function(a){
                        return 'user/confirm?token=' + a.token;
                    }
                }).otherwise({
                    redirect: '/error'
                });


        }]
).run(function (TeamService, $rootScope, $location, $cookieStore, UserService, $http, $templateCache, $route) {


        $rootScope.hasRole = function (role) {

            if ($rootScope.user === undefined) {
                return false;
            }

            if ($rootScope.user.roles[role] === undefined) {
                return false;
            }

            return $rootScope.user.roles[role];
        };

        $rootScope.logout = function () {
            $http.get('/logout').success(function () {
                $rootScope.authenticated = false;
                delete $rootScope.user;
            });

        };

        $rootScope.messages = [];

        $rootScope.maxMessageId = undefined;


        $rootScope.event = function () {

            $http.get('/firstcheckmessages').success(function (data) {
                $rootScope.messages = data;
                var source = new EventSource("/messages", {
                    withCredentials: true
                });
                source.onmessage = function (data) {
                    $rootScope.$apply(function () {


                        $rootScope.messages = angular.fromJson(data.data);


                        if ($location.path() == '/mymessages') {
                            var scopeOfMessages = angular.element('#messagesDiv').scope();

                            var indexes = [];
                            angular.forEach($rootScope.messages, function (v, index) {

                                if (!$rootScope.maxMessageId || v.id > $rootScope.maxMessageId) {


                                    angular.forEach(scopeOfMessages.allMessages, function (val, k) {
                                        if (val.userFrom == v.userFrom) {

                                            if (!val.isCollapsed) {
                                                indexes.push(index);
                                                scopeOfMessages.createChat(v, val, k, val.correspondences.length);

                                            } else {
                                                val.read = false;
                                                val.unread += 1;
                                            }

                                        }
                                    })

                                }

                            });

                            indexes.reverse();

                            angular.forEach(indexes, function (index) {
                                $http.get('/messageseen?id=' + $rootScope.messages[index].id);
                                $rootScope.messages.splice(index, 1);
                            })

                        }

                        if ($rootScope.messages.length > 0) {
                            $rootScope.maxMessageId = $rootScope.messages[$rootScope.messages.length - 1].id;
                        }


                    });
                };
            });


        };


        UserService.get(function (user) {
            $rootScope.user = user;
            $rootScope.authenticated = $rootScope.hasRole('ROLE_USER');

            if ($rootScope.authenticated) {
                TeamService.getTeams({identifier: user.name}, function (teams) {
                    $rootScope.user.teams = teams;
                });

                $rootScope.event();
            }
        }, function (res) {
            $rootScope.user = res.data;
        });

        $rootScope.trustHtml = function (templateId) {
            return $templateCache.get(templateId);
        };


        $rootScope.initialized = true;
    });
