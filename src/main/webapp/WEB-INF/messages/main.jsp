<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="panel panel-default" id="messagesDiv" ng-show="allMessages">
  <!-- Default panel contents -->
  <div class="panel-heading"><spring:message code="messages.main.notifications"/></div>

  <div class="panel-body">
    <table class="table">
      <thead>
      <tr>
        <td></td>
        <td><spring:message code="messages.main.from"/></td>
        <td><spring:message code="messages.main.message"/></td>
        <td></td>
      </tr>
      </thead>
      <tbody>
      <tr  ng-click="trClicked($index,message)" ng-repeat="message in allMessages" ng-mouseenter="enter='enter'" ng-mouseleave="enter=''"  ng-class="{ enter : enter , read : !message.read }  ">
      <td><a href="#"  data-toggle="modal" data-target="#removeMessage"><img width="15"  ng-click="forDeleteMessage(message, $index);" src="/images/icon/delete_icon.png"/></a></td>
      <td><a ng-href="/profile/{{message.userFrom}}" ng-click="$event.stopPropagation();"><img height="20" ng-src="/user/avatarimage?name={{message.userFrom}}">{{message.userFrom}}</a></td>
      <td>
        <p ng-bind-html="message.message"></p>
        <hr>
        <div collapse="message.isCollapsed" ng-click="$event.stopPropagation();">
          <div show class="well well-lg" style="height:250px; overflow-y: scroll;">
            <table id="response" style="table-layout: fixed; width:100%">
              <tr ng-repeat="correspondence in message.correspondences">
                <td class="received label label-primary" style="width: 20%;">{{correspondence.date | dateLocale:' yy.MM.dd HH:mm'}}
                </td><td class="received label label-info">{{correspondence.from}}
              </td><td style="word-wrap: break-word;"><span compile ng-bind-html="correspondence.message"></span>
              </td></tr>
            </table>
            <fieldset>
              <div>
                <input type="text" minlength="1" maxlength="500" ng-enter="messageSend(message.userFrom, messageInput, message.correspondences); messageInput = '';" ng-model="messageInput"
                       style="width: 100%;margin-bottom: 20px; line-height: 20px; margin-top:20px; word-wrap: break-word;"/>
              </div>
            </fieldset>
          </div>
        </div>
      </td>
      <td><span class="badge">{{message.unreadd}}</span></td>
      </tr>
      </tbody>
    </table>
  </div>

</div>
<div class="col-md-6 col-md-offset-3">
  <pagination boundary-links="true" ng-change="pageChanged()" total-items="bigTotalItems" ng-model="bigCurrentPage"
              max-size="maxSize" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;"
              first-text="&laquo;" last-text="&raquo;"></pagination>
</div>

<div class="modal fade" id="removeMessage" ng-controller="DeleteMessageController" tabindex="-1" role="dialog"
     aria-labelledby="DeleteMessageModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title panel-heading text-center" id="DeleteMessageController"><spring:message code="messages.main.confirmation"/></h4>
      </div>
      <div class="modal-body">

        <spring:message code="messages.main.douwanttoremovemessage"/>
      </div>
      <div class="modal-footer ng-scope">
        <button class="btn btn-primary" ng-click="deleteMessage()">OK</button>
        <button class="btn btn-warning" ng-click="cancelDeleteDialog()">Cancel</button>
      </div>
    </div>
  </div>
</div>
