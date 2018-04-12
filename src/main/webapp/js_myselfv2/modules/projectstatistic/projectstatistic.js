/**
 * Created by zxbing on 15-11-11.
 * 项目表统计js
 */
var moduleLoaded = function(){
    $('#center_table').datagrid({
        url : $('#contextPath').val() + '/projectstatisticAction!datagrid.action',
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
            {field : 'usercode',title : '用户编号',width : 80,sortable : true},
            {field : 'username',title : '用户名',width : 80,sortable : true},
            {field : 'domain',title : '所属领域',width : 80,sortable : true},
            {field : 'declare_level',title : '申报级别',width : 80,sortable : true},
            {field : 'cxqt0a',title : '0A-创新群体',width : 80,sortable : true},
            {field : 'yqzz0b',title : '0B-仪器研制',width : 80,sortable : true},
            {field : 'skfound0c',title : '0C-社科基金',width : 80,sortable : true},
            {field : 'zdfound01',title : '01-重大基金',width : 80,sortable : true},
            {field : 'cjzdfound0101',title : '01-01-参加重大基金',width : 120,sortable : true},
            {field : 'zdfound02', title : '02-重点基金', width : 80, resizable : true},
            {field : 'cjzdfound0201', title : '02-01-参加重点基金', width : 150, resizable : true},
            {field : 'zdyjjh03',title : '03-重大研究计划',width : 120,sortable : true},
            {field : 'cjzdyjjh0301',title : '03-01-参加重大研究计划',width : 150,sortable : true},
            {field : 'msfound04',title : '04-面上基金',width : 80,sortable : true},
            {field : 'cjmsfound0401',title : '04-01-参加面上基金',width : 150,sortable : true},
            {field : 'qnfound05',title : '05-青年基金',width : 80,sortable : true},
            {field : 'cjqnfound0501',title : '05-01-参加青年基金',width : 150,sortable : false},
            {field : 'zxfound06',title : '06-专项基金',width : 80,sortable : true},
            {field : 'cjzxfound0601',title : '06-01-参加专项基金',width : 150,sortable : true},
            {field : 'jcqn07', title : '07-杰青', width : 60, resizable : true},
            {field : 'cjjqrw0701',title : '07-01-参加杰青任务',width : 150,sortable : true},
            {field : 'yq08',title : '08-优青',width : 60,sortable : true},
            {field : 'qtyth09',title : '09-其他-研讨会',width : 100,sortable : true},
            {field : 'mission86311',title : '11-863任务',width : 80,sortable : true},
            {field : 'dfrw12',title : '1A-重点研发计划',width : 100,sortable : true},
            {field : 'mission973',title : '12-973任务',width : 80,sortable : true},
            {field : 'kjzc13',title : '13-科技支撑',width : 80,sortable : true},
            {field : 'jctjpt14',title : '14-基础条件平台',width : 150,sortable : true},
            {field : 'kjzdzx15',title : '15-科技重大专项',width : 150,sortable : true},
            {field : 'jcxgzzx16',title : '16-基础性工作专项',width : 150,sortable : true},
            {field : 'gyxhyzx17',title : '17-公益性行业专项',width : 150,sortable : true},
            {field : 'rkx18',title : '18-软科学',width : 80,sortable : true},
            {field : 'mission19',title : '19-其他国家任务',width : 120,sortable : true},
            {field : 'gjhz21',title : '41-国际合作',width : 80,sortable : true},
            {field : 'jg21',title : '21-JG',width : 60,sortable : true},
            {field : 'mission22',title : '22-部委任务',width : 80,sortable : true},
            {field : 'mission23',title : '23-地方任务',width : 80,sortable : true},
            {field : 'hxwt24',title : '24-横向委托',width : 80,sortable : true},
            {field : 'qywt25',title : '25-企业委托',width : 80,sortable : true},
            {field : 'yzd31',title : '31-院重大',width : 80,sortable : true},
            {field : 'yfx32',title : '32-院方向',width : 80,sortable : true},
            {field : 'yyd33',title : '33-院先导',width : 80,sortable : true},
            {field : 'yzdbs34',title : '34-院重点部署',width : 120,sortable : true},
            {field : 'ysts35',title : '35-院STS',width : 80,sortable : true},
            {field : 'yqtzdsys36',title : '36-院其他-重点实验室',width : 180,sortable : true},
            {field : 'yrc37',title : '37-院人才',width : 80,sortable : true},
            {field : 'yqt39',title : '39-院其他',width : 80,sortable : true},
            {field : 'gjhz41',title : '41-国际合作',width : 80,sortable : true},
            {field : 'slyqy51',title : '51-所自主部署',width : 100,sortable : true},
            //{field : 'szfound52',title : '52-所长基金',width : 80,sortable : false},
            {field : 'other99',title : '99-其他',width : 60,sortable : true},
            {field : 'notcount',title : '不计',width : 60,sortable : true}
        ]],
        toolbar : '#tb'
    });
};

var userRole = $('#urole').val();
if(userRole === '管理员'){
    var calcBtn =document.getElementById("totalProjectCalc");
    calcBtn.style.visibility="visible";
} else {
    var calcBtn =document.getElementById("totalProjectCalc");
    calcBtn.style.visibility="hidden";
}

//项目统计更新计算
var projectTotalCalculate = function(){
    var msg = "您确定要执行项目统计更新计算吗？\n\n请确认！";
    if (confirm(msg)==true){
        //触发后端存储过程
        $.blockUI({  message: '<h1>正在更新项目统计,请稍候！</h1>',
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
            url : 'projectstatisticAction!calculateProject.action',
            dataType: "json",
            success : function(data) {
                if(data.success){
                    $.unblockUI({  message: '<h1>项目统计更新完成！</h1>'});
                    setTimeout($.unblockUI, 2000);

                    //重新读取表数据
                    $('#center_table').datagrid('reload');
                }else{
                    $.unblockUI({  message: '<h1>项目统计更新失败！</h1>'});
                    setTimeout($.unblockUI, 2000);
                }

            },
            error : function(xhr, ajaxOptions, thrownError) {
                $.blockUI({  message: '<h1>项目统计更新失败！</h1>'});
                setTimeout($.unblockUI, 2000);
            }
        });
    }

}

var proStaExportExcel = function(){
    downloadExcel('projectstatisticAction!downloadExcel.action');
};

var proStaAdvancedSearch = function(){
    $('#proStaAdvancedSearch_dialog').dialog('open');
};

var ps_adv_search_btn_ok = function(){
    var username = $('#ps_username').textbox('getValue');
    var domain = $('#ps_domain').textbox('getValue');
    var search_usercode=$('#ps_usercode').textbox('getValue');

    $('#center_table').datagrid({
        onBeforeLoad : function(param){
            param.s_username = username;
            param.s_domain = domain;
            param.search_usercode = search_usercode;
        }
    });

    $('#proStaAdvancedSearch_dialog').dialog('close');
};

var ps_adv_search_btn_cancel = function(){
    $('#proStaAdvancedSearch_dialog').dialog('close');
};