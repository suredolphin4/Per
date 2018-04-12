/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {
	$('#center_table')
			.datagrid(
					{
						url : $('#contextPath').val()
								+ '/thesisAction!datagrid.action',
						fit : true,
						fitColumns : false,
						border : false,
						pagination : true,
						idField : 'lwid',
						pageSize : 100,
						pageList : [20,50,100,150,200],
						sortName : 'savetime',
						sortOrder : 'desc',
						checkOnSelect : true,
						selectOnCheck : false,
						queryParams : {
							usercode : $('#usercode').val()
						},
						columns : [ [
								{
									field : 'lwid',
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
									field : 'pubkind',
									title : '刊物类别',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'title',
									title : '论文题目',
									width : 300,
									sortable : true
								},
								{
									field : 'examinestatus',
									title : '审核状态',
									width : 100,
									sortable : true
								},	
								{
									field : 'auditUser',
									title : '审核人',
									width : 100,
									sortable : true
								},
								{
									field : 'auditOpinion',
									title : '审核意见',
									width : 100,
									sortable : true
								},	
								{
									field : 'pubtitle',
									title : '刊物名称',
									width : 150,
									sortable : true
								},
								{
									field : 'pubcode',
									title : '标准刊号',
									width : 150,
									sortable : true
								},
								{
									field : 'roll',
									title : '卷',
									width : 50,
									sortable : true
								},	
								{
									field : 'expect',
									title : '期',
									width : 50,
									sortable : true
								},	
								{
									field : 'begin2end',
									title : '起止页',
									width : 80,
									sortable : true
								},		
								{
									field : 'unit',
									title : '单位排名',
									width : 80,
									sortable : true
								},
								{
									field : 'commauthor',
									title : '通讯作者',
									width : 130,
									sortable : true
								},
								{
									field : 'commauthorcode',
									title : '通讯作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'commauthorscore',
									title : '通讯作者绩效',
									width : 80
								},
								{
									field : 'firstauthor',
									title : '第一作者',
									width : 130,
									sortable : true
								},
								{
									field : 'firstauthorcode',
									title : '第一作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'firstauthorscore',
									title : '第一作者绩效',
									width : 80
								},
								{
									field : 'secauthor',
									title : '第二作者',
									width : 130,
									sortable : true
								},
								{
									field : 'secauthorcode',
									title : '第二作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'secauthorscore',
									title : '第二作者绩效',
									width : 80
								},
								{
									field : 'threeauthor',
									title : '第三作者',
									width : 130,
									sortable : true
								},
								{
									field : 'threeauthorcode',
									title : '第三作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'threeauthorscore',
									title : '第三作者绩效',
									width : 80
								},
								{
									field : 'fourauthor',
									title : '第四作者',
									width : 130,
									sortable : true
								},
								{
									field : 'fourauthorcode',
									title : '第四作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'fourauthorscore',
									title : '第四作者绩效',
									width : 80
								},
								{
									field : 'fiveauthor',
									title : '第五作者',
									width : 130,
									sortable : true
								},
								{
									field : 'fiveauthorcode',
									title : '第五作者arp',
									width : 130,
									sortable : true
								},
								{
									field : 'fiveauthorscore',
									title : '第五作者绩效',
									width : 80
								},
								{
									field : 'otherauthor',
									title : '其他作者',
									width : 150,
									sortable : true
								},	
								{
									field : 'otherauthorcode',
									title : '其他作者arp',
									width : 150,
									sortable : true
								},	
								{
									field : 'status',
									title : '论文状态',
									width : 130,
									sortable : true
								},
								{
									field : 'doi',
									title : 'doi号',
									width : 150,
									sortable : true
								},
								{
									field : 'interpartner',
									title : '是否国际合作',
									width : 150,
									sortable : true
								},
								{
									field : 'submitUser',
									title : '提交人',
									width : 170,
									sortable : true
								},
								{
									field : 'submitTime',
									title : '提交时间',
									width : 170,
									sortable : true
								},
								{
									field : 'append',
									title : '附件',
									width : 150,
									sortable : true,
									formatter : function(value, rowData,
											rowIndex) {
										// function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
										if (value) {
											var downloadurl = $('#contextPath').val() + "/download.action" + "?" + "fileName=" + encodeURIComponent(encodeURIComponent(value));
											return "<a href='" + downloadurl
													+ "' >" + value
													+ "</a>";
										}
									}
								} ] ],
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
								$('#import').hide();
								$('#audit').hide();
							} else if (rolename == "秘书") {
								$('#insert').hide();
								$('#del').hide();
								$('#sub').hide();
								//$('#update').hide();
								$('#revoke').hide();
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
						     var fieldArray = ["firstauthorscore", "secauthorscore", "threeauthorscore", "fourauthorscore", "fiveauthorscore", "commauthorscore"]
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
	
	if($('#urole').val() !== '管理员'){
		$('#center_table').datagrid('hideColumn','auditUser');
	}

	if($('#urole').val() === '科研秘书'){
		$('#center_table').datagrid('hideColumn','commauhtorscore');
		$('#center_table').datagrid('hideColumn','firstauhtorscore');
		$('#center_table').datagrid('hideColumn','secauhtorscore');
		$('#center_table').datagrid('hideColumn','threeauhtorscore');
		$('#center_table').datagrid('hideColumn','fourauhtorscore');
		$('#center_table').datagrid('hideColumn','fiveauhtorscore');
	}
	
	$('#audit_filter').combobox({
		onSelect : function(record) {
			var item = record.value;
			$('#center_table').datagrid({
				onBeforeLoad : function(param) {
					param.auditfilter = item;
				}
			});
		}
	});

	var userRole = $('#urole').val();
	if(userRole === '管理员'){
		var calcBtn =document.getElementById("btnAllLwExport");
		calcBtn.style.visibility="visible";
	} else {
		var calcBtn =document.getElementById("btnAllLwExport");
		calcBtn.style.visibility="hidden";
	}

	// 上传
	applyExcelUpload('#lw_import', "/thesisAction!upLoadExcel.action", null,'#center_table');

	//编辑刊物类别提前绑定
	getPubKindList('#e_pubkind', function() {
		var pubKind = $('#e_pubkind').combobox('getText');
		var pubKindarr = [ "国内刊物", "SCI收录", "SSCI收录" ];
		
		if ($.inArray(pubKind, pubKindarr) !== -1) {
			$('#e_pubtitle').textbox("readonly", true).textbox('clear');
			$('#e_pubcode').textbox("readonly", true).textbox('clear');
			$('#e_subclass').textbox("readonly", true).textbox('clear');
			$('#e_factor').textbox("readonly", true).textbox('clear');
			$('#e_pubranking').textbox("readonly", true).textbox('clear');
			$('#e_topcomm').textbox("readonly", true).textbox('clear');
		} else {
			// 若不在以上三个期刊类别中，则需自己填写
			$('#e_pubtitle').textbox("readonly", false).textbox('clear');
			$('#e_pubcode').textbox("readonly", false).textbox('clear');
			$('#e_subclass').textbox("readonly", false).textbox('clear');
			$('#e_factor').textbox("readonly", false).textbox('clear');
			$('#e_pubranking').textbox("readonly", false).textbox('clear');
			$('#e_topcomm').textbox("readonly", false).textbox('clear');
		}
	});

	// 编辑 论文状态下拉列表
	searchlwstatus('#e_lw_status');
	
	$('#admin_thesis_addDialog').dialog({
		onClose:  function(){
			//附件清空
			clearUpload('lw_upload');
		}
	});
	
	$('#admin_thesis_editDialog').dialog({
		onClose:  function(){
			//附件清空
			$('#lw_edit_append').val('');
			clearUpload('e_lw_upload');
		}
	});
};

/**
 * ***************************************** Thesis Operator
 * *****************************************
 */
// 论文添加
var append = function() {
	// 动态加载论文状态等下拉框
	searchlwstatus('#lw_status');

	// 加载刊物类别列表
	getPubKindList('#pubkind', function() {
		var pubKind = $('#pubkind').combobox('getText');
		var pubKindarr = [ "国内刊物", "SCI收录", "SSCI收录" ];
		if ($.inArray(pubKind, pubKindarr) !== -1) {
			$('#pubtitle').textbox("readonly", true).textbox('clear');
			$('#pubcode').textbox("readonly", true).textbox('clear');
			$('#subclass').textbox("readonly", true).textbox('clear');
			$('#factor').textbox("readonly", true).textbox('clear');
			$('#pubranking').textbox("readonly", true).textbox('clear');
			$('#topcomm').textbox("readonly", true).textbox('clear');
		} else {
			// 若不在以上三个期刊类别中，则需自己填写
			$('#pubtitle').textbox("readonly", false).textbox('clear');
			$('#pubcode').textbox("readonly", false).textbox('clear');
			$('#subclass').textbox("readonly", false).textbox('clear');
			$('#factor').textbox("readonly", false).textbox('clear');
			$('#pubranking').textbox("readonly", false).textbox('clear');
			$('#topcomm').textbox("readonly", false).textbox('clear');
		}
	});

	// 处理eayui-textbox click绑定
	$('#pubtitle').textbox('textbox').bind('click', function() {
		searchObjTitle('#pubkind', '#pubtitle');
	});

	$('#commauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("commauthor");
	});

	$('#firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("firstauthor");
	});

	$('#secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("secauthor");
	});

	$('#threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("threeauthor");
	});

	$('#fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("fourauthor");
	});

	$('#fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("fiveauthor");
	});

	$('#otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("otherauthor");
	});

	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}

	yearData = yearData.reverse();
	$('#lw_year').combobox('loadData', yearData);

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
	$('#unit').combobox('loadData', unitData);
	
	openDialog('admin_thesis_addDialog');
	$('#admin_thesis_addForm').form('clear');
	applyAjaxFileUpload('#lw_upload', "/upload.action", "#lw_add_append");
	
};

