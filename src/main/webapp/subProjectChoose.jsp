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
    <base prefix="${context}" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}jquery/easyui/themes/color.css">

    <script type="text/javascript" src="${contextPath}jquery/jquery.min.js"></script>

    <script type="text/javascript" src="${contextPath}jquery/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${contextPath}jquery/easyui/plugins/jquery.treegrid.js"></script>
    <script type="text/javascript" src="${contextPath}jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${contextPath}jquery/utils.js"></script>
</head>
<body class="easyui-layout" fit="true">

    <div data-options="region:'center'" title="WBS概算模板明细">
        <table id="treegrid" class="easyui-treegrid"  fit="true"
               data-options="method: 'get',
						nowrap:true,
						fitColumns:false,
						singleSelect: 'true',
						idField:'id',
                        treeField:'name',
						url: '${contextPath}project/wbsTemplateDetail', loadFilter: treegridLoadFilter ">
            <thead>
            <tr >
                <th data-options="field:'id',checkbox:true" width="30"></th>
                <th data-options="field:'name', halign:'center', align:'left'" width="300">名称</th>
                <th data-options="field:'code', halign:'center', align:'left'" width="120">编码</th>
            </tr>
            </thead>
        </table>

    </div>

    <div data-options="region:'west'" style="width:400px" title="WBS概算模板列表">
        <table id="datagrid" class="easyui-datagrid"  fit="true"
               data-options="method: 'get',
						nowrap:true,
						fitColumns:false,
						singleSelect: 'true',
						url: '${contextPath}project/wbsTemplate', onSelect: datagridOnSelect ">
            <thead>
            <tr >
                <th data-options="field:'disciplineTypeName', halign:'center', align:'left'" width="200">板块小类</th>
                <th data-options="field:'industryTypeName', halign:'center', align:'left'" width="200">板块大类</th>
            </tr>
            </thead>
        </table>
    </div>

    <script>

        function datagridOnSelect(index, row) {
            $('#treegrid').treegrid('options').queryParams = {
                industryType: row.industryType
                , disciplineType: row.disciplineType
            }
            $('#treegrid').treegrid('reload')
        }

        function treegridLoadFilter(data) {
            if (data && data.rows) {
                for (var i = 0; i < data.rows.length; i++) {
                    data.rows[i]._parentId = data.rows[i].pid
                }
                return data;
            }
            return {rows: []};
        }
    </script>
</body>
</html>
