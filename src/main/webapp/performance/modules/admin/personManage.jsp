<%@ page language="java" pageEncoding="UTF-8"%>
<input type=hidden id="contextPath" value="${pageContext.request.contextPath}" />
<div id="admin_rygl_partBar" class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:480px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_rygl_searchForm" method="post">
		人员查询：<input name="name"  class="easyui-textbox" data-options="border:true" style="width:200px"/>
		 <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
		  <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">重置</a>
	</form>
</div>
<div id="admin_rygl_dateBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:500px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_rygl_searchTimeForm" method="post">
		起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required"> 截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">

	</form>
</div>

<div id="admin_rygl_comBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_rygl_searchstutasForm" method="post">
		<select class="easyui-combobox" name="state" style="width:200px;">
			<option value="AL">审核中</option>
			<option value="AK">已提交</option>
			<option value="AZ">审核通过</option>
		</select>

	</form>
</div>


<div id="admin_rygl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_rygl_datagrid"></table>
	</div>
</div>


<input id="personContextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- import excel-->
<div id="admin_rygl_import" class="easyui-dialog" data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_rygl_importForm').form('submit', {
						url : '${pageContext.request.contextPath}/personAction!upLoadExcel.action',
						success : function(result) {
						$('#admin_rygl_import').dialog('close');
						if(result!=''){
							var r = jQuery.parseJSON(result);
						
						
						if(r.success){
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
						
						 $('#admin_rygl_datagrid').datagrid('reload');
						}else{
							$.messager.show({
									title : '提示',
									msg : '导入失败！请检查excel格式'
								});
						}
						}
					});
				}
			}]" style="width: 500px;height:200px;" align="center">
	<form id="admin_rygl_importForm" method="post" enctype="multipart/form-data">
		<div style="margin-bottom:20px">
			<div>文件浏览:</div>
			<input class="easyui-filebox" name="url" id="url" data-options="prompt:'选择'" style="width:100%">
		</div>
	</form>
</div>



<div id="admin_rygl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_rygl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/personAction!add.action',
						beforeSubmit:getyhris(),
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#admin_rygl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_rygl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#selRoles').find('option').remove();
					        $('#allRoles').find('option').remove();
							$('#admin_rygl_addDialog').dialog('close');
						}
					});
				}
			}]" style="width: 550px;height:300px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_rygl_addForm" method="post">
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>姓名</th>
				<td><input name="name" class="easyui-textbox" data-options="required:true, prompt:'请填写员工名称', width:'150px'"/></td>
				<th>员工编号</th>
				<td><input name="usercode"  class="easyui-textbox"     data-options="required:true,border:true, prompt:'请填写员工编号', width:'150px'" /></td>
			</tr>
			<tr>
				<th>领域：</th>
				<td><input class="easyui-combobox" id="add_person_domain" name="domain" data-options="required:true,editable:false,prompt:'请选择领域', width:'150px'"/></td>
				<th>研究室：</th>
				<td><input class="easyui-combobox" id="add_person_lab" name="lab" data-options="required:true,editable:false, prompt:'请选择实验室', width:'150px'"/></td>
			</tr>
			<tr>
				<th>部门</th>
				<td><input name="department" id="department"  class="easyui-textbox"  data-options="required:true, prompt:'请填写部门名称', width:'150px'" /></td>
				<th>人员类型：</th>
				<td><select class="easyui-combobox" id="add_person_persontype" name="persontype" data-options="required:true,editable:false, prompt:'请选择人员类型', width:'150px'"></select></td>
			</tr>
			<tr>
				<th>申报级别：</th>
				<td><select class="easyui-combobox" id="add_person_personcate" name="personcate" data-options="prompt:'请选择人员申报级别',editable:false, width:'150px'"></select></td>
			</tr>
		</table>
	</form>
</div>


<!-- 编辑 -->
<div id="admin_rygl_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑人员',modal:true,buttons:'#edit_dt_btn'" 
 style="width: 550px;height:300px;padding-top:20px;padding-left:20px;padding-right:20px">
	<form id="admin_rygl_editForm" method="post" enctype="multipart/form-data">
		<table  class="altrowstable" cellspacing=”0″>
			<tr>
				<th>姓名</th>
				<td><input name="name"  id="e_person_name" class="easyui-textbox" data-options="required:true, prompt:'请填写人员名称', width:'150px'" /></td>
				<th>员工编号</th>
				<td><input name="usercode"  id="e_person_usercode"  class="easyui-numberbox"   max="9999"  maxlength="4"   data-options="required:true, prompt:'请填写员工编号', width:'150px'" /></td>
			</tr>
			<tr>
				<th>领域：</th>
				<td><select name="domain" id="e_person_domain" class="easyui-combobox"  data-options="border:true,editable:false, prompt:'请选择领域', width:'150px'"></select></td>
				<th>研究室：</th>
				<td><select name="lab" id="e_person_lab" class="easyui-combobox"  data-options="border:true,editable:false, prompt:'请选择研究室', width:'150px'"></select></td>
			</tr>
			<tr>
				<th>部门</th>
				<td><input name="department" id="e_person_department" class="easyui-textbox" data-options="prompt:'请填写部门名称', width:'150px'" /></td>
				<th>人员类型：</th>
				<td><select class="easyui-combobox" id="e_person_persontype" name="persontype" data-options="required:true, editable:false,prompt:'请选择人员类型', width:'150px'"></select></td>
			</tr>
		 	<tr>
				<th>申报级别：</th>
				<td><select class="easyui-combobox" id="e_person_personcate" name="personcate" data-options="prompt:'请选择人员申报级别',editable:false, width:'150px'"></select></td>
			</tr>
		
		</table>
		<input name="personid" id="e_person_personid" type="hidden" />
	</form>
</div>

<div id="edit_dt_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-ok" onclick="editdt_btn_ok()">保存</a>
	 <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="editdt_btn_cancel()">取消</a>
</div>
