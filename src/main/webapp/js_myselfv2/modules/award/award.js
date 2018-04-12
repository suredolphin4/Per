/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {
	$('#center_table')
			.datagrid(
					{
						url : $('#contextPath').val()
								+ '/awardAction!datagrid.action',
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
						columns : [ [
								{
									field : 'id',
									title : '编号',
									width : 150,
									checkbox : true
								},
								{
									field : 'year',
									title : '年度',
									width : 50,
									sortable : true
								},
								{
									field : 'awardProjectName',
									title : '奖励项目名称',
									width : 250,
									sortable : true
								},
								{
									field : 'awardName',
									title : '奖励名称',
									width : 250,
									sortable : true
								},
								{
									field : 'auditStatus',
									title : '审核状态',
									width : '100',
									sortable : true
								},	
								{
									field : 'auditOpinion',
									title : '审核意见',
									width : '100',
									sortable : true
								},	
								{
									field : 'unit',
									title : '完成单位',
									width : 150,
									sortable : true
								}, 
								{
									field : 'geoRank',
									title : '我所排名',
									width : 50,
									sortable : true
								},
								{
									field : 'department',
									title : '授奖部门',
									width : 150,
									sortable : true
								},
								{
									field : 'level',
									title : '奖励级别',
									width : 100,
									sortable : true
								},
								{
									field : 'rank',
									title : '奖励等级',
									width : 100,
									sortable : false
								},																										
								{
									field : 'awardeeOne',
									title : '获奖人1',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeOne',
									title : '获奖人1arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee1score',
									title : 'arp1绩效',
									width : 50
								},
								{
									field : 'awardeeTwo',
									title : '获奖人2',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeTwo',
									title : '获奖人2arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee2score',
									title : 'arp2绩效',
									width : 50
								},
								{
									field : 'awardeeThree',
									title : '获奖人3',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeThree',
									title : '获奖人3arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee3score',
									title : 'arp3绩效',
									width : 50
								},
								{
									field : 'awardeeFour',
									title : '获奖人4',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeFour',
									title : '获奖人4arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee4score',
									title : 'arp4绩效',
									width : 50
								},
								{
									field : 'awardeeFive',
									title : '获奖人5',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeFive',
									title : '获奖人5arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee5score',
									title : 'arp5绩效',
									width : 50
								},
								{
									field : 'awardeeSix',
									title : '获奖人6',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeSix',
									title : '获奖人6arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee6score',
									title : 'arp6绩效',
									width : 50
								},
								{
									field : 'awardeeSeven',
									title : '获奖人7',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeSeven',
									title : '获奖人7arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee7score',
									title : 'arp7绩效',
									width : 50
								},
								{
									field : 'awardeeEight',
									title : '获奖人8',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeEight',
									title : '获奖人8arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee8score',
									title : 'arp8绩效',
									width : 50
								},
								{
									field : 'awardeeNine',
									title : '获奖人9',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeNine',
									title : '获奖人9arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee9score',
									title : 'arp9绩效',
									width : 50
								},
								{
									field : 'awardeeTen',
									title : '获奖人10',
									width : 100,
									sortable : true
								},
								{
									field : 'awardeeCodeTen',
									title : '获奖人10arp',
									width : 100,
									sortable : true
								},
								{
									field : 'awardee10score',
									title : 'arp10绩效',
									width : 50
								},
								{
									field : 'awardeeOther',
									title : '其他获奖人',
									width : 150,
									sortable : true
								},
								{
									field : 'awardeeCodeOther',
									title : '其他获奖人arp',
									width : 150,
									sortable : true
								},
								{
									field : 'otherawardeescore',
									title : '其他arp绩效',
									width : 50
								},
								{
									field : 'submitUser',
									title : '提交人',
									width : 150,
									sortable : true
								},
								{
									field : 'submitTime',
									title : '提交时间',
									width : 150,
									sortable : true
								},
								{
									field : 'append',
									title : '附件',
									width : 150,
									resizable : true,
									formatter : function(value, rowData, rowIndex) {
										// function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
										if (value) {
											var downloadurl = $('#contextPath')
													.val()
													+ "/download.action"
													+ "?"
													+ "fileName=" + encodeURIComponent(encodeURIComponent(value));
											return "<a href='" + downloadurl
													+ "' >" + value
													+ "</a>"; 
										}
									}
								} ] ],
						toolbar : '#tb',
						onLoadSuccess: function(data){  
							var rolename = $('#urole').val();
							if (rolename == "科研人员") {
								$('#import').hide();
								$('#audit').hide();
							} else if (rolename == "秘书") {
								$('#insert').hide();
								$('#del').hide();
								$('#sub').hide();
								$('#revoke').hide();
								//$('#update').hide();
								$('#import').hide();
								$('#audit').hide();
							} else if (rolename == "管理员") {
								$('#revoke').hide();
							} else {
								$('#insert').hide();
								$('#del').hide();
								$('#sub').hide();
								$('#update').hide();
								$('#import').hide();
							}
							
							 var panel = $(this).datagrid('getPanel');   
						     var tr = panel.find('div.datagrid-body tr'); 
						     var fieldArray = ["awardee1score", "awardee2score", "awardee3score", "awardee4score", "awardee5score","awardee6score","awardee7score", "awardee8score", "awardee9score", "awardee10score", "otherawardeescore"]
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

	 $('#wt_audit_filter').combobox({
		 onSelect: function(record){
			 var item = record.value;
			 $('#center_table').datagrid({
				 onBeforeLoad: function(param){
					 param.auditfilter = item;
				 }
			 });
		 }
	 });
	 
	//关闭dialog时，清空附件信息
	$('#award_append_dialog').dialog({
		onClose:  function(){
			$('#e_awd_append').val('');
			clearUpload('awd_upload');
		}
	}); 
	
	$('#award_edit_dialog').dialog({
		onClose:  function(){
			$('#e_awd_append').val('');
			clearUpload('e_awd_upload');
		}
	});

	// 上传
	applyExcelUpload('#award_import', "/awardAction!upLoadExcel.action", null,'#center_table');

};

//奖励Excel导入
var award_import_excel = function() {
	$('#import_award_dialog').dialog("open");
};

//奖励Excel导出
var award_export_excel = function(){
	downloadExcel('awardAction!downloadExcel.action');
};

//=====================================奖励增加===========================================
var award_append = function(){
	//处理easyui-textbox click绑定
	$('#awdee_one').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_one");
	});

	$('#awdee_two').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_two");
	});
	
	$('#awdee_three').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_three");
	});
	
	$('#awdee_four').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_four");
	});
	
	$('#awdee_five').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_five");
	});
	
	$('#awdee_six').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_six");
	});
	
	$('#awdee_seven').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_seven");
	});
	
	$('#awdee_eight').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_eight");
	});
	
	$('#awdee_nine').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_nine");
	});
	
	$('#awdee_ten').textbox('textbox').bind('click', function() {
		searchObjAuthor("awdee_ten");
	});
	
	$('#awdee_other').textbox('textbox').bind('click', function() {
		searchOtherAuthor("awdee_other");
	});
	
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}
	yearData = yearData.reverse();
	$('#awd_year').combobox('loadData', yearData);
	
	// unitrank
	var unitData = [];
	for (var i = 0; i < 9; i++) {
		unitData[i] = {};
		unitData[i].id = i + 1;
		unitData[i].text = i + 1;
	}
	unitData.push({
		id : '9以后',
		text : '9以后'
	});
	unitData.push({
		id : '无单位排名',
		text : '无单位排名'
	});
	$('#awd_georank').combobox('loadData', unitData);
	
	$('#award_append_dialog').dialog("open");
	$('#award_append_form').form('clear');
	applyAjaxFileUpload('#awd_upload', "/upload.action", "#awd_append");
};

