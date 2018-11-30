<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科研单位信息管理</title>
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css">
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="js/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
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
    <div style="max-width:900px;margin:0 auto;padding-top: 10px;">
        <div style="font-family: 'Times New Roman',Georgia,Serif,'Microsoft YaHei',sans-serif;font-size:12px;">
            <label style="margin:0">单位名称:</label> <input id="dwmc" class="easyui-textbox" type="text"
                                                         style="height:30px;width:150px;"/>
            <label style="margin-left:10px;">单位编码:</label> <input id="dwbm" class="easyui-textbox" type="text"
                                                                  style="height:30px;width:150px;"/>
            <label style="margin-left:10px;">可用标志:</label>
            <span style="height:30px;width:70px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="zt" name="zt" style="width:100%;height:30px;line-height:30px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: 'Times New Roman',Georgia,Serif,'Microsoft YaHei',sans-serif;font-size: 12px">
                    <option value="all">全部</option>
                    <option value="1">可用</option>
                    <option value="0">禁用</option>
                </select>
            </span>

            <div style="display: inline-block;margin-left:10px;">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="find()">查找</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="newUser()">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="editUser()">修改</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
                   style="width:70px;height:30px;border:1px solid #95B8E7;" onclick="deleteUser()">删除</a>
            </div>
        </div>
    </div>
    <!--<div style="margin-top:15px;margin-bottom:5px;">-->
    <!--<hr style=" height:2px;border:none;border-top:1px solid #95B8E7;"/>-->
    <!--</div>-->
    <!--表格-->
    <div style="margin-top:10px;width:100%;position:absolute;top:40px;bottom:0;left:0;">
        <table id="t_dwxx" title="科研单位信息" class="easyui-datagrid"
               style="width:100%;height:100%;"
               data-options="pagination:false,rownumbers:true,fitColumns:true,singleSelect:true">
        </table>
    </div>

    <div id="new_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#new_dlg-buttons" data-options="draggable:true,modal:true">
        <form id="new_fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
                <input name="new_dwmc" class="easyui-textbox" label="单位名称:" style="width:100%"/>
            </div>
        </form>
    </div>

    <div id="new_dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNew()"
           style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="closeDlg('new_dlg')" style="width:90px">取消</a>
    </div>

    <div id="edit_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#edit_dlg-buttons" data-options="draggable:true,modal:true">
        <form id="edit_fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
                <input name="dwmc" class="easyui-textbox" label="单位名称:" style="width:100%"/>
            </div>
            <div>
                <!--<input name="zt" class="easyui-textbox" required="true" label="可用标志:" style="width:100%">-->
                <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">可用标志:</label>
                <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                    <select id="edit_zt" name="zt" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                        <option value="1">可用</option>
                        <option value="0">禁用</option>
                    </select>
                </span>
            </div>
        </form>
    </div>

    <div id="edit_dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()"
           style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="closeDlg('edit_dlg')" style="width:90px">取消</a>
    </div>

</div>

<script type="text/javascript">
    var type;//操作类型,new:添加 edit:修改
    var param = {};
    $(function () {
        $("#dwmc").textbox('textbox').bind('click',function(){
            this.value = '';
        });
        $("#dwbm").textbox('textbox').bind('click',function(){
            this.value = '';
        });
        $("#dwmc").textbox("textbox").bind('keyup',function (event) {
            if (event.which == 13) {
                find();
            }
        });
        $("#dwbm").textbox("textbox").bind('keyup',function (event) {
            if (event.which == 13) {
                find();
            }
        });
    });

    $("#t_dwxx").datagrid({
        columns:[[
            {
                field:'dwmc',
                title:'单位名称',
                width:150
            },
            {
                field:'dwbm',
                title:'单位编码',
                width:100
            },
            {
                field:'zt',
                title:'可用标志',
                width:100,
                formatter:function (value,row,index) {
                    if(value === "1"){
                        return "可用";
                    }else{
                        return "禁用";
                    }
                }
            }
        ]]
    });


    function findAll() {
        var data = {
            a01: "",
            a02: "",
            a03: "all"
        };
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "company/query.do",
            data: data, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){

                }else if (data && data.rows) {
                    $('#t_dwxx').datagrid('loadData', data);//Load local data, the old rows will be removed.
                } else {

                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象

            }
        })

    }

    //查询
    function find() {
        var dwmc = ($("#dwmc").val()).trim();
        var dwbm = ($("#dwbm").val()).trim();
        var zt = ($("#zt").val()).trim();

        var data = {
            a01: dwmc,
            a02: dwbm,
            a03: zt
        };
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "company/query.do",
            data: data, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){
                    $.messager.alert('提示', "没有查询到符合条件的记录", "info");
                }else if (data && data.rows) {
                    $('#t_dwxx').datagrid('loadData', data);//Load local data, the old rows will be removed.
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

    function newUser() {
        $('#new_dlg').dialog('open').dialog('center').dialog('setTitle', '添加');
        $('#new_fm').form('clear');
        type = "new";
        //点保存saveUser()的之后才会从fm的form提交到数据库
    }

    //修改
    function editUser() {
        var row = $('#t_dwxx').datagrid('getSelected');//Return the first selected row record or null.
        if (row) {
            type = "edit";
            param.a02 = row.dwbm;//修改的时候，保存原来的部门编码
            $('#edit_dlg').dialog('open').dialog('center').dialog('setTitle', '修改');
            $('#edit_fm').form('load', row);//对话框中的form加载选择row的内容
        }
    }

    function saveNew() {
        var param = {};
        param.a01 = ($("input[name='new_dwmc']").val()).trim();
        if(!param.a01) return;
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "company/add.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $('#new_dlg').dialog('close');
                    findAll();
                } else {
                    //成功返回数据，但是返回false ?
                    $.messager.alert({
                        title: '提示',
                        msg: "添加失败！"
                    });
                }
            },
            error: function (xhr, status, error) {
                // $('#new_dlg').dialog('close');
                $.messager.alert({
                    title: '提示',
                    msg: "请求失败：" + status
                });
            }
        });
    }

    function saveEdit() {
        param.a01 = ($("input[name='dwmc']").val()).trim();
        param.a03 = $("#edit_zt").val();
        if(param.a01 == ""){
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "company/update.do",
            data: param,
            success: function (result) {
                if (result.state) {
                    $('#edit_dlg').dialog('close');
                    findAll();
                } else {
                    //成功返回数据，但是返回false ?
                    $.messager.alert({
                        title: '提示',
                        msg: "修改失败！"
                    });
                }
            },
            error: function (xhr, status, error) {
                // $('#edit_dlg').dialog('close');
                $.messager.alert({
                    title: '提示',
                    msg: "请求失败：" + status
                });
            }
        })
    }



    function closeDlg(id) {
        $('#' + id).dialog('close');
    }

    function deleteUser() {
        var row = $('#t_dwxx').datagrid('getSelected');
        if (row) {
            var dwbm = row.dwbm;
            var dwmc = row.dwmc;
            $.messager.confirm('提示', "是否删除该单位？", function (r) {
                if (r) {
                    //传递选中行数据
                    $.post('company/delete.do', {a01: dwbm}, function (result) {
                        if (result.state) {
                            $.messager.alert({
                                title: '提示',
                                msg: "删除成功！"
                            });
                            findAll();   // reload the user data
                        } else {
                            $.messager.alert({    // show error message
                                title: '提示',
                                msg: "删除失败：" + result.errorMsg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }

</script>
</body>
</html>