/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_yhgl_datagrid').datagrid({
		url : $('#userContextPath').val() + '/userAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'userid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		/*pagePosition : 'both',*/
		checkOnSelect : true,
		selectOnCheck : false,
		onLoadSuccess:function(data){
			//json = $.parseJSON(data);
			for(var i=0;i<data.rows.length;i++){
				
				data.rows[i].roles[0].rolename;
			}
			
		},
		frozenColumns : [ [ {
			field : 'userid',
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
			title : '用户编号',
			width : 50,
			sortable : true
			
		},{
			field : 'domain',
			title : '领域名称',
			width : 150,
			sortable : true
		},{
			field : 'domainnum',
			title : '领域编号',
			width : 50,
			sortable : true,
			hidden:true
		},{
			field : 'lab',
			title : '研究室',
			width : 150,
			sortable : true
			
		},{
			field : 'userCategory',
			title : '申报级别',
			width : 150,
			sortable : true
			
		},{
			field : 'labnum',
			title : '研究室编号',
			width : 50,
			sortable : true,
			hidden:true
		},{
			field:'roles',
			title:'所属角色',
			width : 50,
			formatter: function(value,row,index){
				if(typeof(row) != "undefined" && typeof(row.roles)!="undefined" && typeof(row.roles[0])!="undefined"){
				return row.roles[0].rolename;
				}
			}	
		},{
			field:'rolename',
			title:'角色名称',
			width : 50,
			hidden:true,
			sortable : true
		}, {
			field : 'savetime',
			title : '创建时间',
			width : 150,
			sortable : true
		} ] ],
		toolbar : '#tb'
		/*toolbar : [ {
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
				editfun();
			}
		} , '-' , {
			text : '过滤',
			iconCls : 'icon-filter',
			handler : function() {
				alert("常规过滤");
			}
		} , '-' , {
			text : '密码重置',
			iconCls : 'icon-lock',
			handler : function() {
				resetPasswd();
			}
		}, '-' , {
			text : '名称检索',
			iconCls : 'icon-search',
			handler : function() {
				$('#admin_yhgl_partBar').dialog('open');
			}
		},  '-' , {
			text : '日期查询',
			iconCls : 'icon-datefilter',
			handler : function() {
				$('#admin_yhgl_dateBar').dialog('open');
			}
		},  '-' , {
			text : '状态查询',
			iconCls : 'icon-stutafilter',
			handler : function() {
				
				$('#admin_yhgl_comBar').dialog('open');
			}
		}, '-' , {
			text : '导入',
			iconCls : 'icon-impo',
			handler : function() {
				
				$('#admin_yhgl_import').dialog('open');
			}
		}, '-' , {
			text : '导出',
			iconCls : 'icon-export',
			handler : function() {
				exportfunc();
//				$('#admin_yhgl_comBar').dialog('open');
			}
		}]*/
	
		
		
	});
	
	$('#dd2').datebox({
		onSelect: function(date){
			alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		}
	});
};

