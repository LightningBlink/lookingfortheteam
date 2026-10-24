<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><span ng-if="hasRole('ROLE_ADMIN')">
    <a href="/addnew"><spring:message code="news.news.addnew"/></a>
</span>

<div class="list-group">
    <div class="list-group-item" ng-repeat="new in news">
        <p class="text-right">
            <span ng-if="hasRole('ROLE_ADMIN')">
                <a href="#" ng-click="updateNew(new.id)"><spring:message code="news.news.update"/></a>
                <a href="#" ng-click="deleteNew(new.id)"><spring:message code="news.news.remove"/></a>
                <a href="#" ng-click="showNew(new.id)" ng-show="!new.show"><spring:message code="news.news.show"/></a>
            </span>
        </p>

        <h3 class="list-group-item-heading page-header margin5"><a href="/item/{{new.id}}">{{new.header}}</a></h3>

        <p class="list-group-item-text" ng-bind-html="new.description"></p>

        <p  class="list-group-item-text text-right date-color">{{new.date | dateLocale}} <spring:message code="news.news.createdby"/> <img height="17" src="/user/avatarimage?name={{new.authorName}}"/> {{new.authorName}}</p>
    </div>
</div>

<div class="col-md-6 col-md-offset-3">
    <pagination boundary-links="true" ng-change="pageChanged()" total-items="bigTotalItems" ng-model="bigCurrentPage"
                max-size="maxSize" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;"
                first-text="&laquo;" last-text="&raquo;"></pagination>
</div>
