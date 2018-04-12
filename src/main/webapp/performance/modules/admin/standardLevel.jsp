<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_bzlevel_partBar" class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:400px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchForm" method="post">
		名称模糊查询：<input name="name" /> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
	</form>
</div>
<div id="admin_bzlevel_dateBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:550px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchTimeForm" method="post">
		起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required"> 截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchdate();">查询</a> 
	</form>
</div>

<div id="admin_bzlevel_comBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_bzlevel_searchstutasForm" method="post">
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
<div id="admin_bzlevel_export" class="easyui-dialog" data-options="region:'center',title:'excel导出',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_bzlevel_exportForm" method="post">
		<select class="easyui-combobox" name="state" style="width:150px;">
			<option value="0">导出已选记录</option>
			<option value="1">按过滤结果导出</option>
			<option value="2">全部导出</option>
		</select>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="export();">查询</a> 

	</form>
</div>
<div id="admin_bzlevel_import" class="easyui-dialog" data-options="region:'center',title:'excel导入',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_bzlevel_importForm" method="post">
	    <input class="easyui-filebox" style="width:100%">

	</form>
</div>


<div id="admin_bzlevel_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_bzlevel_datagrid"></table>
	</div>
</div>

<div id="admin_bzlevel_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_bzlevel_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/bzLevelAction!add.action',
						beforeSubmit:getyhris(),
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_bzlevel_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_bzlevel_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#selRoles').find('option').remove();
					        $('#allRoles').find('option').remove();
							$('#admin_bzlevel_addDialog').dialog('close');
						}
					});
				}
			}]"
	style="width: 500px;height:500px;" align="center">
	<form id="admin_bzlevel_addForm" method="post">
		<table>
			<tr>
				<!-- <th>编号</th>
				<td><input name="id" readonly="readonly" />
				</td> -->
				<th>级别名称</th>
				<td><input name="name" id="name" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			
			<tr>
				<!-- <th>最后修改时间</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td> -->
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>分数</th>
				<td><input name="score" id="score" /></td>
				<th></th>
				<td></td>
			</tr>
			
		</table>
	</form>
</div>