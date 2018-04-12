/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_yhgl_datagrid').datagrid({
		url : $('#contextPath').val() + '/userAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'userid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		/*pagePosition : 'both',*/
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'userid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'pwd',
			title : '密码',
			width : 150,
			formatter : function(value, row, index) {
				return '******';
			}
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
		}, '-' , {
			text : '过滤',
			iconCls : 'icon-filter',
			handler : function() {
				alert("常规过滤");
			}
		}, '-' , {
			text : '密码重置',
			iconCls : 'icon-lock',
			handler : function() {
				alert("密码重置");
			}
		}, '-' , {
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_yhgl_partBar').dialog('open');
			}
		}, '-' , {
			text : '日期查询',
			iconCls : 'icon-datefilter',
			handler : function() {
				$('#admin_yhgl_dateBar').dialog('open');
			}
		}, '-' , {
			text : '状态查询',
			iconCls : 'icon-stutafilter',
			handler : function() {
				
				$('#admin_yhgl_comBar').dialog('open');
			}
		}, '-' , {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_yhgl_comBar').dialog('open');
			}
		}, '-' , {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				
				$('#admin_yhgl_comBar').dialog('open');
			}
		}]
	
		
		
	});
	
	$('#dd2').datebox({
		onSelect: function(date){
			alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		}
	});
};


/******************************************* Plan Grade Manage Operator ******************************************/
function searchFun() {
	$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
}
function clearFun() {
	$('#admin_yhgl_layout input[name=name]').val('');
	$('#admin_yhgl_datagrid').datagrid('load', {});
}
function append() {
	 $.ajax( {    
		    url: $('#contextPath').val() + '/roleAction!datagrid.action',// 跳转到 action    
		   /*  data:{    
		             selRollBack : selRollBack,    
		             selOperatorsCode : selOperatorsCode,    
		             PROVINCECODE : PROVINCECODE,    
		             pass2 : pass2    
		    },     */
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			for(var i=0;i<datas[attr].length;i++){
		    				//alert(datas[attr][i].rightname);
		    				 $('#allRoles').append(
				                        "<option value='"+datas[attr][i].roleid+"'>"+datas[attr][i].rolename
				                                + "</option>");
		    			}
		    		}
		    	 }
		    	 
		        
		     },    
		     error : function() {    
		          // view("异常！");    
		          alert("异常！");    
		     }    
		});  
	$('#admin_yhgl_addForm input').val('');
	$('#admin_yhgl_addDialog').dialog('open');
	
	
}

function remove() {
	var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelected');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelections');
	console.info(rows);
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].userid);
			
				}
				$.ajax({
					url : $('#contextPath').val() + '/userAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_yhgl_datagrid').datagrid('load');
						$('#admin_yhgl_datagrid').datagrid('unselectAll');
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