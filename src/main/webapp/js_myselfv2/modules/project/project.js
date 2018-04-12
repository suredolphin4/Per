/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {	
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/projectAction!datagrid.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 100,
		pageList : [20,50,100,150,200],
		sortName : 'beginTime',
		sortOrder : 'desc',
		checkOnSelect : true,
		selectOnCheck : false,
		queryParams: {
			usercode : $('#usercode').val()
		},

		//frozenColumns : [ [ {field : 'id', title : '编号',width : 150,checkbox : true},
		//                    {field : 'projectid', title : '课题编号', width : 70,resizable : true, sortable : true},
		// 				] ],
		columns : [ [
			{field : 'id', title : '编号',width : 150,checkbox : true},
			{field : 'projectid', title : '课题编号', width : 70,resizable : true, sortable : true},

			{field : 'leader', title : '课题组长', width : 110, resizable : true,sortable : true},
						{field : 'name', title : '课题全称', width : 300, resizable : true,sortable : true},
	    				{field : 'provider', title : '甲方', width : 150, resizable : true,sortable : true},
					
						{field : 'collegeCode',title : '院级课题编码',width : 150,sortable : true},
						{field : 'beginTime',title : '开题年度',width : 120,sortable : true},
						{field : 'endTime',title : '结题年度',width : 120,sortable : true},
						{field : 'subCode',title : '子课题号',width : 150,sortable : true}, 
						{field : 'subLeader',title : '子课题负责人',width : 150,sortable : true}, 
						{field : 'subLeaderCode',title : '子课题负责人arp',width : 150,sortable : true}, 
						{field : 'subName',title : '子课题全称',width : 300,sortable : true}, 
					
						{field : 'subProjectStay',title : '子课题留所经费',width : 150,sortable : true},
						{field : 'taskCode',title : '任务代码',width : 100,sortable : true},
						{field : 'detailType',title : '明细任务类型',width : 150,sortable : true},
						{field : 'roleCode',title : '角色代码',width : 100,sortable : false},
						{field : 'savetime',title : '保存时间',width : 80,sortable : true},
						{field : 'score',title : '分值',width : 180,sortable : true}
				]],
		toolbar : '#tb',
		onLoadError : function() {
			$.messager.show({
				title : '提示',
				msg : '加载数据失败！'
			});
		},
		onLoadSuccess:function(){
			var rolename=$('#urole').val();
			if(rolename=="科研人员"){
				$('#insert').hide();
				$('#delAll').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
			}else if(rolename=="秘书"){
				$('#insert').hide();
				$('#del').hide();
				$('#delAll').hide();
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
		}
	});
	
	if($('#urole').val() === '秘书'){
		$('#center_table').datagrid('hideColumn','score');
	}
	
	//上传
	applyExcelUpload('#project_import',"/projectAction!upLoadExcel.action",null,'#center_table');
};

/******************************************* Project Operator ******************************************/


