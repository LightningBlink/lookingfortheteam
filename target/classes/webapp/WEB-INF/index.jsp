<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>

<html lang="en" ng-app="myApp" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=cp1251"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Looking for team or teammate. Поиск клана или соклановца по множеству популярных онлайн игр.">
    <meta name="robots" content="all"/>
    <meta name="keywords" content="looking for team, looking for party, Team Recruitment,  looking for teammate, ищу клан, поиск клана, хочу в клан, ищу команду, вступлю в клан, ищу пати"/>
    <meta name="author" content="Rozhko Denis">

    <link rel="alternate" href="http://lookingforteam.net/posts?lang=en" hreflang="en"/>
    <link rel="alternate" href="http://lookingforteam.net/posts?lang=ru" hreflang="ru"/>

    <title>Looking For Team. Поиск команды.</title>

    <!-- Bootstrap -->
    <link rel="shortcut icon" href="/images/logo/anime-eye-md.png" type="image/png">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/form-helpres/dist/css/bootstrap-formhelpers.min.css" rel="stylesheet"/>
    <link href="/css/angular-ui-notification.min.css" rel="stylesheet"/>
    <link href="/css/animation.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="//vk.com/js/api/openapi.js" type="text/javascript"></script>

    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/jquery-1.12.1.min.js"></script>
    <script src="/js/angular.min.js"></script>
    <script src="/js/angular-route.min.js"></script>
    <script src="/js/angular-resource.min.js"></script>
    <script src="/js/angular-cookies.min.js"></script>
    <script src="/js/angular-sanitize.min.js"></script>
    <script src="/js/ng-flow-master/dist/ng-flow-standalone.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/ui-bootstrap-tpls-0.11.2.min.js"></script>
    <script src="/js/angular-ui-notification.min.js"></script>
    <script src="/js/angular-websocket.js"></script>
    <script src="/js/form-helpres/dist/js/output.min.js"></script>
    <%--<script src="/js/sock/sockjs-0.3.4.js"></script>--%>
    <%--<script src="/js/sock/stomp.js"></script>--%>
    <script src="/js/service.js"></script>
    <script src="/js/module.js"></script>
    <script src="/js/route.js"></script>
    <script src="/js/directives.js"></script>
    <script src="/js/controller.js"></script>
    <script src="/js/filters.js"></script>


    <base href="/"/>
</head>


<body class="full" ng-controller="MenuController">
<script type="text/ng-template" id="login.html">
    <li>
        <a href="#" ng-click="changeLang('en')">en <img class="country-flag" src="/images/flags/us.png"/></a>
    </li>
    <li>
        <a href="#" ng-click="changeLang('ru')">ru <img class="country-flag" src="/images/flags/ru.png"/></a>
    </li>
    <li><a href="#" ng-click="logout()"><spring:message code="index.logout"/></a></li>
    <li><a href="profile/{{user.name}}"><img height="20" src="/user/avatarimage?name={{user.name}}"/> {{user.name}}</a>
    </li>
</script>

<script type="text/ng-template" id="logout.html">
    <li>
        <a href="#" ng-click="changeLang('en')"><img class="country-flag" src="/images/flags/us.png"/>en</a>
    </li>
    <li>
        <a href="#" ng-click="changeLang('ru')"><img class="country-flag" src="/images/flags/ru.png"/>ru</a>
    </li>
    <li><a data-toggle="modal" data-target="#registration" href="#"><spring:message code="index.registration"/></a></li>
    <li><a href="#" data-toggle="modal" data-target="#login"><spring:message code="index.login"/></a></li>
