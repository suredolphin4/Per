/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_qxgl_datagrid').datagrid({
		url : $('#authorityContextPath').val() + '/rightAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'rightid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'rightname',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'rightid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'rightname',
			title : '权限名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'rightcode',
			title : '权限',
			width : 150,
			sortable : true
		},{
			field : 'righturl',
			title : 'url',
			width : 150,
			sortable : true
		}, {
			field : 'rightpos',
			title : '权限位',
			width : 150,
			sortable : true
		} ,{
			field : 'common',
			title : '公有',
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
				qxedit();
			}
		}, '-' ]
	});
};


/******************************************* Authority Operator ******************************************/
function searchFun() {
	$('#admin_qxgl_datagrid').datagrid('load', serializeObject($('#admin_qxgl_searchForm')));
}
function clearFun() {
	$('#admin_qxgl_layout input[name=name]').val('');
	$('#admin_qxgl_datagrid').datagrid('load', {});
}
function append() {
	$('#admin_qxgl_addForm input').val('');
	$('#admin_qxgl_addDialog').dialog('open');
}
function remove() {
	var rows = $('#admin_qxgl_datagrid').datagrid('getChecked');
	//var rows = $('#admin_qxgl_datagrid').datagrid('getSelected');
	//var rows = $('#admin_qxgl_datagrid').datagrid('getSelections');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].rightid);
				}
				$.ajax({
					url : $('#authorityContextPath').val() + '/rightAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_qxgl_datagrid').datagrid('load');
						$('#admin_qxgl_datagrid').datagrid('unselectAll');
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

function qxedit(){
	var rows = $('#admin_qxgl_datagrid').datagrid('getChecked');
	if(rows.length==1){
	var d=$('<div/>').dialog({
		width:400,
		height:200,
		href:$('#authorityContextPath').val() + 'performance/modules/admin/authorityEdit.jsp',
		modal:true,
		title:'修改权限',
		buttons:[{
			text:'修改',
			handler:function(){
				$('#admin_qxgl_updateForm').form('submit',{
					
					url:$('#authorityContextPath').val() + '/rightAction!edit.action',
					success : function(r) {
						var objr = jQuery.parseJSON(r);
						if (objr.success) {
							d.dialog('close');
						
						
						
						$('#admin_qxgl_datagrid').datagrid('updateRow',{
							index:$('#admin_qxgl_datagrid').datagrid('getRowIndex',rows[0].rightid),
							row:objr.obj
						});
						
						
						
/* 								$('#admin_jsgl_datagrid').datagrid('load');
*/							}
						$.messager.show({
							title:'提示',
							msg:obj.msg
							
						});
					}
				});
			}
			
		}],
		onClose:function(){
			$(this).dialog('destory');
		},
		onLoad:function(){
			//回显
			 $('#admin_qxgl_updateForm input[name=rightid]').val(rows[0].rightid); 
			 $('#admin_qxgl_updateForm input[name=rightname]').val(rows[0].rightname); 
			 $('#admin_qxgl_updateForm input[name=righturl]').val(rows[0].righturl); 
			 $('#admin_qxgl_updateForm input[name=rightcode]').val(rows[0].rightcode); 
			 $('#admin_qxgl_updateForm input[name=rightpos]').val(rows[0].rightpos); 
		/* 	$('#admin_qxgl_updateForm').form('reload',rows[0]); */
		}
		
		
	});
	
}else{
	$.messager.alert('提示','请选择一条数据进行修改');
}
}