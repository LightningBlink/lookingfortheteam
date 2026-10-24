<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div ng-if="!userInfo" class="alert alert-danger" role="alert">

    <spring:message code="profile.not_found"/>
</div>