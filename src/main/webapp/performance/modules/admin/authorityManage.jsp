<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_qxgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="admin_qxgl_searchForm"  method="post">
			检索用户名称(可模糊查询)：<input name="rightname" /> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_qxgl_datagrid"></table>
	</div>
</div>

<input id="authorityContextPath" type="hidden" value="${pageContext.request.contextPath}"/>

<div id="admin_qxgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_qxgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/rightAction!add.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_qxgl_datagrid').datagrid('load');*/
								/*$('#admin_qxgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_qxgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_qxgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]" style="width: 500px;height:200px;" align="center">
	<form id="admin_qxgl_addForm" method="post">
		<table>
			<tr>
				
				<th>权限名称</th>
				<td><input name="rightname" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>权限码</th>
				<td><input name="rightcode" class="easyui-validatebox" />
				</td>
				<th>url</th>
				<td><input name="righturl"  />
				</td>
			</tr>
			
			<tr>
				<th>权限位</th>
				<td><input name="rightpos"  />
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>公有</th>
				<td><input name="common"  />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>