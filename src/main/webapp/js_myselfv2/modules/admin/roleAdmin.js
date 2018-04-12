/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$('#admin_jsgl_datagrid').datagrid({
		url : $('#contextPath').val() + '/roleAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'roleid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'rolename',
		sortOrder : 'asc',
		/*pagePosition : 'both',*/
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'roleid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'rolename',
			title : '角色名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'rolevalue',
			title : '角色值',
			width : 150
			
		}, {
			field : 'createdatetime',
			title : '创建时间',
			width : 150,
			sortable : false
		}, {
			field : 'modifydatetime',
			title : '最后修改时间',
			width : 150,
			sortable : false
		},{
			
			field : 'rights',
			title : '权限',
			width : 150,
			sortable : false
			
		}] ],
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
				editfun();
			}
		}, '-' ]
	});
};

/******************************************* Role Admin Manage Operator ******************************************/

function searchFun() {
	$('#admin_jsgl_datagrid').datagrid('load', serializeObject($('#admin_jsgl_searchForm')));
}
function clearFun() {
	$('#admin_jsgl_layout input[name=name]').val('');
	$('#admin_jsgl_datagrid').datagrid('load', {});
}
function append() {
	
	 $.ajax( {    
	    url: $('#contextPath').val() + '/rightAction!datagrid.action',// 跳转到 action    
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
	    				 $('#all').append(
			                        "<option value='"+datas[attr][i].rightid+"'>"+datas[attr][i].rightname
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
	$('#admin_jsgl_addForm input').val('');
	$('#admin_jsgl_addDialog').dialog('open');

}
function editfun(){
	
	
	 
	var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
	if(rows.length==1){
	var d=$('<div/>').dialog({
		width:600,
		height:500,
		href: $('#contextPath').val() + 'performance/modules/admin/roleEdit.jsp',
		modal:true,
		title:'修改角色',
		buttons:[{
			text:'修改',
			handler:function(){
				$('#admin_jsgl_updateForm').form('submit',{
					
					url: $('#contextPath').val() + '/roleAction!edit.action',
					success : function(r) {
						var objr = jQuery.parseJSON(r);
						if (objr.success) {
							d.dialog('close');
						
						
						
						$('#admin_jsgl_datagrid').datagrid('updateRow',{
							index:$('#admin_jsgl_datagrid').datagrid('getRowIndex',rows[0].roleid),
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
			console.info(rows[0]);
			//回显
			 $('#admin_jsgl_updateForm input[name=roleid]').val(rows[0].roleid); 
			 $('#admin_jsgl_updateForm input[name=rolename]').val(rows[0].rolename); 
			 $('#admin_jsgl_updateForm input[name=rolevalue]').val(rows[0].rolevalue); 
			 $('#admin_jsgl_updateForm input[name=roledesc]').val(rows[0].roledesc);
			
			 $('#admin_jsgl_updateForm input[name=uprights]').val(rows[0].rights.rightname); 
			 
			/* $('#admin_jsgl_updateForm').form('load',rows[0]); */
			
		}
		
		
	});
	
	

	 
	
}else{
	$.messager.alert('提示','请选择一条数据进行修改');
}
}
function remove() {
	var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelected');
	//var rows = $('#admin_yhgl_datagrid').datagrid('getSelections');
	console.info(rows);
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].roleid);
				}
				$.ajax({
					url : $('#contextPath').val() + '/roleAction!remove.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(r) {
						$('#admin_jsgl_datagrid').datagrid('load');
						$('#admin_jsgl_datagrid').datagrid('unselectAll');
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
// 4.删除select中选中的项      
function jsRemoveSelectedItemFromSelect(objSelect) {          
    var length = objSelect.options.length - 1;      
    for(var i = length; i >= 0; i--){      
        if(objSelect[i].selected == true){      
            objSelect.options[i] = null;      
        }      
    }      
}   



function toleft(){
	
	
	
 var sel=$('#all').find("option:selected");
 if(sel.lenght!=0){
	 
    for(var i=0;i<sel.length;i++){ 
    		
    	$("#rights option[value='"+sel[i].value+"']").remove();
    		
    	 $('#rights').append("<option value='"+sel[i].value+"'>"+sel[i].text
			                                + "</option>");
			 
    }
 }else{
	 $.messager.show({
			title : '提示',
			msg : '请选择权限'
		});
 }
 
}

function toright(){
	 var sel=$('#rights').find("option:selected");
	 for(var i=0;i<sel.length;i++){ 
	    	//alert(sel[i].text);
	    	/*  $('#selright').append("<option value='"+sel[i].value+"'>"+sel[i].text
				                                + "</option>"); */
	    	 
	    	 $("#rights option[value='"+sel[i].value+"']").remove();
	        /* var varitem = new Option($(str+"1").options[i].text,$(str+"1").options[i].value);  
	        $(str+"0").options.add(varitem); */  
	    }  
}
function toUpright(){
	 var sel=$('#uprights').find("option:selected");
	 for(var i=0;i<sel.length;i++){ 
	    	//alert(sel[i].text);
	    	alert(sel[i].value);
	    	/*  $('#selright').append("<option value='"+sel[i].value+"'>"+sel[i].text
				                                + "</option>"); */
	    	 
	    	 $("#uprights option[value='"+sel[i].value+"']").remove();
	        /* var varitem = new Option($(str+"1").options[i].text,$(str+"1").options[i].value);  
	        $(str+"0").options.add(varitem); */  
	    }  
}
function toUpleft(){
	
	
	 var sel=$('#upall').find("option:selected");
	 if(sel.lenght!=0){
		 
	    for(var i=0;i<sel.length;i++){ 
	    		
	    	$("#uprights option[value='"+sel[i].value+"']").remove();
	    		
	    	 $('#uprights').append("<option value='"+sel[i].value+"'>"+sel[i].text
				                                + "</option>");
				 
	    }
	 }else{
		 $.messager.show({
				title : '提示',
				msg : '请选择权限'
			});
	 }
	 
	}
function getris(){
	var ris=$('#rights').find("option");	
	var rightarr="";
	for(var k=0;k<$('#rights').find("option").length;k++){
		rightarr+=ris[k].value+",";
		
		
	}
	
	$("#roledesc").attr("value",rightarr);
	
}
