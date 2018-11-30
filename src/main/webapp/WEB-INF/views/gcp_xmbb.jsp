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
    <script src="js/jquery.table2excel.js" type="text/javascript"></script>
    
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
    <div id="find_btn">
         <div style="display:inline-block;margin-left:10px;margin-top:10px;">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="find()">查询</a>
        </div>
    </div>
    
    <%-- 表格 --%>
    <div style="margin-top:10px;width:100%;position:absolute;top:50px;bottom:0;left:0;" buttons="#find_btn">
        <table id="t_tjbb_xm" title="GCP项目信息统计" class="easyui-datagrid"
               style="width:100%;height:100%;"  
               data-options="pagination:false,rownumbers:true,fitColumns:true,singleSelect:true,toolbar:'#export_xm'">
        </table>
    </div>
    <div id="export_xm" style="padding:2px 5px;">
    <%-- download="GCP项目信息统计.xls" --%>
        <a id="a_export"  href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="exportXm()">导出</a>
    </div>

    <div id="names" class="easyui-dialog" title="受试者姓名" 
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-man'"
     style="width:500px;height:500px;padding:10px;">

         <table id="t_xm" class="easyui-datagrid"
               style="width:100%;height:100%;"  
               data-options="rownumbers:true,fitColumns:true,singleSelect:true">
        </table>

     </div>

</div>

<script type="text/javascript">

    $(function(){
        find();
    });
    var param = {};

     $("#t_xm").datagrid({
        // loadFilter: pagerFilter,
        columns:[[
            {
                field:'xm',
                title:'姓名',
                width:200
            }]]
     });


  
// 分页数据的操作  
    function pagerFilter(data) {  
        data.total = data.rows.length;

        var dg = $(this);  
        var opts = dg.datagrid('options');  
        var pager = dg.datagrid('getPager');  
        pager.pagination({  
            onSelectPage: function (pageNum, pageSize) {  
                opts.pageNumber = pageNum;  
                opts.pageSize = pageSize;  
                pager.pagination('refresh', {  
                    pageNumber: pageNum,  
                    pageSize: pageSize  
                });  
                dg.datagrid('loadData', data);  
            }  
        });  
        if (!data.originalRows) {  
            data.originalRows = (data.rows);  
        }  
        var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);  
        var end = start + parseInt(opts.pageSize);  
        data.rows = (data.originalRows.slice(start, end));  
        return data;  
    }  



// {"rows":[{"rzls":"22","syfq":"13","cdzy":"麻醉专业","xmmc":"盐酸帕洛诺司琼注射液","xmbm":"100001"},
    $("#t_tjbb_xm").datagrid({
        remoteSort: false,
        columns:[[
            {
                field:'xmmc',
                title:'项目名称',
                width:400,
                sortable:true
            },
            {
                field:'xmbm',
                title:'项目编码',
                width:200,
                hidden: true
            },
            {
                field:'syfq',
                title:'试验分期',
                width:100
            },
            {
                field:'cdzy',
                title:'承担专业',
                width:200,
                sortable:true
            },
            {
                field:'rzls',
                title:'入组例数',
                width:100,
                sortable:true,
                sorter:function(a,b){  
                    a = parseInt(a);  
                    b = parseInt(b); 
                    return a-b;  
			    }  
            },            
            {
                field:'sszxm',
                width:100,
                title:"受试者姓名",
                formatter: function(value,row,index){
                    var theRow = JSON.stringify(row).replace(/"/g,"'");
                    return '<a style="color:blue;text-decoration: none" onclick="findNames(' + theRow + ')">受试者姓名</a>';
                }
            },
        ]]
    });


    function base64 (s) { return window.btoa(unescape(encodeURIComponent(s))) }
    
  

    function genExpContent(data){
        var str = '<tr><td>项目名称\t</td><td>试验分期\t</td><td>承担专业\t</td><td>入组例数\t</td><td>受试者姓名\t</td>';
        var items={'xmmc':'','syfq':'','cdzy':'','rzls':'','sszxm':''};
        for(var i=0;i<data.length;i++){
            str += "<tr>";
            for(let item in items){
                str += "<td>" + data[i][item]  +"\t" + "</td>";
            }
            str += "</tr>";
        }
      var worksheet = '费用表报';
      var uri = 'data:application/vnd.ms-excel;base64,';
      var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>'+ worksheet + '</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>'+ str +'</table></body></html>';
    //   $("#a_export").attr('href',uri + base64(template));
       window.location.href = uri + base64(template);
    }

    //输出base64编码
    function base64 (s) {
        // unescape对中文转码了
         return window.btoa(unescape(encodeURIComponent(s))); 
    }


    function genSSZXM(str,data,i){
        data[i].sszxm = str;
    }

  // 导出数据
    function exportXm() {
        var data = $("#t_tjbb_xm").datagrid("getRows");
        genAllNames(data,genExpContent);
    }

    function genAllNames(data,callback){
         // 先把所有姓名查出来，再导出
        var dtd = $.Deferred();
        var count = 0;
        for(var i=0;i<data.length;i++){
            var xmbm = data[i].xmbm;
            $.when(getNames(xmbm,i,data,genSSZXM)).
            done(function(){
                count++;
                if(count === data.length){
                    dtd.resolve();
                    console.log("count:" + count);
                    callback(data);
                }
            }); // 在回调中形成
        }
        return dtd;
    }

    function getNames(xmbm,i,data,callBack){
            var dtd = $.Deferred();
            param.a01 = xmbm;
            $.ajax({
                type: "GET",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                url: "gcprzlsdetail/query.do",
                data: param, //规定要发送到服务器的数据
                success: function (nameData) {
                        if(nameData.rows == "0"){
                            $.messager.alert('提示', "没有查询到受试者姓名", "info");
                        }else if (nameData && nameData.rows) {
                            // $('#t_xm').datagrid
                            var arr = nameData.rows;
                            var nameStr = '';
                            for(var k=0; k<arr.length; k++){
                                if(k === arr.length -1){
                                    nameStr += arr[k]['xm'];
                                }else{
                                    nameStr += arr[k]['xm'] + ",";
                                }
                            }
                            callBack(nameStr,data,i);
                            dtd.resolve();
                        } else {
                            $.messager.alert('Error', "返回受试者姓名数据格式有误！", "error");
                        }
                    },
                    error: function (xhr, status, error) {
                            //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                            $.messager.alert('提示', "查询受试者姓名数据失败！", "error");
                        }
                    });
            return dtd;
                
    }

    // 姓名
    function findNames(row) {
        
        var xmmc = row.xmmc;
        $("#names").attr("title",xmmc);
        param.a01 = row.xmbm;
        $.ajax({
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "gcprzlsdetail/query.do",
            data: param, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){
                    $.messager.alert('提示', "没有查询到符合条件的记录", "info");
                }else if (data && data.rows) {
                    // $('#t_xm').datagrid
                    $('#names').window('open').window('center');
                    $('#t_xm').datagrid('loadData', data);//Load local data, the old rows will be removed.
                    
                } else {
                    $.messager.alert('Error', "返回数据格式有误！", "error");
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('提示', "查询数据失败！", "error");
            }
        });

    }
    // 项目
    function find() {
        param.a01 = $("#startDay").val();
        param.a02 = $("#endDay").val();
        $.ajax({
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "gcprzls/query.do",
            data: param, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){
                    $.messager.alert('提示', "没有查询到符合条件的记录", "info");
                }else if (data && data.rows) {
                    $('#t_tjbb_xm').datagrid('loadData', data);//Load local data, the old rows will be removed.
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


</script>
</body>
</html>