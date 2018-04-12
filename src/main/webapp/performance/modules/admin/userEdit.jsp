<%@ page language="java" pageEncoding="UTF-8"%>
	<form id="admin_yhgl_updateForm" method="post">
	<script type="text/javascript">
/* if($('#admin_yhgl_updateForm input[name=allRole]  option').length==0){	
	
	 $.ajax( {    
		    url:'${pageContext.request.contextPath}/roleAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			for(var i=0;i<datas[attr].length;i++){
		    				//alert(datas[attr][i].rightname);
		    				
		    				$('#admin_yhgl_updateForm select[name=allRole]').append(
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
} */


function rolechange(){
	
	alert("aaa");
	
	 $.ajax( {    
		    url:'${pageContext.request.contextPath}/roleAction!datagrid.action',// 跳转到 action    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {  
		    	 var datas= eval(data);  
		    	 
		    	 for(attr in datas){
		    		if(datas[attr].length>1){
		    			for(var i=0;i<datas[attr].length;i++){
		    				//alert(datas[attr][i].rightname);
		    				
		    				$('#admin_yhgl_updateForm select[name=allRole]').append(
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
}
function toUpyhleft(){
	
	
	
	 var sel=$('#admin_yhgl_updateForm select[name=allRole]').find("option:selected");
	 if(sel.lenght!=0){
		 
	    for(var i=0;i<sel.length;i++){ 
	    		
	    	$("#roles option[value='"+sel[i].value+"']").remove();
	    	
	    	 $('#admin_yhgl_updateForm select[name=roles]').append("<option value='"+sel[i].value+"'>"+sel[i].text
				                                + "</option>");
				 
	    }
	 }else{
		 $.messager.show({
				title : '提示',
				msg : '请选择权限'
			});
	 }
	 
	}
	
	function toUpyhright(){
		 var sel=$('#admin_yhgl_updateForm select[name=roles]').find("option:selected");
		 for(var i=0;i<sel.length;i++){ 
		    	//alert(sel[i].text);
		    	/*  $('#selright').append("<option value='"+sel[i].value+"'>"+sel[i].text
					                                + "</option>"); */
		    	 
		    	 $("#roles option[value='"+sel[i].value+"']").remove();
		        /* var varitem = new Option($(str+"1").options[i].text,$(str+"1").options[i].value);  
		        $(str+"0").options.add(varitem); */  
		    }  
	}

</script>
			<input name="userid"   hidden="hidden" />
		<table  class="altrowstable" cellspacing=”0″>
		
			<tr>
				
				<th>登录名称</th>
				<td  colspan="1"><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			
			<tr>
				<th>员工编号</th>
				<td  colspan="1"><input name="usercode" />
				</td>
			</tr>
			<tr>
				<th></th>
				<td  colspan="1"><input name="comment"  id="comment" type="hidden" /></td>
			</tr>
			<tr>
				<th>角色</th>
 				<td  colspan="1"><input name="allRole"   id="allRole" style="width:150px"  class="easyui-co   onchange="rolechange()"></input></td>

 	<!-- <td><select name="allRole"   id="allRole" style="width:150px" size="10" multiple="multiple"></select></td>
				<td><input name="yhgl_upbut_left" type="button" onclick="toUpyhleft()"></input></td>
				<td><input name="yhgl_upbutton2" type="button" onclick="toUpyhright()"></input></td>

				<td><select name="roles" id="roles" style="width:150px" size="10" multiple="multiple"></select></td>
				<th></th>
				<td></td> -->
			</tr>
		</table>
	</form>