var addaward_btn_ok = function(){
	$('#award_append_form').form('submit', {
		url:  $('#contextPath').val() + '/awardAction!append.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
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

				$('#award_append_dialog').dialog("close");
			} else {
				if(obj.msg === "duplicate row")
					$.messager.alert("操作提示", "操作失败, 奖励已存在！", "error");
			}
		}
	});
};

var addaward_btn_cancel = function(){
	$('#award_append_dialog').dialog("close");
};
//=========================================================================

//奖励删除
var award_remove = function(){
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
			if (rows[i].auditStatus === '审核通过' ) {
				$.messager.alert("警告", "您不能删除审核通过的记录，请重新选择！");
				return;
			}else if(rows[i].auditStatus === '已提交'){
				$.messager.alert("警告", "您不能删除已提交的记录，请重新选择！");
				return;
			}
		}
	}

	$.messager.confirm("确认", "您确定要删除选择的奖励记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.ids = '';
			for (var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.ids += rows[i].id;
					continue;
				}
				toRemove.ids += (rows[i].id + ',');
			}
			var remove = JSON.stringify(toRemove);
			$.ajax({
				url : $('#contextPath').val() + '/awardAction!remove.action',
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
						// 重新加载奖励列表
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

//奖励修改
var award_edit = function(){
	//获取当前编辑行
	var rows = $('#center_table').datagrid('getChecked');
	if(rows.length < 1){
		$.messager.alert("提示", "请选中一行后编辑！");
		return;
	}else if(rows.length > 1){
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		return;
	}
	
	// 防御，若为科研人员，则不能删除“审核”通过的记录
	var rolename = $('#urole').val();
	
//	if (rolename == "科研人员") {
//		if (rows[0].auditStatus === '审核通过') {
//			$.messager.alert("警告", "您不能编辑审核通过的记录，请重新选择！");
//			return;
//		} else if (rows[0].auditStatus === '已提交') {
//			$.messager.alert("警告", "您不能编辑已提交的记录，请重新选择！");
//			return;
//		}
//	}
	
	//处理easyui-textbox click绑定
	$('#e_awdee_one').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_one");
	});

	$('#e_awdee_two').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_two");
	});
	
	$('#e_awdee_three').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_three");
	});
	
	$('#e_awdee_four').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_four");
	});
	
	$('#e_awdee_five').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_five");
	});
	
	$('#e_awdee_six').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_six");
	});
	
	$('#e_awdee_seven').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_seven");
	});
	
	$('#e_awdee_eight').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_eight");
	});
	
	$('#e_awdee_nine').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_nine");
	});
	
	$('#e_awdee_ten').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_awdee_ten");
	});
	
	$('#e_awdee_other').textbox('textbox').bind('click', function() {
		searchOtherAuthor("e_awdee_other");
	});
	
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}
	yearData = yearData.reverse();
	$('#e_awd_year').combobox('loadData', yearData);
	
	// unitrank
	var unitData = [];
	for (var i = 0; i < 9; i++) {
		unitData[i] = {};
		unitData[i].id = i + 1;
		unitData[i].text = i + 1;
	}
	unitData.push({
		id : '9以后',
		text : '9以后'
	});
	unitData.push({
		id : '无单位排名',
		text : '无单位排名'
	});
	$('#e_awd_georank').combobox('loadData', unitData);
	
	//回显
	var row = rows[0];
	$('#e_awd_id').val(row.id);
	$('#e_awd_name').textbox('setValue', row.awardName);
	$('#e_awd_pro_name').textbox('setValue', row.awardProjectName);
	$('#e_awd_unit').textbox('setValue', row.unit);
	$('#e_awd_department').textbox('setValue', row.department);
	$('#e_awd_year').combobox('setValue', row.year);
	$('#e_awd_georank').textbox('setValue', row.geoRank);
	$('#e_awd_level').combobox('setValue', row.level);
	$('#e_awd_rank').combobox('setValue', row.rank);
	$('#e_awd_num').textbox('setValue', row.numOfAwardee);
	$('#e_awdee_one').textbox('setValue', row.awardeeOne);
	$('#e_awdee_onecode').val(row.awardeeCodeOne);
	$('#e_awdee_two').textbox('setValue', row.awardeeTwo);
	$('#e_awdee_twocode').val(row.awardeeCodeTwo);
	$('#e_awdee_three').textbox('setValue', row.awardeeThree);
	$('#e_awdee_threecode').val(row.awardeeCodeThree);
	$('#e_awdee_four').textbox('setValue', row.awardeeFour);
	$('#e_awdee_fourcode').val(row.awardeeCodeFour);
	$('#e_awdee_five').textbox('setValue', row.awardeeFive);
	$('#e_awdee_fivecode').val(row.awardeeCodeFive);
	$('#e_awdee_six').textbox('setValue', row.awardeeSix);
	$('#e_awdee_sixcode').val(row.awardeeCodeSix);
	$('#e_awdee_seven').textbox('setValue', row.awardeeSeven);
	$('#e_awdee_sevencode').val(row.awardeeCodeSeven);
	$('#e_awdee_eight').textbox('setValue', row.awardeeEight);
	$('#e_awdee_eightcode').val(row.awardeeCodeEight);
	$('#e_awdee_nine').textbox('setValue', row.awardeeNine);
	$('#e_awdee_ninecode').val(row.awardeeCodeNine);
	$('#e_awdee_ten').textbox('setValue', row.awardeeTen);
	$('#e_awdee_tencode').val(row.awardeeCodeTen);
	$('#e_awdee_other').textbox('setValue', row.awardeeOther);
	$('#e_awdee_othercode').val(row.awardeeCodeOther);
	
	if(rolename === "科研人员" && rows.auditStatus === "已退回"){
		$('#e_awd_auditstatus').val("已保存");
	}else{
		$('#e_awd_auditstatus').val(row.auditStatus);
	}

	if(!row.append){
		//当前记录不存在附件
		applyAjaxFileUpload('#e_awd_upload', "/upload.action", "#e_awd_append");
	}else{
		$('#e_awd_append').val(row.append);
		editShowUpload('#e_awd_upload', '#e_awd_append', row.append);
	}
	
	EditValidate($('#award_edit_form'), rows[0].auditStatus);
	
	$('#award_edit_dialog').dialog('open');
};

