<%@ page language="java" pageEncoding="UTF-8"%>
	<div  id="admin_yhgl_partBar"  class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:450px;height: 100px;padding-top:20px;padding-left:20px">
		<form id="admin_yhgl_searchForm" method="post">
			用户查询：<input name="name" id="admin_yhgl_searchForm_name"  class="easyui-textbox" data-options="border:true" style="width:200px"/>
			 <a  class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
			 <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">重置</a>
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
	
	<div id="admin_yhgl_addDilog_title" class="easyui-dialog"
		data-options="region:'north',title:'人员查询',border:false,closed:true,buttons:'#pub_btn'"
		style="width:400px;height: 420px;">
		<form id="admin_yhgl_addForm_title" method="post">
			人员查询：<input name="usercode" id="admin_yhgl_search_usercode"     class="easyui-textbox" data-options="border:true"  style="width:50%"  /> 
			<a class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchPersonFunc();">查询</a>
		</form>
		<div id="admin_yhgl_addDilog_titlediv" class="easyui-layout"
			data-options="fit:true,border:false">
			<div data-options="region:'center',border:false"
				style="width:350px;height: 340px;">
				<table id="admin_yhgl_addDilog_datagrid"></table>
			</div>
		</div>
	</div>
	
	
<table id="admin_yhgl_datagrid"></table>

<input id="userContextPath" type="hidden" value="${pageContext.request.contextPath}"/>

<!-- import excel-->
	<div id="admin_yhgl_import" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					ExcelImport('#admin_yhgl_importForm','#admin_yhgl_import');
				}
			}]"
	style="width: 500px;height:200px;" align="center">
	<form id="admin_yhgl_importForm" method="post" enctype="multipart/form-data">
		   <div style="margin-bottom:20px">
            <div>文件浏览:</div>
            
            <input class="easyui-filebox" name="url" id="url" data-options="prompt:'选择'" style="width:100%">
        	
     <!--    <input type="file" id="excelPath" name="excelPath"/>&nbsp;&nbsp;   
<input type="button"  value="导入Excel" onclick="importEmp()"/>  -->
        </div>
	</form>
</div>
	
	
	
	<!-- 工具栏 -->
<div id="tb">
	<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="user_append()">增加</a>
	<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="user_remove()">删除</a>
	<a id="resetPasswd" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lock', plain:true" onclick="resetPasswd()">密码重置</a>
	<a id="nameSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search', plain:true" onclick="searchByName()">检索</a> 
	<a id="batchInsert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="user_batch_append()">批量增加</a>
	<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importByExcel()">导入</a>
    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="refreshfunc_Manger('#admin_yhgl_datagrid')">刷新</a>
</div>
	
<!-- 批处理 -->	

<div id="admin_yhgl_addDilog_batch" class="easyui-dialog"
		data-options="region:'north',title:'人员批量添加',border:false,closed:true,buttons:'#yhgl_addDilog_batch_btn'"
		style="width:400px;height: 420px;">
		<form id="admin_yhgl_addForm_batch" method="post">
			人员查询：<input name="usercode" id="admin_yhgl_search_batch_usercode"     class="easyui-textbox" data-options="border:true"  style="width:50%"  /> 
			<a class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchBatchPersonFunc();">查询</a>
		</form>
		<div id="admin_yhgl_addDilog_batch_titlediv" class="easyui-layout"
			data-options="fit:true,border:false">
			<div data-options="region:'center',border:false"
				style="width:350px;height: 340px;">
				<table id="admin_yhgl_addDilog_batch_datagrid"></table>
			</div>
		</div>
	</div>
	
	
	<!-- batch -->
	<div id="yhgl_addDilog_batch_btn" >
       	<input id="yhgl_addDilog_batch_btn_filter" name="batch_roles" class="easyui-combobox"  data-options="width:'100px', editable:false" />
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDilog_batch_add()">添加</a>
	</div>
	
	
<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					submitSysUser();
		
		
		
					<%-- $('#admin_yhgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						beforeSubmit:getyhris(),
						success : function(b) {
							var obj = jQuery.parseJSON(b);
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
					}); --%>
				}
			}]" style="width: 520px;height:330px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_yhgl_addForm" name="admin_yhgl_addForm"  method="post">
		<table   class="altrowstable" cellspacing=”0″>
			<tr>
				<th>用户编号</th>
<!-- 				<td><input name="usercode" id="usercode"   class="easyui-validatebox" data-options="required:true" />-->
				<td colspan="1"><input name="usercode" id="admin_yhgl_add_usercode"   class="easyui-textbox" data-options="required:true,border:true"   style="width:240px"   onfocus="searchUsercode()" /></td>
					<td><input id="selfwrite" name="selfwrite" type="checkbox"  onclick="selfWriteCheck()"   />选择用户</td>
			</tr>
			<tr>
		
					<th>用户名称</th>
				<td colspan="2"><input name="name" id="name" class="easyui-textbox"  style="width:240px" data-options="border:true" />
			</tr>
			<tr>
		
				<th>领域</th>
				<td colspan="2"><select name="domain"    id="admin_yhgl_add_domain"  class="easyui-combobox" style="width:240px"  data-options="prompt:'选择所属领域',editable:false" ></select></td>
				</td>
			</tr>
			<tr>
		
				<th>申报级别</th>
				<td colspan="2"><select name="userCategory"  id="admin_yhgl_add_userCategory"  class="easyui-combobox" style="width:240px"  data-options="prompt:'选择申报级别',editable:false" ></select></td>
				</td>
			</tr>
			
			<tr>
				<!-- <td><input class="easyui-combobox" id="domain" name="domain"
						data-options=" panelHeight:'auto'"  readonly="readonly" >
						</td> -->
				<th>角色</th>
				<td colspan="2"><select name="allRoles"   id="admin_yhgl_add_allRoles"    data-options="editable:false"  class="easyui-combobox" style="width:240px"   style="width:240px"  ></select></td>
			</tr>
			
<!-- 			<tr>
				<th>领域</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td>
				<th></th>
				<td></td>
			</tr> -->
				<input name="comment"  id="comment" type="hidden"/>
				<input name="department"  id="admin_yhgl_add_department" type="hidden"/>
				<input name="lab"  id="admin_yhgl_add_lab" type="hidden"/>
 				<input name="userTy"  id="admin_yhgl_add_userTy" type="hidden"/>				
			<tr>
<!-- 				<td><select name="allRoles"   id="allRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="but_left" type="button" onclick="toyhleft()"></input></td>
				<td><input name="button2" type="button" onclick="toyhright()"></input></td>

				<td><select name="selRoles" id="selRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
 -->			</tr>
		</table>
	</form>
</div>