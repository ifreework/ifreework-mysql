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
	<div class="warpe" id="personal">
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

		<div class="interest_list" style="border-bottom: 1rem #ededed solid;">
			<ul>
				<li class="animated bounceInLeft clear_border"><a
					href="user_admin.html"> <img src="${user.imgPath }">
						<div class="list_r">
							<p>
								<span>${user.personName }</span>
							</p>
							<p>三生</p>
							<div class="useradmin">
								<p>
									VIP使用截止日期<i class="fa fa-angle-right"></i>
								</p>
								<span class="color_r">2018/01/01</span>
							</div>
						</div>
				</a>
			</ul>
		</div>

		<div class="nav margin_top">
			<ul>
				<li><a href="Property.html">
						<p>
							<img src="${imagePath }/mobile/icon_hj.png"
								class="animated bounceIn">
						</p> <span>微主页</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_mj.png"
								class="animated rotateIn">
						</p> <span>微名片</span>
				</a></li>
				<li><a href="Repair.html">
						<p>
							<img src="${imagePath }/mobile/icon_jkjz.png"
								class="animated bounceIn">
						</p> <span>产品展示</span>
				</a></li>
				<li><a href="#">
						<p>
							<img src="${imagePath }/mobile/icon_zd.png"
								class="animated rotateIn">
						</p> <span>公司介绍</span>
				</a></li>
			</ul>
		</div>
		
		<div class="Area">
			<ul>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_zn.png"
						class="animated bounceIn"> <span>我的相册</span>
				</a></li>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_sq.png"
						class="animated rotateIn"> <span>留言管理</span>
				</a></li>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_xq.png"
						class="animated bounceIn"> <span>推广奖励</span>
				</a></li>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_wy.png"
						class="animated bounceIn"> <span>帮助中心</span>
				</a></li>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_fk.png"
						class="animated rotateIn"> <span>联系客服</span>
				</a></li>
				<li><a href="#"> <img src="${imagePath }/mobile/icon_ts.png"
						class="animated bounceIn"> <span>个人信息</span>
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
</body>
</html>