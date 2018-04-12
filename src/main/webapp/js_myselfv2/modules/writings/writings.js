/**
 * ***************************************** Init Operator
 * *****************************************
 */
var moduleLoaded = function() {
	$('#center_table').datagrid({
		url : $('#contextPath').val() + '/writingsAction!datagrid.action',
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
		columns : [ [ {
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
			field : 'title',
			title : '著作名称',
			width : 300,
			sortable : true
		}, {
			field : 'pubKind',
			title : '出版类别',
			width : 150,
			sortable : true
		}, {
			field : 'writingKind',
			title : '著作类别',
			width : 150,
			sortable : true
		}, {
			field : 'press',
			title : '出版社',
			width : 150,
			sortable : true
		}, {
			field : 'interCooperate',
			title : '是否国际合作',
			width : 150,
			sortable : true
		}, {
			field : 'wordCount',
			title : '总字数（万）',
			width : 150,
			sortable : true
		}, {
			field : 'isbn',
			title : 'ISBN号',
			width : 150,
			sortable : true
		}, 
		{
			field : 'audit_status',
			title : '审核状态',
			width : 100,
			sortable : true
		}, {
			field : 'auditOpinion',
			title : '审核意见',
			width : 150,
			sortable : true
		}, {
			field : 'first_editor',
			title : '主编一',
			width : 100,
			sortable : true
		},{
			field : 'first_editor_code',
			title : '主编一arp',
			width : 100,
			sortable : true
		},
		{
			field : 'firsteditorscore',
			title : '主编一绩效',
			width : 80
		},
		{
			field : 'second_editor',
			title : '主编二',
			width : 100,
			sortable : true
		},{
			field : 'second_editor_code',
			title : '主编二arp',
			width : 100,
			sortable : true
		},
		{
			field : 'seceditorscore',
			title : '主编二绩效',
			width : 80
		},
		{
			field : 'third_editor',
			title : '主编三',
			width : 100,
			sortable : true
		},{
			field : 'third_editor_code',
			title : '主编三arp',
			width : 100,
			sortable : true
		},
		{
			field : 'threeeditorscore',
			title : '主编三绩效',
			width : 80
		},
		{
			field : 'other_editor',
			title : '其他主编',
			width : 150,
			sortable : true
		},
		{
			field : 'other_editor_code',
			title : '其他主编arp',
			width : 150,
			sortable : true
		},
		{
			field : 'othereditorscore',
			title : '其他主编绩效',
			width : 80
		},
			{
				field : 'first_subeditor',
				title : '副主编一',
				width : 100,
				sortable : true
			},{
				field : 'first_subeditor_code',
				title : '副主编一arp',
				width : 100,
				sortable : true
			},
			{
				field : 'firstsubeditorscore',
				title : '副主编一绩效',
				width : 80
			},
			{
				field : 'second_subeditor',
				title : '副主编二',
				width : 100,
				sortable : true
			},{
				field : 'second_subeditor_code',
				title : '副主编二arp',
				width : 100,
				sortable : true
			},
			{
				field : 'secsubeditorscore',
				title : '副主编二绩效',
				width : 80
			},
			{
				field : 'third_subeditor',
				title : '副主编三',
				width : 100,
				sortable : true
			},{
				field : 'third_subeditor_code',
				title : '副主编三arp',
				width : 100,
				sortable : true
			},
			{
				field : 'threesubeditorscore',
				title : '副主编三绩效',
				width : 80
			},
			{
				field : 'other_subeditor',
				title : '其他副主编',
				width : 150,
				sortable : true
			},
			{
				field : 'other_subeditor_code',
				title : '其他副主编arp',
				width : 150,
				sortable : true
			},
			{
				field : 'othersubeditorscore',
				title : '其他副主编绩效',
				width : 80
			},
		{
			field : 'first_author',
			title : '第一作者',
			width : 100,
			sortable : true
		},
		{
			field : 'first_author_code',
			title : '第一作者arp',
			width : 100,
			sortable : true
		},
		{
			field : 'firstauthorscore',
			title : '第一作者绩效',
			width : 80
		},
		{
			field : 'second_author',
			title : '第二作者',
			width : 100,
			sortable : true
		}, 
		{
			field : 'second_author_code',
			title : '第二作者arp',
			width : 100,
			sortable : true
		},
		{
			field : 'secauthorscore',
			title : '第二作者绩效',
			width : 80
		},
		{
			field : 'third_author',
			title : '第三作者',
			width : 100,
			sortable : true
		}, 
		{
			field : 'third_author_code',
			title : '第三作者arp',
			width : 100,
			sortable : true
		},
		{
			field : 'threeauthorscore',
			title : '第三作者绩效',
			width : 80
		},
		{
			field : 'fourth_author',
			title : '第四作者',
			width : 100,
			sortable : true
		}, 
		{
			field : 'fourth_author_code',
			title : '第四作者arp',
			width : 100,
			sortable : true
		},
		{
			field : 'fourauthorscore',
			title : '第四作者绩效',
			width : 80
		},
		{
			field : 'fifth_author',
			title : '第五作者',
			width : 100,
			sortable : true
		},
		{
			field : 'fifth_author_code',
			title : '第五作者arp',
			width : 100,
			sortable : true
		},
		{
			field : 'fiveauthorscore',
			title : '第五作者绩效',
			width : 80
		},
		{
			field : 'other_author',
			title : '其他作者',
			width : 150,
			sortable : true
		}, 
		{
			field : 'other_author_code',
			title : '其他作者arp',
			width : 300,
			sortable : true
		},
		{
			field : 'otherauthorscore',
			title : '其他作者绩效',
			width : 80
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
			width : 100,
			sortable : true,
			formatter : function(value, rowData,
					rowIndex) {
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
	
	$('#wt_audit_filter').combobox({
		onSelect : function(record) {
			var item = record.value;
			$('#center_table').datagrid({
				onBeforeLoad : function(param) {
					param.auditfilter = item;
				}
			});
		}
	});
	
	$('#writing_add_dialog').dialog({
		onClose:  function(){
			//其他作者列表清空
			//$('#otherauthor_list').datalist('loadData', []);
			
			clearUpload('wt_upload');
		}
	});
	
	$('#writing_edit_dialog').dialog({
		onClose:  function(){
			//其他作者列表清空
			//$('#otherauthor_list').datalist('loadData', []);
			$('#e_wt_append').val('');
			clearUpload('e_wt_upload');
		}
	});
	
	applyExcelUpload('#writing_import', "/writingsAction!upLoadExcel.action", null,'#center_table');
//	applyAjaxFileUpload('#writing_import', "/writingsAction!upLoadExcel.action", null);
	
	var currYear = new Date().getFullYear(), yearData = [];
	for (var i = 1990; i <= currYear+1; i++) {
		yearData[i - 1990] = {};
		yearData[i - 1990].id = i;
		yearData[i - 1990].text = i;
	}
	yearData = yearData.reverse();
	$('#wt_year').combobox('loadData', yearData);
	$('#e_wt_year').combobox('loadData', yearData);
	
	//允许编辑字数列
	$('#writing_otherperson_result_datagrid').edatagrid({
		width:300,
		height:340,
		autoSave:true
	});
	
	$('#writing_otherperson_result_datagrid .numberbox').bind('keyup', function(e){
		var code = e.keyCode || e.which;
	    if(code == 13){
		    //保存更改 第一次编辑可能不会改变值
		    $('#writing_otherperson_result_datagrid').edatagrid('acceptChanges');
	    	$('#writing_otherperson_result_datagrid').edatagrid('endEdit', editIndex);
	    	//do something
	    }
	});
};

// 著作添加
var wt_append = function() {
	// 界面初始化
	$('#wt_first_editor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_first_editor");
	});

	$('#wt_second_editor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_second_editor");
	});

	$('#wt_third_editor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_third_editor");
	});

	$('#wt_other_editor').textbox('textbox').bind('click', function() {
		//wtSearchOtherPerson("wt_other_editor");
		searchOtherAuthor("wt_other_editor")
	});
	$('#wt_first_subeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_first_subeditor");
	});

	$('#wt_second_subeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_second_subeditor");
	});

	$('#wt_third_subeditor').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_third_subeditor");
	});

	$('#wt_other_subeditor').textbox('textbox').bind('click', function() {
		//wtSearchOtherPerson("wt_other_subeditor");
		searchOtherAuthor("wt_other_subeditor")
	});


	$('#wt_first_author').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_first_author");
	});

	$('#wt_second_author').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_second_author");
	});

	$('#wt_third_author').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_third_author");
	});

	$('#wt_fourth_author').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_fourth_author");
	});

	$('#wt_fifth_author').textbox('textbox').bind('click', function() {
		searchObjAuthor("wt_fifth_author");
	});

	$('#wt_other_author').textbox('textbox').bind('click', function() {
		//wtSearchOtherPerson("wt_other_author");
		searchOtherAuthor("wt_other_author")
	});
	

	$('#writing_add_dialog').dialog('open');
	$('#writing_add_form').form('clear');
	applyAjaxFileUpload('#wt_upload', "/upload.action", "#wt_append");
};

