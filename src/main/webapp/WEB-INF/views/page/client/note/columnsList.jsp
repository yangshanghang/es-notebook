<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
<head>
    <title>专栏中心</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body>
<div class="main ml0">
    <jsp:include page="../common/header.jsp?cur=columns"/>
    <input type="hidden" id="js-categoryId" value="${categoryId}">
    <div class="jszx-search-outer">
        <div class="search-bar">
            <div class="search-item">
                <div class="search-ipt-bg">
                    <input type="input" name="queryParam" value="${queryParam}" placeholder="请输入搜索的关键字" id="queryParam"
                           maxlength="100">
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
                <ul>
                <li><a type="button" class="js-type <c:if test="${empty categoryId}"> cur </c:if>"  <c:if test="${empty categoryId}"> js-type="1" </c:if>>全部</a></li>
                <c:forEach items="${categoryList}" begin="0" end="6" var="category">
                    <li><a js-category-id="${category.id}"  class="js-type ellips" title="${category.name}">${category.name}</a></li>
                </c:forEach>
                <c:if test="${categoryList.size() > 7}">
                    <li>
                        <a class="more">+更多</a>
                        <div class="more-type" id="js-addType">
                            <c:forEach items="${categoryList}" begin="7" var="category">
                                <a js-category-id="${category.id}" class="js-hiddenType ellips" title="${category.name}">${category.name}</a>
                            </c:forEach>
                        </div>
                    </li>
                </c:if>
                </ul>
            </div>
            <div class="tips">我们共为您找到<em id="itemCount">${columnsDataDto.total}</em>篇专栏，耗时<em id="took">${columnsDataDto.took}</em>毫秒</div>
            <div class="notebook-box min-h500">
                <div class="js-template">
                    <c:if test="${columnsDataDto.total > 0}">
                        <ul class="notebook-list">
                            <c:forEach items="${columnsDataDto.hits}" var="data">
                                <li>
                                    <h3>
                                        <span class="column-icon-bg">专栏</span>
                                        <a href="<c:url value='/client/note/tocolumnnotelist/${data._id}'/>" target="_blank">
                                                ${data._source.name}
                                        </a>
                                    </h3>
                                    <p class="item-summary ellips_line3">${data._source.introduce}</p>
                                    <div class="item-foot">
                                        <span>分类：${data._source.categoryName}</span>
                                        <span>创建者：${data._source.author}</span>
                                        <span>创建时间：<fmt:formatDate value="${data._source.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                        <span>最新编辑时间：<fmt:formatDate value="${data._source.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                        <span>笔记数：${data._source.noteCount}</span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${columnsDataDto.total == 0}">
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
                <span class="column-icon-bg">专栏</span>
                <a href="<c:url value='/client/note/tocolumnnotelist/{{_id}}'/>" target="_blank">
                    {{{_source.name}}}
                </a>
            </h3>
            <p class="item-summary ellips_line3">{{{_source.introduce}}}</p>
            <div class="item-foot">
                <span>分类：{{{_source.categoryName}}}</span>
                <span>创建者：{{{_source.author}}}</span>
                <span>创建时间：{{prettifyDate _source.createTime}}</span>
                <span>最新编辑时间：{{prettifyDate _source.operateTime}}</span>
                <span>笔记数：{{_source.noteCount}}</span>
            </div>
        </li>
        {{/each}}
    </ul>
    {{/ifCond}}
</script>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/note/controller/columnsListController.js'/>"></script>
</html>
