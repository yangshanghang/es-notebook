<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>错误</title>
    <jsp:include page="client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body>
    <jsp:include page="client/common/header.jsp"/>
    <div class="contain ">
        <div class="info-tip error-tip min-h500">
            <img src="<c:url value= '/img/client/error-tip.png'/> " alt="">
            <p>唔，出错啦！请稍后再试</p>
        </div>
    </div>
    <jsp:include page="client/common/footer.jsp"/>
</body>
</html>
