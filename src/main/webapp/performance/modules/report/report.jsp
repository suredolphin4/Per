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

	<!-- 咨询报告表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove', plain:true"
			onclick="removezxfunc()">删除</a> <a id="update"
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改/查看</a>
		<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-submit', plain:true"
			onclick="submitfunc()">提交</a>
		<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo', plain:true"
			onclick="revokefunc()">撤回</a>  
		<a id="audit" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-audit', plain:true" onclick="auditfunc()">审核</a>
		<a id="lw_statusfilter" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-stutafilter', plain:true"
			onclick="advancedSearch()">高级查询</a> <input id="audit_filter"
			name="audit_filter" class="easyui-combobox"
			data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
		<a id="export" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-export', plain:true"
			onclick="exportzxfunc()">导出</a> <a id="refresh"
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload', plain:true"
			onclick="refreshfunc()">刷新</a>
	</div>

	<!-- 添加咨询报告 -->
	<div id="admin_report_addDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '增加咨询报告',modal: true,buttons:'#add_zx_btn'"
		style="width:580px;height: 420px;padding-top:20px;padding-left:20px">
		<form id="admin_report_addForm" method="post"
			enctype="multipart/form-data">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>咨询报告标题</th>
					<td colspan="3"><input name="title" class="easyui-textbox"
						data-options="required:true, prompt:'请填写咨询报告标题', width:'410px'" /></td>

				</tr>
				<tr>
					<th>年度</th>
					<td colspan="1"><input id="zx_year" name="year"
						class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'150px', editable:false" />
					</td>
					<th>批示领导</th>
					<td colspan="1"><input type="text" name="auditleader"
						id="auditleader" class="easyui-textbox"
						data-options="required:true,prompt:'请填写批示领导',width:'150px'" /></td>
				</tr>
				<tr>
					<th>批示部门</th>
					<td colspan="1"><input type="text" name="auditdepart"
						id="auditdepart" class="easyui-textbox"
						data-options="required:true, prompt:'请填写批示部门',width:'150px'" /></td>
					<th>批示部门级别</th>
					<td colspan="1"><select name="auditlevel" id="auditlevel"
						class="easyui-combobox"
						data-options="required:true, prompt:'请选择批示部门级别', width:'150px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'两办采纳' ,value:'两办采纳'}, {label:'总理以上批示', value:'总理以上批示'}, {label:'副总理以上批示', value:'副总理以上批示'},{label:'其他' ,value:'其他'}]"></select></td>

				</tr>
				<tr>
					<th>批示日期</th>
					<td colspan="1"><input name="auditdate" id="auditdate"
						class="easyui-textbox"
						data-options="required:true, prompt:'请选择批示日期',width:'150px'" /></td>

					<th>撰写人1</th>
					<td colspan="1"><input name="firstauthor" id="zx_firstauthor"
						class="easyui-textbox" data-options="width:'150px', required:true" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人2</th>
					<td colspan="1"><input name="secauthor" id="zx_secauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人3</th>
					<td colspan="1"><input name="threeauthor" id="zx_threeauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>


				</tr>
				<tr>
					<th>撰写人4</th>
					<td colspan="1"><input name="fourauthor" id="zx_fourauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人5</th>
					<td colspan="1"><input name="fiveauthor" id="zx_fiveauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>


				</tr>
				<tr>
					<th>其他撰写人</th>
					<td colspan="3"><input name="otherauthor" id="zx_otherauthor"
						class="easyui-textbox" data-options="width:'410px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload"
						id="zx_upload" /></td>
				</tr>
				<input type="hidden" name="firstauthorcode" id="zx_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="zx_secauthorcode" />
				<input type="hidden" name="threeauthorcode" id="zx_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="zx_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="zx_fiveauthorcode" />
				<input type="hidden" name="otherauthorcode" id="zx_otherauthorcode" />
				<input type="hidden" name="examinestatus" id="auditstatus" />
				<input type="hidden" name="append" id="zx_add_append" />
			</table>
		</form>
	</div>
	<div id="add_zx_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addzx_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="addzx_btn_cancel()">取消</a>
	</div>



	<!-- 审核界面 -->
	<div id="audit_zx_dialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '咨询报告审核',modal: true,buttons:'#audit_zx_btn'"
		style="width:380px;height:330px;padding-top:20px;padding-left:20px">
		<form id="audit_zx_form" method="post">
			<!--   <table cellpadding="5">  	 -->
			<table class="altrowstable" >	
				<tr>				
					<td colspan="3"><input type="radio" id="zx_audit_pass" name="audit"
						value="pass" checked>审核通过</td>
				</tr>
				<tr>					
					<td colspan="3"><input type="radio" id="zx_audit_reject" name="audit"
						value="reject">驳回</td>
				</tr>
				<tr>					
					<td colspan="3"><input id="zx_audit_opinion" name="audit_opinion"
						class="easyui-textbox" type="text" data-options="multiline:true"
						style="height:100px;width:300px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="audit_zx_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="auditzx_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="auditzx_btn_cancel()">取消</a>
	</div>

	<!-- 编辑咨询报告 -->
	<div id="admin_report_editDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑咨询报告',modal:true,buttons:'#edit_zx_btn'"
		style="width:580px;height: 420px;padding-top:20px;padding-left:20px">
		<form id="admin_report_editForm" method="post"
			enctype="multipart/form-data">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>咨询报告标题</th>
					<td colspan="3"><input id="e_zx_title" name="title"
						class="easyui-textbox"
						data-options="required:true, prompt:'请填写咨询报告标题', width:'410px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td colspan="1"><input id="e_zx_year" name="year"
						class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'150px', editable:false" />
					</td>
					<th>批示领导</th>
					<td colspan="1"><input type="text" name="auditleader"
						id="e_zx_auditleader" class="easyui-textbox"
						data-options="required:true,prompt:'请填写批示领导',width:'150px'" /></td>
				</tr>
				<tr>
					<th>批示部门</th>
					<td colspan="1"><input type="text" name="auditdepart"
						id="e_zx_auditdepart" class="easyui-textbox"
						data-options="required:true, prompt:'请填写批示部门',width:'150px'" /></td>
					<th>批示部门级别</th>
					<td colspan="1"><select name="auditlevel"
						id="e_zx_auditlevel" class="easyui-combobox"
						data-options="required:true, prompt:'请选择批示部门级别', width:'150px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'两办采纳' ,value:'两办采纳'}, {label:'总理以上批示', value:'总理以上批示'}, {label:'副总理以上批示', value:'副总理以上批示'},{label:'其他' ,value:'其他'}]"></select></td>
				</tr>
				<tr>
					<th>批示日期</th>
					<td colspan="1"><input name="auditdate" id="e_zx_auditdate"
						class="easyui-textbox" data-options="required:true, prompt:'请选择批示日期',width:'150px'" /></td>
					<th>撰写人1</th>
					<td colspan="1"><input name="firstauthor"
						id="e_zx_firstauthor" class="easyui-textbox"
						data-options="width:'150px', required:true" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人2</th>
					<td colspan="1"><input name="secauthor" id="e_zx_secauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人3</th>
					<td colspan="1"><input name="threeauthor"
						id="e_zx_threeauthor" class="easyui-textbox"
						data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人4</th>
					<td colspan="1"><input name="fourauthor" id="e_zx_fourauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人5</th>
					<td colspan="1"><input name="fiveauthor" id="e_zx_fiveauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>其他撰写人</th>
					<td colspan="3"><input name="otherauthor"
						id="e_zx_otherauthor" class="easyui-textbox"
						data-options="width:'410px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload"
						id="e_zx_upload" /></td>
				</tr>
				<input type="hidden" name="zxid" id="e_zx_zxid" />
				<input type="hidden" name="firstauthorcode"
					id="e_zx_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="e_zx_secauthorcode" />
				<input type="hidden" name="threeauthorcode"
					id="e_zx_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="e_zx_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="e_zx_fiveauthorcode" />
				<input type="hidden" name="otherauthorcode"
					id="e_zx_otherauthorcode" />
				<input type="hidden" name="examinestatus" id="e_zx_examinestatus" />
				<input type="hidden" name="append" id="zx_edit_append" />
			</table>
		</form>
	</div>

	<div id="edit_zx_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="edit_zx_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="edit_zx_btn_cancel()">取消</a>
	</div>


	<div id="import_zx" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入咨询报告',modal: true,buttons:'#import_zx_btn'"
		style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_report_importForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="zx_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#import_zx_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="editzx_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editzx_btn_cancel()">取消</a>
	</div>

	<div id="export_zx" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '附件导出',modal: true,buttons:'#export_zx_btn'"
		style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<form id="admin_report_exportForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="zx_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#export_zx_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="exportzx_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="exportzx_btn_cancel()">取消</a>
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
				<th>报告题目</th>
				<td><input type="text" id="s_lw_name" name="s_lw_name"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
				<th>撰写人</th>
				<td><input type="text" id="s_lw_author" name="s_lw_author"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			</tr>
			<tr>
				<th>撰写人编号</th>
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
