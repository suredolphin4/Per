/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_lwstutas_datagrid').datagrid({
		url : $('#contextPath').val() + '/lwStutasAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'lwstutasid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'lwstutasid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'name',
			title : '论文类型名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'doi',
			title : 'doi',
			width : 150,

		}, {
			field : 'createdatetime',
			title : '创建时间',
			width : 150,
			sortable : true
		}, {
			field : 'modifydatetime',
			title : '最后修改时间',
			width : 150,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				append();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				remove();
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
			}
		}, '-', {
			text : '过滤',
			iconCls : 'icon-filter',
			handler : function() {
				alert("常规过滤");
			}
		}, '-', {
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_lwstutas_partBar').dialog('open');
			}
		}, '-', {
			text : '日期查询',
			iconCls : 'icon-datefilter',
			handler : function() {
				$('#admin_lwstutas_dateBar').dialog('open');
			}
		}, '-', {
			text : '状态查询',
			iconCls : 'icon-stutafilter',
			handler : function() {

				$('#admin_lwstutas_comBar').dialog('open');
			}
		}, '-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_lwstutas_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {

				$('#admin_lwstutas_export').dialog('open');
			}
		} ]

	});

	$('#dd2').datebox({
		onSelect : function(date) {
			alert(date.getFullYear() + ":" + (date.getMonth() + 1) + ":" + date.getDate());
		}
	});
};

/******************************************* Write Category Manage Operator ******************************************/
function searchFun() {
	$('#admin_lwstutas_datagrid').datagrid('load', serializeObject($('#admin_lwstutas_searchForm')));
}
function clearFun() {
	$('#admin_lwstutas_layout input[name=name]').val('');
	$('#admin_lwstutas_datagrid').datagrid('load', {});
}
function append() {

	$('#admin_lwstutas_addForm input').val('');
	$('#admin_lwstutas_addDialog').dialog('open');

}

function remove() {
	var rows = $('#admin_lwstutas_datagrid').datagrid('getChecked');
	console.info(rows);
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].lwstutasid);

				}
				$.ajax({
					url : $('#contextPath').val() + '/lwStutasAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_lwstutas_datagrid').datagrid('load');
						$('#admin_lwstutas_datagrid').datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}
				});
			}
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : '请勾选要删除的记录！'
		});
	}
}



function getyhris() {
	var ris = $('#selRoles').find("option");
	var rightarr = "";
	for (var k = 0; k < $('#selRoles').find("option").length; k++) {
		rightarr += ris[k].value + ",";

	}

	$("#comment").attr("value", rightarr);

}