// 确认添加著作
var addwriting_btn_ok = function() {
	/*
	// 提交前验证，总字数是否大于每个作者字数之和
	var word_count = Number($('#wt_word_count').numberbox('getValue')), 
		first_editor_wc = Number($('#wt_first_editor_wc').numberbox('getValue')), 
		second_editor_wc = Number($('#wt_second_editor_wc').numberbox('getValue')), 
		third_editor_wc = Number($('#wt_third_editor_wc').numberbox('getValue')), 
		first_author_wc = Number($('#wt_first_author_wc').numberbox('getValue')), 
		second_author_wc = Number($('#wt_second_author_wc').numberbox('getValue')), 
		third_author_wc = Number($('#wt_third_author_wc').numberbox('getValue')), 
		fourth_author_wc = Number($('#wt_fourth_author_wc').numberbox('getValue')), 
		fifth_author_wc = Number($('#wt_fifth_author_wc').numberbox('getValue'));
	
	var other_editor_wc = $('#wt_other_editor_wc').textbox('getValue'),
	    other_author_wc = $('#wt_other_author_wc').textbox('getValue');
	
	var other_editor_wc_result = other_editor_wc.split(',').map(function(x){return Number(x);}),
		other_author_wc_result = other_author_wc.split(',').map(function(x){return Number(x);});

	var editor_sum = first_editor_wc + second_editor_wc + third_editor_wc,
		author_sum = first_author_wc + second_author_wc + third_author_wc + fourth_author_wc + fifth_author_wc;

	for(var i=0;i<other_editor_wc_result.length;i++){
		editor_sum += other_editor_wc_result[i];
	}
	for(var i=0;i<other_author_wc_result.length;i++){
		author_sum += other_author_wc_result[i];
	}
	
	if(word_count < (editor_sum + author_sum)){
		$.messager.alert('错误', '编辑及作者字数和不应超过总字数，请核对后填写！');
		return;
	}
	*/
	
	$('#writing_add_form').form('submit', {
		url : $('#contextPath').val() + '/writingsAction!add.action',
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

				$('#writing_add_dialog').dialog('close');
			} else {
				if(obj.msg === "duplicate row")
					$.messager.alert("操作提示", "操作失败, 著作已存在！", "error");
			}
		}
	});
};

