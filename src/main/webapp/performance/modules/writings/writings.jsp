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
	<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="wt_append()">增加</a>
	<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="wt_remove()">删除</a>
	<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="wt_edit()">修改/查看</a>
	<a id="sub" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-submit', plain:true" onclick="wt_submit()">提交</a> 
	<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo', plain:true" onclick="wt_revoke()">撤回</a> 
	<a id="audit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-audit', plain:true" onclick="wt_audit()">审核</a>
	<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="advancedSearch()">高级查询</a>
	<input id="wt_audit_filter" name="audit_filter" class="easyui-combobox"  data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
	<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
</div>

<!-- 增加著作界面  -->
<div id="writing_add_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加著作',modal: true,buttons:'#add_writing_btn'"
	 	style="width:705px;height: 512px;padding-top:20px;padding-left:20px">
	<form id="writing_add_form" method="post">
		<fieldset>
			<legend>著作信息</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2" >
					<tr>
						<th>著作名称</th>
						<td colspan="3"><input class="easyui-textbox" name="title" id="wt_title" data-options="width:'515px',required:true,prompt:'请填写著作名称'"/></td>
					</tr>
					<tr>
						<th>出版社</th>
						<td colspan="3"><input class="easyui-textbox" name="press" id="wt_press" data-options="width:'515px',required:true,prompt:'请填写出版社名称'"/></td>
					</tr>
					<tr>
						<th>出版类别</th>
						<td><input class="easyui-combobox" name="pubKind" id="wt_pub_kind" data-options="width:'200px',required:true, prompt:'请选择出版类别',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国内出版-中文' ,value:'国内出版-中文'}, 
					{label:'国内出版-外文', value:'国内出版-外文'},
					 {label:'国外出版-外文', value:'国外出版-外文'},
					 {label:'国外出版-中文', value:'国外出版-中文'}]" /></td>
					 	<th>著作类别</th>
						<td><input class="easyui-combobox" name="writingKind" id="wt_writing_kind" data-options="width:'200px',required:true, prompt:'请选择著作类别',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'编著' ,value:'编著'}, {label:'专著', value:'专著'}, 
					{label:'译著', value:'译著'}, {label:'其他', value:'其他'}]" /></td>
					</tr>
					<tr>
						<th>国际合作</th>
						<td><input class="easyui-combobox" name="interCooperate" id="wt_inter" data-options="width:'200px',required:true, prompt:'是否国际合作', editable:false, valueField: 'label',
					textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" /></td>
						<th>年度</th>
						<td><input class="easyui-combobox" name="year" id="wt_year" data-options="valueField:'id', textField:'text', required:true,prompt:'请选择年度', editable:false, width:'200px'" /></td>
					</tr>
					<tr>
						<th>总字数</th>
						<td><input class="easyui-numberbox" name="wordCount" id="wt_word_count" data-options="required:true, prompt:'请填写总字数(万)', width:'200px',min:0,precision:2,missingMessage:'必须填写正数'" /></td>
						<th>ISBN</th>
						<td><input class="easyui-textbox" name="isbn" id="wt_isbn" data-options="width:'200px',required:true,prompt:'请填写ISBN'" /> </td>
					</tr>
				</table>
		</fieldset>
		
		<fieldset>
			<legend>主编</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>主编一</th>
					<td><input class="easyui-textbox" name="first_editor" id="wt_first_editor" data-options="width:'200px', required:true" readonly="readonly"></td>
					<th>主编二</th>
					<td><input class="easyui-textbox" name="second_editor" id="wt_second_editor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
				<tr>
					<th>主编三</th>
					<td><input class="easyui-textbox" name="third_editor" id="wt_third_editor" data-options="width:'200px'" readonly="readonly"></td>
					<th>其他主编</th>
					<td><input class="easyui-textbox" name="other_editor" id="wt_other_editor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>副主编</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2" >
				<tr>
					<th>副主编一</th>
					<td><input class="easyui-textbox" name="first_subeditor" id="wt_first_subeditor" data-options="width:'200px'" readonly="readonly"></td>
					<th>副主编二</th>
					<td><input class="easyui-textbox" name="second_subeditor" id="wt_second_subeditor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
				<tr>
					<th>副主编三</th>
					<td><input class="easyui-textbox" name="third_subeditor" id="wt_third_subeditor" data-options="width:'200px'" readonly="readonly"></td>
					<th>其他副主编</th>
					<td><input class="easyui-textbox" name="other_subeditor" id="wt_other_subeditor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>作者</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2">
				<tr>
					<th>作者一</th>
					<td><input class="easyui-textbox" name="first_author" id="wt_first_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>作者二</th>
					<td><input class="easyui-textbox" name="second_author" id="wt_second_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>作者三</th>
					<td><input class="easyui-textbox" name="third_author" id="wt_third_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>作者四</th>
					<td><input class="easyui-textbox" name="fourth_author" id="wt_fourth_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>作者五</th>
					<td><input class="easyui-textbox" name="fifth_author" id="wt_fifth_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>其他作者</th>
					<td><input class="easyui-textbox" name="other_author" id="wt_other_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
			</table>	
		</fieldset>
		
		<fieldset>
			<legend>附件</legend>
			<table class="altrowstable" cellspacing="0" cellpadding="2">
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="wt_upload" /></td>
				</tr>
			</table>
		</fieldset>
		
		<input type="hidden" name="first_editor_code" id="wt_first_editorcode" />
		<input type="hidden" name="second_editor_code" id="wt_second_editorcode" />
		<input type="hidden" name="third_editor_code" id="wt_third_editorcode" />
		<input type="hidden" name="other_editor_code" id="wt_other_editorcode" />
		<input type="hidden" name="first_subeditor_code" id="wt_first_subeditorcode" />
		<input type="hidden" name="second_subeditor_code" id="wt_second_subeditorcode" />
		<input type="hidden" name="third_subeditor_code" id="wt_third_subeditorcode" />
		<input type="hidden" name="other_subeditor_code" id="wt_other_subeditorcode" />
		<input type="hidden" name="first_author_code" id="wt_first_authorcode" />
		<input type="hidden" name="second_author_code" id="wt_second_authorcode" />
		<input type="hidden" name="third_author_code" id="wt_third_authorcode" />
		<input type="hidden" name="fourth_author_code" id="wt_fourth_authorcode" />
		<input type="hidden" name="fifth_author_code" id="wt_fifth_authorcode" />
		<input type="hidden" name="other_author_code" id="wt_other_authorcode" />
		
		<input type="hidden" name="audit_status" id="auditstatus" />
		<input type="hidden" name="append" id="wt_append" />
	</form>
