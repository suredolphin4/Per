/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {
	$('#center_table').datagrid(
			{
				url : $('#contextPath').val() + '/publishAction!datagrid.action',
				fit : true,
				fitColumns : false,
				nowrap : true,
				border : false,
				pagination : true,
				idField : 'publishid',
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
							field : 'publishid',
							title : '编号',
							width : 150,
							checkbox : true,
							sortable : true
						},
						{
							field : 'year',
							title : '年度',
							width : 50,
							sortable : true
						},
						{
							field : 'title',
							title : '数据出版名称',
							width : 250,
							resizable : true,
							sortable : true
						},
						{
							field : 'isbn',
							title : 'doi号',
							width : 80,
							resizable : true,
							sortable : true
						},
						{
							field : 'publishtype',
							title : '出版社',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'examinestatus',
							title : '审核状态',
							width : 100,
							sortable : true
						},
						{
							field : 'auditopinion',
							title : '审核意见',
							width : 100,
							sortable : true
						},
						{
							field : 'firsteditor',
							title : '主编1',
							width : 100,
							sortable : true
						},
						{
							field : 'firsteditorcode',
							title : '主编1arp',
							width : 100,
							sortable : true
						},
						{
							field : 'firsteditorscore',
							title : '主编1绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'seceditor',
							title : '主编2',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'seceditorcode',
							title : '主编2arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'seceditorscore',
							title : '主编2绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'thirdeditor',
							title : '主编3',
							width : 100,
							sortable : true
						},
						{
							field : 'thirdeditorcode',
							title : '主编3arp',
							width : 100,
							sortable : true
						},
						{
							field : 'threeeditorscore',
							title : '主编3绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'othereditor',
							title : '其他主编',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'othereditorcode',
							title : '其他主编arp',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'othereditorscore',
							title : '其他主编绩效',
							width : 150,
							resizable : true
						},
						{
							field : 'firstsubeditor',
							title : '副主编1',
							width : 100,
							sortable : true
						},
						{
							field : 'firstsubeditorcode',
							title : '副主编1arp',
							width : 100,
							sortable : true
						},
						{
							field : 'firstsubeditorscore',
							title : '副主编1绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'secsubeditor',
							title : '副主编2',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'secsubeditorcode',
							title : '副主编2arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'secsubeditorscore',
							title : '副主编2绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'thirdsubeditor',
							title : '副主编3',
							width : 100,
							sortable : true
						},
						{
							field : 'thirdsubeditorcode',
							title : '副主编3arp',
							width : 100,
							sortable : true
						},
						{
							field : 'threesubeditorscore',
							title : '副主编3绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'othersubeditor',
							title : '其他副主编',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'othersubeditorcode',
							title : '其他副主编arp',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'othersubeditorscore',
							title : '其他副主编绩效',
							width : 150,
							resizable : true
						},
						{
							field : 'firstauthor',
							title : '编辑1',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'firstauthorcode',
							title : '编辑1arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'firstauthorscore',
							title : '编辑1绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'secauthor',
							title : '编辑2',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'secauthorcode',
							title : '编辑2arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'secauthorscore',
							title : '编辑2绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'threeauthor',
							title : '编辑3',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'threeauthorcode',
							title : '编辑3arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'threeauthorscore',
							title : '编辑3绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'fourauthor',
							title : '编辑4',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fourauthorcode',
							title : '编辑4arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fourauthorscore',
							title : '编辑4绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'fiveauthor',
							title : '编辑5',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fiveauthorcode',
							title : '编辑5arp',
							width : 100,
							resizable : true,
							sortable : true
						},
						{
							field : 'fiveauthorscore',
							title : '编辑5绩效',
							width : 80,
							resizable : true
						},
						{
							field : 'otherauthor',
							title : '其他编辑',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'otherauthorcode',
							title : '其他编辑arp',
							width : 150,
							resizable : true,
							sortable : true
						},
						{
							field : 'otherauthorscore',
							title : '其他编辑绩效',
							width : 150,
							resizable : true
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
				     var fieldArray = ["firsteditorscore", "seceditorscore", "threeeditorscore","othereditorscore","firstsubeditorscore", "secsubeditorscore", "threesubeditorscore","othersubeditorscore","firstauthorscore", "secauthorscore", "threeauthorscore", "fourauthorscore", "fiveauthorscore", "otherauthorscore"];
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
	
	$('#admin_publish_addDialog').dialog({
		onClose:  function(){
			clearUpload('publish_upload');
		}
	});
	
	$('#admin_publish_editDialog').dialog({
		onClose:  function(){
			$('#publish_edit_append').val('');
			clearUpload('e_publish_upload');
		}
	});
	
	// 上传
	applyExcelUpload('#publish_import', "/publishAction!upLoadExcel.action", null,'#center_table');

};

/**
 * ***************************************** Thesis Operator
 * *****************************************
 */
// 添加
var append = function() {

	// 处理eayui-textbox click绑定
	$('#publish_firsteditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_firsteditor");
	});

	$('#publish_seceditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_seceditor");
	});

	$('#publish_thirdeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_thirdeditor");
	});
	$('#publish_othereditor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("publish_othereditor");
	});
	$('#publish_firstsubeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_firstsubeditor");
	});

	$('#publish_secsubeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_secsubeditor");
	});

	$('#publish_thirdsubeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_thirdsubeditor");
	});
	$('#publish_othersubeditor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("publish_othersubeditor");
	});

	$('#publish_firstauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_firstauthor");
	});

	$('#publish_secauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_secauthor");
	});

	$('#publish_threeauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_threeauthor");
	});

	$('#publish_fourauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_fourauthor");
	});

	$('#publish_fiveauthor').textbox('textbox').bind('click', function() {
		searchObjAuthor("publish_fiveauthor");
	});

	$('#publish_otherauthor').textbox('textbox').bind('click', function() {
		searchOtherAuthor("publish_otherauthor");
	});

	// year
	var currYear = new Date().getFullYear(), yearData = [];
	for ( var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}

	yearData = yearData.reverse();
	$('#publish_year').combobox('loadData', yearData);

	$('#admin_publish_addDialog').dialog('open');
	$('#admin_publish_addForm').form('clear');
	applyAjaxFileUpload('#publish_upload', "/upload.action", "#publish_add_append");
};

