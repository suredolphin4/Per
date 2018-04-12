<%@ page language="java" pageEncoding="UTF-8"%>
<%
	response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
	response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0
%>

	<form id="admin_jsgl_updateForm" method="post">
	<script type="text/javascript">
<!--
/* $(document).ready(function(){ */
if($('#admin_jsgl_updateForm input[name=upall]  option').length==0){	
	
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
		    				 $('#upall').append(
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
}
/* 	});  */
//-->
</script>
		<table>
			<tr>
				<th>角色编号</th>
				<td><input name="roleid" class="easyui-validatebox"   readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>角色名称</th>
				<td><input name="rolename" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				
				<th>角色值</th>
				<td><input name="rolevalue" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>角色描述</th>
				<td><input name="reledesc" />
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>权限</th>
				<td><select name="upall"   id="upall" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="button2" type="button" onclick="toUpleft()"></input></td>
				<td><input name="button2" type="button" onclick="toUpright()"></input></td>

				<td><select name="uprights" id="uprights" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
