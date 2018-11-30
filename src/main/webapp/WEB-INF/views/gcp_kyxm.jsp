<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科研项目</title>
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
<div  id="main" class="easyui-layout" style="height: 100%;padding:0">
    <div data-options="region:'north',collapsible:false" style="height: 50px;overflow: hidden">
        <!--工具条-->
        <div style="max-width:600px;margin:0 auto;padding-top:10px;padding-bottom:10px;">
            <div style="font-family: 'Times New Roman',Georgia,Serif,'Microsoft YaHei',sans-serif;font-size:12px;">
                <label style="margin:0">项目名称:</label> <input id="xmmc" class="easyui-textbox" type="text"
                                                             style="height:30px;width:110px;"/>
                <!--<label style="margin-left:10px;">项目编码:</label> <input id="xmbm" class="easyui-textbox" type="text"-->
                <!--style="height:30px;width:150px;">-->
                <label style="margin-left:10px;">项目状态:</label>
                <span style="height:30px;width:70px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                    <select id="zt" name="zt" style="width:100%;height:30px;line-height:30px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: 'Times New Roman',Georgia,Serif,'Microsoft YaHei',sans-serif;">
                        <option value="all">全部</option>
                        <option value="1" selected="selected">申请</option>
                        <option value="2">已审批</option>
                    </select>
                </span>

                <div style="display: inline-block;margin-left:10px;">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
                       style="width:60px;height:30px;border:1px solid #95B8E7;" onclick="find()">查找</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"
                       style="width:60px;height:30px;border:1px solid #95B8E7;" onclick="newXm()">添加</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"
                       style="width:60px;height:30px;border:1px solid #95B8E7;" onclick="editXm()">修改</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
                       style="width:60px;height:30px;border:1px solid #95B8E7;" onclick="deleteXm()">删除</a>
                </div>
            </div>
        </div>
    </div>

    <div data-options="region:'east',collapsible:false" style="width:510px;">
        <!--详情-->
        <!--style="background-color: #E0ECFF"-->
        <div id="xq_toolbar" class="datagrid-toolbar" >
            <!--data-options="size:'large'"-->
            <span style="display:inline-block;margin-right: 10px;margin-left:1px;color:#0E2D5F;font-size:12px;font-weight:bold;">科研项目详情</span>
            <a id="xq_SaveBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save"
               onclick="saveXmxq()">保存</a>
            <!--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear"-->
            <!--onclick="deleteXm()">删除</a>-->
            <a id="sp_Button" style="display: none" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok"
               onclick="spXm()">审批</a>
        </div>
        <form id="f_kyxmxq" method="post" novalidate style="margin-left:10px;padding:0" toolbar="#xq_toolbar">
            <table style="width:100%">
                <tr style="display:inline-block;">
                    <!--readonly="readonly"-->
                    <td style="width:150px;"><input id="xq_xmbm" name="xq_xmbm" class="easyui-textbox"  label="项目编码:" style="width:100%" labelPosition="top" readonly="readonly"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="cfdah" name="cfdah" class="easyui-textbox"  label="CFDA批件号:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="ywmc" name="ywmc" class="easyui-textbox" label="药物名称:" style="width:100%" labelPosition="top"/></td>
                </tr>

                <tr style="display:inline-block;">
                    <td  style="width:150px;">
                        <!--<input name="ywjx" class="easyui-textbox" required="true" label="药物剂型:" style="width:100%" labelPosition="top">-->
                        <label style="height: 22px;line-height: 22px;vertical-align: middle;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">药物剂型:</label>
                        <span style="height:24px;width:156px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                            <select id="ywjx" name="ywjx" style="width:100%;height:24px;line-height:24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;">
                            </select>
                        </span>
                    </td>
                    <td  style="width:150px;padding-left:5px"><input id="syz" name="syz" class="easyui-textbox" label="适应症:" style="width:100%" labelPosition="top"></td>
                   
                    <td  style="width:150px;padding-left:5px">
                        <label style="height: 22px;line-height: 22px;vertical-align: middle;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">注册分类:</label>
                        <span style="height:24px;width:156px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                            <select id="zcfl" name="zcfl" style="width:100%;height:24px;line-height:24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;">
                            </select>
                        </span>
                    </td>
                </tr>

                <tr style="display:inline-block;">
                    <!--<td  style="width:150px;"><input name="syfq" class="easyui-textbox" required="true" label="试验分期:" style="width:100%" labelPosition="top"></td>-->
                    <td style="width:150px;">
                        <label style="height: 22px;line-height: 22px;vertical-align: middle;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">试验分期:</label>
                        <span style="height:24px;width:156px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                            <select id="syfq" name="syfq" style="width:100%;height:24px;line-height:24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;">
                            </select>
                        </span>
                    </td>
                    <!--<td  style="width:150px;padding-left:5px"><input name="lxlb" class="easyui-textbox" required="true" label="立项类别:" style="width:100%" labelPosition="top"></td>-->
                    <td style="width:150px;padding-left:5px">
                        <label style="height: 22px;line-height: 22px;vertical-align: middle;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">立项类别:</label>
                        <span style="height:24px;width:156px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                            <select id="lxlb" name="lxlb" style="width:100%;height:24px;line-height:24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;">
                            </select>
                        </span>
                    </td>
                    <td  style="width:150px;padding-left:5px">
                        <!--<input id="scxs" name="scxs" class="easyui-textbox" required="true" label="审查形式:" style="width:100%" labelPosition="top">-->
                        <label style="height: 22px;line-height: 22px;vertical-align: middle;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">审查形式:</label>
                        <span style="height:24px;width:156px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                            <select id="scxs" name="scxs" style="width:100%;height:24px;line-height:24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;">
                            </select>
                        </span>
                    </td>
                </tr>

                <tr style="display:inline-block;">
                    <td  style="width:150px;"><input id="sbz" name="sbz" class="easyui-textbox" label="申办者:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="sbzlxdh" name="sbzlxdh" class="easyui-textbox"   label="申办者联系方式:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="cro" name="cro" class="easyui-textbox"   label="CRO:" style="width:100%" labelPosition="top"/></td>
                </tr>

                <tr style="display:inline-block;">
                    <td  style="width:150px;"><input id="crolxdh" name="crolxdh" class="easyui-textbox"  label="CRO联系方式:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="zzdw" name="zzdw" class="easyui-textbox"   label="组长单位:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="zzdwyjy" name="zzdwyjy" class="easyui-textbox"   label="组长单位主要研究者:" style="width:100%" labelPosition="top"/></td>
                </tr>

                <tr style="display:inline-block;">
                    <td  style="width:150px;"><input id="bzxcdjy" name="bzxcdjy" class="easyui-textbox"   label="本中心承担专业:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="bzxcdjyyjy" name="bzxcdjyyjy" class="easyui-textbox"   label="本中心承担专业研究者:" style="width:99%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="jhls" name="jhls" class="easyui-textbox"   label="计划完成总例数:" style="width:100%" labelPosition="top"/></td>
                </tr>

                <tr style="display:inline-block;">
                    <td  style="width:150px;"><input id="yyls" name="yyls" class="easyui-textbox"   label="本中心计划完成例数:" style="width:100%" labelPosition="top"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="zgks" name="zgksmc" class="easyui-textbox"   label="主管科室:" style="width:100%" labelPosition="top" readonly="readonly"/></td>
                    <td  style="width:150px;padding-left:5px"><input id="zgys" name="zgysxm" class="easyui-textbox"   label="主管医生:" style="width:100%" labelPosition="top" readonly="readonly"/></td>
                </tr>

            </table>
        </form>

        <!--阶段表格-->
        <div style="margin-top:10px;width:100%;">
            <!--rownumbers:true,-->
            <table id="t_jd" class="easyui-datagrid"
                   style="width:100%;height:auto;max-height: 208px"
                   data-options="pagination:false,fitColumns:true,singleSelect:true" toolbar="#jd_toolbar">
            </table>
        </div>



    </div>

    <div data-options="region:'center'" style="width:100%;padding:0">

        <!--项目信息 表格-->
        <div style="width:100%;position:absolute;top:0px;bottom:0;left:0;">
            <table id="t_kyxm" title="科研项目信息" class="easyui-datagrid"
                   style="width:100%;height:100%;"
                   data-options="pagination:false,fitColumns:true,singleSelect:true">
            </table>
        </div>
    </div>
</div>

<!--添加 项目 对话框-->
<div id="new_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#new_dlg-buttons" data-options="draggable:true,modal:true">
    <form id="new_fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <div style="margin-bottom:10px">
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">科研单位:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="new_kydwmc" name="new_kydwmc" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                </select>
            </span>
        </div>
        <div style="margin-bottom:10px">
            <input name="new_xmmc" class="easyui-textbox" label="项目名称:" style="width:100%"/>
        </div>
        <div style="margin-bottom:10px">
            <!--<input name="new_xmlx" class="easyui-textbox" required="true" label="项目类型:" style="width:100%">-->
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">项目类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="new_xmlx" name="new_xmlx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">药物临床试验</option>
                    <option value="2">医疗器械临床试验</option>
                    <option value="3">诊断试剂临床试验</option>
                    <option value="4">其他</option>
                </select>
            </span>
        </div>
        <div style="margin-bottom:10px">
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">报销类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="new_fylx" name="fylx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">全部报销</option>
                    <option value="2">部分报销</option>
                    <option value="3">手工报销</option>
                </select>
            </span>
        </div>

        <div style="margin-bottom:10px">
            <!--<input name="yxrq" class="easyui-textbox" required="true" label="立项日期:" style="width:100%">-->
            <input name="new_yxrq" class="easyui-datebox" label="立项日期:" labelPosition="left" style="width:100%;"/>
        </div>

    </form>