var editaward_btn_ok = function(){
	$('#award_edit_form').form('submit', {
		url: $('#contextPath').val() + '/awardAction!edit.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			//修改成功，解析返回的json信息
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
				//重新加载论文列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');

				$.messager.show({
					title : '提示',
					msg : obj.msg
				});

				$('#award_edit_dialog').dialog('close');
			}else{
				if(obj.msg === "duolicate row")
					$.messager.alert("操作提示", "操作失败,奖励已存在！", "error");
			}
		}
	});
};

var editaward_btn_cancel = function(){
	$('#award_edit_dialog').dialog('close');
};

//奖励提交
var award_submit = function(){
	// 获取要提交的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可提交的记录！'
		});
		return;
	}

	// 将需要提交的奖励列表
	var toSubmit = {};
	toSubmit.submitUser = $('#username').val();
	toSubmit.awdList = [];
	for (var i = 0; i < rows.length; i++) {
		// 防御，排除审核状态高于“审核通过”的记录
		if (rows[i].examinestatus === '审核通过' || rows[i].examinestatus === '已提交') {
			$.messager.alert('警告', '您不能重复提交！');
			return;
		}
		toSubmit.awdList.push({
			id : rows[i].id
		});
	}

	var awdlist = JSON.stringify(toSubmit);
	$.ajax({
		url : $('#contextPath').val() + '/awardAction!submit.action',
		data : 'awdlist=' + awdlist,
		error : function() {
			$.messager.show({
				title : '错误',
				msg : '提交出现错误！请联系管理员处理！'
			});
		},
		success : function(data) {
			json = $.parseJSON(data);
			if (json.success) {
				// 重新加载论文列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

//已提交奖励撤回
var award_revoke = function(){
	// 获取要提交的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可撤销的记录！'
		});
		return;
	}

	$.messager.confirm("确认", "您确定要撤回选择的奖励记录？", function(r) {
		if (r) {

			//将需要撤销的奖励列表
			var toRevoke = {};
			toRevoke.awdList = [];
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].auditStatus !== '已提交') {
					$.messager.alert('警告', '您只能撤回已提交的记录，请仔细检查选择的奖励记录！');
					return;
				}

				toRevoke.awdList.push({
					id : rows[i].id
				});
			}

			var awdlist = JSON.stringify(toRevoke);
			$.ajax({
				url : $('#contextPath').val() + '/awardAction!revoke.action',
				data : 'awdlist=' + awdlist,
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '撤销出现错误！请联系管理员处理！'
					});
				},
				success : function(data) {
					json = $.parseJSON(data);
					if (json.success) {
						// 重新加载奖励列表
						$('#center_table').datagrid('reload').datagrid(
								'clearChecked');
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

//奖励审核
var award_audit = function(){
	// 只能审核状态为“已提交”的记录
	// 获取要审核的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可审核的记录！'
		});
		return;
	}

	//审核界面
	$('#awd_audit_dialog').dialog('open');
};

