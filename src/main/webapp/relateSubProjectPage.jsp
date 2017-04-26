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
    <title>关联集团三算管理系统子项目</title>
    <base href="${contextPath}" />
    <link rel="stylesheet" type="text/css" href="jquery/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery/easyui/themes/color.css">

    <script type="text/javascript" src="jquery/jquery.min.js"></script>

    <script type="text/javascript" src="jquery/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery/easyui/plugins/jquery.treegrid.js"></script>
    <script type="text/javascript" src="jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jquery/utils.js"></script>
</head>
<body class="easyui-layout" fit="true">

    <div id="queryBar">
        项目名称：<input type="text" name="projName" id="projName" />
    </div>

    <div data-options="region:'center'" title="集团三算管理系统的子项目列表">
        <table id="datagrid" class="easyui-datagrid"  fit="true"
               data-options="method: 'get',
						nowrap:true,
						fitColumns:false,
						singleSelect: 'true',
						idField:'id',
						rownumbers: true,
						toolbar: '#queryBar',
						url: '${contextPath}wsc/getSubProjectList'">
            <thead>
            <tr >
                <th data-options="field:'id',checkbox:true" width="30"></th>
                <th data-options="field:'subProjName', halign:'center', align:'left'" width="350">子项目名称</th>
                <th data-options="field:'projectName', halign:'center', align:'left'" width="350">主项目名称</th>
                <th data-options="field:'industryTypeName', halign:'center', align:'left'" width="200">板块大类</th>
            </tr>
            </thead>
        </table>
    </div>

    <script>
        $('#projName').on('keypress', function(ev) {
            if (ev.keyCode == 13) {
                $('#datagrid').datagrid({
                    queryParams: {
                        projName: $.trim($(this).val())
                    }
                }).datagrid('clearSelections');
                return false;
            }
            ev.stopPropagation()
        })
    </script>
</body>
</html>
