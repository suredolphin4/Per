/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	
	searchEntity('#e_person_domain','#e_person_lab');
	searchLab('#e_person_lab');
	searchPersonType('#e_person_persontype');
	searchPersonCate('#e_person_personcate');
	
	searchEntity('#add_person_domain','#add_person_lab');
	searchLab('#add_person_lab');
	searchPersonType('#add_person_persontype');
	searchPersonCate('#add_person_personcate');
	
	
	$('#admin_rygl_datagrid').datagrid({
		url : $('#personContextPath').val() + '/personAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'personid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		/*pagePosition : 'both',*/
		checkOnSelect : true,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'personid',
			title : '编号',
			width : 50,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 100,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'usercode',
			title : '员工编号',
			width : 50,
			sortable : true
		}, {
			field : 'domain',
			title : '领域',
			width : 100,
			sortable : true
		}, {
			field : 'domaincode',
			title : '领域编号',
			width : 100,
			sortable : true
		}, {
			field : 'lab',
			title : '研究室',
			width : 100,
			sortable : true
		}, {
			field : 'labnum',
			title : '研究室编号',
			width : 100,
			sortable : true
		}, {
			field : 'department',
			title : '部门',
			width : 150,
			sortable : true
		}, {
			field : 'personcate',
			title : '申报级别',
			width : 150,
			sortable : true
		}, {
			field : 'persontype',
			title : '员工类型',
			width : 150,
			sortable : true
		}, {
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
		}, '-' ,{
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_rygl_partBar').dialog('open');
			}
		}, '-' ,{
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_rygl_import').dialog('open');
			}
		}, '-' , {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_rygl_comBar').dialog('open');
			}
		}, '-' , {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				refreshfunc_Manger('#admin_rygl_datagrid');
//				$('#admin_rygl_comBar').dialog('open');
			}
		}]
	
		
		
	});
	
	$('#dd2').datebox({
		onSelect: function(date){
			alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		}
	});
	
//	searchEntity('#e_person_domain','#e_person_lab');
//	searchLab('#e_person_lab');
//	searchPersonType('#e_person_persontype');
//	searchPersonCate('#e_person_personcate');
//	
//	searchEntity('#add_person_domain','#add_person_lab');
//	searchLab('#add_person_lab');
//	searchPersonType('#add_person_persontype');
//	searchPersonCate('#add_person_personcate');
	
	
};

/******************************************* Person Manage Operator ******************************************/


var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('personAction!downloadExcel.action');
};

function searchPersonCate(EntityString){	
	var url = $('#personContextPath').val() + "/userCateAction!datagrid.action";
    $.getJSON(url, function(json) {
        $(EntityString).combobox({
        	data : json.rows,
        	valueField : 'name',
			textField : 'name',
        });
    });
}
function searchPersonType(EntityString){	
	var url = $('#personContextPath').val() + "/userTypeAction!datagrid.action";
	$.getJSON(url, function(json) {
		$(EntityString).combobox({
			data : json.rows,
			valueField : 'name',
			textField : 'name',
		});
	});
}
function searchLab(EntityString){	
	var url = $('#personContextPath').val() + "/labAction!datagrid.action";
	$.getJSON(url, function(json) {
		$(EntityString).combobox({
			data : json.rows,
			valueField : 'name',
			textField : 'name',
		});
	});
}

function searchEntity(EntityString,childString){	
	 var url = $('#personContextPath').val() + "/domainAction!datagrid.action";
     $.getJSON(url, function(json) {
     $(EntityString).combobox({
     data : json.rows,
     valueField:'name',
/*                 valueField:'domainid',
*/                 textField:'name',
     onSelect:function(record){
    	 var domainid=record.domainid;
    	 var labUrl=$('#personContextPath').val() + "/labAction!datagrid.action";
    	 $.getJSON(labUrl,{domaincode:domainid},function(labjson){
    		 $(childString).combobox({
                 data : labjson.rows,
                 valueField:'name',
/*                             valueField:'labid',
*/                             textField:'name'
    		 });
    	
    	 	});
     	} 
    	 
     });
     });	
}

