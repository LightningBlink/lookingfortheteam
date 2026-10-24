<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2 class="lead page-header margin5">{{entity.header}}</h2  >

    <p ng-bind-html="entity.description"></p>

    <p  class=" text-right date-color">{{entity.date | dateLocale:'yyyy-MM-dd HH:mm'}} <spring:message code="news.news.createdby"/> {{entity.authorName}}</p>
</div>