</script>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul ng-if="!hasRole('ROLE_USER')" class="nav navbar-nav navbar-left">
                <li>
                    <img class="beta-icon" src="/images/beta.png"/>
                </li>
            </ul>
            <ul ng-if="hasRole('ROLE_USER')" class="nav navbar-nav navbar-left">
                <li>
                    <img class="beta-icon" src="/images/beta.png"/>
                </li>
                <li role="presentation">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span><spring:message
                            code="index.myteams"/><span class="badge">{{user.teams.length == 0 ? '' : getUnique(user.teams) }}</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li ng-repeat="team in user.teams | unique:'name'"><a href="/team/{{team.id}}"
                                                                              class="text-center"><img
                                style="float: left;" width="30" src="/user/avatarimage?name={{team.name}}&isTeam=true">{{team.name}}</a></li>
                    </ul>
                </li>
                <li><a href="mymessages" data-ng-click="reloadPage('mymessages')"><span
                        class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    <spring:message code="index.messages"/>
                    <span class="badge">{{messages.length == 0 ? '' : messages.length}}</span></a>
            </ul>
            <ul class="nav navbar-nav navbar-right" login>
            </ul>
        </div>
    </div>
</nav>
<div class="container body">
    <h2 style="position: absolute;float: right; font-size: 1px; color: #273052;">looking for team, looking for party, Team Recruitment,  looking for teammate, ищу клан, поиск клана, хочу в клан, ищу команду, вступлю в клан, ищу пати</h2>
    <div style="padding-top: 50px;">

        <div class="row">
            <div class="col-md-4">
                <img width="150" style="float:right;"
                     src="/images/logo/anime-eye-md.png">

                <h1 class="font-team-namesss" style="color: black;">
                    LOOKING FOR TEAM
                </h1>
            </div>
        </div>


    </div>
    <span style="margin-top: 50px; position: absolute;background: #222;color: #139ff7;" class="alert"><spring:message code="index.title.nav"/> <img alt="arrow" src="/images/arrow.png" height="20"/></span>
    <nav class="navbar" style="margin-top: 100px; margin-bottom: 0;">
        <div class="navbar-header"><img alt="hots" src="/images/navbar/hots.png" height="45"/></div>
        <div class="navbar-header"><img alt="hearthstone" src="/images/navbar/hs.png" height="45"/></div>
        <div class="navbar-header"><img alt="StarCraft 2" src="/images/navbar/sc.png" height="45"/></div>
        <div class="navbar-header"><img alt="WarCraft 3" src="/images/navbar/wc3.png" height="45"/></div>
        <div class="navbar-header"><img alt="Dota 2" src="/images/navbar/dota%202.png" height="45"/></div>
        <div class="navbar-header"><img alt="counter strike global offensive" src="/images/navbar/cs.png" height="45"/>
        </div>
        <div class="navbar-header"><img alt="League of Legends" src="/images/navbar/lol.png" height="45"/></div>
        <div class="navbar-header"><img alt="World OF Tanks" src="/images/navbar/wot.png" height="45"/></div>
        <div class="navbar-header"><img alt="Diablo 3" src="/images/navbar/diablo.png" height="45"/></div>
    </nav>

    <nav class="navbar navbar-inverse" style="margin-bottom: 0;" role="navigation">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="{{array_active[0]}}" ng-click="activeFunc(0, 'news')"><a href="news"><spring:message
                            code="index.news"/></a></li>
                    <li id="postsId" class="{{array_active[1]}}" ng-click="activeFunc(1, 'posts')"><a href="posts"><spring:message
                            code="index.posts"/></a></li>
                    <li class="{{array_active[2]}}" ng-click="activeFunc(2, 'looking')"><a
                            href="looking"><spring:message
                            code="index.lookingforteam"/></a></li>
                    <li ng-if="hasRole('ROLE_USER')" class="{{array_active[3]}}"
                        ng-click="activeFunc(3, 'profile/' + user.name)"><a
                            href="profile/{{user.name}}"><spring:message code="index.profile"/></a></li>
                    <li class="{{array_active[4]}}" ng-click="activeFunc(4, 'about')"><a
                            href="about"><spring:message code="about.about"/></a></li>
                </ul>

            </div>
        </div>
    </nav>
    <div style="background: #ffffff;">
        <div class="col-md-8 row" style="margin-left: 1px; margin-top: 10px;" ng-view></div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="panel panel-info">
                            <div class="panel-heading" style="text-align: center;"><spring:message
                                    code="index.populargames"/></div>
                            <div class="panel-body">
                                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

                                    <div class="carousel-inner" role="listbox">
                                        <div id="car{{$index}}" class="item {{$index == 0 ? 'active' : ''}}"
                                             ng-repeat="alt in alternativeGames">

                                            <img alt="{{alt.name}}" ng-src="images/games/{{alt.filePath}}.jpg">

                                            <p style="height : 70px; text-align: center;" class="font-help-text">
                                                {{alt.name}}</p>
                                        </div>
                                    </div>
                                    <a class="left carousel-control" href="#carousel-example-generic" role="button"
                                       data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="right carousel-control" href="#carousel-example-generic" role="button"
                                       data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <p style="text-align: center;" class="list-group-item list-group-item-info"><spring:message
                                code="index.onlinestreams"/></p>
                        <table class="table">
                            <tr ng-repeat="stream in streams">
                                <td style="text-align: center;"><img ng-src="{{stream.logo}}" width="40" height="40"/><a
                                        href="{{stream.url}}"
                                        class="font-help-text"
                                        target="_blank">
                                    {{stream.name}}</a>

                                    <p style="text-align: center;">
                                        <spring:message code="index.viewers"/> : {{stream.viewers}}
                                    </p>
                                </td>
                            </tr>

                        </table>
                    </li>
                    <li class="list-group-item">
                        <script type="text/javascript" src="//vk.com/js/api/openapi.js?120"></script>

                        <!-- VK Widget -->
                        <div id="vk_groups"></div>
                        <script type="text/javascript">
                            VK.Widgets.Group("vk_groups", {
                                mode: 0,
                                width: "auto",
                                height: "200",
                                color1: 'FFFFFF',
                                color2: '2B587A',
                                color3: '5B7FA6'
                            }, 106797977);
                        </script>
                    </li>
                </ul>
            </div>
        </div>
        <div style="padding-bottom: 30px; visibility : hidden; color: #ffffff;">
            AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        </div>
        <div id="footer"
             style="background: #273052; color:#f5f5f5; text-align: center; padding-top: 20px; padding-bottom: 20px;">
            Copyright &copy; All rights reserved 2016
        </div>
    </div>

