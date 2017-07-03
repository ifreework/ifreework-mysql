<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/mobile/include/head.jsp"%>

<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>${user.personName }的微名片</title>
</head>
<body >
	<div class="warpe" id="card">
		<div class="banner">
			<div class="swipe">
					<a href="#"><img src="${imagePath }/mobile/pic8.jpg"
							alt="" /></a>
			</div>
			<div class="mess_iocn animated rotateIn">
				<a href="javascript:history.go(-1)"> <i
					class="fa fa-angle-left puff_left circle"></i>
				</a>
			</div>
			<div class="mess_iocn mess_iocn2 animated rotateIn">
				<a href="${contextPath}/mobile/homePage?m=${user.userId}"> <i class="fa fa-home puff_left"></i>
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
					${user.remarks == null || user.remarks == '' ? '尚未填写个性签名' : user.remarks}
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
		            <a href="javascript:M.alert('该功能暂未开通')">
		                <p><img src="${imagePath}/mobile/icon_wy.png" class="animated bounceIn"></p>
		                <span>名片</span>
		            </a>
		        </li>
		        <li>
		            <a href="javascript:M.alert('该功能暂未开通')">
		                <p><img src="${imagePath}/mobile/icon_er.png" class="animated rotateIn"></p>
		                <span>名片二维码</span>
		            </a>
		        </li>
		        <li>
		            <a href="javascript:M.alert('该功能暂未开通')">
		                <p><img src="${imagePath}/mobile/icon_jk.png" class="animated bounceIn"></p>
		                <span>锁屏壁纸</span>
		            </a>
		        </li>
		        <li>
		            <a href="javascript:M.alert('该功能暂未开通')">
		                <p><img src="${imagePath}/mobile/icon_sqh.png" class="animated rotateIn"></p>
		                <span>分享</span>
		            </a>
		        </li>
		    </ul>
		</div>
		
	    <div class="navside footer">
	        <ul class="sc_list">
	            <li>
	                <a href="tel:${user.phone }">
	                    <p><i class="fa fa-phone"></i></p>
	                </a>
	            </li>
	            <li>
	                <a href="javascript:void(0)">
	                    <p><i class="fa  fa-comments-o"></i></p>
	                </a>
	            </li>
	            <li>
	                <a href="javascript:M.alert('该功能暂未开通')">
	                    <p><i class="fa fa-comment-o"></i></p>
	                </a>
	            </li>
	        </ul>
    </div>

	</div>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
<script type="text/javascript">

</script>
</html>