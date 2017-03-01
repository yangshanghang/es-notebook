<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--路径配置--%>
<script>
    window.__NOTEBOOK__ = {
        // 根路径
        root_url : "<c:url value='/' />".split(';')[0]
    };
</script>
<script src="<c:url value='/js/lib/require.js'/>"></script>
<script src="<c:url value='/js/config.js?v=676c09c93d'/>"></script>
<script src="<c:url value='/js/client/common/controller/baseController.js'/>"></script>