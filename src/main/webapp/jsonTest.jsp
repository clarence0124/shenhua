<%--
  Created by IntelliJ IDEA.
  com.test.User: Administrator
  Date: 2016/12/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/project.tld" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <base href="${contextPath}" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/color.css">

    <script type="text/javascript" src="${contextPath}jquery/jquery.min.js"></script>

    <script type="text/javascript" src="${contextPath}jquery/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${contextPath}jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${contextPath}jquery/utils.js"></script>
</head>
<body class="easyui-layout" fit="true">
    <script>
        $.getJSON('/test/test2', function(r) {
            console.log(r)
        })
    </script>
</body>
</html>
