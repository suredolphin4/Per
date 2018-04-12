<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>地理所绩效管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js_myselfv2/generatetable.js"></script>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/gray/easyui.css" type="text/css"></link>
<!-- <link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link> -->
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
</head>
<link rel="stylesheet" href="css/styles.css" type="text/css"></link>
</head>


<script type="text/javascript">
	$(function() {
		var $dg = $("#dg");
		$dg.datagrid({
			url : "servlet/list",
			width : 700,
			height : 250,
			columns : [ [ {
				field : 'code',
				title : 'Code',
				width : 100,
				editor : "validatebox"
			}, {
				field : 'name',
				title : 'Name',
				width : 200,
				editor : "validatebox"
			}, {
				field : 'price',
				title : 'Price',
				width : 200,
				align : 'right',
				editor : "numberbox"
			} ] ],
			toolbar : [ {
				text : "添加",
				iconCls : "icon-add",
				handler : function() {
					$dg.datagrid('appendRow', {});
					var rows = $dg.datagrid('getRows');
					$dg.datagrid('beginEdit', rows.length - 1);
				}
			}, {
				text : "编辑",
				iconCls : "icon-edit",
				handler : function() {
					var row = $dg.datagrid('getSelected');
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('beginEdit', rowIndex);
					}
				}
			}, {
				text : "删除",
				iconCls : "icon-remove",
				handler : function() {
					var row = $dg.datagrid('getSelected');
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('deleteRow', rowIndex);
					}
				}
			}, {
				text : "结束编辑",
				iconCls : "icon-cancel",
				handler :endEdit
			}, {
				text : "保存",
				iconCls : "icon-save",
				handler : function() {
					endEdit();
					if ($dg.datagrid('getChanges').length) {
						var inserted = $dg.datagrid('getChanges', "inserted");
						var deleted = $dg.datagrid('getChanges', "deleted");
						var updated = $dg.datagrid('getChanges', "updated");
						
						var effectRow = new Object();
						if (inserted.length) {
							effectRow["inserted"] = JSON.stringify(inserted);
						}
						if (deleted.length) {
							effectRow["deleted"] = JSON.stringify(deleted);
						}
						if (updated.length) {
							effectRow["updated"] = JSON.stringify(updated);
						}

						$.post("servlet/commit", effectRow, function(rsp) {
							if(rsp.status){
								$.messager.alert("提示", "提交成功！");
								$dg.datagrid('acceptChanges');
							}
						}, "JSON").error(function() {
							$.messager.alert("提示", "提交错误了！");
						});
					}
				}
			} ]
		});
		
		function endEdit(){
			var rows = $dg.datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$dg.datagrid('endEdit', i);
			}
		}
	});
</script>
<body>

	<div>
		<table id="dg" title="批量操作"></table>
	</div>


	<div>站点：<input class="easyui-combobox" width="200px" id="stnmCombo">&nbsp;
起始时间：<input id="StartTime" class="easyui-datebox"  editable="false" />
&nbsp; 结束时间：<input id="EndTime" class="easyui-datebox" editable="false" />
<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> &nbsp;
<a id="export" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
</div>

<div class="easyui-layout" fit="true">
    <table id="dg"></table>
</div> 
</body>
</html>