// 取消添加著作
var addwriting_btn_cancel = function() {
	$('#writing_add_dialog').dialog('close');
};

// 删除著作
var wt_remove = function() {
	// 获取要删除的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可删除的记录！'
		});
		return;
	}

	// 防御，若为科研人员，则不能删除“审核通过“的记录
	var rolename = $('#urole').val();
	if (rolename === "科研人员") {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].audit_status === '审核通过' || rows[i].audit_status === '已提交') {
				$.messager.alert("警告", "您不能删除审核通过或已提交的记录，请重新选择！");
				return;
			}
		}
	}

	$.messager.confirm("确认", "您确定要删除选择的著作记录？", function(r) {
		if (r) {
			// 将需要提交的论文列表
			var toRemove = {};
			toRemove.wtids = '';
			for (var i = 0; i < rows.length; i++) {
				if (i === rows.length - 1) {
					toRemove.wtids += rows[i].id;
					continue;
				}
				toRemove.wtids += (rows[i].id + ',');
			}
			var remove = JSON.stringify(toRemove);
			$
					.ajax({
						url : $('#contextPath').val()
								+ '/writingsAction!remove.action',
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
								// 重新加载著作列表
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

// 编辑著作
var wt_edit = function() {
	// 获取当前选中行的id
	var rows = $('#center_table').datagrid('getChecked');
	if (rows.length < 1) {
		$.messager.alert("提示", "请选中一行后编辑！");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示", "只能编辑选中的一行记录！请您重新选择");
		return;
	}

	// 防御，若为科研人员
	/*
	var rolename = $('#urole').val();
	
	if (rolename == "科研人员") {
		if (rows[0].examinestatus === '审核通过') {
			$.messager.alert("警告", "您不能编辑审核通过的记录，请重新选择！");
			return;
		} else if (rows[0].examinestatus === '已提交') {
			$.messager.alert("警告", "您不能编辑已提交的记录，请重新选择！");
			return;
		}
	}
	*/
	// 只能编辑一行
	if (rows.length == 1) {
		var rolename = $('#urole').val();

		$('#e_wt_first_editor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_first_editor");
		});

		$('#e_wt_second_editor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_second_editor");
		});

		$('#e_wt_third_editor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_third_editor");
		});

		$('#e_wt_other_editor').textbox('textbox').bind('click', function () {
			//wtSearchOtherPerson("e_wt_other_editor");
			searchOtherAuthor("e_wt_other_editor");
		});
		$('#e_wt_first_subeditor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_first_subeditor");
		});

		$('#e_wt_second_subeditor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_second_subeditor");
		});

		$('#e_wt_third_subeditor').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_third_subeditor");
		});

		$('#e_wt_other_subeditor').textbox('textbox').bind('click', function () {
			//wtSearchOtherPerson("e_wt_other_subeditor");
			searchOtherAuthor("e_wt_other_subeditor");
		});

		$('#e_wt_first_author').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_first_author");
		});

		$('#e_wt_second_author').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_second_author");
		});

		$('#e_wt_third_author').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_third_author");
		});

		$('#e_wt_fourth_author').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_fourth_author");
		});

		$('#e_wt_fifth_author').textbox('textbox').bind('click', function () {
			searchObjAuthor("e_wt_fifth_author");
		});

		$('#e_wt_other_author').textbox('textbox').bind('click', function () {
			//wtSearchOtherPerson("e_wt_other_author");
			searchOtherAuthor("e_wt_other_author");
		});

		//回显著作
		var row = rows[0];
		$('#e_wt_id').val(rows[0].id);
		$('#e_wt_title').textbox('setValue', rows[0].title);
		$('#e_wt_press').textbox('setValue', rows[0].press);
		$('#e_wt_pub_kind').combobox('setValue', rows[0].pubKind);
		$('#e_wt_inter').textbox('setValue', rows[0].interCooperate);
		$('#e_wt_word_count').textbox('setValue', rows[0].wordCount);
		$('#e_wt_isbn').textbox('setValue', rows[0].isbn);
		$('#e_wt_writing_kind').textbox('setValue', rows[0].writingKind);
		$('#e_wt_year').combobox('setValue', rows[0].year);
		$('#e_wt_first_editor').textbox('setValue', rows[0].first_editor);
