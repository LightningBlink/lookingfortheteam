var newsControllers = angular.module('newsControllers', []);
var lookingTeamControllers = angular.module("lookingTeamControllers", []);
var profileControllers = angular.module("profileControllers", []);
var messagesController = angular.module("messagesController", []);


app.controller('LoginController', ['Notification', '$scope', '$rootScope', '$location', '$cookieStore', 'UserService', 'TeamService', function (Notification, $scope, $rootScope, $location, $cookieStore, UserService, TeamService) {


    $scope.sendPassword = function (input, valid) {

        if (valid) {
            UserService.sendEmailPassword({emailSendPassword: input}, function (data) {
                $scope.emailSendPassword = false;
                if (data.success) {
                    Notification.success(data.message);
                } else {
                    Notification.error(data.message);
                }


            })
        }
    };

    $scope.login = function (isValid) {
        if (!isValid) {
            return;
        }
        UserService.authenticate($.param({username: $scope.username, password: $scope.password}), function () {
            UserService.get(function (user) {
                $rootScope.user = user;
                $rootScope.authenticated = $rootScope.hasRole('ROLE_USER');
                angular.element('#login').modal('toggle');
                $scope.login_error = false;

                if ($rootScope.authenticated) {

                    TeamService.getTeams({identifier: user.name}, function (teams) {
                        $rootScope.user.teams = teams;
                    });

                    $rootScope.event();
                }
            });


        }, function () {
            $scope.login_error = true;
        });
    };
}]);


app.controller('PostsController', ['$scope', function ($scope, $route) {

    $scope.animatePathMethod = function (page) {
        $scope.animationPage = page + '.html';

        var element;
        for(var i = 1; i < 6; i++){
            element =  angular.element("#template" +i);
            if(element.hasClass('active')){
                element.removeClass('active');
            }
        }

        angular.element("#" + page).addClass("active");

    }
}]);

app.controller('RegistrationController', ['Notification', '$scope', '$rootScope', '$location', '$cookieStore', 'UserService', function (Notification, $scope, $rootScope, $location, $cookieStore, UserService) {


    $scope.registration = function (isValid) {
        if (!isValid) {
            return;
        }

        UserService.registration(
            $.param({
                    username: $scope.r_username,
                    password: $scope.r_password,
                    email: $scope.r_email
                }
            ), function (data) {
                if (data.success) {
                    Notification.success(data.message);
                    angular.element('#registration').modal('toggle');
                } else {
                    Notification.error(data.message);
                }


            });
    };
}]);


