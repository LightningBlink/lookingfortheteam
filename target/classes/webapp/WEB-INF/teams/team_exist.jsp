<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form  ng-init="avataruploaderror='<spring:message code="profile.error.upload"/>'" class="form-horizontal" ng-controller="AvatarUploaderTeam"
      flow-init="{target:'/user/uploadavatar?isTeam=true&teamName=' + info.team.name}" flow-name="obj.flow"
      flow-files-submitted="obj.flow.upload()" flow-file-progress="someHandlerMethod( $file, $flow )"  flow-file-added="!!{png:1,gif:1,jpg:1,jpeg:1}[$file.getExtension()]">
    <div class="page-header">
        <div class="row">

            <div class="col-md-3">
                <div class="form-group">
                    <div style="padding: 10px;">
                        <div class="text-center"><kbd><spring:message code="teams.team_exist.teamlogo"/></kbd></div>
                        <div class="thumbnail" ng-show="info.team.hasAvatar && obj.flow.files.length == 0">
                            <img src="/user/avatarimage?name={{info.team.name}}&isTeam=true"/>
                        </div>
                        <div class="thumbnail" ng-hide="info.team.hasAvatar">
                            <img src="/images/Group-512.png"/>
                        </div>
                        <div class="thumbnail" ng-show="obj.flow.files.length && !$flow.isUploading()">
                            <img flow-img="obj.flow.files[0]"/>
                        </div>
                        <div ng-if="info.leaderName == user.name" class="text-center">
                        <span style="width: 100%;" class="btn btn-info" ng-hide="info.team.hasAvatar" flow-btn
                              flow-attrs="{accept:'image/*'}"><spring:message
                                code="teams.team_exist.selectimage"/></span>
                        <span style="width: 100%;" class="btn btn-info" ng-show="info.team.hasAvatar" flow-btn
                              flow-attrs="{accept:'image/*'}"><spring:message code="teams.team_exist.change"/></span>
                            <%--<a href="#" class="btn btn-danger" ng-show="info.team.hasAvatar"--%>
                            <%--ng-click="removeImage()">--%>
                            <%--Remove--%>
                            <%--</a>--%>


                            <div class="progress progress-striped"  ng-show="$flow.isUploading() || $flow.progress() == 1" style="margin-top: 20px;">

                                <div class="progress-bar" role="progressbar"   aria-valuemin="0" aria-valuemax="100" ng-style="{width: ($flow.progress() * 100) + '%'}">
                                </div>
                            </div>
                                                            <span ng-if="info.leaderName == user.name">
                    <button style="width: 100%; margin-top: 10px;" data-toggle="modal" data-target="#leaveTeam"
                            ng-click="leaveTeamButton(game, info.team.id, info.leaderName)" class="btn btn-primary">
                        <spring:message code="teams.team_exist.removeteam"/></button>
                </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="form-group ">
                    <div>
                        <span class="col-md-9 font-title text-center">{{info.team.name}}</span>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="jumbotron" ng-show="info.team.aboutTeam" style="margin-left: 20px; margin-right: 20px;">
                    <span class="paragraph-text"> {{info.team.aboutTeam}}</span>
                </div>
            </div>
        </div>


    </div>
    <div class="row">
        <div class="col-md-5 well">

            <div class="form-group ">
                <label class="col-sm-3 control-label"><spring:message code="teams.team_exist.country"/> </label>

        <span class="col-sm-9 paragraph-text ng-binding ng-scope">       <img width="25"
                                                                              ng-src="/images/flags/{{info.team.nation.toLowerCase()}}.png"/> {{info.team.nation}} </span>
            </div>
            <div class="form-group ">
                <label class="col-sm-3 control-label"><spring:message code="teams.team_exist.teamleader"/></label>

                <span class="col-sm-9 paragraph-text ng-binding ng-scope">
                    <a ng-href="profile/{{info.leaderName}}"><img height="20"
                                                                  ng-src="/user/avatarimage?name={{info.leaderName}}">{{info.leaderName}}</a>
                    </span>
            </div>

            <div class="form-group ">
                <label class="col-sm-3 control-label"><spring:message code="teams.team_exist.datecreated"/></label>

                <span class="col-sm-9 paragraph-text ng-binding ng-scope"> {{info.team.date | date:'yyyy-MM-dd'}}</span>
            </div>


            <div ng-hide="info.games.length == 0 || user.name != info.leaderName ">
                <div class="form-group">
                    <button type="button" ng-click="addGame(gameSelect)" class="btn btn-primary"><spring:message
                            code="teams.team_exist.addgame"/></button>
                </div>

                <div class="form-group">
                    <select ng-model="gameSelect" class="form-control" ng-options="game.name for game in info.games">
                        <option value=""><spring:message code="teams.team_exist.choosethegame"/></option>
                    </select>
                </div>

            </div>
        </div>
        <div class="col-md-7">
            <div ng-show="authenticated" class="well">
                <form id="do-chat">
                    <h4 class="alert alert-success text-center"><spring:message code="teams.team_exist.teamchat"/></h4>

                    <div id="chat-div" style="height:250px; overflow-y: scroll;">
                        <table id="response" style="table-layout: fixed; width:100%;">
                            <tr ng-repeat="message in chatMessages[info.team.id]">
                                <td class="received label label-primary">{{message.received |
                                    date:'HH:mm'}}
                                </td>
                                <td class="received label label-info">
                                    <a ng-href="profile/{{message.sender}}">{{message.sender}}</a>
                                </td>
                                <td style="word-wrap: break-word;"><kbd>{{message.message}}</kbd>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <fieldset>
                        <div>
                            <input type="text" ng-enter="sendMessage(messageInput)" ng-model="messageInput"
                                   style="width: 100%;margin-bottom: 20px; line-height: 20px" id="message"/>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>

    <div class="page-header">
        <h2><spring:message code="teams.team_exist.recaps"/></h2>
    </div>

    <div class="panel panel-primary paragraph-text" ng-repeat="game in gamesHeader">
        <div class="panel-heading">
            <span style="font-size: 30px;"><img width="50" ng-src="/images/games/{{game.filePath}}.jpg"/> {{game.name}}</span>
            <a href="#" style="color: #333;">
                <span ng-if="user.name == info.leaderName && gamesHeader.length > 1" data-toggle="modal"
                      data-target="#removeGame" ng-click="removeGameFn(game.name, info.team.id, game.id)"
                      class="glyphicon glyphicon-remove block-right" aria-hidden="true"></span>
            </a>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <td>#</td>
                    <td></td>
                    <td><spring:message code="teams.team_exist.nickname"/></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="teammate in games[game.name]">
                    <td>
                        {{$index +1}}
                    </td>
                    <td class="text-center">
                        <img height="20"
                             ng-src="/user/avatarimage?name={{teammate.username}}">
                    </td>
                    <td>
                        <a ng-href="profile/{{teammate.username}}">{{teammate.username}}</a>

                    </td>
                    <td>
                        <%--{{teammate.post}}--%>
                    </td>
                    <td>
                        <span ng-if="teammate.username == user.name || user.name == info.leaderName ">
                            <button data-toggle="modal" data-target="#leaveTeam"
                                    ng-click="leaveTeamButton(game.name, info.team.id, teammate.username)"
                                    class="btn btn-primary"><spring:message code="teams.team_exist.leave"/></button>
                        </span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>