</div>
<div id="add_writing_btn" >
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addwriting_btn_ok()">保存</a> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="addwriting_btn_cancel()">取消</a>
</div>

<!-- 编辑著作界面  -->
<div id="writing_edit_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '编辑著作',modal: true,buttons:'#edit_writing_btn'"
	 	style="width:705px;height:500px;padding-top:20px;padding-left:20px">
	<form id="writing_edit_form" method="post">
		<fieldset>
			<legend>著作信息</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>著作名称</th>
					<td colspan="3"><input class="easyui-textbox" name="title" id="e_wt_title" data-options="width:'515px',required:true,prompt:'请填写著作名称'"/></td>
				</tr>
				<tr>
					<th>出版社</th>
					<td colspan="3"><input class="easyui-textbox" name="press" id="e_wt_press" data-options="width:'515px',required:true,prompt:'请填写出版社名称'"/></td>
				</tr>
				<tr>
					<th>出版类别</th>
					<td><input class="easyui-combobox" name="pubKind" id="e_wt_pub_kind" data-options="width:'200px',required:true, prompt:'请选择出版类别',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'国内出版-中文' ,value:'国内出版-中文'}, 
					{label:'国内出版-外文', value:'国内出版-外文'},
					 {label:'国外出版-外文', value:'国外出版-外文'},
					 {label:'国外出版-中文', value:'国外出版-中文'}]" /></td>
					<th>著作类别</th>
					<td><input class="easyui-combobox" name="writingKind" id="e_wt_writing_kind" data-options="width:'200px',required:true, prompt:'请选择著作类别',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'编著' ,value:'编著'}, {label:'专著', value:'专著'}, 
					{label:'译著', value:'译著'}, {label:'其他', value:'其他'}]" /></td>
				</tr>
				<tr>
					<th>国际合作</th>
					<td><input class="easyui-combobox" name="interCooperate" id="e_wt_inter" data-options="width:'200px',required:true, prompt:'是否国际合作', editable:false, valueField: 'label',
					textField: 'value', data:[{label:'是' ,value:'是'}, {label:'否', value:'否'}]" /></td>
					<th>年度</th>
					<td><input class="easyui-combobox" name="year" id="e_wt_year" data-options="valueField:'id', textField:'text', required:true, prompt:'请选择年度',editable:false, width:'200px'" /></td>
				</tr>
				<tr>
					<th>总字数</th>
					<td><input class="easyui-numberbox" name="wordCount" id="e_wt_word_count" data-options="required:true,prompt:'请填写总字数(万)',width:'200px',min:0,precision:2,missingMessage:'必须填写正数'" /></td>
					<th>ISBN</th>
					<td><input class="easyui-textbox" name="isbn" id="e_wt_isbn" data-options="width:'200px',required:true,prompt:'请填写ISBN'" /> </td>
				</tr>
			</table>			
		</fieldset>
		<fieldset>
			<legend>主编</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>主编一</th>
					<td><input class="easyui-textbox" name="first_editor" id="e_wt_first_editor" data-options="width:'200px', required:true" readonly="readonly"></td>
					<th>主编二</th>
					<td><input class="easyui-textbox" name="second_editor" id="e_wt_second_editor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
				<tr>
					<th>主编三</th>
					<td><input class="easyui-textbox" name="third_editor" id="e_wt_third_editor" data-options="width:'200px'" readonly="readonly"></td>
					<th>其他主编</th>
					<td><input class="easyui-textbox" name="other_editor" id="e_wt_other_editor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>副主编</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>副主编一</th>
					<td><input class="easyui-textbox" name="first_subeditor" id="e_wt_first_subeditor" data-options="width:'200px'" readonly="readonly"></td>
					<th>副主编二</th>
					<td><input class="easyui-textbox" name="second_subeditor" id="e_wt_second_subeditor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
				<tr>
					<th>副主编三</th>
					<td><input class="easyui-textbox" name="third_subeditor" id="e_wt_third_subeditor" data-options="width:'200px'" readonly="readonly"></td>
					<th>其他副主编</th>
					<td><input class="easyui-textbox" name="other_subeditor" id="e_wt_other_subeditor" data-options="width:'200px'" readonly="readonly"></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>作者</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>作者一</th>
					<td><input class="easyui-textbox" name="first_author" id="e_wt_first_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>作者二</th>
					<td><input class="easyui-textbox" name="second_author" id="e_wt_second_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>作者三</th>
					<td><input class="easyui-textbox" name="third_author" id="e_wt_third_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>作者四</th>
					<td><input class="easyui-textbox" name="fourth_author" id="e_wt_fourth_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>作者五</th>
					<td><input class="easyui-textbox" name="fifth_author" id="e_wt_fifth_author" data-options="width:'200px'" readonly="readonly"/></td>
					<th>其他作者</th>
					<td><input class="easyui-textbox" name="other_author" id="e_wt_other_author" data-options="width:'200px'" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>附件</legend>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="e_wt_upload" /></td>
				</tr>
			</table>
		</fieldset>
		
		<!-- id--> 
		<input type="hidden" name="id" id="e_wt_id"  />
		
		<input type="hidden" name="first_editor_code" id="e_wt_first_editorcode" />
		<input type="hidden" name="second_editor_code" id="e_wt_second_editorcode" />
		<input type="hidden" name="third_editor_code" id="e_wt_third_editorcode" />
		<input type="hidden" name="other_editor_code" id="e_wt_other_editorcode" />
		<input type="hidden" name="first_subeditor_code" id="e_wt_first_subeditorcode" />
		<input type="hidden" name="second_subeditor_code" id="e_wt_second_subeditorcode" />
		<input type="hidden" name="third_subeditor_code" id="e_wt_third_subeditorcode" />
		<input type="hidden" name="other_subeditor_code" id="e_wt_other_subeditorcode" />
		<input type="hidden" name="first_author_code" id="e_wt_first_authorcode" />
		<input type="hidden" name="second_author_code" id="e_wt_second_authorcode" />
		<input type="hidden" name="third_author_code" id="e_wt_third_authorcode" />
		<input type="hidden" name="fourth_author_code" id="e_wt_fourth_authorcode" />
		<input type="hidden" name="fifth_author_code" id="e_wt_fifth_authorcode" />
		<input type="hidden" name="other_author_code" id="e_wt_other_authorcode" />
		
		<input type="hidden" name="audit_status" id="e_wt_audit_status"/>
		<input type="hidden" name="append" id="e_wt_append" />
		
	</form>
