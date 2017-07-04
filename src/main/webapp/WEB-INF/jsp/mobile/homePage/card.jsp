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
					<span><label><i class="fa fa-comments"></i></label> ${user.weixin }</span>
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
				<a href="${contextPath }/mobile/company?m=${user.userId}">
					<span class="icon"><i class="fa fa-hospital-o"></i></span>
					<span>${user.company.companyName }</span>
				</a>
			</div>
			<div class="address">
				<a href="javascript:void(0)" id="address_a">
					<span class="icon"><i class="fa  fa-map-marker"></i></span>
					<span>地址</span>
				</a>
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
		            <a href="javascript:void(0)" id="wx_card">
		                <p><img src="${imagePath}/mobile/icon_er.png" class="animated rotateIn"></p>
		                <span>微名片二维码</span>
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
	                <a href="javascript:void(0)" id="weixinImg">
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
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	
</body>
<script type="text/javascript">
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${jssdk.appId}', // 必填，公众号的唯一标识
	    timestamp: ${jssdk.timestamp}, // 必填，生成签名的时间戳
	    nonceStr: '${jssdk.nonceStr}', // 必填，生成签名的随机串
	    signature: '${jssdk.signature}',// 必填，签名，见附录1
	    
	    jsApiList: [
	        'checkJsApi',
	        'chooseImage',
	        'previewImage',
	        'openLocation',
	        'getLocation'
	      ]
	});
	wx.ready(function () {
		  $('#weixinImg').on("click",function (event) {
			  	var url = "${user.weixinImg}";
			  	if(!M.isNull(url)){
			  		 wx.previewImage({
					    	current: url, // 当前显示图片的http链接
					    	urls: [url] // 需要预览的图片http链接列表
					    });
			  	}
		  });
		  $('#wx_card').on("click",function (event) {
			  	var url = window.location.href;
			  	url = encodeURI(url);
			  	url = "http://wnsx231.gnway.org/ifreework-mysql/mobile/card/qrCode?m=${user.userId}&url=" + url;
			  	
			  	if(!M.isNull(url)){
			  		 wx.previewImage({
					    	current: url, // 当前显示图片的http链接
					    	urls: [url] // 需要预览的图片http链接列表
					    });
			  	}
		  });
	});
</script>
</html>