function searchFun() {
	$('#admin_rygl_datagrid').datagrid({
		url : $('#personContextPath').val() + '/personAction!datagrid.action',
		queryParams: {name:$('#admin_rygl_searchForm input[name=name]').val(),usercode:$('#admin_rygl_searchForm input[name=name]').val()},
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'personid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		/*pagePosition : 'both',*/
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'personid',
			title : '编号',
			width : 50,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 100,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'usercode',
			title : '员工编号',
			width : 50,
			sortable : true
			
		}, {
			field : 'domain',
			title : '领域',
			width : 100,
			sortable : true
		}, {
			field : 'domaincode',
			title : '领域编号',
			width : 100,
			sortable : true
		}, {
			field : 'lab',
			title : '研究室',
			width : 100,
			sortable : true
		}, {
			field : 'labnum',
			title : '研究室编号',
			width : 100,
			sortable : true
		}, {
			field : 'department',
			title : '部门',
			width : 150,
			sortable : true
		}, {
			field : 'personcate',
			title : '申报级别',
			width : 150,
			sortable : true
		}, {
			field : 'persontype',
			title : '员工类型',
			width : 150,
			sortable : true
		}, {
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
		}, '-' ,{
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_rygl_partBar').dialog('open');
			}
		}, '-' ,{
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_rygl_import').dialog('open');
			}
		}, '-' , {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_rygl_comBar').dialog('open');
			}
		}, '-' , {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				refreshfunc_Manger('#admin_rygl_datagrid');
//				$('#admin_rygl_comBar').dialog('open');
			}
		}]
	
		
		
	});
	
}
function clearFun() {
	$('#admin_rygl_layout input[name=name]').val('');
	$('#admin_rygl_datagrid').datagrid('load', {});
}
function append() {
            /*	 var url = $('#personContextPath').val() + "/domainAction!datagrid.action";
                 $.getJSON(url, function(json) {
                 $('#domain').combobox({
                 data : json.rows,
                 valueField:'name',
                 valueField:'domainid',
                 textField:'name',
                 onSelect:function(record){
                	 var domainid=record.domainid;
                	 var labUrl=$('#personContextPath').val() + "/labAction!datagrid.action";
                	 $.getJSON(labUrl,{domaincode:domainid},function(labjson){
                		 $('#lab').combobox({
                             data : labjson.rows,
                             valueField:'name',
                             valueField:'labid',
                             textField:'name'
                	 });
                	
                 });
                 } 
                	 
                 });
                 });	*/
	
	/*  $.ajax( {    
		    url:'${pageContext.request.contextPath}/roleAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			for(var i=0;i<datas[attr].length;i++){
		    				  $('#allRoles').append(
				                        "<option value='"+datas[attr][i].roleid+"'>"+datas[attr][i].rolename
				                                + "</option>"); 
		    			}
		    		}
		    	 }
		    	 
		        
		     },    
		     error : function() {    
		          alert("异常！");    
		     }    
		});   */
	$('#admin_rygl_addDialog').dialog('open');
	$('#admin_yhgl_addForm').form('clear');
	
	
}

function remove() {
	var rows = $('#admin_rygl_datagrid').datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].personid);
				}
				$.ajax({
					url : $('#personContextPath').val() + '/personAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_rygl_datagrid').datagrid('uncheckAll');
//						$('#admin_rygl_datagrid').datagrid('unselectAll');
						$('#admin_rygl_datagrid').datagrid('reload');
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

function toyhleft(){
	 var sel=$('#allRoles').find("option:selected");
	 if(sel.lenght!=0){
		 
	    for(var i=0;i<sel.length;i++){ 
	    		
	    	$("#selRoles option[value='"+sel[i].value+"']").remove();
	    		
	    	 $('#selRoles').append("<option value='"+sel[i].value+"'>"+sel[i].text
				                                + "</option>");
				 
	    }
	 }else{
		 $.messager.show({
				title : '提示',
				msg : '请选择权限'
			});
	 }
	 
	}
	
	function toyhright(){
		 var sel=$('#selRoles').find("option:selected");
		 for(var i=0;i<sel.length;i++){ 
		    	//alert(sel[i].text);
		    	/*  $('#selright').append("<option value='"+sel[i].value+"'>"+sel[i].text
					                                + "</option>"); */
		    	 
		    	 $("#selRoles option[value='"+sel[i].value+"']").remove();
		        /* var varitem = new Option($(str+"1").options[i].text,$(str+"1").options[i].value);  
		        $(str+"0").options.add(varitem); */  
		    }  
	}

	
	
	function getyhris(){
		var ris=$('#selRoles').find("option");	
		var rightarr="";
		for(var k=0;k<$('#selRoles').find("option").length;k++){
			rightarr+=ris[k].value+",";
			
			
		}
		
		$("#comment").attr("value",rightarr);
		
	}
	
	
	
	
	
	// 属性编辑操作
	var editfunc = function() {
	
	
		
		// 获取当前选中行的id
		var rows = $('#admin_rygl_datagrid').datagrid('getChecked');
		// 只能编辑一行
		if (rows.length == 1) {
			// 回显
			
			$('#e_person_personid').val(rows[0].personid);
			$('#e_person_name').textbox('setValue', rows[0].name);
			$('#e_person_domain').combobox('setValue', rows[0].domain);
			$('#e_person_usercode').textbox('setValue', rows[0].usercode);
			$('#e_person_lab').combobox('setValue', rows[0].lab);
			$('#e_person_department').textbox('setValue', rows[0].department);
			$('#e_person_persontype').combobox('setValue', rows[0].persontype);
			$('#e_person_personcate').combobox('setValue', rows[0].personcate);
			$('#admin_rygl_editDialog').dialog('open');
		}else{
			$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		}
	};

	var editdt_btn_ok = function() {
		$('#admin_rygl_editForm').form('submit', {
			url : $('#contextPath').val() + '/personAction!edit.action',
			onSubmit : function(param) {
				return $(this).form('validate');
			},
			success : function(r) {
				// 修改成功，解析返回的json信息
				var objr = jQuery.parseJSON(r);
				if (objr.success) {
					// 更新论文列表datagrid
					/* $('#admin_rygl_datagrid').datagrid('updateRow',{
					 index:$('#admin_rygl_datagrid').datagrid('getRowIndex',r[0].personid),
					 row:objr.obj
					 });*/

					 
					 $('#admin_rygl_editDialog').dialog('close');
					// 重新加载论文列表
					$('#admin_rygl_datagrid').datagrid('reload');
				}

				$.messager.show({
					title : '提示',
					msg : objr.msg
				});

				$('#admin_rygl_editDialog').dialog('close');
			}
		});
		$('#admin_rygl_editDialog').dialog('close');
	};

	var editdt_btn_cancel = function() {
		$('#admin_rygl_editDialog').dialog('close');
	};
