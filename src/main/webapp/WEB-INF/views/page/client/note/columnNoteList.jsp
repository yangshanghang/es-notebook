<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
<head>
    <title>专栏笔记</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body>
<div class="main ml0">
    <jsp:include page="../common/header.jsp?cur=columns"/>
    <div class="mod-1200 contain clearfix mt103">
        <div class="list-con fl mod-930 mr20">
            <div class="modify-entrance">
                <input type="hidden" id="js-columnId" value="${columns.id}">
                <c:if test="${!empty SESSION_USER_INFO && SESSION_USER_INFO.id == columns.userId}">
                    <a href="<c:url value='/person/columns/toedit/${columns.id}'/>">修改专栏</a>
                    <a href="javascript:void(0)" id="delete-column">删除专栏</a>
                </c:if>
            </div>
            <div class="crumb">
                <a href="/client/columns/list" class="">专栏中心 &nbsp;</a><em>&gt; &nbsp;</em>
                <a href="/client/columns/list/${category.id}">${category.name}</a><em>&nbsp; &gt; &nbsp;</em>
                <span>${columns.name}</span>
            </div>
            <div class="tips">
                我们共为您找到<em id="itemCount">${noteList.total}</em>篇笔记，耗时<em id="took">${noteList.took}</em>毫秒
                <script>
                    window._bd_share_config = {
                        "common": {
                            "bdSnsKey": {},
                            "bdComment": "${columns.introduce}",
                            "bdText": "${columns.name}",
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
                <div id="share" class="fr">
                    <div class="fl bdsharebuttonbox">
                        <a href="javascript:void(0)" class="bds_more color-gray" data-cmd="more">分享</a>
                    </div>
                </div>
            </div>
            <div class="notebook-box min-h500">
                <div class="js-template">
                    <ul class="notebook-list">
                        <c:forEach items="${noteList.hits}" var="data">
                            <li>
                                <h3>
                                    <a href="<c:url value='/client/note/detail/${data._id}'/>" target="_blank">
                                        ${data._source.title}
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
                </div>
            </div>
        </div>
        <div class="fl mod-240 sidebar-min-h related-articles">
            <jsp:include page="../common/right.jsp"/>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</div>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/note/controller/columnNoteListController.js'/>"></script>
</body>
</html>
