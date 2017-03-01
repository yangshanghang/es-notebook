<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>专栏新增修改</title>
    <jsp:include page="../../client/common/meta.jsp"/>
    <link rel="stylesheet" href="<c:url value='/css/person.css'/>">
</head>
<body class="mynote-scss">
<jsp:include page="../common/left.jsp?cur=myColumns"/>
<div class="main main-bg">
    <jsp:include page="../common/header.jsp?cur=myNote"/>
    <div class="contain mt20r20m0l20 bgcolorwhite mt103">
        <input type="hidden" id="typeId" value="${columns.categoryId}"/>
        <form id="js-form">
            <!-- 表单隐藏域 -->
            <input type="hidden" name="id" id="js-columnId" value="${columns.id}"/>
            <input type="hidden" name="author" value="${SESSION_USER_INFO.realName}">
            <input type="hidden" name="userId" value="${SESSION_USER_INFO.id}">

            <div class="notebook-box edit-notebook">
                <h1 class="h1-title center"><i
                        class=${columns == null?"icon-add":"icon-edit2"}></i>${columns == null?'专栏新增':'专栏修改'}</h1>
                <ul class="form-edit">
                    <li><input type="text" name="name" id="1" class="ipt-txt ipt-input" maxlength="40"
                               value="${columns.name}" placeholder="请输入专栏名称"/></li>
                    <li><textarea name="introduce" id="2" class="ipt-txt ipt-textarea" maxlength="180"
                                  placeholder="请输入专栏介绍">${columns.introduce}</textarea></li>
                </ul>
                <div class="type js-allType">
                    <c:forEach items="${categoryList}" begin="0" end="7" varStatus="status" var="noteCategory">
                        <li>
                            <a js-category-id="${noteCategory.id}" href="javascript:void(0)" class="js-type ellips"
                               title="${noteCategory.name}">
                                    ${noteCategory.name}
                            </a>
                        </li>
                    </c:forEach>
                    <c:if test="${categoryList.size() > 8}">
                        <li>
                            <a href="javascript:void(0)" class="more" onclick="return false;">+更多</a>

                            <div class="more-type" id="js-addType">
                                <c:forEach items="${categoryList}" begin="8" var="noteCategory">
                                    <a js-category-id="${noteCategory.id}" href="javascript:void(0)"
                                       class="js-hiddenType ellips js-type"
                                       title="${noteCategory.name}">${noteCategory.name}</a>
                                </c:forEach>
                            </div>
                        </li>
                    </c:if>
                </div>

                <p class="mt10 authority-set">
                    <input id="label-visibleStatus0" type="radio" name="visibleStatus" value="0" <c:if test="${columns.visibleStatus==0 || empty columns}">checked="checked"</c:if> >
                    <label for="label-visibleStatus0"> 公开 </label>
                    <input id="label-visibleStatus1" type="radio" name="visibleStatus" value="1" <c:if test="${columns.visibleStatus==1}">checked="checked"</c:if> >
                    <label for="label-visibleStatus1"> 仅自己可见 </label>
                </p>
                <div class="column-note-box">
                    <table cellpadding="0" cellspacing="0" width="100%" class="column-edit-tb mt10">
                        <tr>
                            <th width="50%" colspan="2">
                                <div>已选笔记 <span class="tiny-tip">点击拖动即可进行笔记排序</span></div>
                                <div class="search-btn">
                                    <input type="text" class="js-look-text">
                                    <input type="button" value="查找" class="look-btn js-look-note">
                                </div>
                                <span class="center-line"></span>
                            </th>
                        </tr>
                        <tr>
                            <td colspan="2" class="clearfix">
                                <ul class="js-sort note-selected connectedSortable">
                                    <c:forEach items="${columnNoteList.hits}" var="columnNote">
                                        <li data-id="${columnNote._id}" title="${columnNote._source.title}"><i
                                                class="minus-icon js-note-minus">&minus;</i>${columnNote._source.title}
                                        </li>
                                    </c:forEach>
                                </ul>
                                <ul class="js-sort connectedSortable search-result">
                                    <c:forEach items="${notColumnNoteList.hits}" var="notColumnNote">
                                        <li data-id="${notColumnNote._id}" title="${notColumnNote._source.title}"><i
                                                class="add-icon js-note-add">+</i>${notColumnNote._source.title}
                                        </li>
                                    </c:forEach>
                                </ul>
                                <span class="center-line"></span>
                            </td>
                        </tr>
                    </table>
                </div>

                <p class="center mt20">
                    <input type="button" class="btn" id="js-submit" value='保存'/>
                    <input type="button" class="btn" id="cancel" value="取消"/>
                </p>
            </div>
        </form>
    </div>
    <jsp:include page="../../client/common/footer.jsp"/>
</div>
</div>
<jsp:include page="../../client/common/common.jsp"/>
<script id="listTemplate" type="text/x-handlebars-template">
    {{#each this}}

    <li data-id="{{_id}}" title="{{{_source.title}}}"><i class="add-icon js-note-add">+</i>{{{_source.title}}}</li>

    {{/each}}
</script>
<script src="<c:url value='/js/person/note/controller/columnAddAndEditController.js'/>"></script>
</body>
</html>