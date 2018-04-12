/**
 * 人员选择
 */

// user 
var searchObjAuthor = function(author) {
	focusId = author;
	openDialog('admin_thesis_addDialog_author');
	
	//若不为非本所人员
	if($('#' + focusId + 'code').val() !== '-1'){
		$('#noself').prop("checked", false);
		$('#outsider').textbox('setValue', '');
		$('#outsider').textbox("readonly", true);
	}
	
	//若输入框已存在人员
	if($('#' + focusId).textbox('getValue') !== '' ){
		//若为本所人员，定位到相关条目
		if($('#' + focusId + 'code').val() !== '-1'){
			showbackAuthorFunc($('#' + focusId + 'code').val());
			return;
		}else{
			//若为非本所人员，回显到非本所人员输入框，并设置非本所人员开关
			$('#noself').prop("checked", true);
			$('#outsider').val($('#' + focusId).textbox('getValue'));
			$('#outsider').textbox("readonly", false);
			return;
		}
	}
	
	$('#name').textbox('setValue', '');
	searchAuthorFunc();
};

//other author
var searchOtherAuthor = function(otherauthor) {
	focusId = otherauthor;
	openDialog('thesis_addDialog_otherauthor');
	$('#otherauthor_name').textbox('setValue', '');
	searchOtherAuthorFunc();
	$('#thesis_addotherauthorDialog_datagrid').datagrid('clearChecked').datagrid('clearSelections');
	//$('#otherauthor_list').datalist('loadData', []);  //clear all datagrid
	$('#otherperson_result_datagrid').datagrid('loadData', []);
	
	//回显otherauthor
	var otherauthorValue = $('#' + focusId).textbox('getValue'),
		otherauthorCode = $('#' + focusId + 'code').val();
	var otherauthorArray = otherauthorValue.split(','),
		otherauthorCodeArray = otherauthorCode.split(',');
	
	var resultList = $('#otherperson_result_datagrid').datagrid('getData');
	for(var i = 1; i < otherauthorArray.length - 1; i++){
		var contains = false;
		for (var j = 0; j < resultList.rows.length; j++) {
			var row = resultList.rows[j];
			if (row.personname === otherauthorArray[i] && row.personcode === otherauthorCodeArray[i]) {
				contains = true;
			}
		}
		
		if (contains === false) {
			$('#otherperson_result_datagrid').datagrid('appendRow', {
				personname : otherauthorArray[i],
				personcode : otherauthorCodeArray[i]
			});
		}
	}
	
	$('#otherperson_result_datagrid').datagrid('enableDnd');
};

