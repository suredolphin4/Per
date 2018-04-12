<%@ page language="java" pageEncoding="UTF-8"%>
	<form id="admin_qxgl_updateForm" method="post">
		<table>
			<tr>
				<th>权限编号</th>
				<td><input name="rightid" class="easyui-validatebox"   readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>权限名称</th>
				<td><input name="rightname" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				
				<th>权限值</th>
				<td><input name="rightcode" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				
				<th>url</th>
				<td><input name="righturl" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			
		</table>
	</form>
