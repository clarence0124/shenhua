(function($) {
	
  // 功能选择树
  var funTreeObj = null;
	
  var FunctionScope = {
	
    // 树配置参数
	"funTreeSetting": {
      "view": {"selectedMulti": false}
      , "check": {"enable": true, "chkStyle": "checkbox", "chkboxType": {"chkboxType": { "Y": "p", "N": "s" }}}
      , "callback": {
        "onExpand": function(event, treeId, treeNode) { // 展开时关闭其它同级节点
          if (funTreeObj) {
            var siblings = funTreeObj.getNodesByParam("pid", treeNode.pid, null);
            $.each(siblings, function() {
              if (treeNode.id != this.id && this.open) {
                funTreeObj.expandNode(this, false, true);	
              }
            });
          }
        }
        , "onClick": function (event, treeId, treeNode) {
          $('#functionInfo').load('fun/modFunction.do', {'functionId' : treeNode.id});
        }
      }
    }
  
    //加载功能树
    , "loadFunctionTree": function() {
      $.getJSON('fun/listFunctionForSelect.do', function(zTreeNodes) {
        funTreeObj = $.fn.zTree.init($("#funTree"), FunctionScope.funTreeSetting, zTreeNodes);
        $('#functionInfo').html('');
      });
    }

     //增加功能按钮事件
    , "addFunctionBtnClick": function() {
      var url = 'fun/addFunction.do';
      var nodes = funTreeObj.getSelectedNodes();
      var selNode = null;
      if (1 == nodes.length) {
        selNode = nodes[0];
        url = url + '?function.parentId=' + nodes[0].id;
      }
		    
      var addDialog = top.sy.modalDialog({
        "id": 'modFunDialog'
        , "title": '添加功能'
        , "iconCls": 'icon-server_add'
        , "width": 650
        , "height": 480
        , "href": url
        , "onDestroy": function() {
		  	  
        }
        , buttons: [
          {"text": '保存', "handler": function() {
            addDialog.dialog('body').find('form').form('submit', {
              "success": function(result) {
                result = JSON.parse(result);
                showAlert(result.msg, function() {
                  if (result.status) {
                    funTreeObj.addNodes(selNode, result.ztreeData, false);
                    addDialog.dialog('destroy');
                  }
                });
              }
            });
          }}
          , {text: '关闭', handler: function() {addDialog.dialog('destroy')}}
        ]
      });
    }

    //删除功能按钮事件
    , "delFunctionBtnClick": function() {
      var nodes = funTreeObj.getSelectedNodes();
      if (1 != nodes.length) {
        showAlert('请先选择要删除的功能！');
        return false;
      }
      var selNode = nodes[0];
      if (selNode.children && 0 < selNode.children.length) {
        showAlert('该功能节点下仍存在子功能节点，不能删除！');
        return false;
      }

      showConfirm('警告', '数据删除后将不能恢复，您确定要删除当前所选项目？', function() {
        var url = 'fun/deleteFunction.do?function.id=' + selNode.id;
        $.getJSON(url, function(result) {
  	      showAlert(result.msg, function() {
            if (result.status) {
              funTreeObj.removeNode(selNode, false);
              $('#functionInfo').html('');	
            } 
  	      });
        });
      });
    }
    , "expandFunctionBtnClick": function() {
    	funTreeObj.expandAll(true);
    }
    , "collapseFunctionBtnClick": function() {
    	funTreeObj.expandAll(false);
    }
  };
	
  //加载树
  FunctionScope.loadFunctionTree();
  
  //注册按钮事件
  $('button[id]').each(function() {
	// 按约定检索是否有该事件。
	var onClickFunction = FunctionScope[this.id + 'Click'];
	if (onClickFunction) {
	  $(this).on('click', onClickFunction);
	}
  });

})(jQuery);

//修改功能按钮事件
/*function modFunctionBtnClick() {
  var nodes = funTreeObj.getSelectedNodes();
  if (1 != nodes.length) {
    $.messager.alert('提示', '请先选择要删除的功能！', 'error');
    return false;
  }
  if (!selNode) {
    showTips('请选择一个节点进行操作。');
    return false;
  }
  
  var url = 'fun/editFunctionPage.action?do.id=' + selNode.id;
  $.layer({
      type : 2,
      shade : [0],
      title : ['修改功能',true],
      iframe : {src : url},
      shade : [0.5 , '#000' , true],
      area : ['800px' , '600px'],
      offset : ['50%', '50%'],
      end : function() {
	    window.location.href = window.location.href;
	  }
  });
}*/