// 确认添加
var addlw_btn_ok = function() {
	$('#admin_thesis_addForm').form('submit', {
		url : $('#contextPath').val() + '/thesisAction!add.action',
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
				
				$('#admin_thesis_addDialog').dialog('close');
			} else {
				if(obj.msg === "duplicate row")
					$.messager.alert("操作提示", "操作失败,论文已存在！", "error");
			}
		}
	});
};

// 取消添加
var addlw_btn_cancel = function() {
	$('#admin_thesis_addDialog').dialog('close');
};

// 论文删除操作
var removelwfunc = function() {
	// 获取要删除的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可删除的记录！'
		});
		return;
	}

	//防御，若为科研人员，则不能删除“审核”通过的记录
	var rolename = $('#urole').val();
	if (rolename == '科研人员') {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].examinestatus === '审核通过' ) {
				$.messager.alert("警告", "您不能删除审核通过的记录，请重新选择！");
				return;
			}else if(rows[i].examinestatus === '已提交'){
				$.messager.alert("警告", "您不能删除已提交的记录，请重新选择！");
				return;
			}
		}
	}

	$.messager.confirm("确认", "您确定要删除选择的论文记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.lwids = '';
			for (var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.lwids += rows[i].lwid;
					continue;
				}
				toRemove.lwids += (rows[i].lwid + ',');
			}
			var remove = JSON.stringify(toRemove);
			$.ajax({
				url : $('#contextPath').val() + '/thesisAction!remove.action',
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


// 论文属性编辑操作
var editfunc = function() {
	// 获取当前选中行的id
	var rows = $('#center_table').datagrid('getChecked');
	if (rows.length < 1) {
		$.messager.alert("提示", "请选中一行后编辑！");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		return;
	}
	
	// 防御，若为科研人员，则不能删除“审核”通过的记录
	var rolename = $('#urole').val();
//	if (rolename == "科研人员") {
//		if (rows[0].examinestatus === '审核通过') {
//			$.messager.alert("警告", "您不能编辑审核通过的记录，请重新选择！");
//			return;
//		} else if (rows[0].examinestatus === '已提交') {
//			$.messager.alert("警告", "您不能编辑已提交的记录，请重新选择！");
//			return;
//		}
//	}

	// 初始化combobox选项
	// searchlwstatus('#e_lw_status');

	// getPubKindList('#e_pubkind', function(){
	// var pubKind = $('#e_pubkind').combobox('getText');
	// var pubKindarr = [ "国内刊物", "SCI收录", "SSCI收录"];
	// if($.inArray(pubKind, pubKindarr) !== -1){
	// $('#e_pubtitle').textbox("readonly", true);
	// $('#e_pubcode').textbox("readonly", true);
	// $('#e_subclass').textbox("readonly", true);
	// $('#e_factor').textbox("readonly", true);
	// $('#e_pubranking').textbox("readonly", true);
	// }else{
	// //若不在以上三个期刊类别中，则需自己填写
	// $('#e_pubtitle').textbox("readonly", false);
	// $('#e_pubcode').textbox("readonly", false);
	// $('#e_subclass').textbox("readonly", false);
	// $('#e_factor').textbox("readonly", false);
	// $('#e_pubranking').textbox("readonly", false);
	// }
	// });

	// 处理eayui-textbox click绑定
	$('#e_pubtitle').textbox('textbox').bind('click', function() {
		searchObjTitle('#e_pubkind', '#e_pubtitle');
	});

	$('#e_commauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_commauthor");
	});

	$('#e_firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_firstauthor");
	});

	$('#e_secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_secauthor");
	});

	$('#e_threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_threeauthor");
	});

	$('#e_fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_fourauthor");
	});

	$('#e_fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("e_fiveauthor");
	});

	$('#e_otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("e_otherauthor");
	});

	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}

	yearData = yearData.reverse();
	$('#e_lw_year').combobox('loadData', yearData);
	$('#e_lw_year').combobox('select', yearData[0].id);

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
	$('#e_unit').combobox('loadData', unitData);

	// 回显
	$('#e_lwid').val(rows[0].lwid);
	$('#e_lw_title').textbox('setValue', rows[0].title);
	$('#e_lw_year').combobox('setValue', rows[0].year);
	$('#e_lw_status').combobox('setValue', rows[0].status);
	$('#e_lw_doi').textbox('setValue', rows[0].doi);
	$('#e_pubkind').combobox('setValue', rows[0].pubkind);
	$('#e_pubtitle').textbox('setValue', rows[0].pubtitle);
	$('#e_pubcode').textbox('setValue', rows[0].pubcode);
	$('#e_subclass').textbox('setValue', rows[0].subclass);
	$('#e_factor').textbox('setValue', rows[0].factor);
	$('#e_inter').combobox('setValue', rows[0].inter);
	$('#e_pubranking').textbox('setValue', rows[0].pubranking);
	$('#e_topcomm').textbox('setValue', rows[0].topcomm);
	$('#e_roll').textbox('setValue', rows[0].roll);
	$('#e_expect').textbox('setValue', rows[0].expect);
	$('#e_begin2end').textbox('setValue', rows[0].begin2end);
	$('#e_unit').combobox('setValue', rows[0].unit);
	$('#e_interpartner').combobox('setValue', rows[0].interpartner);
	$('#e_commauthor').textbox('setValue', rows[0].commauthor);
	$('#e_firstauthor').textbox('setValue', rows[0].firstauthor);
	$('#e_secauthor').textbox('setValue', rows[0].secauthor);
	$('#e_threeauthor').textbox('setValue', rows[0].threeauthor);
	$('#e_fourauthor').textbox('setValue', rows[0].fourauthor);
	$('#e_fiveauthor').textbox('setValue', rows[0].fiveauthor);
	$('#e_otherauthor').textbox('setValue', rows[0].otherauthor);
	$('#e_commauthorcode').val(rows[0].commauthorcode);
	$('#e_firstauthorcode').val(rows[0].firstauthorcode);
	$('#e_secauthorcode').val(rows[0].secauthorcode);
	$('#e_threeauthorcode').val(rows[0].threeauthorcode);
	$('#e_fourauthorcode').val(rows[0].fourauthorcode);
	$('#e_fiveauthorcode').val(rows[0].fiveauthorcode);
	$('#e_otherauthorcode').val(rows[0].otherauthorcode);

	if(rolename === "科研人员" && rows[0].examinestatus === "已退回"){
		$('#e_examinestatus').val("已保存");
	}else{
		$('#e_examinestatus').val(rows[0].examinestatus);
	}

	if(!rows[0].append){
		//当前记录不存在附件
		applyAjaxFileUpload('#e_lw_upload', "/upload.action", "#lw_edit_append");
	}else{
		$('#lw_edit_append').val(rows[0].append);
		editShowUpload('#e_lw_upload', '#lw_edit_append', rows[0].append);
	}
	
	EditValidate($('#admin_thesis_editForm'), rows[0].examinestatus);
	
	openDialog('admin_thesis_editDialog');
};

