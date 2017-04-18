var openIframeWindow = function(options) {
    var opts = $.extend({
        title : '&nbsp;',
        width : 640,
        height : 480,
        modal : true,
        onClose : function() {
            $(this).dialog('destroy');
        }
    }, options);
    opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
    var href=opts.href;
    opts.href="";
    var dialog=$('<div style="overflow:hidden;" ><iframe style="width:100%;height:100%;border: 0;overflow: hidden;" ></iframe></div>').dialog(opts);
    dialog.find("iframe").attr("src",href);
    return dialog;
};

var showAlert = function(msg, callback) {
    if (top.jQuery && top.jQuery.messager) {
        top.jQuery.messager.alert("提示", msg, 'info', callback);
    }
    else if (jQuery && jQuery.messager) {
        jQuery.messager.alert('提示', msg, 'info');
    } else {
        window.alert(msg);
        if (callback && ("function" == typeof callback)) callback();
    }
}

var showConfirm = function(title, msg, fn1, fn2) {
    if (top.jQuery && top.jQuery.messager) {
        top.jQuery.messager.confirm(title, msg, function(r){
            if (r) {
                if (fn1 && ("function" == typeof fn1)) fn1();
            }	else {
                if (fn2 && ("function" == typeof fn2)) fn2();
            }
        });
    }
    else if (jQuery && jQuery.messager) {
        jQuery.messager.confirm(title, msg, function(r){
            if (r) {
                if (fn1 && ("function" == typeof fn1)) fn1();
            }	else {
                if (fn2 && ("function" == typeof fn2)) fn2();
            }
        });
    } else {
        if (window.confirm(msg)) {
            if (fn1 && ("function" == typeof fn1)) fn1();
        } else {
            if (fn2 && ("function" == typeof fn2)) fn2();
        }
    }
}

/**
 * 金额格式化方法，显示两位小数并带千位分隔符
 */
function numberFormat(value) {
    var symbol = '';
    if (-1 != value.indexOf('-')) {
        symbol = '-';
        value = value.substring(1, value.length);
    }
    var ps = value.split(".");
    var prefixLen = ps[0].length%3;
    var prefix = ps[0].substring(0, prefixLen);
    for (var i = 0; i < parseInt(ps[0].length/3); i++) {
        if ('' != prefix) prefix += ',';
        prefix += ps[0].substring(prefixLen, prefixLen+3);
        prefixLen += 3;
    }
    return symbol + prefix + (1 < ps.length ? ("." + ps[1]) : "");
}

/**
 * easyui单元格格式化，金额显示两位小数并带千位分隔符
 */
function easyuiMoneyFormatter(value, row, index) {
    if (null == value || ('' == value && 0 != value)) {
        return '';
    }
    if (0 == value) return '0.00';
    if (!isNaN(value)) {
        value = parseFloat(value).toFixed(2);
        if (-1 == value.indexOf(',')) {
            return numberFormat(value);
        }
    }
    return '0.00';
}