// 确认添加
var addpublish_btn_ok = function() {
	$('#admin_publish_addForm').form('submit', {
		url : $('#contextPath').val() + '/publishAction!add.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function(r) {
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
				// $('#center_table').datagrid('insertRow',{
				// index:0,
				// row:obj.obj
				// });

				$('#center_table').datagrid('reload');

				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
//				clearUpload('publish_upload');
				$('#admin_publish_addDialog').dialog('close');
			} else {
				$.messager.alert("操作提示", "操作失败,该数据出版记录已存在！", "error");
			}
		}
	});
};

// 取消添加
var addpublish_btn_cancel = function() {
//	clearUpload('dt_upload');
	$('#admin_publish_addDialog').dialog('close');
};

// 论文删除操作
var removepublishfunc = function() {
	// 获取要删除的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可删除的记录！'
		});
		return;
	}

	// 防御，若为科研人员，则不能删除“审核”通过或“已提交”的记录
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

	$.messager.confirm("确认", "您确定要删除选择的数据出版记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.publishids = '';
			for ( var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.publishids += rows[i].publishid;
					continue;
				}
				toRemove.publishids += (rows[i].publishid + ',');
			}
			var remove = JSON.stringify(toRemove);
			$.ajax({
				url : $('#contextPath').val() + '/publishAction!remove.action',
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

// 属性编辑操作
var editpublishfunc = function() {
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

		// 处理eayui-textbox click绑定
		$('#e_publish_firsteditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_firsteditor");
		});

		$('#e_publish_seceditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_seceditor");
		});

		$('#e_publish_thirdeditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_thirdeditor");
		});
		$('#e_publish_othereditor').textbox('textbox').bind('click', function() {
			searchOtherAuthor("e_publish_othereditor");
		});
		$('#e_publish_firstsubeditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_firstsubeditor");
		});

		$('#e_publish_secsubeditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_secsubeditor");
		});

		$('#e_publish_thirdsubeditor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_thirdsubeditor");
		});
		$('#e_publish_othersubeditor').textbox('textbox').bind('click', function() {
			searchOtherAuthor("e_publish_othersubeditor");
		});

		$('#e_publish_firstauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_firstauthor");
		});

		$('#e_publish_secauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_secauthor");
		});

		$('#e_publish_threeauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_threeauthor");
		});

		$('#e_publish_fourauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_fourauthor");
		});

		$('#e_publish_fiveauthor').textbox('textbox').bind('click', function() {
			searchObjAuthor("e_publish_fiveauthor");
		});

		$('#e_publish_otherauthor').textbox('textbox').bind('click', function() {
			searchOtherAuthor("e_publish_otherauthor");
		});

		// year
		var currYear = new Date().getFullYear(), yearData = [];
		for ( var i = 1990; i <= currYear+1; i++) {
			yearData[i - 1990] = {};
			yearData[i - 1990].id = i;
			yearData[i - 1990].text = i;
		}

		yearData = yearData.reverse();
		$('#e_publish_year').combobox('loadData', yearData);
		$('#e_publish_year').combobox('select', yearData[0].id);

		// 回显
		$('#e_publish_publishid').val(rows[0].publishid);
		$('#e_publish_title').textbox('setValue', rows[0].title);
		$('#e_publish_year').combobox('setValue', rows[0].year);
		$('#e_publish_isbn').textbox('setValue', rows[0].isbn);
		$('#e_publish_publishtype').textbox('setValue', rows[0].publishtype);
		$('#e_publish_firsteditor').textbox('setValue', rows[0].firsteditor);
		$('#e_publish_seceditor').textbox('setValue', rows[0].seceditor);
		$('#e_publish_thirdeditor').textbox('setValue', rows[0].thirdeditor);
		$('#e_publish_othereditor').textbox('setValue', rows[0].othereditor);
		$('#e_publish_firstsubeditor').textbox('setValue', rows[0].firstsubeditor);
		$('#e_publish_secsubeditor').textbox('setValue', rows[0].secsubeditor);
		$('#e_publish_thirdsubeditor').textbox('setValue', rows[0].thirdsubeditor);
		$('#e_publish_othersubeditor').textbox('setValue', rows[0].othersubeditor);
		$('#e_publish_firstauthor').textbox('setValue', rows[0].firstauthor);
		$('#e_publish_secauthor').textbox('setValue', rows[0].secauthor);
		$('#e_publish_threeauthor').textbox('setValue', rows[0].threeauthor);
		$('#e_publish_fourauthor').textbox('setValue', rows[0].fourauthor);
		$('#e_publish_fiveauthor').textbox('setValue', rows[0].fiveauthor);
		$('#e_publish_otherauthor').textbox('setValue', rows[0].otherauthor);
		
		$('#e_publish_firsteditorcode').val(rows[0].firsteditorcode);
		$('#e_publish_seceditorcode').val(rows[0].seceditorcode);
		$('#e_publish_thirdeditorcode').val(rows[0].thirdeditorcode);
		$('#e_publish_othereditorcode').val(rows[0].othereditorcode);
		$('#e_publish_firstsubeditorcode').val(rows[0].firstsubeditorcode);
		$('#e_publish_secsubeditorcode').val(rows[0].secsubeditorcode);
		$('#e_publish_thirdsubeditorcode').val(rows[0].thirdsubeditorcode);
		$('#e_publish_othersubeditorcode').val(rows[0].othersubeditorcode);
		$('#e_publish_firstauthorcode').val(rows[0].firstauthorcode);
		$('#e_publish_secauthorcode').val(rows[0].secauthorcode);
		$('#e_publish_threeauthorcode').val(rows[0].threeauthorcode);
		$('#e_publish_fourauthorcode').val(rows[0].fourauthorcode);
		$('#e_publish_fiveauthorcode').val(rows[0].fiveauthorcode);
		$('#e_publish_otherauthorcode').val(rows[0].otherauthorcode);
