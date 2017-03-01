<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <title>我的笔记</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css?v=93fdaee35d'/>">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=myNote"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="search-box">
        <input type="text" class="ipt-txt" placeholder="请输入搜索的关键字" id="queryParam" value="${queryParam}" maxlength="100">
        <input type="button" class="ipt-btn" id="query" value="立即搜索">
    </div>
    <div class="contain mt20r20m0l20 bgcolorwhite">
        <div class="type js-allType">
            <li><a type="button" class="js-type cur" js-type="1">全部</a></li>
            <c:forEach items="${noteTypes}" begin="0" end="7" var="noteType">
                <li><a js-category-id="${noteType.id}" class="js-type ellips" title="${noteType.name}">${noteType.name}</a></li>
            </c:forEach>
            <c:if test="${noteTypes.size() > 8}">
                <li>
                    <a class="more">+更多</a>
                    <!--更多类目-->
                    <div id="js-addBox" class="more-type">
                        <c:forEach items="${noteTypes}" begin="8" var="noteType">
                            <a js-category-id="${noteType.id}" class="js-hiddenType ellips" title="${noteType.name}">${noteType.name}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:if>
        </div>

        <div class="tips">我们共为您找到<em id="itemCount">${dataDto.total}</em>篇笔记，耗时<em id="took">${dataDto.took}</em>毫秒</div>
        <div class="notebook-box">
            <div class="js-template">
                <c:if test="${dataDto.total > 0}">
                    <ul class="notebook-list">
                        <c:forEach items="${dataDto.hits}" var="data">
                            <li>
                                <h3>
                                    <a href="<c:url value='/person/note/edit/${data._id}'/>"><i class="edit"></i></a>
                                    <a class="delete cursor" js-id="${data._id}"><i class="del"></i></a>
                                    <a href="<c:url value='/client/note/detail/${data._id}'/>" target="_blank">
                                            ${data._source.title}
                                        <c:if test="${data._source.attachmentFlag == '1'}">
                                            <span class="favorite-icon"></span>
                                        </c:if>
                                        <c:if test="${data._source.visibleStatus == '1'}">
                                            <img src="<c:url value='/img/client/lock.png' /> "/>
                                        </c:if>
                                    </a>
                                </h3>
                                <c:if test="${!empty data._source.scaleImgUrl && data._source.scaleImgUrl != '-1'}">
                                    <div class="thumbnail">
                                        <img class="img-thumbnail"
                                             src="${fn:substring(data._source.scaleImgUrl,0,fn:indexOf(data._source.scaleImgUrl,'&'))}">
                                    </div>
                                </c:if>
                                <p class="item-summary ellips_line3">${data._source.summary}</p>
                                <div class="item-foot">
                                    <span>分类：${data._source.category.name}</span>
                                    <span>创建者：${data._source.author}</span>
                                    <span>创建时间：<fmt:formatDate value="${data._source.createTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>最新编辑时间：<fmt:formatDate value="${data._source.operateTime}"
                                                                 pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span>赞：${data._source.praiseCount}</span>
                                    <span>评论：${data._source.commentCount}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${dataDto.total == 0}">
                    <div class="info-tip no-data-tip min-h500 pdt120 center">
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
                <a href="<c:url value='/person/note/edit/{{_id}}'/>"><i class="edit"></i></a>
                <a class="delete cursor" js-id="{{_id}}"><i class="del"></i></a>
                <a href="<c:url value='/client/note/detail/{{_id}}'/>" target="_blank">
                    {{{_source.title}}}
                    {{#ifCond _source.attachmentFlag '==' 1 }}
                        <span class="favorite-icon"></span>
                    {{/ifCond}}

                    {{#ifCond _source.visibleStatus '==' 1 }}
                        <img src="<c:url value='/img/client/lock.png' /> "/>
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
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/person/note/controller/listController.js?v=f1408eb5fc'/>"></script>
</html>
