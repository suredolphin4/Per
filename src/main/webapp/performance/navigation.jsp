<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript">

function logOut()
{
	if(confirm("您确定要退出吗?"))
	{
		window.location.href = "${pageContext.request.contextPath}/loginAction!toLogin.action";
	}
}
</script>

<div class="nav">
	<div>
		<div class="tips-clicked" onmouseover="this.style.cursor='hand'">
			<a onclick="logOut()">退出</a>
		</div>
		<div class="tips-clicked" onmouseover="this.style.cursor='hand'">
			<a onclick="changePassword()">修改密码</a>
		</div>
		<!--
		<div class="tips-clicked"><a>邮件</a></div>
		-->
		<div class="tips">
			<a>你好：</a>
			<s:if test="#session['user'] != null">
			<a>
				<s:property value="#session['user'].name" />
			</a>
			</s:if>
			</div>
		</div>
		<div class="perfTitle">
			<div class="wrapper">
				<div>
					<img class="log" alt="" src="images/u16.png" />
				</div>
				<div class="log2">
					<p>
						<span>绩效管理系统</span>
					</p>
				</div>
			</div>
		</div>
	</div>
<div id="menuContainer" class="easyui-ribbon" data-options="border: false, plain:true, headerMargin: 320" style="width:100%;">
	<div title="各类信息查询">
		<div class="ribbon-toolbar">
			<a id='tab0-1'  title="论文" class="easyui-linkbutton" data-options="selected:true, plain:true, scripts:'thesis', moduleName:'thesis', name:'thesis',iconCls:'icon-thesis-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">论文</li></a>
		</div>
		
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="咨询报告" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'report', moduleName:'report', name:'report',iconCls:'icon-report-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">咨询报告</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="奖励" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'award', moduleName:'award', name:'award',iconCls:'icon-reward-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">奖励</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="著作" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'writings', moduleName:'writings', name:'writings',iconCls:'icon-writings-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">著作</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="规划" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'plan', moduleName:'plan', name:'plan',iconCls:'icon-plan-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">规划</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="标准" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'standed', moduleName:'standed', name:'standed',iconCls:'icon-standard-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">标准</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="地图" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'map', moduleName:'map', name:'map',iconCls:'icon-map-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">地图</li></a>
		</div>

		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="第三方评估" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'thirdAssess', moduleName:'thirdAssess', name:'thirdAssess',iconCls:'icon-thirdAssess-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">第三方评估</li></a>
		</div>

		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="数据出版" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'publish', moduleName:'publish', name:'publish',iconCls:'icon-publish-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">数据出版</li></a>
		</div>

		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="成果转化" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'achievetrans', moduleName:'achievetrans', name:'achievetrans',iconCls:'icon-achievetrans-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">成果转化</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="软件" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'software', moduleName:'software', name:'software',iconCls:'icon-software-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">软件</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="专利" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'patent', moduleName:'patent', name:'patent',iconCls:'icon-patent-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">专利</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="项目" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'project', moduleName:'project', name:'project',iconCls:'icon-project-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">项目</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="授课" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'lessons', moduleName:'lessons', name:'lessons',iconCls:'icon-teach-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">授课</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="研究生获奖" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'graduate', moduleName:'graduate', name:'graduate',iconCls:'icon-student-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">研究生获奖</li></a>
		</div>
	</div>
		  
	<div title="绩效总表">
		<div class="ribbon-toolbar">
			<a id='tab1-1' title="科研成果数量统计" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'research', moduleName:'research', name:'research',iconCls:'icon-jx1-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">科研成果数量统计</li></a>
		</div>
			
		<!--	            
		<div class="ribbon-toolbar-sep"></div> 
		<div class="ribbon-toolbar">
			<a title="承担任务数量统计" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'task', moduleName:'task', name:'task',iconCls:'icon-jx2-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">承担任务数量统计</li></a>
		</div>
		-->			            
		<div class="ribbon-toolbar-sep"></div> 
		<div class="ribbon-toolbar">
			<a title="总和绩效分值" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'score', moduleName:'score', name:'score',iconCls:'icon-jx3-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">总和绩效分值</li></a>
		</div>
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="项目表统计" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'projectstatistic', moduleName:'projectstatistic', name:'projectstatistic',iconCls:'icon-jx2-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">项目表统计</li> </a>
		</div>
		<div class="ribbon-toolbar-sep"></div>
		<div class="ribbon-toolbar">
			<a title="工资统计" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'salarystatistic', moduleName:'salarystatistic', name:'salarystatistic',iconCls:'icon-jx4-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">工资统计</li> </a>
		</div>
	</div>
	    				    
	<div title="系统管理">
		<div class="ribbon-toolbar">
			<a id='tab2-1' title="用户管理" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'userAdmin', moduleName:'admin', name:'userAdmin.jsp',iconCls:'icon-manage1-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">用户管理</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div> 
		<div class="ribbon-toolbar">
			<a title="字典表管理" class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'', moduleName:'admin', name:'thesis',iconCls:'icon-manage2-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">字典表管理</li></a>
		</div>
					            
		<div class="ribbon-toolbar-sep"></div> 
		<div class="ribbon-toolbar">
			<a title="绩效因子管理"  class="easyui-linkbutton" data-options="selected:false, plain:true, scripts:'', moduleName:'admin', name:'thesis',iconCls:'icon-manage3-large',iconAlign:'top',size:'large'"><li class="ribbon-toolbar-text">绩效因子管理</li></a>
		</div>
	</div>
</div>



	<!-- 修改密码界面 -->
	<div id="change_password_dialog" class="easyui-dialog"
		data-options="region:'top',title:'修改密码',buttons:'#passwordButtons',border:false,closed:true,modal:true"
		style="width:320px;height: 180px;padding-top:10px;padding-left:10px">
		
		<table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
			<tr>
				<th>新密码：</th>
				<td><input id="password" name="password" validType="length[6,10]" class="easyui-validatebox" required="true" type="password" value=""/></td>
			</tr>
			<tr>
				<th>确认密码：</th>
				<td><input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /></td>
			</tr>
		</table>

		<div id="admin_thesis_addDialog_authordiv" class="easyui-layout"
			data-options="fit:true,border:false">
			<div data-options="region:'center',border:false"
				style="width:300px;height: 200px;">
				<table id="admin_thesis_addauthorDialog_datagrid"></table>
			</div>
		</div>

	</div>
	<div id="passwordButtons" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="change_password_btn_ok()">确认</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="change_password_btn_cancel()">取消</a>
    </div>

</html>