/******************************************* User Operator ******************************************/
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('userAction!downloadExcel.action');
};
function searchFun() {
	$('#admin_yhgl_datagrid').datagrid({
		url : $('#userContextPath').val() + '/userAction!datagrid.action',
		fit : true,
		queryParams: {name:$('#admin_yhgl_searchForm input[name=name]').val(),usercode:$('#admin_yhgl_searchForm input[name=name]').val()},
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'userid',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'savetime',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		onLoadSuccess:function(data){
		//	console.info("leng"+data.rows[2].roles[0].rolename);
			//json = $.parseJSON(data);
			/*for(var i=0;i<data.rows.length;i++){
				
				data.rows[i].roles[0].rolename;
			}*/
			
		},
		frozenColumns : [ [ {
			field : 'userid',
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
			
		},{
			field : 'domain',
			title : '领域名称',
			width : 150,
			sortable : true
		},{
			field : 'domainnum',
			title : '领域编号',
			width : 50,
			sortable : true,
			hidden:true
		},{
			field : 'lab',
			title : '研究室',
			width : 150,
			sortable : true
			
		},{
			field : 'userCategory',
			title : '申报级别',
			width : 150,
			sortable : true
			
		},{
			field : 'labnum',
			title : '研究室编号',
			width : 50,
			sortable : true,
			hidden:true
			
		},{
			field:'roles',
			title:'所属角色',
			width : 50,
			formatter: function(value,row,index){
				if(typeof(row) != "undefined" && typeof(row.roles)!="undefined" && typeof(row.roles[0])!="undefined"){
					return row.roles[0].rolename;
					
					}
			}	
		},{
			field:'rolename',
			title:'角色名称',
			width : 50,
			hidden:true,
			sortable : true
		} , {
			field : 'savetime',
			title : '创建时间',
			width : 150,
			sortable : true
		}] ],
		toolbar : '#tb'
//		toolbar : [ {
//			text : '增加',
//			iconCls : 'icon-add',
//			handler : function() {
//				append();
//			}
//		}, '-', {
//			text : '删除',
//			iconCls : 'icon-remove',
//			handler : function() {
//				remove();
//			}
//		}, '-' , {
//			text : '密码重置',
//			iconCls : 'icon-lock',
//			handler : function() {
//				resetPasswd();
//			}
//		}, '-' , {
//			text : '名称检索',
//			iconCls : 'icon-search',
//			handler : function() {
//				$('#admin_yhgl_partBar').dialog('open');
//			}
//		},{
//			text : '导入',
//			iconCls : 'icon-impo',
//			handler : function() {
//				$('#admin_yhgl_import').dialog('open');
//			}
//		}, '-' , {
//			text : '导出',
//			iconCls : 'icon-export',
//			handler : function() {
//				exportfunc();
////				$('#admin_yhgl_comBar').dialog('open');
//			}
//		}]
	});
	//$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
}

var importByExcel=function(){
	$('#admin_yhgl_import').dialog('open');
};

