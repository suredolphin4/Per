/**
 * 下载表格数据
 * @param actionUrl
 */
function downloadExcel(actionUrl){
	//$.messager.show({
	//	title : '提示',
	//	msg : '系统正在导出Excel表格，请耐心等候！'
	//});
	$.blockUI({  message: '<h1>正在导出excel,请稍候！</h1>',
	css: {
		border: 'none',
			padding: '15px',
			backgroundColor: '#000',
			'-webkit-border-radius': '10px',
			'-moz-border-radius': '10px',
			opacity: .5,
			color: '#fff'
	} });

	$.ajax({
        type : 'GET',
        url : $('#contextPath').val() + '/' + actionUrl,	//thesisAction!downloadExcel.action',
        dataType: "text",
        contentType : 'application/vnd.ms-excel',	//'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        //data : ko.mapping.toJSON(licenseModel),
        success : function(data) {
			$.unblockUI();

        	var jsonObj = JSON.parse(data);
        	//window.location.href = encodeURI($('#contextPath').val() + jsonObj.obj);
			window.location.href = encodeURI($('#contextPath').val() + "/download.action" + "?" + "fileName=" + jsonObj.obj);
        	
        	//$.messager.show({
        	//	title : '成功',
        	//	msg : 'Excel导出完成！'
        	//});
        	//downloadFile('D:\test.xlsx', data);
        },
        error : function(xhr, ajaxOptions, thrownError) {
            //// error handling
        	//$.messager.show({
        	//	title : '失败',
        	//	msg : '导出Excel表格失败！'
        	//});
			$.blockUI({  message: '<h1>导出excel失败！</h1>'});
			setTimeout($.unblockUI, 2000);
        }
    });
}


function openDialog(id){
	var width = document.body.clientWidth;
	var height = document.body.clientHeight;
	var headerHeight = $("#header").height();
	var dialogHeight = $('#' + id).height() + 105;
	
	var layoutHeight = headerHeight;
	if((dialogHeight + 15) >= height){
		layoutHeight = 0;
	} else if( (dialogHeight + headerHeight) > height) {
		layoutHeight = height - dialogHeight - 15;
	}
	
    //var footerHeight = $("#footer").height();
    //var layoutHeight = height-headerHeight - footerHeight - 5;
    //var layoutWidth = width - 230 > 0 ? width - 230 : 0;
	//$('#' + id).dialog({left:layoutWidth/2, top:layoutHeight/2});
	$('#' + id).dialog({top:layoutHeight});
	return $('#' + id).dialog('open');
}

/***********************************修改密码**********************************************/
$(function(){
	$.extend($.fn.validatebox.defaults.rules, {    
	    /*必须和某个字段相等*/  
	    equalTo: {  
	        validator:function(value,param){  
	            return $(param[0]).val() == value;  
	        },  
	        message:'字段不匹配'  
	    }  
	             
	});  
});

function changePassword(){
	openDialog('change_password_dialog');
}

function change_password_btn_ok(){
	var password = $('#password').val();
	var len = password.length;
	if( len < 6 || len > 10){
		$.messager.show({
			title : '提示',
			msg : '输入密码不能少于6位或者大于10位'
		});
		return;
	}
	
	if(password !== $('#repassword').val()){
		$.messager.show({
			title : '提示',
			msg : '确认密码不相同'
		});
		return;
	}
	
	$.ajax({
		url : $('#contextPath').val() + '/loginAction!changePassword.action',
		data : 'password=' + password + '&usercode=' + $('#usercode').val(),
		error : function() {
			$.messager.show({
				title : '错误',
				msg : '修改密码错误！'
			});
		},
		success : function(data) {
			json = $.parseJSON(data);
			$.messager.show({
				title : '提示',
				msg : json.msg
			});
			
			$('#change_password_dialog').dialog('close');
			window.location.href = $('#contextPath').val() + "/loginAction!toLogin.action";
		},
		type : 'POST'
	});
//	$('#change_password_dialog').form(
//			'submit',
//			{
//				url : $('#contextPath').val() + '/userAction!changePassword.action',
//				onSubmit : function(param) {
//					return $(this).form('validate');
//				},
//				success : function(r) {
//					// 修改成功，解析返回的json信息
//					var objr = jQuery.parseJSON(r);
//					$.messager.show({
//						title : '提示',
//						msg : objr.msg
//					});
//
//					d.dialog('close');
//				}
//			});
}

function change_password_btn_cancel(){
	$('#change_password_dialog').dialog('close');
}


/**************************************表格操作************************************/
var refreshfunc = function(){
	$('#center_table').datagrid("reload");
	$('#center_table').datagrid('clearSelections');
	$('#center_table').datagrid('clearChecked');
};
function selectAll(){
var datasize=$('#center_table').datagrid('getChecked');
var data=$('#center_table').datagrid('getData');
console.info(data);
if(datasize==0){
	//$('#center_table').datagrid('checkAll');
	if(data.total>0){
		for(var i=0;i<data.total;i++){
			$('#center_table').datagrid('checkRow', i);
		}
		var dsize=$('#center_table').datagrid('getChecked');
		console.info(dsize);
//		$.each(data.rows, function(index, item){
//		                if(item.checked){
//		                    $('#dg').datagrid('checkRow', index);
//		                }
//		           });
	}
}else{
	$('#center_table').datagrid('clearChecked');
	$('#center_table').datagrid('clearSelections');
}

};
/**************************************后台刷新操作************************************/
var refreshfunc_Manger = function(tableName){
	$(tableName).datagrid("reload");
	$(tableName).datagrid('clearSelections');
	$(tableName).datagrid('clearChecked');

};