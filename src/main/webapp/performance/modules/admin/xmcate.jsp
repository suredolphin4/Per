<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
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
	});

	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
	}
	function clearFun() {
		$('#admin_yhgl_layout input[name=name]').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
	}
	function append() {
		 $.ajax( {    
			    url:'${pageContext.request.contextPath}/roleAction!datagrid.action',// 跳转到 action    
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
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].userid);
				
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/userAction!remove.action',
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
</script>
	<div  id="admin_yhgl_partBar"  class="easyui-dialog" data-options="region:'north',title:'查询条件',border:false,closed:true" style="width:400px;height: 100px;padding-top:20px;padding-left:20px">
		<form id="admin_yhgl_searchForm" method="post">
			名称模糊查询：<input name="name"/> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">关闭</a>
		</form>
	</div>
	<div  id="admin_yhgl_dateBar" class="easyui-dialog"   data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:500px;height: 100px;padding-top:20px;padding-left:20px">
		<form id="admin_yhgl_searchTimeForm"  method="post">
			起始日期：<input id="dd1" type="text" class="easyui-datebox" required="required">
			截止日期：<input id="dd2" type="text" class="easyui-datebox" required="required">
			
		</form>
	</div>
	
	<div  id="admin_yhgl_comBar" class="easyui-dialog"   data-options="region:'center',title:'查询条件',border:false,closed:true" style="width:300px;height: 100px;padding-top:20px;padding-left:40px">
		<form id="admin_yhgl_searchstutasForm" method="post">
			<select class="easyui-combobox" name="state" style="width:200px;">
        <option value="AL">审核中</option>
        <option value="AK">已提交</option>
        <option value="AZ">审核通过</option>
    </select>
			
		</form>
	</div>
	
	
<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>

<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_yhgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						beforeSubmit:getyhris(),
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
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
			}]" style="width: 500px;height:500px;" align="center">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<!-- <th>编号</th>
				<td><input name="id" readonly="readonly" />
				</td> -->
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
				<!-- <th>创建时间</th>
				<td><input name="createdatetime" readonly="readonly" />
				</td> -->
			</tr>
			<tr>
				<!-- <th>最后修改时间</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td> -->
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>备注</th>
				<td><input name="comment"  id="comment" /></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>角色</th>
				<td><select name="allRoles"   id="allRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="but_left" type="button" onclick="toyhleft()"></input></td>
				<td><input name="button2" type="button" onclick="toyhright()"></input></td>

				<td><select name="selRoles" id="selRoles" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>