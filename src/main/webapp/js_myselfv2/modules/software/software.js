/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {
	$('#center_table')
			.datagrid(
					{
						url : $('#contextPath').val()
								+ '/softwareAction!datagrid.action',
						fit : true,
						fitColumns : false,
						border : false,
						pagination : true,
						idField : 'rjid',
						pageSize : 100,
						pageList : [20,50,100,150,200],
						sortName : 'title',
						sortOrder : 'asc',
						checkOnSelect : true,
						selectOnCheck : false,
						queryParams: {
							usercode : $('#usercode').val()
						},
						columns : [ [
								{
									field : 'rjid',
									title : '编号',
									width : 50,
									checkbox : true
								},
								{
									field : 'year',
									title : '年份',
									width : 50,
									sortable : true
								},
								{
									field : 'title',
									title : '软件名称',
									width : 300,
									sortable : true
								},
								
								{
									field : 'registernum',
									title : '登记号',
									width : 150,
									resizable : true
								},
								{
									field : 'auditdate',
									title : '登记批准日期',
									width : 150,
									resizable : true
								},
								{
									field : 'copyright',
									title : '著作权人',
									width : 150,
									resizable : true
								},
								{
									field : 'othercopyright',
									title : '其他著作权人',
									width : 150,
									resizable : true
								},
								{
									field : 'departorder',
									title : '单位排名',
									width : 90,
									sortable : false
								},								
								{
									field : 'firstauthor',
									title : '设计人1',
									width : 100,
									resizable : true
								},
								{
									field : 'firstauthorcode',
									title : '设计人1arp',
									width : 100,
									resizable : true
								},
								{
									field : 'firstauthorscore',
									title : 'arp1绩效',
									width : 80,
									resizable : true
								},
								{
									field : 'secauthor',
									title : '设计人2',
									width : 100,
									resizable : true
								},
								{
									field : 'secauthorcode',
									title : '设计人2arp',
									width : 100,
									resizable : true
								},
								{
									field : 'secauthorscore',
									title : 'arp2绩效',
									width : 80,
									resizable : true
								},
								{
									field : 'threeauthor',
									title : '设计人3',
									width : 100,
									resizable : true
								},
								{
									field : 'threeauthorcode',
									title : '设计人3arp',
									width : 100,
									resizable : true
								},
								{
									field : 'threeauthorscore',
									title : 'arp3绩效',
									width : 80,
									resizable : true
								},
								{
									field : 'fourauthor',
									title : '设计人4',
									width : 100,
									resizable : true
								},
								{
									field : 'fourauthorcode',
									title : '设计人4arp',
									width : 100,
									resizable : true
								},
								{
									field : 'fourauthorscore',
									title : 'arp4绩效',
									width : 80,
									resizable : true
								},
								{
									field : 'fiveauthor',
									title : '设计人5',
									width : 100,
									resizable : true
								},
								{
									field : 'fiveauthorcode',
									title : '设计人5arp',
									width : 100,
									resizable : true
								},
								{
									field : 'fiveauthorscore',
									title : 'arp5绩效',
									width : 80,
									resizable : true
								},
								{
									field : 'otherauthor',
									title : '其他设计人',
									width : 150,
									resizable : true
								},
								{
									field : 'otherauthorcode',
									title : '其他设计人arp',
									width : 150,
									resizable : true
								},
								{
									field : 'otherauthorscore',
									title : '其他arp绩效',
									width : 80,
									resizable : true
								}
								
								 ] ],
						toolbar : '#tb',
						onLoadError : function() {
							$.messager.show({
								title : '提示',
								msg : '加载数据失败！'
							});
						},
						onLoadSuccess: function(data){  
							var rolename = $('#urole').val();
							if (rolename == "科研人员") {
								$('#insert').hide();
								$('#del').hide();
								$('#update').hide();
								$('#import').hide();
								$('#audit').hide();
								$('#delAll').hide();
							} else if (rolename == "秘书") {
								$('#delAll').hide();
								$('#insert').hide();
								$('#del').hide();
								$('#sub').hide();
								$('#update').hide();
								$('#import').hide();
							} else if (rolename == "管理员") {

							} else {
								$('#delAll').hide();
								$('#insert').hide();
								$('#del').hide();
								$('#sub').hide();
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
	
	$('#add_auditdate').datebox({	    
	    editable:false
	});
	$('#e_rj_auditdate').datebox({	    
	    editable:false
	});
	// 上传
	applyExcelUpload('#rj_import', "/softwareAction!upLoadExcel.action", null,'#center_table');

};

/**
 * ***************************************** Thesis Operator
 * *****************************************
 */

var removeAll = function(){
	$.messager.confirm('确认', '您是否要清空所有的数据？', function(rd) {
		if (rd) {
			$.ajax({
				url : $('#contextPath').val() + '/softwareAction!removeAll.action',
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
// 论文删除操作
var removefunc = function() {
	// 获取要删除的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可删除的记录！'
		});
		return;
	}

	// 防御，若为科研人员，则不能删除“审核”通过的记录
	var rolename = $('#urole').val();
	if (rolename == "科研人员") {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].examinestatus === '审核通过') {
				$.messager.alert("警告", "您不能删除审核通过的记录，请重新选择！");
				return;
			}
		}
	}

	$.messager.confirm("确认", "您确定要删除选择的论文记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.rjids = '';
			for (var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.rjids += rows[i].rjid;
					continue;
				}
				toRemove.rjids += (rows[i].rjid + ',');
			}
			var remove = JSON.stringify(toRemove);
			$.ajax({
				url : $('#contextPath').val() + '/softwareAction!remove.action',
				data : 'remove=' + remove,
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '删除出现错误！请联系管理员处理！'
					});
				},
				success : function(data) {
					json = $.parseJSON(data);
					if (json.success) {
						// 重新加载论文列表
						refreshfunc();
					}

					$.messager.show({
						title : '提示',
						msg : json.msg
					});
				},
				type : 'POST'
			});
		}
	});
};


