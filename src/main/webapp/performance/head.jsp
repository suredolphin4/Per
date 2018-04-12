<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
  <title>中科院绩效管理系统</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"/>
  <link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/icon.css" type="text/css"/>
  <link rel="stylesheet" href="jslibv2/jquery-easyui-ribbon/ribbon.css" type="text/css"/>
  <link rel="stylesheet" href="jslibv2/jquery-easyui-ribbon/ribbon-icon.css" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="css/common.css"/>
  <link rel="stylesheet" href="css/indexform.css" type="text/css"/>
  <link rel="stylesheet" href="css/styles.css" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="css/writings.css"/>
  <link rel="stylesheet" type="text/css" href="css/award.css"/>
  <link rel="stylesheet" type="text/css" href="css/thesis.css"/>
  <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/plugins/jquery.edatagrid.js"></script>
  <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/plugins/datagrid-dnd.js"></script>
  <script type="text/javascript" src="jslibv2/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="jslibv2/jquery-easyui-ribbon/jquery.ribbon.js"></script>
  <script type="text/javascript" src="jslibv2/jquery.blockUI.js"></script>
  <script type="text/javascript" src="js_myselfv2/common.js"></script>
  <script type="text/javascript" src="js_myselfv2/util.js?v151119"></script>
  <script type="text/javascript" src="jslibv2/syUtil.js"></script>
  <script type="text/javascript" src="jslibv2/jquery.uploadify.v2.1.0.min.js"></script>
  <script type="text/javascript" src="jslibv2/jquery.ajaxfileupload.js"></script>
  <script type="text/javascript" src="js_myselfv2/upload.js"></script>
  <script type="text/javascript" src="js_myselfv2/selectperson_V1.0.1.js"></script>
</head>

