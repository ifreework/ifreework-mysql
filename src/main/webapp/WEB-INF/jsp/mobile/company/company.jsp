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
<title>${user.company }的公司介绍</title>
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

			<div class="title_name">
				<a href="my_home9.html">${user.company.companyName }工作室介绍<i
					class=" icon-angle-down"></i>
				</a>
			</div>
			
			<div class="mess_iocn animated rotateIn">
				<a href="message.html"> <i
					class="fa fa-angle-left puff_left circle"></i>
				</a>
			</div>
			<div class="mess_iocn mess_iocn2 animated rotateIn">
				<a href="message.html"> <i class="fa fa-home puff_left"></i>
				</a>
			</div>
		</div>

		<div class="main">
			<div class="content-slide">
				<div class="interest_list">
					<ul>
						<li class="animated bounceInLeft"><a
							href="Information_xq.html"> <img
								src="${imagePath }/mobile/banner.jpg">
								<div class="list_r">
									<p>
										<span>社会组织</span>
									</p>
									<p class="fin_p">该组织发布的信息标题，显示最新一条...</p>
									<i class=" icon-angle-right"></i>
								</div>
						</a></li>
						<li class="animated bounceInRight"><a
							href="Information_xq.html"> <img
								src="${imagePath }/mobile/banner.jpg">
								<div class="list_r">
									<p>
										<span>社会组织</span>
									</p>
									<p class="fin_p">该组织发布的信息标题，显示最新一条...</p>
									<i class=" icon-angle-right"></i>
								</div>
						</a></li>
						<li class="animated bounceInLeft"><a
							href="Information_xq.html"> <img
								src="${imagePath }/mobile/banner.jpg">
								<div class="list_r">
									<p>
										<span>社会组织</span>
									</p>
									<p class="fin_p">该组织发布的信息标题，显示最新一条...</p>
									<i class=" icon-angle-right"></i>
								</div>
						</a></li>
						<li class="animated bounceInRight"><a
							href="Information_xq.html"> <img
								src="${imagePath }/mobile/banner.jpg">
								<div class="list_r">
									<p>
										<span>社会组织</span>
									</p>
									<p class="fin_p">该组织发布的信息标题，显示最新一条...</p>
									<i class=" icon-angle-right"></i>
								</div>
						</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="footer">
			<div class="id_bth">
				<a href="#">免责声明</a> <a href="#">关于我们</a> <a href="#">用户中心</a>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
</html>