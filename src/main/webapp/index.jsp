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
    <title>神华投控对接集团管理系统原型设计</title>
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
        <input type="button" value="关联集团三算管理系统项目" id="relateSubProjectBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
        <input type="button" value="导出项目概算" id="relateTemplateDetailBtn" style="height:30px; margin-top:10px; margin-left: 20px;"/>
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
                <th data-options="field:'projectCode',align:'left'" width="120">全过程项目编号</th>
                <th data-options="field:'projectName',align:'left'" width="400">全过程项目名称</th>
                <th data-options="field:'subProjectName',align:'left'" width="400">三算系统子项目名称</th>
                <th data-options="field:'industryTypeName',align:'left'" width="150">板块大类</th>
                <th data-options="field:'disciplineTypeName',align:'left'" width="150">板块小类</th>
            </tr>
            </thead>
        </table>

    </div>

    <script>

        function getProjectSelectRow() {
            var datagrid = $('#datagrid');
            var data = datagrid.datagrid('getSelected');
            if (!data) {
                return showAlert('请选择一个项目进行此操作');
            } else {
                return data;
            }
        }

        $(function() {
            var winWidth = $(window).width() * 0.8;
            var winHeight = $(window).height() * 0.8;
            $('#relateSubProjectBtn').on('click', function() {
                var project = getProjectSelectRow();
                if (project) {
                    var win = openIframeWindow({
                        'title': '1、从集团三项管理系统的子项目列表中选择需要关联的子项目。',
                        'width': winWidth,
                        'height': winHeight,
                        'href': '${contextPath}project/' + project.id + "/relateSubProjectPage",
                        'buttons': [
                            {text: '下一步', handler: function() {
                                var iframeWindow = win.find('iframe').get(0).contentWindow;
                                var subProject = iframeWindow.$('#datagrid').datagrid('getSelected');
                                if (!subProject) {
                                    return top.showAlert("请先从集团三项管理系统的子项目列表中选择一条子项目记录，再进行操作。");
                                } else {

                                    var industryTypeName = subProject.industryTypeName;
                                    if (!industryTypeName) {
                                        return top.showAlert("请先完善板块大类的信息。");
                                    }

                                    var win1 = openIframeWindow({
                                        'title': '2、确定向集团三算管理系统导入数据所使用的模板。',
                                        'width': $(window).width() * 0.8,
                                        'height': $(window).height() * 0.8,
                                        'href': '${contextPath}project/' + project.id + "/relateTemplatePage?industryTypeName=" + industryTypeName,
                                        'buttons': [
                                            {text: '确定', handler: function() {
                                                var iframeWindow = win1.find('iframe').get(0).contentWindow;
                                                var category = iframeWindow.$('#datagrid').datagrid('getSelected');
                                                if (!category) {
                                                    return top.showAlert("请选择一个模板记录进行关联操作");
                                                } else {
                                                    console.log(category)
                                                    $.post('${contextPath}project/' + project.id + '/relateSubProjectAndIndustryTypeName', {'subProjectId': subProject.id, 'disciplineType': category.disciplineType, 'industryType': category.industryType}, function (r) {

                                                        top.showAlert(r.msg, function() {
                                                            if (r.success) {
                                                                win1.dialog('close');
                                                                win.dialog('close');
                                                                $('#datagrid').datagrid('reload')
                                                            }
                                                        })
                                                    }, 'json');
                                                }
                                            }}, {text: '取消', handler: function() {
                                                win1.dialog('close');
                                                win.dialog('close');
                                            }}
                                        ]
                                    });
                                }
                            }},
                            {text:'取消', handler: function() {
                                win.dialog('close')
                            }}
                        ]
                    })
                }
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
        })
    </script>
</body>
</html>
