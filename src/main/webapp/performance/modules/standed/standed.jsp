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

	<!-- 标准表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove', plain:true"
			onclick="removebzfunc()">删除</a> <a id="update"
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit', plain:true" onclick="editbzfunc()">修改/查看</a>
		<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-submit', plain:true"
			onclick="submitfunc()">提交</a> 
		<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo', plain:true"
			onclick="revokefunc()">撤回</a> 
		<a id="audit" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-audit', plain:true" onclick="auditfunc()">审核</a>
		<a id="bz_statusfilter" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-stutafilter', plain:true"
			onclick="advancedSearch()">高级查询</a> <input id="audit_filter"
			name="audit_filter" class="easyui-combobox"
			data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
		<a id="export" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-export', plain:true"
			onclick="exportfunc()">导出</a> <a id="refresh"
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload', plain:true"
			onclick="refreshfunc()">刷新</a>
	</div>

	<!-- 添加标准 -->
	<div id="admin_standed_addDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '增加标准',modal: true,buttons:'#add_bz_btn'"
		style="width:705px;height: 550px;padding-top:8px;padding-left:20px">
		<form id="admin_standed_addForm" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>标准信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>标准名称</th>
						<td colspan="3"><input name="title" class="easyui-textbox"
							data-options="required:true, prompt:'请填写标准名称', width:'515px'" /></td>
					</tr>
					<tr>
						<th>年度</th>
						<td><input id="year" name="year" class="easyui-combobox"
							data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'200px', editable:false" />
						</td>
						<th>标准编号</th>
						<td><input type="text" name="markid" id="markid"
							class="easyui-textbox"
							data-options="required:true, prompt:'请填写标准编号',width:'200px'" /></td>

					</tr>
					<tr>
						<th>颁布单位</th>
						<td><input type="text" name="auditdepart" id="auditdepart"
							class="easyui-textbox"
							data-options="required:true, prompt:'请填写颁布单位',width:'200px'" /></td>
						<th>标准类别</th>
						<td><select name="auditlevel" id="auditlevel"
							class="easyui-combobox"
							data-options="required:true, prompt:'请选择标准类别', width:'200px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国家标准' ,value:'国家标准'}, {label:'行业标准', value:'行业标准'}, {label:'省市以上地方标准', value:'省市以上地方标准'},{label:'其他' ,value:'其他'}]"></select></td>
					</tr>
					<tr>
					<tr>
						<th>批准日期</th>
						<td><input name="auditdate" id="bz_auditdate"
							class="easyui-textbox"
							data-options="required:true, prompt:'请选择批准日期',width:'200px'" /></td>
						<th>单位排序</th>
						<td><input name="departorder" id="departorder"
							class="easyui-combobox"
							data-options="required:true,prompt:'请选择单位排序', valueField:'id', textField:'text',width:'200px', editable:false" />
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>完成人</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>完成人1</th>
						<td><input name="firstauthor" id="bz_firstauthor"
							class="easyui-textbox"
							data-options="width:'200px', required:true" readonly="readonly"/></td>

						<th>完成人2</th>
						<td><input name="secauthor" id="bz_secauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>

					<tr>
						<th>完成人3</th>
						<td><input name="threeauthor" id="bz_threeauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>

						<th>完成人4</th>
						<td><input name="fourauthor" id="bz_fourauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>完成人5</th>
						<td><input name="fiveauthor" id="bz_fiveauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>

						<th>其他完成人</th>
						<td><input name="otherauthor" id="bz_otherauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>附件</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>附件</th>
						<td colspan="3"><input type="file" name="upload"
							id="bz_upload" /></td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="firstauthorcode" id="bz_firstauthorcode" />
			<input type="hidden" name="secauthorcode" id="bz_secauthorcode" /> <input
				type="hidden" name="threeauthorcode" id="bz_threeauthorcode" /> <input
				type="hidden" name="fourauthorcode" id="bz_fourauthorcode" /> <input
				type="hidden" name="fiveauthorcode" id="bz_fiveauthorcode" /> <input
				type="hidden" name="otherauthorcode" id="bz_otherauthorcode" /> <input
				type="hidden" name="auditopinion" id="bz_auditopinion" /> 
				<input type="hidden" name="examinestatus" id="auditstatus" />
				<input type="hidden" name="append" id="bz_add_append" />
			</table>
		</form>
	</div>
	<div id="add_bz_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addbz_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="addbz_btn_cancel()">取消</a>
	</div>

	<!-- 审核对话框 -->
	<div id="audit_bz_dialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '审核标准',modal: true,buttons:'#audit_bz_btn'"
		style="width:380px;height:330px;padding-top:20px;padding-left:20px">
		<form id="audit_bz_form" method="post">
			<!--   <table cellpadding="5">  -->
			<table class="altrowstable" >	
				<tr>					
					<td><input type="radio" id="bz_audit_pass" name="audit"
						value="pass" checked>审核通过</td>
				</tr>
				<tr>					
					<td><input type="radio" id="bz_audit_reject" name="audit"
						value="reject">驳回</td>
				</tr>
				<tr>					
					<td><input id="bz_audit_opinion" name="audit_opinion"
						class="easyui-textbox" type="text" data-options="multiline:true"
						style="height:100px;width:300px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="audit_bz_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="auditbz_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="auditbz_btn_cancel()">取消</a>
	</div>

	<!-- 编辑标准 -->
	<div id="admin_standed_editDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '编辑标准',modal: true,buttons:'#edit_bz_btn'"
		style="width:705px;height: 550px;padding-top:8px;padding-left:20px">
		<form id="admin_standed_editForm" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>标准信息</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>标准名称</th>
						<td colspan="3"><input name="title" id="e_bz_title"
							class="easyui-textbox"
							data-options="required:true, prompt:'请填写标准名称', width:'515px'" /></td>

					</tr>
					<tr>
						<th>年度</th>
						<td><input id="e_bz_year" name="year" class="easyui-combobox"
							data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'200px', editable:false" />
						</td>
						<th>标准编号</th>
						<td><input type="text" name="markid" id="e_bz_markid"
							class="easyui-textbox"
							data-options="required:true, prompt:'请填写标准编号',width:'200px'" /></td>

					</tr>
					<tr>
						<th>颁布单位</th>
						<td><input type="text" name="auditdepart"
							id="e_bz_auditdepart" class="easyui-textbox"
							data-options="required:true, prompt:'请填写颁布单位',width:'200px'" /></td>
						<th>标准类别</th>
						<td><select name="auditlevel" id="e_bz_auditlevel"
							class="easyui-combobox"
							data-options="required:true, prompt:'请选择标准类别', width:'200px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国家标准' ,value:'国家标准'}, {label:'行业标准', value:'行业标准'}, {label:'省市以上地方标准', value:'省市以上地方标准'},{label:'其他' ,value:'其他'}]"></select></td>
					</tr>
					<tr>
						<th>批准日期</th>
						<td><input name="auditdate" id="e_bz_auditdate"
							class="easyui-textbox"
							data-options="required:true, prompt:'请选择批准日期',width:'200px'" /></td>
						<th>单位排序</th>
						<td><input name="departorder" id="e_bz_departorder"
							class="easyui-combobox"
							data-options="required:true, prompt:'请选择单位排序',valueField:'id', textField:'text',width:'200px', editable:false" />
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>完成人</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>完成人1</th>
						<td><input name="firstauthor" id="e_bz_firstauthor"
							class="easyui-textbox"
							data-options="width:'200px', required:true" readonly="readonly"/></td>

						<th>完成人2</th>
						<td><input name="secauthor" id="e_bz_secauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>

					<tr>
						<th>完成人3</th>
						<td><input name="threeauthor" id="e_bz_threeauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>

						<th>完成人4</th>
						<td><input name="fourauthor" id="e_bz_fourauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>完成人5</th>
						<td><input name="fiveauthor" id="e_bz_fiveauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>

						<th>其他完成人</th>
						<td><input name="otherauthor" id="e_bz_otherauthor"
							class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>

					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>附件</legend>
				<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
					<tr>
						<th>附件</th>
						<td colspan="3"><input type="file" name="upload" id="e_bz_upload" /></td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="bzid" id="e_bz_bzid" /> <input
				type="hidden" name="firstauthorcode" id="e_bz_firstauthorcode" /> <input
				type="hidden" name="secauthorcode" id="e_bz_secauthorcode" /> <input
				type="hidden" name="threeauthorcode" id="e_bz_threeauthorcode" /> <input
				type="hidden" name="fourauthorcode" id="e_bz_fourauthorcode" /> <input
				type="hidden" name="fiveauthorcode" id="e_bz_fiveauthorcode" /> <input
				type="hidden" name="otherauthorcode" id="e_bz_otherauthorcode" /> <input
				type="hidden" name="append" id="bz_edit_append" /> <input
				type="hidden" name="examinestatus" id="e_bz_examinestatus" />
			</table>
		</form>
	</div>
	<div id="edit_bz_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="editbz_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editbz_btn_cancel()">取消</a>
	</div>
	<div id="import_bz" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入标准',modal: true,buttons:'#import_bz_btn'"
		style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_standed_importForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="bz_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#import_bz_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="editbz_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editbz_btn_cancel()">取消</a>
	</div>

	<!-- 论下载对话框  -->
	<div id="export_bz" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '标准导出',modal: true,buttons:'#export_bz_btn'"
		style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<form id="admin_standed_exportForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="bz_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#export_bz_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="exportbz_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="exportbz_btn_cancel()">取消</a>
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
				<th>标准名称</th>
				<td><input type="text" id="s_lw_name" name="s_lw_name"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
				<th>完成人</th>
				<td><input type="text" id="s_lw_author" name="s_lw_author"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			</tr>
			<tr>
				<th>完成人编号</th>
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
</body>
</html>
