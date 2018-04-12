/**
 * Created by zyh on 2016/4/3.
 */
var moduleLoaded = function(){
    $('#center_table').datagrid({
        url : $('#contextPath').val() + '/salarystatisticAction!datagrid.action',
        fit : true,
        fitColumns : false,
        border : false,
        pagination : true,
        idField : 'usercode',
        pageSize : 100,
        pageList : [20,50,100,150,200],
//		sortName : 'year',
//		sortOrder : 'asc',
        checkOnSelect : true,
        selectOnCheck : false,
        queryParams: {
            usercode : $('#usercode').val()
        },
        columns : [ [
            {field : 'usercode',title : '用户编号',width : 80,sortable : false},
            {field : 'username',title : '用户名',width : 80,sortable : false},
            {field : 'sci_thesis_type', title : 'SSCI或SCI论文类型', width : 120, resizable : false},
            {field : 'sci_thesis_first_count', title : 'SSCI或SCI论文一作数量', width : 140, resizable : false},
            {field : 'sci_thesis_second_count',title : 'SSCI或SCI论文二作数量',width : 140,sortable : false},
            {field : 'sci_thesis_third_count',title : 'SSCI或SCI论文三作数量',width : 140,sortable : false},
            {field : 'sci_thesis_salary',title : 'SSCI或SCI论文工资',width : 120,sortable : false},
            {field : 'nsci_thesis_type', title : '国内核心及国际非SCI期刊类型', width : 180, resizable : false},
            {field : 'nsci_thesis_first_count', title : '国内核心及国际非SCI期刊一作数量', width : 200, resizable : false},
            {field : 'nsci_thesis_second_count',title : '国内核心及国际非SCI期刊二作数量',width : 200,sortable : false},
            {field : 'nsci_thesis_third_count',title : '国内核心及国际非SCI期刊三作数量',width : 200,sortable : false},
            {field : 'nsci_thesis_salary',title : '国内核心及国际非SCI期刊工资',width : 180,sortable : false},
            {field : 'patent_type',title : '发明授权专利类型',width : 120,sortable : false},
            {field : 'patent_first_count',title : '发明授权专利一作数量',width : 130,sortable : false},
            {field : 'patent_second_count',title : '发明授权专利二作数量',width : 130,sortable : false},
            {field : 'patent_third_count',title : '发明授权专利三作数量',width : 130,sortable : false},
            {field : 'patent_salary',title : '发明授权专利工资',width : 130,sortable : false},
            {field : 'report_type',title : '重大咨询报告类型',width : 120,sortable : false},
            {field : 'report_first_count',title : '重大咨询报告一作数量',width : 140,sortable : false},
            {field : 'report_second_count',title : '重大咨询报告二作数量',width : 140,sortable : false},
            {field : 'report_third_count',title : '重大咨询报告三作数量',width : 140,sortable : false},
            {field : 'report_salary',title : '重大咨询报告工资',width : 120,sortable : false},
            {field : 'thesis_patent_report_sumup',title : '论文_专利_报告工资总计',width : 160,sortable : false, formatter:formatSalary},
            {field : 'n_plan_type',title : '重大规划类型',width : 100,sortable : false},
            {field : 'n_plan_first_count',title : '重大规划一作数量',width : 120,sortable : false},
            {field : 'n_plan_second_count',title : '重大规划二作数量',width : 120,sortable : false},
            {field : 'n_plan_third_count',title : '重大规划三作数量',width : 120,sortable : false},
            {field : 'n_plan_salary',title : '重大规划工资',width : 100,sortable : false},
            {field : 'p_plan_type',title : '部门或省级规划类型',width : 130,sortable : false},
            {field : 'p_plan_first_count',title : '部门或省级规划一作数量',width : 170,sortable : false},
            {field : 'p_plan_second_count',title : '部门或省级规划二作数量',width : 170,sortable : false},
            {field : 'p_plan_third_count',title : '部门或省级规划三作数量',width : 170,sortable : false},
            {field : 'p_plan_salary',title : '部门或省级规划工资',width : 120,sortable : false},
            {field : 'n_standard_type',title : '国家标准类型',width : 80,sortable : false},
            {field : 'n_standard_first_count',title : '国家标准一作数量',width : 100,sortable : false},
            {field : 'n_standard_second_count',title : '国家标准二作数量',width : 100,sortable : false},
            {field : 'n_standard_third_count',title : '国家标准三作数量',width : 100,sortable : false},
            {field : 'n_standard_salary',title : '国家标准工资',width : 80,sortable : false},
            {field : 'plan_standard_sumup',title : '规划_标准工资总计',width : 140,sortable : false, formatter:formatSalary},
            {field : 'writing_type',title : '著作类型',width : 80,sortable : false},
            {field : 'writing_first_count',title : '著作一作数量',width : 80,sortable : false},
            {field : 'writing_second_count',title : '著作二作数量',width : 80,sortable : false},
            {field : 'writing_third_count',title : '著作三作数量',width : 80,sortable : false},
            {field : 'writing_salary',title : '专著编著工资',width : 60,sortable : false},
            {field : 'national_map_type', title : '国家地图类型', width : 80, resizable : false},
            {field : 'national_map_first_count', title : '国家地图一作数量', width : 100, resizable : false},
            {field : 'national_map_second_count',title : '国家地图二作数量',width : 100,sortable : false},
            {field : 'national_map_third_count',title : '国家地图三作数量',width : 100,sortable : false},
            {field : 'national_map_salary',title : '国家地图工资',width : 80,sortable : false},
            {field : 'region_map_type',title : '区域地图类型',width : 80,sortable : false},
            {field : 'region_map_first_count',title : '区域地图一作数量',width : 100,sortable : false},
            {field : 'region_map_second_count',title : '区域地图二作数量',width : 100,sortable : false},
            {field : 'region_map_third_count',title : '区域地图三作数量',width : 100,sortable : false},
            {field : 'region_map_salary',title : '区域地图工资',width : 80,sortable : false},
            {field : 'writing_map_sumup',title : '专著编著_地图工资总计',width : 140,sortable : false,formatter:formatSalary},
            {field : 'all_sumup',title : '工资总计',width : 100,sortable : false, formatter:formatSalary}

           ]],
        toolbar : '#tb'
    });
};

