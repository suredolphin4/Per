<%@ page language="java" pageEncoding="UTF-8"%>
<%
	response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
	response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0
%>
<script type="text/javascript">
	$(function() {
		$('#admin_jsgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/roleAction!datagrid.action',
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
	});

	function searchFun() {
		$('#admin_jsgl_datagrid').datagrid('load', serializeObject($('#admin_jsgl_searchForm')));
	}
	function clearFun() {
		$('#admin_jsgl_layout input[name=name]').val('');
		$('#admin_jsgl_datagrid').datagrid('load', {});
	}
	function append() {
		
		 $.ajax( {    
		    url:'${pageContext.request.contextPath}/rightAction!datagrid.action',// 跳转到 action    
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
			href:'${pageContext.request.contextPath}/admin/jsglEdit.jsp',
			modal:true,
			title:'修改角色',
			buttons:[{
				text:'修改',
				handler:function(){
					$('#admin_jsgl_updateForm').form('submit',{
						
						url:'${pageContext.request.contextPath}/roleAction!edit.action',
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
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].roleid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/roleAction!remove.action',
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
	
</script>
<div id="admin_jsgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="admin_jsgl_searchForm">
			检索用户名称(可模糊查询)：<input name="rolename" /> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_jsgl_datagrid"></table>
	</div>
</div>

<div id="admin_jsgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加角色',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_jsgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!add.action',
						beforeSubmit:getris(),
						data:{rights:tempright},
						<!-- data:{rights:$('#rights > option').attr('selected','selected')}, -->
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_jsgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_jsgl_addDialog').dialog('close');
							}
							
							
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
							$('#rights').find('option').remove();
					        $('#all').find('option').remove();
								$('#admin_jsgl_addDialog').dialog('close');
							
						}
					});
				}
			}]" style="width: 600px;height:500px;" align="center">
	<form id="admin_jsgl_addForm" method="post">
		<table>
			<tr>
				<th>角色名称</th>
				<td><input name="rolename" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>

				<th>角色值</th>
				<td><input name="rolevalue" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>角色描述</th>
				<td><input name="roledesc"  id="roledesc" /></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>权限</th>
				<td><select name="all"   id="all" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="button2" type="button" onclick="toleft()"></input></td>
				<td><input name="button2" type="button" onclick="toright()"></input></td>

				<td><select name="rights" id="rights" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
			</tr>
			 <tr>
			 	<td><input name="tempright" id="tempright" type="hidden"></input></td>
			 </tr>
		</table>
	</form>
</div>