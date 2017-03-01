<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>注册筑巢笔记</title>
    <jsp:include page="../common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css?v=b0f9c61b91'/>">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="mod register-mod">
    <form action="" id="js-form" class="form-box" style="display: block">
        <h1 class="login-title"><i class="icons mr10"></i>注册账号</h1>
        <ul>
            <li>
                <input type="text" class="ipt" name="email" maxlength="30" placeholder="请输入邮箱作为登录账号">
            </li>
            <li>
                <input type="password" class="ipt" name="password" maxlength="20" placeholder="请输入密码">
            </li>
            <li>
                <input type="text" class="ipt" name="realName" maxlength="20" placeholder="请输入真实姓名">
            </li>
            <li>
                <input type="text" class="ipt" name="qq" maxlength="10" placeholder="请输入qq号">
            </li>
            <li>
                <span class="js-error"></span>
            </li>
            <li>
                <input class="btn js-user-reg" type="button" value="立即注册">
            </li>
        </ul>
    </form>

    <!-- 注册成功 -->
    <div class="form-box js-register-success pdl80 pos_rel w-700 none">
        <div class="mail-icon"></div>
        <p class="success-txt">
        感谢注册，确认邮件已发送至你的注册邮箱 : <span class="js-email"></span> <br/>
        <span class="gray">请进入邮箱查看邮件，并激活筑巢笔记帐号。</span>
        </p>
        <a target="_blank" id="js-address" class="mail-btn">登录邮箱</a><br>
        <span>
            <span class="gray">没有收到邮件？</span><br/>
            1. 请检查邮箱地址是否正确，你可以返回&nbsp;<a class="link-blue js-again-write" href="javascript:void(0)">重新填写</a><br>
            2. 检查你的邮件垃圾箱<br>
            3. 若仍未收到确认，请尝试&nbsp;<a href="<c:url value='/client/toactivateemail'/> " class="link-blue">重新发送</a>
        </span>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/login/controller/registerController.js?v=54440d51b7'/>"></script>
</body>
</html>
