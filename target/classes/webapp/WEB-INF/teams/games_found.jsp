<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table class="table table-striped">
  <thead>
  <tr>
    <th> </th>
    <th class="games-header-th"><spring:message code="teams.games_found.games"/></th>
    <th class="games-header-th"></th>
    <th></th>
  </tr>
  </thead>
  <tbody>
  <tr ng-repeat="game in games | orderBy:'name'">

    <td><img width="100" ng-src="/images/games/{{game.filePath}}.jpg"/></td>
    <td class="games-header">{{game.name}}</td>
    <td class="text-center games-header">
      <a style="width: 100%;" ng-href="looking/team/{{game.name}}" class="btn  btn-default"><spring:message code="teams.games_found.findtheteam"/> </a></br>
      <a style="width: 100%;" ng-href="looking{{game.hasTeam.link}}" class="btn  btn-default">{{game.hasTeam.message ? '<spring:message code="teams.games_found.createteam"/>' : '<spring:message code="teams.games_found.findtheteammate"/>'}}</a>
    </td>
  </tr>
  </tbody>
</table>