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

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="关联" id="relateBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'center'">
        <div class="easyui-layout" fit="true">

            <div data-options="region:'center'" title="WBS概算模板明细">
                <table id="templateDetails" class="easyui-treegrid"  fit="true"
                       data-options="method: 'get', nowrap:true, fitColumns:false, singleSelect: 'true', idField:'id', treeField:'name',
						url: '${contextPath}project/${projectId}/wbsTemplateDetail', loadFilter: treegridLoadFilter ">
                    <thead>
                    <tr >
                        <th data-options="field:'id',checkbox:true" width="30"></th>
                        <th data-options="field:'name', halign:'center', align:'left'" width="300">名称</th>
                        <th data-options="field:'code', halign:'center', align:'left'" width="120">编码</th>
                    </tr>
                    </thead>
                </table>

            </div>

            <div data-options="region:'west'" style="width:500px" title="工程结构">
                <table id="projectStructures" class="easyui-treegrid"  fit="true"
                       data-options="method: 'get', nowrap:true, fitColumns:false, singleSelect: 'true', idField:'id', treeField:'nodeName', rownumbers:'true',
						url: '${contextPath}project/${projectId}/projectStructure', loadFilter: treegridLoadFilter2">
                    <thead>
                    <tr >
                        <th data-options="field:'tempId', checkbox:'true'">板块小类</th>
                        <th data-options="field:'nodeName', halign:'center', align:'left'" width="200">名称</th>
                        <th data-options="field:'nodeCode', halign:'center', align:'left'" width="200">编码</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    <script>

        function treegridLoadFilter(data) {
            if (data && data.rows) {
                for (var i = 0; i < data.rows.length; i++) {
                    data.rows[i]._parentId = data.rows[i].pid
                }
                return data;
            }
            return {rows: []};
        }

        function treegridLoadFilter2(data) {
            if (data && data.rows) {
                for (var i = 0; i < data.rows.length; i++) {
                    data.rows[i]._parentId = data.rows[i].parentId
                }
                return data;
            }
            return {rows: []};
        }

        $('#relateBtn').on('click', function() {
            var td = $('#templateDetails').treegrid('getSelected');
            var ps = $('#projectStructures').treegrid('getSelected');
            if (!ps || !td) {
                return top.showAlert('请选择一个项目结节和一个模板明细进行关联操作');
            }

            $.post('${contextPath}project/' + ps.id + '/wbsTemplateDetailRelate', {'_method': 'PUT', 'projectStructureId': ps.id, 'wbsTemplateDetailId': td.id}, function (r) {

                top.showAlert(r.msg, function() {
                    if (r.success) {
                        win.dialog('close');
                        $('#datagrid').datagrid('reload')
                    }
                })
            }, 'json');
        });

    </script>
</body>
</html>
