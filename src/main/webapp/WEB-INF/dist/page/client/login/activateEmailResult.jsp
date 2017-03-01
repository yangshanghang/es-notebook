<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>激活邮箱结果</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body class="sass-log">
<jsp:include page="../common/header.jsp"/>
<div class="mod findpwd-mod">
    <div class="form-box js-pass-form">
        <h1 class="login-title">
            <c:if test="${!overtime}">
                ${resultInfo} <a href="<c:url value='/client/tologin?ref=index'/> " class="link-blue">立即登录</a>
            </c:if>
            <c:if test="${overtime}">
                ${resultInfo}<a href="<c:url value="/client/toactivateemail"/>">请重新激活</a>
            </c:if>
        </h1>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
</body>
</html>
