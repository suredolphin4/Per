/**
 * ***************************************** Init Operator ******************************************
 **/
var moduleLoaded = function() {

	$('#center_table')
			.datagrid(
					{
						url : $('#contextPath').val()
								+ '/scoreAction!datagrid.action',
						fit : true,
						fitColumns : false,
						border : false,
						pagination : true,
						rownumbers : true,
						idField : 'id',
						pageSize : 100,
						pageList : [ 20, 30, 40, 50, 100 ],
//						sortName : 'savetime',
//						sortOrder : 'desc',
//						checkOnSelect : false,
						checkOnSelect : false,
						selectOnCheck : false,
						queryParams: {
							usercode : $('#usercode').val()
						},
						onLoadSuccess : function() {
							var rolename = $('#urole').val();
						},
						columns : [ [
								{
									field : 'id',
									title : '编号',
									width : 50,
									hidden : true
								},
								{
									field : 'domain',
									title : '研究领域',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'name',
									title : '名称',
									width : 80,
									sortable : true
								},
								{
									field : 'usercode',
									title : '用户编号',
									width : 80,
									sortable : true
								},
								{
									field : 'total',
									title : '科研绩效合计',
									width : 100,
									resizable : true
								},
								{
									field : 'sciThesisScore',
									title : 'SCI和SSCI论文',
									width : 120,
									resizable : true
								},
								{
									field : 'inlandThesisScore',
									title : '国内论文',
									width : 80,
									resizable : true
								},
								{
									field : 'otherThesisScore',
									title : '其他论文',
									width : 80,
									resizable : true
								},
								{
									field : 'reportScore',
									title : '咨询报告',
									width : 150,
									resizable : true
								},
								{
									field : 'patentScore',
									title : '专利',
									width : 150,
									resizable : true
								},
								{
									field : 'softwareScore',
									title : '软件',
									width : 150,
									resizable : true
								},
								{
									field : 'standardScore',
									title : '标准',
									width : 150,
									resizable : true
								},
								{
									field : 'planScore',
									title : '规划',
									width : 150,
									resizable : true
								},
								{
									field : 'writingScore',
									title : '著作',
									width : 150,
									resizable : true
								},
								{
									field : 'mapScore',
									title : '地图集',
									width : 150,
									resizable : true
								},

								{
									field : 'projectScore',
									title : '项目大小',
									width : 150,
									resizable : true
								},
								{
									field : 'awardScore',
									title : '国家及省部级奖励',
									width : 120,
									resizable : true
								},
								{
									field : 'graduteScore',
									title : '学生得奖',
									width : 80,
									resizable : true
								},
								{
									field : 'lessonScore',
									title : '授课',
									width : 150,
									resizable : true
								},
							    {
								    field : 'publishScore',
								    title : '数据出版',
								    width : 150,
								    resizable : true
							    },
							    {
								    field : 'achievetransScore',
								    title : '成果转化',
								    width : 150,
								    resizable : true
							    },
							{
								field : 'thirdAssessScore',
								title : '第三方评估',
								width : 200,
								resizable : true
							},
								{
									field : 'level',
									title : '申报级别',
									width : 150,
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
							 var panel = $(this).datagrid('getPanel');   
						     var tr = panel.find('div.datagrid-body tr'); 
						     var fieldArray = ["total"]
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
};

//论文导出
var exportfunc = function() {
	// $('#export_lw').dialog("open");
	downloadExcel('scoreAction!downloadExcel.action');
};

/**
 * ***************************************** research statistics Operator *******************************************/
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
	var domain = $('#s_which_domain').combobox('getText');
	var authorcode = $('#s_lw_authorcode').val();

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.s_begin_year = begin_year;
			param.s_end_year = end_year;
			param.s_lw_author = author;
			param.s_lw_authorcode = authorcode;
			param.s_which_domain = domain;
		}
	});

	// 最后关闭
	$('#advancedSearch_dialog').dialog('close');
};

// 高级查询取消
var adv_search_btn_cancel = function() {
	$('#advancedSearch_dialog').dialog('close');
};

// 研究成果数量统计导出
var exportzxfunc = function() {
	downloadExcel('researchAction!downloadExcel.action');
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