<div class="modal fade" id="leaveTeam" ng-controller="LeaveTeamController" tabindex="-1" role="dialog"
     aria-labelledby="LeaveTeamModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title panel-heading text-center" id="LeaveTeamController"><spring:message
                        code="teams.team_exist.confirmation"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="teams.team_exist.douwanttoleave"/>
            </div>
            <div class="modal-footer ng-scope">
                <button class="btn btn-primary" ng-click="leaveTeam()"><spring:message
                        code="teams.team_exist.ok"/></button>
                <button class="btn btn-warning" ng-click="cancelLeaveTeam()"><spring:message
                        code="teams.team_exist.cancel"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="removeGame" ng-controller="RemoveGameController" tabindex="-1" role="dialog"
     aria-labelledby="RemoveGameModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title panel-heading text-center" id="RemoveGameController"><spring:message
                        code="teams.team_exist.confirmation"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="teams.team_exist.douwanttoremovethisgame"/>
            </div>
            <div class="modal-footer ng-scope">
                <button class="btn btn-primary" ng-click="removeGameModal()"><spring:message
                        code="teams.team_exist.ok"/></button>
                <button class="btn btn-warning" ng-click="cancelRemoveGameModal()"><spring:message
                        code="teams.team_exist.cancel"/></button>
            </div>
        </div>
    </div>
</div>
