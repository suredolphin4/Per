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
						rownumbers : true,
						idField : 'id',
						pageSize : 100,
						pageList : [ 10, 20, 30, 40, 50, 100 ],
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
									field : 'username',
									title : '名称',
									width : 60,
									sortable : true
								},
								{
									field : 'usercode',
									title : '用户编号',
									width : 60,
									sortable : true
								},
								{
									field : 'domain',
									title : '领域',
									width : 100,
									resizable : true,
									sortable : true
								},
								{
									field : 'paper_total_asl',
									title : '第一通讯或作者',
									width : 100,
									sortable : true
								},
								{
									field : 'paper_total',
									title : '论文合计',
									width : 70,
									resizable : true,
									sortable : true
								},
								{
									field : 'sci_pub_total_asl',
									title : 'SCI/SSCI已发表合计(第一或通讯作者)',
									width : 200,
									resizable : true,
									sortable : true
								},
								{
									field : 'sci_pub_total',
									title : 'SCI/SSCI已发表合计',
									width : 130,
									resizable : true,
									sortable : true
								},
								{
									field : 'sci_accept_total_asl',
									title : 'SCI/SSCI已接受合计(第一或通讯作者)',
									width : 200,
									resizable : true,
									sortable : true
								},
								{
									field : 'sci_accept_total',
									title : 'SCI/SSCI已接受合计',
									width : 140,
									resizable : true,
									sortable : true
								},
								{
									field : 'inland_total_asl',
									title : '国内刊物合计(第一或通讯作者)',
									width : 180,
									resizable : true,
									sortable : true
								},
								{
									field : 'inland_total',
									title : '国内刊物合计',
									width : 100,
									sortable : true
								},
								{
									field : 'ei_total_asl',
									title : 'EI收录合计(第一或通讯作者)',
									width : 170,
									sortable : true
								},
								{
									field : 'ei_total',
									title : 'EI收录合计',
									width : 100,
									sortable : true
								},
								{
									field : 'istp_total',
									title : 'ISTP收录合计',
									width : 100,
									sortable : true
								},
								{
									field : 'abroad_total',
									title : '其他国外刊物合计',
									width : 110,
									resizable : true,
									sortable : true
								},
								{
									field : 'books_total',
									title : '著作合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'software_total',
									title : '软件合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'patent_total',
									title : '专利合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'award_total',
									title : '奖励合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'report_total',
									title : '咨询报告合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'plan_total',
									title : '规划合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'standard_total',
									title : '标准合计',
									width : 80,
									resizable : true,
									sortable : true
								},
							    {
								    field : 'map_total',
								    title : '地图合计',
								    width : 80,
							    	resizable : true,
								    sortable : true
							    },
								{
									field : 'publish_total',
									title : '数据出版合计',
									width : 80,
									resizable : true,
									sortable : true
								},
								{
									field : 'achievetrans_total',
									title : '成果转化合计',
									width : 80,
									resizable : true,
									sortable : true
								},
							{
								field : 'thirdAssess_total',
								title : '第三方评估合计',
								width : 100,
								resizable : true,
								sortable : true
							},
								{
									field : 'dec_level',
									title : '申报级别',
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
	var domain = $('#s_which_domain').combobox('getText');
	var name = $('#s_which_name').val();
	var search_usercode=$('#s_which_usercode').val();

	// 过滤操作
	$('#center_table').datagrid({
		onBeforeLoad : function(param) {
			param.domain = domain;
			param.username = name;
			param.search_usercode=search_usercode;
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
var exportfunc = function() {
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
