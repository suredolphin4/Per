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
	<a id="insert" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="award_append()">增加</a>
	<a id="del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="award_remove()">删除</a>
	<a id="update" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="award_edit()">修改/查看</a>
	<a id="sub" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-submit', plain:true" onclick="award_submit()">提交</a>
	<a id="revoke" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo', plain:true" onclick="award_revoke()">撤回</a> 
	<a id="audit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-audit', plain:true" onclick="award_audit()">审核</a>
	<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="advancedSearch()">高级查询</a>
	<input id="wt_audit_filter" name="audit_filter" class="easyui-combobox"  data-options="width:'100px', editable:false, valueField:'id', textField:'value', data:[{id:'全部', value:'全部', selected:true},{id:'已保存', value:'已保存'},{id:'已提交', value:'已提交'},{id:'审核通过', value:'审核通过'},{id:'已退回', value:'已退回'}]" />
	<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="award_import_excel()">导入</a>
    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="award_export_excel()">导出</a>
    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
</div>

<!-- Excel导入界面   -->
<div id="import_award_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入奖励',modal: true,buttons:'#import_award_btn'"
 	style="width:400px;height: 250px;padding-top:20px;padding-left:20px">
	<table>
		<tr>
		<th>附件</th>
		<td><input type="file" name="upload" id="award_import"></td>
		</tr>
	</table>
</div>
<div id="#import_award_btn" >
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="">保存</a> 
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="">取消</a>
</div>

<!-- Excel导出界面  -->

<!-- 增加奖励界面 -->
	<div id="award_append_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加奖励',modal: true,buttons:'#add_award_btn'"
	 	style="width:705px;height: 600px;padding-top:20px;padding-left:20px">
		<form id="award_append_form" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>奖励信息</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2">
					<tr>
						<th>奖励名称</th>
						<td colspan="3"><input id="awd_name" name="awardName" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写奖励名称'" /></td>
					</tr>
					<tr>
						<th>项目名称</th>
						<td colspan="3"><input id="awd_pro_name" name="awardProjectName" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写项目名称'" /></td>
					</tr>
					<tr>
						<th>完成单位</th>
						<td colspan="3"><input id="awd_unit" name="unit" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写完成单位'" /></td>
					</tr>
					<tr>
						<th>授奖部门</th>
						<td colspan="3"><input id="awd_department" name="department" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写授奖部门'" /></td>
					</tr>
					<tr>
						<th>年度</th>
						<td><input id="awd_year" name="year" class="easyui-combobox" data-options="valueField:'id', textField:'text',width:'200px',required:true,prompt:'请选择获奖年度',editable:false" /></td>
						<th>我所排名</th>
						<td><input id="awd_georank" name="geoRank" class="easyui-combobox" data-options="width:'200px',required:true,prompt:'请选择我所排名',editable:false,valueField:'id', textField:'text' "/></td>
					</tr>
					<tr>
						<th>奖励级别</th>
						<td><input id="awd_level" name="level" class="easyui-combobox" data-options="width:'200px',required:true,prompt:'请选择奖励级别',editable:false, valueField: 'label',
						textField: 'value', data:[{label:'国家' ,value:'国家'}, {label:'省部级', value:'省部级'}, {label:'其他', value:'其他'}]"  /></td>
						<th>奖励等级</th>
						<td><input id="awd_rank" name="rank" class="easyui-combobox" data-options="width:'200px',prompt:'请选择奖励等级',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'特等' ,value:'特等'}, {label:'一等', value:'一等'},  {label:'二等', value:'二等'}, 
					{label:'三等', value:'三等'}, {label:'其他', value:'其他'}]" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>获奖人</legend>
					<table class="altrowstable" cellspacing="0" cellpadding="2">
						<tr>
							<th>获奖人1</th>
							<td><input id="awdee_one" name="awardeeOne" class="easyui-textbox" data-options="width:'200px', required:true" readonly="readonly"/></td>
							<th>获奖人2</th>
							<td><input id="awdee_two" name="awardeeTwo" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>获奖人3</th>
							<td><input id="awdee_three" name="awardeeThree" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
							<th>获奖人4</th>
							<td><input id="awdee_four" name="awardeeFour" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>获奖人5</th>
							<td><input id="awdee_five" name="awardeeFive" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
							<th>获奖人6</th>
							<td><input id="awdee_six" name="awardeeSix" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>获奖人7</th>
							<td><input id="awdee_seven" name="awardeeSeven" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
							<th>获奖人8</th>
							<td><input id="awdee_eight" name="awardeeEight" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>获奖人9</th>
							<td><input id="awdee_nine" name="awardeeNine" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
							<th>获奖人10</th>
							<td><input id="awdee_ten" name="awardeeTen" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>其他获奖人</th>
							<td><input id="awdee_other" name="awardeeOther" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						</tr>
					</table>
			</fieldset>
			<fieldset>
				<legend>附件</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2">
					<tr>
						<th>附件</th>
						<td><input type="file" name="upload" id="awd_upload" /></td>
					</tr>
				</table>
			</fieldset>
			
			<!-- usercode -->
			<input type="hidden" id="awdee_onecode" name="awardeeCodeOne" />
			<input type="hidden" id="awdee_twocode" name="awardeeCodeTwo" />
			<input type="hidden" id="awdee_threecode" name="awardeeCodeThree" />
			<input type="hidden" id="awdee_fourcode" name="awardeeCodeFour" />
			<input type="hidden" id="awdee_fivecode" name="awardeeCodeFive" />
			<input type="hidden" id="awdee_sixcode" name="awardeeCodeSix" />
			<input type="hidden" id="awdee_sevencode" name="awardeeCodeSeven" />
			<input type="hidden" id="awdee_eightcode" name="awardeeCodeEight" />
			<input type="hidden" id="awdee_ninecode" name="awardeeCodeNine" />
			<input type="hidden" id="awdee_tencode" name="awardeeCodeTen" />
			<input type="hidden" id="awdee_othercode" name="awardeeCodeOther" />
			
			<!-- 其它属性 -->
			<input type="hidden" id="auditstatus" name="auditStatus" />
			<input type="hidden" id="awd_append" name="append" />
		</form>
    </div>
    <div id="add_award_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addaward_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="addaward_btn_cancel()">取消</a>
    </div>

