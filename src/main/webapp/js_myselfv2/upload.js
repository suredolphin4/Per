var applyExcelUpload = function(element, action, destId,dialogid) {
	$(element).AjaxFileUpload(
			{
				action : $('#contextPath').val() + action,
				onChange : function(filename) {
					// Create a span element to notify the user of an upload in
					// progress
					var $span = $("<span />").attr("class", $(this).attr("id"))
					.text("Uploading").insertAfter($(this));
					
					$(this).remove();
					
					interval = window.setInterval(function() {
						var text = $span.text();
						if (text.length < 13) {
							$span.text(text + ".");
						} else {
							$span.text("Uploading");
						}
					}, 200);
				},
				onSubmit : function(filename) {
					return true;
				},
				onComplete : function(filename, response) {
					
					window.clearInterval(interval);
					var $span = $("span." + $(this).attr("id")).text(filename + " "),
					$fileInput = $("<input />").attr({
						type : "file",
						name : $(this).attr("name"),
						id : $(this).attr("id")
					});

					if (response.success) {
						//获取上传文件下载路径
						var append = response.saveFileName;
						if (destId) {
							$(destId).val(append);
						}
						$('#dialogid').datagrid('load',{});
						refreshfunc();
						
					} else {
						$span.replaceWith($fileInput);
						applyExcelUpload($fileInput, action, destId);
						if(typeof(response.msg) !== "undefined"){
							$.messager.alert("错误", response.msg);
						}else{
							$.messager.alert("错误", "导入出错,请检查excel！");
						}
						return;
					}
					
					$("<a />").attr("href", "javascript:void(0)").text("重新上传").bind("click", function(e) {
						$span.replaceWith($fileInput);
						applyExcelUpload($fileInput, action,	destId);
					}).appendTo($span);
				}
			});
};



/**
 * 附件上传
 */

/**
 * 异步文件上传
 * param:element 类型(type)为file,名称(name)为upload的输入控件(input)
 * param:action 目标URL，统一为/upload.action
 * param:destId 上传完成后，返回保存在服务器的文件名，并赋给id为destId的隐藏input控件
 */
var applyAjaxFileUpload = function(element, action, destId) {
	$(element).AjaxFileUpload(
			{
				action : $('#contextPath').val() + action,
				onChange : function(filename) {
					// Create a span element to notify the user of an upload in
					// progress
					var $span = $("<span />").attr("class", $(this).attr("id"))
					.text("Uploading").insertAfter($(this));
					
					$(this).remove();
					
					interval = window.setInterval(function() {
						var text = $span.text();
						if (text.length < 13) {
							$span.text(text + ".");
						} else {
							$span.text("Uploading");
						}
					}, 200);
				},
				onSubmit : function(filename) {
					return true;
				},
				onComplete : function(filename, response) {
					
					window.clearInterval(interval);
					var $span = $("span." + $(this).attr("id")).text(filename + " "),
					$fileInput = $("<input />").attr({
						type : "file",
						name : $(this).attr("name"),
						id : $(this).attr("id")
					});
					
					
					//处理文件类型不合法,文件超过限制等情况
					if(typeof(response.fieldErrors) !== "undefined" && response.fieldErrors.upload != null
							&& response.fieldErrors.upload.length !== 0) {
						var error = response.fieldErrors.upload[0].substring(0, response.fieldErrors.upload[0].indexOf(':'));
						if(error === 'File too large'){
							$.messager.alert("错误", "附件超过20M限制,请重新选择文件上传!");
						}else if(error === "Content-Type not allowed"){
							$.messager.alert("错误", "不合法的附件类型!支持的文件类型有pdf,图片(jpg,jpeg,png等),word,excel");
						}
						$span.replaceWith($fileInput);
						applyAjaxFileUpload($fileInput, action, destId);
						return;
					}

					if (response.success) {
						//获取上传文件下载路径
						var append = response.saveFileName;
						if (destId) {
							$(destId).val(append);
						}
					} else {
						$span.replaceWith($fileInput);
						applyAjaxFileUpload($fileInput, action, destId);
						$.messager.alert("错误", response.message);
						return;
					}
					
					$("<a />").attr("href", "javascript:void(0)").text("重新上传").bind("click", function(e) {
						$span.replaceWith($fileInput);
						applyAjaxFileUpload($fileInput, action,	destId);
					}).appendTo($span);
				}
			});
};

/**
 * 对话框关闭前，清空上传控件
 * param:uploadId 上传控件的id，自定义
 */
var clearUpload = function(uploadId){
	var element = $('.' + uploadId);
	if(!element){
		return;
	}else if(element.is('span')){
		var fileInput = $("<input />").attr({
			type : 'file',
			name : 'upload',
			id : uploadId
		});
		
		element.replaceWith(fileInput);
	}
};

/**
 * 附件编辑回显处理
 * param:uploadId   附件上传控件id
 * param:appendId   回显控件id
 * param:appendName 已有附件名称
 */
var editShowUpload = function(uploadId, appendId, appendName){
	//已存在附件,回显处理	
	var $fileInput = $(uploadId),
	    id = $fileInput.attr('id'), 
	    name = $fileInput.attr('name');
	
	var $span = $("<span />").attr("class", id).text(appendName + ' ').insertAfter($fileInput);
	
	//删除input file控件
	$fileInput.remove();
	
	//附件名后添加一个"重新上传"链接
	$("<a id='uploadFile'/>").attr("href", "javascript:void(0)").text("重新上传").bind("click", function(e) {
		    //清空hidden upload
			$(appendId).val('');

			//重置上传控件,重新绑定file input
			$fileInput = $("<input />").attr({
				type : "file",
				name : name,
				id : id
			});
			
			$span.replaceWith($fileInput);
			applyAjaxFileUpload($fileInput, "/upload.action", appendId);
	}).appendTo($span);
};

