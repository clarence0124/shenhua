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
        <input type="button" value="导入概算数据" id="import1Btn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="导入合同数据" id="import2Btn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="导入进度款结算数据" id="import3Btn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="导入工件计价数据" id="import4Btn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'north'" style="height: 50px">
        <input type="button" value="导入审批数据" id="import5Btn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'center'">

        <table id="datagrid" class="easyui-datagrid"  fit="true"
               data-options="method: 'get',
						nowrap:true,
						fitColumns:false,
						singleSelect: 'true',
						url: '${context}/project?cmd=listSubProject' ">
            <thead>
            <tr >
                <th data-options="field:'id',checkbox:true" width="30"></th>
                <th data-options="field:'subProjName',align:'center'" width="400">子项目名称</th>
                <th data-options="field:'projectName',align:'center'" width="400">主项目名称</th>
                <th data-options="field:'industryTypeName',align:'center'" width="120">板块大类</th>

            </tr>
            </thead>
        </table>

    </div>

    <script>
        $(function() {
            $('#importBtn').on('click', function() {
                var datagrid = $('#datagrid');
                var data = datagrid.datagrid('getSelected');

                if (!data) {
                    return showAlert('请选择一条项目记录进行此操作');
                }

                var win = openIframeWindow({
                    'title': '选择集团造价管理信息系统子项目,',
                    'width': $(window).width() * 0.8,
                    'height': $(window).height() * 0.8,
                    'href': '${context}/project?cmd=subProjectChoose&projectId=' + data.id,
                    'buttons': [[
                        {text: '确定', handler: function() {

                        }}
                    ]]
                })
            })
        })
    </script>
</body>
</html>
