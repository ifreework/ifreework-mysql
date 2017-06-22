<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false"%>
<%@ page import="com.ifreework.common.constant.Constant" %>
<%@ page import="com.ifreework.entity.system.Config" %>
<%@ page import="com.ifreework.common.manager.SysTemConfigManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title><%=SysTemConfigManager.get(Config.SYSTEM_NAME) %></title>
<link rel="shortcut icon" href="${ imagePath }/system/favicon.ico"
	type="image/x-icon" />
	
<!-- bootstrap核心样式 -->	
<link rel="stylesheet" href="${ cssPath }/bootstrap.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/ifreework.min.css"></link>

<!-- icon字体样式 -->
<link rel="stylesheet" href="${ cssPath }/font-awesome.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/weather-icons.min.css"  />
<link rel="stylesheet" href="${ cssPath }/typicons.min.css"  />
	
<!-- 自定义核心样式 -->
<link rel="stylesheet" href="${ cssPath }/style.css"  />
<link rel="stylesheet" href="${ cssPath }/base.css"  />
<link rel="stylesheet" href="${ cssPath }/index/index.css"  />

<!-- jquery,bootstrap核心 -->
<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ jsPath }/jquery/jquery.cookie.js"></script>
<script src="${ jsPath }/bootstrap/bootstrap.min.js"></script>

<!-- jquery,bootstrap插件 -->
<script src="${ jsPath }/bootstrap/bootbox/bootbox.js"></script>
<!-- -->
<script src="${ jsPath }/cloud.js"></script>  
<!-- 部分自定义方法 -->
<script src="${ jsPath }/ifreework.js"></script>
<script type="text/javascript" src="${ jsPath }/browser.js"></script>

<script>
var SUCCESS = "<%=Constant.SUCCESS %>"; //前后台统一成功标志
var ERROR = "<%=Constant.ERROR %>";  //前后台错误标志
var FAILED = "<%=Constant.FAILED %>"; //前后台失败标志

$(function(){
	$().ready(function(){
		//根据浏览器大小，初始化登录框的位置
		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
			$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		})  
		
		//点击登录按钮，执行登录操作
		$("#login").click(function(event) {
			event.preventDefault();
			submitUserData();
		});
	});
	
	//登录操作
	function submitUserData(){
		if (validate()) {
			W.ajax({
				url : "${contextPath}/login",
				data:{username:$("#username").val(),password:$("#password").val()},
				success : function(result) {
					if(result.result == SUCCESS){
						changeCookie($("#cookieBox").get(0).checked);
						location.href="${contextPath}/main"
					}else{
						bootbox.unload();
						bootbox.alert(result.msg);
					}
				}
			});
		}
	}
	
	//验证用户名密码是否为空
	function validate() {
		if (W.isNull($("#username").val())) {
			bootbox.alert("请输入您的用户名。");
			return false;
		}
		if (W.isNull($("#password").val())) {
			bootbox.alert("请输入您的密码。");
			return false;
		}
		return true;
	}
	
	//显示验证提示信息
	//保存和修改cookie
	function changeCookie(checked){
		if(checked){
			$.cookie("username", $("#name").val());
			$.cookie("password", $("#psd").val());
		}else{
			$.cookie('username', '', { expires: -1 });
			$.cookie('password', '', { expires: -1 });
		}
	}
	
	//如果用户在cookie中保存了数据的话，将自动填充用户名密码
	function loadCookie(){
		var username = $.cookie("username");
		var pwd = $.cookie("password");
		
		if(!W.isNull(username) && !W.isNull(pwd)){
			$("#cookieBox").attr("checked","checked"); 
			$("#username").val(username);
			$("#password").val(pwd);
		}
	}
   
});  

</script>
</head>
<body style="background-color:#1c77ac; background-image:url(${imagePath}/ui/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录iFreeWork权限管理平台</span>    
    <ul>
    <li><a href="javascript:void(0);">帮助</a></li>
    <li><a href="javascript:void(0);">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo">
    	<span class="logo-img"><img alt="" src="${imagePath}/system/Logo_48px.png"></span>
    	<span class="logo-text">iFreeWork权限管理平台</span></span> 
       
    <div class="loginbox">
    
    <ul>
	    <li><input name="username" id="username" type="text" class="loginuser" placeholder="用户名"/></li>
	    <li><input name="password" id="password" type="password" class="loginpwd" placeholder="密码" /></li>
	    <li><input name="" type="button" class="loginbtn" value="登录"  id="login"/>
		    <label>
		    	<input name="cookieBox" type="checkbox" id="cookieBox" />
		      	<span class="cookie-text">记住密码</span>
		    </label>
		    <label>
		    	<a href="#">忘记密码？</a>
		    </label>
	    </li>
    </ul>
    
    </div>
    
    </div>
    
    <div class="loginbm">©2017  735789026@qq.com</div>
    
</body>

</html>
