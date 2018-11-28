<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>长沙市中心医院GCP 登录系统</title>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<!--
<%
Object username =  session.getAttribute("username");
String strUserName = (username == null || username == "") ? "" : username.toString(); 
%>
-->
<script>
    $(document).ready(
        function () {
            $("#username").keyup(function (event) {
                if (event.which == 13) {
                    $("#password").focus();
                }
            });
            
            $("#password").keyup(function (event) {
                if (event.which == 13) {
                    submitForm();
                }
            });

            $('#ff').form('load', {
                username: '<%=strUserName%>',
            pwd: ''
        });
    });

    $.extend($.fn.validatebox.defaults.rules, {
        customMinLength: {
            validator: function (value, param) {
                return value.length >= param[0] && value.length <= param[1];
            },
            message: '请输入{0} - {1} 个字符.'
        }
    });

    function submitForm() {
	    $("#ff").submit();
	    return;
    }

    function clearForm() {
        $('#ff').form('reset');
    }
</script>
</head>
<body style=" background-color:#f7f7ff" style="text-align:center;">
<div style=" margin:0 auto; width:447px; height:393px; margin-top:100px; padding-top:1px; background-image:url(img/login_bg.png); background-repeat:no-repeat">
    <div style="width:400px; margin-left:100px; margin-top:204px;">
        <form id="ff" method="post" action="logon.do">
            <div style="margin-top:5px;">
              <input class="easyui-validatebox" type="text" id="username" name="username" style="border:0px; height:33px; width: 280px; " data-options="required:true,validType:'customMinLength[3,15]',missingMessage:'请输入用户名'" />
            </div>
            <div style="margin-top:27px;">
              <input class="easyui-validatebox" type="password" id="password" name="password" style="border:0px; height:33px; width: 280px; " data-options="required:true,validType:'length[3,15]',missingMessage:'请输密码',invalidMessage:'密码长度必须为3-15位'" />
            </div>
        </form>
        <div style="text-align:left;padding:5px; margin-top:40px;">
                <a href="javascript:void(0)" class="easyui-linkbutton" onClick="submitForm()" style="width:80px" data-options="iconCls:'icon-ok'">登录</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onClick="clearForm()" style="width:80px" data-options="iconCls:'icon-clear'">重置</a>
        </div>
    </div>
</div>
</body>
</html>