// 获取人员
function searchAuthorFunc() {
	$('#thesis_addauthor_datagrid').datagrid({
		url : $('#contextPath').val() + '/personAction!datagrid.action',
		fit : false,
		queryParams : {
			name : $('#name').val(),
			usercode : $('#name').val()
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
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		columns : [ [ {
			field : 'personid',
			title : 'ID',
			width : 50,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 100,
			sortable : true
		}, {
			field : 'usercode',
			title : '编号',
			width : 50
		}, {
			field : 'lab',
			title : '研究室',
			width : 100
		}, {
			field : 'domain',
			title : '领域',
			width : 100
		} ] ],
		width : 400,
		height : 340,
		autoRowHeight : false
	});
	
	$('#thesis_addauthor_datagrid').datagrid('clearChecked');
}

//获取人员
function showbackAuthorFunc(usercode) {
	$('#thesis_addauthor_datagrid').datagrid({
		url : $('#contextPath').val() + '/personAction!findPerson.action',
		fit : false,
		queryParams : {
			usercode : usercode
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
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : false,
		columns : [ [ {
			field : 'personid',
			title : 'ID',
			width : 50,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名称',
			width : 100,
			sortable : true
		}, {
			field : 'usercode',
			title : '编号',
			width : 50
		}, {
			field : 'lab',
			title : '研究室',
			width : 100
		}, {
			field : 'domain',
			title : '领域',
			width : 100
		} ] ],
		width : 400,
		height : 340,
		autoRowHeight : false,
		onLoadSuccess : function(data){
			if(data.rows.length ===1){
				var row = data.rows[0];
				if(row.usercode === usercode){
					//设置勾选
					$(this).datagrid('checkRow', 0);
				}
			}
		}
	});
}


//人员选择确认
function author_btn_ok() {
	// 若非本所人员勾选且不为空，则只处理非本所人员，非本所人员authorcode统一为-1
	if ($('#noself').prop("checked") == true) {
//		if ($('#outsider').val()) {
//			$('#' + focusId + '').textbox('setValue', $('#outsider').val());
//			$('#' + focusId + 'code').val(-1);
//			$('#admin_thesis_addDialog_author').dialog('close');
//		} else {
//			// 提醒用户，若为非本所人员，则需填写正确的作者名称
//			$.messager.alert("提醒", "非本所人员，请填写作者姓名，不能为空！");
//		}
		$('#' + focusId + '').textbox('setValue', $('#outsider').val());
		if ($('#outsider').val()) {
			$('#' + focusId + 'code').val(-1);
		}else{
			$('#' + focusId + 'code').val('');
		}
		$('#admin_thesis_addDialog_author').dialog('close');
	} else {
		// 选择本所人员
		var rows = $('#thesis_addauthor_datagrid').datagrid('getChecked');
		if (rows.length == 1) {
			$('#' + focusId + '').textbox('setValue', rows[0].name);
			$('#' + focusId + 'code').val(rows[0].usercode);
		}else{
			$('#' + focusId + '').textbox('setValue', '');
			$('#' + focusId + 'code').val('');
		}

		$('#admin_thesis_addDialog_author').dialog('close');
	}
}

// 人员选择取消
var author_btn_cancel = function() {
	$('#admin_thesis_addDialog_author').dialog('close');
};

//其它作者界面读取函数
var searchOtherAuthorFunc = function() {
	$('#thesis_addotherauthorDialog_datagrid').datagrid({
		url : $('#contextPath').val() + '/personAction!datagrid.action',
		fit : false,
		queryParams : {
			name : $('#otherauthor_name').val(),
			usercode : $('#otherauthor_name').val()
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

//其它作者选择,添加到列表
var otherauthor_btn_add = function() {
	var datagridValues = $('#otherperson_result_datagrid').datagrid('getData');
	// 获取当前需要添加的作者列表
	// 1.获取本所人员
	var rows = $('#thesis_addotherauthorDialog_datagrid').datagrid('getChecked');
	for (var i = 0; i < rows.length; i++) {
		var contains = false;
		for (var j = 0; j < datagridValues.rows.length; j++) {
			var row = datagridValues.rows[j];
			if (row.personcode === rows[i].usercode && row.personname === rows[i].name) {
				contains = true;
			}
		}
		if (contains === false) {
			$('#otherperson_result_datagrid').datagrid('appendRow', {
				personcode : rows[i].usercode,
				personname : rows[i].name
			});
		}
	}
	// 2.获取非本所人员
	var outsider = $('#outsider_other').textbox('getValue');
	if (outsider !== '') {
		contains = false;
		for (j = 0; j < datagridValues.rows.length; j++) {
			var row = datagridValues.rows[j];
			if (outsider === row.text) {
				contains = true;
			}
		}
		if (contains === false) {
			$('#otherperson_result_datagrid').datagrid('appendRow', {
				personcode : -1,
				personname : outsider
			});
			$('#outsider_other').textbox('clear');
		}
	}
	
	$('#otherperson_result_datagrid').datagrid('enableDnd');
};

//其它作者删除,从列表删除
var otherauthor_btn_remove = function() {
	var rows = $('#otherperson_result_datagrid').datagrid('getSelections');
	for (var i = rows.length - 1; i >= 0; i--) {
		var index = $('#otherperson_result_datagrid').datagrid('getRowIndex', rows[i]);
		$('#otherperson_result_datagrid').datagrid('deleteRow', index);
	}
	
	$('#otherperson_result_datagrid').datagrid('enableDnd');
};

//其它作者选择完毕后确认
var otherauthor_btn_confirm = function() {
	// 获取datalist
	var rows = $('#otherperson_result_datagrid').datagrid('getData').rows;
    if(rows.length === 0){
		$('#' + focusId).textbox('setValue', '');
		$('#' + focusId + 'code').val('');
        $('#thesis_addDialog_otherauthor').dialog('close');
        return;
    }

	var username = '', usercode = '';
	for (var i = 0; i < rows.length; i++) {
		username += ',';
		username += rows[i].personname;
		usercode += ',';
		usercode += rows[i].personcode;
	}

	username += ',';
	usercode += ',';

	$('#' + focusId).textbox('setValue', username);
	$('#' + focusId + 'code').val(usercode);

	$('#thesis_addDialog_otherauthor').dialog('close');
};

// 其它作者界面关闭，取消选择
var otherauthor_btn_cancel = function() {
	$('#thesis_addDialog_otherauthor').dialog('close');
};

// 需要填写非本所人员
function onOutsiderCheck() {
	if ($('#noself').prop("checked") == true) {
		$('#outsider').textbox("readonly", false);
		// 禁用本所人员表的勾选功能

	} else {
		$('#outsider').textbox("readonly", true);
	}
}


