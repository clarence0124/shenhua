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
        <input type="button" value="关联模板明细" id="relateTemplateDetailBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
        <input type="button" value="向集团管理系统导入数据" id="importBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
    </div>

    <div data-options="region:'center'">

        <table id="datagrid" class="easyui-datagrid"  fit="true"
               data-options="method: 'get', pageSize:10,
						pageNumber:1,
						rownumbers:true,
						pagination:true,
						nowrap:true,
						fitColumns:false,
						pageList: [10,20,50,100],
						singleSelect: 'true',
						url: '${contextPath}project/listData' ">
            <thead>
            <tr >
                <th data-options="field:'id',checkbox:true" width="30"></th>
                <th data-options="field:'projectCode',halign:'center',align:'left'" width="120">项目编号</th>
                <th data-options="field:'projectName',halign:'center',align:'left'" width="500">项目名称</th>
                <th data-options="field:'createEmployee',align:'center'" width="150">创建人</th>
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

                var win = openIframeWindow({
                    'title': '项目关联模板',
                    'width': $(window).width() * 0.8,
                    'height': $(window).height() * 0.8,
                    'href': '${contextPath}project/' + data.id + "/relateTemplatePage",
                    'buttons': [
                        {text: '确定', handler: function() {
                            var iframeWindow = win.find('iframe').get(0).contentWindow;
                            var row = iframeWindow.$('#datagrid').datagrid('getSelected');
                            if (!row) {
                                return top.showAlert("请选择一个模板记录进行关联操作");
                            } else {
                                $.post('${contextPath}project/' + data.id + '/wbsTemplateRelate', {'_method': 'PUT', 'disciplineType': row.disciplineType, 'industryType': row.industryType}, function (r) {

                                    top.showAlert(r.msg, function() {
                                        if (r.success) {
                                            win.dialog('close');
                                            $('#datagrid').datagrid('reload')
                                        }
                                    })
                                }, 'json');
                            }
                        }}
                    ]
                })
            });

            $('#relateTemplateDetailBtn').on('click', function() {

                var datagrid = $('#datagrid');
                var data = datagrid.datagrid('getSelected');

                if (!data) {
                    return showAlert('请选择一个节点进行此操作');
                }

                var win = openIframeWindow({
                    'title': '项目结构关联模板明细',
                    'width': $(window).width() * 0.8,
                    'height': $(window).height() * 0.8,
                    'href': '${contextPath}project/' + data.id + "/relateTemplateDetailPage"
                });
                return false;
            });

            $('#importBtn').on('click', function() {
                var datagrid = $('#datagrid');
                var data = datagrid.datagrid('getSelected');

                if (!data) {
                    return showAlert('请选择一条项目记录进行此操作');
                }

                window.location.href = '${contextPath}project/' + data.id + '/projectStructurePage'
            })
        })
    </script>
</body>
</html>
