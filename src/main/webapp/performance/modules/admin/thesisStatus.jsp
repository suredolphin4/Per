<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_lwstutas_partBar" class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:400px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchForm" method="post">
		名称模糊查询：<input name="name" /> 
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
	</form>
</div>
<div id="admin_lwstutas_dateBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:550px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchTimeForm" method="post">
		起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required"> 截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchdate();">查询</a> 
	</form>
</div>

<div id="admin_lwstutas_comBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_lwstutas_searchstutasForm" method="post">
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
<div id="admin_lwstutas_export" class="easyui-dialog" data-options="region:'center',title:'excel导出',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_lwstutas_exportForm" method="post">
		<select class="easyui-combobox" name="state" style="width:150px;">
			<option value="0">导出已选记录</option>
			<option value="1">按过滤结果导出</option>
			<option value="2">全部导出</option>
		</select>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="export();">查询</a> 

	</form>
</div>



<!-- import excel-->
	<div id="admin_lwstutas_import" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					lwStutasExcelImport('#admin_lwstutas_importForm','#admin_lwstutas_import');
				}
			}]"
	style="width: 500px;height:200px;" align="center">
	<form id="admin_lwstutas_importForm" method="post" enctype="multipart/form-data">
		   <div style="margin-bottom:20px">
            <div>文件浏览:</div>
            
            <input class="easyui-filebox" name="url" id="url" data-options="prompt:'选择'" style="width:100%">
        
     <!--    <input type="file" id="excelPath" name="excelPath"/>&nbsp;&nbsp;   
<input type="button"  value="导入Excel" onclick="importEmp()"/>  -->
        </div>
	</form>
</div>


<div id="admin_lwstutas_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_lwstutas_datagrid"></table>
	</div>
</div>

<div id="admin_lwstutas_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加论文状态',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_lwstutas_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/lwStutasAction!add.action',
						beforeSubmit:getyhris(),
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_lwstutas_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_lwstutas_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#admin_lwstutas_addDialog').dialog('close');
						}
					});
				}
			}]"
	style="width: 450px;height:200px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_lwstutas_addForm" method="post">
		<table  class="altrowstable" cellspacing=”0″>
			<tr>
				<th>论文类型名称</th>
				<td colspan="1"><input name="name" id="name" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>doi</th>
				<td colspan="1"><input name="doi" id="doi"   /></td>
			</tr>
			
		</table>
	</form>
</div>

<!-- 编辑 -->
<div id="admin_lwstutas_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑论文状态',modal:true,buttons:'#edit_dt_btn'" 
	style="width: 450px;height:200px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_lwstutas_editForm" method="post" enctype="multipart/form-data">
		
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>领域名称</th>
				<td><input name="name"  id="e_lwstutas_name"   class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写领域名称', width:'150px'"  /></td>
			</tr>	
		</table>
			<input name="lwstutasid" id="e_lwstutas_lwstutasid"   type="hidden"  />
<!-- 			<input name="lwstutasid" id="e_lwstutas_lwstutasid"   type="hidden" class="easyui-textbox" /> -->
	</form>
</div>

<div id="edit_dt_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editdt_btn_ok()">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editdt_btn_cancel()">取消</a>
</div>