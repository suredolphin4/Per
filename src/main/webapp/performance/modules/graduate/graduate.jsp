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
		<a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="graduateAdvancedSearch()">高级查询</a>
		<a id="import" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-impo', plain:true" onclick="importfunc()">导入</a>
	    <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="exportfunc()">导出</a>
	    <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
		    <a id="delAll" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-removeAll', plain:true" onclick="removeAll()">清空数据</a>
	
	</div>

	
	<!-- 高级搜索 -->
	<div id="graduateAdvancedSearch_dialog" class="easyui-dialog" style="width: 580px;height: 220px;padding-top:20px;padding-left:20px"
		data-options="region:'north',title:'高级检索',border:false,closed:true,modal:true,
			buttons:[{
				text:'搜索',
				iconCls:'icon-ok',
				handler:function(){adv_search_btn_ok();}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){adv_search_btn_cancel();}
			}]"
		>
			<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
				<tr>
					<th style="width: 85px; ">起始年度</th>
					<td><input id="graduate_begin_year" name="graduate_begin_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
					<th>终止年度</th>
					<td><input id="graduate_end_year" name="graduate_end_year"  class="easyui-combobox" data-options="valueField:'id', textField:'text', width:'150px', editable:false" /></td>
				</tr>
				<tr>
					<th style="width: 44px; ">奖项</th>
					<td><input type="text" id="graduate_lw_name" name="graduate_lw_name"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
					<th style="width: 70px; ">参与者</th>
					<td><input type="text" id="graduate_lw_author" name="graduate_lw_author"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
				</tr>
				<tr>
					<th style="width: 70px; ">参与者编号</th>
					<td><input type="text" id="graduate_lw_authorcode" name="graduate_lw_authorcode"  class="f1 easyui-textbox" data-options="width:'150px'"/></td>
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
	
	<!-- 添加论文 -->
	<div id="admin_graduate_addDialog" class="easyui-dialog" style="width:580px;height: 240px;padding-top:20px;padding-left:20px" 
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '添加 奖项',modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:function(){add_graduate_btn_ok();}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){add_graduate_btn_cancel();}
			}]"
	 	>
		<form id="admin_graduate_addForm" method="post" enctype="multipart/form-data">
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>奖项</th>
				<td colspan="3">
					<input id="add_award" name="award" class="easyui-textbox" style="width:415px;"
					data-options="required:true, prompt:'填写奖项'" />
				</td>
			</tr>
			<tr>
				<th>获奖学生</th>
				<td colspan="1">
					<input name="student" id="add_student" class="easyui-textbox"  data-options="width:'150px'"/>
				</td>
				<th>教师</th>
				<td colspan="1">
					<input name="teacher" id="add_teacher" class="easyui-textbox"  data-options="width:'150px'"/>
				</td>
			</tr>
			<tr>
				<th>分值</th>
				<td colspan="1">
					<input name="score" id="add_score" class="easyui-textbox"  data-options="required:true, width:'150px'"/>
				</td>
				<th>年度</th>
				<td colspan="1">
					<input id="add_year" name="year" class="easyui-combobox"
					data-options="valueField:'id', textField:'text', required:true, prompt:'选择年份', width:'150px', editable:false" />
				</td>
			</tr>
			<input type="hidden" name="teacherCode" id="add_teachercode" />
			<input type="hidden" name="studentCode" id="add_studentcode" />
		</table>
		</form>
    </div>
    
	<!-- 编辑获奖情况 -->
	<div id="admin_graduate_editDialog" class="easyui-dialog" style="width:580px;height: 240px;padding-top:20px;padding-left:20px"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title:'编辑论文',modal:true,
		buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:function(){edit_graduate_btn_ok();}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){edit_graduate_btn_cancel();}
			}]"
	 	>
	<form id="admin_graduate_editForm" method="post" enctype="multipart/form-data">
	<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
		<tr>
			<th>奖项</th>
			<td colspan="3"><input id="e_award" name="award" class="easyui-textbox"
				data-options="required:true, width:'415px'" /></td>
		</tr>
		<tr>
			<th>获奖学生</th>
			<td colspan="1"><input id="e_student" name="student"class="easyui-textbox"  data-options="width:'150px'"/></td>
			<th>教师</th>
			<td colspan="1"><input id="e_teacher" name="teacher" class="easyui-textbox"  data-options="width:'150px'"/></td>
		</tr>
		<tr>
			<th>分值</th>
			<td><input id="e_score" name="score" class="easyui-textbox"  data-options="required:true, width:'150px'"/></td>
			<th>年度</th>
			<td><input id="e_year" name="year" class="easyui-combobox"
				data-options="valueField:'id', textField:'text', required:true, width:'150px', editable:true" />
			</td>
		</tr>
		<input type="hidden" name="teacherCode" id="e_teachercode" />
		<input type="hidden" name="studentCode" id="e_studentcode" />
		<input type="hidden" name="yjid" id="e_yjid" />
	</table>
	</form>
	</div>


	<div id="import_graduate_dilog" class="easyui-dialog" style="width:400px;height: 250px;padding-top:20px;padding-left:20px" 
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '导入研究生',modal: true">
		<form id="admin_thesis_importForm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
		<th>附件</th>
		<td><input type="file" name="upload" id="graduate_import"></td>
		</tr>
	</table>
	</form>
	</div>

	<!-- 论下载对话框  -->
	<div id="export_lw" class="easyui-dialog" style="width:400px;height: 300px;padding-top:20px;padding-left:20px" 
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '论文导出',modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:function(){exportlw_btn_ok();}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){exportlw_btn_cancel();}
			}]"
	 	>
		<form id="admin_thesis_exportForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>附件</th>
					<td><input type="file" name="upload" id="graduate_export"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>