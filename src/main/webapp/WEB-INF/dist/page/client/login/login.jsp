<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>登录筑巢笔记</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="mod login-mod">
    <form class="form-box" id="js-form">
        <h1 class="login-title"><i class="icons mr10"></i>用户登录</h1>
        <input type="hidden" id="ref" value="${ref}"/>
        <ul>
            <li>
                <input type="text" name="email" maxlength="30" class="ipt" placeholder="请输入注册邮箱">
            </li>
            <li>
                <input type="password" name="password" maxlength="20" class="ipt" placeholder="请输入密码">
            </li>
            <span class="error-place"></span>
            <li>
                <input type="button" class="btn js-user-log" value="登 录"/>
            </li>
            <li class="forget-pw">
                <a href="<c:url value='/client/toretrievepass'/>">忘记密码</a>
            </li>
        </ul>
        <div class="third-login">
            <h5>第三方登录</h5>
            <p class="icons"><a href="javascript:void(0);"></a><a href="javascript:void(0);"></a><a href="javascript:void(0);"></a></p>
        </div>
    </form>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/loginController.js?v=efdc148e96'/>"></script>
</body>
</html>
