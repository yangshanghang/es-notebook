<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
<head>
    <title>检索中心</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body class="list-scss">

<div class="main ml0">
    <jsp:include page="../common/header.jsp?cur=index"/>

    <div class="jszx-search-outer">
        <div class="search-bar">
            <div class="search-item">
                <div class="search-ipt-bg">
                    <input type="input" name="queryParam" value="${queryParam}" placeholder="请输入搜索的关键字" id="queryParam" maxlength="100">
                </div>
                <div class="search-btn">
                    <i class="search-icon"></i>
                    <input type="button" id="query" value="搜索">
                </div>
            </div>
        </div>
    </div>
    <div class="mod-1200 contain clearfix">
        <div class="list-con fl mod-930 mr20">
            <div class="type js-allType">
                <li><a type="button" class="js-type cur" js-type="1">全部</a></li>
                <c:forEach items="${noteTypes}" begin="0" end="6" var="noteType">
                    <li><a js-category-id="${noteType.id}"  class="js-type ellips" title="${noteType.name}">${noteType.name}</a></li>
                </c:forEach>
                <c:if test="${noteTypes.size() > 7}">
                    <li>
                        <a class="more">+更多</a>
                        <div class="more-type">
                            <c:forEach items="${noteTypes}" begin="7" var="noteType">
                                <a js-category-id="${noteType.id}" class="js-hiddenType ellips" title="${noteType.name}">${noteType.name}</a>
                            </c:forEach>
                        </div>
                    </li>
                </c:if>
            </div>
            <div class="tips">我们共为您找到<em id="itemCount">${dataDto.total}</em>篇笔记，耗时<em id="took">${dataDto.took}</em>毫秒</div>
            <div class="notebook-box min-h500">
                <div class="js-template">
                    <c:if test="${dataDto.total > 0}">
                        <ul class="notebook-list">
                            <c:forEach items="${dataDto.hits}" var="data">
                            <li>
                                <h3 class="ellips">
                                    <span class="note-icon-bg"><span class="note-icon"></span></span>
                                    <a href="<c:url value='/client/note/detail/${data._id}'/>" target="_blank" title="${data._source.title}">
                                        ${data._source.title}
                                        <c:if test="${data._source.attachmentFlag == '1'}">
                                            <span class="favorite-icon"></span>
                                        </c:if>
                                    </a>
                                </h3>
                                <c:if test="${!empty data._source.scaleImgUrl && data._source.scaleImgUrl != '-1'}">
                                    <div class="thumbnail">
                                        <img class="img-thumbnail" src="${fn:substring(data._source.scaleImgUrl,0,fn:indexOf(data._source.scaleImgUrl,'&'))}">
                                    </div>
                                </c:if>
                                <p class="item-summary ellips_line3">${data._source.summary}</p>
                                <div class="item-foot">
                                    <span> 分类：${data._source.category.name}</span>
                                    <span>创建者：${data._source.author}</span>
                                    <span>创建时间：<fmt:formatDate value="${data._source.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>最新编辑时间：<fmt:formatDate value="${data._source.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>赞：${data._source.praiseCount}</span>
                                    <span>评论：${data._source.commentCount}</span>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${dataDto.total == 0}">
                        <div class="info-tip no-data-tip pdt120 center">
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
        <div class="fl mod-240 sidebar-min-h related-articles">
            <jsp:include page="../common/right.jsp"/>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</div>
</body>
<script id="listTemplate" type="text/x-handlebars-template">
    {{#ifCond hitsList 'length' 0}}
        <div class="info-tip no-data-tip js-template pdt120">
            <%--<img src="<c:url value='/img/client/no-data-tip.png'/> " alt="">--%>
            <p>很抱歉，没有找到相关结果</p>
        </div>
    {{else}}
    <ul class="notebook-list">
        {{#each hitsList}}
        <li>
            <h3>
                <span class="note-icon-bg"><span class="note-icon"></span></span>
                <a href="<c:url value='/client/note/detail/{{_id}}'/>" target="_blank">
                    {{{_source.title}}}
                    {{#ifCond _source.attachmentFlag '==' 1 }}
                    <span class="favorite-icon"></span>
                    {{/ifCond}}
                </a>
            </h3>
            {{#ifCond _source.scaleImgUrl '!=' -1}}
            <div class="thumbnail">
                <img class="img-thumbnail" src="{{substring _source.scaleImgUrl}}">
            </div>
            {{/ifCond}}
            <p class="item-summary ellips_line3">{{{_source.summary}}}</p>
            <div class="item-foot">
                <span>分类：{{{_source.category.name}}}</span>
                <span>创建者：{{{_source.author}}}</span>
                <span>创建时间：{{prettifyDate _source.createTime}}</span>
                <span>最新编辑时间：{{prettifyDate _source.operateTime}}</span>
                <span>赞：{{_source.praiseCount}}</span>
                <span>评论：{{_source.commentCount}}</span>
            </div>
        </li>
        {{/each}}
    </ul>
    {{/ifCond}}
</script>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/note/controller/listController.js'/>"></script>
</html>
