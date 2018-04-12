/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {
	$("#xzgl").css({
		"display" : "block"
	}).siblings().css({
		"display" : "block"
	});
};

/******************************************* Patent Operator ******************************************/
function lunwen() {

	$('#layout_center_tabs').datagrid({
		url : '${pageContext.request.contextPath}/roleAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'roleid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'rolename',
		sortOrder : 'asc',
		/*pagePosition : 'both',*/
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'roleid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'rolename',
			title : '角色名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'rolevalue',
			title : '角色值',
			width : 150

		}, {
			field : 'createdatetime',
			title : '创建时间',
			width : 150,
			sortable : false
		}, {
			field : 'modifydatetime',
			title : '最后修改时间',
			width : 150,
			sortable : false
		}, {

			field : 'rights',
			title : '权限',
			width : 150,
			sortable : false

		} ] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				append();

			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				remove();
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				editfun();
			}
		}, '-' ]
	});
}

$(function() {
	$('a').click(function() {
		var id = $(this).attr("id");
		var x = $(this).parent().index();
		$('a').each(function(index, element) {

			var other_id = $(this).attr("id");
			if (id == other_id) {
				$('#glxxcx').addClass("active");
				$('#jxzb').removeClass("active");

			} else {
				$('#glxxcx').removeClass("active");
				$('#jxzb').addClass("active");
			}
		});
		if (x == 0) {

			$("#crm").css({
				"display" : "block"
			}).siblings().css({
				"display" : "none"
			});
			/* 			  $('#cc').layout('collapse','west');
			 */$('#westpage').remove();

			/*    $("#jxzb").live("click",function(){
				    $('#westpage').slideToggle();
				  });
			 */

		} else if (x == 1) {
			$("#xzgl").css({
				"display" : "block"
			}).siblings().css({
				"display" : "none"
			});
			/*  $('#westpage').css({"display":"block"}); */
		}

	});

	$('img').mouseover(function() {
		var x = $(this).parent().index();
		if (x == 0) {
			alert("lunwen1");
		} else if (x == 0) {
			alert("lunwen2");

		} else if (x == 0) {
			alert("lunwen3");

		} else if (x == 0) {
			alert("lunwen4");

		} else if (x == 0) {
			alert("lunwen5");

		} else if (x == 0) {
			alert("lunwen6");

		} else if (x == 0) {
			alert("lunwen7");

		} else if (x == 0) {
			alert("lunwen8");

		} else if (x == 0) {
			alert("lunwen9");

		} else if (x == 0) {

			alert("lunwen10");
		} else if (x == 0) {

			alert("lunwen11");
		} else if (x == 0) {

			alert("lunwen12");
		}
	});
});