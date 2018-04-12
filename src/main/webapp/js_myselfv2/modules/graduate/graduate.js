/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {	
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/graduateAction!datagrid.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'yjid',
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
				$('#delAll').hide();
				$('#insert').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
			}else if(rolename=="管理员"){
				
			}else{
				$('#delAll').hide();
				$('#insert').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
			}
		},
		frozenColumns : [ [ {field : 'yjid', title : '编号',width : 150,checkbox : true},
		                    {field : 'year', title : '年度', width : 50, resizable : true, sortable : true}
		 				] ],
		columns : [ [   
					    
						{field : 'award', title : '奖项', width : 200, resizable : true, sortable : true},
	    				{field : 'student', title : '获奖学生', width : 150, resizable : true, sortable : true},
						{field : 'teacher', title : '教师', width : 150, resizable : true, sortable : true},
						{field : 'teacherCode', title : '教师arp', width : 150, resizable : true, sortable : true},
						{field : 'score',title : '分值',width : 150,sortable : true}, 
						{field : 'savetime',title : '保存时间',width : 180,sortable : false}
				]],
		toolbar : '#tb'
	});
	
	if($('#urole').val() === '秘书'){
		$('#center_table').datagrid('hideColumn','score');
	}
	
	//上传
	applyExcelUpload('#graduate_import',"/graduateAction!upLoadExcel.action",null,'#center_table');
	
};

/******************************************* Thesis Operator ******************************************/
	//论文添加
	var append = function(){
		
		$('#add_teacher').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_teacher");
		});
		
		$('#add_student').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_student");
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
        
        //unitrank
        var unitData = [];
        for(var i=0;i<9;i++){
        	unitData[i] = {};
        	unitData[i].id = i+1;
        	unitData[i].text = i+1;
        }
        unitData.push({id:i+1, text:'9以后'});
        unitData.push({id:i+2, text:'无单位排名'});
        $('#unit').combobox('loadData', unitData);
        
        openDialog('admin_graduate_addDialog');
		$('#admin_graduate_addForm').form('clear');
		//applyAjaxFileUpload('#lw_upload',"/upload.action");
	};
	
	//确认添加
	var add_graduate_btn_ok = function(){
		$('#admin_graduate_addForm').form('submit', {
			url : $('#contextPath').val() + '/graduateAction!add.action',
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
					
					$('#admin_graduate_addDialog').dialog('close');
				}else{
					$.messager.alert("操作提示", "操作失败,论文已存在！","error");
				}
			}
		});
	};
	
	//取消添加
	var add_graduate_btn_cancel = function(){
		$('#admin_graduate_addDialog').dialog('close');
	};
	
	
	
	var removeAll = function(){
		$.messager.confirm('确认', '您是否要清空所有的数据？', function(rd) {
			if (rd) {
				$.ajax({
					url : $('#contextPath').val() + '/graduateAction!removeAll.action',
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
		
	};
	//论文删除操作
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
		
		$.messager.confirm("确认", "您确定要删除选择的论文记录？", function(r){
			if(r){
				//将需要提交的论文列表
				var toRemove = {};
				toRemove.ids = '';
				for(var i = 0; i < rows.length; i++){
					if(i === rows.length -1){
						toRemove.ids += rows[i].yjid;
						continue;
					}
					toRemove.ids += (rows[i].yjid + ',');
				}
				var remove = JSON.stringify(toRemove);
				$.ajax({
					url: $('#contextPath').val() + '/graduateAction!remove.action',
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
	    $('#admin_graduate_editForm input').val();
	    
	    $('#e_teacher').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_teacher");
		});
		
		$('#e_student').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_student");
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
		if(len == 1){
			//回显
			$('#e_yjid').val(rows[len-1].yjid);
			$('#e_award').textbox('setValue', rows[len-1].award);
			$('#e_year').combobox('setValue', rows[len-1].year);
			$('#e_student').textbox('setValue', rows[len-1].student);
			$('#e_teacher').textbox('setValue', rows[len-1].teacher);
			$('#e_score').textbox('setValue', rows[len-1].score);
			$('#e_studentcode').val(rows[len-1].studentCode);
			$('#e_teachercode').val(rows[len-1].teacherCode);
			
			openDialog('admin_graduate_editDialog');
			//applyAjaxFileUpload('#e_lw_upload',"/upload.action");
		} else {
			$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		}
	};
	
	var edit_graduate_btn_ok = function(){
		$('#admin_graduate_editForm').form('submit',{		
			url: $('#contextPath').val() + '/graduateAction!edit.action', 
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
				
				$('#admin_graduate_editDialog').dialog('close');
			}
		});
	};
	
	var edit_graduate_btn_cancel = function(){
		$('#admin_graduate_editDialog').dialog('close');
	};
	
	
	function lunwen() {
		$('#center_table').datagrid({
			url : $('#contextPath').val() + '/graduateAction!datagrid.action',
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
					editfunc();
				}
			}, '-' ]
		});
	}
    
    //论文导入
    var importfunc=function(){
    	openDialog('import_graduate_dilog');
    };
   
    //论文导出
    var exportfunc=function(){
    	//$('#export_lw').dialog("open");
    	downloadExcel('graduateAction!downloadExcel.action');
    };
    
    var exportlw_btn_ok = function(){
		$('#admin_thesis_exportForm').form('submit',{		
			url: $('#contextPath').val() + '/graduateAction!edit.action', 
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
	
	//高级查询
	var graduateAdvancedSearch = function (){
		//year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear+1;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#graduate_begin_year').combobox('loadData', yearData);
        $('#graduate_begin_year').combobox('select', yearData[0].id);
        $('#graduate_begin_year').combobox('setValue', '');
        $('#graduate_end_year').combobox('loadData', yearData);
        $('#graduate_end_year').combobox('select', yearData[0].id);
        $('#graduate_end_year').combobox('setValue', '');
		
		var top = $('#lw_statusfilter').offset().top + 30;
		var left = $('#lw_statusfilter').offset().left;
		$('#graduateAdvancedSearch_dialog').dialog('open').dialog('resize',{top: top,left:left});
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
		var begin_year = $('#graduate_begin_year').combobox('getValue');
		var end_year = $('#graduate_end_year').combobox('getValue');
		
		var author = $('#graduate_lw_author').val();
		var name = $('#graduate_lw_name').val();
		var authorcode = $('#graduate_lw_authorcode').val();
		
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
		$('#graduateAdvancedSearch_dialog').dialog('close');
	};
	
	//高级查询取消
	var adv_search_btn_cancel = function(){
		$('#graduateAdvancedSearch_dialog').dialog('close');
	};