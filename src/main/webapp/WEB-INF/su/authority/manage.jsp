<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${contextPath}" />
    <title>系统功能管理主页面</title>
    <meta charset="utf-8" />

    <c:set var="static" value="${contextPath}/static" />
    <jsp:include page="/inc.jsp"></jsp:include>
    <link type="text/css" href="js/jquery/zTree3.5.14/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>

    <script type="text/javascript" src="${static}/js/util.js"></script>
    <script type="text/javascript" src="${static}/core/fun/main.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">

    <div data-options="region:'west'" title="系统功能" style="padding:5px; width:400px;">
        <div class="easyui-layout" fit="true">
            <div data-options="region:'north',border:false"  style="height:44px;overflow:hidden"   >
                <div class="formSep btn-group" style="width:95%">
                    <button id="addFunctionBtn" class="btn btn-default btn-sm" style="font-size:12px">添加</button>
                    <button id="delFunctionBtn" class="btn btn-default btn-sm" style="font-size:12px">删除</button>
                    <button id="expandFunctionBtn" class="btn btn-default btn-sm" style="font-size:12px">展开</button>
                    <button id="collapseFunctionBtn" class="btn btn-default btn-sm" style="font-size:12px">收起</button>
                </div>
            </div>
            <div data-options="region:'center',border:false,fit:true"  >
                <ul id="funTree" class="ztree"></ul>
            </div>
        </div>
    </div>

    <div data-options="region:'center'" title="明细" style="padding:5px;" >
        <div id="functionInfo"></div>
    </div>
</div>

</body>
</html>