var userRole = $('#urole').val();
if(userRole === '管理员'){
    var calcBtn =document.getElementById("caculate");
    calcBtn.style.visibility="visible";
} else {
    var calcBtn =document.getElementById("caculate");
    calcBtn.style.visibility="hidden";
}

var salStaExportExcel = function(){
    downloadExcel('salarystatisticAction!downloadExcel.action');
};


//计算工资
var salCalculate = function(){
    var msg = "您确定要执行工资计算吗？\n\n请确认！";
    if (confirm(msg)==true){
    //触发后端存储过程
    $.blockUI({  message: '<h1>正在计算工资,请稍候！</h1>',
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        } });

    $.ajax({
        type : 'GET',
        url : 'salarystatisticAction!calculateSalary.action',
        dataType: "json",
        success : function(data) {
            if(data.success){
                $.unblockUI({  message: '<h1>工资计算完成！</h1>'});
                setTimeout($.unblockUI, 2000);

                //重新读取表数据
                $('#center_table').datagrid('reload');
            }else{
                $.unblockUI({  message: '<h1>工资计算失败！</h1>'});
                setTimeout($.unblockUI, 2000);
            }
            //$.messager.show({
            //	title : '成功',
            //	msg : 'Excel导出完成！'
            //});
            //downloadFile('D:\test.xlsx', data);
        },
        error : function(xhr, ajaxOptions, thrownError) {
            $.blockUI({  message: '<h1>工资计算失败！</h1>'});
            setTimeout($.unblockUI, 2000);
        }
    });
    }
}

var salStaAdvancedSearch = function(){
    $('#salStaAdvancedSearch_dialog').dialog('open');
};

var ps_adv_search_btn_ok = function(){
    var username = $('#ps_username').textbox('getValue');
    // var domain = $('#ps_domain').textbox('getValue');
    var search_usercode=$('#ps_usercode').textbox('getValue');

    $('#center_table').datagrid({
        onBeforeLoad : function(param){
            param.s_username = username;
            // param.s_domain = domain;
            param.search_usercode = search_usercode;
        }
    });

    $('#salStaAdvancedSearch_dialog').dialog('close');
};

var ps_adv_search_btn_cancel = function(){
    $('#salStaAdvancedSearch_dialog').dialog('close');
};

var formatSalary = function(val,row){
    if (val != 0){
        return '<span style="color:#990000;font-weight:bolder;text-align:center">'+val+'</span>';
    } else {
        return val;
    }
}