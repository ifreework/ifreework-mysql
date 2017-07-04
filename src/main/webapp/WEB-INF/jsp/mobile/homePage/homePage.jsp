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
<title>${user.personName }的${user.company.companyName }工作室</title>
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
				<a href="my_home9.html">${user.personName }的${user.company.companyName }工作室<i
					class=" icon-angle-down"></i>
				</a>
			</div>
			
			<div class="mess_iocn animated rotateIn">
				<a href="javascript:history.go(-1)"> <i class="fa fa-angle-left puff_left circle"></i>
				</a>
			</div>
			<div class="mess_iocn mess_iocn2 animated rotateIn">
				<a href="${contextPath}/mobile/homePage?m=${user.userId}"> <i class="fa fa-home puff_left"></i>
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
					<a href="javascript:void(0)" id="weixinImg">
						<p style="color: #f2a218;">我的微信</p> <span
						class="animated fadeInLeft">${user.weixin }</span> <img
						src="${imagePath }/mobile/icon_jkzx.png" class="animated rotateIn">
					</a>
				</div>
			</div>
		</div>
		<div class="nav">
			<ul>
				<li><a href="${contextPath }/mobile/card?m=${user.userId}">
						<p>
							<img src="${imagePath }/mobile/icon_mj.png"
								class="animated bounceIn">
						</p> <span>我的名片</span>
				</a></li>
				<li><a href="${contextPath }/mobile/company?m=${user.userId}">
						<p>
							<img src="${imagePath }/mobile/icon_hj.png"
								class="animated rotateIn">
						</p> <span>公司介绍</span>
				</a></li>
				<li><a href="${contextPath }/mobile/product?m=${user.userId}">
						<p>
							<img src="${imagePath }/mobile/icon_jkjz.png"
								class="animated bounceIn">
						</p> <span>产品展示</span>
				</a></li>
				<li><a href="${contextPath }/mobile/articleList?m=${user.userId}">
						<p>
							<img src="${imagePath }/mobile/icon_km.png"
								class="animated rotateIn">
						</p> <span>我的优荐</span>
				</a></li>
				<li><a href="javascript:M.alert('该功能暂未开通')">
						<p>
							<img src="${imagePath }/mobile/icon_zn.png"
								class="animated bounceIn">
						</p> <span>我的相册</span>
				</a></li>
				<li><a href="javascript:M.alert('该功能暂未开通')">
						<p>
							<img src="${imagePath }/mobile/icon_sq.png"
								class="animated rotateIn">
						</p> <span>给我留言</span>
				</a></li>
			</ul>
		</div>

		<div class="footer">
			<a href="javascript:M.alert('该功能暂未开通')">免责声明</a> <a href="javascript:M.alert('该功能暂未开通')">关于我们</a> <a href="javascript:M.alert('该功能暂未开通')">用户中心</a>
		</div>

		<div class="navside">
			<ul>
				<li class="margin_left animated bounceInLeft"><a href="${contextPath }/homePage?m=${user.userId}" class="navside_hover">
						<p>
							<i class="fa fa-home"></i>
						</p> <span>首页</span>
				</a></li>
				<li class="animated bounceInRight"><a href="${contextPath}/mobile/personal?m=${user.userId }">
						<p>
							<i class="fa fa-user"></i>
						</p> <span>个人中心</span>
				</a></li>
				<li class="animated bounceInLeft"><a href="${contextPath}/mobile/articleList?m=${user.userId }">
						<p>
							<i class="fa fa-th-large"></i>
						</p> <span>微分享</span>
				</a></li>
			</ul>
		</div>


	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	
	<script type="text/javascript">
		var openId = $.cookie("openId");
		var userId = "${user.userId}";
		if(openId != userId){
			$(".navside").hide();
		}
		
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${jssdk.appId}', // 必填，公众号的唯一标识
		    timestamp: ${jssdk.timestamp}, // 必填，生成签名的时间戳
		    nonceStr: '${jssdk.nonceStr}', // 必填，生成签名的随机串
		    signature: '${jssdk.signature}',// 必填，签名，见附录1
		    
		    jsApiList: [
		        'checkJsApi',
		        'chooseImage',
		        'previewImage'
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
		});
	</script>
</body>
</html>