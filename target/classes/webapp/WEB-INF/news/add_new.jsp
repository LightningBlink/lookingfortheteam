<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="/js/ckeditor/ckeditor.js"></script>
<div>
    <form action="/admin/savenew" method="POST">
        <input name="id" type="hidden" value="{{new.id}}">
        <div class="form-group">
            <label for="header"><spring:message code="news.add_new.header"/></label>
            <input type="text" class="form-control" name="header" id="header" value="{{new.header}}" placeholder="Enter <spring:message code="news.add_new.header"/>">
        </div>
        <div class="form-group">
            <label for="editor1"><spring:message code="news.add_new.description"/></label>
            <textarea name="editor1" class="form-control" ng-model="new.description" id="editor1" rows="10" cols="80">
                <%--{{new.description}}--%>
            </textarea>
        </div>

        <div class="form-group">
            <button class="btn btn-default" type="submit"><spring:message code="news.add_new.publish"/></button>
        </div>
    </form>
</div>

<script>

    setTimeout(function(){
    CKEDITOR.replace('editor1', {
        filebrowserBrowseUrl: '/js/Filemanager-master/index.html'
    });
    },1500);

</script>