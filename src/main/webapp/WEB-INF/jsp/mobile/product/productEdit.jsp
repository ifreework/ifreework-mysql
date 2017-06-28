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
<body>
	<div class="warpe">
		<div class="head">
			<a href="#" class="return"><i class="fa fa-chevron-left"></i></a>
			发布商品 <a href="Interest_search.html"
				class="search animated fadeInRight" style="font-size: 1.4rem;">发布</a>
		</div>
		<div class="edit_t">
			<input type="text" placeholder="输入产品名称">
			<textarea rows="8" placeholder="描述一下想发布的产品信息吧"></textarea>
		</div>
		<div class="main">
			<div class="edit">
				<div class="edit_b">
					<ul>
						<li class="margin_left"><a href="#"> <img
								src="${imagePath }/mobile/banner.jpg">
						</a></li>
						<li><a href="#"> <img
								src="${imagePath }/mobile/banner.jpg">
						</a></li>
						<li><a href="#" class="add_img">+ <input type="file">
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="part2 inter_add">
			<ul>
				<li class="animated bounceInLeft clear_border"><a
					href="my_home.html"> <span class="puff_left">产品类型</span> <i
						class="fa fa-angle-right"></i>
				</a></li>
			</ul>

		</div>
		<div class="part part2">
	        <ul>
	            <li>
	                <input type="text" placeholder="请填写产品规格型号">
	                <label class="puff_left">规格型号：</label>
	            </li>
	            <li>
	                <input type="text" placeholder="请填写产品价格">
	                <label class="puff_left">价格：</label>
	            </li>
	        </ul>
	    </div>
		
	</div>
</body>
</html>