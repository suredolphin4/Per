/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	
	searchLabEntity('#add_lab_domainid','#add_lab_domaincode','#add_lab_domainid');
	searchLabEntity('#e_lab_domainid','#e_lab_domaincode','#add_lab_domainid');
	
	$('#admin_lab_datagrid').datagrid({
		url : $('#contextPath').val() + '/labAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'labid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'labid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'name',
			title : '实验室名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'domainname',
			title : '所属领域',
			width : 150,
			sortable : true
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
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_lab_partBar').dialog('open');
			}
		}, '-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_lab_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_lab_export').dialog('open');
			}
		} ]

	});

	$('#dd2').datebox({
		onSelect : function(date) {
			alert(date.getFullYear() + ":" + (date.getMonth() + 1) + ":" + date.getDate());
		}
	});
	
	
	
/*	searchLabEntity('#add_lab_domainid','#add_lab_domaincode','#add_lab_domainname');
	searchLabEntity('#e_lab_domainid','#e_lab_domaincode','#e_lab_domainname');
*/};


/******************************************* Lab Operator ******************************************/
function searchLabEntity(EntityString,option1,option2){	
	var url = $('#contextPath').val() + "/domainAction!datagrid.action";
    $.getJSON(url, function(json) {
        $(EntityString).combobox({
        	data : json.rows,
        	valueField : 'name',
			textField : 'name',
			 onSelect:function(record){
				 $(option1).val(record.domainid);
		    	 $(option2).val(record.name);
/*		    	 $(option1).textbox('setValue',record.domainid);
		    	 $(option2).textbox('setValue',record.name);
*/		    	// $('#add_lab_domainid').combobox('setValue', record.name);
			}
        	
        });
    });
}




function searchFun() {
	//$('#admin_lab_datagrid').datagrid('load', serializeObject($('#admin_lab_searchForm')));
	
	$('#admin_lab_datagrid').datagrid({
		url : $('#contextPath').val() + '/labAction!datagrid.action',
		fit : true,
		queryParams: {name:$('#lab_search_name').val()},
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'labid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'labid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'name',
			title : '研究室名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ 
		 {
			field : 'domainname',
			title : '所属领域',
			width : 150,
			sortable : true
		},{
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
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_lab_partBar').dialog('open');
			}
		}, '-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_lab_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
				/*$('#admin_lab_export').dialog('open');*/
			}
		} ]

	});
}
function clearFun() {
	$('#admin_lab_layout input[name=name]').val('');
	$('#admin_lab_datagrid').datagrid('load', {});
}

var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('labAction!downloadExcel.action');
};

function append() {

	$('#admin_lab_addForm input').val('');
	$('#admin_lab_addDialog').dialog('open');

}

function remove() {
	var rows = $('#admin_lab_datagrid').datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].labid);

				}
				$.ajax({
					url : $('#contextPath').val() + '/labAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_lab_datagrid').datagrid('load');
						$('#admin_lab_datagrid').datagrid('unselectAll');
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
	var rows = $('#admin_lab_datagrid').datagrid('getChecked');
	// 只能编辑一行
	if (rows.length == 1) {
		// 回显
		$('#admin_lab_editDialog').dialog('open');
		
		$('#e_lab_labid').val(rows[0].labid);
		$('#e_lab_name').textbox('setValue', rows[0].name);
		//$('#e_lab_domainname').textbox('setValue', rows[0].domainname);
		//$('#e_lab_domaincode').textbox('setValue', rows[0].domaincode);
		$('#e_lab_domainname').val(rows[0].domainname);
		$('#e_lab_domaincode').val(rows[0].domaincode);
		$('#e_lab_code').textbox('setValue', rows[0].code);
		$('#e_lab_domainid').combobox('setValue', rows[0].domaincode);
		$('#e_lab_domainid').combobox('setText', rows[0].domainname);
		
	
	}else{
		
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
	}
};

var editdt_btn_ok = function() {
	$('#admin_lab_editForm').form('submit', {
		url : $('#contextPath').val() + '/labAction!edit.action',
		onSubmit : function(param) {
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				// 更新论文列表datagrid
//				 $('#admin_lab_datagrid').datagrid('updateRow',{
//				 index:$('#admin_lab_datagrid').datagrid('getRowIndex',r[0].labid),
//				 row:objr.obj
//				 });

				// 重新加载论文列表
				$('#admin_lab_datagrid').datagrid('reload');
				$('#admin_lab_editDialog').dialog('close');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});

			$('#admin_lab_editDialog').dialog('close');
		}
	});
};

var editdt_btn_cancel = function() {
	$('#admin_lab_editDialog').dialog('close');
};