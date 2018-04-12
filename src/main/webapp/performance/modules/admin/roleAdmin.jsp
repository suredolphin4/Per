<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_jsgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="admin_jsgl_searchForm">
			检索用户名称(可模糊查询)：<input name="rolename" /> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_jsgl_datagrid"></table>
	</div>
</div>

<div id="admin_jsgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加角色',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_jsgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!add.action',
						beforeSubmit:getris(),
						data:{rights:tempright},
						<!-- data:{rights:$('#rights > option').attr('selected','selected')}, -->
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_jsgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_jsgl_addDialog').dialog('close');
							}
							
							
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#rights').find('option').remove();
					        $('#all').find('option').remove();
								$('#admin_jsgl_addDialog').dialog('close');
							
						}
					});
				}
			}]" style="width: 600px;height:500px;" align="center">
	<form id="admin_jsgl_addForm" method="post">
		<table>
			<tr>
				<th>角色名称</th>
				<td><input name="rolename" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>

				<th>角色值</th>
				<td><input name="rolevalue" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>角色描述</th>
				<td><input name="roledesc"  id="roledesc" /></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>权限</th>
				<td><select name="all"   id="all" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="button2" type="button" onclick="toleft()"></input></td>
				<td><input name="button2" type="button" onclick="toright()"></input></td>

				<td><select name="rights" id="rights" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
			</tr>
			 <tr>
			 	<td><input name="tempright" id="tempright" type="hidden"></input></td>
			 </tr>
		</table>
	</form>
</div>