// 高级查询
var advancedSearch = function() {
	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}

	yearData = yearData.reverse();
	$('#s_begin_year').combobox('loadData', yearData);
	$('#s_begin_year').combobox('select', yearData[0].id);
	$('#s_begin_year').combobox('setValue', '');
	$('#s_end_year').combobox('loadData', yearData);
	$('#s_end_year').combobox('select', yearData[0].id);
	$('#s_end_year').combobox('setValue', '');
	
	var top = $('#rj_statusfilter').offset().top + 30;
	var left = $('#rj_statusfilter').offset().left;
	$('#advancedSearch_dialog').dialog('open').dialog('resize', {
		top : top,
		left : left
	});
	// 实验室、领域动态获取
	var url = $('#contextPath').val() + "/domainAction!datagrid.action";
	$.getJSON(url, function(json) {
		$('#s_which_domain').combobox(
				{
					data : json.rows,
					valueField : 'domainid',
					textField : 'name',
					onSelect : function(record) {
						var domainid = record.domainid;
						var labUrl = $('#contextPath').val()
								+ "/labAction!datagrid.action";
						$.getJSON(labUrl, {
							domaincode : domainid
						}, function(labjson) {
							$('#s_which_lab').combobox({
								data : labjson.rows,
								valueField : 'labid',
								textField : 'name'
							});
						});
					}
				});
	});
};

// 高级查询确定
var adv_search_btn_ok = function() {
	// 获取过滤条件
	var begin_year = $('#s_begin_year').combobox('getValue');
	var end_year = $('#s_end_year').combobox('getValue');

	var author = $('#s_lw_author').val();
	var name = $('#s_lw_name').val();
	var authorcode = $('#s_lw_authorcode').val();

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.s_begin_year = begin_year;
			param.s_end_year = end_year;
			param.s_lw_author = author;
			param.s_lw_name = name;
			param.s_lw_authorcode = authorcode;
		}
	});

	// 最后关闭
	$('#advancedSearch_dialog').dialog('close');
};

// 高级查询取消
var adv_search_btn_cancel = function() {
	$('#advancedSearch_dialog').dialog('close');
};

// 论文导入
var importrjfunc = function() {
	$('#import_rj').dialog("open");
};

// 论文导出
var exportrjfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('softwareAction!downloadExcel.action');
};

var exportrj_btn_ok = function() {
	$('#admin_soft_exportForm').form(
			'submit',
			{
				url : $('#contextPath').val() + '/softwareAction!edit.action',
				onSubmit : function(param) {
					return $(this).form('validate');
				},
				success : function(r) {
					// 修改成功，解析返回的json信息
					var objr = jQuery.parseJSON(r);
					if (objr.success) {
						// 更新论文列表datagrid
						$('#center_table').datagrid(
								'updateRow',
								{
									index : $('#center_table').datagrid(
											'getRowIndex', rows[0].rjid),
									row : objr.obj
								});

						// 重新加载论文列表
						 //$('#center_table').datagrid('reload');
					}

					$.messager.show({
						title : '提示',
						msg : objr.msg
					});

					d.dialog('close');
				}
			});
};

var exportrj_btn_cancel = function() {
	$('#admin_soft_exportForm').dialog('close');
};

function downloadFile(fileName, content) {
	var aLink = document.createElement('a');
	var blob = new Blob([ content ]);
	var evt = document.createEvent("HTMLEvents");
	evt.initEvent("click", false, false);// initEvent 不加后两个参数在FF下会报错, 感谢
											// Barret Lee 的反馈
	aLink.download = fileName;
	aLink.href = URL.createObjectURL(blob);
	aLink.dispatchEvent(evt);
}


