<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>修改筑巢笔记密码</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body class="bgcolorwhite">
<jsp:include page="../common/header.jsp"/>
<div class="mod findpwd-mod">
    <c:if test="${!overtime}">
        <form class="form-box"id="js-form">
            <h1 class="login-title">修改密码</h1>
            <input type="hidden" name="id" value="${id}">
            <ul>
                <li>
                    <input type="password" class="ipt" placeholder="请输入新密码" id="password" name="password" maxlength="20">
                </li>
                <span class="error-place"></span>
                <li>
                    <input type="button" id="submit" class="btn" value="修改密码"/>
                </li>
            </ul>
        </form>
    </c:if>
    <c:if test="${overtime}">
        <div class="form-box js-pass-form" >
        <h1>密码修改已超时，请重新<a href="<c:url value='/client/toretrievepass'/>">找回密码！</a></h1>
        </div>
    </c:if>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/changePasswordController.js'/>"></script>
</body>
</html>
