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
        <input type="button" value="关联模板" id="relateTemplateBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
        <input type="button" value="导入概算" id="importBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'center'">

        <table id="datagrid" class="easyui-treegrid"  fit="true"
               data-options="method: 'get',
						nowrap:true,
						fitColumns:false,
						pageList: [10,20,50,100],
						singleSelect: 'true',
						idField:'id',
                        treeField:'nodeName',
						url: '${contextPath}project/${projectId}/projectStructure' ">
            <thead>
            <tr >
                <th data-options="field:'id',checkbox:true" width="30"></th>
                <th data-options="field:'nodeCode',halign:'center',align:'left'" width="120">项目编号</th>
                <th data-options="field:'nodeName',halign:'center',align:'left'" width="500">项目名称</th>
            </tr>
            </thead>
        </table>

    </div>

    <script>
        $(function() {

            $('#relateTemplateBtn').on('click', function() {
                var datagrid = $('#datagrid');
                var data = datagrid.datagrid('getSelected');

                if (!data) {
                    return showAlert('请选择一个节点进行此操作');
                }

                if (data.children && 0 < data.children.length) {
                    return showAlert('选择关联模板操作的节点不能存在子节点结构');
                }

                var win = openIframeWindow({
                    'title': '选择集团造价管理信息系统子项目',
                    'width': $(window).width() * 0.8,
                    'height': $(window).height() * 0.8,
                    'href': '${contextPath}project?cmd=subProjectChoose&projectId=' + data.id,
                    'buttons': [
                        {text: '确定', handler: function() {
                            var iframeWindow = win.find('iframe').get(0).contentWindow;
                            var row = iframeWindow.$('#treegrid').treegrid('getSelected');
                            if (!row) {
                                return top.showAlert("请选择一个模板明细记录");
                            } else {
                                alert(row.id);
                                win.dialog('close');
                                $('#datagrid').datagrid('reload')
                            }
                        }}
                    ]
                })
            });

            $('#importBtn').on('click', function() {
                var datagrid = $('#datagrid');
                var data = datagrid.datagrid('getSelected');

                if (!data) {
                    return showAlert('请选择一个节点进行此操作');
                }

                if (data.children && 0 < data.children.length) {
                    return showAlert('选择导入操作的节点不能存在子节点结构');
                }

                if (!data.tempId) {
                    return showAlert('选择导入操作的节点还没有关联模板');
                }



            })
        })
    </script>
</body>
</html>