//	$('#e_wt_first_editor_wc').textbox('setValue', rows[0].first_editor_wc);
		$('#e_wt_second_editor').textbox('setValue', rows[0].second_editor);
//	$('#e_wt_second_editor_wc').textbox('setValue', rows[0].second_editor_wc);
		$('#e_wt_third_editor').textbox('setValue', rows[0].third_editor);
//	$('#e_wt_third_editor_wc').textbox('setValue', rows[0].third_editor_wc);
		$('#e_wt_other_editor').textbox('setValue', rows[0].other_editor);
//	$('#e_wt_other_editor_wc').textbox('setValue', rows[0].other_editor_wc);
		$('#e_wt_first_subeditor').textbox('setValue', rows[0].first_subeditor);
		$('#e_wt_second_subeditor').textbox('setValue', rows[0].second_subeditor);
		$('#e_wt_third_subeditor').textbox('setValue', rows[0].third_subeditor);
		$('#e_wt_other_subeditor').textbox('setValue', rows[0].other_subeditor);
		//$('#e_wt_audit_status').textbox('setValue',row[0].audit_status);

		$('#e_wt_first_author').textbox('setValue', rows[0].first_author);
//	$('#e_wt_first_author_wc').textbox('setValue', rows[0].first_author_wc);
		$('#e_wt_second_author').textbox('setValue', rows[0].second_author);
