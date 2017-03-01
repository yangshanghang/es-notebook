<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${qualityNoteList.total > 0}">
    <h2>精品笔记</h2>
</c:if>
<ul class="jingpin-list sidebar-bg">
    <c:forEach items="${qualityNoteList.hits}" var="data">
        <li>
            <a href="<c:url value='/client/note/detail/${data._source.id}'/>" <c:if test="${param.cur != 'detail'}">  target="_blank" </c:if> >
                <div class="pic">
                    <img class="img-thumbnail"
                         src="${fn:substring(data._source.scaleImgUrl,0,fn:indexOf(data._source.scaleImgUrl,'&'))}">
                </div>
                <h3 class="ellips" title="${data._source.title}">${data._source.title}</h3>
                <p class="item-summary ellips_line3" title="${data._source.summary}">
                    <c:if test="${fn:length(data._source.summary) > 25}">
                        ${fn:substring(data._source.summary,0 ,25 )}...
                    </c:if>
                    <c:if test="${fn:length(data._source.summary) <= 25}">
                        ${data._source.summary}
                    </c:if>
                </p>
            </a>
        </li>
    </c:forEach>
</ul>

