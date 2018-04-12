<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/performance/head.jsp"%>

<script type="text/javascript">
	$(function() {

		$("#ydbg").css({
			"display" : "block"
		});
		
		
		$('.topTitle').click(function() {
			var id = $(this).attr("id");
			var x = $(this).parent().index();
			$('a').each(function(index, element) {
				var other_id = $(this).attr("id");
				if (id == other_id) {
					if(id='glxxcx'){
					$('#glxxcx').addClass("active");
					$('#jxzb').removeClass("active");
					$('#sysmanage').removeClass("active");

				} else if(id=='sysmanage'){
					alert("sys");
					$('#glxxcx').removeClass("active");
					$('#jxzb').removeClass("active");
					$('#sysmanage').addClass("active");
				}else if(id=='jxzb')
					$('#glxxcx').removeClass("active");
					$('#sysmanage').removeClass("active");
					$('#jxzb').addClass("active");
					
			}
			});
			
			
			if (x == 0) {
				
				$("#xzgl").css({
				"display" : "block"
				});
				$("#ydbg").css({
					"display" : "none"
				});
				$("#crm").css({
					"display" : "none"
				});
				
				//$("#westpage").css({"display":"none"});

			} else if (x == 1) {
				$("#xzgl").css({
					"display" : "none"
				});
				$("#ydbg").css({
					"display" : "none"
				});
				$("#crm").css({
					"display" : "block"
				});
				
			}else if(x==2){
				
				
				$("#xzgl").css({
					"display" : "none"
				});
				$("#ydbg").css({
					"display" : "block"
				});
				$("#crm").css({
					"display" : "none"
				});
			}
			
		});
	});
	

	$("#ydbg").css({
		"display" : "block"
	});
</script>
</head>
<body>
<%@include file="/performance/navigation.jsp"%>
	<div id="cc" name="cc1" class="easyui-layout"    style="position:relative; bottom:0px;height:100%; width:100%;overflow: hidden">
		<!-- <div data-options="region:'north'" style="height:60px;"></div> -->
		<div id="westpage" name="westpage" data-options="region:'west'" style="width:200px;">
			<jsp:include page="layout/west.jsp"></jsp:include>
		</div>

		<div id="centerpage" name="centerpage" data-options="region:'center',title:'',fit:true" style="overflow: hidden;">
			<jsp:include page="layout/center.jsp"></jsp:include>
		</div>

		<div></div>

	</div>
</body>
</html>
