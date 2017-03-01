<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
    <a class="logo" href="<c:url value='/'/>"></a>
    <ul class="menu-list">
        <li class="addnote-item">
            <a href="<c:url value='/person/note/add'/>" <c:if test="${param.cur == 'addNote'}"> class="cur"</c:if>>
                <i class="addnote-icon"></i>
                记录笔记
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/note/list'/>" <c:if test="${param.cur == 'myNote'}"> class="cur"</c:if>>
                <span><i class="notebook-icon"></i></span>
                我的笔记
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/myfavorite/list'/>" <c:if test="${param.cur == 'myFavorite'}"> class="cur"</c:if>>
                <span><i class="favorite-icon"></i></span>
                我的收藏
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/columns/list'/>" <c:if test="${param.cur == 'myColumns'}"> class="cur"</c:if>>
                <span><i class="columns-icon"></i></span>
                我的专栏
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/myfocus/list'/>" <c:if test="${param.cur == 'myFocus'}"> class="cur"</c:if>>
                <span><i class="focus-icon"></i></span>
                分类关注
            </a>
        </li>
    </ul>
</div>