<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_lab_partBar" class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:450px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchForm" method="post">
		名称模糊查询：<input name="name" id="lab_search_name"  class="easyui-textbox" data-options="border:true" style="width:200px"/> 
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">重置</a>
	</form>
</div>
<input type=hidden id="contextPath"  value="${pageContext.request.contextPath}" />
<div id="admin_lab_dateBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:550px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchTimeForm" method="post">
		起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required"> 截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchdate();">查询</a> 
	</form>
</div>

<div id="admin_lab_comBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_lab_searchstutasForm" method="post">
		<select class="easyui-combobox" name="state" style="width:150px;">
			<option value="0">审核中</option>
			<option value="1">已提交</option>
			<option value="2">审核通过</option>
			<option value="3">已保存</option>
			<option value="3">已驳回</option>
		</select>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchstutas();">查询</a> 

	</form>
</div>
<div id="admin_lab_export" class="easyui-dialog" data-options="region:'center',title:'excel导出',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_lab_exportForm" method="post">
		<select class="easyui-combobox" name="state" style="width:150px;">
			<option value="0">导出已选记录</option>
			<option value="1">按过滤结果导出</option>
			<option value="2">全部导出</option>
		</select>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="export();">查询</a> 

	</form>
</div>
<!-- <div id="admin_lab_import" class="easyui-dialog" data-options="region:'center',title:'excel导入',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_lab_importForm" method="post">
	    <input class="easyui-filebox" style="width:100%">

	</form>
</div>
 -->

<div id="admin_lab_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_lab_datagrid"></table>
	</div>
</div>

<!-- import excel-->
	<div id="admin_lab_import" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_lab_importForm').form('submit', {
						url : '${pageContext.request.contextPath}/labAction!upLoadExcel.action',
						success : function(result) {
						
						if(result!=''){
			
							var r = jQuery.parseJSON(result);
						if(r.success){
						$('#admin_lab_import').dialog('close');
							$.messager.show({
									title : '提示',
									msg : '导入成功'
								});
						}else{
								$.messager.show({
									title : '提示',
									msg : '导入失败！请检查excel格式'
								});
						
						}
						}else{
							$.messager.show({
									title : '提示',
									msg : '导入失败！请检查excel格式'
								});
						}
						
						}
					});
				}
			}]"
	style="width: 500px;height:200px;" align="center">
	<form id="admin_lab_importForm" method="post" enctype="multipart/form-data">
		   <div style="margin-bottom:20px">
            <div>文件浏览:</div>
            
            <input class="easyui-filebox" name="url" id="url" data-options="prompt:'选择'" style="width:100%">
        
        </div>
	</form>
</div>

<!-- add-->

<div id="admin_lab_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加实验室',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_lab_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/labAction!add.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#admin_lab_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_lab_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#admin_lab_addDialog').dialog('close');
						}
					});
				}
			}]"
	style="width: 380px;height:260px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_lab_addForm" method="post">
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>实验室名称</th>
				<td colspan="1"><input name="name" id="add_lab_name" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室名称', width:'150px'" /></td>
				
			</tr>
			<tr>
				
				<th>实验室编码</th>
				<td colspan="1"><input name="code" id="add_lab_code" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室编码', width:'150px'" /></td>
				
			</tr>
			<tr>
				<th>领域</th>
				<td colspan="1"><input name="domainname" id="add_lab_domainid" class="easyui-combobox"  data-options="required:true,border:true, prompt:'请选择领域', width:'150px',editable:false"></input></td>
			</tr>
			
		
		</table>
<!-- 			<input name="domainname" id="add_lab_domainname" type="hidden" />
 -->			<input name="domaincode" id="add_lab_domaincode" type="hidden" />
	</form>
</div>



<!-- 编辑 -->
<div id="admin_lab_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑研究室',modal:true,buttons:'#edit_dt_btn'" 
style="width: 380px;height:260px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_lab_editForm" method="post" enctype="multipart/form-data">
		
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>实验室名称</th>
				<td><input name="name" id="e_lab_name" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室名称', width:'150px'" /></td>
				
			</tr>
			<tr>
				
				<th>实验室编码</th>
				<td><input name="code" id="e_lab_code" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室编码', width:'150px'" /></td>
				
			</tr>
			<tr>
				<th>领域</th>
				<td><select name="domainname" id="e_lab_domainid" class="easyui-combobox"  data-options="required:true,border:true, prompt:'请选择期刊类型', width:'150px',editable:false"></select></td>
			</tr>
			
		</table>
				<input name="labid" id="e_lab_labid" type="hidden" />
				<input name="domaincode" id="e_lab_domaincode"    type="hidden"   />
<!-- 				<input name="domainname" id="e_lab_domainname"   type="hidden"  />
 -->	</form>
</div>

<div id="edit_dt_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editdt_btn_ok()">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editdt_btn_cancel()">取消</a>
</div>