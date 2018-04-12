LazyLoad = function(win){  
    var list,
        isIE = /*@cc_on!@*/!1,
        doc = win.document,
        head = doc.getElementsByTagName('head')[0];
 
    function createEl(type, attrs){
        var el = doc.createElement(type),
            attr;
        for(attr in attrs){
            el.setAttribute(attr, attrs[attr]);
        }
        return el;
    }
    function finish(obj){
        list.shift();
        if(!list.length){
            obj.fn.call(obj.scope);
        }
    }
    function js(urls, fn, scope){
        list = [].concat(urls);
        var obj = {scope:scope||win, fn:fn};
        for(var i=0,len=urls.length; i<len; i++){
            var script = createEl('script', {src: urls[i]});
            if(isIE){
                script.onreadystatechange = function(){
                    var readyState = this.readyState;
                    if (readyState == 'loaded' || readyState == 'complete'){
                        this.onreadystatechange = null;
                        finish(obj);
                    }
                }
            }else{
                script.onload = script.onerror = function(){
                    finish(obj);
                }
            }
            head.appendChild(script);
        }
    }
    return {js:js};
}(this);

FormValidate = function(formObj){
	var success = false;
	var isAdmin = false;
	var userRole = $('#urole').val();
	if(userRole === '管理员'){
		isAdmin = true;
	} 
	
	$("#auditstatus",formObj).each(function(){
		if(isAdmin){
			this.value = '已提交';
		} else {
			this.value = '已保存';
		}

	});
	
	//2.判断是否提交重复的用户
	var isSameUser = false;
	var usercodeArray = [];
	$("input[type=hidden]",formObj).each(function(){
		var lowercaseName = this.name.toLowerCase();
		 if(lowercaseName.indexOf('code') > -1 && lowercaseName !== 'commauthorcode' &&
				 lowercaseName !== 'pubcode'){
			 if(this.value !== undefined && this.value !== '' && this.value !== ""){
				 if ($.inArray(this.value, usercodeArray) !== -1) {
					 isSameUser = true;
					 return false;
				 } else {
					 //判断其他作者是否包含此用户
					 if(this.value.indexOf(',') > -1){
						 var otherAuthorArray = this.value.split(',');
						 for(var i = 0; i < usercodeArray.length; i++){
							 if($.inArray(usercodeArray[i], otherAuthorArray) !== -1){
								 isSameUser = true;
								 return false;
							 }
						 }
					 }
				 }
				 if(this.value != "-1")
					 usercodeArray.push(this.value);
			 }
		 }
	});
	if(isSameUser){
		$.messager.show({
			title: '错误',
			msg: '不允许添加重复的用户！'
		});
		return false;
	}
	
	if(isAdmin)
		return true;
	
	var userCode = $('#usercode').val();
	if(userCode === '')
		return false;
	
	//2.非管理员用户，判断提交记录有自己
	$("input[type=hidden]",formObj).each(function(){
		var lowercaseName = this.name.toLowerCase();
		 if(lowercaseName.indexOf('code') > -1){
			 if(this.value.indexOf(userCode) > -1){
				 success = true;
				 return true;
			 }
		 }
	});
	
	if(!success){
		$.messager.show({
			title: '错误',
			msg: '只能提交包含自己的信息！'
		});
	}
	
	return success;
}

EditValidate = function(formObj, examineStatus){
	
	try
	{
		$("input",formObj).removeAttr("disabled"); 
		$('.easyui-combobox',formObj).each(function(){
			$(this).combobox('enable');
		});
		
//		$('.datebox',formObj).each(function(){
//			$(this).prev().datebox({
//	            disabled: false
//	        });
//		});
		//4.禁用保存按钮
		$("a", formObj.parent().next()).each(function(){
			if($.trim(this.text) === '保存'){
				$(this).linkbutton("enable");
			}
		});
	}catch(e){
		
	}
	
	var userRole = $('#urole').val();
	if(userRole === '管理员'){
		return;
	} else {
		if(examineStatus === '已提交' || examineStatus === '审核通过'){
			//1.禁用文本框
			$("input",formObj).attr("disabled","disabled");
			//2.禁用combobox
			$('.easyui-combobox',formObj).each(function(){
				$(this).combobox('disable');
			});
			//3.禁用上传按钮u
			$('#uploadFile', formObj).removeAttr('href');
			$('#uploadFile', formObj).unbind('click');
//			//4.禁用日期框
//			$('.datebox',formObj).each(function(){
//				$(this).prev().datebox({
//		            disabled: true
//		        });
//			});
			//4.禁用保存按钮
			$("a", formObj.parent().next()).each(function(){
				if($.trim(this.text) === '保存'){
					$(this).linkbutton("disable");
				}
			});
		}
	}
}
