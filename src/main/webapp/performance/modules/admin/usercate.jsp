<%@ page language="java" pageEncoding="UTF-8"%>
	
<div id="admin_usercate_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_usercate_datagrid"></table>
	</div>
</div>

<input type=hidden id="contextPath"  value="${pageContext.request.contextPath}" />
<!-- import excel-->
	<div id="admin_usercate_import" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_usercate_importForm').form('submit', {
						url : '${pageContext.request.contextPath}/userCateAction!upLoadExcel.action',
						success : function(r) {
						$('#admin_usercate_import').dialog('close');
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
	<form id="admin_usercate_importForm" method="post" enctype="multipart/form-data">
		   <div style="margin-bottom:20px">
            <div>文件浏览:</div>
            
            <input class="easyui-filebox" name="url" id="url" data-options="prompt:'选择'" style="width:100%">
        
        </div>
	</form>
</div>


<!-- 编辑 -->
<div id="admin_usercate_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑申报级别',modal:true,buttons:'#edit_dt_btn'" 
style="width: 370px;height:200px;padding-top:20px;padding-left:40px;padding-right:20px">
	<form id="admin_usercate_editForm" method="post" enctype="multipart/form-data">
		
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>申报级别名称</th>
				<td colspan="1"><input name="name" id="e_usercate_name" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室名称', width:'150px'" /></td>
				
			</tr>
		</table>
				<input name="usercateid" id="e_usercate_usercateid"     type="hidden" />
	</form>
</div>

<div id="edit_dt_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editdt_btn_ok()">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editdt_btn_cancel()">取消</a>
</div>

<div id="admin_usercate_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_usercate_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userCateAction!add.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_usercate_datagrid').datagrid('load');*/
								/*$('#admin_usercate_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_usercate_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_usercate_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#admin_usercate_addDialog').dialog('close');
						}
					});
				}
			}]" style="width: 400px;height:200px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_usercate_addForm" method="post">
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>申报级别名称</th>
				<td colspan="1"><input name="name" id="add_usercate_name" class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写实验室名称', width:'150px'" /></td>
				
			</tr>
			
		</table>
	</form>
</div>