var editlw_btn_ok = function() {
	$('#admin_thesis_editForm').form('submit', {
		url : $('#contextPath').val() + '/thesisAction!edit.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {
				// 更新论文列表datagrid
				// $('#center_table').datagrid('updateRow',{
				// index:$('#center_table').datagrid('getRowIndex',rows[0].lwid),
				// row:objr.obj
				// });

				// 重新加载论文列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});
			
			$('#admin_thesis_editDialog').dialog('close');
		}
	});
};

var editlw_btn_cancel = function() {
	$('#admin_thesis_editDialog').dialog('close');
};

// 提交操作
function submitfunc() {
	// 获取要提交的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可提交的记录！'
		});
		return;
	}

	// 将需要提交的论文列表
	var toSubmit = {};
	toSubmit.submitUser =  $('#username').val();
	toSubmit.lwList = [];
	for (var i = 0; i < rows.length; i++) {
		// 防御，排除审核状态高于“审核通过”的记录
		if (rows[i].examinestatus === '审核通过' || rows[i].examinestatus === '已提交') {
			$.messager.alert('警告', '您不能重复提交！');
			return;
		}
//		else if(rows[i].examinestatus === '已退回'){
//			$.messager.alert('警告', '您不能提交已退回的记录，请重新修改后提交！');
//			return;
//		}
		toSubmit.lwList.push({
			lwid : rows[i].lwid
		});
	}
	
	var lwlist = JSON.stringify(toSubmit);
	$.ajax({
		url : $('#contextPath').val() + '/thesisAction!submit.action',
		data : 'lwlist=' + lwlist,
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
}

//撤销操作
var revokefunc = function(){
	// 获取要提交的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可撤销的记录！'
		});
		return;
	}
	

	$.messager.confirm("确认", "您确定要撤回选择的论文记录？", function(r) {
		if (r) {
			// 将需要撤销的论文列表
			var toRevoke = {};
			toRevoke.lwList = [];
			for (var i = 0; i < rows.length; i++) {
				// 防御，排除审核状态高于“审核通过”的记录
				if (rows[i].examinestatus !== '已提交') {
					$.messager.alert('警告', '您只能撤回已提交的记录，请仔细检查选择的论文记录！');
					return;
				}

				toRevoke.lwList.push({
					lwid : rows[i].lwid
				});
			}

			var lwlist = JSON.stringify(toRevoke);
			$.ajax({
				url : $('#contextPath').val() + '/thesisAction!revoke.action',
				data : 'lwlist=' + lwlist,
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '撤销出现错误！请联系管理员处理！'
					});
				},
				success : function(data) {
					json = $.parseJSON(data);
					if (json.success) {
						// 重新加载论文列表
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

// 审核操作，只有管理员具备审核权限
var auditfunc = function() {
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

	// 审核界面
	openDialog('audit_dialog');
};

// 审核确认
var auditlw_btn_ok = function() {
	var rows = $('#center_table').datagrid('getChecked');
	var auditValue = $("#audit_form input[type='radio'][name='audit']:checked").val(), 
		auditOpinion = $('#audit_opinion').textbox('getValue'),
		auditUser = $('#usercode').val();

	//将需要提交的论文列表
	var toSubmit = {};
	toSubmit.audit = auditValue;
	toSubmit.auditOpinion = auditOpinion;
	toSubmit.auditUser = auditUser;
	toSubmit.lwList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		toSubmit.lwList.push({
			lwid : rows[i].lwid
		});
	}

	var audit = JSON.stringify(toSubmit);

	$.ajax({
		url : $('#contextPath').val() + '/thesisAction!audit.action',
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

			$('#audit_dialog').dialog('close');

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

// 审核取消
var auditlw_btn_cancel = function() {
	$('#audit_dialog').dialog('close');
};

function lunwen() {
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/thesisAction!datagrid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'roleid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'rolename',
		sortOrder : 'asc',
		/* pagePosition : 'both', */
		checkOnSelect : false,
		selectOnCheck : false,
		queryParams : {
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

// 刊物名称
var searchObjTitle = function(pubkind, pubtitle) {
	var pubKindarr = [ "国内刊物", "SCI收录", "SSCI收录" ];
	var otherpubKindArr = ["ISTP收录", "EI收录", "其他国外刊物", "其他"];
	
	var pubKind = $(pubkind).combobox('getText');
	if ($.inArray(pubKind, pubKindarr) !== -1) {
		openDialog('admin_thesis_addDialog_title');
		if(pubtitle === '#pubtitle'){
			$("#admin_thesis_addDialog_title #s_status_marker").val("add");
			showbackPub(pubtitle, '#pubcode', '#pubkind');
		}else if(pubtitle === '#e_pubtitle'){
			$("#admin_thesis_addDialog_title #s_status_marker").val("edit");
			showbackPub(pubtitle, '#e_pubcode', '#e_pubkind');
		}
	} else {
		if($.inArray(pubKind, otherpubKindArr) === -1){
			$.messager.show({
				title : '提示',
				msg : '先选定期刊类型'
			});
		}
	}
};

//新增期刊
var pub_btn_add = function(){
	var pubKind = $('#pubkind').combobox('getText');
	//$('#add_pubkind').textbox('setValue', pubKind);
	$('#add_pubkind').val(pubKind);
	//$('#add_remark').textbox('setValue', '自新增期刊');
	$('#add_remark').val('自新增期刊');
	
	openDialog('admin_pubpart_addDialog');
};

var addpubpart_btn_ok = function(){
	var temp1=$('#pubkind').combobox('getText');
	var temp2=$('#pre_add_pubpart_issn').textbox('getValue');
	
	var temp=temp1+","+temp2;
	$.ajax( {    
	   url : $('#contextPath').val() +'/pubPartAction!pubPartCheck.action',// 跳转到 action    
	    type:'post', 
	    data : {
			ids : temp
		},
	    cache:false,    
	    dataType:'json',    
	    success:function(objr) {  	
			if (objr.success) {
				
				//alert('该用户为非法用户或该用户已经存在，不能重复添加！！');
				$.messager.show({
					title : '提示',
					msg : '该期刊非法或已经存在，不能重复添加！！'
				});
				$('#admin_pubpart_addDialog').dialog('close');
				
			}else{
				$('#admin_pubpark_addForm').form('submit', {
					url : $('#contextPath').val() + '/pubPartAction!add.action',
					onSubmit : function(param) {
						return $(this).form('validate');
					},
					success : function(r) {
						// 修改成功，解析返回的json信息
						var objr = jQuery.parseJSON(r);
						if (objr.success) {
							// 重新加载论文列表
							$('#center_table').datagrid('reload');
						}

						$.messager.show({
							title : '提示',
							msg : objr.msg
						});

						$('#admin_pubpart_addDialog').dialog('close');
					}
				});
				
			}   
	     },    
	     error : function() {  
	    	alert('期刊检查出错');  
	     }    
	});
};

var addpubpart_btn_cancel = function(){
	$('#admin_pubpart_addDialog').dialog('close');
};

//确认选择刊物
var pub_btn_ok = function() {
	var rows = $('#thesis_pubpart_datagrid').datagrid('getChecked');
	if (rows.length > 0) {
		if ($('#s_status_marker').val() === 'add') {
			$('#pubtitle').textbox('setValue', rows[0].title);
			$('#pubcode').textbox('setValue', rows[0].issn);
			$('#subclass').textbox('setValue', rows[0].zone);
			$('#factor').textbox('setValue', rows[0].factor);
			$('#inter').textbox('setValue', rows[0].inter);
			$('#pubranking').textbox('setValue', rows[0].pubranking);
		}else if($('#s_status_marker').val() === 'edit'){
			$('#e_pubtitle').textbox('setValue', rows[0].title);
			$('#e_pubcode').textbox('setValue', rows[0].issn);
			$('#e_subclass').textbox('setValue', rows[0].zone);
			$('#e_factor').textbox('setValue', rows[0].factor);
			$('#e_inter').textbox('setValue', rows[0].inter);
			$('#e_pubranking').textbox('setValue', rows[0].pubranking);
		}
		
		$('#admin_thesis_addDialog_title').dialog('close');
	} else {
		if ($('#s_status_marker').val() === 'add') {
			$('#pubtitle').textbox('setValue', '');
			$('#pubcode').textbox('setValue', '');
			$('#subclass').textbox('setValue', '');
			$('#factor').textbox('setValue', '');
			$('#inter').textbox('setValue', '');
			$('#pubranking').textbox('setValue', '');
		}else if($('#s_status_marker').val() === 'edit'){
			$('#e_pubtitle').textbox('setValue', '');
			$('#e_pubcode').textbox('setValue', '');
			$('#e_subclass').textbox('setValue', '');
			$('#e_factor').textbox('setValue', '');
			$('#e_inter').textbox('setValue', '');
			$('#e_pubranking').textbox('setValue', '');
		}

		$('#admin_thesis_addDialog_title').dialog('close');
	}
};

// 取消选择刊物
var pub_btn_cancel = function() {
	$('#admin_thesis_addDialog_title').dialog('close');
};

//回显期刊
var showbackPub = function(pubtitle, issn, pubkind){
	$('#thesis_pubpart_datagrid').datagrid({
		url : $('#contextPath').val() + '/pubPartAction!getPubListByKind.action',
		fit : false,
		queryParams : {
			title : $(pubtitle).textbox('getValue'),
			issn : $(issn).textbox('getValue'),
			pubkind : $(pubkind).combobox('getValue')
		},
		fitColumns : true,
		border : true,
		pagination : true,
		pagePosition : 'bottom',
		idField : 'tpid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'title',
		sortOrder : 'asc',
		singleSelect : false,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [ [ {
			field : 'tpid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'title',
			title : '期刊名称',
			width : 150,
			sortable : true
		}, {
			field : 'issn',
			title : 'issn',
			width : 150
		},{
			field : 'pubkind',
			title : '期刊类别',
			width :  150
		}
		] ],
		width : 500,
		height : 310,
		autoRowHeight : false,
		onLoadSuccess : function(data){
			var row;
			for(var i=0; i < data.rows.length; i++){
				row = data.rows[i];
				if(row.title === $(pubtitle).textbox('getValue') && row.issn === $(issn).textbox('getValue')
						&& row.pubkind === $(pubkind).combobox('getValue')){
					$(this).datagrid('checkRow', i);
					return;
				}
			}
		}
	});
};

//读取期刊列表
function searchTitleFunc() {
	var pubkind = '';
	if($('#s_status_marker').val() === 'add'){
		pubkind = $('#pubkind').combobox('getValue');
	}else if($('#s_status_marker').val() === 'edit'){
		pubkind = $('#e_pubkind').combobox('getValue');
	}
	
	$('#thesis_pubpart_datagrid').datagrid({
		url : $('#contextPath').val() + '/pubPartAction!getPubListByKind.action',
		fit : false,
		queryParams : {
			pubkind : pubkind,
			title : $('#s_title').val(),
			issn : $('#s_issn').val()
		},
		fitColumns : true,
		border : true,
		pagination : true,
		pagePosition : 'bottom',
		idField : 'tpid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'title',
		sortOrder : 'asc',
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		columns : [ [ {
			field : 'tpid',
			title : '编号',
			width : 150,
			checkbox : true
		}, {
			field : 'title',
			title : '期刊名称',
			width : 150,
			sortable : true
		}, {
			field : 'issn',
			title : 'issn',
			width : 150
		},{
			field : 'pubkind',
			title : '期刊类别',
			width :  150
		}
		] ],
		width : 500,
		height : 310,
		autoRowHeight : false
	});
}

// lwstatus
function searchlwstatus(LwStatus) {
	var url = $('#contextPath').val() + "/lwStutasAction!getLwStatusList.action";
	$.getJSON(url, function(json) {
		$(LwStatus).combobox({
			data : json.rows,
			valueField : 'name',
			textField : 'name',
			onSelect : function() {
//				var lwstatus = $(LwStatus).combobox('getText');
//				if (lwstatus === "已接收有doi") {
//					$('#doi').textbox('readonly', false);
//					$('#e_lw_doi').textbox('readonly', false);
//				} else if (lwstatus === "正式发表" || lwstatus === "已接收无doi") {
//					$('#doi').textbox('setText', '');
//					$('#doi').textbox('readonly', true);
//					$('#e_lw_doi').textbox('setText', '');
//					$('#e_lw_doi').textbox('readonly', true);
//				}
			}
		});
	});
}

// get pubkind list
var getPubKindList = function(pubkind, callback) {
	var url = $('#contextPath').val() + "/pubKindAction!datagrid.action";
	$.getJSON(url, function(json) {
		$(pubkind).combobox({
			data : json.rows,
			valueField : 'pubkind',
			textField : 'pubkind',
			onChange : callback
		});
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

	$('#s_begin_year').combobox('loadData', yearData);
	$('#s_begin_year').combobox('select', yearData[0].id);
	$('#s_begin_year').combobox('setValue', '');
	var newyearData = yearData.slice(0);
	newyearData = newyearData.reverse();
	$('#s_end_year').combobox('loadData', newyearData);
	$('#s_end_year').combobox('select', newyearData[0].id);
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

// 高级查询确定
var adv_search_btn_ok = function() {
	// 获取过滤条件
	var begin_year = $('#s_begin_year').combobox('getValue');
	var end_year = $('#s_end_year').combobox('getValue');

	var author = $('#s_lw_author').val();
	var authorcode = $('#s_lw_authorcode').val();
	var name = $('#s_lw_name').val();
	var pubname = $('#s_lw_pubname').val();

	// 实验室、领域
//	var domain = $('#s_which_domain').combobox('getText');
//	var lab = $('#s_which_lab').combobox('getText');

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.s_begin_year = begin_year;
			param.s_end_year = end_year;
			param.s_lw_author = author;
			param.s_lw_authorcode = authorcode;
			param.s_lw_name = name;
			param.s_lw_pubname = pubname;
			//param.s_which_domain = domain;
			//param.s_which_lab = lab;
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
var importfunc = function() {
	openDialog('import_lw');
};

// 论文导出
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('thesisAction!downloadExcel.action');
};

var exportlw_btn_ok = function() {
	$('#admin_thesis_exportForm').form(
			'submit',
			{
				url : $('#contextPath').val() + '/thesisAction!edit.action',
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
											'getRowIndex', rows[0].lwid),
									row : objr.obj
								});

						// 重新加载论文列表
						// $('#center_table').datagrid('reload');
					}

					$.messager.show({
						title : '提示',
						msg : objr.msg
					});

					d.dialog('close');
				}
			});
};

var exportlw_btn_cancel = function() {
	$('#admin_thesis_exportForm').dialog('close');
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

//导出包含分数的表
//导出Excel
function exportAllExcel() {
	var msg = "您确定要导出包含绩效分数的全部论文吗？此方式速度较慢，且为全部论文。\n\n请确认！";
	if (confirm(msg)==true) {
		downloadExcel('thesisAction!downloadExcelIncludingScore.action');
	}
}
