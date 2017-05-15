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

    <div data-options="region:'center'" title="">
        <table id="templateDetails" class="easyui-treegrid"  fit="true"
               data-options="method: 'get', nowrap:true, fitColumns:false, singleSelect: 'false', idField:'id', treeField:'name', rownumbers: false,
                            url: '${contextPath}project/${projectId}/wbsTemplateDetailWithSum', loadFilter: treegridLoadFilter, rowStyler: rowStyler1">
            <thead>
            <tr >
                <%--<th data-options="field:'id',checkbox:true" width="30"></th>--%>
                <th data-options="field:'name', halign:'left', align:'left'" width="300">名称</th>
                <th data-options="field:'spec', halign:'center', align:'center'" width="120">规格</th>
                <th data-options="field:'amount', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">工程量</th>
                <th data-options="field:'unit', halign:'center', align:'center'" width="120">单位</th>
                <th data-options="field:'civilEcost', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">土建概算</th>
                <th data-options="field:'equipmentEcost', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">设备概算</th>
                <th data-options="field:'installEcost', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">安装概算</th>
                <th data-options="field:'feeEcost', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">费用概算</th>
                <th data-options="field:'otherEcost', halign:'center', align:'right', formatter:easyuiMoneyFormatter" width="120">其他概算</th>
            </tr>
            </thead>
        </table>

    </div>

    <script>
        function rowStyler1(r) {
            if (r && r.id) {
                if (0 == r.id.indexOf('2_')) {
                    return 'color:blue';
                }
            }
            return '';
        }

        function treegridLoadFilter(data) {
            if (data && data.rows) {
                for (var i = 0; i < data.rows.length; i++) {
                    var cur = data.rows[i]
                    if (cur.pid) {
                        cur._parentId = cur.pid
                    }
                }
                return data;
            }
            return {rows: []};
        }
    </script>
</body>
</html>
