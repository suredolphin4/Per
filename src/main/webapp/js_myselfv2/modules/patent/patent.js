/******************************************* Init Operator ******************************************/
var moduleLoaded = function() {	
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/patentAction!datagrid.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 100,
		pageList : [20,50,100,150,200],
	//	sortName : 'name',
	//	sortOrder : 'desc',
		checkOnSelect : true,
		selectOnCheck : false,
		queryParams: {
			usercode : $('#usercode').val()
		},
		frozenColumns : [ [ {field : 'id', title : '编号',width : 150,checkbox : true},
		 				] ],
		columns : [ [   
		                {field : 'year', title : '授权年度', width : 100, resizable : true},
						{field : 'name', title : '专利名称', width : 300, resizable : true},    				
	    				{field : 'code', title : '授权号', width : 200, resizable : true},
	    				{field : 'date', title : '授权日期', width : 150, resizable : true},
						{field : 'type', title : '专利类别', width : 100, resizable : true},
						{field : 'owner',title : '专利权人',width : 260,sortable : false}, 
						{field : 'other',title : '其他专利权人',width : 200,sortable : false}, 
						{field : 'rank',title : '单位排名',width : 100,sortable : true}, 
						{field : 'firstInvector',title : '发明人1',width : 100,sortable : true}, 
						{field : 'firstInvectorcode',title : '发明人1arp',width : 100,sortable : true}, 
						{field : 'firstauthorscore',title : 'arp1绩效',width : 80}, 
						{field : 'secondInvector',title : '发明人2',width : 100,sortable : true},
						{field : 'secondInvectorcode',title : '发明人2arp',width : 100,sortable : true},
						{field : 'secauthorscore',title : 'arp2绩效',width : 80}, 
						{field : 'thirdInvector',title : '发明人3',width : 100,sortable : true},
						{field : 'thirdInvectorcode',title : '发明人3arp',width : 100,sortable : true},
						{field : 'threeauthorscore',title : 'arp3绩效',width : 80}, 
						{field : 'fourthInvector',title : '发明人4',width : 100,sortable : true},
						{field : 'fourthInvectorcode',title : '发明人4arp',width : 100,sortable : true},
						{field : 'fourauthorscore',title : 'arp4绩效',width : 80}, 
						{field : 'fifthInvector',title : '发明人5',width : 100,sortable : true},
						{field : 'fifthInvectorcode',title : '发明人5arp',width : 100,sortable : true},
						{field : 'fiveauthorscore',title : 'arp5绩效',width : 80}, 
						{field : 'otherInvector',title : '其他发明人',width : 150,sortable : true},
						{field : 'otherInvectorcode',title : '其他发明人arp',width : 150,sortable : true},
						{field : 'otherauthorscore',title : '其他arp绩效',width : 100},
						{field : 'savetime',title : '保存时间',width : 100}
//						{field : 'audit',title : '审核',width : 100,sortable : true}
				]],
		toolbar : '#tb',
		onLoadError : function() {
			$.messager.show({
				title : '提示',
				msg : '加载数据失败！'
			});
		},
		onLoadSuccess: function(data){  
			var rolename=$('#urole').val();	
			if(rolename=="科研人员"){
				$('#delAll').hide();
				$('#insert').hide();
				$('#del').hide();
				$('#update').hide();
				$('#import').hide();
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
			
			 var panel = $(this).datagrid('getPanel');   
		     var tr = panel.find('div.datagrid-body tr'); 
		     var fieldArray = ["firstauthorscore", "secauthorscore", "threeauthorscore", "fourauthorscore", "fiveauthorscore", "otherauthorscore"]
		     tr.each(function(){ 
		    	 for(var i = 0; i < fieldArray.length; i++){
		    		 var td = $(this).children('td[field="' + fieldArray[i] + '"]');   
			         td.children("div").css({  
			        	 "font-weight": "bolder",
			        	 "color": "#990000",
			        	 "text-align": "center"  
			         }); 
		    	 }
		            
		     });  
		 } 
	});
	
	//上传
	applyExcelUpload('#patent_import',"/patentAction!upLoadExcel.action",null,'#center_table');
	
};