messagesController.controller('MessagesController', ['$sce', 'MessageService', '$location', '$scope', '$rootScope', '$http', 'Notification', function ($sce, MessageService, $location, $scope, $rootScope, $http, Notification) {


    $scope.messageSend = function (userTo, message, correspondence) {
        $http.get('/sendmessage', {
            params: {
                userFrom: $scope.user.name,
                userTo: userTo,
                message: message
            }
        }).success(function () {
            var date = new Date();
            correspondence.push({
                id: 1,
                date: {
                    year: 1900 + date.getYear(),
                    monthValue: 1 + date.getMonth(),
                    dayOfMonth: date.getDate(),
                    hour: date.getHours(),
                    minute: date.getMinutes()
                },
                message: '<kbd>' + message + '</kbd>',
                from: $scope.user.name
            })
        });

    };

    $scope.createChat = function (v, message, indexTr, k) {
        if (v.isShow) {
            if (v.invitation && v.userFrom != $scope.user.name) {
                v.message = $sce.trustAsHtml('<kbd>' + v.message + '</kbd>' + '<br><button class="btn btn-primary" ng-click="acceptTeam(' + v.id + ',' + indexTr + ',' + k + ')">OK</button>' +
                '<button class="btn btn-warning" ng-click="declineTeam(' + message.id + ',' + indexTr + ',' + k + ')">Cancel</button>');
            } else {
                v.message = $sce.trustAsHtml('<kbd>' + v.message + '</kbd>');
            }

            message.correspondences.push({
                id: v.id,
                date: v.date,
                message: v.message,
                from: v.userFrom
            });
        }
    };

    $scope.trClicked = function (indexTr, message) {
        message.isCollapsed = !message.isCollapsed;

        if (!message.isCollapsed) {

            $http.get('/correspondence?userName=' + message.userFrom).success(function (data) {
                message.correspondences = [];
                angular.forEach(data, function (v, k) {
                    $scope.createChat(v, message, indexTr, k);
                });


                if (!message.read) {
                    $http.get('/messageseen?id=' + message.id).success(function () {
                        message.read = true;
                        angular.forEach(message.correspondences, function (vC, kC) {
                            if (vC.read) {
                                return;
                            }
                            for (var i = 0; i < $rootScope.messages.length; i++) {
                                if ($rootScope.messages[i].id == vC.id) {
                                    $rootScope.messages.splice(i, 1);
                                    break;
                                }
                            }
                        });

                        message.unreadd = 0;
                    }).error(function (data) {
                        Notification.error(data.message);
                    });
                }
            })
        }

    };

    $scope.acceptTeam = function (param, index, k) {
        MessageService.acceptInvite({param: param}, function (data) {
            if (data.success) {
                Notification.success(data.message);
                $rootScope.user.teams.push(data.json);
                $scope.allMessages[index].correspondences.splice(k, 1);
            } else {
                Notification.error(data.message);
            }
        });
    };


    $scope.declineTeam = function (param, index, k) {
        MessageService.declineTeam({param: param}, function (data) {
            if (data.success) {
                $scope.allMessages[index].correspondences.splice(k, 1);
            } else {
                Notification.error(data.message);
            }

        });
    };

    $http.get('/getmessages').
        success(function (data) {
            data.content = angular.forEach(data.content, function (v) {
                v.isCollapsed = true;
            });
            $scope.allMessages = data.content;
            $scope.maxSize = 10;
            $scope.bigTotalItems = data['totalElements'];
            $scope.bigCurrentPage = 1;

            angular.forEach($scope.allMessages, function (v, k) {
                v.unreadd = 0;
                if (!v.read) {

                    angular.forEach($rootScope.messages, function (v1, k1) {
                        if (v1.userFrom == v.userFrom) {
                            v.unreadd += 1;
                        }
                    });


                }
            });


        }).
        error(function (data) {
            Notification.error(data.message);
        });


    $scope.pageChanged = function () {
        $http.get('/getmessages', {
            params: {
                page: $scope.bigCurrentPage - 1,
                size: 10
            }
        }).success(function (data) {
            data.content = angular.forEach(data.content, function (v) {
                v.isCollapsed = true;
            });
            $scope.allMessages = data.content
        });
    };
}]);


messagesController.controller('DeleteMessageController', ['Notification', '$scope', '$rootScope', '$http', function (Notification, $scope, $rootScope, $http) {

    $scope.cancelDeleteDialog = function () {
        angular.element('#removeMessage').modal('toggle');
    };

    $scope.obj = {};

    $scope.deleteMessage = function () {
        $http.get('/deletemessage?id=' + $scope.obj.message.id)
            .success(function (data) {
                if (data.success) {
                    Notification.success(data.message);
                    $scope.allMessages.splice($scope.obj.index, 1);
                    //var fromUser = $scope.allMessages[$scope.obj.index]['userFrom'];
                    //
                    //var size = $rootScope.messages.length;
                    //for (var i = 0; i < size; i++) {
                    //    if ($rootScope.messages[i].userFrom == fromUser) {
                    //        $rootScope.messages.splice(i, 1);
                    //        i = -1;
                    //        size--;
                    //    }
                    //}

                } else {
                    Notification.error(data.message);
                }
                angular.element('#removeMessage').modal('toggle');
            })
            .error(function (data) {
                Notification.error(data.message);
            })
    };

    $rootScope.forDeleteMessage = function (message, index) {
        $scope.obj = {
            message: message,
            index: index
        };
    };


}]);


