<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
    <script type="text/javascript" src="js_myselfv2/indexform.js"></script>
    <link rel="stylesheet" href="css/indexform.css" type="text/css"></link>
    <link rel="stylesheet" href="css/styles.css" type="text/css"></link>
    <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="jslibv2/syUtil.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div id="buts" name="buts">
    <div><s:a action="thesisAction!toThesis.action" namespace="/">著作 &nbsp;&nbsp;&nbsp;</s:a></div>
    <div><s:a action="thesisAction!toThesisEdit.action" namespace="/">论文</s:a></div>
</div>
<div id="pags" name="pags">

</div>
</body>
</html>