</div>
</body>

<div class="modal fade" id="login" ng-controller="LoginController" tabindex="-1" role="dialog"
     aria-labelledby="LoginModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title panel-heading text-center" id="LoginModalLabel"><spring:message
                        code="index.logininaccount"/></h4>
            </div>
            <div class="modal-body">
                <form name="formLog" class="css-form" novalidate>
                    <div class="alert alert-danger" role="alert" ng-show="login_error">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only"><spring:message code="index.error"/>:</span>
                        <spring:message code="index.loginorpasswordwrong"/>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                            <input type="text" ng-model="username" name="username" required="" class="form-control"
                                   id="username" placeholder="<spring:message code="index.enterusername"/>">
                        </div>
                        <div ng-show="formLog.$submitted">
                            <div class="error-input" ng-show="formLog.username.$error.required">
                                <spring:message code="index.tellusyourname"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" ng-model="password" required="" class="form-control" id="password"
                                   name="password" placeholder="<spring:message code="index.enterpassword"/>">
                        </div>
                        <div ng-show="formLog.$submitted">
                            <div class="error-input" ng-show="formLog.password.$error.required">
                                <spring:message code="index.telluspassword"/>
                            </div>
                        </div>
                    </div>

                    <button type="submit" ng-click="login(formLog.$valid)" class="btn btn-primary hundred">
                        <spring:message code="index.log.in"/>
                    </button>
                </form>
            </div>
            <div class="modal-footer panel-heading text-center">
                <a href="#" ng-click="emailSendPassword=!emailSendPassword"><spring:message
                        code="index.forgotpwassword"/></a>

                <form name="formSendEmail" ng-submit="sendPassword(sendMailPassword, formSendEmail.$valid)" novalidate>
                    <div ng-show="emailSendPassword" class="input-group">
                        <input ng-model="sendMailPassword" type="email" id="emailSendPassword" name="emailSendPassword"
                               required=""
                               class="form-control" placeholder="<spring:message
                                    code="index.needwriteemail"/>"/>
                               <span class="input-group-btn">
        <button class="btn btn-default" type="submit"><spring:message code="index.send"/></button>
      </span>
                    </div>

                    <div ng-show="formSendEmail.$submitted">
                        <div class="error-input" ng-show="formSendEmail.emailSendPassword.$error.required">
                            <spring:message code="index.tellusemail"/></div>
                        <div class="error-input" ng-show="formSendEmail.emailSendPassword.$error.email"><spring:message
                                code="index.validation.email"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="registration" ng-controller="RegistrationController" tabindex="-1" role="dialog"
     aria-labelledby="RegistrationModalLabel" aria-hidden="true">
    <form name="formReg" class="css-form" novalidate>
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title panel-heading text-center" id="RegistrationController"><spring:message
                            code="index.createanaccount"/></h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input type="text" ng-model="r_username" required="" pattern="[a-zA-Z0-9-]+" minlength="4"
                                   maxlength="15"
                                   name="r_username" id="r_username"
                                   class="form-control"
                                   placeholder="<spring:message code="index.enterusername"/>">
                        </div>
                        <div ng-show="formReg.$submitted">
                            <div class="error-input" ng-show="formReg.r_username.$error.required">
                                <spring:message code="index.tellususername"/>
                            </div>
                            <div class="error-input" ng-show="formReg.r_username.$error.minlength ||
                             formReg.r_username.$error.maxlength">
                                <spring:message code="index.validation.username"/>
                            </div>
                            <div class="error-input" ng-show="formReg.r_username.$error.pattern">
                                <spring:message code="index.validation.username.pattern"/>{{Object.keys(formReg.r_username.$error)}}
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                            <input type="email" maxlength="40" ng-model="r_email" required="" name="r_email" id="r_email"
                                   class="form-control"
                                   placeholder="<spring:message code="index.enteremail"/>">
                        </div>
                        <div ng-show="formReg.$submitted">
                            <div class="error-input" ng-show="formReg.r_email.$error.required"><spring:message
                                    code="index.tellusemail"/></div>
                            <div class="error-input" ng-show="formReg.r_email.$error.email"><spring:message
                                    code="index.validation.email"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" ng-model="r_password" id="r_password" repeat maxlength="20"
                                   minlength="4"
                                   required="" class="form-control"
                                   name="r_password"
                                   placeholder="<spring:message code="index.enterpassword"/>">
                        </div>
                        <div ng-show="formReg.$submitted">
                            <div class="error-input" ng-show="formReg.r_password.$error.required"><spring:message
                                    code="index.validation.password1"/>
                            </div>
                            <div class="error-input" ng-show="formReg.r_password.$error.maxlength ||
                            formReg.r_password.$error.minlength">
                                <spring:message code="index.validation.password2"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" id="repeatr_password" maxlength="20" minlength="4"
                                   required="" class="form-control"
                                   name="repeatr_password"
                                   placeholder="<spring:message code="index.repeatpassword"/>">
                        </div>
                        <div ng-show="formReg.$submitted">
                            <div class="error-input" ng-show="formReg.repeatr_password.$error.required"><spring:message
                                    code="index.validation.password1"/>
                            </div>
                            <div class="error-input" ng-show="formReg.repeatr_password.$error.maxlength ||
                            formReg.r_password.$error.minlength">
                                <spring:message code="index.validation.password2"/>
                            </div>

                            <div class="error-input" ng-show="formReg.r_password.$error.repeat">
                                <spring:message code="index.passwordnotequals"/>
                            </div>
                        </div>
                    </div>

                    <button type="submit" ng-click="registration(formReg.$valid)" class="btn btn-primary hundred">
                        <spring:message code="index.registration"/>
                    </button>
                </div>
                <div class="modal-footer text-center">
                    <spring:message code="index.singupforfree"/>

                </div>
            </div>
        </div>
    </form>
</div>

<div class="modal fade" id="error" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title panel-heading text-center"><spring:message code="index.error"/></h4>
            </div>
            <div id="error-content" class="modal-body">
            </div>
        </div>
    </div>
</div>
</html>