</div>
<div id="edit_writing_btn" >
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="edit_writing_btn_ok()">保存</a> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit_writing_btn_cancel()">取消</a>
</div>

<!-- Excel导入界面   -->
<div id="import_writing_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入著作',modal: true,buttons:'#import_writing_btn'"
 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
	<table>
		<tr>
		<th>附件</th>
		<td><input type="file" name="upload" id="writing_import"></td>
		</tr>
	</table>
</div>
<div id="#import_writing_btn" >
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="">保存</a> 
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="">取消</a>
</div>

<!-- Excel导出界面   -->

<!-- 著作审核界面 -->
<div id="wt_audit_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加论文',modal: true,buttons:'#wt_audit_lw_btn'"
	style="width:380px;height:330px;padding-top:20px;padding-left:20px" >
	<form id="wt_audit_form" method="post">
		<table class="altrowstable" >	
			<tr>			
				<td><input type="radio" id="wt_audit_pass" name="audit" value="pass" checked >审核通过</td>
			</tr>
			<tr>				
				<td><input type="radio" id="wt_audit_reject" name="audit" value="reject" >驳回</td>
			</tr>
			<tr>				
				<td><input id="wt_audit_opinion" name="audit_opinion" class="easyui-textbox" type="text" data-options="multiline:true" style="height:100px;width:300px"></td>
			</tr>
		</table>
	</form>
