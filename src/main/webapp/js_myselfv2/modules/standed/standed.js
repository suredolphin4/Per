/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {

	$('#center_table').datagrid(
			{
				url : $('#contextPath').val()
						+ '/standedAction!datagrid.action',
				fit : true,
				fitColumns : false,
				border : false,
				pagination : true,
				idField : 'bzid',
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
							field : 'bzid',
							title : '编号',
							width : 100,
							checkbox : true
						},
						{
							field : 'year',
							title : '年度',
							width : 50,
							sortable : true
						},
						{
							field : 'title',
							title : '标准名称',
							width : 250,
							sortable : true
						},
						{
							field : 'markid',
							title : '标准编号',
							width : 180,
							resizable : true,
							sortable : true
						},
						{
							field : 'auditdepart',
							title : '颁布单位',
							width : 180,
							sortable : true
						},
						{
							field : 'auditlevel',
							title : '标准类别',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'auditdate',
							title : '批准日期',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'departorder',
							title : '单位排序',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'examinestatus',
							title : '审核状态',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'auditopinion',
							title : '审核意见',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'firstauthor',
							title : '完成人1',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'firstauthorcode',
							title : '完成人1arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'firstauthorscore',
							title : 'arp1绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'secauthor',
							title : '完成人2',
							width : 100,
							sortable : true
						},
						{
							field : 'secauthorcode',
							title : '完成人2arp',
							width : 100,
							sortable : true
						},
						{
							field : 'secauthorscore',
							title : 'arp2绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'threeauthor',
							title : '完成人3',
							width : 100,
							sortable : true
						},
						{
							field : 'threeauthorcode',
							title : '完成人3arp',
							width : 100,
							sortable : true
						},
						{
							field : 'threeauthorscore',
							title : 'arp3绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'fourauthor',
							title : '完成人4',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fourauthorcode',
							title : '完成人4arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fourauthorscore',
							title : 'arp4绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'fiveauthor',
							title : '完成人5',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fiveauthorcode',
							title : '完成人5arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fiveauthorscore',
							title : 'arp5绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'otherauthor',
							title : '其他完成人',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'otherauthorcode',
							title : '其他完成人arp',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'otherauthorscore',
							title : '其他arp绩效',
							width : 150,
							resizable : true
						},
						{
							field : 'submitUser',
							title : '提交人',
							width : 150,
							resizable : true,
							sortable : true
						},	
						{
							field : 'submitTime',
							title : '提交时间',
							width : 150,
							resizable : true,
							sortable : true
						},					
						{
							field : 'append',
							title : '附件',
							width : 150,
							resizable : true,
							sortable : true,
							formatter : function(value, rowData, rowIndex) {
								// function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
								if (value) {
									var downloadurl = $('#contextPath').val()
											+ "/download.action" + "?"
											+ "fileName=" + encodeURIComponent(encodeURIComponent(value));
									return "<a href='" + downloadurl + "' >"
											+ value + "</a>";
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
//						$('#export').hide();
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

	$('#bz_auditdate').datebox({	    
	    editable:false
	});
	$('#e_bz_auditdate').datebox({	    
	    editable:false
	});
	
	$('#admin_standed_addDialog').dialog({
		onClose:  function(){
			clearUpload('bz_upload');
		}
	});
	$('#admin_standed_editDialog').dialog({
		onClose:  function(){
			//附件清空
			$('#bz_edit_append').val('');
			clearUpload('e_bz_upload');
		}
	});

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

	// 上传
	applyExcelUpload('#bz_import', "/standedAction!upLoadExcel.action",null,'#center_table');

};

/**
 * ***************************************** Thesis Operator
 * *****************************************
 */
// 标准添加
var append = function() {

	$('#bz_firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("bz_firstauthor");
	});

	$('#bz_secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("bz_secauthor");
	});

	$('#bz_threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("bz_threeauthor");
	});

	$('#bz_fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("bz_fourauthor");
	});

	$('#bz_fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("bz_fiveauthor");
	});

	$('#bz_otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("bz_otherauthor");
	});

	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for ( var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}

	yearData = yearData.reverse();
	$('#year').combobox('loadData', yearData);

	// unitrank
	var unitData = [];
	for ( var i = 0; i < 9; i++) {
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
	$('#departorder').combobox('loadData', unitData);

	$('#admin_standed_addDialog').dialog('open');
	$('#admin_standed_addForm').form('clear');
	applyAjaxFileUpload('#bz_upload', "/upload.action","#bz_add_append");
};

// 确认添加
var addbz_btn_ok = function() {
	$('#admin_standed_addForm').form('submit', {
		url : $('#contextPath').val() + '/standedAction!add.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
				$('#center_table').datagrid('insertRow', {
					index : 0,
					row : obj.obj
				});

				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
//				clearUpload('bz_upload');
				$('#admin_standed_addDialog').dialog('close');
			} else {
				$.messager.alert("操作提示", "操作失败,该标准记录已存在！", "error");
			}
		}
	});
};

// 取消添加
var addbz_btn_cancel = function() {
//	clearUpload('bz_upload');
	$('#admin_standed_addDialog').dialog('close');
};