app.controller('MenuController', ['$rootScope', '$route', '$http', '$scope', '$location', 'WS', '$filter', function ($rootScope, $route, $http, $scope, $location, WS, $filter) {

    if ($location.path() == '/') {
        $location.path('/posts');
    }

    $scope.streams = [];


    $scope.changeLang = function (lang) {
        $location.search({lang: lang});
        location.reload();
    };

    var twichFunc = function (e, isEvent) {
        if (!isEvent) {
            var id = e.relatedTarget.id.substring(3);
        } else {
            id = e
        }

        $http.get('https://api.twitch.tv/kraken/streams?game=' +
        $scope.alternativeGames[id].name
        + '&limit=3').then(
            function (data) {
                $scope.streams = [];
                data = data.data;
                angular.forEach(data.streams, function (v) {
                    $scope.streams.push({
                        url: v.channel.url,
                        name: v.channel.display_name,
                        logo: v.channel.logo,
                        viewers: v.viewers
                    });

                })
            }
        )
    };

    var car = angular.element('.carousel');
    car.carousel({
        interval: 15000
    });


    $http.get("/game/getgames").then(function (data) {
        $scope.alternativeGames = data.data;

        twichFunc(0, true);

        car.on('slide.bs.carousel slid.bs.carousel', twichFunc);
    });

    $scope.getUnique = function (collection) {

        return $filter("unique")(collection, 'name').length;

    };

    $scope.submit = function () {
        WS.send("ATP SERVER");
    };

    var TEXT_ACTIVE = 'active';

    $scope.pathes = ['/news', '/posts', '/looking', '/profile', '/about'];

    var topPath = $location.path();

    $scope.activeFunc = function (param, param1) {
        $scope.array_active = new Array(5);
        $scope.array_active[param] = TEXT_ACTIVE;

        $scope.reloadPage(param1);

    };

    $scope.reloadPage = function (path) {
        if ($location.path() == "/" + path) {
            $route.reload();
        }
    };

    for (var i = 0; i < $scope.pathes.length; i++) {
        if (topPath.indexOf($scope.pathes[i]) === 0) {
            $scope.array_active = new Array(4);
            $scope.array_active[i] = TEXT_ACTIVE;
        }
    }


    if ($location.path() == '/login') {
        angular.element('#login').modal('toggle');
    } else if ($location.path() == '/registration') {
        angular.element('#registration').modal('toggle');
    }
}]);

newsControllers.controller('MainNewsController', ['$http', '$location', '$scope', '$sce', 'NewsService', function ($http, $location, $scope, $sce, NewsService) {

    var page = NewsService.getAll(function () {
        trustHtml(page.content, $scope.news = []);
        $scope.maxSize = 5;
        $scope.bigTotalItems = page['totalElements'];
        $scope.bigCurrentPage = 1;
    });


    $scope.deleteNew = function (id) {
        $http.delete('/admin/deletenew/' + id).success(function () {
            window.location.reload();
        })
    };

    $scope.showNew = function (id) {
        $http.get('/admin/shownew/' + id).success(function () {
            window.location.reload();
        })
    };

    $scope.updateNew = function (id) {
        $location.path("/addnew").search({id: id});

    };


    $scope.pageChanged = function () {
        page = NewsService.getAll({page: $scope.bigCurrentPage - 1, size: 10}, function () {
            trustHtml(page.content, $scope.news = []);
        });
    };


    function trustHtml(input, out) {
        angular.forEach(input, function (value) {
            var tmp = value;
            tmp.description = $sce.trustAsHtml(value.description);
            this.push(tmp);
        }, out);
    }

}]);

app.controller('AddNewController', ['$scope', '$routeParams', 'NewsService', function ($scope, $routeParams, NewsService) {

    if ($routeParams.id) {
        NewsService.get({id: $routeParams.id}, function (data) {
            $scope.new = data;
        });

    }

}]);

app.controller('NewController', ['$sce', '$scope', '$routeParams', 'NewsService', function ($sce, $scope, $routeParams, NewsService) {
    var entity = NewsService.get({id: $routeParams.id}, function () {
        $scope.entity = entity;
        $scope.entity.description = $sce.trustAsHtml(entity.description)
    })

}]);


