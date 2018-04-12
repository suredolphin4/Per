/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {	
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/lessonsAction!datagrid.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 100,
		pageList : [20,50,100,150,200],
		sortName : 'savetime',
		sortOrder : 'desc',
		checkOnSelect : true,
		selectOnCheck : false,
		queryParams: {
			usercode : $('#usercode').val()
		},
		onLoadSuccess:function(){
			var rolename=$('#urole').val();	
			if(rolename=="科研人员"){
				$('#import').hide();
				$('#delAll').hide();
				$('#insert').hide();
				$('#del').hide();
				$('#update').hide();
			}else if(rolename=="秘书"){
				$('#insert').hide();
				$('#delAll').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
			}else if(rolename=="管理员"){
				
			}else{
				$('#insert').hide();
				$('#delAll').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
			}
		},
		frozenColumns : [ [ {field : 'id', title : '编号',width : 150,checkbox : true},
		                    {field : 'year', title : '年度', width : 50, resizable : true,sortable : true}
		 				] ],
		columns : [ [   
					    
						{field : 'name', title : '课程名称', width : 200, resizable : true,sortable : true},
						{field : 'teacher', title : '教师', width : 150, resizable : true,sortable : true},
						{field : 'teacherCode', title : '教师arp', width : 150, resizable : true,sortable : true},
						{field : 'role', title : '角色', width : 200, resizable : true,sortable : true},
						{field : 'type', title : '课程类型', width : 200, resizable : true,sortable : true},
						{field : 'period', title : '学时', width : 200, resizable : true,sortable : true},
						{field : 'score',title : '分值',width : 150,sortable : true}, 
						{field : 'savetime',title : '保存时间',width : 180,sortable : false}
				]],
		toolbar : '#tb'
	});
	
	if($('#urole').val() === '秘书'){
		$('#center_table').datagrid('hideColumn','score');
	}
	
	//上传
	applyExcelUpload('#lessons_import',"/lessonsAction!upLoadExcel.action",null,'#center_table');
	
};