</div>
<div id="wt_audit_lw_btn" >
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="auditwt_btn_ok()">保存</a> 
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="auditwt_btn_cancel()">取消</a>
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
					<th>著作名称</th>
					<td><input type="text" id="s_lw_name" name="s_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th>参与人</th>
					<td><input type="text" id="s_lw_author" name="s_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
					<th>参与人编号</th>
					<td><input type="text" id="s_lw_authorcode" name="s_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
			</table>
	</div>
	<div id="advanced_search_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
    </div>
    
<!-- 著作其它人员添加界面 -->
<div id="writing_otherauthor_dialog" class="easyui-dialog"
	data-options="region:'north',title:'人员查询',buttons:'#wt_otherauthor_btn',border:false,closed:true,modal:true"
	style="width:900px;height: 500px;padding-top:20px;padding-left:20px">
	<table>
			<tr>
				<th>本所人员：</th>
				<td><input type="text" class="easyui-textbox" name="other_author_name" id="wt_otherperson_name" data-options="width:'150px'" /></td>
				<th></th>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true"
					onclick="wtGetOtherPersonList();">搜索</a></td>
			</tr>
			<tr>
				<th>非本所人员：</th>
				<td><input type="text" class="easyui-textbox" name="outsider_other" id="wt_outsider_other" data-options="width:'150px'" /></td>
			</tr>
	</table>

	<div  data-options="fit:true,border:true">
		<div style="float:left;width:400px;height:340px;">
			<table id="writing_otherperson_datagrid" class="easyui-datagrid">
			</table>
		</div>
		<div style="float:left;width:120px;height:340px;text-align:center;">
			<div>
				<input type="button" id="otherperson_add2list" value="-->" class="easyui-linkbutton" style="width:50px;" onclick="otherperson_btn_add()"/>
				<br/>
				<br/>
				<input type="button" id="otherperson_removefromlist" value="<--" class="easyui-linkbutton" style="width:50px;" onclick="otherperson_btn_remove()"/>
			</div>
		</div>
		<div style="float:left;width:300px;height:340px;">
			<table id="writing_otherperson_result_datagrid" class="easyui-datagrid"  style="width:300px;height:340px"
			title="人员选择结果列表" iconCls="icon-edit" singleSelect="true" fitColumns="true">
        		<thead>
			        <tr>
			            <th field="personname" width="150">人员名称</th>
			            <th field="personcode" width="70">人员编号</th>
			            <th field="personwc" width="80" align="right" editor="{type:'numberbox',options:{precision:2}}">字数(万)</th>
			        </tr>
			    </thead>
			</table>
		</div>
	</div>
</div>
<div id="wt_otherauthor_btn" >
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="otherperson_btn_confirm()">确认</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="otherperson_btn_cancel()">取消</a>
</div>
</body>
</html>