</div>

<div id="new_dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNew()"
       style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="closeDlg('new_dlg')" style="width:90px">取消</a>
</div>
<!--编辑 项目 对话框-->
<div id="edit_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#edit_dlg-buttons" data-options="draggable:true,modal:true">
    <form id="edit_fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <div style="margin-bottom:10px">
            <input name="xmmc" class="easyui-textbox" label="项目名称:" style="width:100%"/>
        </div>
        <div style="margin-bottom:10px">
            <!--<input name="kydwmc" class="easyui-textbox" required="true" label="科研单位:" style="width:100%">-->
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">科研单位:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="edit_kydwmc" name="edit_kydwmc" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                </select>
            </span>
        </div>
        <div style="margin-bottom:10px">
            <!--<input name="xmlx" class="easyui-textbox" required="true" label="项目类型:" style="width:100%">-->
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">项目类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="edit_xmlx" name="xmlx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">药物临床试验</option>
                    <option value="2">医疗器械临床试验</option>
                    <option value="3">诊断试剂临床试验</option>
                    <option value="4">其他</option>
                </select>
            </span>
        </div>
        <div style="margin-bottom:10px">
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">报销类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="edit_fylx" name="fylx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">全部报销</option>
                    <option value="2">部分报销</option>
                    <option value="3">手工报销</option>
                </select>
            </span>
        </div>
        <!--<div style="margin-bottom:10px">-->
        <!--<label style="display:inline-block;width:80px;text-align: left;height: 24px;line-height: 24px">项目状态:</label>-->
        <!--<span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">-->
        <!--<select id="edit_zt" name="zt" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">-->
        <!--<option value="1">申请</option>-->
        <!--<option value="2">已审批</option>-->
        <!--<option value="3">已作废</option>-->
        <!--</select>-->
        <!--</span>-->
        <!--</div>-->
        <div style="margin-bottom:10px">
            <!--<input name="yxrq" class="easyui-textbox" required="true" label="立项日期:" style="width:100%">-->
            <input name="yxrq" class="easyui-datebox" label="立项日期:" labelPosition="left" style="width:100%;"/>
        </div>

    </form>
</div>

<div id="edit_dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()"
       style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="closeDlg('edit_dlg')" style="width:90px">取消</a>
</div>

<!--阶段 增 删 改 按钮-->
<div id="jd_toolbar">
    <span style="display:inline-block;margin-right: 10px;margin-left:1px;color:#0E2D5F;font-size:12px;font-weight:bold;">科研项目阶段</span>
    <a id="addStageBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addStage()">新增</a>
    <a id="editStageBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="editStage()">修改</a>
    <a id="delStageBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="deleteStage()">删除</a>
</div>

<!--新增 阶段-->
<div id="newjd_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#newjd_dlg-buttons" data-options="draggable:true,modal:true">
    <form id="newjd_fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <div style="margin-bottom:10px">
            <input id="jd_xmbm" name="jd_xmbm" class="easyui-textbox" label="项目编码:" style="width:100%" readonly="readonly"/>
        </div>
        <div style="margin-bottom:10px">
            <input name="jdmc" class="easyui-textbox" label="阶段名称:" style="width:100%"/>
        </div>
        <div style="margin-bottom:10px">
            <!--<input name="kydwmc" class="easyui-textbox" required="true" label="科研单位:" style="width:100%">-->
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">阶段类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="new_jdlx" name="new_jdlx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">筛选阶段</option>
                    <option value="5">导入阶段</option>
                    <option value="2">治疗阶段</option>
                    <option value="3">电话访视</option>
                    <option value="4">随访阶段</option>
                    <option value="6">结束阶段</option>
                </select>
            </span>
        </div>

    </form>
</div>

<div id="newjd_dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveJd()"
       style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="closeDlg('newjd_dlg')" style="width:90px">取消</a>
</div>

<!--编辑 阶段-->
<div id="editjd_dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#editjd_dlg-buttons" data-options="draggable:true,modal:true">
    <form id="editjd_fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <div style="margin-bottom:10px">
            <input id="editjd_xmbm" name="xmbm" class="easyui-textbox" label="项目编码:" style="width:100%" readonly="readonly"/>
        </div>
        <div style="margin-bottom:10px">
            <input id="edit_xh" name="xh" class="easyui-textbox" label="序号:" style="width:100%" readonly="readonly"/>
        </div>
        <div style="margin-bottom:10px">
            <input id="edit_jdmc" name="jdmc" class="easyui-textbox" label="阶段名称:" style="width:100%"/>
        </div>
        <div style="margin-bottom:10px">
            <!--<input name="kydwmc" class="easyui-textbox" required="true" label="科研单位:" style="width:100%">-->
            <label style="display:inline-block;width:79px;text-align: left;height: 24px;line-height: 24px">阶段类型:</label>
            <span style="height:30px;width:201px;position: relative; background-color: #fff;vertical-align: middle;display: inline-block;overflow: hidden;white-space: nowrap;margin: 0;padding: 0;border-radius: 5px 5px 5px 5px;">
                <select id="edit_jdlx" name="jdlx" style="width: 201px; margin: 0; padding:0 4px;height: 24px; line-height: 24px;border:1px solid #95B8E7;border-radius: 5px;outline:none;font-family: Arial;font-size: 12px">
                    <option value="1">筛选阶段</option>
                    <option value="5">导入阶段</option>
                    <option value="2">治疗阶段</option>
                    <option value="3">电话访视</option>
                    <option value="4">随访阶段</option>
                    <option value="6">结束阶段</option>
                </select>
            </span>
        </div>

    </form>
</div>

<div id="editjd_dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEditJd()"
       style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="closeDlg('editjd_dlg')" style="width:90px">取消</a>
</div>

