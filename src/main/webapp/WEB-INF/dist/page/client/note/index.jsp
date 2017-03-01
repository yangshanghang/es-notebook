<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>筑巢笔记</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body class="index-scss">
<jsp:include page="../common/header.jsp"/>

<div class="banner-bg">
    <div class="search-bar">
        <h3>筑巢笔记
            <span>一点一滴，从现在开始，一起笔记，一起筑巢！</span>
        </h3>
        <div class="search-item">
            <form action="<c:url value='/client/note/list'/>" id="js-form">
                <div class="search-ipt-bg">
                    <input type="input" placeholder="请输入搜索的关键字" id="queryParam" name="queryParam">
                </div>
                <div class="search-btn">
                    <i class="search-icon"></i>
                    <input type="button" id="query" value="搜索">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="mod info-box">
    <dl>
        <dt>最新<i class="new-icon"></i></dt>
        <c:forEach var="note" items="${map['new']}">
            <dd>
                <em></em>
                <a href="<c:url value='/client/note/detail/${note.id}'/>" class="ellips" target="_blank">
                    ${note.title}
                </a>
            </dd>
        </c:forEach>
    </dl>
    <dl>
        <dt>最热<i class="hot-icon"></i></dt>
        <c:forEach var="note" items="${map['hot']}">
            <dd>
                <em></em>
                <a href="<c:url value='/client/note/detail/${note.id}'/>" class="ellips" target="_blank">
                    ${note.title}
                </a>
            </dd>
        </c:forEach>
    </dl>
    <dl>
        <dt>推荐<i class="recommend-icon"></i></dt>
        <c:forEach var="note" items="${map['new']}">
            <dd>
                <em></em>
                <a href="<c:url value='/client/note/detail/${note.id}'/> " class="ellips" target="_blank">
                    ${note.title}
                </a>
            </dd>
        </c:forEach>
    </dl>
</div>
</body>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/note/controller/indexController.js?v=c66f5d3421'/>"></script>
</html>
