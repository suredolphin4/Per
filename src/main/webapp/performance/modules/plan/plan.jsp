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




	<!-- 表工具栏 -->
	<div id="tb">
		<a id="insert" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
		<a id="del" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove', plain:true"
			onclick="removeghfunc()">删除</a> <a id="update"
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit', plain:true" onclick="editghfunc()">修改/查看</a>
		<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-submit', plain:true"
			onclick="submitfunc()">提交</a> 
		<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo', plain:true"
			onclick="revokefunc()">撤回</a>
			
		<a id="audit" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-audit', plain:true" onclick="auditfunc()">审核</a>
		<a id="zx_statusfilter" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-stutafilter', plain:true"
			onclick="advancedSearch()">高级查询</a> <input id="zx_audit_filter"
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


	<!-- 高级搜索 -->
	<div id="advancedSearch_dialog" class="easyui-dialog"
		data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
		style="width: 580px;height: 220px;padding-top:20px;padding-left:20px">
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>起始年度</th>
				<td><input id="s_zx_begin_year" name="s_begin_year"
					class="easyui-combobox"
					data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				<th>终止年度</th>
				<td><input id="s_zx_end_year" name="s_end_year"
					class="easyui-combobox"
					data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
			</tr>
			<tr>
				<th>规划题目</th>
				<td><input type="text" id="s_zx_name" name="s_zx_name"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
				<th>撰写人</th>
				<td><input type="text" id="s_zx_author" name="s_zx_author"
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

	<!-- 添加 -->
	<div id="admin_plan_addDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '增加规划',modal: true,buttons:'#add_gh_btn'"
		style="width:580px;height: 400px;padding-top:20px;padding-left:20px">
		<form id="admin_plan_addForm" method="post"
			enctype="multipart/form-data">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>规划题目</th>
					<td colspan="3"><input id="gh_title" name="title"
						class="easyui-textbox"
						data-options="required:true, prompt:'请填写规划题目', width:'410px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td colspan="1"><input id="gh_year" name="year"
						class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'150px', editable:false" />
					</td>
					<th>批准实施部门</th>
					<td colspan="1"><input type="text" name="auditdepart"
						id="gh_auditdepart" class="easyui-textbox"
						data-options="required:true, prompt:'请填写批准实施部门',width:'150px'" /></td>
				</tr>
				<tr>
					<th>批准日期</th>
					<td><input type="text" name="auditdate" id="gh_auditdate"
						class="easyui-textbox"
						data-options="required:true, prompt:'请选择批准日期',width:'150px'" /></td>

					<th>规划级别</th>
					<td colspan="1"><select name="auditlevel" id="gh_auditlevel"
						class="easyui-combobox"
						data-options="required:true, prompt:'请选择规划级别', width:'150px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国家级(发文实施)' ,value:'国家级(发文实施)'}, {label:'省部级(发文实施)', value:'省部级(发文实施)'}, {label:'地市级(发文实施)', value:'地市级(发文实施)'}]"></select></td>
				</tr>
				<tr>
					<th>撰写人1</th>
					<td colspan="1"><input name="firstauthor" id="gh_firstauthor"
						class="easyui-textbox" data-options="width:'150px', required:true" readonly="readonly"/>
					</td>
					<th>撰写人2</th>
					<td colspan="1"><input name="secauthor" id="gh_secauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人3</th>
					<td colspan="1"><input name="threeauthor" id="gh_threeauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人4</th>
					<td colspan="1"><input name="fourauthor" id="gh_fourauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人5</th>
					<td colspan="1"><input name="fiveauthor" id="gh_fiveauthor"
						class="easyui-textbox" data-options="width:'150px'"
						onclick="seachObjauthor()" readonly="readonly"/></td>
					<th>其他撰写人</th>
					<td colspan="1"><input name="otherauthor" id="gh_otherauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload"
						id="gh_upload" /></td>
				</tr>
				<input type="hidden" name="firstauthorcode" id="gh_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="gh_secauthorcode" />
				<input type="hidden" name="threeauthorcode" id="gh_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="gh_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="gh_fiveauthorcode" />
				<input type="hidden" name="otherauthorcode" id="gh_otherauthorcode" />
				<input type="hidden" name="examinestatus" id="auditstatus" />
				<input type="hidden" name="append" id="gh_add_append" />
			</table>
		</form>
	</div>
	<div id="add_gh_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addgh_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="addgh_btn_cancel()">取消</a>
	</div>

	<!-- 编辑 -->
	<div id="admin_plan_editDialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑规划',modal:true,buttons:'#edit_gh_btn'"
		style="width:580px;height: 400px;padding-top:20px;padding-left:20px">
		<form id="admin_plan_editForm" method="post"
			enctype="multipart/form-data">
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>规划题目</th>
					<td colspan="3"><input id="e_gh_title" name="title"
						class="easyui-textbox"
						data-options="required:true, prompt:'请填写规划题目',  width:'410px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td colspan="1"><input id="e_gh_year" name="year"
						class="easyui-combobox"
						data-options="valueField:'id', textField:'text', required:true,prompt:'请选择年度', width:'150px', editable:false" />
					</td>
					<th>批准实施部门</th>
					<td colspan="1"><input type="text" name="auditdepart"
						id="e_gh_auditdepart" class="easyui-textbox"
						data-options="required:true, prompt:'请填写批准实施部门', width:'150px'" /></td>
				</tr>
				<tr>
					<th>批准日期</th>
					<td colspan="1"><input type="text" name="auditdate"
						id="e_gh_auditdate" class="easyui-textbox"
						data-options="required:true, prompt:'请选择批准日期', width:'150px'" /></td>
					<th>规划级别</th>
					<td colspan="1"><select name="auditlevel" id="e_gh_auditlevel"
						class="easyui-combobox"
						data-options="required:true, prompt:'请选择规划级别', width:'150px',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国家级(发文实施)' ,value:'国家级(发文实施)'}, {label:'省部级(发文实施)', value:'省部级(发文实施)'}, {label:'地市级(发文实施)', value:'地市级(发文实施)'}]"></select></td>
				</tr>
				<tr>
					<th>撰写人1</th>
					<td colspan="1"><input name="firstauthor"
						id="e_gh_firstauthor" class="easyui-textbox"
						data-options="width:'150px', required:true" readonly="readonly"/></td>
					<th>撰写人2</th>
					<td colspan="1"><input name="secauthor" id="e_gh_secauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人3</th>
					<td colspan="1"><input name="threeauthor"
						id="e_gh_threeauthor" class="easyui-textbox"
						data-options="width:'150px'" readonly="readonly"/></td>
					<th>撰写人4</th>
					<td colspan="1"><input name="fourauthor" id="e_gh_fourauthor"
						class="easyui-textbox" data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>撰写人5</th>
					<td colspan="1"><input name="fiveauthor" id="e_gh_fiveauthor"
						class="easyui-textbox" data-options="width:'150px'"
						onclick="seachObjauthor()" readonly="readonly"/></td>
					<th>其他撰写人</th>
					<td colspan="1"><input name="otherauthor"
						id="e_gh_otherauthor" class="easyui-textbox"
						data-options="width:'150px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload"
						id="e_gh_upload"></td>
				</tr>

				<input type="hidden" name="ghid" id="e_ghid" />
				<input type="hidden" name="firstauthorcode"
					id="e_gh_firstauthorcode" />
				<input type="hidden" name="secauthorcode" id="e_gh_secauthorcode" />
				<input type="hidden" name="threeauthorcode"
					id="e_gh_threeauthorcode" />
				<input type="hidden" name="fourauthorcode" id="e_gh_fourauthorcode" />
				<input type="hidden" name="fiveauthorcode" id="e_gh_fiveauthorcode" />
				<input type="hidden" name="examinestatus" id="e_gh_examinestatus" />
				<input type="hidden" name="otherauthorcode" id="e_gh_otherauthorcode" />
				<input type="hidden" name="append" id="gh_edit_append" />
			</table>
		</form>
	</div>

	<div id="edit_gh_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="editgh_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editgh_btn_cancel()">取消</a>
	</div>


	<div id="audit_gh_dialog" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '审核规划',modal: true,buttons:'#audit_gh_btn'"
		style="width:380px;height:330px;padding-top:20px;padding-left:20px">
		<form id="audit_gh_form" method="post">
		<!-- 	<table cellpadding="5">     -->
		<table class="altrowstable" >	
				<tr>					
					<td><input type="radio" id="gh_audit_pass" name="audit"
						value="pass" checked>审核通过</td>
				</tr>
				<tr>
					<td><input type="radio" id="gh_audit_reject" name="audit"
						value="reject">驳回</td>
				</tr>
				<tr>
					<td><input id="gh_audit_opinion" name="audit_opinion"
						class="easyui-textbox" type="text" data-options="multiline:true"
						style="height:100px;width:300px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="audit_gh_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="auditgh_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="auditgh_btn_cancel()">取消</a>
	</div>
	<div id="import_gh" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入规划',modal: true,buttons:'#import_gh_btn'"
		style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_plan_importForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="gh_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#import_gh_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="editgh_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editgh_btn_cancel()">取消</a>
	</div>

	<div id="export_gh" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导出规划',modal: true,buttons:'#export_gh_btn'"
		style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<form id="admin_plan_exportForm" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="gh_import"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="#export_gh_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="exportgh_btn_ok()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="exportgh_btn_cancel()">取消</a>
	</div>

</body>
</html>

