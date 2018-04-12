<%@ page language="java" pageEncoding="UTF-8"%>

<div id="admin_pubpart_partBar" class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:450px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_pubpart_searchForm" method="post">
		期刊查询：<input name="title"  id="pubpart_search_title"  class="easyui-textbox" data-options="border:true" style="width:200px" /> 
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">重置</a>
	</form>
</div>
<div id="admin_pubpart_dateBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:550px;height: 100px;padding-top:20px;padding-left:20px">
	<form id="admin_yhgl_searchTimeForm" method="post">
		起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required"> 截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchdate();">查询</a> 
	</form>
</div>
<input type=hidden id="contextPath"
		value="${pageContext.request.contextPath}" />
<div id="admin_pubpart_comBar" class="easyui-dialog" data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_pubpart_searchstutasForm" method="post">
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
<div id="admin_pubpart_export" class="easyui-dialog" data-options="region:'center',title:'excel导出',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
	<form id="admin_pubpart_exportForm" method="post">
		<select class="easyui-combobox" name="state" style="width:150px;">
			<option value="0">导出已选记录</option>
			<option value="1">按过滤结果导出</option>
			<option value="2">全部导出</option>
		</select>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="export();">查询</a> 

	</form>
</div>

     
     
<div id="admin_pubpart_layout" class="easyui-layout" data-options="fit:true,border:false"   style="overflow: hidden;"  >
	<div data-options="fit:true,region:'center',border:false">
		<table id="admin_pubpart_datagrid"></table>
	</div>
</div>



<!-- import excel-->
	<div id="admin_pubpart_import" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'excel导入',buttons:[{
				text : 'excel导入',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_pubpart_importForm').form('submit', {
						url : '${pageContext.request.contextPath}/pubPartAction!upLoadExcel.action',
						data:{url:getPath($('#pubPart_import_url').filebox('getValue'))},
						success : function(result) {
						
						 $('#admin_pubpart_import').dialog('close');
						$('#admin_pubpart_datagrid').datagrid('reload');
						if(result!=''){
							var a = jQuery.parseJSON(result);
						if(a.success){
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
	<form id="admin_pubpart_importForm" method="post" enctype="multipart/form-data">
		   <div style="margin-bottom:20px">
            <div>文件浏览:</div>
            <input class="easyui-filebox" name="url" id="pubPart_import_url" data-options="prompt:'选择'" style="width:100%">
        </div>
	</form>
</div>
	
	
	
<!-- add -->

<div id="admin_pubpart_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加期刊',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
				submitPubPart();
					<%-- $('#admin_pubpart_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/pubPartAction!add.action',
						beforeSubmit:getyhris(),
						success : function(rb) {
							var obj = jQuery.parseJSON(rb);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_pubpart_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_pubpart_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						
							$('#admin_pubpart_addDialog').dialog('close');
						}
					}); --%>
				}
			}]"
	style="width:560px;height: 300px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_pubpart_addForm" method="post">
		<table  class="altrowstable" cellspacing=”0″ >
			<tr>
				<th>期刊名称</th>
				<td  colspan="1"><input name="title" id="title"   class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写期刊名称', width:'150px'"  /></td>
				<th>issn</th>
				<td colspan="1"><input name="issn" id="add_pubpart_issn"  class="easyui-textbox"      data-options="required:true,border:true, prompt:'请填写issn', width:'150px'"  />
				</td>
			</tr>
			<tr>
				<th>影响因子</th>
				<td colspan="1"><input name="factor"    id="factor"   class="easyui-numberbox"   max="9999"   precision="3"  maxlength="20"   data-options="border:true, prompt:'请填写当年影响因子', width:'150px'" /></td>
				<th>分区</th>
				<td colspan="1"><input name="zone"  id="zone"   class="easyui-numberbox"    max="9999"  maxlength="20"   data-options="border:true, prompt:'请填写分区', width:'150px'"  /></td>
			</tr>
			
			<tr>
				<th>期刊类别</th>
				<td colspan="1"><select name="pubkind" id="add_pubpart_pubkind" class="easyui-combobox"  data-options="required:true,border:true, prompt:'请选择期刊类别', width:'150px',editable:false"></select></td>
				<th>排名</th>
				<td colspan="1"><input name="pubranking"  id="pubranking"   class="easyui-textbox"  data-options="border:true, prompt:'请填写期刊排名', width:'150px'"  /></td>
			</tr>
			<tr>
				<th>顶级说明</th>
				<td colspan="1"><input name="topcomm"  id="topcomm"   class="easyui-textbox"  data-options="border:true, prompt:'请填写顶级说明', width:'150px'" /></td>
				<th>备注</th>
				<td colspan="1"><input name="remark"  id="remark"   class="easyui-textbox"  data-options="border:true, prompt:'请填写备注', width:'150px'" /></td>
			</tr>
			
		</table>
	</form>
</div>




<!-- 编辑 -->
<div id="admin_pubpart_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑期刊分区',modal:true,buttons:'#edit_dt_btn'" 
style="width:560px;height: 300px;padding-top:20px;padding-left:20px;padding-right:20px" align="center">
	<form id="admin_pubpart_editForm" method="post" enctype="multipart/form-data">
		
		<table class="altrowstable" cellspacing=”0″>
			<tr>
				<th>期刊名称</th>
				<td><input name="title" id="e_pubpart_title"   class="easyui-textbox" data-options="required:true,border:true, prompt:'请填写期刊名称', width:'150px'"  /></td>
				<th>issn</th>
				<td><input name="issn" id="e_pubpart_issn" class="easyui-textbox"       data-options="required:true,border:true, prompt:'请填写issn', width:'150px'"  />
				</td>
			</tr>
			<tr>
				<th>影响因子</th>
				<td><input name="factor"    id="e_pubpart_factor"  class="easyui-numberbox"   precision="3"  max="9999"  maxlength="20"   data-options="border:true, prompt:'请填写当年影响因子', width:'150px'" /></td>
				<th>小类分区</th>
				<td><input name="zone"  id="e_pubpart_zone"   class="easyui-numberbox"   max="9999"  maxlength="20" data-options="border:true, prompt:'请填写分区', width:'150px'"  /></td>
			</tr>
			<tr>
				<th>期刊类别</th>
					<td><select name="pubkind" id="e_pubpart_pubkind" class="easyui-combobox"  data-options="required:true,border:true, prompt:'请选择期刊类别', width:'150px',editable:false"></select></td>
				<th>排名</th>
				<td><input name="pubranking"  id="e_pubpart_pubranking"   class="easyui-textbox"  data-options="border:true, prompt:'请填写期刊排名', width:'150px'"  /></td>
			</tr>
			<tr>
				<th>顶级说明</th>
				<td><input name="topcomm"  id="e_pubpart_topcomm"   class="easyui-textbox"  data-options="border:true, prompt:'请填写顶级说明', width:'150px'" /></td>
				<th>备注</th>
				<td><input name="remark"  id="e_pubpart_remark"   class="easyui-textbox"  data-options="border:true, prompt:'请填写备注', width:'150px'" /></td>
			</tr>
			<tr>
				<input name="tpid" id="e_pubpart_tpid" type="hidden" />
			</tr>
		</table>
	</form>
</div>

<div id="edit_dt_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editdt_btn_ok()">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editdt_btn_cancel()">取消</a>
</div>