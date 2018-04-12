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

<input type=hidden id="contextPath"
	value="${pageContext.request.contextPath}" />


<!-- 论文表工具栏 -->
<div id="tb">

	<a id="insert" href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
	<a id="del" href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove', plain:true"
		onclick="removefunc()">删除</a> <a id="update" href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改</a>
	<!-- 
		<a id="sub" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-submit', plain:true" onclick="submitfunc()">提交</a> 
		<a id="audit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-audit', plain:true" onclick="auditfunc()">审核</a>
		 -->
	<a id="rj_statusfilter" href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-stutafilter', plain:true"
		onclick="advancedSearch()">高级查询</a>
	<!-- 
		<input id="audit_filter" name="audit_filter" class="easyui-combobox"  data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
		 -->
	<a id="import" href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-impo', plain:true"
		onclick="importrjfunc()">导入</a> <a id="export"
		href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-export', plain:true"
		onclick="exportrjfunc()">导出</a> <a id="refresh"
		href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-reload', plain:true"
		onclick="refreshfunc()">刷新</a>
	<a id="delAll" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-removeAll', plain:true" onclick="removeAll()">清空数据</a>
		
</div>



<!-- 高级搜索 -->
<div id="advancedSearch_dialog" class="easyui-dialog"
	data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
	style="width: 580px;height: 220px;padding-top:20px;padding-left:20px">
	<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
		<tr>
			<th>起始年度</th>
			<td><input id="s_begin_year" name="s_begin_year"
				class="easyui-combobox"
				data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
			<th>终止年度</th>
			<td><input id="s_end_year" name="s_end_year"
				class="easyui-combobox"
				data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
		</tr>
		<tr>
			<th>名称</th>
			<td><input type="text" id="s_lw_name" name="s_lw_name"
				class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			<th>作者</th>
			<td><input type="text" id="s_lw_author" name="s_lw_author"
				class="f1 easyui-textbox" data-options="width:'150px'" /></td>
		</tr>
		<tr>
			<th>作者编号</th>
			<td><input type="text" id="s_lw_authorcode" name="s_lw_authorcode"
					   class="f1 easyui-textbox" data-options="width:'150px'" /></td>
		</tr>
	</table>
</div>
<div id="advanced_search_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
</div>


<div id="import_rj" class="easyui-dialog"
	data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入软件',modal: true,buttons:'#import_rj_btn'"
	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
	<form id="admin_soft_importForm" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<th>附件</th>
				<td><input type="file" name="upload" id="rj_import"></td>
			</tr>
		</table>
	</form>
</div>
<div id="#import_rj_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="editrj_btn_ok()">保存</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="editrj_btn_cancel()">取消</a>
</div>

<!-- 论下载对话框  -->
<div id="export_rj" class="easyui-dialog"
	data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '软件导出',modal: true,buttons:'#export_rj_btn'"
	style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
	<form id="admin_soft_exportForm" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<th>附件</th>
				<td><input type="file" name="upload" id="rj_import"></td>
			</tr>
		</table>
	</form>
</div>
<div id="#export_rj_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="exportrj_btn_ok()">保存</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="exportrj_btn_cancel()">取消</a>
</div>


<!-- 添加软件 -->
<div id="admin_software_addDialog" class="easyui-dialog"
	data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加软件',modal: true,buttons:'#add_lw_btn'"
	style="width:705px;height: 550px;padding-top:8px;padding-left:20px">
	<form id="admin_software_addForm" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<legend>软件信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>软件名称</th>
					<td colspan="3"><input id="add_title" name="title"
						class="easyui-textbox"
						data-options="required:true, prompt:'软件名称', width:'515px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td><input id="add_year" name="year" class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'200px', editable:false" />
					</td>
					<th>登记号</th>
					<td><input name="registernum" id="add_registernum"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>登记批准日期</th>
					<td><input name="auditdate" id="add_auditdate"
						class="easyui-textbox" data-options="width:'200px'" /></td>

					<th>著作权人</th>
					<td><input name="copyright" id="add_copyright"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>其他专利权人</th>
					<td><input name="othercopyright" id="add_othercopyright"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>单位排名</th>
					<td><input name="departorder" id="add_departorder"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>设计人</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>设计人1</th>
					<td><input name="firstauthor" id="rj_firstauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>设计人2</th>
					<td><input name="secauthor" id="rj_secauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>设计人3</th>
					<td><input name="threeauthor" id="rj_threeauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>设计人4</th>
					<td><input name="fourauthor" id="rj_fourauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>设计人5</th>
					<td><input name="fiveauthor" id="rj_fiveauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>其他设计人</th>
					<td><input name="otherauthor" id="rj_otherauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<input type="hidden" name="rjid" id="rjid" />
				<input type="hidden" name="firstauthorcode" id="rj_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="rj_secauthorcode" />
				<input type="hidden" name="threeauthorcode" id="rj_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="rj_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="rj_fiveauthorcode" />
				<input type="hidden" name="otherauthorcode" id="rj_otherauthorcode" />
			</table>
		</fieldset>
	</form>
</div>
<div id="add_lw_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="add_software_btn_ok()">保存</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="add_software_btn_cancel()">取消</a>
</div>

<!-- 编辑软件 -->
<div id="admin_software_editDialog" class="easyui-dialog"
	data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑软件',modal:true,buttons:'#edit_lw_btn'"
	style="width:705px;height: 550px;padding-top:8px;padding-left:20px">
	<form id="admin_software_editForm" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<legend>软件信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>软件名称</th>
					<td colspan="3"><input id="e_title" name="title" class="easyui-textbox"
						data-options="required:true, prompt:'软件名称', width:'515px'" /></td>

				</tr>
				<tr>
					<th>年度</th>
					<td><input id="e_year" name="year" class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'200px', editable:false" />
					</td>
					<th>登记号</th>
					<td><input name="registernum" id="e_registernum"
						class="easyui-textbox" data-options="width:'200px'" /></td>

				</tr>
				<tr>
					<th>登记批准日期</th>
					<td><input name="auditdate" id="e_rj_auditdate"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>著作权人</th>
					<td><input name="copyright" id="e_copyright"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>其他专利权人</th>
					<td><input name="othercopyright" id="e_othercopyright"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>单位排名</th>
					<td><input name="departorder" id="e_departorder"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>副主编</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>设计人1</th>
					<td><input name="firstauthor" id="e_rj_firstauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>设计人2</th>
					<td><input name="secauthor" id="e_rj_secauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>设计人3</th>
					<td><input name="threeauthor" id="e_rj_threeauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>设计人4</th>
					<td><input name="fourauthor" id="e_rj_fourauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<tr>
					<th>设计人5</th>
					<td><input name="fiveauthor" id="e_rj_fiveauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
					<th>其他设计人</th>
					<td><input name="otherauthor" id="e_rj_otherauthor"
						class="easyui-textbox" data-options="width:'200px'" /></td>
				</tr>
				<input type="hidden" name="rjid" id="e_rjid" />
				<input type="hidden" name="firstauthorcode" id="e_rj_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="e_rj_secauthorcode" />
				<input type="hidden" name="threeauthorcode" id="e_rj_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="e_rj_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="e_rj_fiveauthorcode" />
				<input type="hidden" name="otherauthorcode" id="e_rj_otherauthorcode" />
			</table>
		</fieldset>
	</form>
</div>
<div id="edit_lw_btn">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="edit_software_btn_ok()">保存</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="edit_software_btn_cancel()">取消</a>
</div>
</body>
</html>