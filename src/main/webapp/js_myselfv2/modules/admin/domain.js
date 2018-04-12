/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_domain_datagrid').datagrid({
		url : $('#contextPath').val() + '/domainAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'domainid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'domainid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'name',
			title : '领域名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
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
				editfunc();
			}
		}, '-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				$('#admin_domain_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_domain_export').dialog('open');
			}
		} ]

	});

	$('#dd2').datebox({
		onSelect : function(date) {
			alert(date.getFullYear() + ":" + (date.getMonth() + 1) + ":" + date.getDate());
		}
	});
};

/******************************************* Domain Operator ******************************************/
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('domainAction!downloadExcel.action');
};

function searchFun() {
	$('#admin_domain_datagrid').datagrid('load', serializeObject($('#admin_domain_searchForm')));
}
function clearFun() {
	$('#admin_domain_layout input[name=name]').val('');
	$('#admin_domain_datagrid').datagrid('load', {});
}
function append() {

	$('#admin_domain_addForm input').val('');
	$('#admin_domain_addDialog').dialog('open');

}

function remove() {
	var rows = $('#admin_domain_datagrid').datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].domainid);

				}
				$.ajax({
					url : $('#contextPath').val() + '/domainAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_domain_datagrid').datagrid('load');
						$('#admin_domain_datagrid').datagrid('unselectAll');
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


//属性编辑操作
function editfunc() {
	var rows = $('#admin_domain_datagrid').datagrid('getChecked');
	// 只能编辑一行
	if (rows.length == 1) {
		// 回显
		$('#admin_domain_editDialog').dialog('open');
		$('#e_domain_domainid').val(rows[0].domainid);
		$('#e_domain_name').textbox('setValue', rows[0].name);
		//$('#e_domain_pubkind').combobox('setValue', rows[0].pubkind);
		
	
	}else{
		
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
	}
};



var editdt_btn_ok = function() {
	$('#admin_domain_editForm').form('submit', {
		url : $('#contextPath').val() + '/domainAction!edit.action',
		onSubmit : function(param) {
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				// 更新论文列表datagrid
				/* $('#admin_domain_datagrid').datagrid('updateRow',{
				 index:$('#admin_domain_datagrid').datagrid('getRowIndex',r[0].domainid),
				 row:objr.obj
				 });*/

				 $('#admin_domain_editDialog').dialog('close');
				// 重新加载论文列表
				$('#admin_domain_datagrid').datagrid('reload');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});

			$('#admin_domain_editDialog').dialog('close');
		}
	});
};

var editdt_btn_cancel = function() {
	$('#admin_domain_editDialog').dialog('close');
};