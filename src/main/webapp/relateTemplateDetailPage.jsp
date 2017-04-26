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
    <script type="text/javascript" src="jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jquery/utils.js"></script>
</head>
<body class="easyui-layout" fit="true">

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="关联" id="relateBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
        <input type="button" value="导出" id="exportBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'center'">
        <div class="easyui-layout" fit="true">

            <div data-options="region:'center'" title="三算管理系统项目结构">
                <table id="templateDetails" class="easyui-treegrid"  fit="true"
                       data-options="method: 'get', nowrap:true, fitColumns:false, singleSelect: 'false', idField:'id', treeField:'name', rownumbers: false,
						url: '${contextPath}project/${projectId}/wbsTemplateDetail', loadFilter: treegridLoadFilter, onBeforeSelect: singleCheckOnBeforeSelect">
                    <thead>
                    <tr >
                        <%--<th data-options="field:'id',checkbox:true" width="30"></th>--%>
                        <th data-options="field:'name', halign:'center', align:'left'" width="300">名称</th>
                       <%--     <th data-options="field:'nodeDepth', halign:'center', align:'left'" width="120">深度</th>
                        <th data-options="field:'brotherOrderNo', halign:'center', align:'left'" width="120">排序</th>--%>
                    </tr>
                    </thead>
                </table>

            </div>

            <div data-options="region:'west'" style="width:500px" title="全过程管理系统项目结构">
                <table id="projectStructures" class="easyui-treegrid"  fit="true"
                       data-options="method: 'get', nowrap:true, fitColumns:true, singleSelect: false, idField:'id', treeField:'nodeName', rownumbers: false,
						url: '${contextPath}project/${projectId}/projectStructure', loadFilter: treegridLoadFilter2, onBeforeSelect: singleCheckOnBeforeSelect, rowStyler: projectStructuresRowStyler">
                    <thead>
                    <tr >
                        <%--<th data-options="field:'id', checkbox: true"></th>--%>
                        <th data-options="field:'nodeName', halign:'center', align:'left'" width="300">名称</th>
                        <%--<th data-options="field:'nodeCode', halign:'center', align:'left'" width="200">编码</th>--%>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    <script>

        function projectStructuresRowStyler(r) {
            if (r && r.estimateTemplateId) {
                return 'color:blue';
            }
            return '';
        }

        function leafCheckOnlyFormatter(v, r) {
            if (r.children && 0 < r.children.length) return '';
            return '<input type="checkbox" name="projectStructureId" value="' + v + '" />';
        }

        function singleCheckOnBeforeSelect(r) {
            return !r.children || 0 == r.children.length;
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

            var ps = $('#projectStructures').treegrid('getSelections');
            if (0 == ps.length) {
                return top.showAlert("请先在全过程管理系统项目结构选择至少一个叶子节点再进行操作。");
            }

            var td = $('#templateDetails').treegrid('getSelected');
            if (!td) {
                return top.showAlert('请先在三算管理系统项目结构选择一个叶子节点再进行操作。');
            }

            var psIds = [];
            $.each(ps, function() {
                psIds.push(this.id)
            });

            $.post('${contextPath}project/wbsTemplateDetailRelate', {'_method': 'PUT', 'projectStructureIds': psIds, 'estimateTemplateId': td.id}, function (r) {

                top.showAlert(r.msg, function() {
                    if (r.success) {
                        $('#projectStructures').treegrid('reload');
                        $('#templateDetails').treegrid('reload');
                    }
                })
            }, 'json');

            return false;
        });


        $('#exportBtn').on('click', function() {
            var win = openIframeWindow({
                'title': '导出预览',
                'width': $(window).width() * 0.8,
                'height': $(window).height() * 0.8,
                'href': '${contextPath}project/${projectId}/exportPage',
                'buttons': [{text: '确认并导出', handler: function() {
                    window.location.href = '${contextPath}project/${projectId}/exportResult';
                }}, {text: '取消', handler: function() {
                    win.dialog('close')
                }}]
            });
            return false;
        })
    </script>
</body>
</html>