//		$('#e_dt_examinestatus').val(rows[0].examinestatus);
		// TODO 是否需要添加其它重要字段

		if(rolename === "科研人员" && rows[0].examinestatus === "已退回"){
			$('#e_publish_examinestatus').val("已保存");
		}else{
			$('#e_publish_examinestatus').val(rows[0].examinestatus);
		}
		
		if(!rows[0].append){
			//当前记录不存在附件
			applyAjaxFileUpload('#e_publish_upload', "/upload.action","#publish_edit_append");
		}else{
			$('#publish_edit_append').val(rows[0].append);
			editShowUpload('#e_publish_upload', '#publish_edit_append', rows[0].append);
		}

		EditValidate($('#admin_publish_editForm'), rows[0].examinestatus);
		$('#admin_publish_editDialog').dialog('open');
		
	}
};

var editpublish_btn_ok = function() {
	$('#admin_publish_editForm').form('submit', {
		url : $('#contextPath').val() + '/publishAction!edit.action',
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
//			clearUpload('e_publish_upload');
			$('#admin_publish_editDialog').dialog('close');
		}
	});
};

var editpublish_btn_cancel = function() {
//	clearUpload('e_dt_upload');
	$('#admin_publish_editDialog').dialog('close');
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

	//将需要提交的论文列表
	var toSubmit = {};
	toSubmit.submitUser = $('#username').val();
	toSubmit.publishList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		// 防御，排除审核状态高于“审核通过”的记录
		if (rows[i].examinestatus === '审核通过' || rows[i].examinestatus === '已提交') {
			$.messager.alert('警告', '您不能重复提交！');
			return;
		}		
		toSubmit.publishList.push({
			publishid : rows[i].publishid
		});
	}

	var publishlist = JSON.stringify(toSubmit);
	$.ajax({
		url : $('#contextPath').val() + '/publishAction!submit.action',
		data : 'publishlist=' + publishlist,
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

//撤回已提交地图信息
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
	
	$.messager.confirm("确认", "您确定要撤回选择的数据出版记录？", function(r) {
		if (r) {

			// 将需要撤销的论文列表
			var toRevoke = {};
			toRevoke.publishList = [];
			for (var i = 0; i < rows.length; i++) {
				// 防御，排除审核状态高于“审核通过”的记录
				if (rows[i].examinestatus !== '已提交') {
					$.messager.alert('警告', '您只能撤回已提交的记录，请仔细检查选择的数据出版记录！');
					return;
				}

				toRevoke.publishList.push({
					publishid : rows[i].publishid
				});
			}

			var publishList = JSON.stringify(toRevoke);
			$.ajax({
				url : $('#contextPath').val() + '/publishAction!revoke.action',
				data : 'publishList=' + publishList,
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '撤销出现错误！请联系管理员处理！'
					});
				},
				success : function(data) {
					json = $.parseJSON(data);
					if (json.success) {
						// 重新加载地图列表
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
	$('#publish_audit_opinion').textbox('setValue', "");
	$('#audit_publish_dialog').dialog('open');
};

// 审核确认
var auditpublish_btn_ok = function() {
	var rows = $('#center_table').datagrid('getChecked');
	var auditValue = $(
			"#audit_publish_form input[type='radio'][name='audit']:checked").val(), auditOpinion = $(
			'#publish_audit_opinion').textbox('getValue');

	// 将需要提交的论文列表
	var toSubmit = {};
	toSubmit.audit = auditValue;
	toSubmit.auditOpinion = auditOpinion;
	toSubmit.publishList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		toSubmit.publishList.push({
			publishid : rows[i].publishid
		});
	}

	var audit = JSON.stringify(toSubmit);

	$.ajax({
		url : $('#contextPath').val() + '/publishAction!audit.action',
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

			$('#audit_publish_dialog').dialog('close');

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

// 审核取消
var auditpublish_btn_cancel = function() {
	$('#audit_publish_dialog').dialog('close');
};



// 高级查询
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

	var top = $('#publish_statusfilter').offset().top + 30;
	var left = $('#publish_statusfilter').offset().left;
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
var importfunc = function() {
	$('#import_publish').dialog("open");
};

// 论文导出
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('publishAction!downloadExcel.action');
};

/*
var exportpublish_btn_ok = function() {
	$('#admin_publish_exportForm').form(
			'submit',
			{
				url : $('#contextPath').val() + '/publishAction!edit.action',
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
											'getRowIndex', rows[0].publishid),
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

var exportpublish_btn_cancel = function() {
	$('#admin_publish_exportForm').dialog('close');
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
*/