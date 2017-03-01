<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top-menu bg-blue">
    <input type="hidden" id="js-user" value="${SESSION_USER_INFO.id}"/>
    <div class="mod">
        <ul class="menu-list">
            <li class="note-item">
                <a href="<c:url value='/'/>"><i class="notebook-icon"></i></a>
            </li>
            <li class="line-item"></li>
            <li><a href="<c:url value='/client/note/list'/>" <c:if test="${param.cur == 'index'}"> class="cur"</c:if>>检索中心</a>
            </li>
            <li><a href="<c:url value='/client/columns/list'/>" <c:if
                    test="${param.cur == 'columns'}"> class="cur"</c:if>>专栏中心</a></li>
            <li>
                <a href="<c:if test="${!empty SESSION_USER_INFO.id}"> <c:url value='/person/note/list'/></c:if>
                <c:if test="${empty SESSION_USER_INFO.id}"> <c:url value='/client/tologin?ref=person'/></c:if>"
                        <c:if test="${param.cur == 'login'}"> class="cur"</c:if> id="js-my-Note">我的笔记</a>
            </li>
        </ul>
        <div class="login-list">
            <c:if test="${empty SESSION_USER_INFO}">
                <a href="<c:url value='/client/tologin?ref=index'/>" class="login-btn"> 登录</a>
                <a href="<c:url value='/client/toregister'/>" class="login-btn"> 注册</a>
            </c:if>
            <c:if test="${!empty SESSION_USER_INFO}">
                <div class="login-after login-info">
                <span class="user-name">
                    <img src="<c:url value='/img/client/avatar.png'/> " class="" alt="">
                    <span class="mr5">${SESSION_USER_INFO.realName}</span>
                </span>
                    <a href="javascript:void(0);" class="log-out mr5">退出 <i class="glyphicon glyphicon-log-out"></i></a>
                </div>
            </c:if>
        </div>
    </div>
</div>