//	$('#e_wt_second_author_wc').textbox('setValue', rows[0].second_author_wc);
		$('#e_wt_third_author').textbox('setValue', rows[0].third_author);
//	$('#e_wt_third_author_wc').textbox('setValue', rows[0].third_author_wc);
		$('#e_wt_fourth_author').textbox('setValue', rows[0].fourth_author);
//	$('#e_wt_fourth_author_wc').textbox('setValue', rows[0].fourth_author_wc);
		$('#e_wt_fifth_author').textbox('setValue', rows[0].fifth_author);
//	$('#e_wt_fifth_author_wc').textbox('setValue', rows[0].fifth_author_wc);
		$('#e_wt_other_author').textbox('setValue', rows[0].other_author);
//	$('#e_wt_other_author_wc').textbox('setValue', rows[0].other_author_wc);

		//code
		$('#e_wt_first_editorcode').val(rows[0].first_editor_code);
		$('#e_wt_second_editorcode').val(rows[0].second_editor_code);
		$('#e_wt_third_editorcode').val(rows[0].third_editor_code);
		$('#e_wt_other_editorcode').val(rows[0].other_editor_code);
		$('#e_wt_first_subeditorcode').val(rows[0].first_subeditor_code);
		$('#e_wt_second_subeditorcode').val(rows[0].second_subeditor_code);
		$('#e_wt_third_subeditorcode').val(rows[0].third_subeditor_code);
		$('#e_wt_other_subeditorcode').val(rows[0].other_subeditor_code);
		$('#e_wt_first_authorcode').val(rows[0].first_author_code);
		$('#e_wt_second_authorcode').val(rows[0].second_author_code);
		$('#e_wt_third_authorcode').val(rows[0].third_author_code);
		$('#e_wt_fourth_authorcode').val(rows[0].fourth_author_code);
		$('#e_wt_fifth_authorcode').val(rows[0].fifth_author_code);
		$('#e_wt_other_authorcode').val(rows[0].other_author_code);

		if(rolename === "科研人员" && rows[0].audit_status === "已退回"){
			$('#e_wt_audit_status').val("已保存");
		}else{
			$('#e_wt_audit_status').val(rows[0].audit_status);
		}

		if (!row.append) {
			//当前记录不存在附件
			applyAjaxFileUpload('#e_wt_upload', "/upload.action", "#e_wt_append");
		} else {
			$('#e_wt_append').val(row.append);
			editShowUpload('#e_wt_upload', '#e_wt_append', row.append);
		}

		EditValidate($('#writing_edit_form'), rows[0].audit_status);
		$('#writing_edit_dialog').dialog('open');
	}
};

