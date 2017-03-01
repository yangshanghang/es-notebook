<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <title>我的专栏</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css'/>">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=myColumns"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="search-box">
        <input type="text" class="ipt-txt" placeholder="请输入搜索的关键字" id="queryParam" value="${queryParam}"
               maxlength="100">
        <input type="button" class="ipt-btn" id="query" value="立即搜索">
    </div>
    <div class="contain mt20r20m0l20 bgcolorwhite">
        <div class="type js-allType">
            <ul>
                <li><a type="button" class="js-type cur" js-type="1">全部</a></li>
                <c:forEach items="${categoryList}" begin="0" end="7" var="category">
                    <li><a js-category-id="${category.id}" class="js-type ellips"
                           title="${category.name}">${category.name}</a></li>
                </c:forEach>
                <c:if test="${categoryList.size() > 8}">
                    <li>
                        <a class="more">+更多</a>
                        <div class="more-type">
                            <c:forEach items="${categoryList}" begin="8" var="category">
                                <a js-category-id="${category.id}" class="js-hiddenType ellips"
                                   title="${category.name}">${category.name}</a>
                            </c:forEach>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="tips">
            我们共为您找到<em id="itemCount">${columnsDataDto.total}</em>篇笔记，耗时<em id="took">${columnsDataDto.took}</em>毫秒
        </div>

        <div class="notebook-box min-h500">
            <div class="add-column clearfix">
                <a href="<c:url value='/person/columns/toadd'/>" class="fl columns-add"><b>+</b>新建专栏</a>
            </div>
            <div class="js-template">
                <ul class="notebook-list">
                    <c:if test="${columnsDataDto.total > 0}">
                        <c:forEach items="${columnsDataDto.hits}" var="data">
                            <li>
                                <h3>
                                    <a href="toedit/${data._id}"><i class="edit"></i></a>
                                    <a class="js-delete cursor" js-id="${data._id}"><i class="del"></i></a>
                                    <a href="<c:url value='/client/note/tocolumnnotelist/${data._id}'/>" target="_blank">
                                            ${data._source.name}
                                        <c:if test="${data._source.visibleStatus == '1'}">
                                            <img src="<c:url value='/img/client/lock.png' /> "/>
                                        </c:if>
                                    </a>
                                </h3>
                                <p class="item-summary ellips_line3">${data._source.introduce}</p>
                                <div class="item-foot">
                                    <span>分类：${data._source.categoryName}</span>
                                    <span>创建者：${data._source.author}</span>
                                    <span>创建时间：<fmt:formatDate value="${data._source.createTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>最新编辑时间：<fmt:formatDate value="${data._source.operateTime}"
                                                                 pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>笔记数：${data._source.noteCount}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
                <c:if test="${columnsDataDto.total == 0}">
                    <div class="info-tip no-data-tip min-h500 pdt120 center">
                        <%--<img src="<c:url value='/img/client/no-data-tip.png'/> " alt="">--%>
                        <p>很抱歉，没有找到相关结果</p>
                    </div>
                </c:if>
            </div>
            <!-- 分页 -->
            <div id="js-pagination" class="notice-pagination-box clearfix paging">
                <div id="pagination" class="pagination"><span class="current prev"><i
                        class="icon-triangle-left"></i>上一页</span><span class="current">1</span><span
                        class="current next">下一页<i class="icon-triangle-right"></i></span></div>
            </div>
        </div>
    </div>
    <jsp:include page="../../client/common/footer.jsp"/>
</body>
<script id="listTemplate" type="text/x-handlebars-template">
    {{#ifCond hitsList 'length' 0}}
    <div class="info-tip no-data-tip min-h500 pdt120">
        <%--<img src="<c:url value='/img/client/no-data-tip.png'/> " alt="">--%>
        <p>很抱歉，没有找到相关结果</p>
    </div>
    {{else}}
    <ul class="notebook-list">
        {{#each hitsList}}
        <li>
            <h3>
                <a href="<c:url value='toedit/{{_id}}'/>"><i class="edit"></i></a>
                <a class="js-delete cursor" js-id="{{_id}}"><i class="del"></i></a>
                <a href="<c:url value='/client/note/tocolumnnotelist/{{_id}}'/>" target="_blank">
                    {{{_source.name}}}
                    {{#ifCond _source.visibleStatus '==' 1 }}
                    <img src="<c:url value='/img/client/lock.png' /> "/>
                    {{/ifCond}}
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
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/person/note/controller/columnListController.js'/>"></script>
</html>