/******************************************* Thesis Operator ******************************************/
	//论文添加
	var append = function(){
		
		$('#add_teacher').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_teacher");
		});
		
        //year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear+1;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#add_year').combobox('loadData', yearData);
        
        openDialog('admin_lessons_addDialog');
		$('#admin_lessons_addForm').form('clear');
		//applyAjaxFileUpload('#lw_upload',"/upload.action");
	};
	
	//确认添加
	var add_lessons_btn_ok = function(){
		$('#admin_lessons_addForm').form('submit', {
			url : $('#contextPath').val() + '/lessonsAction!add.action',
			onSubmit: function(param){
				if(!FormValidate($(this)))
					return false;
				return $(this).form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
//					$('#center_table').datagrid('insertRow',{
//						index:0,
//						row:obj.obj
//					});
					
					$('#center_table').datagrid('reload');
										
					$.messager.show({
						title : '提示',
						msg : obj.msg
					});
					
					$('#admin_lessons_addDialog').dialog('close');
				}else{
					$.messager.alert("操作提示", "操作失败,课程已存在！","error");
				}
			}
		});
	};
	
	//取消添加
	var add_lessons_btn_cancel = function(){
		$('#admin_lessons_addDialog').dialog('close');
	};
	var removeAll = function(){
		$.messager.confirm('确认', '您是否要清空所有的数据？', function(rd) {
			if (rd) {
				$.ajax({
					url : $('#contextPath').val() + '/lessonsAction!removeAll.action',
					dataType : 'json',
					success : function(re) {
						refreshfunc();
						$.messager.show({
							title : '提示',
							msg : re.msg
						});
					},
					error:function(re){
						$.messager.show({
							title : '提示',
							msg : re.msg
						});
					}
				});
			}
		});
		
		refreshfunc();
	};
	
	//删除操作
	var removefunc = function(){
		//获取要删除的所有行
		var rows = $('#center_table').datagrid('getChecked');
		
		if(rows.length < 1){
			$.messager.show({
				title: '操作提示',
				msg: '请您至少选择一条可删除的记录！'
			});
			return;
		}
		
		$.messager.confirm("确认", "您确定要删除选择的课程记录？", function(r){
			if(r){
				//将需要提交的论文列表
				var toRemove = {};
				toRemove.ids = '';
				for(var i = 0; i < rows.length; i++){
					if(i === rows.length -1){
						toRemove.ids += rows[i].id;
						continue;
					}
					toRemove.ids += (rows[i].id + ',');
				}
				var remove = JSON.stringify(toRemove);
				$.ajax({
					url: $('#contextPath').val() + '/lessonsAction!remove.action',
					data: 'remove=' + remove,
					error: function(){
						$.messager.show({
			    			title: '错误',
			    			msg: '删除出现错误！请联系管理员处理！'
		    			});
					},
					success: function(data){
		  				json = $.parseJSON(data);
		  				if(json.success){
		  					//重新加载论文列表
		  					refreshfunc();
		  				}
		  			
		  				$.messager.show({
			    			title: '提示',
			    			msg: json.msg
		    			});
					},
					type: 'POST'
				});
			}
		});
	};
	
	//论文属性编辑操作
	var editfunc = function(){
	    $('#admin_lessons_editForm input').val();
	    
	    $('#e_teacher').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_teacher");
		});
	    
        //year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear+1;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#e_year').combobox('loadData', yearData);
        $('#e_year').combobox('select', yearData[0].id);
        
		//获取当前选中行的id
		var rows = $('#center_table').datagrid('getChecked');
		//只能编辑一行
		var len = rows.length;
		if(len == 1 ){
			//回显
			$('#e_id').val(rows[len-1].id);
			$('#e_year').combobox('setValue', rows[len-1].year);
			$('#e_name').textbox('setValue', rows[len-1].name);
			$('#e_teacher').textbox('setValue', rows[len-1].teacher);
			$('#e_role').textbox('setValue', rows[len-1].role);
			$('#e_type').textbox('setValue', rows[len-1].type);
			$('#e_period').textbox('setValue', rows[len-1].period);
			$('#e_score').textbox('setValue', rows[len-1].score);
			$('#e_teachercode').val(rows[len-1].teacherCode);
			
			openDialog('admin_lessons_editDialog');	
			//applyAjaxFileUpload('#e_lw_upload',"/upload.action");
		} else {
			$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		}
	};
	
	var edit_lessons_btn_ok = function(){
		$('#admin_lessons_editForm').form('submit',{		
			url: $('#contextPath').val() + '/lessonsAction!edit.action', 
			onSubmit: function(param){
				if(!FormValidate($(this)))
					return false;
				return $(this).form('validate');
			},
			success : function(r) {
				//修改成功，解析返回的json信息
				var objr = jQuery.parseJSON(r);
				if (objr.success) {
					//更新论文列表datagrid
//					$('#center_table').datagrid('updateRow',{
//						index:$('#center_table').datagrid('getRowIndex',rows[0].lwid),
//						row:objr.obj
//					});
					
					//重新加载论文列表
					$('#center_table').datagrid('reload');
				}
				
				$.messager.show({
					title:'提示',
					msg:objr.msg
				});
				
				$('#admin_lessons_editDialog').dialog('close');
			}
		});
	};
	
	var edit_lessons_btn_cancel = function(){
		$('#admin_lessons_editDialog').dialog('close');
	};
	
	
	function lunwen() {
		$('#center_table').datagrid({
			url : $('#contextPath').val() + '/lessonsAction!datagrid.action',
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
			queryParams: {
				usercode : $('#usercode').val()
			},
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
					editfunc();
				}
			}, '-' ]
		});
	}
	
	//other author 
	var searchOtherAuthor = function(otherauthor){
		focusId=otherauthor;
		openDialog('thesis_addDialog_otherauthor');	
		searchOtherAuthorFunc();
	};
	
	//其它作者界面读取函数
	var searchOtherAuthorFunc = function(){
		$('#thesis_addotherauthorDialog_datagrid').datagrid({
			url : $('#contextPath').val() + '/personAction!datagrid.action',
			fit : false,
			queryParams: {name : $('#otherauthor_name').val(), usercode: $('#otherauthor_name').val()},
			fitColumns : true,
			border : true,
			pagination : true,
			pagePosition : 'bottom',
			idField : 'personid',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
			sortOrder : 'asc',
			singleSelect: false,
			checkOnSelect : false,
			selectOnCheck : false,
			columns : [ [ {
				field : 'personid',
				title : 'ID',
				width : 50,
				checkbox : true,
				fixed : true
			}, 
			{
				field : 'name',
				title : '用户名称',
				width : 100,
				sortable : true,
				fixed : true
			}, 
			{
				field : 'usercode',
				title : '编号',
				width : 50,
				fixed : true
			},
			{
				field : 'lab',
				title : '研究室',
				width : 100,
				fixed : true
			}, 
			{
				field : 'domain',
				title : '领域',
				width : 100
			}  ] ],
			width: 350,
			height: 340,
			autoRowHeight: false
		});
	};
	
	//其它作者选择,添加到列表
	var otherauthor_btn_add = function(){
		var datalistValues = $('#otherauthor_list').datalist('getData');
		//获取当前需要添加的作者列表
		//1.获取本所人员
		var rows = $('#thesis_addotherauthorDialog_datagrid').datagrid('getChecked');
		for(var i = 0; i < rows.length; i++){
			var contains = false;
			for(var j=0; j < datalistValues.rows.length;j++){
				var row = datalistValues.rows[j];
				if(row.value === rows[i].usercode && row.text === rows[i].name){
					contains = true;
				}
			}
			if(contains === false){
				$('#otherauthor_list').datalist('appendRow',{
					value: rows[i].usercode,
					text: rows[i].name
				});
			}
		}
		//2.获取非本所人员
		var outsider = $('#outsider_other').textbox('getValue');
		if(outsider !== '' ){
			contains = false;
			for(j=0; j < datalistValues.rows.length;j++){
				var row = datalistValues.rows[j];
				if(outsider === row.text){
					contains = true;
				}
			}
			if(contains === false){
				$('#otherauthor_list').datalist('appendRow',{
					value: -1,
					text: outsider
				});
				$('#outsider_other').textbox('clear');
			}
		}
	};
	
	//其它作者删除,从列表删除
	var otherauthor_btn_remove = function(){
		var rows = $('#otherauthor_list').datalist('getSelections');
		for (var i = rows.length - 1; i >= 0; i--) {  
		    var index = $('#otherauthor_list').datalist('getRowIndex',rows[i]);  
		    $('#otherauthor_list').datalist('deleteRow', index);  
		}  
	};
	
	//其它作者选择完毕后确认
	var otherauthor_btn_confirm = function(){
		//获取datalist
		var rows = $('#otherauthor_list').datalist('getData').rows;
		var username='', usercode='';
		for(var i=0; i < rows.length; i++){
			username += ',';
			username += rows[i].text;
			usercode += ',';
			usercode += rows[i].value;
		}
		
		username += ',';
		usercode += ',';
		
		$('#' + focusId).textbox('setValue', username);
		$('#' + focusId + 'code').val(usercode);
		
		$('#thesis_addDialog_otherauthor').dialog('close');
	};
	
	//其它作者界面关闭，取消选择
	var otherauthor_btn_cancel = function(){
		$('#thesis_addDialog_otherauthor').dialog('close');
	};
	
	//需要填写非本所人员
	function onOutsiderCheck(){
		if($('#noself').prop("checked")==true){
			$('#outsider').prop("readonly", false);
			//禁用本所人员表的勾选功能
			
		}else{
			$('#outsider').prop("readonly", true);
		}
	}
	
	//高级查询
	var lessonsAdvancedSearch = function (){
		//year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear+1;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#lessons_begin_year').combobox('loadData', yearData);
        $('#lessons_begin_year').combobox('select', yearData[0].id);
        $('#lessons_begin_year').combobox('setValue', '');
        $('#lessons_end_year').combobox('loadData', yearData);
        $('#lessons_end_year').combobox('select', yearData[0].id);
        $('#lessons_end_year').combobox('setValue', '');
		
		var top = $('#lw_statusfilter').offset().top + 30;
		var left = $('#lw_statusfilter').offset().left;
		$('#lessonsAdvancedSearch_dialog').dialog('open').dialog('resize',{top: top,left:left});
		//实验室、领域动态获取
		var url = $('#contextPath').val() + "/domainAction!datagrid.action";
        $.getJSON(url, function(json) {
            $('#patent_which_domain').combobox({
              	data : json.rows,
                valueField:'domainid',
                textField:'name',
                onSelect:function(record){
                	var domainid=record.domainid;
                	var labUrl= $('#contextPath').val() + "/labAction!datagrid.action";
                	$.getJSON(labUrl,{domaincode:domainid},function(labjson){
                		$('#patent_which_lab').combobox({
                            data : labjson.rows,
                            valueField:'labid',
                            textField:'name'
                		});	
                	});
                } 	 
             });
        });
	};
	
	//高级查询确定
	var adv_search_btn_ok = function(){
		//获取过滤条件
		var begin_year = $('#lessons_begin_year').combobox('getValue');
		var end_year = $('#lessons_end_year').combobox('getValue');
		
		var author = $('#lessons_lw_author').val();
		var name = $('#lessons_lw_name').val();
		var authorcode = $('#lessons_lw_authorcode').val();
		
//		//实验室、领域
//		var domain = $('#patent_which_domain').combobox('getText');
//		var lab = $('#patent_which_lab').combobox('getText');
		
		//过滤操作
		$('#center_table').datagrid({
			onBeforeLoad: function(param){
				param.s_begin_year = begin_year;
				param.s_end_year = end_year;
				param.s_lw_author = author;
				param.s_lw_name = name;
				param.s_lw_authorcode = authorcode;
//				param.s_which_domain = domain;
//				param.s_which_lab = lab;
			}
		});
		
		//最后关闭
		$('#lessonsAdvancedSearch_dialog').dialog('close');
	};
	
	//高级查询取消
	var adv_search_btn_cancel = function(){
		$('#lessonsAdvancedSearch_dialog').dialog('close');
	};
	
	//异步文件上传
	var applyAjaxFileUpload = function(element, action){        
        $(element).AjaxFileUpload({
                action:  $('#contextPath').val() + action,
                onChange: function(filename) {
						// Create a span element to notify the user of an upload in progress
						var $span = $("<span />")
							.attr("class", $(this).attr("id"))
							.text("Uploading")
							.insertAfter($(this));

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
                onSubmit: function(filename){
                	//filename = "改变上传文件名.pdf";
                	return true;
                },
                onComplete: function(filename, response) {
					window.clearInterval(interval);
					var $span = $("span." + $(this).attr("id")).text(filename + " "),
						$fileInput = $("<input />").attr({type: "file", name: $(this).attr("name"), id: $(this).attr("id")});
					
					
					if(!response.success){
						$span.replaceWith($fileInput);
						applyAjaxFileUpload($fileInput, action);
						alert(response.error);
						return;
					}

					$("<a />").attr("href", "javascript:void(0)").text("x").bind("click", function(e) {
							$span.replaceWith($fileInput);
							applyAjaxFileUpload($fileInput, action);
						}).appendTo($span);
				}
        });
    };
    
    //论文导入
    var importfunc=function(){
    	openDialog('import_lessons');	
    };
   
    //论文导出
    var exportfunc=function(){
    	//$('#export_lw').dialog("open");
    	downloadExcel('lessonsAction!downloadExcel.action');
    };
    
    var exportlw_btn_ok = function(){
		$('#admin_thesis_exportForm').form('submit',{		
			url: $('#contextPath').val() + '/lessonsAction!edit.action', 
			onSubmit: function(param){
				return $(this).form('validate');
			},
			success : function(r) {
				//修改成功，解析返回的json信息
				var objr = jQuery.parseJSON(r);
				if (objr.success) {
					//更新论文列表datagrid
					$('#center_table').datagrid('updateRow',{
						index:$('#center_table').datagrid('getRowIndex',rows[0].lwid),
						row:objr.obj
					});
					
					//重新加载论文列表
					//$('#center_table').datagrid('reload');
				}
				
				$.messager.show({
					title:'提示',
					msg:objr.msg
				});
				
				d.dialog('close');
			}
		});
	};
	
	var exportlw_btn_cancel = function(){
		$('#admin_thesis_exportForm').dialog('close');
	};
	
	function downloadFile(fileName, content){
	    var aLink = document.createElement('a');
	    var blob = new Blob([content]);
	    var evt = document.createEvent("HTMLEvents");
	    evt.initEvent("click", false, false);//initEvent 不加后两个参数在FF下会报错, 感谢 Barret Lee 的反馈
	    aLink.download = fileName;
	    aLink.href = URL.createObjectURL(blob);
	    aLink.dispatchEvent(evt);
	}