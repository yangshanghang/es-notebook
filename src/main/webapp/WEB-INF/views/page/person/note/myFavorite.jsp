<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
<head>
    <title>我的收藏</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css'/>" type="text/css">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=myFavorite"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="search-box">
        <input type="text" class="ipt-txt" placeholder="仅限标题模糊查询" id="queryParam">
        <input type="submit" class="ipt-btn" id="query" value="立即搜索">
    </div>
    <div class="contain mt20r20m0l20 bgcolorwhite">
        <div class="type js-allType">
            <li><a class="js-type cur" js-type="1">全部</a></li>
            <c:forEach items="${noteTypes}" begin="0" end="7" var="noteType">
                <li><a class="js-type ellips" js-category-id="${noteType.id}" title="${noteType.name}">${noteType.name}</a></li>
            </c:forEach>
            <c:if test="${noteTypes.size() > 8}">
                <li>
                    <a href="javascript:void(0)" class="more">+更多</a>
                    <!-- 更多内容 -->
                    <div class="more-type">
                        <c:forEach items="${noteTypes}" begin="8" var="noteType">
                            <a class="js-hiddenType ellips" js-category-id="${noteType.id}" title="${noteType.name}">${noteType.name}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:if>
        </div>
        <div class="tips">我们为您找到相关结果约<em id="itemCount">${pageInfo.total}</em>条</div>
        <div class="notebook-box">
            <div class="js-template">
                <c:if test="${pageInfo.total > 0}">
                    <ul class="notebook-list">
                        <c:forEach items="${pageInfo.list}" var="favorite">
                            <li>
                                <h3>
                                    <i class="star js-cancel" js-category-id="${favorite.id}"></i>
                                    <a href="<c:url value='/client/note/detail/${favorite.id}'/>"
                                       target="_blank">${favorite.title}</a>
                                    <c:if test="${favorite.attachmentFlag == '1'}">
                                        <span class="favorite-icon"></span>
                                    </c:if>
                                </h3>
                                <c:if test="${!empty favorite.scaleImgUrl && favorite.scaleImgUrl != '-1'}">
                                    <div class="thumbnail">
                                        <img src="${fn:substring(favorite.scaleImgUrl,0,fn:indexOf(favorite.scaleImgUrl,'&'))}">
                                    </div>
                                </c:if>
                                <p class="item-summary ellips_line3">${favorite.summary}</p>
                                <div class="item-foot">
                                    <span>分类：${favorite.categoryName}</span>
                                    <span>作者：${favorite.author}</span>
                                    <span>创建时间：<fmt:formatDate value="${favorite.createTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>编辑时间：<fmt:formatDate value="${favorite.operateTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>赞：${favorite.praiseCount}</span>
                                    <span>评论：${favorite.commentCount}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${pageInfo.total == 0}">
                    <div class="info-tip no-data-tip min-h800 center">
                        <%--<img src="<c:url value='/img/client/no-data-tip.png'/> " alt="">--%>
                        <p>很抱歉，没有找到相关结果</p>
                    </div>
                </c:if>
            </div>
            <!-- 分页 -->
            <div id="js-pagination" class="notice-pagination-box clearfix paging">
                <div id="pagination" class="pagination"></div>
            </div>
        </div>
    </div>
    <jsp:include page="../../client/common/footer.jsp"/>
</div>
</body>
<script id="listTemplate" type="text/x-handlebars-template">
    {{#ifCond list 'length' 0}}
        <div class="info-tip no-data-tip min-h500">
            <%--<img src="<c:url value='/img/client/no-data-tip.png'/> " alt="">--%>
            <p>很抱歉，没有找到相关结果</p>
        </div>
    {{else}}
    <ul class="notebook-list">
        {{#each list}}
        <li>
            <h3>
                <i class="star js-cancel" js-category-id="{{id}}"></i>
                <a href="<c:url value='/client/note/detail/{{id}}'/>" target="_blank">{{title}}</a>
                {{#ifCond attachmentFlag '==' 1 }}
                <span class="favorite-icon"></span>
                {{/ifCond}}
                </a>
            </h3>
            {{#ifCond scaleImgUrl '!=' -1}}
            <div class="thumbnail">
                <img src="{{substring scaleImgUrl}}">
            </div>
            {{/ifCond}}
            <div class="item-summary ellips_line3">{{summary}}</div>
            <div class="item-foot">
                <span>分类：{{categoryName}}</span>
                <span>作者：{{author}}</span>
                <span>创建时间：{{prettifyDate createTime}}</span>
                <span>编辑时间：{{prettifyDate operateTime}}</span>
                <span>赞：{{praiseCount}}</span>
                <span>评论：{{commentCount}}</span>
            </div>
        </li>
        {{/each}}
    </ul>
    {{/ifCond}}
</script>
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/person/note/controller/myFavoriteController.js'/>"></script>
</html>
