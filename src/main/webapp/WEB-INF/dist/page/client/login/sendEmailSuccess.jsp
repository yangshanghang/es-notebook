<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>发送邮件成功页</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body class="sass-log">
<jsp:include page="../common/header.jsp"/>
<div class="mod findpwd-mod">
    <div class="form-box js-pass-form mt210">
        <div class="pass-icon"></div>
        <p>请访问邮件中给出的网页链接地址，<br>根据页面提示完成${emailInfo == 'activateemail'?'账户激活':'密码重设'}。</p>
        <a href="<c:url value='/client/tologin?ref=index'/> " id="js-confirm" class="btn btn2">确定</a>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/activeEmailControlelr.js?v=e6cbf1c69b'/>"></script>
</body>
</html>