lookingTeamControllers.controller('MainLookingTeamController', ['UserService', '$scope', function (UserService, $scope) {

    $scope.transelater = '';
    $scope.templates =
        [{url: 'games_found.html'},
            {url: 'games_not_found.html'}];

    UserService.getUserByUsername({name: $scope.user.name}, function (data) {
        $scope.user = data;
        $scope.games = [];
        angular.forEach($scope.user.userGames, function (v) {
            var game = v.pk.game;
            game.hasTeam = {message: true, value: false, link: '/createteam/' + game.id};
            this.push(game);

        }, $scope.games);


        angular.forEach($scope.games, function (v) {
            var exist = false;
            angular.forEach($scope.user['userTeams'], function (userTeam) {
                if (userTeam.pk.game && v.id == userTeam.pk.game.id) {
                    exist = true;
                }
            });

            if (exist) {
                v.hasTeam = {message: false, value: true, link: '/teammate/' + v.name};
            }
        });

        if ($scope.games.length) {
            $scope.template = $scope.templates[0];
        } else $scope.template = $scope.templates[1];


    });

}]);

app.controller('AvatarUploaderTeam', ['$scope', 'Notification', function ($scope, Notification) {
    $scope.progress = 0;
    $scope.removeImage = function () {
        $scope.$flow.cancel();
        $scope.info.team.hasAvatar = false;
    };


    $scope.$on('flow::fileAdded', function (event, $flow, flowFile) {
        $scope.progress = 0;
        var ext = flowFile.getExtension();

        if (!(ext == 'jpeg' || ext == 'jpg') || flowFile.size > 2000000) {
            Notification.error($scope.avataruploaderror);
            event.preventDefault();
        } else {
            $scope.info.team.hasAvatar = true;
        }
    });

}]);

app.controller('AvatarUploaderUser', ['$scope', 'Notification', function ($scope, Notification) {

    $scope.removeImage = function () {
        $scope.$flow.cancel();
        $scope.userInfo.hasAvatar = false;
    };


    $scope.$on('flow::fileAdded', function (event, $flow, flowFile) {
        $scope.progress = 0;
        var ext = flowFile.getExtension();

        if (!(ext == 'jpeg' || ext == 'jpg') || flowFile.size > 2000000) {
            Notification.error($scope.avataruploaderror);
            event.preventDefault();
        } else {
            $scope.userInfo.hasAvatar = true;
        }
    });


}]);
lookingTeamControllers.controller('TeamController', ['$rootScope', 'TeamService', '$scope', 'Notification', '$routeParams', 'WS', function ($rootScope, TeamService, $scope, Notification, $routeParams, WS) {


    $scope.templatesTeam =
        [{url: 'team_exist.html'},
            {url: 'team_not_exist.html'}];

    $rootScope.chat = $routeParams.id;

    WS.connect();


    $scope.sendMessage = function (messageInput) {
        WS.send(messageInput);
        $scope.$$childTail.$$childHead.messageInput = "";
    };


    $scope.leaveTeamButton = function (game, id, username) {
        $scope.prepareLeaveObj = {
            game: game,
            idTeam: id,
            username: username
        }

    };


    $scope.addGame = function (game) {

        if (game) {
            TeamService.addGameToTeam($.param({game: game.name, teamId: $scope.info.team.id}), function (data) {
                if (data.success) {
                    Notification.success(data.message);
                    $scope.games[game.name] = [];
                    $scope.info.games.splice($scope.info.games.indexOf(game), 1);
                    $scope.gamesHeader.push(game);


                } else {
                    Notification.error(data.message);
                }
            });

        }
    };

    $scope.removeGameFn = function (game, teamId, id) {
        $scope.removeGame = {
            game: game,
            teamId: teamId,
            id: id
        };
    };


    TeamService.getTeam({identifier: $routeParams.id}, function (info) {
        $scope.info = info;

        if ($scope.info.team) {
            $scope.templateTeam = $scope.templatesTeam[0];
        } else {
            $scope.templateTeam = $scope.templatesTeam[1];
            return;
        }


        $scope.games = {};

        angular.forEach(info.teamGames, function (game) {
            $scope.games[game.name] = [];
        });


        angular.forEach(info.teammateInfos, function (v) {

            this[v.game].push({
                username: v.username,
                post: v.post
            });

        }, $scope.games);


        $scope.gamesHeader = info.teamGames;
    });


}]);

