/******************************************* Init Operator ******************************************/
//处理键盘事件  
function doKey(e){  
    var ev = e || window.event;//获取event对象  
    var obj = ev.target || ev.srcElement;//获取事件源  
    var t = obj.type || obj.getAttribute('type');//获取事件源类型  
    var readOnly = obj.readOnly || obj.getAttribute('readOnly');//获取事件源类型  
    var disabled = obj.disabled || obj.getAttribute('disabled');//获取事件源类型  
    if(ev.keyCode == 8 && (t != "password" && t != "text" && t != "textarea" || readOnly || disabled)){  
        return false;  
    }  
}  

$(document).ready(function() {
	//1.设置权限界面
	var rolename = $('#urole').val();	
	init(rolename);
	
	//2.默认添加首页论文内容
	addModuleContentByName('thesis', 'thesis', "thesis");
	
	//3.点击时候触发事件
	$("#menuContainer").tabs({
		   onSelect:function(title,index){
		        if(index == 2){
		        	addLeftContentPanel('layout/west.jsp');
		        } else {
		        	removeLeftContentPanel();
		        }
		        changeModule('tab' + index + '-1');
		        setTimeout(resizeLayout, 500);
		   }
	});
	
	//4.初始化设置屏幕大小
	setTimeout(resizeLayout, 500);
	
	//禁止后退键 作用于Firefox、Opera  
	document.onkeypress=doKey; 
	//禁止后退键  作用于IE、Chrome  
	document.onkeydown=doKey;  
});

var init = function (roleID){
	
	//1.设置登录权限
	if(roleID == "科研人员"){
		showTab(0);
		showTab(1);
		hideTab(2);
		//hideTab(1);
		$("#menuContainer").tabs("select", 0);
	} else if(roleID == "秘书"){
		showTab(0);
		hideTab(1);
		hideTab(2);
		$("#menuContainer").tabs("select", 0);
	} else if(roleID === "管理员"){
		showTab(0);
		showTab(1);
		showTab(2);
		$("#menuContainer").tabs("select", 0);
	} else {
		//Todo
		throw Exception("无效登录角色:" + roleID);
	}
	
	$(window).resize(function(){
		 setTimeout(resizeLayout, 500);
	}); 
};

var resizeLayout = function(){
    var height = document.body.clientHeight;
    var headerHeight = $("#header").height();
    var footerHeight = $("#footer").height();
    var layoutHeight = height-headerHeight - footerHeight - 5;
    //$('#container').attr({height:layoutHeight});
    $('#container').layout({  
    	height: layoutHeight  
    }); 
};

var hideTab = function(index){
	var tabOption = $("#menuContainer").tabs('getTab', index).panel('options').tab;
	tabOption.hide();
};

var showTab = function(index){
	var tabOption = $("#menuContainer").tabs('getTab', index).panel('options').tab;
	tabOption.show();
};
/******************************************* Left Tree Panel Operator ******************************************/
function addLeftContentPanel(url){
	$('#contentLayout').layout('add',{
	    region: 'west',
	    width: 230,
	    title: '',
	    split: true,
	    href: url
	});
}

function removeLeftContentPanel(){
	$("#contentLayout").layout("remove","west");
}

/******************************************* Content Panel Operator ******************************************/
function addModuleContentByJsp(moduleName, jspName, scripts){
	removeModuleContentPanel();
	removeModulesScript();
	
	
	var url = "performance/modules/" + moduleName + "/" + jspName;
	
	$('#contentLayout').layout('add',{
	    region: 'center',
	    width: 180,
	    title: '',
	    split: true,
	    href: url,
	    onLoad:function(){
	    	try
	    	{
	    		//超时已经跳转到login页面，但因为是模块加载只能局部显示，需要整体调整
	    		if($("#reg-form").length > 0){
	    			window.location.href = $('#contextPath').val() + "/loginAction!toLogin.action";
	    		} else {
	    			addModulesScript(moduleName, scripts);
	    		}
	    		
	    	} catch(e){
	    		alert(e);
	    	}
	    	
	    },
	    OnError : function(){
	    	alet('happy');
	    }
	});
}

function addModuleContentByName(moduleName, name, scripts){
	removeModuleContentPanel();
	removeModulesScript();
	
	//thesisAction!toThesis.action
	var url = name + "Action!to" +  name.charAt(0).toUpperCase() + name.slice(1) + ".action"; 
	
	$('#contentLayout').layout('add',{
	    region: 'center',
	    width: 180,
	    title: '',
	    split: true,
	    href: url,
	    onLoad:function(){
	    	try
	    	{
	    		//超时已经跳转到login页面，但因为是模块加载只能局部显示，需要整体调整
	    		if($("#reg-form").length > 0){
	    			window.location.href = $('#contextPath').val() + "/loginAction!toLogin.action";
	    		} else {
	    			addModulesScript(moduleName, scripts);
	    		}
	    	} catch(e){
	    		alert(e);
	    	}
	    	
	    }
	});
}

function removeModuleContentPanel(){
	$("#contentLayout").layout("remove","center");
	$("#contextPath").next().nextAll().remove();
}

/******************************************* Main Framework Operator ******************************************/
function changeModule(id){
	var bopts = $('#' + id).linkbutton('options');
	
	//1.change selected button style
	$('#menuContainer').find('.l-btn').each(function(e){
		$(this).linkbutton({selected:false});
	});
	 $('#' + id).linkbutton({selected:true});
	 
	 if(bopts.name.indexOf(".jsp") > -1){
		 addModuleContentByJsp(bopts.moduleName, bopts.name, bopts.scripts);
	 } else {
		 addModuleContentByName(bopts.moduleName, bopts.name, bopts.scripts);
	 }
}

function addModulesScript(moduleName, scripts){
	if(scripts !== undefined && scripts !== ""){
		var scriptArray = [];
		var scriptNames = scripts.split(",");
		for(var index in scriptNames){
			var script = scriptNames[index];
			if(script !== undefined || script !== "")
				//scriptCount++;
				//loadScript("js_myselfv2v2/modules/" + moduleName + "/" + script + ".js", moduleLoadcallback);
				scriptArray.push("js_myselfv2/modules/" + moduleName + "/" + script + ".js" + "?v1.0.1");
		}
		
		LazyLoad.js(scriptArray, function(){
			moduleLoaded();
		});
	}
	
	
}

//var scriptCount = 0;
//var moduleLoadcallback = function(){
//	scriptCount--;
//	if(scriptCount === 0){
//		alert('test');
//		moduleLoaded();
//	}
//};

function removeModulesScript()
{
	var scripts = document.getElementsByTagName('script');
	var head = document.getElementsByTagName('head')[0];
	for(var index=scripts.length; index > 0; index--){
		var script = scripts[index-1];
		if(script.src === undefined)
			continue;
		if(script.src.indexOf('modules') > 0){
			head.removeChild(script);
		}
	}
}

function addPatchScript(scripts){
	if(scripts !== undefined && scripts !== ""){
		var scriptNames = scripts.split(",");
		for(var index in scriptNames){
			var script = scriptNames[index];
			if(script !== undefined || script !== "")
				loadScript(script);
		}
	}
}

function loadScript(url, callback)
{
    // Adding the script tag to the head as suggested before
    var head = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = url;

    // Then bind the event to the callback function.
    // There are several events for cross browser compatibility.
    script.onreadystatechange = callback;
    script.onload = callback;

    // Fire the loading
    head.appendChild(script);
}
