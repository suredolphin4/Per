<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>地理所绩效管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
<script type="text/javascript">
$('#ff').form('submit', {
	url : '${pageContext.request.contextPath}/user/userAction!getUserAction.action',
    onSubmit: function(){
		// do some check
		// return false to prevent submit;
		alert(data);
    },
    success:function(data){
		alert(data);
    }
});

</script>
  </head>
  
  <body>
   <form id="ff" method="post">
    <div>
		<label for="name">Name:</label>
		<input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
    </div>
    <div>
		<label for="email">Email:</label>
		<input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />
    </div>
    
</form>
  </body>
</html>
