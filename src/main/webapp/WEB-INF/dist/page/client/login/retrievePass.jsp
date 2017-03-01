<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>找回筑巢笔记密码</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="mod findpwd-mod">
    <form class="form-box js-pass-form" id="js-form">
        <h1 class="login-title"><i class="icons mr10"></i>找回密码</h1>
        <ul>
            <li>
                <input type="text" class="ipt" placeholder="请输入注册邮箱" id="js-email" name="email" maxlength="30">
            </li>
            <span class="error-place"></span>
            <li>
                <input type="button" class="btn js-user-pass" value="找回密码"/>
            </li>
        </ul>
    </form>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/retrievePassController.js?v=a538565c0f'/>"></script>
</body>
</html>