<!--人员维护-->
<div id="winSelRy" class="easyui-dialog" title="参与人员"
     data-options="modal:true,buttons:'#winSelRyBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:500px;padding:10px;">

    <table id="gridYXRY" class="easyui-datagrid" title="已选参与人员" style="width:100%;height:180px"
           data-options="singleSelect:true,collapsible:false,
            onDblClickRow:function(rowIndex, rowData){
                //alert(rowData.czydm);
                for(var i = 0; i < selRyData.length; i++)
                {
                    if (selRyData[i].czydm == rowData.czydm)
                    {
                        selRyData.splice(i,1);
                        $('#gridYXRY').datagrid({
                            data:selRyData
                        });
                        break;
                    }
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:180,align:'left'">部门名称</th>
            <th data-options="field:'czydm',width:80,align:'center'">工号</th>
            <th data-options="field:'czyxm',width:80,align:'center'">姓名</th>
            <th data-options="field:'lxdh',width:100,align:'center'">联系电话</th>
        </tr>
        </thead>
    </table>

    <div style="margin-top:10px;"></div>

    <input id="txtSearchRy" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchRy,prompt:'请输入工号、姓名或拼音码查找人员'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridDXRY" class="easyui-datagrid" title="待选人员" style="width:100%; height:180px; "
           data-options="singleSelect:true,collapsible:false,
            onDblClickRow:function(rowIndex, rowData){
                //alert(rowData.czydm);
                try{
                    var bFind = false;
                    for(var i = 0; i < selRyData.length; i++)
                    {
                        if (selRyData[i].czydm == rowData.czydm)
                        {
                            bFind = true;
                            break;
                        }
                    }
                    if (!bFind)
                    {
                        selRyData.push(rowData);
                        $('#gridYXRY').datagrid({
                            data:selRyData
                        });
                    }
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:180,align:'left'">部门名称</th>
            <th data-options="field:'czydm',width:80,align:'center'">工号</th>
            <th data-options="field:'czyxm',width:80,align:'center'">姓名</th>
            <th data-options="field:'lxdh',width:100,align:'center'">联系电话</th>
        </tr>
        </thead>
    </table>

</div>

<div id="winSelRyBtn">
    <a id="btnSaveRy" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="SaveRy()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winSelRy').window('close');">取消</a>
</div>


<!--科室维护-->
<div id="winSelKs" class="easyui-dialog" title="参与科室"
     data-options="modal:true,buttons:'#winSelKsBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:500px;padding:10px;">

    <table id="gridYXKS" class="easyui-datagrid" title="已选参与科室" style="width:100%;height:180px"
           data-options="singleSelect:true,collapsible:false,
            onDblClickRow:function(rowIndex, rowData){
                //alert(rowData.bmbm);
                for(var i = 0; i < selKsData.length; i++)
                {
                    if (selKsData[i].bmbm == rowData.bmbm)
                    {
                        selKsData.splice(i,1);
                        $('#gridYXKS').datagrid({
                            data:selKsData
                        });
                        break;
                    }
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:360,align:'left'">部门名称</th>
            <th data-options="field:'bmbm',width:80,align:'center'">部门编码</th>
        </tr>
        </thead>
    </table>

    <div style="margin-top:10px;"></div>


    <input id="txtSearchKS" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchKs,prompt:'请输入科室名或拼音码查找科室'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridDXKS" class="easyui-datagrid" title="待选科室" style="width:100%; height:180px; "
           data-options="singleSelect:true,collapsible:false,
            onDblClickRow:function(rowIndex, rowData){
                try{
                    var bFind = false;
                    for(var i = 0; i < selKsData.length; i++)
                    {
                        if (selKsData[i].bmbm == rowData.bmbm)
                        {
                            bFind = true;
                            break;
                        }
                    }
                    if (!bFind)
                    {
                        selKsData.push(rowData);
                        $('#gridYXKS').datagrid({
                            data:selKsData
                        });
                    }
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:360,align:'left'">部门名称</th>
            <th data-options="field:'bmbm',width:80,align:'center'">部门编码</th>
        </tr>
        </thead>
    </table>

</div>
<div id="winSelKsBtn">
    <a id="btnSaveKs" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="SaveKs()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winSelKs').window('close');">取消</a>
</div>

<!--医嘱维护-->
<div id="winSelYz" class="easyui-dialog" title="阶段医嘱"
     data-options="modal:true,buttons:'#winSelYzBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:700px;height:500px;padding:10px;">

    <table id="gridYXYZ" class="easyui-datagrid" style="width:100%;height:405px"
           data-options="singleSelect:true,collapsible:false,
            onDblClickRow:function(rowIndex, rowData){
                //alert(rowData.bmbm);
                /*
                if (window.confirm('确认要删除本条医嘱？'))
                {
                    for(var i = 0; i < selYzData.length; i++)
                    {
                        if (selYzData[i].dm == rowData.dm)
                        {
                            selYzData.splice(i,1);
                            $('#gridYXYZ').datagrid({
                                data:selYzData
                            });
                            break;
                        }
                    }
                }
                */
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'yzlb',width:80,align:'center'">医嘱分类</th>
            <th data-options="field:'yzdm',width:80,align:'center'">医嘱代码</th>
            <th data-options="field:'yzmc',width:200,align:'left'">医嘱名称</th>
            <th data-options="field:'zxksmc',width:90,align:'center'">执行科室</th>
            <th data-options="field:'bzxmmc',width:40,align:'center'">必做</th>
            <th data-options="field:'kybzmc',width:40,align:'center'">可用</th>
            <th data-options="field:'jzfsmc',width:50,align:'center'">记账方式</th>
            <th data-options="field:'yzje',width:60,align:'center'">医嘱金额</th>
            <th data-options="field:'xh',width:40,align:'center'">序号</th>
        </tr>
        </thead>
    </table>

</div>
<div id="winSelYzBtn">
    <a id="btnNewYz" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="NewYz(0)">新增</a>
    <a id="btnEditYz" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="NewYz(1)">修改</a>
    <a id="btnDelYz" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="NewYz(2)">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winSelYz').window('close');">取消</a>
</div>



<div id="winAddYz" class="easyui-dialog" title="添加医嘱"
     data-options="modal:true,buttons:'#winAddYzBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:500px;padding:10px;">

    <form id="formAddYz">

        <div style="margin-top:10px;"></div>
        <div style="display:inline-block;margin-top:10px">
            序　　号：<input id="ctrl_XH" class="easyui-numberspinner" value="1" data-options="increment:1,min:0,max:10000" style="width:80px;" />
            必做项目：<input id="ctrl_BZXM" type="checkbox" value="1" />&nbsp;
            可用标志：<input id="ctrl_KYBZ" type="checkbox" value="1" />&nbsp;
            记账方式：<input id="ctrl_JZFS" type="checkbox" value="1"/>&nbsp;
        </div>

        <div style="display:inline-block;margin-top:10px">
            医　　嘱：<input id="ctrl_YZMC" type="text" style="width:120px;" readonly />
            <input id="ctrl_YZLB" type="text" style="width:60px;" readonly/>
            <a href="javascript:$('#winAddYz_yz').window('open').window('center');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择医嘱</a>
        </div>

        <div style="display:inline-block;margin-top:10px">
            执行科室：<input id="ctrl_ZXKSMC" type="text"  style="width:120px;" readonly/>
            <input id="ctrl_ZXKS" type="text" style="width:60px;" readonly/>
            <a href="javascript:$('#winAddYz_ks').window('open').window('center');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择科室</a>
        </div>



    </form>
</div>
<div id="winAddYzBtn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="SaveYz()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winAddYz').window('close'); $('#formAddYz').form('clear');">取消</a>
</div>



<div id="winAddYz_yz" class="easyui-dialog" title="选择医嘱"
     data-options="modal:true,buttons:'#winAddYzYzBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:400px;padding:10px;">

    <input id="txtAddSearchYZ" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchAddYz,prompt:'请输入医嘱名或拼音码查找医嘱'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridAddDXYZ" class="easyui-datagrid" title="待选医嘱" style="width:100%; height:270px; "
           data-options="singleSelect:true,collapsible:false,
                onDblClickRow:function(rowIndex, rowData){
                try{
                    //序号、执行科室、必做项目、可用标志、剂量、用法109、用法说明、频度sys_pd_sypd、天数、总数量
                    //id,gyid,xh,yzlb,yzdm,yzmc,zxks,bzxm,kybz,jl,yf,yfsm,pd,ts,zsl
                    winAddYz_YzConfirm();
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'yzfl',width:80,align:'center'">医嘱分类</th>
            <th data-options="field:'dmmc',width:360,align:'left'">医嘱名称</th>
            <th data-options="field:'dm',width:360,align:'left'">医嘱代码</th>
            <th data-options="field:'yzje',width:360,align:'left'">医嘱金额</th>
        </tr>
        </thead>
    </table>
</div>
<div id="winAddYzYzBtn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="winAddYz_YzConfirm()">确定</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winAddYz_yz').window('close');">取消</a>
</div>




<div id="winAddYz_ks" class="easyui-dialog" title="选择科室"
     data-options="modal:true,buttons:'#winAddYzKsBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:400px;padding:10px;">

    <input id="txtAddSearchKS" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchAddKs,prompt:'请输入科室名或拼音码查找科室'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridAddDXKS" class="easyui-datagrid" title="待选科室" style="width:100%; height:270px; "
           data-options="singleSelect:true,collapsible:false,
                onDblClickRow:function(rowIndex, rowData){
                try{
                    winAddYz_KsConfirm();
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:360,align:'left'">部门名称</th>
            <th data-options="field:'bmbm',width:80,align:'center'">部门编码</th>
        </tr>
        </thead>
    </table>
</div>
<div id="winAddYzKsBtn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="winAddYz_KsConfirm()">确定</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winAddYz_ks').window('close');">取消</a>
</div>




<div id="winAddYz_yf" class="easyui-dialog" title="选择用法"
     data-options="modal:true,buttons:'#winAddYzYfBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:400px;padding:10px;">

    <input id="txtAddSearchYF" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchAddYf,prompt:'请输入用法名或代码查找用法'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridAddDXYF" class="easyui-datagrid" title="待选用法" style="width:100%; height:270px; "
           data-options="singleSelect:true,collapsible:false,
                onDblClickRow:function(rowIndex, rowData){
                try{
                    winAddYz_YfConfirm();
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'uname',width:360,align:'left'">用法名称</th>
            <th data-options="field:'dm',width:80,align:'center'">用法编码</th>
        </tr>
        </thead>
    </table>
</div>
<div id="winAddYzYfBtn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="winAddYz_YfConfirm()">确定</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winAddYz_yf').window('close');">取消</a>
</div>


<div id="winAddYz_pd" class="easyui-dialog" title="选择频度"
     data-options="modal:true,buttons:'#winAddYzPdBtn',closed:true,resizable:true,iconCls:'icon-save'"
     style=" width:500px;height:400px;padding:10px;">

    <input id="txtAddSearchPD" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:SearchAddPd,prompt:'请输入频度名称、代码或拉丁文查找用法'"/>

    <div style="margin-top:10px;"></div>

    <table id="gridAddDXPD" class="easyui-datagrid" title="待选频度" style="width:100%; height:270px; "
           data-options="singleSelect:true,collapsible:false,
                onDblClickRow:function(rowIndex, rowData){
                try{
                    winAddYz_PdConfirm();
                }
                catch(e){
                    alert('err:' + e.description);
                }
            }
            ">
        <thead>
        <tr>
            <th data-options="field:'dmmc',width:120,align:'left'">频度名称</th>
            <th data-options="field:'ldw',width:80,align:'center'">频度拉丁文</th>
            <th data-options="field:'dm',width:80,align:'center'">频度编码</th>
        </tr>
        </thead>
    </table>
</div>
<div id="winAddYzPdBtn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="winAddYz_PdConfirm()">确定</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winAddYz_pd').window('close');">取消</a>
</div>

<!--详情 主管科室:-->
<div id="xq_selKs" class="easyui-dialog" title="科室"
     data-options="modal:true,closed:true,resizable:true"
     style=" width:500px;height:300px;padding:10px;">

    <input id="xq_txtSearchKS" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:xqSearchKs,prompt:'请输入科室名或拼音码查找科室'"/>

    <div style="margin-top:10px;"></div>

    <!--//双击某一行选择;设置值，关闭窗口-->
    <!--$("#bzxcdjy").textbox({"setValue":rowData.bzxcdjy}); //setText -->
    <!--//$("#f_kyxmxq").form("load",{bzxcdjy:rowData.bzxcdjy});-->
    <!---->
    <table id="xq_gridDXKS" class="easyui-datagrid" title="" style="width:100%; height:210px; "
           data-options="singleSelect:true,collapsible:false">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:360,align:'left'">部门名称</th>
            <th data-options="field:'bmbm',width:80,align:'center'">部门编码</th>
        </tr>
        </thead>
    </table>

</div>
<!--<div id="xq_winSelKsBtn">-->
<!--<a id="xq_btnSaveKs" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:false" onclick="SaveKs()">保存</a>-->
<!--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#winSelKs').window('close');">取消</a>-->
<!--</div>-->

<!--详情 主管医生:-->
<div id="xq_selRy" class="easyui-dialog" title="人员"
     data-options="modal:true,closed:true,resizable:true"
     style=" width:500px;height:300px;padding:10px;">

    <input id="xq_txtSearchRY" class="easyui-searchbox" style="width:100%;"
           data-options="searcher:xqSearchRy,prompt:'请输入工号、姓名或拼音码查找人员'"/>

    <div style="margin-top:10px;"></div>

    <!--//双击某一行选择;设置值，关闭窗口-->
    <!--$("#bzxcdjy").textbox({"setValue":rowData.bzxcdjy}); //setText -->
    <!--//$("#f_kyxmxq").form("load",{bzxcdjy:rowData.bzxcdjy});-->
    <!--  -->
    <table id="xq_gridDXRY" class="easyui-datagrid" title="" style="width:100%; height:210px; "
           data-options="singleSelect:true,collapsible:false">
        <thead>
        <tr>
            <th data-options="field:'bmmc',width:180,align:'left'">部门名称</th>
            <th data-options="field:'czydm',width:80,align:'center'">工号</th>
            <th data-options="field:'czyxm',width:80,align:'center'">姓名</th>
            <th data-options="field:'lxdh',width:100,align:'center'">联系电话</th>
        </tr>
        </thead>
    </table>

</div>

<script>
    var param = {};
    var editJdParam = {};



    $(function () {
        //隐藏审批按钮
        // $("#sp_Button").css('display','none');
        //显示审批按钮
        // $("#sp_Button").css('display','inline-block');

        $("#xmmc").textbox('textbox').bind('click',function(){
            this.value = '';
        });

        $("#xmmc").textbox("textbox").bind('keyup',function (event) {
            if (event.which == 13) {
                find();
            }
        });

        // $("#ywmc").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#syz").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // // $("#scxs").textbox('textbox').bind('click',function(){
        // //     this.value = '';
        // // });
        //
        // $("#sbz").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#sbzlxdh").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#cro").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#crolxdh").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#zzdw").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#zzdwyjy").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        //点击输入框，弹出选择框
        // $("#bzxcdjy").textbox('textbox').bind('click',function(){
        //
        // });
        // //
        // $("#bzxcdjyyjy").textbox('textbox').bind('click',function(){
        //
        // });
        //
        // $("#jhls").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        //
        // $("#yyls").textbox('textbox').bind('click',function(){
        //     this.value = '';
        // });
        $("#zgks").textbox('textbox').bind('click',function(){
            if(!$("input[name='xq_xmbm']").val()){
                return;
            }
            xqSelKs();
        });
        //
        $("#zgys").textbox('textbox').bind('click',function(){
            if(!$("input[name='xq_xmbm']").val()){
                return;
            }
            xqSelRy();
        });
        //初始化 详情下拉框
        genOptions("ywjx","108");
        genOptions("zcfl","286");//注册分类变成输入框？
        genOptions("syfq","287");
        genOptions("lxlb","288");
        genOptions("scxs","289");
        $("#f_kyxmxq").form('clear');//清空详情下拉项
    });

    function spXm() {
        var xmbm = $("#xq_xmbm").val();
        // if(!xmbm){
        //     return;
        // }
        $.messager.confirm('提示', "确认审批? (审批后项目信息不可修改)", function (r) {
            if (r) {
                var param = {
                    a01:xmbm,
                    a02:2   //项目状态 2 :已审批
                };
                $.ajax({
                    type:'POST',
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    dataType: "json",
                    url:'project/updatestatus.do',
                    data:param,
                    success: function (data) {
                        if (data.state) {
                            kyxm_zt = '2';//审批后，标志改变
                            //隐藏审批按钮
                            $("#sp_Button").css('display','none');
                            //置灰保存按钮
                            $("#xq_SaveBtn").linkbutton('disable');

                            $("#addStageBtn").linkbutton('disable');
                            $("#editStageBtn").linkbutton('disable');
                            $("#delStageBtn").linkbutton('disable');



                            //修改状态后更新表格
                            refreshXmxx();
                            $.messager.alert({
                                title: '提示',
                                msg: "审批成功！"
                            });
                        } else {
                            $.messager.alert({
                                title: '提示',
                                msg: "审批失败！"
                            });
                        }
                    },
                    error: function (xhr, status, error) {
                        // $('#new_dlg').dialog('close');
                        $.messager.alert({
                            title: '提示',
                            msg: "审批请求失败：" + status
                        });
                    }

                })
            }
        });


    }

    // a01:108 药物剂型
    // a01:286 注册分类
    // a01:287 试验分期
    // a01:288 立项类别
    // a01:289 审查形式
    //DM,UNAME
    function genOptions(id,lxbm,selectText) {
        $("#" + id).html("");//先清空
        var showText = "";
        switch(lxbm){
            case "108":
                showText = "药物剂型";
                break;
            case "286":
                showText = "注册分类";
                break;
            case "287":
                showText = "试验分期";
                break;
            case "288":
                showText = "立项类别";
                break;
            case "289":
                showText = "审查形式";
                break;
        }
        var data = {
            a01: lxbm
        };
        $.ajax({
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "basetable/query.do",
            data: data, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows === "0"){
                    // $.messager.alert('提示', "尚无" + showText + "信息", "info");
                }else if (data && data.rows) {
                    for(var i=0;i<data.rows.length;i++){
                        if(selectText === data.rows[i].uname){
                            $("#" + id).append('<option selected="selected" value="' + data.rows[i].dm + '">'+ data.rows[i].uname + '</option>');
                        }else{
                            $("#" + id).append('<option value="' + data.rows[i].dm + '">'+ data.rows[i].uname + '</option>');

                        }
                    }
                } else {
                    $.messager.alert('提示', showText + "信息异常", "error");
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('提示', showText + "信息加载失败", "error");
            }
        })
    }


    function findJd(xmbm) {
        var param = {"a01":xmbm};
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "stage/query.do",
            data: param, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){
                    $('#t_jd').datagrid('loadData',{rows:[]});
                }else if (data && data.rows) {
                    $('#t_jd').datagrid('loadData', data);//Load local data, the old rows will be removed.
                } else {

                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象

            }
        });
    }

    function saveJd() {
        var param = {};
        param.a01 = $("#jd_xmbm").val();
        param.a02 = $("#new_jdlx").val();
        param.a03 = ($("#newjd_fm input[name='jdmc']").val()).trim(); //newjd_fm


        if(!param.a01 || !param.a02 || !param.a03) return;
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "stage/add.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $('#newjd_dlg').dialog('close');
                    findJd(param.a01);//刷新阶段表格
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


    function findXq(xmbm) {
        var param = {"a01":xmbm};
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "projectdetail/query.do",
            data: param, //规定要发送到服务器的数据
            success: function (data) {
                if(!data){
                    $.messager.alert('提示', "加载项目详情失败!", "error");
                }else if (data && data.rows) {
                    $('#f_kyxmxq').form('load', data.rows[0]);
                    zgks = data.rows[0].zgks;
                    zgys = data.rows[0].zgys;
                    //form load 能加载 select 的 value
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('提示', "请求项目详情失败!", "error");
            }
        })
    }

    function saveXmxq(){
        var param = {};
        param.a01 = ($("input[name='xq_xmbm']").val());
        param.a02 = ($("input[name='cfdah']").val()).trim();
        param.a03 = ($("input[name='ywmc']").val()).trim();
        param.a04 =  $("#ywjx").val(); //加载的时候，select要设置value
        param.a05 = ($("input[name='syz']").val()).trim();
        param.a06 = $("#zcfl").val();
        //param.a06 = ($("input[name='zcfl']").val()).trim();

        param.a07 = $("#syfq").val();
        param.a08 = $("#lxlb").val();
        param.a09 = $("#scxs").val();
        param.a10 = ($("input[name='sbz']").val()).trim();
        param.a11 = ($("input[name='sbzlxdh']").val()).trim();
        param.a12 = ($("input[name='cro']").val()).trim();

        param.a13 = ($("input[name='crolxdh']").val()).trim();
        param.a14 = ($("input[name='zzdw']").val()).trim();
        param.a15 = ($("input[name='zzdwyjy']").val()).trim();
        param.a16 = ($("input[name='bzxcdjy']").val()).trim();
        param.a17 = ($("input[name='bzxcdjyyjy']").val()).trim();
        param.a18 = ($("input[name='jhls']").val()).trim();
        param.a19 = ($("input[name='yyls']").val()).trim();
        param.a20 = zgys;//主管医生
        param.a21 = zgks;//主管科室
        //if(!(param.a01&&param.a02&&param.a03&&param.a04&&param.a05&&param.a06&&param.a07&&param.a08&&param.a09&&param.a10&&param.a11&&param.a12&&param.a13&&param.a14&&param.a15&&param.a16&&param.a17&&param.a18&&param.a19))
        //{
        //$.messager.alert('提示', "信息填写不完整", "info");
        //return;
        //}

        if(!param.a01){
            return;
        }
        //通过 置灰 保存按钮控制
        // if(kyxm_zt != "1"){
        //     switch (kyxm_zt){
        //         case '2':
        //             text = '已审批';
        //             $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //             return;
        //         // case '3':
        //         //     text = '已作废';
        //         //     $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //         //     return;
        //     }
        // }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "projectdetail/update.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $.messager.alert('提示', "保存成功！", "info");
                } else {
                    //成功返回数据，但是返回false ?
                    $.messager.alert({
                        title: '提示',
                        msg: "保存失败！"
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

    function clearXq(){
        var xmbm = $("#xq_xmbm").val();
        $("#f_kyxmxq").form('clear');
        // $("#f_kyxmxq input").val('');
        // if(xmbm != ""){
        //     //id 选择器才能修改显示值;需要保留项目编号？
        //     $("#xq_xmbm").textbox({"value":xmbm});
        // }
    }

    function addStage() {
        var xmbm = $("#xq_xmbm").val();
        if(!xmbm) return;//没有项目编码，点击新增阶段无效
        // if(kyxm_zt != "1"){
        //     switch (kyxm_zt){
        //         case '2':
        //             text = '已审批';
        //             $.messager.alert('提示', "项目" + text + ",不可新增阶段", "info");
        //             return;
        //         // case '3':
        //         //     text = '已作废';
        //         //     $.messager.alert('提示', "项目" + text + ",不可新增阶段", "info");
        //         //     return;
        //     }
        // }
        $('#newjd_fm').form('clear');
        // $("#jd_xmbm").val(xmbm);//无效
        $('#newjd_fm').form('load', {"jd_xmbm":xmbm,"new_jdlx":"1"});
        // $("#jd_xmbm").textbox({"value":xmbm});//input 框第二次塌陷
        // $("#new_jdlx").val("1");//为 阶段类型 下拉框 初始化一个值
        $('#newjd_dlg').dialog('open').dialog('center').dialog('setTitle', '新增阶段');

    }

    function editStage() {
        var row = $('#t_jd').datagrid('getSelected');//Return the first selected row record or null.
        if (row) {
            // if(kyxm_zt != "1"){
            //     switch (kyxm_zt){
            //         case '2':
            //             text = '已审批';
            //             $.messager.alert('提示', "项目" + text + ",不可修改阶段", "info");
            //             return;
            //         // case '3':
            //         //     text = '已作废';
            //         //     $.messager.alert('提示', "项目" + text + ",不可修改阶段", "info");
            //         //     return;
            //     }
            // }
            //row 会包含load到所有数据，即使field中没有
            editJdParam.a01 = row.id;  //阶段 id
            $('#editjd_fm').form('load', row);//对话框中的form加载选择row的内容
            // var xmbm = $("#xq_xmbm").val(); // row.xmbm ??
            // $("#editjd_xmbm").textbox({"value":xmbm});//项目编码可以 自动加载！
            // $("#edit_jdlx").val(row.jdlx);
            $('#editjd_dlg').dialog('open').dialog('center').dialog('setTitle', '修改阶段');
        }

    }

    function saveEditJd() {

        editJdParam.a02 =  $("#editjd_xmbm").val(); //项目编码
        // editJdParam.a03 = ($("#edit_xh").val()).trim(); //序号
        editJdParam.a03= $("#edit_jdlx").val(); //阶段类型 select
        editJdParam.a04= ($("#edit_jdmc").val()).trim();  //阶段名称
        if(!(editJdParam.a01&&editJdParam.a02&&editJdParam.a03&&editJdParam.a04)){
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "stage/update.do",
            data: editJdParam,
            success: function (result) {
                if (result.state) {
                    $('#editjd_dlg').dialog('close');
                    findJd(editJdParam.a02);
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

    function deleteStage() {
        var row = $('#t_jd').datagrid('getSelected');
        if (row) {
            // if(kyxm_zt != "1"){
            //     switch (kyxm_zt){
            //         case '2':
            //             text = '已审批';
            //             $.messager.alert('提示', "项目" + text + ",不可删除阶段", "info");
            //             return;
            //         // case '3':
            //         //     text = '已作废';
            //         //     $.messager.alert('提示', "项目" + text + ",不可删除阶段", "info");
            //         //     return;
            //     }
            // }
            var id = row.id;
            var xmbm = row.xmbm;
            $.messager.confirm('提示', "是否删除该阶段？", function (r) {
                if (r) {
                    //传递选中行数据
                    $.post('stage/delete.do', {a01: xmbm,a02:id}, function (result) {
                        if (result.state) {
                            $.messager.alert({
                                title: '提示',
                                msg: "删除成功！"
                            });
                            findJd(xmbm);   // reload the user data
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


    $("#t_jd").datagrid(
        {
            columns:[[
                //
                {
                    field:'id',
                    width:100,
                    hidden : 'true',
                    title:"ID"
                },
                {
                    field:'xmbm',
                    width:100,
                    title:"项目编码",
                    hidden : 'true'
                },
                {
                    field:'xh',
                    width:100,
                    title:"序号"
                },
                {
                    field:'jdmc',
                    width:100,
                    title:"阶段名称"
                },
                {
                    field:'jdlx',
                    width:100,
                    title:"阶段类型",
                    formatter: function(value,row,index){
                      //  <option value="1">筛选阶段</option>
                      //  <option value="5">导入阶段</option>
                      //  <option value="2">治疗阶段</option>
                      //  <option value="3">电话访视</option>
                      //  <option value="4">随访阶段</option>
                      //  <option value="6">结束阶段</option>
                        var text = "";
                        switch(row.jdlx){
                            case "1":
                                text = "筛选阶段";
                                break;
                            case "5":
                                text = "导入阶段";
                                break;
                            case "2":
                                text = "治疗阶段";
                                break;
                            case "3":
                                text = "电话访视";
                                break;
                            case "4":
                                text = "随访阶段";
                                break;
                            case "6":
                                text = "结束阶段";
                                break;
                        }
                        return text;//只改变显示,表格值不变
                    }
                } ,
                {
                    field:'yzwh',
                    width:100,
                    title:"医嘱维护",
                    formatter: function(value,row,index){
                        // 阶段名称 关联 医嘱？
                        var theRow = JSON.stringify(row).replace(/"/g,"'");
                        return '<a style="color:blue;text-decoration: none" onclick="SelYz(' + theRow + ')">医嘱维护</a>';
                    }
                }
            ]]
        }
    );


    var kyxm_zt;
    $('#t_kyxm').datagrid({
        //双击某行
        onDblClickRow: function(index,row){
            if(row){
                // $("#main").layout("expand","east");
                // $('#f_kyxmxq').form('load', row);
                var xmbm = row.xmbm;
                kyxm_zt = row.zt;//记录当前项目状态，判断是否可用编辑
                if(kyxm_zt == '1'){
                    //显示审批按钮
                    $("#sp_Button").css('display','inline-block');
                    //启用右侧按钮
                    $("#xq_SaveBtn").linkbutton('enable');
                    $("#addStageBtn").linkbutton('enable');
                    $("#editStageBtn").linkbutton('enable');
                    $("#delStageBtn").linkbutton('enable');
                }else{
                    //隐藏审批按钮
                    $("#sp_Button").css('display','none');
                    //置灰保存按钮
                    $("#xq_SaveBtn").linkbutton('disable');
                    $("#addStageBtn").linkbutton('disable');
                    $("#editStageBtn").linkbutton('disable');
                    $("#delStageBtn").linkbutton('disable');
                }
                $("#f_kyxmxq").form('clear');//清空详情

                $("#xq_xmbm").textbox({"value":xmbm});

                findXq(xmbm);
                findJd(xmbm);

            }
        },
        columns:[[
            {
                field:'xmbm',
                width:65,
                title:"项目编码"
            },
            {
                field:'xmmc',
                width:145,
                title:"项目名称"
            },
            {
                field:'kydwmc',
                width:150,
                title:"科研单位"
            },
            {
                field:'xmlx',
                width:100,
                title:"项目类型",
                formatter:function (value,row,index) {
                    switch(value){
                        case '1':
                            return '药物临床试验';
                            break;
                        case '2':
                            return '医疗器械临床试验';
                            break;
                        case '3':
                            return '诊断试剂临床试验';
                            break;
                        case '4':
                            return '其他';
                            break;
                        default:
                            return '';
                    }
                }
            },
            {
                field:'fylx',
                title:"报销类型",
                width:70,
                formatter:function (value,row,index) {
                    switch (value){
                        case "1":
                            return "全部报销";
                            break;
                        case "2":
                            return "部分报销";
                            break;
                        case "3":
                            return "手工报销";
                            break;
                        default:
                            return "";
                    }
                }
            },
            {
                field:'zt',
                width:65,
                title:"项目状态",
                formatter: function(value,row,index){
                    switch (value){
                        case '1':
                            return '申请';
                        case '2':
                            return '已审批';
                        default:
                            return '';
                    }
                }
            },
            {
                field:'yxrq',
                width:90,
                title:"立项日期"
            },
            {
                field:'zbrq',
                width:130,
                title:"制表日期"
            },
            {
                field:'cyks',
                width:70,
                title:"参与科室",
                formatter: function(value,row,index){
                    var theRow = JSON.stringify(row).replace(/"/g,"'");
                    return '<a style="color:blue;text-decoration: none" onclick="SelKs(' + theRow + ')">科室维护</a>';
                }
            },
            {
                field:'cyry',
                width:70,
                title:"参与人员",
                formatter: function(value,row,index){
                    var theRow = JSON.stringify(row).replace(/"/g,"'");
                    return '<a style="color:blue;text-decoration: none" onclick="SelRy(' + theRow + ')">人员维护</a>';
                }
            }
        ]]
    });

    //科研单位 下拉项
    function genKydwmc(id,selectedText) {
        $("#" + id).html("");//先清空
        var data = {
            a01: "",
            a02: "",
            a03: "1"  // 1:可用
        };
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "company/query.do",
            data: data, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows === "0"){
                    // $.messager.alert('提示', "尚无科研单位", "info");
                }else if (data && data.rows) {
                    for(var i=0;i<data.rows.length;i++){
                        if(selectedText === data.rows[i].dwmc){
                            $("#" + id).append('<option selected="selected" value="' + data.rows[i].dwbm + '">'+ data.rows[i].dwmc + '</option>');
                        }else{
                            $("#" + id).append('<option value="' + data.rows[i].dwbm + '">'+ data.rows[i].dwmc + '</option>');

                        }
                    }
                } else {
                    $.messager.alert('提示', "科研单位信息异常", "error");
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('提示', "科研单位信息加载失败", "error");
            }
        })
    }

    //新增 科研项目
    function saveNew() {
        var param = {};
        param.a01 = ($("input[name='new_xmmc']").val()).trim();//项目名称
        param.a02 = $("#new_kydwmc").val();//科研单位 编码
        param.a03 = $("#new_xmlx").val();//项目类型
        param.a04 = ($("input[name='new_yxrq']").val()).trim();//立项日期
        param.a05 = $("#new_fylx").val();//fylx 报销类型
        if(!param.a01 || !param.a02 || !param.a03 || !param.a04) return;
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "project/add.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $('#new_dlg').dialog('close');
                    refreshXmxx();
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

    function refreshXmxx() {
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "project/query.do",
            data: lastCondition, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows == "0"){

                }else if (data && data.rows) {
                    $('#t_kyxm').datagrid('loadData', data);//Load local data, the old rows will be removed.
                    //清空右侧加载信息
                    $("#f_kyxmxq").form('clear');
                    $('#t_jd').datagrid('loadData',{rows:[]});
                    //隐藏审批按钮
                    $("#sp_Button").css('display','none');
                    //修改 项目状态 标志？
                    kyxm_zt = '1';//右侧无内容，也可以不用管？
                    //启用右侧按钮？
                    $("#xq_SaveBtn").linkbutton('enable');
                    $("#addStageBtn").linkbutton('enable');
                    $("#editStageBtn").linkbutton('enable');
                    $("#delStageBtn").linkbutton('enable');
                } else {

                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象

            }
        })

    }

    //查找之后，清空详情和阶段表格！！！！！！！！！！！！！！！！！！！！！！！！
    // $("#f_kyxmxq").form('clear');
    // $('#t_jd').datagrid('loadData',{rows:[]});

    var lastCondition = {a01:'',a02:'all'};

    //查询
    function find() {
        var xmmc = ($("#xmmc").val()).trim();
        var zt = $("#zt").val();

        var data = {
            a01: xmmc,
            a02: zt
        };
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "project/query.do",
            data: data, //规定要发送到服务器的数据
            success: function (data) {
                if(data.rows === "0"){
                    // $.messager.alert('提示', "没有查询到符合条件的项目", "info");
                }else if (data && data.rows) {
                    $('#t_kyxm').datagrid('loadData', data);//Load local data, the old rows will be removed.
                    lastCondition.a01 = xmmc;
                    lastCondition.a02 = zt;
                    //清空右侧加载信息
                    $("#f_kyxmxq").form('clear');
                    $('#t_jd').datagrid('loadData',{rows:[]});
                    //隐藏审批按钮
                    $("#sp_Button").css('display','none');
                    //修改 项目状态 标志？
                    kyxm_zt = '1';//右侧无内容，也可以不用管？
                    //启用右侧按钮？
                    $("#xq_SaveBtn").linkbutton('enable');
                    $("#addStageBtn").linkbutton('enable');
                    $("#editStageBtn").linkbutton('enable');
                    $("#delStageBtn").linkbutton('enable');
                } else {
                    $.messager.alert('Error', "返回数据格式有误！", "error");
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                $.messager.alert('Error', "查询数据失败！", "error");
            }
        })

    }

    function deleteXm() {
        var row = $('#t_kyxm').datagrid('getSelected');
        if (row) {
            var text;
            switch (row.zt){
                // case '1':
                //     text =  '申请';
                //     break;
                case '2':
                    text = '已审批';
                    $.messager.alert('提示', "项目" + text + ",不可删除", "info");
                    return;
                // case '3':
                //     text = '已作废';
                //     $.messager.alert('提示', "项目" + text + ",不可删除", "info");
                //     return;
            }
            var xmbm = row.xmbm;
            $.messager.confirm('提示', "是否删除该项目？", function (r) {
                if (r) {
                    //传递选中行数据
                    $.post('project/delete.do', {a01: xmbm}, function (result) {
                        if (result.state) {
                            $.messager.alert({
                                title: '提示',
                                msg: "删除成功！"
                            });
                            refreshXmxx();   // reload the user data
                            var xq_xmbm = $("#xq_xmbm").val();
                            if(xq_xmbm === xmbm){
                                //当前删除的项目是 右侧加载的
                                $("#f_kyxmxq").form('clear');
                                $('#t_jd').datagrid('loadData',{rows:[]});
                            }
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

    function newXm() {
        $('#new_fm').form('clear');
        // $('#new_fm input').val('');
        $('#new_fm').form('load', {"fylx":"1","new_xmlx":"1"});//为下拉框赋默认值
        genKydwmc("new_kydwmc");
        $('#new_dlg').dialog('open').dialog('center').dialog('setTitle', '添加');
        // $("#new_zt").val("0");//初始化状态为 新建
        //点保存saveUser()的之后才会从fm的form提交到数据库
    }

    //修改
    function editXm() {
        var row = $('#t_kyxm').datagrid('getSelected');//Return the first selected row record or null.
        if (row) {
            var text ;
            switch (row.zt){
                // case '1':
                //     text =  '申请';
                //     break;
                case '2':
                    text = '已审批';
                    $.messager.alert('提示', "项目" + text + ",不可修改", "info");
                    return;
                // case '3':
                //     text = '已作废';
                //     $.messager.alert('提示', "项目" + text + ",不可修改", "info");
                //     return;
            }
            param.a01 = row.xmbm;//修改的时候，保存原来的项目编码
            genKydwmc("edit_kydwmc",row.kydwmc);//append延迟添加了，所以选不到
            $('#edit_dlg').dialog('open').dialog('center').dialog('setTitle', '修改');
            $('#edit_fm').form('load', row);//对话框中的form加载选择row的内容
        }
    }


    function saveEdit() {
        param.a02 = ($("input[name='xmmc']").val()).trim();
        param.a03 = $("#edit_kydwmc").val();//单位名称？？？
        param.a04 = $("#edit_xmlx").val();
        param.a05 = ($("input[name='yxrq']").val()).trim();
        param.a06 = $("#edit_fylx").val();//fylx 报销类型
        // param.a07 = $("#edit_zt").val();//项目状态不能直接编辑
        if(!(param.a01&&param.a02&&param.a03&&param.a04&&param.a05)){
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "project/update.do",
            data: param,
            success: function (result) {
                if (result.state) {
                    $('#edit_dlg').dialog('close');
                    refreshXmxx();
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

    // 人员 科室
    var iGyid = ""; //当前选择的项目ID

    var iJdid = ""; //阶段ID

    var allRyData = [];
    var selRyData = [];

    var allKsData = [];
    var selKsData = [];
    var allYzData = [];
    var selYzData = [];

    var allYfData = [];
    var allPdData = [];

    function getData(strType) {
        //$.messager.progress();
        var strUrl = "";
        switch(strType)
        {
            case "selRy": //选择的人员
                selRyData = [];
                strUrl = "inworkers/query.do";
                break;
            case "allRy": //所有人员
                if (allRyData.length > 0)
                    return;
                //allRyData = [];
                strUrl = "workers/query.do";
                break;
            case "selKs": //选择的科室
                selKsData = [];
                strUrl = "indepartment/query.do";
                break;
            case "allKs": //所有科室
                if (allKsData.length > 0)
                    return;

                //allKsData = [];
                strUrl = "department/query.do";
                break;

            case "selYz": //选择的医嘱
                selYzData = [];
                strUrl = "stageadvice/query.do";
                break;
            case "allYz": //所有医嘱
                if (allYzData.length > 0)
                    return;

                strUrl = "doctoradvice/query.do";
                break;
            case "allYf": //用法
                if (allYfData.length > 0)
                    return;

                strUrl = "basetable/query.do";
                break;
            case "allPd": //频度
                if (allPdData.length > 0)
                    return;
                strUrl = "frequency/query.do";
                break;
        }

        var param = {};
        param.a01 = iGyid;
        if (strType == "selYz")
            param.a01 = iJdid;
        else if (strType == "allYf")
            param.a01 = "109";

        $.ajax({
            type: "get",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            url: strUrl,
            data: param,
            success: function (data) {
                if (data && data.rows) {
                    switch(strType)
                    {
                        case "selRy": //选择的人员
                            selRyData = data.rows;
                            //alert("sel " + selRyData.length);

                            $('#gridYXRY').datagrid({
                                data:selRyData
                            });
                            //$('#gridYXRY').datagrid('loadData', selRyData);
                            break;
                        case "allRy": //所有人员
                            allRyData = data.rows;
                            //alert("all " + allRyData.length);

                            $('#gridDXRY').datagrid({
                                data:allRyData
                            });
                            //$('#gridYXRY').combogrid('loadData', allRyData);
                            break;

                        case "selKs": //选择的科室
                            selKsData = data.rows;
                            //alert("sel " + selKsData.length);

                            $('#gridYXKS').datagrid({
                                data:selKsData
                            });
                            break;
                        case "allKs": //所有科室
                            allKsData = data.rows;
                            //alert("all " + allKsData.length);

                            $('#gridDXKS').datagrid({
                                data:allKsData
                            });

                            $('#gridAddDXKS').datagrid({
                                data:allKsData
                            });
                            break;
                        case "selYz": //选择的医嘱
                            selYzData = data.rows;
                            try
                            {
                                for (var iSelYzData = 0; iSelYzData < selYzData.length; iSelYzData++)
                                {
                                    selYzData[iSelYzData].jzfsmc = selYzData[iSelYzData].jzfs == "1" ? "是" : "否";
                                    selYzData[iSelYzData].bzxmmc = selYzData[iSelYzData].bzxm == "1" ? "是" : "否";
                                    selYzData[iSelYzData].kybzmc = selYzData[iSelYzData].kybz == "1" ? "是" : "否";
                                }
                            }
                            catch (e)
                            {
                                alert(e.description);
                            }
                            $('#gridYXYZ').datagrid({
                                data:selYzData
                            });
                            break;
                        case "allYz": //所有医嘱
                            allYzData = data.rows;
                            $('#gridAddDXYZ').datagrid({
                                data:allYzData
                            });
                            break;
                        case "allYf": //用法
                            allYfData = data.rows;
                            $('#gridAddDXYF').datagrid({
                                data:allYfData
                            });
                            break;
                        case "allPd": //频度
                            allPdData = data.rows;
                            $('#gridAddDXPD').datagrid({
                                data:allPdData
                            });
                            break;
                    }
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                alert("err: " + error + " status:" + status);
            }
        });
    }

    function SelRy(row)
    {
        //if (allRyData.length == 0)
        iGyid = row.xmbm;
        getData("allRy");
        getData("selRy");
        if(row.zt == '1'){
            $("#btnSaveRy").linkbutton('enable');
        }else{
            $("#btnSaveRy").linkbutton('disable');
        }
        $('#winSelRy').window('open').window('center');
    }

    function SearchRy(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxryData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allRyData.length; i++)
                {
                    if (allRyData[i]['czydm'].indexOf(value) >= 0 || nullToEmpty(allRyData[i]['czyxm']).indexOf(value) >= 0 || nullToEmpty(allRyData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxryData.push(allRyData[i]);
                    }
                }
            }
            else
                filterDxryData = allRyData;

            $('#gridDXRY').datagrid({
                data:filterDxryData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }

    function SaveRy()
    {
        var param = {};
        param.a01 = iGyid;

        param.a02 = "";
        for(var i = 0; i < selRyData.length; i++)
            param.a02 += selRyData[i].czydm + ",";
        // alert(param.a02);
        if(!param.a01)
        {
            alert("未选择指定项目");
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "inworkers/add.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $('#winSelRy').dialog('close');
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

    function SelKs(row)
    {
        iGyid = row.xmbm;
        getData("allKs");
        getData("selKs");
        //按钮启用和禁用  btnSaveKs
        if(row.zt == '1'){
            $("#btnSaveKs").linkbutton('enable');
        }else{
            $("#btnSaveKs").linkbutton('disable');
        }
        $('#winSelKs').window('open').window('center');
    }

    function SearchKs(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxksData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allKsData.length; i++)
                {
                    if (allKsData[i]['bmbm'].indexOf(value) >= 0 || allKsData[i]['bmmc'].indexOf(value) >= 0 || nullToEmpty(allKsData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxksData.push(allKsData[i]);
                    }
                }
            }
            else
                filterDxksData = allKsData;

            $('#gridDXKS').datagrid({
                data:filterDxksData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }


    function SaveKs()
    {
        var param = {};
        param.a01 = iGyid;

        param.a02 = "";
        for(var i = 0; i < selKsData.length; i++)
            param.a02 += selKsData[i].bmbm + ",";
        //alert(param.a02);
        if(!param.a01)
        {
            alert("未选择指定项目");
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            url: "indepartment/add.do",
            data: param,
            success: function (data) {
                if (data.state) {
                    $('#winSelKs').dialog('close');
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

    //医嘱
    function SelYz(row)
    {
        iJdid = row.id;
        getData("allYz");
        getData("selYz");
        getData("allKs");
        getData("allYf");
        getData("allPd");
        //按钮启用和禁用
        if(kyxm_zt == '1'){
            $("#btnNewYz").linkbutton('enable');
            $("#btnEditYz").linkbutton('enable');
            $("#btnDelYz").linkbutton('enable');
        }else{
            $("#btnNewYz").linkbutton('disable');
            $("#btnEditYz").linkbutton('disable');
            $("#btnDelYz").linkbutton('disable');
        }
        $('#winSelYz').window('open').window('center');
    }

    function SearchAddYz(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxyzData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allYzData.length; i++)
                {
                    if (allYzData[i]['dmmc'].indexOf(value) >= 0 || allYzData[i]['yzfl'].indexOf(value) >= 0 || nullToEmpty(allYzData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxyzData.push(allYzData[i]);
                    }
                }
            }
            else
                filterDxyzData = allYzData;

            $('#gridAddDXYZ').datagrid({
                data:filterDxyzData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }

    var rowDataYz; //阶段医嘱的一条数据
    var iJdYzModifyType;
    function NewYz(iType)
    {
        try
        {
            rowDataYz = {};
            iJdYzModifyType = iType;
            $('#formAddYz').form('clear');
            
            $('#ctrl_XH').numberspinner('setValue', "1");
            $('#ctrl_JZFS').prop("checked", true);
            $('#ctrl_BZXM').prop("checked", true);
            $('#ctrl_KYBZ').prop("checked", true);

            if (iType == 1)
            {
                //修改医嘱
                var selRow = $('#gridYXYZ').datagrid('getSelected');
                if (selRow == null)
                {
                    alert("请先选择需编辑的行");
                    return;
                }
                rowDataYz = selRow;
                $('#ctrl_XH').numberspinner('setValue', rowDataYz.xh);
                $('#ctrl_YZLB').val(rowDataYz.yzlb);
                $('#ctrl_YZMC').val(rowDataYz.yzmc);
                $('#ctrl_ZXKS').val(rowDataYz.zxks);
                $('#ctrl_ZXKSMC').val(rowDataYz.zxksmc);

                $('#ctrl_JZFS').prop("checked",rowDataYz.jzfs == "1" ? true : false);
                $('#ctrl_BZXM').prop("checked",rowDataYz.bzxm == "1" ? true : false);
                $('#ctrl_KYBZ').prop("checked",rowDataYz.kybz == "1" ? true : false);

            }
            else if (iType == 2)
            {
                //删除
                if (window.confirm('确认要删除本条医嘱？'))
                    SaveYz();
                return;
            }
            $('#winAddYz').window('open').window('center');
        }
        catch (e)
        {
            alert("出错：" + e.description);
        }
    }

    function SaveYz()
    {
        try
        {
            var strUrl = "stageadvice/add.do";
            if (iJdYzModifyType == 1 || iJdYzModifyType == 2)
            {
                var selRow = $('#gridYXYZ').datagrid('getSelected');
                if (selRow == null)
                {
                    alert("请先选择医嘱！");
                    return;
                }
                rowDataYz.id = selRow.id;
                if (iJdYzModifyType == 1)
                    strUrl = "stageadvice/update.do";
                else if (iJdYzModifyType == 2)
                    strUrl = "stageadvice/delete.do";
            }
            else
                rowDataYz.id = "0";

            rowDataYz.gyid = iJdid;
            rowDataYz.xh = $('#ctrl_XH').numberspinner('getValue');
            if (rowDataYz.xh.length == 0 && iJdYzModifyType != 2)
            {
                alert("您未指定序号，请返回检查");
                return;
            }
            //rowDataYz.yzlb = $('#ctrl_YZLB').val();
            //rowDataYz.yzdm = $('#ctrl_YZDM').val();
            rowDataYz.jzfs = $('#ctrl_JZFS').is(':checked') ? 1 : 0;
            rowDataYz.bzxm = $('#ctrl_BZXM').is(':checked') ? 1 : 0;
            rowDataYz.kybz = $('#ctrl_KYBZ').is(':checked') ? 1 : 0;

            var param = {};
            param.A01 = rowDataYz.id;
            param.A02 = rowDataYz.gyid;
            param.A03 = rowDataYz.xh;
            param.A04 = rowDataYz.yzlb;
            param.A05 = rowDataYz.yzdm;
            param.A06 = rowDataYz.jzfs;
            param.A07 = rowDataYz.bzxm;
            param.A08 = rowDataYz.kybz;
            param.A09 = rowDataYz.yzmc;
            param.A10 = rowDataYz.zxks;
            param.A11 = rowDataYz.yzje;
            //param.A11 = rowDataYz.jl;
            //param.A12 = rowDataYz.yf;
            //param.A13 = rowDataYz.yfsm;
            //param.A14 = rowDataYz.pd;
            //param.A15 = rowDataYz.ts;
            //param.A16 = rowDataYz.zsl;

            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                url: strUrl,
                data: param,
                success: function (data) {
                    if (data.state) {
                        $('#winAddYz').dialog('close');
                        $('#formAddYz').form('clear');
                        getData("selYz");
                    } else {
                        $.messager.alert({
                            title: '提示',
                            msg: "操作失败！"
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
        catch (e)
        {
            alert("出错：" + e.description);
        }
    }

    function winAddYz_YzConfirm()
    {
        var selRow = $('#gridAddDXYZ').datagrid('getSelected');
        if (selRow == null)
        {
            alert("请先选择医嘱！");
            return;
        }
        rowDataYz.yzlb = selRow.yzfl;
        rowDataYz.yzmc = selRow.dmmc;
        rowDataYz.yzdm = selRow.dm;
        rowDataYz.yzje = selRow.yzje;

        $('#ctrl_YZLB').val(selRow.yzfl);
        $('#ctrl_YZMC').val(selRow.dmmc);

        rowDataYz.zxks = selRow.mrzxks;
        $('#ctrl_ZXKS').val(selRow.mrzxks);
        $('#ctrl_ZXKSMC').val(selRow.mrzxksmc);

        $('#winAddYz_yz').window('close');
    }

    function SearchAddKs(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxksData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allKsData.length; i++)
                {
                    if (allKsData[i]['bmbm'].indexOf(value) >= 0 || allKsData[i]['bmmc'].indexOf(value) >= 0 || nullToEmpty(allKsData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxksData.push(allKsData[i]);
                    }
                }
            }
            else
                filterDxksData = allKsData;

            $('#gridAddDXKS').datagrid({
                data:filterDxksData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }

    function winAddYz_KsConfirm()
    {
        var selRow = $('#gridAddDXKS').datagrid('getSelected');
        if (selRow == null)
        {
            alert("请先选择科室！");
            return;
        }
        rowDataYz.zxks = selRow.bmbm;

        $('#ctrl_ZXKS').val(selRow.bmbm);
        $('#ctrl_ZXKSMC').val(selRow.bmmc);

        $('#winAddYz_ks').window('close');
    }


    function SearchAddYf(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxyfData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allYfData.length; i++)
                {
                    if (allYfData[i]['uname'].indexOf(value) >= 0 || allYfData[i]['dm'] == value)
                    {
                        filterDxyfData.push(allYfData[i]);
                    }
                }
            }
            else
                filterDxyfData = allYfData;

            $('#gridAddDXYF').datagrid({
                data:filterDxyfData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }
    function winAddYz_YfConfirm()
    {
        var selRow = $('#gridAddDXYF').datagrid('getSelected');
        if (selRow == null)
        {
            alert("请先选择用法！");
            return;
        }
        rowDataYz.yf = selRow.dm;

        $('#ctrl_YFMC').val(selRow.uname);

        $('#winAddYz_yf').window('close');
    }

    function SearchAddPd(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxpdData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < allPdData.length; i++)
                {
                    if (allPdData[i]['dmmc'].indexOf(value) >= 0 || allPdData[i]['dm'] == value || nullToEmpty(allPdData[i]['ldw']).toLowerCase().indexOf(value.toLowerCase()) >= 0 )
                    {
                        filterDxpdData.push(allPdData[i]);
                    }
                }
            }
            else
                filterDxpdData = allPdData;

            $('#gridAddDXPD').datagrid({
                data:filterDxpdData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }
    function winAddYz_PdConfirm()
    {
        var selRow = $('#gridAddDXPD').datagrid('getSelected');
        if (selRow == null)
        {
            alert("请先选择频度！");
            return;
        }
        rowDataYz.pd = selRow.dm;

        $('#ctrl_PDLDW').val(selRow.ldw);
        $('#ctrl_PDMC').val(selRow.dmmc);

        $('#winAddYz_pd').window('close');
    }

    function nullToEmpty(value)
    {
        if (value == null || value == undefined )
            return "";
        return value;
    }

    var xqAllKsData = [];
    var xqAllRyData = [];
    var zgys = '';
    var zgks = '';

    $("#xq_gridDXKS").datagrid({
        onDblClickRow: function(rowIndex, rowData){
            // $('#zgks').textbox({'value':rowData.bmmc});//塌陷，点不到了？
            $("#f_kyxmxq").form("load",{zgksmc:rowData.bmmc});
            zgks = rowData.bmbm;
            closeDlg('xq_selKs');
        }
    });

    $("#xq_gridDXRY").datagrid({
        onDblClickRow:function(rowIndex, rowData){
            // $('#zgys').textbox({'value':rowData.czyxm});
            $("#f_kyxmxq").form("load",{zgysxm:rowData.czyxm});
            zgys = rowData.czydm;
            closeDlg('xq_selRy');
        }
    });

    function xqSearchKs(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxksData = [];//过滤后的科室
            if (value.trim().length > 0)
            {
                for(var i = 0; i < xqAllKsData.length; i++)
                {
                    if (xqAllKsData[i]['bmbm'].indexOf(value) >= 0 || xqAllKsData[i]['bmmc'].indexOf(value) >= 0 || nullToEmpty(xqAllKsData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxksData.push(xqAllKsData[i]);
                    }
                }
            }
            else
                filterDxksData = xqAllKsData;

            $('#xq_gridDXKS').datagrid({
                data:filterDxksData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }

    function xqSearchRy(value,name)
    {
        //alert(value + " " + name);
        try{
            var filterDxryData = [];
            if (value.trim().length > 0)
            {
                for(var i = 0; i < xqAllRyData.length; i++)
                {
                    if (xqAllRyData[i]['czydm'].indexOf(value) >= 0 || nullToEmpty(xqAllRyData[i]['czyxm']).indexOf(value) >= 0 || nullToEmpty(xqAllRyData[i]['pym']).toLowerCase().indexOf(value.toLowerCase()) >= 0)
                    {
                        filterDxryData.push(xqAllRyData[i]);
                    }
                }
            }
            else
                filterDxryData = xqAllRyData;

            $('#xq_gridDXRY').datagrid({
                data:filterDxryData
            });
        }
        catch(e){
            alert('err:' + e.description);
        }
    }

    function xqSelKs()
    {
        // if(kyxm_zt != "1"){
        //     switch (kyxm_zt){
        //         case '2':
        //             text = '已审批';
        //             $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //             return;
        //         // case '3':
        //         //     text = '已作废';
        //         //     $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //         //     return;
        //     }
        // }
        getXqData("allKs");
        $("#xq_txtSearchKS").searchbox('setValue','');
        $('#xq_selKs').window('open').window('center');//第二次加载数据，必须在显示状态datagrid加载数据才会渲染？
        //异步查询，第一次打开，数据有可能还没返回？
        if(xqAllKsData.length > 0){
            $('#xq_gridDXKS').datagrid({
                data:xqAllKsData
            });
        }


        // $('#zgks').textbox({'value':"aaaaaa"});//能改变显示
    }

    function xqSelRy()
    {
        // if(kyxm_zt != "1"){
        //     switch (kyxm_zt){
        //         case '2':
        //             text = '已审批';
        //             $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //             return;
        //         // case '3':
        //         //     text = '已作废';
        //         //     $.messager.alert('提示', "项目" + text + ",不可修改", "info");
        //         //     return;
        //     }
        // }
        getXqData("allRy");
        $("#xq_txtSearchRY").searchbox('setValue','');
        $('#xq_selRy').window('open').window('center');
        if(xqAllRyData.length > 0){
            $('#xq_gridDXRY').datagrid({
                data:xqAllRyData
            });
        }
    }

    function getXqData(strType) {
        //$.messager.progress();
        var strUrl = "";
        switch(strType)
        {
            case "allRy": //所有人员
                if (xqAllRyData.length > 0)
                    return;
                //allRyData = [];
                strUrl = "workers/query.do";
                break;
            case "allKs": //所有科室
                if (xqAllKsData.length > 0)
                    return;

                //allKsData = [];
                strUrl = "department/query.do";
                break;

        }

        $.ajax({
            type: "get",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            url: strUrl,
            success: function (data) {
                if (data && data.rows) {
                    switch(strType)
                    {

                        case "allRy": //所有人员
                            xqAllRyData = data.rows;
                            //alert("all " + allRyData.length);

                            $('#xq_gridDXRY').datagrid({
                                data:xqAllRyData
                            });
                            break;
                        case "allKs": //所有科室
                            xqAllKsData = data.rows;
                            //alert("all " + allKsData.length);

                            $('#xq_gridDXKS').datagrid({
                                data:xqAllKsData
                            });
                            break;
                    }
                }
            },
            error: function (xhr, status, error) {
                //XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
                alert("err: " + error + " status:" + status);
            }
        });
    }


</script>

</body>
</html>