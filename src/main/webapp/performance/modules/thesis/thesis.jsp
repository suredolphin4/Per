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
	
<input type=hidden id="contextPath" value="${pageContext.request.contextPath}" /> 

<%--<!-- import excel-->--%>
<%--<div id="admin_thesisimport" class="easyui-dialog"--%>
	<%--data-options="region:'center',closed:true,modal:true,title:'excel导入',buttons:[{--%>
			<%--text : 'excel导入',--%>
			<%--iconCls : 'icon-add',--%>
			<%--handler : function() {--%>
				<%--$('#admin_thesis_importForm').form('submit', {--%>
					<%--url : '${pageContext.request.contextPath}/thesisAction!upLoadExcel.action',--%>
					<%--success : function(r) {--%>
					 <%--$('#admin_thesis_import').dialog('close');--%>
					 <%--$('#center_table').datagrid('load');--%>
					<%--}--%>
				<%--});--%>
			<%--}--%>
		<%--}]"--%>
	<%--style="width: 500px;height:200px;" align="center">--%>
	<%--<form id="admin_thesis_importForm" method="post"--%>
		<%--enctype="multipart/form-data">--%>
		<%--<div style="margin-bottom:20px">--%>
			<%--<div>文件浏览:</div>--%>
			<%--<input class="easyui-filebox" name="append" id="append"--%>
				<%--data-options="prompt:'选择'" style="width:100%">--%>

			<%--<!--    <input type="file" id="excelPath" name="excelPath"/>&nbsp;&nbsp;   --%>
			<%--<input type="button"  value="导入Excel" onclick="importEmp()"/>  -->--%>
		<%--</div>--%>
	<%--</form>--%>
<%--</div>--%>

<!-- 刊物查询相关 -->
<div id="admin_thesis_addDialog_title" class="easyui-dialog"
	data-options="region:'north',title:'刊物查询',border:false,closed:true,buttons:'#pub_btn'"
	style="width:550px;height: 460px;padding-top:20px;padding-left:20px">
	<div>
		<label>刊物名称 </label>
	    <input name="title" id="s_title" /> 
	</div>
	<div>
		<label>刊物编号</label>  
		<input name="issn" id="s_issn" /> 
		<a id="pub_search_btn" class="easyui-linkbutton" onclick="searchTitleFunc()" data-options="iconCls:'icon-search',plain:true">查询</a>
	</div>
	<div id="admin_thesis_addDialog_titlediv" class="easyui-layout"
		data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"
			style="width:500px;height: 320px;">
			<table id="thesis_pubpart_datagrid"></table>
		</div>
	</div>
	
	<input id="s_status_marker" type="hidden" />
</div>
<div id="pub_btn" >
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="pub_btn_add()">添加</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="pub_btn_ok()">选择</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="pub_btn_cancel()">取消</a>
</div>
	
<!-- 论文表工具栏 -->
<div id="tb">
	<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="append()">增加</a>
	<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="removelwfunc()">删除</a>
	<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="editfunc()">修改/查看</a>
	<a id="sub" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-submit', plain:true" onclick="submitfunc()">提交</a>
	<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo', plain:true" onclick="revokefunc()">撤回</a>  
	<a id="audit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-audit', plain:true" onclick="auditfunc()">审核</a>
	<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="advancedSearch()">高级查询</a>
	<input id="audit_filter" name="audit_filter" class="easyui-combobox"  data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
	<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
	<a id="btnAllLwExport" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportAllExcel()">导出(含分值列)</a>
</div>

<!-- 高级搜索 -->
<div id="advancedSearch_dialog" class="easyui-dialog" data-options="region:'north',title:'高级检索',buttons:'#advanced_search_btn',border:false,closed:true,modal:true"
	style="width: 580px;height: 220px;padding-top:20px;padding-left:20px">
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>起始年度</th>
				<td><input id="s_begin_year" name="s_begin_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				<th>终止年度</th>
				<td><input id="s_end_year" name="s_end_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
			</tr>
			<tr>
				<th>论文名称</th>
				<td><input type="text" id="s_lw_name" name="s_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				<th>期刊名称</th>
				<td><input type="text" id="s_lw_pubname" name="s_lw_pubname" class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			</tr>
			<tr>
				<th>作者</th>
				<td><input type="text" id="s_lw_author" name="s_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				<th>作者编号</th>
				<td><input type="text" id="s_lw_authorcode" name="s_lw_authorcode" class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			</tr>
			<!-- 
			<tr>
			    <th>所属领域</th>
				<td><select id="s_which_domain" name="s_which_domain" class="easyui-combobox" data-options="width:'150px'"></select></td>
				<th>所属研究室</th>
				<td><select id="s_which_lab" name="s_which_lab" class="easyui-combobox" data-options="width:'150px'" ></select></td>
			</tr>
			 -->
		</table>