//软件属性编辑操作
var editfunc = function(){
    $('#admin_software_editForm input').val();
    
    //year 
    var currYear = new Date().getFullYear(), yearData = [];
    for(var i=1990;i<=currYear+1;i++){
    	yearData[i-1990] = {};
    	yearData[i-1990].id = i;
     	yearData[i-1990].text = i;
    }
    
	// 处理eayui-textbox click绑定
	$('#e_rj_firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_rj_firstauthor");
	});

	$('#e_rj_secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_rj_secauthor");
	});

	$('#e_rj_threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_rj_threeauthor");
	});

	$('#e_rj_fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_rj_fourauthor");
	});

	$('#e_rj_fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_rj_fiveauthor");
	});

	$('#e_rj_otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("e_rj_otherauthor");
	});
    
    yearData = yearData.reverse();
    $('#e_year').combobox('loadData', yearData);
    $('#e_year').combobox('select', yearData[0].id);
    
	//获取当前选中行的id
	var rows = $('#center_table').datagrid('getChecked');
	//只能编辑一行
	if(rows.length==1){
		//回显
		$('#e_rjid').val(rows[0].rjid);
		$('#e_title').textbox('setValue', rows[0].title);
		$('#e_year').combobox('setValue', rows[0].year);
		$('#e_registernum').textbox('setValue', rows[0].registernum);
		$('#e_auditdate').textbox('setValue', rows[0].auditdate);
		$('#e_examinestatus').textbox('setValue', rows[0].examinestatus);
		$('#e_copyright').textbox('setValue', rows[0].copyright);
		$('#e_othercopyright').textbox('setValue', rows[0].othercopyright);
		$('#e_departorder').textbox('setValue', rows[0].departorder);
		$('#e_rj_firstauthor').textbox('setValue', rows[0].firstauthor);
		$('#e_rj_secauthor').textbox('setValue', rows[0].secauthor);
		$('#e_rj_threeauthor').textbox('setValue', rows[0].threeauthor);
		$('#e_rj_fourauthor').textbox('setValue', rows[0].fourauthor);
		$('#e_rj_fiveauthor').textbox('setValue', rows[0].fiveauthor);
		$('#e_rj_otherauthor').textbox('setValue', rows[0].otherauthor);
		$('#e_rj_firstauthorcode').val(rows[0].firstauthorcode);
		$('#e_rj_secauthorcode').val(rows[0].secauthorcode);
		$('#e_rj_threeauthorcode').val(rows[0].threeauthorcode);
		$('#e_rj_fourauthorcode').val(rows[0].fourauthorcode);
		$('#e_rj_fiveauthorcode').val(rows[0].fiveauthorcode);
		$('#e_rj_otherauthorcode').val(rows[0].otherauthorcode);
		
	//	$('#admin_software_editDialog').dialog('open');	
		//applyAjaxFileUpload('#e_lw_upload',"/upload.action");
		
		
		openDialog('admin_software_editDialog');
	
	} else {
		//Todo 弹出提示消息
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
	}
};

var edit_software_btn_ok = function(){
	$('#admin_software_editForm').form('submit',{		
		url: $('#contextPath').val() + '/softwareAction!edit.action', 
		onSubmit: function(param){
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			//修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				//重新加载论文列表
				//$('#center_table').datagrid('load');
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}
			
			$.messager.show({
				title:'提示',
				msg:objr.msg
			});
			
			$('#admin_software_editDialog').dialog('close');
		}
	});
	//$('#center_table').datagrid('reload');
};

var edit_software_btn_cancel = function(){
	$('#admin_software_editDialog').dialog('close');
};

//论文添加
var append = function(){
	
	$('#rj_firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("rj_firstauthor");
	});

	$('#rj_secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("rj_secauthor");
	});

	$('#rj_threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("rj_threeauthor");
	});

	$('#rj_fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("rj_fourauthor");
	});

	$('#rj_fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("rj_fiveauthor");
	});

	$('#rj_otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("rj_otherauthor");
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
    
	$('#admin_software_addDialog').dialog('open');
	$('#admin_software_addForm').form('clear');
	applyAjaxFileUpload('#lw_upload',"/upload.action");
};

//确认添加
var add_software_btn_ok = function(){
	$('#admin_software_addForm').form('submit', {
		url : $('#contextPath').val() + '/softwareAction!add.action',
		onSubmit: function(param){
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
//				$('#center_table').datagrid('insertRow',{
//					index:0,
//					row:obj.obj
//				});
				
				$('#center_table').datagrid('reload');
									
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
				
				$('#admin_software_addDialog').dialog('close');
			}else{
				$.messager.alert("操作提示", "操作失败,专利已存在！","error");
			}
		}
	});
};

//取消添加
var add_software_btn_cancel = function(){
	$('#admin_software_addDialog').dialog('close');
};