function ExcelImport(formName,dilogName){
	$(formName).form('submit', {
		url :$('#userContextPath').val() + '/userAction!upLoadExcel.action',
		success : function(result) {
		$(dilogName).dialog('close');
		if(result!=""){
			
		var a = jQuery.parseJSON(result);
		if(a.success){
			refreshfunc_Manger('#admin_yhgl_datagrid');
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
		
		$('#admin_yhgl_datagrid').datagrid('reload');
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

var searchByName=function(){
	$('#admin_yhgl_partBar').dialog('open');
};
function clearFun() {
	$('#admin_yhgl_searchForm_name').val('');
	$('#admin_yhgl_datagrid').datagrid('load', {});
}

function searchDomain(){
	 var url = $('#userContextPath').val() + "/domainAction!datagrid.action";
     $.getJSON(url, function(json) {
     $('#admin_yhgl_add_domain').combobox({
     data : json.rows,
     valueField:'name',
//     valueField:'domainid',
     textField:'name',  
/*      onSelect:function(record){
    	 var domainid=record.domainid;
    	 var labUrl="${pageContext.request.contextPath}/labAction!datagrid.action";
    	 $.getJSON(labUrl,{domaincode:domainid},function(labjson){
    		 $('#lab').combobox({
                 data : labjson.rows,
                 valueField:'labid',
                 textField:'name'
    	 });
    	
     });
     }  */
    	 
     });
     });	 
     
 
	/* $.ajax( {    
		    url: $('#userContextPath').val() + '/domainAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	// $('#allRoles').empty();
		    	 $('#domain').empty();
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			
		    			for(var i=0;i<datas[attr].length;i++){
		    				 $('#domain').append(
				                        "<option value='"+datas[attr][i].name+"'>"+datas[attr][i].name
				                                + "</option>");
		    			}
		    		}
		    	 }
		    	 
		        
		     },    
		     error : function() {    
		          // view("异常！");    
		          alert("异常！");    
		     }    
		}); */
}
function searchUserCate(){
	var url = $('#userContextPath').val() + "/userCateAction!datagrid.action";
	$.getJSON(url, function(json) {
		$('#admin_yhgl_add_userCategory').combobox({
			data : json.rows,
			valueField:'name',
//     valueField:'usertypeid',
			textField:'name',  
			
		});
	});	 
	
}

function  searchRoleforBatch(){
	 var url = $('#userContextPath').val() + "/roleAction!datagrid.action";
     $.getJSON(url, function(json) {
     $('#yhgl_addDilog_batch_btn_filter').combobox({
     data : json.rows,
     valueField:'roleid',
     textField:'rolename',  
     value:'5',
    	 
     });
     });	
	
}
function searchRole(){
	
	 var url = $('#userContextPath').val() + "/roleAction!datagrid.action";
     $.getJSON(url, function(json) {
     $('#admin_yhgl_add_allRoles').combobox({
     data : json.rows,
     valueField:'roleid',
     textField:'rolename',  
     value:'5',
    	 
     });
     });	
	/* $.ajax( {    
		    url:$('#userContextPath').val() + '/roleAction!datagrid.action',// 跳转到 action    
		     data:{    
		             selRollBack : selRollBack,    
		             selOperatorsCode : selOperatorsCode,    
		             PROVINCECODE : PROVINCECODE,    
		             pass2 : pass2    
		    },     
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 $('#allRoles').empty();
		    	 
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
		});  */
}



function  selfWriteCheck(){
/*	if($('#selfwrite').prop("checked")==true){
		$('#usercode').textbox('textbox').unbind('focus');
		
	}else{
		 $('#usercode').textbox('textbox').bind('focus', function(){
	        	 searchUsercode();
	         });
		$('#usercode').prop("readonly", true);
//		$('#admin_yhgl_addForm input[name=usercode]').prop("readonly", true);
	}*/
	
	
	searchUsercode();
}

function addDilog_batch_add(){
	//取得role
	var ris=$('#yhgl_addDilog_batch_btn_filter').combobox('getValue'); 
	//var ris2=$('#yhgl_addDilog_batch_btn_filter').combobox('select'); 
//		 for(var k=0;k<ris.length;k++){
//			rightarr+=ris[k].value+",";
//		} 
//		rightarr+=ris.value;
//		$("#comment").val(ris);
	//取得所选中的行数据
		var rows = $('#admin_yhgl_addDilog_batch_datagrid').datagrid('getChecked');
		if (rows.length > 0) {
					for ( var i = 0; i < rows.length; i++) {
//						ids.push(rows[i].userid);
					//	var row = $('#datagrid').datagrid('getData').rows[rows[i].userid];
//						查重
						console.info(rows[i].name);
						submitBatchSysUser(rows[i],ris);
						
						//$('#admin_yhgl_addDilog_batch_datagrid').datagrid('load');
						
						
						$('#admin_yhgl_datagrid').datagrid('reload', {});
						$('#admin_yhgl_addDilog_batch').dialog('close');
//						save   setpwd   setrole
				
					}
					
		}
	
	//关闭dialog
	//刷新data
}

function user_batch_append(){
	$('#admin_yhgl_addDilog_batch').dialog('open');
	//清空form中的textbox
	$('#admin_yhgl_addForm_batch').form('clear');
     searchRoleforBatch();
}
function user_append() {
	
 /*  	 var url = "${pageContext.request.contextPath}/domainAction!datagrid.action";
     $.getJSON(url, function(json) {
     $('#admin_yhgl_addForm input[name=domain]').combobox({
     data : json.rows,
     valueField:'domainid',
     textField:'name'   */
/*      onSelect:function(record){
    	 var domainid=record.domainid;
    	 var labUrl="${pageContext.request.contextPath}/labAction!datagrid.action";
    	 $.getJSON(labUrl,{domaincode:domainid},function(labjson){
    		 $('#lab').combobox({
                 data : labjson.rows,
                 valueField:'labid',
                 textField:'name'
    	 });
    	
     });
     }  */
    	 /*
     });
     });	 */
	
	
	$('#admin_yhgl_addDialog').dialog('open');
	//清空form中的textbox
	$('#admin_yhgl_addForm').form('clear');
     searchDomain();
     searchRole();
     searchUserCate();
      $('#usercode').textbox('textbox').bind('focus', function(){
    	 searchUsercode();
     }); 
      
//      $('#admin_yhgl_add_usercode').numberbox('textbox').bind('onblur', function(){
//    	  userCheck();
//      }); 
   
      
      
	/*  $.ajax( {    
		    url:'${pageContext.request.contextPath}/domainAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 $('#allRoles').empty();
		    	 
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			
		    			for(var i=0;i<datas[attr].length;i++){
		    				//alert(datas[attr][i].rightname);
		    				 $('#domain').append(
				                        "<option value='"+datas[attr][i].domainid+"'>"+datas[attr][i].name
				                                + "</option>");
		    			}
		    		}
		    	 }
		    	 
		        
		     },    
		     error : function() {    
		          // view("异常！");    
		          alert("异常！");    
		     }    
		});  */
     
   //  $('#allRoles').remove();
	/*  $.ajax( {    
		    url:'${pageContext.request.contextPath}/roleAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 $('#allRoles').empty();
		    	 
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
		});   */
     

	
	
}



function user_remove() {
	var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelected');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelections');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].userid);
			
				}
				$.ajax({
					url : $('#userContextPath').val() + '/userAction!remove.action',
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


function getUpyhris(){
	var ris=$('#admin_yhgl_updateForm select[name=roles]').find("option");	
	var rightarr="";
	for(var k=0;k<$('#admin_yhgl_updateForm select[name=roles]').find("option").length;k++){
		rightarr+=ris[k].value+",";
	}
	
	$('#admin_yhgl_updateForm input[name=comment]').attr("value",rightarr);
	
}

function editfun(){
	var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
	if(rows.length==1){
	var d=$('<div/>').dialog({
		width:600,
		height:500,
		href:$('#userContextPath').val() + '/performance/modules/admin/userEdit.jsp',
		modal:true,
		title:'修改用户',
		buttons:[{
			text:'修改',
			handler:function(){
				$('#admin_yhgl_updateForm').form('submit',{
					
					url:$('#userContextPath').val() + '/userAction!edit.action',
					beforeSubmit:getUpyhris(),
					success : function(r) {
						var objr = jQuery.parseJSON(r);
						if (objr.success) {
						d.dialog('close');
						$('#admin_yhgl_datagrid').datagrid('updateRow',{
							index:$('#admin_yhgl_datagrid').datagrid('getRowIndex',r[0].userid),
							row:objr.obj
						});
							$('#admin_yhgl_datagrid').datagrid('reload');
						}
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
			 $('#admin_yhgl_updateForm input[name=userid]').val(rows[0].userid); 
			 $('#admin_yhgl_updateForm input[name=name]').val(rows[0].name); 
		 	 $('#admin_yhgl_updateForm input[name=usercode]').val(rows[0].usercode); 
		 	/*  $('#admin_yhgl_updateForm input[name=pwd]').val(rows[0].pwd);  */
		 	//$('#admin_yhgl_updateForm select[name=allRoles]').val(rows[0].roles.rolename); 
		 	/* for(var j=0;j<rows[0].roles.length;j++){
		 		
			$('#admin_yhgl_updateForm select[name=allRoles]').attr('value',rows[0].roles[j].roleid);
		 	} */
			
			
		 	for(var j=0;j<rows[0].roles.length;j++){
		 		//alert(rows[0].roles[j].rolename);
		 	
		 		
				$('#admin_yhgl_updateForm select[name=allRoles]').append(
                        "<option value='"+rows[0].roles[j].roleid+"'>"+rows[0].roles[j].rolename
                                + "</option>");
			}
		 	/* 
		 	设置select默认选中值
			$('#test').attr('value','2');
		 	 */
		 	
		/* $('#admin_yhgl_updateForm input[name=roles]').val(rows[0].roles.rolename); 
		 	for(var j=0;j<rows[0].roles.length;j++){
				$('#admin_yhgl_updateForm select[name=roles]').append(
                        "<option value='"+rows[0].roles[j].roleid+"'>"+rows[0].roles[j].rolename
                                + "</option>");
			} */
			 
		 	 
		 	 /*	 $('#admin_yhgl_updateForm input[name=roledesc]').val(rows[0].roledesc);
			 $('#admin_yhgl_updateForm input[name=uprights]').val(rows[0].rights.rightname);  */
			/* $('#admin_yhgl_updateForm').form('load',rows[0]); */
		}
	});
}else{
	$.messager.alert('提示','请选择一条数据进行修改');
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
		var ris=$('#admin_yhgl_add_allRoles').combobox('getValue'); 
	//	var ris=$('#admin_yhgl_add_allRoles').find("option:selected");
//		var ris=$('#admin_yhgl_add_allRoles').find("option:selected");
//		var ris=$('#allRoles').find("option:selected");
		var rightarr="";
		
		 for(var k=0;k<ris.length;k++){
			rightarr+=ris[k].value+",";
		} 
		rightarr+=ris.value;
		//$("#comment").attr("value",rightarr);
		//$("#comment").attr("value",ris[0].value);
		$("#comment").val(ris);
	}
/* 		function getyhris(){
		var ris=$('#selRoles').find("option");	
		var rightarr="";
		for(var k=0;k<$('#selRoles').find("option").length;k++){
			rightarr+=ris[k].value+",";
			
			
		}
		
		$("#comment").attr("value",rightarr);
		
	} */
	
	function resetPasswd(){
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if(rows.length > 0){
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].userid);
			}

			$.ajax({
					url : $('#userContextPath').val() + '/userAction!resetPWD.action',
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
	}
	
	function searchUsercode(){
		
		$('#admin_yhgl_add_usercode').val('');
		var top = $('#admin_yhgl_add_usercode').offset().top + 30;
		var left = $('#admin_yhgl_add_usercode').offset().left;
		//	$('#admin_yhgl_addDilog_title').window('open').window('resize',{width:'400px',height:'440px',top: top,left:left});
			
			openDialog("admin_yhgl_addDilog_title");
	}
	
	function submitSysUser(){
		var temp=$('#admin_yhgl_add_usercode').textbox('getValue');
//		var temp=$('#admin_yhgl_add_usercode').val();
		
		$.ajax( {    
		   url : $('#userContextPath').val() +'/userAction!userCheck.action',// 跳转到 action    
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
						msg : '该用户为非法用户或该用户已经存在，不能重复添加！！'
					});
					$('#admin_yhgl_addDialog').dialog('close');
					
				}else{
						$('#admin_yhgl_addForm').form('submit', {
						url : $('#userContextPath').val() +'/userAction!add.action',
						beforeSubmit:getyhris(),
						success : function(b) {
							var obj = jQuery.parseJSON(b);
							if (obj.success) {
								$('#admin_yhgl_datagrid').datagrid('load');
								$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#selRoles').find('option').remove();
					        $('#allRoles').find('option').remove();
							$('#admin_yhgl_addDialog').dialog('close');
						}
					});
					
				}

				
		        
		     },    
		     error : function() {  
		    	alert('用户检查出错');  
		     }    
		}); 
		
	}
	
	//
	function submitBatchSysUser(row,role){
		//var temp=$('#admin_yhgl_add_usercode').textbox('getValue');
//		var temp=$('#admin_yhgl_add_usercode').val();
		var temp=row.usercode;
		$.ajax( {    
			url : $('#userContextPath').val() +'/userAction!userCheck.action',// 跳转到 action    
			type:'post', 
			ContentType: "application/json; charset=utf-8",
			data : {
				ids : temp
			},
			cache:false,    
			dataType:'json',    
			success:function(res) {  
				//var objr = jQuery.parseJSON(res);
				
				if (res.success) {
					$.messager.show({
						title : '提示',
						msg : '用户'+row.name+'为非法用户或该用户已经存在，不能重复添加！！'
					});
					//$('#admin_yhgl_addDialog').dialog('close');
					
				}else{
					
					$.ajax({
						url : $('#userContextPath').val() +'/userAction!add.action',
						//beforeSubmit:getyhris(),
						data:{usercode:row.usercode,name:row.name,domain:row.domain,comment:role,department:row.department,lab:row.lab,userTy:row.userTy,userCa:row.userCa},
						dataType : 'json',
						type:'post',
						contentType:'application/x-www-form-urlencoded; charset=UTF-8',
						success : function(obj) {
							//var obj = jQuery.parseJSON(r);
							//$('#admin_yhgl_datagrid').datagrid('load', {});
							if (obj.success) {
								$('#admin_yhgl_datagrid').datagrid('load');
								$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								//$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
					
					
				}
				
				
				
			},    
			error : function() {  
				alert('用户检查出错');  
			}    
		}); 
		
	}
	
	function searchPersonFunc(){
		/*alert(
				$('#admin_yhgl_search_usercode').val()
				);*/
		
		$('#admin_yhgl_addDilog_datagrid').datagrid({
			url : $('#userContextPath').val() + '/personAction!datagrid.action',
			fit : false,
			queryParams: {usercode:$('#admin_yhgl_search_usercode').val(),name:$('#admin_yhgl_search_usercode').val(),},
			fitColumns : true,
			border : true,
			pagination : true,
			pagePosition: 'bottom',
			idField : 'personid',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'savetime',
			sortOrder : 'desc',
			singleSelect:true,
			checkOnSelect : false,
			selectOnCheck : true,
			onDblClickRow: function (rowIndex, rowData) {  
				 //jquery赋值方式
				/* $('#admin_yhgl_addForm input[name=usercode]').val(rowData.usercode);
		    	 $('#admin_yhgl_addForm input[name=name]').val(rowData.name);*/
				console.info(rowData);
				//easyui textbox赋值方式
		    	 $('#admin_yhgl_add_usercode').textbox('setValue',rowData.usercode);
		    	 $('#admin_yhgl_addForm input[id=name]').textbox('setValue',rowData.name);
		    	 $('#admin_yhgl_add_domain').combobox('setValue',rowData.domain);
		    	 $('#admin_yhgl_add_userCategory').combobox('setValue',rowData.personcate);
		    	 $('#admin_yhgl_add_department').val(rowData.department);
		    	 $('#admin_yhgl_add_lab').val(rowData.lab);
		    	 $('#admin_yhgl_add_userTy').val(rowData.userTy);
		    	 $('#admin_yhgl_addDilog_title').dialog('close');

			},
			columns : [ [ {
				field : 'personid',
				title : '编号',
				width : 50,
				checkbox : true
			}, {
				field : 'name',
				title : '人员名称',
				width : 100,
				sortable : true
			}, {
				field : 'usercode',
				title : '用户编号',
				width : 70
			}, {
				field : 'lab',
				title : '研究室',
				width : 100
			}, {
				field : 'domain',
				title : '领域',
				width : 100
			} ] ],
			width: 350,
			height: 340,
			autoRowHeight: false
		});
	}
	function searchBatchPersonFunc(){
		/*alert(
				$('#admin_yhgl_search_usercode').val()
				);*/
		
		$('#admin_yhgl_addDilog_batch_datagrid').datagrid({
			url : $('#userContextPath').val() + '/personAction!datagrid.action',
			fit : false,
			queryParams: {usercode:$('#admin_yhgl_search_batch_usercode').val(),name:$('#admin_yhgl_search_batch_usercode').val(),},
			fitColumns : true,
			border : true,
			pagination : true,
			pagePosition: 'bottom',
			idField : 'personid',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'savetime',
			sortOrder : 'desc',
			singleSelect:false,
			checkOnSelect : false,
			selectOnCheck : true,
			columns : [ [ {
				field : 'personid',
				title : '编号',
				width : 50,
				checkbox : true
			}, {
				field : 'name',
				title : '人员名称',
				width : 100,
				sortable : true
			}, {
				field : 'usercode',
				title : '用户编号',
				width : 70
			}, {
				field : 'lab',
				title : '研究室',
				width : 100
			}, {
				field : 'domain',
				title : '领域',
				width : 100
			} ] ],
			width: 350,
			height: 340,
			autoRowHeight: false
		});
	}