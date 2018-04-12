<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
<%
	response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
	response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0
%>
	<!-- datagrid -->
	<jsp:include page="../../../layout/center.jsp"></jsp:include>
	<jsp:include page="../../common/searchperson.jsp"></jsp:include>
	
	

	<!-- 论文表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="removefunc()">删除</a>
		<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改</a>
		<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="lessonsAdvancedSearch()">高级查询</a>
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
	    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
	    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
	    	    <a id="delAll" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-removeAll', plain:true" onclick="removeAll()">清空数据</a>
	    
	</div>

	
	<!-- 高级搜索 -->
	<div id="lessonsAdvancedSearch_dialog" class="easyui-dialog" data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
		style="width:580px;height: 220px;padding-top:20px;padding-left:20px">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>起始年度</th>
					<td><input id="lessons_begin_year" name="lessons_begin_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
					<th>终止年度</th>
					<td><input id="lessons_end_year" name="lessons_end_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				</tr>
				<tr>
					<th>课程名称</th>
					<td><input type="text" id="lessons_lw_name" name="lessons_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th>教师</th>
					<td><input type="text" id="lessons_lw_author" name="lessons_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
					<th>教师编号</th>
					<td><input type="text" id="lessons_lw_authorcode" name="lessons_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
			</table>
	</div>
	<div id="advanced_search_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
    </div>
	
	<!-- 添加课程 -->
	<div id="admin_lessons_addDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加课程',modal: true,buttons:'#add_lw_btn'"
	 	style="width:580px;height: 280px;padding-top:20px;padding-left:20px">
		<form id="admin_lessons_addForm" method="post" enctype="multipart/form-data">
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>课程名称</th>
				<td colspan="3"><input id="add_name" name="name" class="easyui-textbox"
					data-options="required:true, prompt:'填写课程名称', width:'415px'" /></td>
			</tr>
			<tr>
				<th>年度</th>
				<td><input id="add_year" name="year" class="easyui-combobox"
					data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'150px', editable:false" />
				</td>
				<th>课程类型</th>
				<td><input name="type" id="add_type" class="easyui-textbox"  data-options="width:'150px'"/></td>
			</tr>
			<tr>
				<th>学时</th>
				<td><input name="period" id="add_period" class="easyui-textbox"  data-options="width:'150px'"/></td>
				<th>分值</th>
				<td><input name="score" id="add_score" class="easyui-textbox"  data-options="required:true, width:'150px'"/></td>
			</tr>
			<tr>
				<th>教师</th>
				<td><input name="teacher" id="add_teacher" class="easyui-textbox"  data-options="required:true, prompt:'授课教师名称',width:'150px'"/></td>
				<th>角色</th>
				<td><input name="role" id="add_role" class="easyui-textbox"  data-options="width:'150px'"/></td>
			</tr>
			<input type="hidden"  name="teacherCode" id="add_teachercode"/>
		</table>
		</form>
    </div>
    <div id="add_lw_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="add_lessons_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="add_lessons_btn_cancel()">取消</a>
    </div>
    
	<!-- 编辑课程-->
	<div id="admin_lessons_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑课程',modal:true,buttons:'#edit_lw_btn'"
	 	style="width:580px;height: 280px;padding-top:20px;padding-left:20px">
	<form id="admin_lessons_editForm" method="post" enctype="multipart/form-data">
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>课程名称</th>
				<td colspan="3"><input id="e_name" name="name" class="easyui-textbox"
					data-options="required:true, prompt:'填写课程名称', width:'415px'" /></td>
			</tr>
			<tr>
				<th>年度</th>
				<td><input id="e_year" name="year" class="easyui-combobox"
					data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'150px', editable:false" />
				</td>
				<th>课程类型</th>
				<td><input name="type" id="e_type" class="easyui-textbox"  data-options="width:'150px'"/></td>
			</tr>
			<tr>
				<th>学时</th>
				<td><input name="period" id="e_period" class="easyui-textbox"  data-options="width:'150px'"/></td>
				<th>分值</th>
				<td><input name="score" id="e_score" class="easyui-textbox"  data-options="required:true, width:'150px'"/></td>
			</tr>
			<tr>
				<th>教师</th>
				<td><input name="teacher" id="e_teacher" class="easyui-textbox"  data-options="required:true, prompt:'授课教师名称',width:'150px'"/></td>
				<th>角色</th>
				<td><input name="role" id="e_role" class="easyui-textbox"  data-options="width:'150px'"/></td>
			</tr>
		</table>
		<input type="hidden"  name="teacherCode" id="e_teachercode"/>
		<input type="hidden" name="id" id="e_id" />
	</form>
	</div>

	<div id="edit_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="edit_lessons_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit_lessons_btn_cancel()">取消</a>
	</div>

	<div id="import_lessons" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入授课',modal: true,buttons:'#import_lw_btn'"
	 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_thesis_importForm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
		<th>附件</th>
		<td><input type="file" name="upload" id="lessons_import"></td>
		</tr>
	</table>
	</form>
	</div>
	<div id="#import_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editlw_btn_cancel()">取消</a>
	</div>

	<!-- 论下载对话框  -->
	<div id="export_lw" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '论文导出',modal: true,buttons:'#export_lw_btn'"
	 	style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<form id="admin_thesis_exportForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="lessons_export"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#export_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="exportlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="exportlw_btn_cancel()">取消</a>
	</div>
	
</body>
</html>