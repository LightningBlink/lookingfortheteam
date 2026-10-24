<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form name="formTeam" novalidate>
    <div class="row">
        <div class="col-md-9 form-horizontal">
            <div class="form-group">
                <label for="name" class="col-sm-4 control-label"><spring:message code="teams.createteam.nameofteam"/> </label>

                <div class="col-sm-8">
                    <input  required="" minlength="2" maxlength="30"  type="text"
                           class="form-control" ng-model="team.name"  name="name" id="name" placeholder="<spring:message code="teams.teammates.name"/>">


                    <div ng-show="formTeam.$submitted">
                        <div class="error-input" ng-show="formTeam.name.$error.required">
                            <spring:message code="teams.createteam.tellusyouteamname"/>

                        </div>
                        <div class="error-input" ng-show="formTeam.name.$error.minlength ||
                             formTeam.name.$error.maxlength">
                            <spring:message code="teams.createteam.validation.teamname"/>
                        </div>
                    </div>
                </div>
            </div>
                <div class="form-group">
                    <label for="aboutTeam" class="col-sm-4 control-label"><spring:message code="teams.createteam.aboutteam"/></label>

                    <div class="col-sm-8">
                        <textarea   style="resize: none; height: 120px;"  maxlength="200" required=""
                                class="form-control" ng-model="team.aboutTeam" name="aboutTeam" id="aboutTeam" placeholder="<spring:message code="teams.createteam.aboutteam"/>"></textarea>


                        <div ng-show="formTeam.$submitted">
                            <div class="error-input" ng-show="formTeam.aboutTeam.$error.required">
                                <spring:message code="teams.createteam.validation.aboutteam"/>
                            </div>

                            <div class="error-input" ng-show="formTeam.aboutTeam.$error.minlength ||
                             formTeam.aboutTeam.$error.maxlength">
                                <spring:message code="teams.createteam.validation.aboutteamlimit"/></div>
                        </div>
                    </div>
                </div>
            <div class="form-group">
                <label for="country" class="col-sm-4 control-label"><spring:message code="teams.createteam.teamnation"/></label>
                <div class="col-sm-8" nationpicker id="country" ng-model="team.nation"   data-flags="true" data-filter="true"></div>
            </div>
            </div>
        </div>

    <div class="text-center">
        <button  type="submit" class="btn btn-primary" ng-click="saveTeam(formTeam.$invalid)"><spring:message code="teams.createteam.save"/></button>
    </div>

</form>

<script src="/js/form-helpres/dist/js/bootstrap-formhelpers.js"></script>