lookingTeamControllers.controller('LeaveTeamController', ['TeamService', 'Notification', '$scope', '$route', '$location', '$window', function (TeamService, Notification, $scope, $route, $location, $window) {

    $scope.cancelLeaveTeam = function () {
        angular.element('#leaveTeam').modal('toggle');
    };

    $scope.leaveTeam = function () {
        TeamService.leaveTeam($.param($scope.prepareLeaveObj), function (data) {
            angular.element('#leaveTeam').modal('toggle');
            if (data.success) {

                if (data.json == 0) {
                    $location.path('/error?message=' + data.message);
                } else {

                    if (!data.json) {
                        $window.location.reload();
                    } else {
                        var key;
                        if ($scope.games) {
                            angular.forEach($scope.games[$scope.prepareLeaveObj.game], function (v, k) {
                                if (v.username == data.json) {
                                    key = k;
                                }
                            });

                            if (key != undefined) {
                                $scope.games[$scope.prepareLeaveObj.game].splice(key, 1);
                            }
                        }

                        for (var i = 0; i < $scope.user.teams.length; i++) {

                            if ($scope.user.teams[i].id == $scope.prepareLeaveObj.idTeam) {
                                $scope.user.teams.splice(i, 1);
                                break;
                            }

                        }

                    }


                    Notification.success(data.message);
                }


            } else {
                Notification.error(data.message);
            }
        });
    };


}
]);

lookingTeamControllers.controller('RemoveGameController', ['TeamService', 'Notification', '$scope', '$route', '$location', function (TeamService, Notification, $scope, $route, $location) {

    $scope.cancelRemoveGameModal = function () {
        angular.element('#removeGame').modal('toggle');
    };

    $scope.removeGameModal = function () {


        TeamService.removeGameFromTeam($.param($scope.removeGame), function (data) {

            if (data.success) {
                Notification.success(data.message);
                var index;
                angular.forEach($scope.gamesHeader, function (v, k) {
                    if (v.name == $scope.removeGame.game) {
                        index = k;
                    }
                });
                $scope.gamesHeader.splice(index, 1);
                $scope.info.games.push({id: $scope.removeGame.id, name: $scope.removeGame.game});

            } else {

                Notification.error(data.message);
            }

            angular.element('#removeGame').modal('toggle');

        })


    };


}
]);

lookingTeamControllers.controller('CreateTeamController', ['$rootScope', 'TeamService', '$scope', 'Notification', '$routeParams', '$location', function ($rootScope, TeamService, $scope, Notification, $routeParams, $location) {


    $scope.saveTeam = function (isInvalid) {
        if (isInvalid) {
            return;
        }

        $scope['team'].idGame = $routeParams.id;

        TeamService.save($.param($scope['team']), function (data) {
            if (data.success) {
                $rootScope.user.teams.push(data.json);
                Notification.success(data.message);
                $location.path('/looking')
            } else {
                Notification.error(data.message);
            }
        })
    }

}]);


lookingTeamControllers.controller('LookingTeamController', ['Notification', 'TeamService', '$scope', '$routeParams', 'MessageService', function (Notification, TeamService, $scope, $routeParams, MessageService) {

    $scope.game = $routeParams.game;

    $scope.country = 'INT';

    $scope.searchTeams = function (keyword, country) {
        TeamService.getLookingTeams({game: $routeParams.game, keyword: keyword, country: country}, function (data) {
            $scope.teams = data.content;
            $scope.maxSize = 10;
            $scope.bigTotalItems = data['totalElements'];
            $scope.bigCurrentPage = 1;
        });
    };

    $scope.searchTeams('', 'INT');

    $scope.accession = function (id, game) {
        MessageService.setAccession({param: id, game: game}, function (data) {
            if (data.success) {
                Notification.success(data.message);
            } else {
                Notification.error(data.message);
            }
        });
    };

    $scope.pageChanged = function () {
        TeamService.getLookingTeams({
            page: $scope.bigCurrentPage - 1,
            size: 10,
            game: $routeParams.game
        }, function (data) {
            $scope.teams = data.content;
        });
    };


}]);

