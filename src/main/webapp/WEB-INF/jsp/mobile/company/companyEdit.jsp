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
<title>文章发布</title>
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
			<input type="text" placeholder="输入公司介绍标题">
			<textarea rows="8" placeholder="输入公司介绍详情吧"></textarea>
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
	</div>
</body>
</html>