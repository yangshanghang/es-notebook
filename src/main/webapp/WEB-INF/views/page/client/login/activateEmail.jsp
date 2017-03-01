<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>激活邮箱</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body class="sass-log bgcolorwhite layer-sass">
    <jsp:include page="../common/header.jsp"/>
    <div class="mod findpwd-mod">
            <form class="form-box js-pass-form" id="js-form">
                    <h1 class="login-title">激活邮箱</h1>
                <ul>
                    <li>
                        <input type="text" class="ipt" placeholder="请输入激活邮箱" id="js-email" name="email" maxlength="30">
                    </li>
                    <span class="error-place"></span>
                    <li>
                        <input type="button" class="btn js-user-pass" value="激活邮箱"/>
                    </li>
                </ul>
            </form>
    </div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/activeEmailControlelr.js'/>"></script>
</body>
</html>