lookingTeamControllers.controller('LookingTeammateController', ['UserService', '$scope', '$routeParams', 'MessageService', 'Notification', function (UserService, $scope, $routeParams, MessageService, Notification) {

    $scope.country = 'INT';
    $scope.game = $routeParams.game;
    UserService.getLookingPlayers({game: $routeParams.game}, function (data) {
        $scope.players = data.content;
        $scope.maxSize = 10;
        $scope.bigTotalItems = data['totalElements'];
        $scope.bigCurrentPage = 1;
    });

    $scope.searchTeammates = function (keyword, country) {
        UserService.getLookingPlayers({game: $routeParams.game, keyword: keyword, country: country}, function (data) {
            $scope.players = data.content;
            $scope.maxSize = 10;
            $scope.bigTotalItems = data['totalElements'];
            $scope.bigCurrentPage = 1;
        });

    };

    $scope.getInfoPlayer = function (player, game) {

        var info = '';
        angular.forEach(player.userGames, function (v) {

            if (v.pk.game.name == game) {
                info = v.info;
            }

        });

        return info;

    };

    $scope.searchTeammates('', 'INT');

    $scope.pageChanged = function () {
        UserService.getLookingPlayers({
            page: $scope.bigCurrentPage - 1,
            size: 10,
            game: $routeParams.game
        }, function (data) {
            $scope.players = data.content;
        });
    };


    $scope.invite = function (username) {
        MessageService.sendInvite({param: username, game: $routeParams.game}, function (data) {
            if (data.success) {
                Notification.success(data.message);
            } else {
                Notification.error(data.message);
            }
        })
    }

}]);


profileControllers.controller('MainProfileController', ['$http', '$scope', 'Notification', 'UserService', '$routeParams', function ($http, $scope, Notification, UserService, $routeParams) {

    $scope.allGames = [];
    $scope.templates =
        [{url: 'user_found.html'},
            {url: 'user_not_found.html'}];


    $scope.changePassObj = {};

    $scope.changePassToServer = function (isValid) {
        if (isValid) {
            UserService.changePasswordToServer($.param($scope.changePassObj), function (data) {
                if (data.success) {
                    Notification.success(data.message);
                    angular.element("#collapseOneChangePass").collapse('toggle');
                } else {
                    Notification.error(data.message);
                }
            });

        }
    };

    $scope.collapseMethod = function ($event) {
        $('.in').collapse('toggle');
        angular.element('#' + $event.toElement.getAttribute('aria-controls')).collapse('toggle');

    };


    $scope.collapseChangePass = function () {
        angular.element("#collapseOneChangePass").collapse('toggle');

    };

    UserService.info({name: $routeParams.username}, function (data) {
        $scope.countryName = data.countryName;
        $scope.userInfo = data.user;

        if ($scope.userInfo) {
            $scope.template = $scope.templates[0];
        } else {
            $scope.template = $scope.templates[1];
        }

        if (!$scope.userInfo.userGames) {
            $scope.userInfo.userGames = [];
        }
        $scope.allGames = data.allGames;

        angular.forEach($scope.userInfo.userGames, function (v, k) {
            angular.forEach($scope.userInfo.userTeams, function (v1, k1) {
                if (v.pk.game.id == v1.pk.game.id) {
                    v.clan = {};
                    v.clan.name = v1.team.name;
                    v.clan.id = v1.team.id;
                }
            });
        })

    });

    $scope.removeGame = function (userGame) {
        $scope.allGames.push(userGame.pk.game);
        $scope.userInfo.userGames.splice($scope.userInfo.userGames.indexOf(userGame), 1)

    };


    $scope.addGame = function () {

        var select = $scope.$$childTail.$$childHead.gameSelect;
        if (select != undefined) {

            $scope.userInfo.userGames.push({
                pk: {
                    game: select
                }
            });
            $scope.allGames.splice($scope.allGames.indexOf(select), 1);

        }
    };

    $scope.messageSend = function (form) {
        if (form.$valid) {
            $http.get('/sendmessage', {
                params: {
                    userFrom: $scope.user.name,
                    userTo: $scope.userInfo.username,
                    message: $scope.$$childTail.message
                }
            }).
                success(function (data) {
                    Notification.success(data.message);
                    angular.element('#message').modal('toggle');
                    $scope.$$childTail.message = '';
                    form.$submitted = false;

                }).
                error(function (data) {
                    Notification.error(data.message);
                });
        }
    };


    $scope.saveUser = function (isValid) {
        if (!isValid) {
            return;
        }

        UserService.save(angular.toJson($scope.userInfo), function (data) {
            if (data.success) {
                Notification.success(data.message);
            } else {
                Notification.error(data.message);
            }
        })
    }
}]);