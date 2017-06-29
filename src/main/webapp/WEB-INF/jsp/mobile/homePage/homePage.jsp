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
<title>${user.personName }的${user.company.companyName }工作室</title>
<link href="${cssPath}/mobile/main.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/style.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/mobile/shake.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/animate.min.css" rel="stylesheet" type="text/css">
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
				<a href="my_home9.html">${user.personName }的三生工作室<i
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
		<div class="id_nav">
			<div class="id_nav_l puff_left">
				<a href="#"> <span><img src="${user.imgPath}"
						class="animated rotateIn"></span>
					<p>${user.personName}</p>
				</a>
			</div>
			<div class="id_nav_r puff_left">
				<div class="id_nav_rt">
					<a href="#">
						<p>我的电话</p> <a class="animated fadeInLeft"
						href="tel:${user.phone }">${user.phone }</a> <img
						src="${imagePath }/mobile/icon_phone.png"
						class="animated rotateIn">
					</a>
				</div>
				<div class="id_nav_rt clear_border">
					<a href="#">
						<p style="color: #f2a218;">我的微信</p> <span
						class="animated fadeInLeft">${user.weixin }</span> <img
						src="${imagePath }/mobile/icon_jkzx.png" class="animated rotateIn">
					</a>
				</div>
			</div>
		</div>
		<div class="nav">
			<ul>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_mj.png"
								class="animated bounceIn">
						</p> <span>我的名片</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_hj.png"
								class="animated rotateIn">
						</p> <span>公司介绍</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_jkjz.png"
								class="animated bounceIn">
						</p> <span>产品展示</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_km.png"
								class="animated rotateIn">
						</p> <span>我的优荐</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_zn.png"
								class="animated bounceIn">
						</p> <span>我的相册</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_sq.png"
								class="animated rotateIn">
						</p> <span>给我留言</span>
				</a></li>
			</ul>
		</div>

		<div class="footer">
			<div class="id_bth">
				<a href="#">免责声明</a> <a href="#">关于我们</a> <a href="#">用户中心</a>
			</div>
		</div>

		<div class="navside">
			<ul>
				<li class="margin_left animated bounceInLeft"><a href="#"
					class="navside_hover">
						<p>
							<i class="fa fa-home"></i>
						</p> <span>首页</span>
				</a></li>
				<li class="animated bounceInRight"><a href="#">
						<p>
							<i class="fa fa-user"></i>
						</p> <span>个人中心</span>
				</a></li>
				<li class="animated bounceInLeft"><a
					href="Information_index.html">
						<p>
							<i class="fa fa-th-large"></i>
						</p> <span>微分享</span>
				</a></li>
			</ul>
		</div>


	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
	<script type="text/javascript">
		var loginUserId = "${user.userId}";
		var pageUserId = 
	</script>
</body>
</html>