<!-- 修改奖励界面 -->
	<div id="award_edit_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '编辑奖励',modal: true,buttons:'#edit_award_btn'"
	 	style="width:705px;height: 600px;padding-top:20px;padding-left:20px">
		<form id="award_edit_form" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>奖励信息</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2">
					<tr>
						<th>奖励名称</th>
						<td colspan="3"><input id="e_awd_name" name="awardName" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写奖励名称'" /></td>
					</tr>
					<tr>
						<th>项目名称</th>
						<td colspan="3"><input id="e_awd_pro_name" name="awardProjectName" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写项目名称'" /></td>
					</tr>
					<tr>
						<th>完成单位</th>
						<td colspan="3"><input id="e_awd_unit" name="unit" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写完成单位'" /></td>
					</tr>
					<tr>
						<th>授奖部门</th>
						<td colspan="3"><input id="e_awd_department" name="department" class="easyui-textbox" data-options="width:'515px', required:true,prompt:'请填写授奖部门'" /></td>
					</tr>
					<tr>
						<th>年度</th>
						<td><input id="e_awd_year" name="year" class="easyui-combobox" data-options="valueField:'id', textField:'text',width:'200px',required:true,prompt:'请选择获奖年度',editable:false" /></td>
						<th>我所排名</th>
						<td><input id="e_awd_georank" name="geoRank" class="easyui-combobox" data-options="width:'200px',required:true,prompt:'请选择我所排名',editable:false,valueField:'id', textField:'text'" /></td>
					</tr>
					<tr>
						<th>奖励级别</th>
						<td><input id="e_awd_level" name="level" class="easyui-combobox" data-options="width:'200px',required:true,prompt:'请选择奖励级别',editable:false, valueField: 'label',
						textField: 'value', data:[{label:'国家' ,value:'国家'}, {label:'省部级', value:'省部级'}, {label:'其他', value:'其他'}]" /></td>
						<th>奖励等级</th>
						<td><input id="e_awd_rank" name="rank" class="easyui-combobox" data-options="width:'200px',required:true,prompt:'请选择奖励等级',editable:false, valueField: 'label',
					textField: 'value', data:[{label:'特等' ,value:'特等'}, {label:'一等', value:'一等'},  {label:'二等', value:'二等'}, 
					{label:'三等', value:'三等'}, {label:'其他', value:'其他'}]" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>获奖人</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2" >
					<tr>
						<th>获奖人1</th>
						<td><input id="e_awdee_one" name="awardeeOne" class="easyui-textbox" data-options="width:'200px', required:true" readonly="readonly"/></td>
						<th>获奖人2</th>
						<td><input id="e_awdee_two" name="awardeeTwo" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>获奖人3</th>
						<td><input id="e_awdee_three" name="awardeeThree" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						<th>获奖人4</th>
						<td><input id="e_awdee_four" name="awardeeFour" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>获奖人5</th>
						<td><input id="e_awdee_five" name="awardeeFive" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						<th>获奖人6</th>
						<td><input id="e_awdee_six" name="awardeeSix" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>获奖人7</th>
						<td><input id="e_awdee_seven" name="awardeeSeven" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						<th>获奖人8</th>
						<td><input id="e_awdee_eight" name="awardeeEight" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>获奖人9</th>
						<td><input id="e_awdee_nine" name="awardeeNine" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
						<th>获奖人10</th>
						<td><input id="e_awdee_ten" name="awardeeTen" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>其他获奖人</th>
						<td><input id="e_awdee_other" name="awardeeOther" class="easyui-textbox" data-options="width:'200px'" readonly="readonly"/></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>附件</legend>
				<table class="altrowstable" cellspacing="0" cellpadding="2">
					<tr>
						<th>附件</th>
						<td><input type="file" id="e_awd_upload" name="upload"/></td>
					</tr>
				</table>
			</fieldset>
			
			<!-- id--> 
			<input type="hidden" id="e_awd_id" name="id" />
			
			<!-- usercode -->
			<input type="hidden" id="e_awdee_onecode" name="awardeeCodeOne" />
			<input type="hidden" id="e_awdee_twocode" name="awardeeCodeTwo" />
			<input type="hidden" id="e_awdee_threecode" name="awardeeCodeThree" />
			<input type="hidden" id="e_awdee_fourcode" name="awardeeCodeFour" />
			<input type="hidden" id="e_awdee_fivecode" name="awardeeCodeFive" />
			<input type="hidden" id="e_awdee_sixcode" name="awardeeCodeSix" />
			<input type="hidden" id="e_awdee_sevencode" name="awardeeCodeSeven" />
			<input type="hidden" id="e_awdee_eightcode" name="awardeeCodeEight" />
			<input type="hidden" id="e_awdee_ninecode" name="awardeeCodeNine" />
			<input type="hidden" id="e_awdee_tencode" name="awardeeCodeTen" />
			<input type="hidden" id="e_awdee_othercode" name="awardeeCodeOther" />
			
			<!-- 其它属性 -->
			<input type="hidden" name="auditStatus" id="e_awd_auditstatus" />
			<input type="hidden" id="e_awd_append" name="append" />
			
		</form>
    </div>
    <div id="edit_award_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editaward_btn_ok()">保存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editaward_btn_cancel()">取消</a>
    </div>

