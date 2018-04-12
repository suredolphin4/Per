<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
<%
	response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
	response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0
%>
	<!-- datagrid -->
	<jsp:include page="../../../layout/center.jsp"></jsp:include>
	<jsp:include page="../../common/searchperson.jsp"></jsp:include>
	
	

	<!-- 论文表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="removefunc()">删除</a>
		<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改</a>
		<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="patentAdvancedSearch()">高级查询</a>
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
	    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
	    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
	    	    <a id="delAll" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-removeAll', plain:true" onclick="removeAll()">清空数据</a>
	    
	</div>

	
	<!-- 高级搜索 -->
	<div id="patentAdvancedSearch_dialog" class="easyui-dialog" data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
		style="width:580px;height: 220px;padding-top:20px;padding-left:20px">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>起始年度</th>
					<td><input id="patent_begin_year" name="patent_begin_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
					<th>终止年度</th>
					<td><input id="patent_end_year" name="patent_end_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				</tr>
				<tr>
					<th>专利名称</th>
					<td><input type="text" id="patent_lw_name" name="patent_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th>发明人</th>
					<td><input type="text" id="patent_lw_author" name="patent_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
					<th>发明人编号</th>
					<td><input type="text" id="patent_lw_authorcode" name="patent_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
			</table>
	</div>
	<div id="advanced_search_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
    </div>
	
	<!-- 添加专利-->
	<div id="admin_patent_addDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加专利',modal: true,buttons:'#add_lw_btn'"
	 	style="width:705px;height: 512px;padding-top:8px;padding-left:20px">
		<form id="admin_patent_addForm" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>专利信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>专利名称</th>
					<td colspan="3"><input id="add_name" name="name" class="easyui-textbox"
						data-options="required:true, prompt:'专利名称', width:'515px'" /></td>
				</tr>
				<tr>
					<th>授权号</th>
					<td colspan="3"><input name="code" id="add_code" class="easyui-textbox"  data-options="width:'515px'"/></td>
				</tr>
				<tr>
					<th>年度</th>
					<td><input id="add_year" name="year" class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'200px', editable:false" />
					</td>
					<th>授权日期</th>
					<td><input name="date" id="add_date" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>专利类别</th>
					<td><input name="type" id="add_type" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>专利权人</th>
					<td><input name="owner" id="add_owner" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>其他专利权</th>
					<td><input name="other" id="add_other" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>单位排名</th>
					<td><input name="rank" id="add_rank" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>发明人</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>发明人1</th>
					<td><input name="firstInvector" id="add_firstInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>发明人2</th>
					<td><input name="secondInvector" id="add_secondInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>发明人3</th>
					<td><input name="thirdInvector" id="add_thirdInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>发明人4</th>
					<td><input name="fourthInvector" id="add_fourthInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>发明人5</th>
					<td><input name="fifthInvector" id="add_fifthInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>其他发明人</th>
					<td><input name="otherInvector" id="add_otherInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
			</table>
		</fieldset>
		
		<input type="hidden" name="firstInvectorcode" id="add_firstInvectorcode">
		<input type="hidden" name="secondInvectorcode" id="add_secondInvectorcode">
		<input type="hidden" name="thirdInvectorcode" id="add_thirdInvectorcode">
		<input type="hidden" name="fourthInvectorcode" id="add_fourthInvectorcode">
		<input type="hidden" name="fifthInvectorcode" id="add_fifthInvectorcode">
		<input type="hidden" name="otherInvectorcode" id="add_otherInvectorcode">
		
		</form>
    </div>
    <div id="add_lw_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="add_patent_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="add_patent_btn_cancel()">取消</a>
    </div>
    
	<!-- 编辑专利 -->
	<div id="admin_patent_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑专利',modal:true,buttons:'#edit_lw_btn'"
	 	style="width:705px;height: 512px;padding-top:20px;padding-left:20px">
	<form id="admin_patent_editForm" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>专利信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>专利名称</th>
					<td colspan="3"><input id="e_name" name="name" class="easyui-textbox"
						data-options="required:true, prompt:'专利名称', width:'515px'" /></td>
				</tr>
				<tr>
					<th>授权号</th>
					<td colspan="3"><input name="code" id="e_code" class="easyui-textbox"  data-options="width:'515px'"/></td>
				</tr>
				<tr>
					<th>年度</th>
					<td><input id="e_year" name="year" class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'200px', editable:false" />
					</td>
					<th>授权日期</th>
					<td><input name="date" id="e_date" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>专利类别</th>
					<td><input name="type" id="e_type" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>专利权人</th>
					<td><input name="owner" id="e_owner" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>其他专利权</th>
					<td><input name="other" id="e_other" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>单位排名</th>
					<td><input name="rank" id="e_rank" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>发明人</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>发明人1</th>
					<td><input name="firstInvector" id="e_firstInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>发明人2</th>
					<td><input name="secondInvector" id="e_secondInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>发明人3</th>
					<td><input name="thirdInvector" id="e_thirdInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>发明人4</th>
					<td><input name="fourthInvector" id="e_fourthInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
				<tr>
					<th>发明人5</th>
					<td><input name="fifthInvector" id="e_fifthInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
					<th>其他发明人</th>
					<td><input name="otherInvector" id="e_otherInvector" class="easyui-textbox"  data-options="width:'200px'"/></td>
				</tr>
			</table>
		</fieldset>
		
		<input type="hidden" name="firstInvectorcode" id="e_firstInvectorcode">
		<input type="hidden" name="secondInvectorcode" id="e_secondInvectorcode">
		<input type="hidden" name="thirdInvectorcode" id="e_thirdInvectorcode">
		<input type="hidden" name="fourthInvectorcode" id="e_fourthInvectorcode">
		<input type="hidden" name="fifthInvectorcode" id="e_fifthInvectorcode">
		<input type="hidden" name="otherInvectorcode" id="e_otherInvectorcode">
		<input type="hidden" name="id" id="e_id" />
	</form>
	</div>

	<div id="edit_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="edit_patent_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit_patent_btn_cancel()">取消</a>
	</div>

	<div id="import_patent" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入专利',modal: true,buttons:'#import_lw_btn'"
	 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_thesis_importForm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
		<th>附件</th>
		<td><input type="file" name="upload" id="patent_import"></td>
		</tr>
	</table>
	</form>
	</div>
	<div id="#import_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editlw_btn_cancel()">取消</a>
	</div>

	<!-- 论下载对话框  -->
	<div id="export_lw" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '论文导出',modal: true,buttons:'#export_lw_btn'"
	 	style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<form id="admin_thesis_exportForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="patent_export"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#export_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="exportlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="exportlw_btn_cancel()">取消</a>
	</div>
	
</body>
</html>