<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GCP管理系统</title>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>

<style type="text/css">
html, body
{
	height: 100%;
	padding: 0px;
	margin: 0px;
	border: 0px;
}

.pannelContent
{
	padding:0px;
	background-color:#fff;
}

#btn_Exit:link {
    color:#fff;
    text-decoration:none;
}
#btn_Exit:hover {
    color:#afafaf;
    text-decoration:none;
}
#btn_Exit:visited {
    color:#fff;
    text-decoration:none;
}
#btn_Exit:active {
    color:#aaa;
    text-decoration:none;
}
</style>

<script language="javascript">
$(document).ready(
function(){
	$('#tt').tree({
		url: 'menu/query.do',
		method: 'get',
		loadFilter: function(data){
			if (data.rows){
				return data.rows;
			} else {
				return data;
			}
	    },
		lines: true,
		animate: true,	
		onClick: function(node){
			//alert(node.url);
			addPanel(node.text, node.url)
		}
	});
	

}
);

function checkLogin()
{
try
{
    $.ajax({
        type: "get",
        contentType: "application/x-www-form-urlencoded",
        url: "islogin.do",
        dataType: "html",
        success: function (data) {
        	//alert(data);
            if (data == "false") {
            	alert("因您长时间未操作，请重新登录。");
                window.location.href = "index.jsp";
            }
        },
        error: function (xhr, status, error) {
            alert("服务器返回错误，请联系系统管理员，服务是否开启，err: " + error + " status:" + status);
            window.location.href = "index.jsp";
        }
    });
}
catch(e){alert(e.description);}

}
window.setInterval("checkLogin()", 10000);

var index = 0;
function addPanel(tabTitle, tabUrl){
	/*
	var tab = $('#tab_main').tabs('getTab', tabTitle);
	if (tab)
	{
		alert("有：" + tabTitle);
	}
	*/
	if (tabUrl.length < 1)
		return;
	if ($('#tab_main').tabs('exists', tabTitle)){
		$('#tab_main').tabs('select', tabTitle);
	} else {
		index++;
		$('#tab_main').tabs('add',{
			title: tabTitle,
			content: '<div style="position: absolute; left:6px; right:6px; top:35px; bottom:0px;"><iframe src="' + tabUrl + '" width="100%" height="100%" frameborder=0></iframe></div>',
			closable: true
		});
	}
}
</script>
</head>

<body class="easyui-layout">

<%
Object username =  session.getAttribute("username");
String strUserName = (username == null || username == "") ? "" : username.toString(); 

Object yourname =  session.getAttribute("yourname");
String strYourName = (yourname == null || yourname == "") ? "" : yourname.toString(); 
%>

<div data-options="region:'north'" style=" height:35px; width:100%; overflow:hidden; background:#204d84; vertical-align:bottom" title="" collapsible="true" data-options="border:false">
		<div style="width:350px; float:left; color:#FFFFFF; margin:8px; ">
		长沙市中心医院　药物临床试验质量管理GCP系统
        </div>
        <div style="width:100px; float:right; margin:8px; color:#FFFFFF; ">
	            用户<%=strUserName%> <a id="btn_Exit" href="logout.do">退出</a>
        </div>
</div>
   
    <div data-options="region:'west',split:true" title="GCP系统" style="width:180px; height:100%;">
		<div class="easyui-layout" style=" height:100%;" data-options="fit:true">
			<div data-options="region:'center',fit:true,split:true,border:false,collapsible:true" title="" style="padding:5px;  ">
				<ul id="tt" class="easyui-tree"  data-options="
						url: '/menu/query.do',
						method: 'get',
						lines:true,
						animate: true
					"></ul>
			</div>
			
			<div id="menu_down" data-options="region:'south',split:true" style="padding:0px; border:0px; height:200px; padding:5px; " title="常用功能">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100%; margin-top:5px; " onClick="javascript:alert('点了我')">按钮1</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100%; margin-top:5px; " onClick="javascript:alert('点了我')">按钮2</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100%; margin-top:5px; " onClick="javascript:alert('点了我')">按钮3</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100%; margin-top:5px; " onClick="javascript:alert('点了我')">按钮4</a>
			</div>
		</div>
			
    </div>
    
	<div id="content" data-options="region:'center'" style="padding:0px; border:0px;">
		<div id="tab_main" class="easyui-tabs" style="width:100%;height:100%">
            <div title="首页" class="pannelContent">
              <div style="margin:10px;">
                GCP （药物临床试验质量管理规范） 编辑
中文名称为"药物临床试验质量管理规范"， 是规范药物临床试验全过程的标准规定，其目的在于保证临床试验过程的规范，结果科学可靠，保护受试者的权益并保障其安全。在我国引入、推动和实施 GCP已有近十年的时间。我国自1986年起就开始了解国际上GCP发展的信息；1992年派员参加了WHO的GCP指南的定稿会议；1993年收集了各国的GCP指导原则并邀请国外专家来华介绍国外实施GCP的情况；1994年举办GCP研讨会并开始酝酿起草我国的GCP规范；1995年成立了由5位临床药理专家组成的起草小组，起草了我国《药品临床试验管理规范》（送审稿），并开始在全国范围内组织GCP知识的培训；1998年3月2日卫生部颁布了《药品临床试验管理规范）（试行）；国家药品监督管理局成立后对该规范进行了进一步的讨论和修改，于1999年9月1日以13号局长令正式颁布并实施。               <br>
              </div>
                
			</div>
        </div>
	</div>
    
    <div data-options="region:'south',split:false" style="height:35px; vertical-align:text-bottom">
        <div style=" margin-top:8px; margin-right:8px; height:20px; text-align:right; vertical-align:bottom">
	        长沙市中心医院信息科　版权所有
        </div>
    </div>

</body>
</html>
