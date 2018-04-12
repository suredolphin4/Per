<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/performance/head.jsp"%>

<script type="text/javascript" src="js_myselfv2/layout/mainFrame.js?v1.0.0"></script>
</head>
<body>
	<!-- Menu -->
	<div id="header" >
		<%@include file="/performance/navigation.jsp"%>	
	</div>
	
	<!--  content and status -->
	<div id="container" class="easyui-layout" style="height:100%; width:100%;">		
		<!-- Content(datagrid) -->
		<div id="centerpage" name="centerpage" data-options="region:'center',title:'',fit:true">
			<div id="contentLayout" class="easyui-layout" data-options="fit:true">

			</div>
		</div>
	</div>
	
	<!-- Status -->
	<div id="footer">
    	<div class="clear"></div>

	    <div id="bottom">
	      <ul>
	      	<!-- 
	        <li><a href="/sitemap/">Site map</a></li>
	         -->
	      </ul>
	      <!-- 	      <p>© Copyright 北京博阳世通信息技术有限公司 2015</p>
 -->	     
 	<div class="footlogo">
        <img src="images/logofont(1).png"/>
    </div>
  <div class="clear"></div>
  
	    </div>
  	</div>
	
	<!-- 存放登录角色 -->
	<input name="urole" id="urole" type="hidden" value="${session.urole}"/>
	<input name="usercode" id="usercode" type="hidden" value="${session.user.getUsercode()}"/>
	<input name="username" id="username" type="hidden" value="${session.user.getName()}"/>
	<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}"/>
</body>
</html>