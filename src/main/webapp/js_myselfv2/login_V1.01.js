$(function(){
	
	$('#reg-form').validate({
		rules:{
			usercode:{required:true,number:false,minlength:1, maxlength:8},
			pwd:{required:true,minlength:6,maxlength:10},
		},
		messages:{
			usercode:{required:'账号为数字类型员工工号',number:'必须输入数字',minlength:'账号不能小于1位数',maxlength:'账号不能超过8位数'},
			pwd:{required:'密码不能为空',minlength:'密码不能小于6位',maxlength:'密码不能超过10位'},
		},
		//检查完表单 并提交表单
		submitHandler: function(form) {
			$.ajax({
				url : $('#contextPath').val() + '/loginAction!findUser.action',
				type:"post",
				dataType:"json",
				data:{usercode:$('#usercode').numberbox('getValue'),pwd:$('#pwd').val()},
				success:function(result){
					if(result=='success'){
						
						form.submit();
					}else{
						$.messager.show({
							title : '提示',
							msg : '用户名或者密码有误，请重新输入！'
						});
					}
				},
				error:function(e){
					$.messager.show({
						title : '提示',
						msg : '用户名或者密码有误，请重新输入！'
					});
				}
				
			});
			//form.submit();//提交表单   取得参数
			}
			
					
    });
	$('#reg-form input').bind('keyup', function(event) {/* 增加回车提交功能 */
		if (event.keyCode == '13') {
			$('#reg-form').submit();
		}
	});

	window.setTimeout(function() {
		$('#reg-form input[name=usercode]').focus();
	}, 0);

});

var reEnterLoginInfo = function(){
	if($('#loginError').length > 0){
		$('#loginError').hide();
	}
};