<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>分类关注</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css?v=93fdaee35d'/>" type="text/css">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=myFocus"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="contain mt20r20m0l20 bgcolorwhite mt103">
        <div class="notebook-box favorite-box ">
            <h1 class="h1-title hr">我的关注</h1>
            <ul class="favorite-list clearfix connectedSortable" id="js-myFocus">
                <c:forEach var="focus" items="${map.focus}">
                    <li data-id= ${focus.id}>
                        <p title="${focus.name}" class="ellips">${focus.name}</p>
                        <span>(${focus.noteCount}篇)</span>
                    </li>
                </c:forEach>
            </ul>
            <h1 class="h1-title hr">推荐关注</h1>
            <ul class="favorite-list clearfix connectedSortable" id="js-allFocus">
                <c:forEach var="focus" items="${map.other}">
                    <li data-id= ${focus.id}>
                        <p title="${focus.name}" class="ellips">${focus.name}</p>
                        <span>(${focus.noteCount}篇)</span>
                    </li>
                </c:forEach>
            </ul>
            <p class="center mt10">
                <button class="btn" id="save">确定</button>
            </p>
        </div>
    </div>
    <jsp:include page="../../client/common/footer.jsp"/>
</div>
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/person/note/controller/myFocusController.js?v=70db43e01a'/>"></script>
</body>
</html>