/******************************************* Thesis Operator ******************************************/
	//论文添加
	var append = function(){
		
		$('#add_firstInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_firstInvector");
		});
		$('#add_secondInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_secondInvector");
		});
		$('#add_thirdInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_thirdInvector");
		});
		$('#add_fourthInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_fourthInvector");
		});
		$('#add_fifthInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("add_fifthInvector");
		});
		$('#add_otherInvector').textbox('textbox').bind('click', function() {
			searchOtherAuthor("add_otherInvector");
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
        
		openDialog('admin_patent_addDialog');
		$('#admin_patent_addForm').form('clear');
		applyAjaxFileUpload('#lw_upload',"/upload.action");
	};
	
	//确认添加
	var add_patent_btn_ok = function(){
		$('#admin_patent_addForm').form('submit', {
			url : $('#contextPath').val() + '/patentAction!add.action',
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
					
					$('#admin_patent_addDialog').dialog('close');
				}else{
					$.messager.alert("操作提示", "操作失败,专利已存在！","error");
				}
			}
		});
	};
	
	//取消添加
	var add_patent_btn_cancel = function(){
		$('#admin_patent_addDialog').dialog('close');
	};
	
	
	var removeAll = function(){
		$.messager.confirm('确认', '您是否要清空所有的数据？', function(rd) {
			if (rd) {
				$.ajax({
					url : $('#contextPath').val() + '/patentAction!removeAll.action',
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
		
		$.messager.confirm("确认", "您确定要删除选择的专利记录？", function(r){
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
					url: $('#contextPath').val() + '/patentAction!remove.action',
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
	    $('#admin_patent_editForm input').val();
	    
	    $('#e_firstInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_firstInvector");
		});
		$('#e_secondInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_secondInvector");
		});
		$('#e_thirdInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_thirdInvector");
		});
		$('#e_fourthInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_fourthInvector");
		});
		$('#e_fifthInvector').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_fifthInvector");
		});
		$('#e_otherInvector').textbox('textbox').bind('click', function() {
			searchOtherAuthor("e_otherInvector");
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
			$('#e_name').textbox('setValue', rows[len-1].name);
			$('#e_year').combobox('setValue', rows[len-1].year);
			$('#e_code').textbox('setValue', rows[len-1].code);
			$('#e_date').textbox('setValue', rows[len-1].date);
			$('#e_type').textbox('setValue', rows[len-1].type);
			$('#e_owner').textbox('setValue', rows[len-1].owner);
			$('#e_other').textbox('setValue', rows[len-1].other);
			$('#e_rank').textbox('setValue', rows[len-1].rank);
			$('#e_firstInvector').textbox('setValue', rows[len-1].firstInvector);
			$('#e_secondInvector').textbox('setValue', rows[len-1].secondInvector);
			$('#e_thirdInvector').textbox('setValue', rows[len-1].thirdInvector);
			$('#e_fourthInvector').textbox('setValue', rows[len-1].fourthInvector);
			$('#e_fifthInvector').textbox('setValue', rows[len-1].fifthInvector);
			$('#e_otherInvector').textbox('setValue', rows[len-1].otherInvector);
			$('#e_firstInvectorcode').val(rows[len-1].firstInvectorcode);
			$('#e_secondInvectorcode').val(rows[len-1].secondInvectorcode);
			$('#e_thirdInvectorcode').val(rows[len-1].thirdInvectorcode);
			$('#e_fourthInvectorcode').val(rows[len-1].fourthInvectorcode);
			$('#e_fifthInvectorcode').val(rows[len-1].fifthInvectorcode);
			$('#e_otherInvectorcode').val(rows[len-1].otherInvectorcode);
			
			openDialog('admin_patent_editDialog');
			applyAjaxFileUpload('#e_lw_upload',"/upload.action");
		} else {
			$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		}
	};
	
	var edit_patent_btn_ok = function(){
		$('#admin_patent_editForm').form('submit',{		
			url: $('#contextPath').val() + '/patentAction!edit.action', 
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
					$('#center_table').datagrid('reload').datagrid('clearChecked');
				}
				
				$.messager.show({
					title:'提示',
					msg:objr.msg
				});
				
				$('#admin_patent_editDialog').dialog('close');
			}
		});
	};
	
	var edit_patent_btn_cancel = function(){
		$('#admin_patent_editDialog').dialog('close');
	};
	
	
	function lunwen() {
		$('#center_table').datagrid({
			url : $('#contextPath').val() + '/patentAction!datagrid.action',
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
	var patentAdvancedSearch = function (){
		//year 
        var currYear = new Date().getFullYear(), yearData = [];
        for(var i=1990;i<=currYear+1;i++){
        	yearData[i-1990] = {};
        	yearData[i-1990].id = i;
         	yearData[i-1990].text = i;
        }
        
        yearData = yearData.reverse();
        $('#patent_begin_year').combobox('loadData', yearData);
        $('#patent_begin_year').combobox('select', yearData[0].id);
        $('#patent_begin_year').combobox('setValue', '');
        $('#patent_end_year').combobox('loadData', yearData);
        $('#patent_end_year').combobox('select', yearData[0].id);
        $('#patent_end_year').combobox('setValue', '');
		
		var top = $('#lw_statusfilter').offset().top + 30;
		var left = $('#lw_statusfilter').offset().left;
		$('#patentAdvancedSearch_dialog').dialog('open').dialog('resize',{top: top,left:left});
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
		var begin_year = $('#patent_begin_year').combobox('getValue');
		var end_year = $('#patent_end_year').combobox('getValue');
		
		var author = $('#patent_lw_author').val();
		var name = $('#patent_lw_name').val();
		var authorcode = $('#patent_lw_authorcode').val();
		
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
		$('#patentAdvancedSearch_dialog').dialog('close');
	};
	
	//高级查询取消
	var adv_search_btn_cancel = function(){
		$('#patentAdvancedSearch_dialog').dialog('close');
	};

    //论文导入
    var importfunc=function(){
    	openDialog('import_patent');
    };
   
    //论文导出
    var exportfunc=function(){
    	//$('#export_lw').dialog("open");
    	downloadExcel('patentAction!downloadExcel.action');
    };
    
    var exportlw_btn_ok = function(){
		$('#admin_thesis_exportForm').form('submit',{		
			url: $('#contextPath').val() + '/patentAction!edit.action', 
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