//项目添加
	var append = function(){
		$('#add_leader').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_leader");
		});
	    $('#add_subLeader').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_subLeader");
		});
	    
        //year 
        var currYear = new Date().getFullYear(), beginYearData = [], endYearData = [];
        for(var i=1990;i<=currYear;i++){
        	beginYearData[i-1990] = {};
        	beginYearData[i-1990].id = i;
        	beginYearData[i-1990].text = i;
        }
        beginYearData = beginYearData.reverse();
        
        for(var i=1990;i<=currYear+10;i++){
        	endYearData[i-1990] = {};
        	endYearData[i-1990].id = i;
        	endYearData[i-1990].text = i;
        }
        endYearData = endYearData.reverse();
        
        $('#add_begin').combobox('loadData', beginYearData);
        $('#add_end').combobox('loadData', endYearData);
        
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
        
		openDialog('admin_project_addDialog');
		$('#admin_project_addForm').form('clear');
		//applyAjaxFileUpload('#lw_upload',"/upload.action");
	};
	
	//确认添加
	var add_project_btn_ok = function(){
		$('#admin_project_addForm').form('submit', {
			url : $('#contextPath').val() + '/projectAction!add.action',
			onSubmit: function(param){
				//if(!FormValidate($(this)))
				//	return false;
				return $(this).form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#center_table').datagrid('reload');
										
					$.messager.show({
						title : '提示',
						msg : obj.msg
					});
					
					$('#admin_project_addDialog').dialog('close');
				}else{
					$.messager.alert("操作提示", "操作失败,项目已存在！","error");
				}
			}
		});
	};
	
	//取消添加
	var add_project_btn_cancel = function(){
		$('#admin_project_addDialog').dialog('close');
	};
	
	
	var removeAll = function(){
		$.messager.confirm('确认', '您是否要清空所有的数据？', function(rd) {
			if (rd) {
				$.ajax({
					url : $('#contextPath').val() + '/projectAction!removeAll.action',
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
		
		$.messager.confirm("确认", "您确定要删除选择的项目记录？", function(r){
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
					url: $('#contextPath').val() + '/projectAction!remove.action',
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
	
	//项目属性编辑操作
	var editfunc = function(){
	    $('#admin_project_editForm input').val();
	    
	    $('#e_leader').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_leader");
		});
	    $('#e_subLeader').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_subLeader");
		});

		var currYear = new Date().getFullYear(), beginYearData = [], endYearData = [];
		for(var i=1990;i<=currYear;i++){
			beginYearData[i-1990] = {};
			beginYearData[i-1990].id = i;
			beginYearData[i-1990].text = i;
		}
		beginYearData = beginYearData.reverse();

		for(var i=1990;i<=currYear+10;i++){
			endYearData[i-1990] = {};
			endYearData[i-1990].id = i;
			endYearData[i-1990].text = i;
		}
		endYearData = endYearData.reverse();
        
        $('#e_begin').combobox('loadData', beginYearData);
        //$('#e_begin').combobox('select', beginYearData[0].id);
        $('#e_end').combobox('loadData', endYearData);
        //$('#e_end').combobox('select', endYearData[0].id);
        
		//获取当前选中行的id
		var rows = $('#center_table').datagrid('getChecked');
		//只能编辑一行
		var len = rows.length;
		if(len == 1){
			//回显
			$('#e_id').val(rows[len-1].id);
			$('#e_projectid').textbox('setValue', rows[len-1].projectid);
			$('#e_leader').textbox('setValue', rows[len-1].leader);
			$('#e_name').textbox('setValue', rows[len-1].name);
			$('#e_provider').textbox('setValue', rows[len-1].provider);
			$('#e_type').textbox('setValue', rows[len-1].type);
			$('#e_collegeCode').textbox('setValue', rows[len-1].collegeCode);
			$('#e_begin').combobox('setValue', rows[len-1].beginTime);
			$('#e_end').combobox('setValue', rows[len-1].endTime);
			$('#e_subCode').textbox('setValue', rows[len-1].subCode);
			$('#e_subLeader').textbox('setValue', rows[len-1].subLeader);
			$('#e_subName').textbox('setValue', rows[len-1].subName);
			$('#e_level').textbox('setValue', rows[len-1].level);
			$('#e_subProjectStay').textbox('setValue', rows[len-1].subProjectStay);
			$('#e_taskCode').textbox('setValue', rows[len-1].taskCode);
			$('#e_level').textbox('setValue', rows[len-1].level);
			$('#e_subProjectStay').textbox('setValue', rows[len-1].subProjectStay);
			$('#e_taskCode').textbox('setValue', rows[len-1].taskCode);
			$('#e_detailType').textbox('setValue', rows[len-1].detailType);
			$('#e_roleCode').textbox('setValue', rows[len-1].roleCode);
			$('#e_score').textbox('setValue', rows[len-1].score);
			$('#e_leadercode').val(rows[len-1].leaderCode);
			$('#e_subLeadercode').val(rows[len-1].subLeaderCode);
			
			openDialog('admin_project_editDialog');
			//applyAjaxFileUpload('#e_lw_upload',"/upload.action");
		} else {
			$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		}
	};
	
	var edit_project_btn_ok = function(){
		$('#admin_project_editForm').form('submit',{		
			url: $('#contextPath').val() + '/projectAction!edit.action', 
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
				
				$('#admin_project_editDialog').dialog('close');
			}
		});
	};
	
	var edit_project_btn_cancel = function(){
		$('#admin_project_editDialog').dialog('close');
	};
	
	
	function lunwen() {
		$('#center_table').datagrid({
			url : $('#contextPath').val() + '/projectAction!datagrid.action',
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
	
	//高级查询
	var projectAdvancedSearch = function (){
		//year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#project_begin_year').combobox('loadData', yearData);
        $('#project_begin_year').combobox('select', yearData[0].id);
        $('#project_begin_year').combobox('setValue', '');
        $('#project_end_year').combobox('loadData', yearData);
        $('#project_end_year').combobox('select', yearData[0].id);
        $('#project_end_year').combobox('setValue', '');
        
		var top = $('#lw_statusfilter').offset().top + 30;
		var left = $('#lw_statusfilter').offset().left;
		$('#projectAdvancedSearch_dialog').dialog('open').dialog('resize',{top: top,left:left});
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
		var begin_year = $('#project_begin_year').combobox('getValue');
		var end_year = $('#project_end_year').combobox('getValue');
		
		var author = $('#project_lw_author').val();
		var authorcode = $('#project_lw_authorcode').val();
		var name = $('#project_lw_name').val();
		var projectID = $('#project_xm_projectID').val();
		
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
				param.s_xm_projectID = projectID;
//				param.s_which_domain = domain;
//				param.s_which_lab = lab;
			}
		});
		
		//最后关闭
		$('#projectAdvancedSearch_dialog').dialog('close');
	};
	
	//高级查询取消
	var adv_search_btn_cancel = function(){
		$('#projectAdvancedSearch_dialog').dialog('close');
	};
    
    //导入
    var importfunc=function(){
    	openDialog('import_project');
    };
   
    //导出
    var exportfunc=function(){
    	//$('#export_lw').dialog("open");
    	downloadExcel('projectAction!downloadExcel.action');
    };
    
    var exportlw_btn_ok = function(){
		$('#admin_thesis_exportForm').form('submit',{		
			url: $('#contextPath').val() + '/projectAction!edit.action', 
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