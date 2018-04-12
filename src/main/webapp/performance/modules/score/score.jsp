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

	<!-- 科研成果统计表工具栏 -->
	<div id="tb">
		<a id="lw_statusfilter" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-stutafilter', plain:true"
			onclick="advancedSearch()">高级查询</a> 
		<a id="refresh" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload', plain:true"
			onclick="refreshfunc()">刷新</a>
		<a id="export" href="javascript:void(0)" class="easyui-linkbutton" 
			data-options="iconCls:'icon-export', plain:true" 
			onclick="exportfunc()">导出</a>
	</div>

	<div id="export_res_stat" class="easyui-dialog"
		data-options="region:'north',border:false, cache:false, closed:true,modal:true,title: '附件导出',modal: true,buttons:'#export_res_stat_btn'"
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
	<div id="#export_res_stat_btn">
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
				<th>姓名</th>
				<td><input type="text" id="s_lw_author" name="s_lw_author"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
				<th>领域</th>
				<td><input type="text" id="s_which_domain" name="s_which_domain"
					class="f1 easyui-textbox" data-options="width:'150px'" /></td>
			</tr>
			<tr>
				<th>姓名编号</th>
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
