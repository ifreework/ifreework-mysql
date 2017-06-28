<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />  
<meta content="telephone=no" name="format-detection" />
<title>${user.personName }的个人中心</title>
<link href="${cssPath}/mobile/main.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/style.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/mobile/shake.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/animate.min.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/idangerous.swiper.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath }/mobile/wo.js"></script>
</head>
<body style="background-color: #fff !important;">
	<div class="warpe">
		<div class="head">
			<a href="#" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			产品类型 <a href="community_sign.html" class="search"><i
				class="fa fa-reorder"></i> </a>
		</div>
		
		<div class="tabs my_tab sqh_tab xx_tab vertical">
			<a href="#" hidefocus="true" class="active">保健品</a> 
			<a href="#" hidefocus="true">饰品</a> 
			<a href="#" hidefocus="true">冲印类</a> 
			<a href="#" hidefocus="true">女士包包</a> 
			<a href="#" hidefocus="true">品质文胸</a>
			<a href="#" hidefocus="true">厨具</a>
			<a href="#" hidefocus="true">精品区</a>
			<a href="#" hidefocus="true">服装类</a>
			<a href="#" hidefocus="true">个人护理专区</a>
			<a href="#" hidefocus="true">床上用品</a>
			<a href="#" hidefocus="true">其他</a>
		</div>
		
		<div class="main tabs_content">
	        <div class="search_txt">
	            <div class="search_t clear_border">
		                        热门产品类型
	            </div>
	            <div class="search_list">
	                <ul>
	                    <li class="animated rotateIn">
	                        <a href="#">标签1</a>
	                    </li>
	                    <li class="animated fadeInRight">
	                        <a href="#">标签2</a>
	                    </li>
	                    <li class="animated bounceIn">
	                        <a href="#">标签3</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>