<!-- 著作审核界面 -->
<div id="awd_audit_dialog" class="easyui-dialog" data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加论文',modal: true,buttons:'#awd_audit_btn'"
	style="width:380px;height:330px;padding-top:20px;padding-left:20px" >
	<form id="awd_audit_form" method="post">
		<table class="altrowstable" >	
			<tr>				
				<td><input type="radio" id="awd_audit_pass" name="audit" value="pass" checked >审核通过</td>
			</tr>
			<tr>			
				<td><input type="radio" id="awd_audit_reject" name="audit" value="reject" >驳回</td>
			</tr>
			<tr>			
				<td><input id="awd_audit_opinion" name="audit_opinion" class="easyui-textbox" type="text" data-options="multiline:true" style="height:100px;width:300px"></td>
			</tr>
		</table>
	</form>
</div>
<div id="awd_audit_btn" >
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="auditaward_btn_ok()">保存</a> 
   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="auditaward_btn_cancel()">取消</a>
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
					<th>项目名称</th>
					<td><input type="text" id="s_lw_name" name="s_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th>获奖人</th>
					<td><input type="text" id="s_lw_author" name="s_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
					<th>获奖人编号</th>
					<td><input type="text" id="s_lw_authorcode" name="s_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
			</table>
	</div>
	<div id="advanced_search_btn" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="adv_search_btn_ok()">搜索</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adv_search_btn_cancel()">取消</a>
    </div>
</body>
</html>