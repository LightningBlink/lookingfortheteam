<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form ng-if="userInfo" ng-init="avataruploaderror='<spring:message code="profile.error.upload"/>'" id="profileForm"  name="formProfile" novalidate>


  <div class="row">
    <div class="col-md-9 form-horizontal">
      <div class="form-group">
        <label for="name" class="col-sm-3 control-label"><spring:message code="profile.found.name"/> </label>

        <div class="col-sm-9">
          <input ng-if="user.name == userInfo.username"  minlength="2" maxlength="20" type="text" class="form-control" ng-model="userInfo.name" name="name" id="name" placeholder="<spring:message code="profile.found.name"/>">
          <span ng-if="user.name != userInfo.username" class="paragraph-text">{{userInfo.name}}</span>

          <div ng-show="formProfile.$submitted">
            <div class="error-input" ng-show="formProfile.name.$error.minlength ||
                             formProfile.name.$error.maxlength">
              <spring:message code="profile.found.validation.name"/>
            </div>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="surname" class="col-sm-3 control-label"><spring:message code="profile.found.surname"/></label>

        <div class="col-sm-9">
          <input type="text" ng-if="user.name == userInfo.username"   minlength="2" maxlength="20" class="form-control" ng-model="userInfo.surname" name="surname" id="surname" placeholder="<spring:message code="profile.found.surname"/>">
          <span ng-if="user.name != userInfo.username" class="paragraph-text">{{userInfo.surname}}</span>

          <div ng-show="formProfile.$submitted">
            <div class="error-input" ng-show="formProfile.surname.$error.minlength ||
                             formProfile.name.$error.maxlength">
              <spring:message code="profile.found.validation.surname"/>
            </div>

          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="country" class="col-sm-3 control-label"><spring:message code="profile.found.country"/></label>

        <div ng-if="user.name == userInfo.username" nationpicker id="country"  class="col-sm-9" ng-model="userInfo.country"  data-flags="true" data-filter="true"></div>
        <span ng-if="user.name != userInfo.username"><img  class="country-flag" ng-src="/images/flags/{{userInfo.country.toLowerCase()}}.png"/><span class="paragraph-text">{{countryName}}</span></span>
      </div>
      <div class="form-group">
        <label for="city" class="col-sm-3 control-label"><spring:message code="profile.found.city"/></label>

        <div class="col-sm-9">
          <input ng-if="user.name == userInfo.username" type="text"   minlength="2" maxlength="30"  class="form-control" ng-model="userInfo.city" name="city" id="city" placeholder="<spring:message code="profile.found.city"/>">
          <span ng-if="user.name != userInfo.username" class="paragraph-text">{{userInfo.city}}</span>

          <div ng-show="formProfile.$submitted">
            <div class="error-input" ng-show="formProfile.city.$error.minlength ||
                             formProfile.name.$error.maxlength">
              <spring:message code="profile.found.validation.city"/>
            </div>

          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="aboutMe" class="col-sm-3 control-label"><spring:message code="profile.found.aboutme"/></label>

        <div class="col-sm-9">
          <textarea ng-if="user.name == userInfo.username"  maxlength="254" style="resize: none;height: 120px;" class="form-control"  ng-model="userInfo.aboutMe" name="aboutMe" id="aboutMe"
                    placeholder="<spring:message code="profile.found.aboutme"/>"></textarea>
          <span ng-if="user.name != userInfo.username" class="paragraph-text">{{userInfo.aboutMe}}</span>

          <div ng-show="formProfile.$submitted">
            <div class="error-input" ng-show="formProfile.aboutMe.$error.minlength ||
                             formProfile.aboutMe.$error.maxlength">
              <spring:message code="profile.found.validation.aboutme"/>
            </div>

          </div>
        </div>
      </div>
    </div>
    <div class="col-md-3 form-horizontal">
      <div class="form-group">
        <div style="padding: 10px;" ng-controller="AvatarUploaderUser" flow-init  flow-name="obj.flow" flow-file-progress flow-files-submitted="obj.flow.upload()" flow-file-added="true" >
          <div class="text-center"><kbd><spring:message code="profile.found.avatar"/></kbd></div>
          <div class="thumbnail" ng-show="userInfo.hasAvatar && obj.flow.files.length == 0">
            <img src="/user/avatarimage?name={{userInfo.username}}"/>
          </div>
          <div class="thumbnail" ng-hide="userInfo.hasAvatar">
            <img src="/images/avatar.png"/>
          </div>
          <div class="thumbnail" ng-show="obj.flow.files.length">
            <img flow-img="obj.flow.files[0]"/>
          </div>
          <div ng-if="user.name == userInfo.username" class="text-center">
                        <span class="btn btn-info" style="width: 100%;" ng-hide="userInfo.hasAvatar" flow-btn
                              flow-attrs="{accept:'image/*'}"><spring:message code="profile.found.selectimage"/></span>
                        <span class="btn btn-info" style="width: 100%;" ng-show="userInfo.hasAvatar" flow-btn
                              flow-attrs="{accept:'image/*'}"><spring:message code="profile.found.change"/></span>
            <a href="#" class="btn btn-danger" style="width: 100%;"  ng-show="userInfo.hasAvatar"
               ng-click="removeImage()">
              <spring:message code="profile.found.remove"/>
            </a>
          </div>
          <div class="progress progress-striped"  ng-show="$flow.isUploading() || $flow.progress() == 1" style="margin-top: 20px;">

            <div class="progress-bar" role="progressbar"   aria-valuemin="0" aria-valuemax="100" ng-style="{width: ($flow.progress() * 100) + '%'}">
            </div>
          </div>
        </div>
      </div>

      <%--<div class="form-group">--%>
        <%--<div ng-if="user.name == userInfo.username" class="checkbox text-center">--%>
          <%--<label>--%>
            <%--<input type="checkbox" ng-model="userInfo.male">--%>
            <%--<spring:message code="profile.found.sex"/>--%>
          <%--</label>--%>
        <%--</div>--%>
                <%--<span style="margin: 40px;" ng-if="user.name != userInfo.username">--%>
                    <%--<label class="control-label"><spring:message code="profile.found.sex"/> : </label>--%>
                    <%--<span  ng-if="userInfo.male" class="paragraph-text"><spring:message code="profile.found.man"/></span>--%>
                    <%--<span  ng-if="!userInfo.male" class="paragraph-text"><spring:message code="profile.found.woman"/></span>--%>
                <%--</span>--%>
      <%--</div>--%>
    </div>
  </div>

  <div>

    <span ng-if="userInfo.username != user.name &&  user.name != 'ANONYMOUS_USER'" class="btn btn-primary" data-toggle="modal" data-target="#message"><spring:message code="profile.found.writemessage"/></span>
  </div>

  <div>
    <div  class="form-inline" ng-hide="allGames.length == 0 || user.name != userInfo.username">
      <div class="form-group">
        <button type="button" ng-click="addGame(selectGame)" class="btn btn-primary"><spring:message code="profile.found.addgame"/></button>
      </div>

      <div class="form-group" >
        <select ng-model="gameSelect" class="form-control" ng-options="gameInfo.name for gameInfo in allGames">
          <option value=""><spring:message code="profile.found.choosethegame"/></option>
        </select>
      </div>
    </div>
    <div>
      <div class="margin-top20">
        <div class="form-group">
          <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default" id="gopa{{$index}}" ng-repeat="userGame in userInfo.userGames">
              <div class="panel-heading" role="tab" id="heading{{$index}}">
                <h4 class="panel-title">
                  <a role="button" data-toggle="collapse" style="cursor: pointer" data-parent="#accordion" ng-click="collapseMethod($event)"
                     aria-expanded="false" aria-controls="collapse{{$index}}">
                    {{userGame.pk.game.name}}

                    <span ng-if="user.name == userInfo.username"  ng-click="removeGame(userGame)" class="glyphicon glyphicon-remove block-right" aria-hidden="true"></span>
                  </a>
                                      <span style="float: right; margin-right: 20px;"  ng-show="formProfile.$submitted">
                                    <span class="error-input" ng-show="formProfile.nickname{{$index}}.$error.required">
                                     <spring:message code="profile.found.validation.nickname"/>
                                    </span>
                   <span class="error-input" ng-show="formProfile.nickname{{$index}}.$error.minlength">
                                     <spring:message code="profile.found.validation.nickname.length"/>
                                    </span>
                       <span class="error-input" ng-show="formProfile.nickname{{$index}}.$error.maxlength">
                                     <spring:message code="profile.found.validation.nickname.length"/>
                                    </span>
                    </span>
                </h4>
              </div>
              <div id="collapse{{$index}}" class="panel-collapse collapse" role="tabpanel"
                   aria-labelledby="heading{{$index}}">
                <div class="panel-body">
                  <div class="row">
                    <label class="col-sm-4 control-label font-help-text-game">{{userGame.pk.game.nickname.toUpperCase()}}</label>

                    <div class="col-sm-4">
                      <input style="margin-top: -5px; " ng-if="user.name == userInfo.username" type="text" name="nickname{{$index}}" required="" minlength="2" maxlength="30" class="form-control" ng-model="userGame.account"
                             placeholder="{{userGame.pk.game.nickname}}">
                      <span ng-if="user.name != userInfo.username">{{userGame.account}}</span>
                    </div>
                  </div>
                  <div class="row margin-top20">
                    <label class="col-sm-4 control-label font-help-text-game">
                      <spring:message code="profile.found.exemplaryskill"/></label>

                    <div class="col-sm-8">
                      <input style="margin-top: -5px; " ng-if="user.name == userInfo.username" type="text" name="info{{$index}}" maxlength="100"  class="form-control" ng-model="userGame.info">
                      <span ng-if="user.name != userInfo.username">{{userGame.info}}</span>
                    </div>

                  </div>
                  <div class="row margin-top20">
                    <label class="col-sm-4 control-label font-help-text-game">
                      <spring:message code="profile.found.team"/></label>

                    <div class="col-sm-8">
                      <img height="20" ng-src="/user/avatarimage?name={{userGame.clan.name}}&amp;isTeam=true"> <a ng-href="team/{{userGame.clan.id}}">{{userGame.clan.name}}</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div ng-if="user.name == userInfo.username" class="form-group">
      <button  type="submit" class="btn btn-primary btn-block" style="width: 30%; margin: auto;"  ng-click="saveUser(formProfile.$valid)"><spring:message code="profile.found.save"/></button>
    </div>
  </div>
