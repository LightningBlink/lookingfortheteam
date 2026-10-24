<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div ng-controller="PostsController" ng-init="animationPage='template1.html'">
    <div class="alert alert-info" role="alert">
        <spring:message code="posts.title"/></div>
    <ul class="nav nav-tabs">
        <li role="presentation" id="template1" class="active"><a ng-click="animatePathMethod('template1')" href="#"><img alt="dota 2" src="/images/icon/dota2.png"
                                                                            width="25"></a></li>
        <li role="presentation" id="template2"><a ng-click="animatePathMethod('template2')" href="#"><img alt="league of legends" src="/images/icon/lol.png" width="25"></a></li>
        <li role="presentation" id="template3"><a ng-click="animatePathMethod('template3')" href="#"><img alt="cs go" src="/images/icon/csgo.png" width="25"></a></li>
        <li role="presentation" id="template4"><a ng-click="animatePathMethod('template4')" href="#"><img alt="hots" src="/images/icon/hots.png" width="25"></a></li>
        <li role="presentation" id="template5"><a ng-click="animatePathMethod('template5')" href="#"><img alt="wot" src="/images/icon/wot.png" width="25"></a></li>
    </ul>

    <div>
        <div ng-include="animationPage"></div>
    </div>
</div>