//奖励审核通过
var auditaward_btn_ok = function(){
	var rows = $('#center_table').datagrid('getChecked');
	var auditValue = $("#awd_audit_form input[type='radio'][name='audit']:checked")
			.val(), auditOpinion = $('#awd_audit_opinion').textbox('getValue');

	//将需要提交的论文列表
	var toSubmit = {};
	toSubmit.audit = auditValue;
	toSubmit.auditOpinion = auditOpinion;
	toSubmit.awdList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		toSubmit.awdList.push({
			id : rows[i].id
		});
	}

	var audit = JSON.stringify(toSubmit);

	$.ajax({
		url : $('#contextPath').val() + '/awardAction!audit.action',
		data : 'audit=' + audit,
		error : function() {
			$.messager.show({
				title : '错误',
				msg : '审核出现错误！请联系管理员处理！'
			});
		},
		success : function(data) {
			json = $.parseJSON(data);
			if (json.success) {
				// 重新加载论文列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}

			$('#awd_audit_dialog').dialog('close');

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

//取消奖励审核操作
var auditaward_btn_cancel = function(){
	$('#awd_audit_dialog').dialog('close');
};

//刷新
var award_refresh = function(){
	$('#center_table').datagrid("reload");
};

//高级查询
var advancedSearch = function() {
	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for ( var i = 1990; i <= currYear+1; i++) {
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

	var top = $('#lw_statusfilter').offset().top + 30;
	var left = $('#lw_statusfilter').offset().left;
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

//高级查询确定
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