</form>
<div style="margin-bottom: 50px;">
  <a ng-show="user.name == userInfo.username" ng-init="isPrivacyShow=false;" style="float: right; display: block;" href="#" ng-click="isPrivacyShow=!isPrivacyShow;"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"> <spring:message code="profile.found.privacy"/> </span></a>
</div>
<div class="panel-group" id="changepass" ng-show="isPrivacyShow"  ng-if="userInfo.username == user.name" role="tablist" >
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOneChangePass">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse"  ng-click="collapseChangePass()" href="#"  aria-expanded="false" aria-controls="collapseOneChangePass">
          <spring:message code="profile.found.changepass"/>
        </a>
      </h4>
    </div>
    <div id="collapseOneChangePass"  class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneChangePass">
      <div class="panel-body">
        <form name="changePassForm" ng-submit="changePassToServer(changePassForm.$valid)" novalidate>
          <div class="input-group input-group-sm">
            <span class="input-group-addon" ><spring:message code="profile.found.oldpass"/></span>
            <input type="password"  required="" minlength="4" maxlength="20" class="form-control" ng-model="changePassObj.oldpass" name="passold">
            <span class="input-group-addon" ><spring:message code="profile.found.newpass"/></span>
            <input type="password" required="" minlength="4" maxlength="20" class="form-control" ng-model="changePassObj.newpass" repeat id="passnew" name="passnew">
            <span class="input-group-addon" ><spring:message code="profile.found.repeatnewpass"/></span>
            <input type="password"  required="" minlength="4" maxlength="20" class="form-control" ng-model="changePassObj.repeatnewpass" id="repeatpassnew" name="passrepeatnew">
          </div>
          <button style="width: 100%;" type="submit"  class="btn btn-default"><spring:message code="profile.found.change"/></button>
          <div  ng-show="changePassForm.$submitted" style="margin-top: 30px; text-align: center;">
                                    <span class="error-input" ng-show="changePassForm.$invalid && !changePassForm.passnew.$error.repeat">
                                      <spring:message code="index.validation.password2"/>
                                    </span>
              <span class="error-input" ng-show="changePassForm.passnew.$error.repeat">
<spring:message code="profile.found.passwordequals"/>
                                    </span>

          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal" id="message" tabindex="-1" role="dialog"
     aria-labelledby="MessageModalLabel" aria-hidden="true">
  <form name="formMessage" class="css-form" novalidate>
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">&times;</span></button>
          <h4 class="modal-title panel-heading text-center" id="MessageModalLabel"><spring:message code="profile.found.writyouremessage"/> </h4>
        </div>
        <div class="modal-body">

          <div>
            <textarea  maxlength="250" style="resize: none;height: 120px;" class="form-control"  required="" ng-model="message" name="message" id="messageText"
                       placeholder="Message"></textarea>

            <div ng-show="formMessage.$submitted">
              <div class="error-input" ng-show="formMessage.message.$error.required">
                <spring:message code="profile.found.textofthemessage"/>
              </div>

            </div>
            <button type="submit" ng-click="messageSend(formMessage)" class="btn btn-primary hundred">
              <spring:message code="profile.found.send"/>
            </button>
          </div>
        </div>
      </div>
    </div>
  </form>
</div>