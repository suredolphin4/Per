/**
 * ***************************************** Init Operator ******************************************
 **/
var moduleLoaded = function() {

	$('#center_table')
			.datagrid(
					{
						url : $('#contextPath').val()
								+ '/researchAction!datagrid.action',
						fit : true,
						fitColumns : false,
						border : false,
						pagination : true,
						idField : 'zxid',
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50 ],
//						sortName : 'savetime',
//						sortOrder : 'desc',
//						checkOnSelect : false,
						checkOnSelect : true,
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
									width : 100,
									checkbox : true,
									sortable : true
								},
								{
									field : 'year',
									title : '年度',
									width : 100,
									sortable : true
								},
								{
									field : 'title',
									title : '报告题目',
									width : 250,
									sortable : true
								},
								{
									field : 'auditleader',
									title : '批示领导',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'auditdepart',
									title : '批示部门',
									width : 150,
									sortable : true
								},
								{
									field : 'auditlevel',
									title : '批示部门级别',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'auditdate',
									title : '批示日期',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'examinestatus',
									title : '审核状态',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'auditopinion',
									title : '审核意见',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'firstauthor',
									title : '撰写人1',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'firstauthorcode',
									title : '撰写人1arp',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'secauthor',
									title : '撰写人2',
									width : 150,
									sortable : true
								},
								{
									field : 'secauthorcode',
									title : '撰写人2arp',
									width : 150,
									sortable : true
								},
								{
									field : 'threeauthor',
									title : '撰写人3',
									width : 150,
									sortable : true
								},
								{
									field : 'threeauthorcode',
									title : '撰写人3arp',
									width : 150,
									sortable : true
								},
								{
									field : 'fourauthor',
									title : '撰写人4',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'fourauthorcode',
									title : '撰写人4arp',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'fiveauthor',
									title : '撰写人5',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'fiveauthorcode',
									title : '撰写人5arp',
									width : 150,
									resizable : true,
									sortable : true
								},
								{
									field : 'otherauthor',
									title : '其他撰写人',
									width : 200,
									resizable : true,
									sortable : true
								},
								{
									field : 'otherauthorcode',
									title : '其他撰写人arp',
									width : 200,
									resizable : true,
									sortable : true
								},
								{
									field : 'savetime',
									title : '审核时间',
									width : 150,
									resizable : true,
									sortable : true
								}
								] ],
						toolbar : '#tb',
						onLoadError : function() {
							$.messager.show({
								title : '提示',
								msg : '加载数据失败！'
							});
						}
					});
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
	var name = $('#s_lw_name').val();

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.s_begin_year = begin_year;
			param.s_end_year = end_year;
			param.s_lw_author = author;
			param.s_lw_name = name;
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
