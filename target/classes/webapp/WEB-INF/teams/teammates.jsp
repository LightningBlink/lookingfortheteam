<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
<div class="row">
    <p class="header-game text-center bg-info">{{game}}</p>

    <div class="col-sm-6">
        <div class="form-group">
            <label for="country" class="control-label"><spring:message code="teams.teammates.name"/></label>
            <div>
            <input type="text" class="form-control " placeholder="Username"
                   ng-model="keyword" aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="form-group">
            <label for="country" class="control-label"><spring:message code="teams.teammates.country"/></label>
            <div nationpicker  id="country" data-all="true"  ng-model="country" data-flags="true"
                 data-filter="true"></div>
        </div>
        <div>
            <button type="button" ng-click="searchTeammates(keyword, country)" class="btn btn-info"><spring:message code="teams.teammates.search"/></button>
        </div>
    </div>

</div>
<div>
    <table class="table text-center">
        <thead>
        <tr>
            <td>#</td>
            <td><spring:message code="teams.teammates.player"/></td>
            <td><spring:message code="teams.teammates.info"/></td>
            <td style="width: 30%;"><spring:message code="teams.teammates.actions"/></td>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="player in players">
            <td>
                {{(10 * (bigCurrentPage - 1)) + $index+1}}
            </td>
            <td>
                <img height="20" src="/user/avatarimage?name={{player.username}}"/> {{player.username}}
            </td>
            <td>{{getInfoPlayer(player, game)}}</td>
            <td>
                <a style="width: 100%;" ng-click="invite(player.username)" class="btn btn-default"><spring:message
                        code="teams.teammates.invite"/></a></br>
                <a style="width: 100%;" href="/profile/{{player.username}}" class="btn btn-default"><spring:message
                        code="teams.teammates.show"/></a>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="col-md-6 col-md-offset-3">
        <pagination boundary-links="true" ng-change="pageChanged()" total-items="bigTotalItems"
                    ng-model="bigCurrentPage"
                    max-size="maxSize" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;"
                    first-text="&laquo;" last-text="&raquo;"></pagination>
    </div>
</div>
</div>