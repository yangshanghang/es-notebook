<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar <c:if test="${flex == true}">sidebar-flexed</c:if> ">
    <a class="logo" href="<c:url value='/'/>"></a>
    <ul class="menu-list">
        <li class="addnote-item ">
            <a href="<c:url value='/person/note/add'/>" <c:if test="${param.cur == 'addNote'}"> class="cur"</c:if> title="记录笔记">
                <i class="addnote-icon"></i>
                <strong>记录笔记</strong>
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/note/list'/>" <c:if test="${param.cur == 'myNote'}"> class="cur"</c:if> title="我的笔记">
                <span><i class="notebook-icon"></i></span>
                <strong>我的笔记</strong>
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/myfavorite/list'/>" <c:if test="${param.cur == 'myFavorite'}"> class="cur"</c:if> title="我的收藏">
                <span><i class="favorite-icon"></i></span>
                <strong>我的收藏</strong>
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/columns/list'/>" <c:if test="${param.cur == 'myColumns'}"> class="cur"</c:if> title="我的专栏">
                <span><i class="columns-icon"></i></span>
                <strong>我的专栏</strong>
            </a>
        </li>
        <li>
            <a href="<c:url value='/person/myfocus/list'/>" <c:if test="${param.cur == 'myFocus'}"> class="cur"</c:if> title="分类关注">
                <span><i class="focus-icon"></i></span>
                <strong>分类关注</strong>
            </a>
        </li>
        <li class="flex-trigger ${flex == true?"js-side":"js-side-flex"}" >
            <p>
                <em><b>${flex == true?"&raquo":"&laquo"}</b></em>
                <strong>收缩侧边栏</strong>
            </p>
        </li>
    </ul>
</div>