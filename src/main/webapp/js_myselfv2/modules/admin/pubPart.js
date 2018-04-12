/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	
	searchEntity('#e_pubpart_pubkind');
	
	$('#admin_pubpart_datagrid').datagrid({
		url : $('#contextPath').val() + '/pubPartAction!datagrid.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'tpid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		checkOnSelect : true,
		selectOnCheck : false,
		columns : [ [ {
			field : 'tpid',
			title : '编号',
			width : 50,
			checkbox : true
		}, {
			field : 'title',
			title : '期刊名称',
			width : 400,
			sortable : true
		} , {
			field : 'issn',
			title : 'issn',
			width : 150,
			sortable : true
		}, {
			field : 'pubkind',
			title : '期刊类别',
			width : 150,
			sortable : true
		}, {
			field : 'factor',
			title : '影响因子',
			width : 100,
			sortable : true
		}, {
			field : 'zone',
			title : '小类分区',
			width : 100,
			sortable : true
		}, {
			field : 'pubranking',
			title : '国内排名',
			width : 100,
			sortable : true
		}, {
			field : 'topcomm',
			title : '顶级说明',
			width : 150,
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : 150,
			sortable : true
		},{
			field : 'savetime',
			title : '创建时间',
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
		},  '-', {
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_pubpart_partBar').dialog('open');
			}
		},'-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_pubpart_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_pubpart_export').dialog('open');
			}
		}, '-' , {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				refreshfunc_Manger('#admin_pubpart_datagrid');
//				$('#admin_rygl_comBar').dialog('open');
			}
		} ]

	});

	
	
};

/******************************************* pubpark Manage Operator ******************************************/
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('pubPartAction!downloadExcel.action');
};

function searchFun() {
	$('#admin_pubpart_datagrid').datagrid({
		url : $('#contextPath').val() + '/pubPartAction!datagrid.action',
		fit : true,
		queryParams: {title:$('#pubpart_search_title').val(),issn:$('#pubpart_search_title').val()},
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'tpid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'tpid',
			title : '编号',
			width : 50,
			checkbox : true
		}, {
			field : 'title',
			title : '期刊名称',
			width : 400,
			sortable : true
		} , {
			field : 'issn',
			title : 'issn',
			width : 150,
			sortable : true
		}, {
			field : 'pubkind',
			title : '期刊类别',
			width : 150,
			sortable : true
		}, {
			field : 'factor',
			title : '影响因子',
			width : 100,
			sortable : true
		}, {
			field : 'zone',
			title : '小类分区',
			width : 100,
			sortable : true
		}, {
			field : 'pubranking',
			title : '国内排名',
			width : 100,
			sortable : true
		}, {
			field : 'topcomm',
			title : '顶级说明',
			width : 150,
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : 150,
			sortable : true
		}] ],
		columns : [ [ {
			field : 'savetime',
			title : '创建时间',
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
		},  '-', {
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_pubpart_partBar').dialog('open');
			}
		},'-', {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_pubpart_import').dialog('open');
			}
		}, '-', {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_pubpart_export').dialog('open');
			}
		}, '-' , {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				refreshfunc_Manger('#admin_pubpart_datagrid');
//				$('#admin_rygl_comBar').dialog('open');
			}
		} ]

	});

}
function clearFun() {
	$('#admin_pubpart_searchForm input[name=title]').val('');
	$('#admin_pubpart_datagrid').datagrid('load', {});
}
function append() {

	$('#admin_pubpart_addForm input').val('');
	$('#admin_pubpart_addDialog').dialog('open');
	
	var url = $('#contextPath').val() + "/pubKindAction!datagrid.action";
    $.getJSON(url, function(json) {
        $('#add_pubpart_pubkind').combobox({
        	data : json.rows,
        	valueField : 'pubkind',
			textField : 'pubkind',
        	onSelect:function(){
        		
        		//var entityString = $('#EntityString').combobox('getText');
        		// $('#s_end_year').combobox('select', yearData[0].id);
        	}
        });
    });

}

function remove() {
	var rows = $('#admin_pubpart_datagrid').datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(rd) {
			if (rd) {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].tpid);

				}
				$.ajax({
					url : $('#contextPath').val() + '/pubPartAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(re) {
						/*$('#admin_pubpart_datagrid').datagrid('load');
						$('#admin_pubpart_datagrid').datagrid('unselectAll');*/
						$('#admin_pubpart_datagrid').datagrid('load');
						$('#admin_pubpart_datagrid').datagrid('uncheckAll');
//						$('#admin_rygl_datagrid').datagrid('unselectAll');
					//	$('#admin_pubpart_datagrid').datagrid('reload');
						$.messager.show({
							title : '提示',
							msg : re.msg
						});
					}
				});
			}
		});
		//$('#admin_pubpart_datagrid').datagrid('clearSelections');
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



