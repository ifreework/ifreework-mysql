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
			<a href="#" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			产品介绍<a href="second_sign.html" class="plus"><i class="fa fa-plus"></i>
			</a> <a href="community_sign.html" class="search"><i
				class="fa fa-reorder"></i> </a>
		</div>

		<div class="banner">
			<div class="swipe">
				<ul id="slider">
					<li><a href="#"><img src="${imagePath }/mobile/banner.jpg"
							alt="" /></a></li>
					<li><a href="#"><img src="${imagePath }/mobile/banner.jpg"
							alt="" /></a></li>
					<li><a href="#"><img src="${imagePath }/mobile/banner.jpg"
							alt="" /></a></li>
					<li><a href="#"><img src="${imagePath }/mobile/banner.jpg"
							alt="" /></a></li>
					<li><a href="#"><img src="${imagePath }/mobile/banner.jpg"
							alt="" /></a></li>
				</ul>
				<div id="pagenavi">
					<a href="javascript:void(0);" class="active"></a> <a
						href="javascript:void(0);" class=""></a> <a
						href="javascript:void(0);" class=""></a> <a
						href="javascript:void(0);" class=""></a> <a
						href="javascript:void(0);" class=""></a>
				</div>
			</div>
		</div>

		<div class="title" style="text-align: center;">
			<input type="text" placeholder="搜索标题"
				class="animated bounceIn zz_search"> <i class="fa fa-search"
				style="margin-left: 1rem;"></i>
		</div>

		<div class="center">
			<div class="pictitle">
				<span class="picbg"></span> <span>时尚女包</span>
				<span class="showmore">查看更多&gt;</span>
			</div>
			<div class="activity">
				<ul>
					<li class="animated fadeInDown"><a href="#"> <img
							src="${imagePath }/mobile/banner.jpg">
							<div class="activity_txt">
								<p>时尚多用女包包</p>
								<p>
									<span></span><span class="color_y">￥1880</span>
								</p>
							</div>
					</a></li>
					<li class="animated fadeInUp"><a href="#"> <img
							src="${imagePath }/mobile/pic4.jpg">
							<div class="activity_txt">
								<p>多功能易拉罐组装产品</p>
								<p>
									<span></span><span class="color_y">￥998</span>
								</p>
							</div>
					</a></li>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
</html>