// 编辑著作确认
var edit_writing_btn_ok = function() {
	/*
	var word_count = Number($('#e_wt_word_count').numberbox('getValue')), 
	first_editor_wc = Number($('#e_wt_first_editor_wc').numberbox('getValue')), 
	second_editor_wc = Number($('#e_wt_second_editor_wc').numberbox('getValue')), 
	third_editor_wc = Number($('#e_wt_third_editor_wc').numberbox('getValue')), 
	first_author_wc = Number($('#e_wt_first_author_wc').numberbox('getValue')), 
	second_author_wc = Number($('#e_wt_second_author_wc').numberbox('getValue')), 
	third_author_wc = Number($('#e_wt_third_author_wc').numberbox('getValue')), 
	fourth_author_wc = Number($('#e_wt_fourth_author_wc').numberbox('getValue')), 
	fifth_author_wc = Number($('#e_wt_fifth_author_wc').numberbox('getValue'));

	var other_editor_wc = $('#e_wt_other_editor_wc').textbox('getValue'),
    	other_author_wc = $('#e_wt_other_author_wc').textbox('getValue');

	var other_editor_wc_result = other_editor_wc.split(',').map(function(x){return Number(x);}),
		other_author_wc_result = other_author_wc.split(',').map(function(x){return Number(x);});
	
	var editor_sum = first_editor_wc + second_editor_wc + third_editor_wc,
		author_sum = first_author_wc + second_author_wc + third_author_wc + fourth_author_wc + fifth_author_wc;
	
	for(var i=0;i<other_editor_wc_result.length;i++){
		editor_sum += other_editor_wc_result[i];
	}
	for(var i=0;i<other_author_wc_result.length;i++){
		author_sum += other_author_wc_result[i];
	}
	
	if(word_count < (editor_sum + author_sum)){
		$.messager.alert('错误', '编辑及作者字数和不应超过总字数，请核对后填写！');
		return;
	}
	*/
	$('#writing_edit_form').form('submit', {
		url : $('#contextPath').val() + '/writingsAction!edit.action',
		onSubmit : function(param) {
			if(!FormValidate($(this)))
				return false;
			return $(this).form('validate');
		},
		success : function (r) {
			// 修改成功，解析返回的json信息
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
				// 重新加载著作列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');

				$.messager.show({
					title: '提示',
					msg: obj.msg
				});

				$('#writing_edit_dialog').dialog('close');
			}
			else {
				if (obj.msg === "duplicate row")
					$.messager.alert("操作提示", "操作失败, 著作已存在！", "error");
			}
		}
	});
};

// 取消编辑著作
var edit_writing_btn_cancel = function() {
	$('#writing_edit_dialog').dialog('close');
};

// 著作Excel导入
var importfunc = function() {
	$('#import_writing_dialog').dialog("open");
};

// 著作Excel导出
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('writingsAction!downloadExcel.action');
};

