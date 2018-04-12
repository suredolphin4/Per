<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>地理所绩效管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script src="jslibv2/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery.validate.js"></script>
<script type="text/javascript" src="jslibv2/jquery.form.js"></script>
<script src="jslibv2/easyform.js"></script>
<script type="text/javascript" src="js_myselfv2/login_V1.01.js"></script>
<!-- <link rel="stylesheet" href="css/css/style.css"> -->
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="css/loginStyles.css" type="text/css"></link>
<link rel="stylesheet" href="css/axure_rp_page.css" type="text/css"></link>
<!-- <link rel="stylesheet" href="assets/css/reset.css"> -->
 <link rel="stylesheet" href="assets/css/supersized.css">
  <link rel="stylesheet" href="assets/css/style.css">
 
<script src="assets/js/supersized.3.2.7.min.js"></script>
<script src="assets/js/supersized-init.js"></script>

</head>
<body>
	<div id="base" class="">

		<!-- Unnamed (Image) -->
		<div id="u0" class="ax_image">
			<img id="u0_img" class="img " src="images/dlym/u0.png"  style="border-radius:20px; " />
			<!-- Unnamed () -->
			<div id="u1" class="text"></div>

		</div>
		<div id="u22" class="u22cla">

			<img id="u2_img" class="img " src="images/dlym/u2.png">

			<div class="page-container">
 
 			<form id="reg-form"  method="post" action="${pageContext.request.contextPath}/loginAction!ValidateUser.action">
 				<table>
     				<div style="margin-bottom: 25px;">
     				<p>績效管理系統</p>
     				</div>
     				<div style="margin-bottom: 25px;">
					<input type="text" name="usercode" id="usercode"   class="easyui-numberbox" easyform="length:1-16;char-normal;real-time;" message="请输入1-6位字符用户名" easytip="disappear:lost-focus;theme:red;"  ajax-message="用户名已存在!"  placeholder="请输入您的用户名！" />
					</div>
					<div style="margin-bottom: 20px;">
					
					<input type="password" name="pwd" id="pwd"      easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:red;"  placeholder="请输入您的用户密码！"/> 
					</div>
					
					<div style="margin-bottom: 20px;">
      				</div>
    </table>
    
    <div class="buttons"   style="margin-top: 20px;"> 
      <input value="登录" type="submit" id="su" name="su">
    </div>
    <br class="clear">
  </form>
			</div>
		</div>



	

		<!-- Unnamed (形状) -->
		<div id="u12" class="ax_文本">
			<img id="u12_img" class="img " src="resources/images/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u13" class="text"></div>
		</div>

		<!-- Unnamed (形状) -->
		<div id="u14" class="ax_形状">
			<img id="u14_img" class="img " src="images/dlym/u14.png" />
			<!-- Unnamed () -->
			<div id="u15" class="text"></div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u16" class="ax_image">
			<img id="u16_img" class="img " src="images/dlym/u16.png" />
			<!-- Unnamed () -->
			<div id="u17" class="text"></div>
		</div>

		<!-- Unnamed (形状) -->
		<div id="u18" class="ax_h1">
			<img id="u18_img" class="img " src="resources/images/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u19" class="text">
				<p>
					<span>绩效管理系统</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (形状) -->
		<div id="u20" class="ax_形状">
			<img id="u20_img" class="img " src="images/dlym/u10.png" />
			<!-- Unnamed () -->
			<div id="u21" class="text">
				<p>
					<span>中科院地理所-</span><span></span><span>绩效</span><span>管理系统</span>
				</p>
			</div>
		</div>


	</div>

</body>
</html>
