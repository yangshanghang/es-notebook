<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${note.title}</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body>
<jsp:include page="../common/header.jsp?cur=index"/>
<div class="mod-1200 clearfix">
    <div class="fl mod-930 jszx-detail mr20">
        <div class="hd">
            <h3>${note.title}</h3>
        <span class="clearfix">
            <em>分类：${note.category.name}</em><em>作者：${note.author}</em><em>创建时间：<fmt:formatDate
                value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></em><em>最新编辑时间：<fmt:formatDate
                value="${note.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
            <a href="javascript:void(0);" class="fr collect js-favorite"><b class="icons"></b><span><c:if
                    test="${isFavorite}">已收藏</c:if> <c:if test="${!isFavorite}">收藏</c:if></span></a>
        </span>
        </div>
        <input id="js-id" type="hidden" value="${indexId}">
        <input id="js-content" type="hidden" value="${note.content}">
        <div class="cont">
            <div class="summary">
                摘要：${note.summary}
            </div>
            <div id="content">
            </div>
            <div class="tool-box">
                <div class="fl ">
                    <a href="javascript:void(0);" class="fl collect js-favorite"><i class="icons icons1"></i><span><c:if
                            test="${isFavorite}">已收藏</c:if> <c:if test="${!isFavorite}">收藏</c:if></span></a>
                    <a href="javascript:void(0);" class="fl collect" id="js-pushPraise"><i
                            class="icons icons2"></i><span>点赞</span></a>
                    <script>
                        window._bd_share_config = {
                            "common": {
                                "bdSnsKey": {},
                                "bdComment": "${note.summary}",
                                "bdText": "${note.title}",
                                "bdMini": "2",
                                "bdMiniList": ["sqq", "youdao"],
                                "bdPic": "",
                                "bdStyle": "1",
                                "bdSize": "24"
                            }, "share": {
                                "bdSize": 16
                            }
                        };
                        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                    </script>
                </div>
                <c:if test="${note.visibleStatus == 0}">
                    <div id="share">
                        <div class="fl bdsharebuttonbox">
                            <a href="javascript:void(0)" class="bds_more color-gray" data-cmd="more">分享</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${note.visibleStatus == 1}">
                    <div class="fl collect">
                        <img src="<c:url value='/img/client/lock.png' /> "/>
                        <span>仅作者可见</span>
                    </div>
                </c:if>
                <c:if test="${!empty SESSION_USER_INFO && SESSION_USER_INFO.id==note.userId}">
                    <div class="fr mr-5">
                        <a href="<c:url value='/person/note/edit/${indexId}'/> " class="fl collect"><i
                                class="icons icons4"></i>修改</a>
                        <a href="javascript:void(0);" class="fl collect" id="delete" js-id="${indexId}"><i
                                class="icons icons5"></i>删除</a>
                    </div>
                </c:if>
            </div>
            <c:if test="${!empty prevNext && noteList.total > 1 && (columns.visibleStatus==0 || SESSION_USER_INFO.id==note.userId)}">
                <div class="previous-next mt10">
                    <c:if test="${!empty prevNext['prev']}">
                        <span class="pre">上一篇：</span><a
                            href="<c:url value='/client/note/detail/${prevNext["prev"].id}'/>">${prevNext["prev"].title}</a>
                    </c:if>
                    <c:if test="${!empty prevNext['prev'] and !empty prevNext['next']}">
                        |
                    </c:if>
                    <c:if test="${!empty prevNext['next']}">
                        <span class="next">下一篇：</span><a
                            href="<c:url value='/client/note/detail/${prevNext["next"].id}'/>">${prevNext["next"].title}</a>
                    </c:if>
                </div>
            </c:if>
            <!-- 评论内容 -->
            <!-- 是否有下一页标识 -->
            <input id="hasNextPage" type="hidden" value="${hasNextPage}"/>
            <div class="comment-box">
                <div class="js-comments">
                    <ul>
                        <c:forEach items="${comments}" var="comment">
                            <li class="js-commentDiv pos_rel">
                                <input type="hidden" js-commentId="${comment.id}" class="js-commentId">
                                <input type="hidden" js-commentUserId="${comment.userId}" class="js-commentUserId">
                                <h6>
                                    <span class="name">${comment.publisher}</span>
                                        <span><fmt:formatDate value="${comment.createTime}"
                                                              pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                </h6>
                                <a class="js-closeComment del" js-commentId="${comment.id}"> <img
                                        src="<c:url value='/img/client/close.png'/> " class="avatar" alt=""> </a>
                                <P>${comment.content}</P>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="load-more center">
                    <span id="js-more" <c:if test="${hasNextPage == false}">style="display:none"</c:if>>+加载更多</span>
                </div>
                <form id="form-comment">
                    <input id="publisher" name="publisher" type="hidden" js-userId="${SESSION_USER_INFO.id}"
                           value="${SESSION_USER_INFO.realName}">
                    <input name="noteId" type="hidden" value="${indexId}">
                    <div class="comment-cz">
                        <textarea name="content" id="comment" cols="30" rows="5" maxlength="500"
                                  <c:if test="${empty SESSION_USER_INFO}">disabled="disabled"</c:if>></textarea>
                        <c:if test="${empty SESSION_USER_INFO}">
                            <span>您还没有登录,请<a href="<c:url value='/client/tologin'/>">[登录]</a></span>
                        </c:if>
                        <p class="right">
                            <input type="button" class="js-comment" <c:if
                                    test="${empty SESSION_USER_INFO}"> disabled="disabled" </c:if> value="发表"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 侧边专栏笔记目录 -->
    <div class="fl mod-240 pt119 related-articles sidebar-min-h js-sidebar-outer">
        <c:if test="${!empty noteList && (columns.visibleStatus==0 || SESSION_USER_INFO.id==note.userId)}">
            <div class="js-sidebar sidebar-bg">
                <h2>本专栏</h2>
                <div class="similar-name ellips"><a href="<c:url value='/client/note/tocolumnnotelist/${columns.id}'/>"
                                                    target="_blank" title="${columns.name}">${columns.name}</a></div>
                <ul class="related-articles-list">
                    <c:forEach items="${noteList.hits}" var="data">
                        <li class="item ellips">
                            <a href="<c:url value='/client/note/detail/${data._source.id}'/>"
                               title="${data._source.title}">${data._source.title}</a>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${noteList.total > 10}">
                    <div class="more">
                        <a href="<c:url value='/client/note/tocolumnnotelist/${columns.id}'/>"
                           target="_blank">本专栏更多内容</a>
                    </div>
                </c:if>
            </div>
        </c:if>
        <jsp:include page="../common/right.jsp?cur=detail"/>
    </div>
</div>
<script id="listTemplate" type="text/x-handlebars-template">
    {{#each list}}
    <ul>
        <li class="js-commentDiv pos_rel">
            <input type="hidden" js-commentId="{{id}}" class="js-commentId">
            <input type="hidden" js-commentUserId="{{userId}}" class="js-commentUserId">
            <h6>
                <span class="name">{{publisher}}</span>
                <span>{{prettifyDate createTime}}</span>
            </h6>
            <a class="js-closeComment del" js-commentId="{{id}}"> <img src="<c:url value='/img/client/close.png'/> "
                                                                       class="avatar" alt=""> </a>
            <P>{{{content}}}</P>
        </li>
    </ul>
    {{/each}}
</script>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/note/controller/detailController.js'/>"></script>
</body>
</html>
