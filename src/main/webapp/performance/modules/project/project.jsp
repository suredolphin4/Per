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
	
	

	<!-- 项目表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="removefunc()">删除</a>
		<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改</a>
		<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="projectAdvancedSearch()">高级查询</a>
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
	    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
	    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
	    <a id="delAll" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-removeAll', plain:true" onclick="removeAll()">清空数据</a>
	</div>

	<!-- 高级搜索 -->
	<div id="projectAdvancedSearch_dialog" class="easyui-dialog" data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
		style="width:580px;height: 220px;padding-top:20px;padding-left:20px">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>开题年度</th>
					<td><input id="project_begin_year" name="project_begin_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
					<th>结题年度</th>
					<td><input id="project_end_year" name="project_end_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				</tr>
				<tr>
					<th>课题名称</th>
					<td><input type="text" id="project_lw_name" name="project_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th>负责人</th>
					<td><input type="text" id="project_lw_author" name="project_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
				<th>负责人编号</th>
					<td><input type="text" id="project_lw_authorcode" name="project_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				<th>课题编号</th>
				<td><input type="text" id="project_xm_projectID" name="project_xm_projectID"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
			</table>
	</div>
	<div id="advanced_search_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
    </div>
	
	<!-- 添加项目-->
	<div id="admin_project_addDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加 项目',modal: true,buttons:'#add_lw_btn'"
	 	style="width:705px;height: 550px;padding-top:20px;padding-left:20px">
		<form id="admin_project_addForm" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>课题信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>课题全称</th>
						<td colspan="3"><input name="name" id="add_name" class="easyui-textbox"  data-options="required:true, width:'515px'"/></td>
					</tr>
					<tr>
						<th>项目编号</th>
						<td><input name="projectid" id="add_projectid" class="easyui-textbox"
							data-options="required:true, prompt:'填写奖项', width:'200px'" /></td>
						<th>课题组长</th>
						<td><input name="leader" id="add_leader" class="easyui-textbox"
							data-options="required:true,editable:false,prompt:'课题组长', width:'200px'" />
						</td>
					</tr>
					<tr>
						<th>甲方</th>
						<td><input name="provider" id="add_provider" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>任务类型</th>
						<td><input name="type" id="add_type" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					
					<tr>
						<th>开始年度</th>
						<td><input name="beginTime" id="add_begin" class="easyui-combobox"  data-options="valueField:'id', textField:'text', required:true, width:'200px', editable:false"/></td>
						<th>终止年度</th>
						<td><input name="endTime" id="add_end" class="easyui-combobox"  data-options="valueField:'id', textField:'text', required:true, width:'200px', editable:false"/></td>
					</tr>
					<tr>
						<th>院级课题编码</th>
						<td><input name="collegeCode" id="add_collegeCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>子课题信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>子课题全称</th>
						<td colspan="3"><input name="subName" id="add_subName" class="easyui-textbox"  data-options="required:true, prompt:'子课题全称', width:'515px'"/></td>
					</tr>
					<tr>
						<th>子课题号</th>
						<td><input name="subCode" id="add_subCode" class="easyui-textbox"  data-options="required:true, prompt:'子课题号', width:'200px'"/></td>
						<th>子课题负责人</th>
						<td><input name="subLeader" id="add_subLeader" class="easyui-textbox"  data-options="required:true,editable:false,prompt:'子课题负责人', width:'200px'"/></td>
					</tr>
					<tr>
						<th>任务级别</th>
						<td><input name="level" id="add_level" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>任务代码</th>
						<td><input name="taskCode" id="add_taskCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					<tr>
						<th>明细任务类型</th>
						<td><input name="detailType" id="add_detailType" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>人员角色代码</th>
						<td><input name="roleCode" id="add_roleCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					<tr>
						<th>分值</th>
						<td><input name="score" id="add_score" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>课题留所经费(万)</th>
						<td><input name="subProjectStay" id="add_subProjectStay" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					<input type="hidden"  name="leaderCode" id="add_leadercode" />
					<input type="hidden"  name="subLeaderCode" id="add_subLeadercode" />
				</table>
			</fieldset>
		</form>
    </div>
    <div id="add_lw_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="add_project_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="add_project_btn_cancel()">取消</a>
    </div>
    
	<!-- 编辑项目 -->
	<div id="admin_project_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑项目',modal:true,buttons:'#edit_lw_btn'"
	 	style="width:705px;height: 550px;padding-top:20px;padding-left:20px">
	<form id="admin_project_editForm" method="post" enctype="multipart/form-data">
		<fieldset>
				<legend>课题信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>课题全称</th>
						<td colspan="3"><input name="name" id="e_name" class="easyui-textbox"  data-options="required:true, width:'515px'"/></td>
					</tr>
					<tr>
						<th>项目编号</th>
						<td><input name="projectid" id="e_projectid" class="easyui-textbox"
							data-options="required:true, prompt:'填写奖项', width:'200px'" /></td>
						<th>课题组长</th>
						<td><input name="leader" id="e_leader" class="easyui-textbox"
							data-options="required:true,editable:false,prompt:'课题组长', width:'200px'" />
						</td>
					</tr>
					<tr>
						<th>甲方</th>
						<td><input name="provider" id="e_provider" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>任务类型</th>
						<td><input name="type" id="e_type" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					
					<tr>
						<th>开始年度</th>
						<td><input name="beginTime" id="e_begin" class="easyui-combobox"  data-options="valueField:'id', textField:'text', required:true, width:'200px', editable:false"/></td>
						<th>终止年度</th>
						<td><input name="endTime" id="e_end" class="easyui-combobox"  data-options="valueField:'id', textField:'text', required:true, width:'200px', editable:false"/></td>
					</tr>
					<tr>
						<th>院级课题编码</th>
						<td><input name="collegeCode" id="e_collegeCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>子课题信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>子课题全称</th>
						<td colspan="3"><input name="subName" id="e_subName" class="easyui-textbox"  data-options="required:true, prompt:'子课题全称', width:'515px'"/></td>
					</tr>
					<tr>
						<th>子课题号</th>
						<td><input name="subCode" id="e_subCode" class="easyui-textbox"  data-options="required:true, prompt:'子课题号', width:'200px'"/></td>
						<th>子课题负责人</th>
						<td><input name="subLeader" id="e_subLeader" class="easyui-textbox"  data-options="required:true,editable:false,prompt:'子课题负责人', width:'200px'"/></td>
					</tr>
					<tr>
						<th>任务级别</th>
						<td><input name="level" id="e_level" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>任务代码</th>
						<td><input name="taskCode" id="e_taskCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					<tr>
						<th>明细任务类型</th>
						<td><input name="detailType" id="e_detailType" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>人员角色代码</th>
						<td><input name="roleCode" id="e_roleCode" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
					<tr>
						<th>分值</th>
						<td><input name="score" id="e_score" class="easyui-textbox"  data-options="width:'200px'"/></td>
						<th>课题留所经费(万)</th>
						<td ><input name="subProjectStay" id="e_subProjectStay" class="easyui-textbox"  data-options="width:'200px'"/></td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden"  name="leaderCode" id="e_leadercode" />
			<input type="hidden"  name="subLeaderCode" id="e_subLeadercode" />
			<input type="hidden" name="id" id="e_id" />
	</form>
	</div>
	<div id="edit_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="edit_project_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit_project_btn_cancel()">取消</a>
	</div>

	<div id="import_project" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入项目',modal: true,buttons:'#import_lw_btn'"
	 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<table>
			<tr>
			<th>附件</th>
			<td><input type="file" name="upload" id="project_import"></td>
			</tr>
		</table>
	</div>
	<div id="#import_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editlw_btn_cancel()">取消</a>
	</div>
	
</body>
</html>