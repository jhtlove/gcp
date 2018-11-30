<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科研项目统计</title>
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css">
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="js/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="js/FileSaver.js" type="text/javascript"></script>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="container" style="height:100%;width: 100%;padding:0;">
    <div class="easyui-panel" style="width:100%;">
        <div style="margin-bottom:20px;margin-left:20px;display:inline-block" >
            <input id="startDay" data-options="formatter:myformatter,parser:myparser" class="easyui-datebox" label="开始日期:" labelPosition="top" style="width:100%;"/>
        </div>
        <div style="margin-bottom:20px;margin-left:20px;display:inline-block">
            <input id="endDay" data-options="formatter:myformatter,parser:myparser" class="easyui-datebox" label="结束日期:" labelPosition="top" style="width:100%;"/>
        </div>
         <div style="display:inline-block;margin-left:10px;">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="find()">查询</a>
        </div>
    </div>
    <%-- 表格 --%>
    <div style="margin-top:10px;width:100%;position:absolute;top:50px;bottom:0;left:0;">
        <table id="t_tjbb_fy" title="GCP检查费用统计" class="easyui-datagrid"
               style="width:100%;height:100%;"
               data-options="pagination:false,rownumbers:true,fitColumns:true,singleSelect:true,toolbar:'#export_fy'">
        </table>
    </div>
    <div id="export_fy" style="padding:2px 5px;">
    <%-- download="GCP检查费用统计.xls" --%>
        <a id="a_export"  href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="exportFy()">导出</a>
    </div>

</div>

<script type="text/javascript">

    var param = {};
// {"yzje":"0","xmmc":"Vericiguat","yznr":"西药处方(GCP)","xm":"何佳劲","zxksdm":"506","yzzxsj":null,"zxksmc":"药物临床试验机构办公室"}]}
    $("#t_tjbb_fy").datagrid({
        remoteSort: false,
        columns:[[
            {
                field:'xmmc',
                title:'项目名称',
                width:400,
                sortable:true
            },
            {
                field:'xm',
                title:'受试者姓名',
                width:100,
                sortable:true
            },
            {
                field:'yznr',
                title:'检查医嘱名称',
                width:300
            },
            {
                field:'yzzxsj',
                title:'检查日期',
                width:200,
                sortable:true
            },
            {
                field:'zxksmc',
                title:'执行科室',
                width:200,
                sortable:true
            },
            {
                field:'yzje',
                title:'金额',
                width:100,
                sortable:true,
                sorter:function(a,b){  
                    a = parseFloat(a);  
                    b = parseFloat(b); 
                    return a-b;  
			    }  
            }
            
        ]]
    });


    // function exportFy() {
    //     var data = $("#t_tjbb_fy").datagrid("getRows");
    //     var blob = new Blob(data,{type:'application/vnd.ms-excel'});
    //     saveAs(blob, "GCP检查费用统计.xls");
    // }

    // 导出数据
    function exportFy() {
        var jsonData = $("#t_tjbb_fy").datagrid("getRows");
        // if(jsonData.length < 1) return;
        var str = '<tr><td>项目名称\t</td><td>受试者姓名\t</td><td>检查医嘱名称\t</td><td>检查日期\t</td><td>执行科室\t</td><td>金额\t</td></tr>';
        var items={'xmmc':'','xm':'','yznr':'','yzzxsj':'','zxksmc':'','yzje':''};
        for(let i = 0 ; i < jsonData.length ; i++ ){
            str+='<tr>';
            for(let item in items){
                //增加\t为了不让表格显示科学计数法或者其他格式
                str+="<td>" + jsonData[i][item] + '\t' + '</td>';     
            }
            str+='</tr>';
        }
      //Worksheet名
      var worksheet = '费用表报';
      var uri = 'data:application/vnd.ms-excel;base64,';
      var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>'+ worksheet + '</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>'+ str +'</table></body></html>';
      window.location.href = uri + base64(template); //a href="#" 时表示一个空链接，点击时链接停留在当前页面，相当于刷新当前页面
    //    $("#a_export").attr('href',uri + base64(template));
    }
    //输出base64编码
    function base64 (s) { return window.btoa(unescape(encodeURIComponent(s))) }


    // 项目
     function find() {
        var start = $("#startDay").val();
        var end = $("#endDay").val();
        if(!start){
            $.messager.alert('提示',"请选择开始日期！","error");
            return;
        }
        if(!end){
            $.messager.alert('提示',"请选择结束日期！","error");
            return;
        }
        if(start > end){
            $.messager.alert('提示',"开始日期不能大于结束日期！","error");
            return;
        }
        param.a01 =start;
        param.a02 = end;

        $.ajax({
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "gcpfymx/query.do",
            data: param, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){
                    $.messager.alert('提示', "没有查询到符合条件的记录", "info");
                }else if (data && data.rows) {
                    $('#t_tjbb_fy').datagrid('loadData', data);//Load local data, the old rows will be removed.
                } else {
                    $.messager.alert('Error', "返回数据格式有误！", "error");
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('提示', "查询数据失败！", "error");
            }
        })

    }

    function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d);
    }

    function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('/'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
    }


</script>
</body>
</html>