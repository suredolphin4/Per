/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_lwstutas_datagrid').datagrid({
		url : $('#contextPath').val() + '/lwStutasAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'lwstutasid',
		pageSize : 20,
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
				editfunc();
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
				exportfunc();
//				$('#admin_lwstutas_export').dialog('open');
			}
		} ]

	});

	$('#dd2').datebox({
		onSelect : function(date) {
			alert(date.getFullYear() + ":" + (date.getMonth() + 1) + ":" + date.getDate());
		}
	});
};

/******************************************* Thesis Status Manage Operator ******************************************/
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('lwStutasAction!downloadExcel.action');
};


function lwStutasExcelImport (formName,dilogName){
	$(formName).form('submit', {
		url :$('#contextPath').val() + '/lwStutasAction!upLoadExcel.action',
		success : function(result) {
		$(dilogName).dialog('close');
		if(result!=""){
			
		var a = jQuery.parseJSON(result);
		if(a.success){
			refreshfunc_Manger('#admin_lwstutas_datagrid');
			$.messager.show({
					title : '提示',
					msg : a.msg
				});
		}else{
				$.messager.show({
					title : '提示',
					msg : a.msg
				});
		
		}
		
		$('#admin_lwstutas_datagrid').datagrid('reload');
		}else{
			$.messager.show({
				title : '提示',
				msg : '导入出错,请检查excel！'
			});
		}
		
		},
		error:function(){
			$.messager.show({
				title : '提示',
				msg : '导入出错,请检查excel！'
			});
		}
	});
}


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

//属性编辑操作
function editfunc() {
	var rows = $('#admin_lwstutas_datagrid').datagrid('getChecked');
	// 只能编辑一行
	if (rows.length == 1) {
		// 回显
		console.info(rows[0]);
		$('#admin_lwstutas_editDialog').dialog('open');
//		$('#e_lwstutas_lwstatusid').val(rows[0].lwstatusid);
	//	$('#e_lwstutas_lwstutasid').textbox('setValue', rows[0].lwstutasid);
		$('#e_lwstutas_lwstutasid').val(rows[0].lwstutasid);
		$('#e_lwstutas_name').textbox('setValue', rows[0].name);
		//$('#e_lwstutas_pubkind').combobox('setValue', rows[0].pubkind);
		
	
	}else{
		
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
	}
};



var editdt_btn_ok = function() {
	$('#admin_lwstutas_editForm').form('submit', {
		url : $('#contextPath').val() + '/lwStutasAction!edit.action',
		onSubmit : function(param) {
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				 $('#admin_lwstutas_editDialog').dialog('close');
				// 重新加载列表
				$('#admin_lwstutas_datagrid').datagrid('reload');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});

			$('#admin_lwstutas_editDialog').dialog('close');
		}
	});
};

var editdt_btn_cancel = function() {
	$('#admin_lwstutas_editDialog').dialog('close');
};



function getyhris() {
	var ris = $('#selRoles').find("option");
	var rightarr = "";
	for (var k = 0; k < $('#selRoles').find("option").length; k++) {
		rightarr += ris[k].value + ",";

	}

	$("#comment").attr("value", rightarr);

}