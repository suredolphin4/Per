<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>地理所绩效管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jslibv2/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslibv2/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslibv2/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>  </head>
<link rel="stylesheet" href="css/loginStyles.css" type="text/css"></link>  
<link rel="stylesheet" href="css/axure_rp_page.css" type="text/css"></link>
  
<script type="text/javascript">
//<![CDATA[
$(function(){
        /*
        *思路大概是先为每一个required添加必填的标记，用each()方法来实现。
        *在each()方法中先是创建一个元素。然后通过append()方法将创建的元素加入到父元素后面。
        *这里面的this用的很精髓，每一次的this都对应着相应的input元素，然后获取相应的父元素。
        *然后为input元素添加失去焦点事件。然后进行用户名、邮件的验证。
        *这里用了一个判断is()，如果是用户名，做相应的处理，如果是邮件做相应的验证。
        *在jQuery框架中，也可以适当的穿插一写原汁原味的javascript代码。比如验证用户名中就有this.value，和this.value.length。对内容进行判断。
        *然后进行的是邮件的验证，貌似用到了正则表达式。
        *然后为input元素添加keyup事件与focus事件。就是在keyup时也要做一下验证，调用blur事件就行了。用triggerHandler()触发器，触发相应的事件。
        *最后提交表单时做统一验证
        *做好整体与细节的处理
        */
        //如果是必填的，则加红星标识.
        $("form :input.required").each(function(){
            var $required = $("<strong class='high'> </strong>"); //创建元素
            $(this).parent().append($required); //然后将它追加到文档中
        });
         //文本框失去焦点后
        $('form :input').blur(function(){
             var $parent = $(this).parent();
             $parent.find(".formtips").remove();
             //验证用户名
             if( $(this).is('#username') ){
                    if( this.value=="" || this.value.length < 6 ){
                        var errorMsg = '请输入至少6位的用户名.';
                        $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                    }else{
                        var okMsg = '输入正确.';
                        $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
                    }
             }
             //验证邮件
             if( $(this).is('#email') ){
                if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
                      var errorMsg = '请输入正确的E-Mail地址.';
                      $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                }else{
                      var okMsg = '输入正确.';
                      $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
                }
             }
        }).keyup(function(){
           $(this).triggerHandler("blur");
        }).focus(function(){
             $(this).triggerHandler("blur");
        });//end blur

        
        //提交，最终验证。
         $('#send').click(function(){
                $("form :input.required").trigger('blur');
                var numError = $('form .onError').length;
                if(numError){
                    return false;
                } 
                alert("注册成功,密码已发到你的邮箱,请查收.");
         });

        //重置
         $('#res').click(function(){
                $(".formtips").remove(); 
         });
})
//]]>
</script>
</head>
  
  <body>
  
  
  <form method="post" action="">
    <div class="int">
        <input type="text" id="username" class="required" />
    </div>
    <div class="int">
        <input type="text" id="email" class="required" />
    </div>
    <div class="int">
        <input type="text" id="personinfo" />
    </div>
    <div class="sub">
        <input type="submit" value="提交" id="send"/><input type="reset" id="res"/>
    </div>
</form>  
  </body>
</html>
