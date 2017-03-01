<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>录入修改笔记</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css?v=93fdaee35d'/>">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=addNote"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="contain mt20r20m0l20 bgcolorwhite mt103">
        <input type="hidden" id="typeId" value="${note.category.id}" />
        <form id="js-form">
            <input type="hidden" id="content" value="${note.content}"/>
            <input type="hidden" name="id" id="js-indexId" value="${indexId}">
            <input type="hidden" name="author" value="${SESSION_USER_INFO.realName}">
            <input type="hidden" name="userId" value="${SESSION_USER_INFO.id}">
            <input type="hidden" name="createTime"
                   value="<fmt:formatDate value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
            <input type="hidden" name="praiseCount" value="${note.praiseCount}"/>

            <div class="notebook-box edit-notebook">
                <h1 class="h1-title center"><i class=${indexId == null?"icon-add":"icon-edit2"}></i>${indexId == null?'记录笔记':'修改笔记'}</h1>
                <ul class="form-edit">
                    <li><input type="text" name="title" id="inputTitle" class="ipt-txt ipt-input" maxlength="50" value="${note.title}"  placeholder="请输入笔记标题"/></li>
                    <li><textarea  name="summary"  id="inputSummary" class="ipt-txt ipt-textarea"  maxlength="400" placeholder="请输入笔记摘要">${note.summary}</textarea></li>
                    <li><script id="container" name="content" type="text/plain"></script></li>
                </ul>

                <div class="type js-allType">
                    <c:forEach items="${categoryList}" begin="0" end="7" varStatus="status"  var="noteType">
                        <li>
                            <a js-category-id="${noteType.id}" href="javascript:void(0)" class="js-type ellips" title="${noteType.name}">
                             ${noteType.name}
                            </a>
                        </li>
                    </c:forEach>
                    <c:if test="${categoryList.size() > 8}">
                        <li>
                            <a href="javascript:void(0)" class="more">+更多</a>

                            <div  class="more-type" id="js-addType">
                                <c:forEach items="${categoryList}" begin="8" var="noteType">
                                    <a js-category-id="${noteType.id}" href="javascript:void(0)" class="js-hiddenType ellips" title="${noteType.name}">${noteType.name}</a>
                                </c:forEach>
                            </div>
                        </li>
                    </c:if>
                </div>
                <p>
                    <input id="label-visibleStatus0" type="radio" name="visibleStatus" value="0" <c:if test="${note.visibleStatus==0 || empty note}">checked="checked"</c:if> ><label for="label-visibleStatus0"> 公开 </label>
                    <input id="label-visibleStatus1" type="radio" name="visibleStatus" value="1" <c:if test="${note.visibleStatus==1}">checked="checked"</c:if> > <label for="label-visibleStatus1"> 仅自己可见 </label>
                </p>
                <p class="center mt20">
                    <input type="button" class="btn" id="js-submit" value=${indexId == null?'录入':'修改'} />
                    <input type="button" class="btn" id="cancel" value="取消" />
                </p>
            </div>
        </form>
    </div>
    <jsp:include page="../../client/common/footer.jsp"/>
</div>
</div>
<jsp:include page="../../client/common/common.jsp"/>
<script>
    // 默认设置粘贴板为全局变量
    require(['zeroClipboard'], function (ZeroClipboard) {
        window['ZeroClipboard'] = ZeroClipboard;
    });
</script>
<script src="<c:url value='/js/person/note/controller/addAndEditController.js?v=10b08494fe'/>"></script>
</body>
</html>