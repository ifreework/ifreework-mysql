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
<body >
	<div class="warpe" id="card">
		<div class="banner">
			<div class="swipe">
					<a href="#"><img src="${imagePath }/mobile/pic8.jpg"
							alt="" /></a>
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
			
			<div class="user_card_info">
				<div class="user_img">
					<img alt="用户未上传头像" src="${user.imgPath }">
				</div>
				<div class="user_info">
					<span class="username">${user.personName }</span>
					<span><label><i class="fa fa-phone"></i></label> ${user.phone }</span>
					<span><label><i class="fa fa-envelope"></i></label> ${user.email }</span>
				</div>
			</div>
			
			
		</div>
		<div class="main">
			<div class="signature">
				<span class="signature-title">
					个性签名
				</span>
				<div class="signature-text">
					${user.remarks }
				</div>
			</div>
		</div>
		
		<div class="main card_center">
			<div class="company">
				<span class="icon"><i class="fa fa-hospital-o"></i></span>
				<span>${user.company.companyName }</span>
			</div>
			<div class="address">
				<span class="icon"><i class="fa  fa-map-marker"></i></span>
				<span>地址</span>
			</div>
		</div>
		
	    <div class="nav">
		    <ul>
		        <li>
		            <a href="Property.html">
		                <p><img src="${imagePath}/mobile/icon_wy.png" class="animated bounceIn"></p>
		                <span>名片</span>
		            </a>
		        </li>
		        <li>
		            <a href="#">
		                <p><img src="${imagePath}/mobile/icon_er.png" class="animated rotateIn"></p>
		                <span>名片二维码</span>
		            </a>
		        </li>
		        <li>
		            <a href="Repair.html">
		                <p><img src="${imagePath}/mobile/icon_jk.png" class="animated bounceIn"></p>
		                <span>锁屏壁纸</span>
		            </a>
		        </li>
		        <li>
		            <a href="#">
		                <p><img src="${imagePath}/mobile/icon_sqh.png" class="animated rotateIn"></p>
		                <span>分享</span>
		            </a>
		        </li>
		    </ul>
		</div>
		
	    <div class="navside footer">
	        <ul class="sc_list">
	            <li>
	                <a href="#">
	                    <p><i class="fa fa-phone"></i></p>
	                </a>
	            </li>
	            <li>
	                <a href="#">
	                    <p><i class="fa  fa-comments-o"></i></p>
	                </a>
	            </li>
	            <li>
	                <a href="#">
	                    <p><i class="fa fa-comment-o"></i></p>
	                </a>
	            </li>
	        </ul>
    </div>

	</div>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
</html>