function searchEntity(EntityString){	
	var url = $('#contextPath').val() + "/pubKindAction!datagrid.action";
    $.getJSON(url, function(json) {
        $(EntityString).combobox({
        	data : json.rows,
        	valueField : 'pubkind',
			textField : 'pubkind',
        	onSelect:function(){
        		
//        		var entityString = $('#EntityString').combobox('getText');
        		// $('#s_end_year').combobox('select', yearData[0].id);
        	}
        });
    });
}




//属性编辑操作
function editfunc() {
	var rows = $('#admin_pubpart_datagrid').datagrid('getChecked');
	// 只能编辑一行
	if (rows.length == 1) {
		// 回显
		$('#admin_pubpart_editDialog').dialog('open');
		
		$('#e_pubpart_tpid').val(rows[0].tpid);
		$('#e_pubpart_title').textbox('setValue', rows[0].title);
		$('#e_pubpart_issn').textbox('setValue', rows[0].issn);
		$('#e_pubpart_factor').textbox('setValue', rows[0].factor);
		$('#e_pubpart_zone').textbox('setValue', rows[0].zone);
		$('#e_pubpart_pubkind').combobox('setValue', rows[0].pubkind);
		$('#e_pubpart_pubranking').textbox('setValue', rows[0].pubranking);
		$('#e_pubpart_topcomm').textbox('setValue', rows[0].topcomm);
		$('#e_pubpart_remark').textbox('setValue', rows[0].remark);
		
	
	}else{
		
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
	}
};

var editdt_btn_ok = function() {
	$('#admin_pubpart_editForm').form('submit', {
		url : $('#contextPath').val() + '/pubPartAction!edit.action',
		onSubmit : function(param) {
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				// 更新论文列表datagrid
			/*	 $('#admin_pubpart_datagrid').datagrid('updateRow',{
				 index:$('#admin_pubpart_datagrid').datagrid('getRowIndex',r[0].tpid),
				 row:objr.obj
				 });
*/
				
				 
				 $('#admin_pubpart_editDialog').dialog('close');
				// 重新加载论文列表
				$('#admin_pubpart_datagrid').datagrid('reload');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});

			$('#admin_pubpart_editDialog').dialog('close');
		}
		
		
	});
	$('#admin_pubpart_editDialog').dialog('close');
};

var editdt_btn_cancel = function() {
	$('#admin_pubpart_editDialog').dialog('close');
};


function getPath(obj) {
    if (obj) {
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            obj.select(); return document.selection.createRange().text;
        }
        else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
            if (obj.files) {
                return obj.files.item(0).getAsDataURL();
            }
            return obj.value;
        }
        return obj.value;
    }
} 



//根据issn和期刊类型查重  add_pubpart_pubkind   add_pubpart_issn
function submitPubPart(){
	
	var temp1=$('#add_pubpart_pubkind').combobox('getText');
	var temp2=$('#add_pubpart_issn').val();
	
	var temp=temp1+","+temp2;
	$.ajax( {    
	   url : $('#contextPath').val() +'/pubPartAction!pubPartCheck.action',// 跳转到 action    
	    type:'post', 
	    data : {
			ids : temp
		},
	    cache:false,    
	    dataType:'json',    
	    success:function(objr) {  
	   
	    	
			if (objr.success) {
				
				//alert('该用户为非法用户或该用户已经存在，不能重复添加！！');
				$.messager.show({
					title : '提示',
					msg : '该期刊非法或已经存在，不能重复添加！！'
				});
				$('#admin_pubpart_addDialog').dialog('close');
				
			}else{
					$('#admin_pubpart_addForm').form('submit', {
					url : $('#contextPath').val() +'/pubPartAction!add.action',
					beforeSubmit:getyhris(),
					success : function(b) {
						var obj = jQuery.parseJSON(b);
						if (obj.success) {
							$('#admin_pubpart_datagrid').datagrid('load');
							$('#admin_pubpart_datagrid').datagrid('appendRow',obj.obj);
							$('#admin_pubpart_datagrid').datagrid('insertRow',{
								index:0,
								row:obj.obj
							});
							$('#admin_pubpart_addDialog').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg : obj.msg
						});
					//	$('#selRoles').find('option').remove();
				     //   $('#allRoles').find('option').remove();
						$('#admin_pubpart_addDialog').dialog('close');
					}
				});
				
			}

			
	        
	     },    
	     error : function() {  
	    	alert('期刊检查出错');  
	     }    
	}); 
	
}