</div>
<div id="advanced_search_btn" >
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
</div>
	
<!-- 添加论文 -->
<div id="admin_thesis_addDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加论文',modal: true,buttons:'#add_lw_btn'"
 	style="width:705px;height:600px;padding-top:20px;padding-left:20px">
	<form id="admin_thesis_addForm" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>论文信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>论文标题</th>
					<td colspan="3"><input name="title" class="easyui-textbox" data-options="required:true, prompt:'请填写论文标题', width:'515px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td><input id="lw_year" name="year" class="easyui-combobox" data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'200px', editable:false" /></td>
					<th>单位排名</th>
					<td><input name="unit" id="unit" class="easyui-combobox" data-options="required:true, prompt:'请选择单位排名',valueField:'id', textField:'text',width:'200px', editable:false" /></td>
				</tr>
				<tr>
					<th>论文状态</th>
					<td><select name="status" id="lw_status" class="easyui-combobox" data-options="width:'200px',editable:false,required:true,prompt:'请选择论文状态'"></select></td>
					<th>doi号</th>
					<td><input name="doi" id="doi" class="easyui-textbox" data-options="width:'200px'" /></td>			
				</tr>
				<tr>
					<th>国际合作</th>
					<td><input name="interpartner" id="interpartner" class="easyui-combobox" data-options="width:'200px',required:true, prompt:'是否国际合作', editable:false, valueField: 'label',
						textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>所属期刊</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2">
				<tr>
					<th>刊物类别</th>
					<td><select name="pubkind" id="pubkind" class="easyui-combobox" data-options="required:true, prompt:'请选择刊物类别', width:'200px',editable:false"></select></td>
					<th>刊物名称</th>
					<td><input name="pubtitle" id="pubtitle" class="easyui-textbox" data-options="width:'200px',required:true, prompt:'请填写刊物名称' " /></td>
				</tr>
				<tr>
					<th>标准刊号</th>
					<td><input name="pubcode" id="pubcode" class="easyui-textbox"  data-options="width:'200px'" readonly="readonly" /></td>
					<th>小类分区</th>
					<td><input name="subclass" id="subclass" class="easyui-textbox" data-options="width:'200px'" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>影响因子</th>
					<td><input name="factor" id="factor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly" /></td>
					<th>顶级说明</th>
					<td><input name="topcomm" id="topcomm" class="easyui-textbox" data-options="width:'200px'" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>国内排名</th>
					<td><input name="pubranking" id="pubranking" class="easyui-textbox" data-options="width:'200px'" readonly="readonly" /></td>
					<th>国际期刊</th>
					<td><input name="inter" id="inter" class="easyui-combobox" data-options="width:'200px',required:true, prompt:'是否国际刊物',editable:false, valueField: 'label',
							textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" />	</td>
				</tr>
				<tr>
					<th>卷号</th>
					<td><input name="roll" id="roll" class="easyui-textbox" data-options="width:'200px',prompt:'请填写卷号'"/></td>
					<th>期号</th>
					<td><input name="expect" id="expect" class="easyui-textbox" data-options="width:'200px',prompt:'请填写期号'"/></td>
				</tr>
				<tr>
					<th>起止页</th>
					<td><input name="begin2end" id="begin2end" class="easyui-textbox" data-options="width:'200px',prompt:'请填写起止页'"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>作者信息</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>通讯作者</th>
					<td><input name="commauthor" id="commauthor" class="easyui-textbox" data-options="width:'200px'"  readonly="readonly"/></td>
					<th>第一作者</th>
					<td><input name="firstauthor" id="firstauthor" class="easyui-textbox" data-options="width:'200px', required:true" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>第二作者</th>
					<td><input name="secauthor" id="secauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					<th>第三作者</th>
					<td><input name="threeauthor" id="threeauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>第四作者</th>
					<td><input name="fourauthor" id="fourauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					<th>第五作者</th>
					<td><input name="fiveauthor" id="fiveauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>其他作者</th>
					<td><input name="otherauthor" id="otherauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>附件</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload" id="lw_upload"  /></td>
				</tr>
				
			</table>
		</fieldset>
			
		<input type="hidden" name="commauthorcode" id="commauthorcode" />
		<input type="hidden" name="firstauthorcode" id="firstauthorcode" />
		<input type="hidden" name="secauthorcode" id="secauthorcode" />
		<input type="hidden" name="threeauthorcode" id="threeauthorcode" />
		<input type="hidden" name="fourauthorcode" id="fourauthorcode" />
		<input type="hidden" name="fiveauthorcode" id="fiveauthorcode" />
		<input type="hidden" name="otherauthorcode" id="otherauthorcode" />
		<input type="hidden" name="examinestatus" id="auditstatus" />
		<input type="hidden" name="append" id="lw_add_append" />
	</form>
   </div>
   <div id="add_lw_btn" >
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addlw_btn_ok()">保存</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="addlw_btn_cancel()">取消</a>
   </div>
    
	<!-- 编辑论文 -->
	<div id="admin_thesis_editDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑论文',modal:true,buttons:'#edit_lw_btn'"
	 	style="width:705px;height: 600px;padding-top:20px;padding-left:20px">
	<form id="admin_thesis_editForm" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>论文信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>论文标题</th>
					<td colspan="3"><input id="e_lw_title" name="title" class="easyui-textbox" data-options="required:true, prompt:'填写论文标题', width:'515px'" /></td>
				</tr>
				<tr>
					<th>年度</th>
					<td><input id="e_lw_year" name="year" class="easyui-combobox" data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度', width:'200px', editable:false" /></td>
					<th>单位排名</th>
					<td><input name="unit" id="e_unit" class="easyui-combobox" data-options="required:true,prompt:'请选择单位排名', valueField:'id', textField:'text',width:'200px', editable:false" /></td>
				</tr>
				<tr>
					<th>论文状态</th>
					<td><select name="status" id="e_lw_status" class="easyui-combobox" data-options="width:'200px',editable:false,required:true,prompt:'请选择论文状态'"></select></td>
					<th>doi号</th>
					<td><input name="doi" id="e_lw_doi" class="easyui-textbox" data-options="width:'200px'" /></td>			
				</tr>
				<tr>
					<th>国际合作</th>
					<td><input name="interpartner" id="e_interpartner" class="easyui-combobox" data-options="width:'200px',required:true, prompt:'是否国际合作', editable:false, valueField: 'label',
						textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>所属期刊</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2">
				<tr>
					<th>刊物类别</th>
					<td><select name="pubkind" id="e_pubkind" class="easyui-combobox" data-options="required:true, prompt:'请选择刊物类别', width:'200px',editable:false"></select></td>
					<th>刊物名称</th>
					<td><input name="pubtitle" id="e_pubtitle" class="easyui-textbox" data-options="width:'200px',required:true, prompt:'请填写刊物名称' " /></td>
				</tr>
				<tr>
					<th>标准刊号</th>
					<td><input name="pubcode" id="e_pubcode" class="easyui-textbox"  data-options="width:'200px',readonly:true"/></td>
					<th>小类分区</th>
					<td><input name="subclass" id="e_subclass" class="easyui-textbox" data-options="width:'200px',readonly:true"/></td>
				</tr>
				<tr>
					<th>影响因子</th>
					<td><input name="factor" id="e_factor" class="easyui-textbox" data-options="width:'200px',readonly:true"/></td>
					<th>顶级说明</th>
					<td><input name="topcomm" id="e_topcomm" class="easyui-textbox" data-options="width:'200px',readonly:true"/></td>
				</tr>
				<tr>
					<th>国内排名</th>
					<td><input name="pubranking" id="e_pubranking" class="easyui-textbox" data-options="width:'200px',readonly:true"/></td>
					<th>国际期刊</th>
					<td><input name="inter" id="e_inter" class="easyui-combobox" data-options="width:'200px',required:true, prompt:'是否国际刊物',editable:false, valueField: 'label',
							textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" />	</td>
				</tr>
				<tr>
					<th>卷号</th>
					<td><input name="roll" id="e_roll" class="easyui-textbox" data-options="width:'200px',prompt:'请填写卷号'"/></td>
					<th>期号</th>
					<td><input name="expect" id="e_expect" class="easyui-textbox" data-options="width:'200px',prompt:'请填写期号'"/></td>
				</tr>
				<tr>
					<th>起止页</th>
					<td><input name="begin2end" id="e_begin2end" class="easyui-textbox" data-options="width:'200px',prompt:'请填写起止页'"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>作者信息</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>通讯作者</th>
					<td><input name="commauthor" id="e_commauthor" class="easyui-textbox" data-options="width:'200px'"  readonly="readonly"/></td>
					<th>第一作者</th>
					<td><input name="firstauthor" id="e_firstauthor" class="easyui-textbox" data-options="width:'200px', required:true" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>第二作者</th>
					<td><input name="secauthor" id="e_secauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					<th>第三作者</th>
					<td><input name="threeauthor" id="e_threeauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>第四作者</th>
					<td><input name="fourauthor" id="e_fourauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					<th>第五作者</th>
					<td><input name="fiveauthor" id="e_fiveauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>其他作者</th>
					<td><input name="otherauthor" id="e_otherauthor" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>附件</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>附件</th>
					<td colspan="3"><input type="file" name="upload" id="e_lw_upload"  /></td>
				</tr>
			</table>
		</fieldset>
		
		<input type="hidden" name="lwid" id="e_lwid" />
		<input type="hidden" name="commauthorcode" id="e_commauthorcode" />
		<input type="hidden" name="firstauthorcode" id="e_firstauthorcode" />
		<input type="hidden" name="secauthorcode" id="e_secauthorcode" />
		<input type="hidden" name="threeauthorcode" id="e_threeauthorcode" />
		<input type="hidden" name="fourauthorcode" id="e_fourauthorcode" />
		<input type="hidden" name="fiveauthorcode" id="e_fiveauthorcode" />
		<input type="hidden" name="otherauthorcode" id="e_otherauthorcode" />
		<input type="hidden" name="examinestatus" id="e_examinestatus" />
		<input type="hidden" name="append" id="lw_edit_append" />
	</form>
	</div>

	<div id="edit_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editlw_btn_cancel()">取消</a>
	</div>

	
	<div id="audit_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '论文审核',modal: true,buttons:'#audit_lw_btn'"
		style="width:380px;height:330px;padding-top:20px;padding-left:20px" >
		<form id="audit_form" method="post">
			<table class="altrowstable" >	
				<tr>					
					<td><input type="radio" id="audit_pass" name="audit" value="pass" checked >审核通过</td>
				</tr>
				<tr>
					<td><input type="radio" id="audit_reject" name="audit" value="reject" >驳回</td>
				</tr>
				<tr>				
					<td><input id="audit_opinion" name="audit_opinion" class="easyui-textbox" type="text" data-options="multiline:true" style="height:100px;width:300px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="audit_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="auditlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="auditlw_btn_cancel()">取消</a>
	</div>

	<!-- 论文excel导入-->
	<div id="import_lw" class="easyui-dialog"
	 data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入论文',modal: true,buttons:'#import_lw_btn'"
	 style="width:620px;height: 500px;padding-top:20px;padding-left:20px">
		<table>
			<tr>
				<th>附件</th>
				<td><input type="file" name="upload" id="lw_import"></td>
			</tr>
		</table>
	</div>
	<div id="#import_lw_btn" >
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editlw_btn_ok()">保存</a> 
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editlw_btn_cancel()">取消</a>
	</div>
	
	<!-- 添加期刊对话框-->
	<div id="admin_pubpart_addDialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加期刊',modal: true,buttons:'#add_pubpark_btn'"
	 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
		<form id="admin_pubpark_addForm" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>期刊名称</th>
				<td><input name="title" class="easyui-textbox"
					data-options="required:true, prompt:'填写期刊名称', width:'280px'" /></td>
			</tr>
			<tr>
				<th>issn</th>
				<td><input name="issn" class="easyui-textbox" id='pre_add_pubpart_issn'
					data-options="required:true, prompt:'填写issn', width:'280px'" />
				</td>
			</tr>
			<!--
			<tr>
				<th>影响因子</th>
				<td><input name="factor" class="easyui-textbox"
					data-options="prompt:'填写期刊影响因子', width:'280px'" />
				</td>
			</tr>
			 -->
			<input type="hidden" name="factor" id="add_factor"/>
			<input type="hidden" name="pubkind" id="add_pubkind"/>
			<input type="hidden" name="remark" id="add_remark" />
			<input type="hidden" name="tpid" id="add_tpid" />
			<input type="hidden" name="zone" id="add_zone" />
			<input type="hidden" name="topcomm" id="add_topcomm" />
			<input type="hidden" name="pubranking" id="add_pubranking" />
		</table>
		</form>
    </div>
    <div id="add_pubpark_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addpubpart_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="addpubpart_btn_cancel()">取消</a>
    </div>

</body>
</html>
