<%@ page language="java" pageEncoding="UTF-8"%>

	<div  id="admin_yhgl_partBar"  class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:400px;height: 100px;padding-top:20px;padding-left:20px">
		<form id="admin_yhgl_searchForm" method="post">
			名称模糊查询：<input name="name"/> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
		</form>
	</div>
	<div  id="admin_yhgl_dateBar" class="easyui-dialog"   data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:500px;height: 100px;padding-top:20px;padding-left:20px">
		<form id="admin_yhgl_searchTimeForm"  method="post">
			起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required">
			截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
			
		</form>
	</div>
	
	<div  id="admin_yhgl_comBar" class="easyui-dialog"   data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
		<form id="admin_yhgl_searchstutasForm" method="post">
			<select class="easyui-combobox" name="state" style="width:200px;">
        <option value="AL">审核中</option>
        <option value="AK">已提交</option>
        <option value="AZ">审核通过</option>
    </select>
			
		</form>
	</div>
	
	
<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>

<input id="mapCateContextPath" type="hidden" value="${pageContext.request.contextPath}"/>

<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_yhgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						beforeSubmit:getyhris(),
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#selRoles').find('option').remove();
					        $('#allRoles').find('option').remove();
							$('#admin_yhgl_addDialog').dialog('close');
						}
					});
				}
			}]" style="width: 500px;height:500px;" align="center">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<!-- <th>编号</th>
				<td><input name="id" readonly="readonly" />
				</td> -->
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
				<!-- <th>创建时间</th>
				<td><input name="createdatetime" readonly="readonly" />
				</td> -->
			</tr>
			<tr>
				<!-- <th>最后修改时间</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td> -->
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>备注</th>
				<td><input name="comment"  id="comment" /></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>角色</th>
				<td><select name="allRoles"   id="allRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="but_left" type="button" onclick="toyhleft()"></input></td>
				<td><input name="button2" type="button" onclick="toyhright()"></input></td>

				<td><select name="selRoles" id="selRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>