//著作提交操作
var wt_submit = function(){
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
	toSubmit.wtList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		// 防御，排除审核状态高于“审核通过”的记录
		if (rows[i].audit_status === '审核通过' || rows[i].audit_status === '已提交') {
			$.messager.alert('警告', '您不能重复提交！');
			return;
		}
		toSubmit.wtList.push({
			id : rows[i].id
		});
	}

	var wtlist = JSON.stringify(toSubmit);
	$.ajax({
		url : $('#contextPath').val() + '/writingsAction!submit.action',
		data : 'wtlist=' + wtlist,
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

//撤回已提交著作
var wt_revoke = function(){
	// 获取要提交的所有行
	var rows = $('#center_table').datagrid('getChecked');

	if (rows.length < 1) {
		$.messager.show({
			title : '操作提示',
			msg : '请您至少选择一条可撤销的记录！'
		});
		return;
	}
	
	$.messager.confirm("确认", "您确定要撤回选择的著作记录？", function(r) {
		if (r) {
			// 将需要撤销的著作列表
			var toRevoke = {};
			toRevoke.wtList = [];
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].audit_status !== '已提交') {
					$.messager.alert('警告', '您只能撤回已提交的记录，请仔细检查选择的著作记录！');
					return;
				}

				toRevoke.wtList.push({
					id : rows[i].id
				});
			}

			var wtlist = JSON.stringify(toRevoke);
			$.ajax({
				url : $('#contextPath').val() + '/writingsAction!revoke.action',
				data : 'wtlist=' + wtlist,
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

//审核操作
var wt_audit = function(){
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
	
//	for (var i = 0; i < rows.length; i++) {
//		// 防御，排除审核状态高于“审核通过”的记录
//		if (rows[i].audit_status === '审核通过' || rows[i].examinestatus === '已提交') {
//			$.messager.alert('警告', '您不能重复提交！');
//			return;
//		}
//		toSubmit.lwList.push({
//			lwid : rows[i].lwid
//		});
//	}

	// 审核界面
	$('#wt_audit_dialog').dialog('open');
};

//审核确认
var auditwt_btn_ok = function(){
	var rows = $('#center_table').datagrid('getChecked');
	var auditValue = $("#wt_audit_form input[type='radio'][name='audit']:checked").val(), 
	    auditOpinion = $('#wt_audit_opinion').textbox('getValue');

	// 将需要审核的著作列表
	var toSubmit = {};
	toSubmit.audit = auditValue;
	toSubmit.auditOpinion = auditOpinion;
	toSubmit.wtList = [];
	var i;
	for (i = 0; i < rows.length; i++) {
		toSubmit.wtList.push({
			id : rows[i].id
		});
	}

	var audit = JSON.stringify(toSubmit);

	$.ajax({
		url : $('#contextPath').val() + '/writingsAction!audit.action',
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
				// 重新加载著作列表
				$('#center_table').datagrid('reload').datagrid('clearChecked');
			}

			$('#wt_audit_dialog').dialog('close');

			$.messager.show({
				title : '提示',
				msg : json.msg
			});
		},
		type : 'POST'
	});
};

//审核取消
var auditwt_btn_cancel = function(){
	$('#wt_audit_dialog').dialog('close');
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

//其他人员选择
var wtSearchOtherPerson = function(otherperson) {
	focusId = otherperson;
	openDialog('writing_otherauthor_dialog');
	wtGetOtherPersonList();
	$('#writing_otherperson_datagrid').datagrid('clearChecked').datagrid('clearSelections');
	$('#writing_otherperson_result_datagrid').edatagrid('loadData', []); //clear all datagrid
	
	//处理著作其他作者回显问题
	var otherauthorValue = $('#' + focusId).textbox('getValue'),
		otherauthorCode = $('#' + focusId + 'code').val(),
		otherauthorWorldCount = $('#' + focusId + '_wc').textbox('getValue'),
		otherauthorArray = otherauthorValue.split(','),
		otherauthorCodeArray = otherauthorCode.split(','),
		otherauthorWCArray = otherauthorWorldCount.split(',');
		//cleanAuthorArray = $.grep(otherauthorArray,function(n){ return(n);}),
		//cleanAuthorCodeArray = $.grep(otherauthorCodeArray,function(n){ return(n);});

	var resultList = $('#writing_otherperson_result_datagrid').edatagrid('getData');
	for(var i=1;i < otherauthorArray.length-1;i++){
		var contains = false;
		for (var j = 0; j < resultList.rows.length; j++) {
			var row = resultList.rows[j];
			if (row.personname === otherauthorArray[i] && row.personcode === otherauthorCodeArray[i]) {
				contains = true;
			}
		}
		if (contains === false) {
			$('#writing_otherperson_result_datagrid').edatagrid('appendRow', {
				personcode : otherauthorCodeArray[i],
				personname : otherauthorArray[i],
				personwc : otherauthorWCArray[i]
			});
		}
	}
	
	//结果datagrid加载完数据后，开启拖拽排序，下同
	$('#writing_otherperson_result_datagrid').edatagrid('enableDnd');
};

//其它作者界面读取函数
var wtGetOtherPersonList = function() {
	$('#writing_otherperson_datagrid').datagrid({
		url : $('#contextPath').val() + '/personAction!datagrid.action',
		fit : false,
		queryParams : {
			name : $('#wt_otherperson_name').val(),
			usercode : $('#wt_otherperson_name').val()
		},
		fitColumns : false,
		border : true,
		pagination : true,
		pagePosition : 'bottom',
		idField : 'personid',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'name',
		sortOrder : 'asc',
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : false,
		columns : [ [ {
			field : 'personid',
			title : 'ID',
			width : 50,
			checkbox : true,
			fixed : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 100,
			sortable : true,
			fixed : true
		}, {
			field : 'usercode',
			title : '编号',
			width : 50,
			fixed : true
		}, {
			field : 'lab',
			title : '研究室',
			width : 100,
			fixed : true
		}, {
			field : 'domain',
			title : '领域',
			width : 100
		} ] ],
		width : 400,
		height : 340,
		autoRowHeight : false
	});
};

//添加其他人员到结果列表
var otherperson_btn_add = function(){
	var resultList = $('#writing_otherperson_result_datagrid').edatagrid('getData');
	// 获取当前需要添加的作者列表
	// 1.获取本所人员
	var rows = $('#writing_otherperson_datagrid').datagrid('getChecked');
	for (var i = 0; i < rows.length; i++) {
		var contains = false;
		for (var j = 0; j < resultList.rows.length; j++) {
			var row = resultList.rows[j];
			if (row.personcode === rows[i].usercode && row.personname === rows[i].name) {
				contains = true;
			}
		}
		if (contains === false) {
			$('#writing_otherperson_result_datagrid').edatagrid('appendRow', {
				personcode : rows[i].usercode,
				personname : rows[i].name,
				personwc : 0.00
			});
		}
	}
	// 2.获取非本所人员
	var outsider = $('#wt_outsider_other').textbox('getValue');
	if (outsider !== '') {
		contains = false;
		for (j = 0; j < resultList.rows.length; j++) {
			var row = resultList.rows[j];
			if (outsider === row.personname) {
				contains = true;
			}
		}
		if (contains === false) {
			$('#writing_otherperson_result_datagrid').edatagrid('appendRow', {
				personcode : -1,
				personname : outsider
			});
			$('#outsider_other').textbox('clear');
		}
	}
	
	$('#writing_otherperson_result_datagrid').edatagrid('enableDnd');
};

//从其他人员到结果列表删除
var otherperson_btn_remove = function(){
	var rows = $('#writing_otherperson_result_datagrid').edatagrid('getSelections');
	for (var i = rows.length - 1; i >= 0; i--) {
		//判等逻辑不同于datalist
		var index = $('#writing_otherperson_result_datagrid').edatagrid('getRowIndex', rows[i]);
		$('#writing_otherperson_result_datagrid').edatagrid('deleteRow', index);
	}
	
	$('#writing_otherperson_result_datagrid').edatagrid('enableDnd');
};

//确认著作其他人员列表
var otherperson_btn_confirm = function(){
	var rows = $('#writing_otherperson_result_datagrid').edatagrid('getData').rows;
    if(rows.length === 0){
		$('#' + focusId).textbox('setValue', '');
		$('#' + focusId + 'code').val('');
		$('#' + focusId + '_wc').textbox('setValue', '');
        $('#writing_otherauthor_dialog').dialog('close');
        return;
    }
	else if(rows.length > 0){
		var index = $('#writing_otherperson_result_datagrid').edatagrid("getRowIndex", rows[rows.length-1]);
		$('#writing_otherperson_result_datagrid').edatagrid('endEdit', index);
	}

	var username = '', usercode = '', wordcount = '';
	for (var i = 0; i < rows.length; i++) {
		username += ',';
		username += rows[i].personname;
		usercode += ',';
		usercode += rows[i].personcode;
		wordcount += ',';
		wordcount += rows[i].personwc;
	}

	username += ',';
	usercode += ',';
	wordcount += ',';

	$('#' + focusId).textbox('setValue', username);
	$('#' + focusId + 'code').val(usercode);
	$('#' + focusId + '_wc').textbox('setValue', wordcount);

	$('#writing_otherauthor_dialog').dialog('close');
};

//取消著作其他人员选择
var otherperson_btn_cancel = function(){
	$('#writing_otherauthor_dialog').dialog('close');
};