// 标准删除操作
var removebzfunc = function() {
	// 获取要删除的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可删除的记录！'
		});
		return;
	}

	// 防御，若为科研人员，则不能删除“审核”通过与“已提交”的记录
	var rolename = $('#urole').val();
	if (rolename == "科研人员") {
		for ( var i = 0; i < rows.length; i++) {
//			if (rows[i].examinestatus === '审核通过') {
			if (rows[i].examinestatus === '审核通过' || rows[i].examinestatus === '已提交') {
				$.messager.alert("警告", "您不能删除审核通过或已提交的记录，请重新选择！");
				return;
			}
		}
	}

	$.messager.confirm("确认", "您确定要删除选择的标准记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.bzids = '';
			for ( var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.bzids += rows[i].bzid;
					continue;
				}
				toRemove.bzids += (rows[i].bzid + ',');
			}
			var remove = JSON.stringify(toRemove);
			$.ajax({
				url : $('#contextPath').val() + '/standedAction!remove.action',
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
	toSubmit.submitUser = $('#username').val();
	toSubmit.bzList = [];
	var i;
	for (i = 0; i < rows.length; i++) {	
		// 防御，排除审核状态高于“审核通过”的记录
		if (rows[i].examinestatus === '审核通过' || rows[i].examinestatus === '已提交') {
			$.messager.alert('警告', '您不能重复提交！');
			return;
		}
		toSubmit.bzList.push({
			bzid : rows[i].bzid
		});
	}

	var bzlist = JSON.stringify(toSubmit);
	$.ajax({
		url : $('#contextPath').val() + '/standedAction!submit.action',
		data : 'bzlist=' + bzlist,
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

//撤回已提交标准记录
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
	

	$.messager.confirm("确认", "您确定要撤回选择的标准记录？", function(r) {
		if (r) {
			// 将需要撤销的论文列表
			var toRevoke = {};
			toRevoke.standardList = [];
			for (var i = 0; i < rows.length; i++) {
				// 防御，排除审核状态高于“审核通过”的记录
				if (rows[i].examinestatus !== '已提交') {
					$.messager.alert('警告', '您只能撤回已提交的记录，请仔细检查选择的标准记录！');
					return;
				}

				toRevoke.standardList.push({
					bzid : rows[i].bzid
				});
			}

			var standardList = JSON.stringify(toRevoke);
			$.ajax({
				url : $('#contextPath').val() + '/standedAction!revoke.action',
				data : 'standardlist=' + standardList,
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '撤销出现错误！请联系管理员处理！'
					});
				},
				success : function(data) {
					json = $.parseJSON(data);
					if (json.success) {
						// 重新加载标准列表
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
	$('#bz_audit_opinion').textbox('setValue',"");
	$('#audit_bz_dialog').dialog('open');
};

// 审核确认
var auditbz_btn_ok = function() {
	var rows = $('#center_table').datagrid('getChecked');
	var auditValue = $(
			"#audit_bz_form input[type='radio'][name='audit']:checked").val(), auditOpinion = $(
			'#bz_audit_opinion').textbox('getValue');

	// 将需要提交的论文列表
	var toSubmit = {};
	toSubmit.audit = auditValue;
	toSubmit.auditOpinion = auditOpinion;
	toSubmit.bzList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		toSubmit.bzList.push({
			bzid : rows[i].bzid
		});
	}

	var audit = JSON.stringify(toSubmit);

	$.ajax({
		url : $('#contextPath').val() + '/standedAction!audit.action',
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

			$('#audit_bz_dialog').dialog('close');

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

// 审核取消
var auditbz_btn_cancel = function() {
	$('#audit_bz_dialog').dialog('close');
};

// 标准属性编辑操作
var editbzfunc = function() {
	// 获取当前选中行的id
	var rows = $('#center_table').datagrid('getChecked');
	
	if (rows.length < 1) {
		$.messager.alert("提示", "请选中一行后编辑！");
	}
	
	if (rows.length > 1) {
		$.messager.alert("提示", "只能编辑一行记录，请重新选择！");
	}

	// 只能编辑一行
	if (rows.length == 1) {
		// 防御，若为科研人员，则不能删除“审核”通过的记录
		var rolename = $('#urole').val();
		
//		if (rolename == "科研人员") {
//			if (rows[0].examinestatus === '审核通过') {
//				$.messager.alert("警告", "您不能编辑审核通过的记录，请重新选择！");
//				return;
//			} else if (rows[0].examinestatus === '已提交') {
//				$.messager.alert("警告", "您不能编辑已提交的记录，请重新选择！");
//				return;
//			}
//		}

		// $('#admin_standed_editForm input').val();

		// 处理eayui-textbox click绑定
		$('#e_bz_firstauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_bz_firstauthor");
		});

		$('#e_bz_secauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_bz_secauthor");
		});

		$('#e_bz_threeauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_bz_threeauthor");
		});

		$('#e_bz_fourauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_bz_fourauthor");
		});

		$('#e_bz_fiveauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_bz_fiveauthor");
		});

		$('#e_bz_otherauthor').textbox('textbox').bind('click', function() {
			searchOtherAuthor("e_bz_otherauthor");
		});

		// year
		var currYear = new Date().getFullYear(), yearData = [];
		for ( var i = 1990; i <= currYear+1; i++) {
			yearData[i - 1990] = {};
			yearData[i - 1990].id = i;
			yearData[i - 1990].text = i;
		}

		yearData = yearData.reverse();
		$('#e_bz_year').combobox('loadData', yearData);
		$('#e_bz_year').combobox('select', yearData[0].id);

		// unitrank
		var unitData = [];
		for ( var i = 0; i < 9; i++) {
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
		$('#e_bz_departorder').combobox('loadData', unitData);

		// // 获取当前选中行的id
		// var rows = $('#center_table').datagrid('getChecked');
		// // 只能编辑一行
		// if (rows.length == 1) {
		// 回显
		$('#e_bz_bzid').val(rows[0].bzid);
		$('#e_bz_title').textbox('setValue', rows[0].title);
		$('#e_bz_year').combobox('setValue', rows[0].year);
		$('#e_bz_markid').textbox('setValue', rows[0].markid);
		$('#e_bz_auditdepart').textbox('setValue', rows[0].auditdepart);
		$('#e_bz_auditlevel').combobox('setValue', rows[0].auditlevel);
		$('#e_bz_auditdate').textbox('setValue', rows[0].auditdate);
		$('#e_bz_departorder').combobox('setValue', rows[0].departorder);
		$('#e_bz_firstauthor').textbox('setValue', rows[0].firstauthor);
		$('#e_bz_secauthor').textbox('setValue', rows[0].secauthor);
		$('#e_bz_threeauthor').textbox('setValue', rows[0].threeauthor);
		$('#e_bz_fourauthor').textbox('setValue', rows[0].fourauthor);
		$('#e_bz_fiveauthor').textbox('setValue', rows[0].fiveauthor);
		$('#e_bz_otherauthor').textbox('setValue', rows[0].otherauthor);
		
		$('#e_bz_firstauthorcode').val(rows[0].firstauthorcode);
		$('#e_bz_secauthorcode').val(rows[0].secauthorcode);
		$('#e_bz_threeauthorcode').val(rows[0].threeauthorcode);
		$('#e_bz_fourauthorcode').val(rows[0].fourauthorcode);
		$('#e_bz_fiveauthorcode').val(rows[0].fiveauthorcode);
		$('#e_bz_otherauthorcode').val(rows[0].otherauthorcode);
//		$('#e_bz_examinestatus').val(rows[0].examinestatus);
		
		if(rolename === "科研人员" && rows[0].examinestatus === "已退回"){
			$('#e_bz_examinestatus').val("已保存");
		}else{
			$('#e_bz_examinestatus').val(rows[0].examinestatus);
		}
		
		if(!rows[0].append){
			//当前记录不存在附件
			applyAjaxFileUpload('#e_bz_upload', "/upload.action", "#bz_edit_append");
		}else{
			$('#bz_edit_append').val(rows[0].append);
			editShowUpload('#e_bz_upload', '#bz_edit_append', rows[0].append);
		}
		
		EditValidate($('#admin_standed_editForm'), rows[0].examinestatus);
		
		$('#admin_standed_editDialog').dialog('open');
	}
};

var editbz_btn_ok = function() {
	$('#admin_standed_editForm').form('submit', {
		url : $('#contextPath').val() + '/standedAction!edit.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			// 修改成功，解析返回的json信息
			var objr = jQuery.parseJSON(r);
			if (objr.success) {

				// 重新加载论文列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}

			$.messager.show({
				title : '提示',
				msg : objr.msg
			});
//			clearUpload('e_bz_upload');
			$('#admin_standed_editDialog').dialog('close');
		}
	});
};

var editbz_btn_cancel = function() {
//	clearUpload('e_bz_upload');
	$('#admin_standed_editDialog').dialog('close');
};

// 论文导入
var importfunc = function() {
	$('#import_bz').dialog("open");
};

// 论文导出
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('standedAction!downloadExcel.action');
};

var exportbz_btn_ok = function() {
	$('#admin_standed_exportForm').form(
			'submit',
			{
				url : $('#contextPath').val() + '/standedAction!edit.action',
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

var exportbz_btn_cancel = function() {
	$('#admin_standed_exportForm').dialog('close');
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
	
	var top = $('#bz_statusfilter').offset().top + 30;
	var left = $('#bz_statusfilter').offset().left;
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

//	// 实验室、领域
//	var domain = $('#s_zx_which_domain').combobox('getText');
//	var lab = $('#s_zx_which_lab').combobox('getText');

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.s_begin_year = begin_year;
			param.s_end_year = end_year;
			param.s_lw_author = author;
			param.s_lw_name = name;
			param.s_lw_authorcode = authorcode;
//			param.s_which_domain = domain;
//			param.s_which_lab = lab;
		}
	});

	// 最后关闭
	$('#advancedSearch_dialog').dialog('close');
};

// 高级查询取消
var adv_search_btn_cancel = function() {
	$('#advancedSearch_dialog').dialog('close');
};
