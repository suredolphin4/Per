<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 人员添加界面 -->
<div id="admin_thesis_addDialog_author" class="easyui-dialog"
	data-options="region:'north',title:'人员查询',buttons:'#author_btn',border:false,closed:true,modal:true"
	style="width:450px;height: 500px;padding-top:20px;padding-left:20px">
	
	<table>
		<tr>
			<th>本所人员：</th>
			<td><input type="text" class="easyui-textbox" name="name" id="name" style="width:200px" /></td>
			<th></th>
			<td><a class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchAuthorFunc();">搜索</a></td>
		</tr>
		<tr>
			<th>非本所人员：</th>
			<td><input type="text" class="easyui-textbox" name="outsider" id="outsider" readonly="readonly"  style="width:200px" /></td>
			<th></th>
			<td><input id="noself" name="noself" type="checkbox"
				onclick="onOutsiderCheck()" /></td>
		</tr>
	</table>

	<div id="admin_thesis_addDialog_authordiv" class="easyui-layout"
		data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"
			style="width:400px;height: 200px;">
			<table id="thesis_addauthor_datagrid"></table>
		</div>
	</div>
</div>
<div id="author_btn" >
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="author_btn_ok()">确认</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="author_btn_cancel()">取消</a>
</div>

<!-- 其它人员添加界面 -->
<div id="thesis_addDialog_otherauthor" class="easyui-dialog"
	data-options="region:'north',title:'人员查询',buttons:'#otherauthor_btn',border:false,closed:true,modal:true"
	style="width:900px;height: 500px;padding-top:20px;padding-left:20px">
	<table>
			<tr>
				<th>本所人员：</th>
				<td><input type="text" class="easyui-textbox" name="other_author_name" id="otherauthor_name" style="width:200px" /></td>
				<th></th>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true"
					onclick="searchOtherAuthorFunc();">搜索</a></td>
			</tr>
			<tr>
				<th>非本所人员：</th>
				<td><input type="text" class="easyui-textbox" name="outsider_other" id="outsider_other" style="width:200px" /></td>
			</tr>
	</table>

	<div id="thesis_addotherDialog_authordiv" data-options="fit:true,border:true">
		<div style="float:left;width:400px;height:340px;">
			<table id="thesis_addotherauthorDialog_datagrid" class="easyui-datagrid">
			</table>
		</div>
		<div style="float:left;width:80px;height:340px;text-align:center;">
			<div>
				<input type="button" id="otherauthor_add2list" value="-->" class="easyui-linkbutton" style="width:50px;" onclick="otherauthor_btn_add()"/>
				<br/>
				<br/>
				<input type="button" id="otherauthor_removefromlist" value="<--" class="easyui-linkbutton" style="width:50px;" onclick="otherauthor_btn_remove()"/>
			</div>
		</div>
		<div style="float:left;width:360px;height:340px">
   			<table id="otherperson_result_datagrid" class="easyui-datagrid"  style="width:360px;height:340px"
			title="人员选择结果列表" iconCls="icon-edit" singleSelect="true" fitColumns="true">
        		<thead>
			        <tr>
			            <th field="personname" width="200">人员名称</th>
			            <th field="personcode" width="160">人员编号</th>
			        </tr>
			    </thead>
			</table>
		</div>
	</div>
</div>
<div id="otherauthor_btn" >
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="otherauthor_btn_confirm()">确认</a> 
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="otherauthor_btn_cancel()">取消</a>
</div>