<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/mobile/include/head.jsp"%>

<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<meta content="telephone=no" name="format-detection" />
		<title>${user.personName }的${user.company.companyName}工作室</title>
	</head>

	<body>
		<div class="warpe" id="personal">
			<div class="banner">
				<div class="swipe">
					<ul id="slider">
						<li>
							<a href="#"><img src="${imagePath }/mobile/banner.jpg" alt="" /></a>
						</li>
						<li>
							<a href="#"><img src="${imagePath }/mobile/banner.jpg" alt="" /></a>
						</li>
						<li>
							<a href="#"><img src="${imagePath }/mobile/banner.jpg" alt="" /></a>
						</li>
						<li>
							<a href="#"><img src="${imagePath }/mobile/banner.jpg" alt="" /></a>
						</li>
						<li>
							<a href="#"><img src="${imagePath }/mobile/banner.jpg" alt="" /></a>
						</li>
					</ul>
					<div id="pagenavi">
						<a href="javascript:void(0);" class="active"></a>
						<a href="javascript:void(0);" class=""></a>
						<a href="javascript:void(0);" class=""></a>
						<a href="javascript:void(0);" class=""></a>
						<a href="javascript:void(0);" class=""></a>
					</div>
				</div>

				<div class="title_name">
					<a href="javascript:void(0)">${user.personName }的个人中心<i class=" icon-angle-down"></i>
					</a>
				</div>

				<div class="mess_iocn mess_iocn2 animated rotateIn">
					<a href="${contextPath}/mobile/personalEdit"> <i class="fa fa-ellipsis-h puff_left"></i>
					</a>
				</div>
			</div>

			<div class="interest_list" style="border-bottom: 1rem #ededed solid;padding: 0">
				<ul>
					<li class="animated bounceInLeft clear_border" style="background: #fff;padding: 1rem;">
						<a href="javascript:void(0);"> <img src="${user.imgPath }" style="width: 5rem;height: 4rem;">
							<div class="list_r">
								<p>
									<span>${user.personName }</span>
								</p>
								<p>三生</p>
								<div class="useradmin" style="top:0">
									<p>
										VIP使用截止日期<i class="fa fa-angle-right"></i>
									</p>
									<span class="color_r"><fmt:formatDate value="${user.vipEndTime}" pattern="yyyy/MM/dd"/></span>
								</div>
							</div>
						</a>
				</ul>
			</div>

			<div class="nav margin_top">
				<ul>
					<li>
						<a href="${contextPath}/mobile/homePage?m=${user.userId }">
							<p>
								<img src="${imagePath }/mobile/icon_hj.png" class="animated bounceIn">
							</p> <span>微主页</span>
						</a>
					</li>
					<li>
						<a href="${contextPath}/mobile/card?m=${user.userId }">
							<p>
								<img src="${imagePath }/mobile/icon_mj.png" class="animated rotateIn">
							</p> <span>微名片</span>
						</a>
					</li>
					<li>
						<a href="${contextPath}/mobile/product?m=${user.userId }">
							<p>
								<img src="${imagePath }/mobile/icon_jkjz.png" class="animated bounceIn">
							</p> <span>产品展示</span>
						</a>
					</li>
					<li>
						<a href="${contextPath}/mobile/company?m=${user.userId }">
							<p>
								<img src="${imagePath }/mobile/icon_zd.png" class="animated rotateIn">
							</p> <span>公司介绍</span>
						</a>
					</li>
				</ul>
			</div>

			<div class="Area">
				<ul>
					<li>
						<a href="${ contextPath }/mobile/album?m=${user.userId}"> <img src="${imagePath }/mobile/icon_zn.png" class="animated bounceIn"> <span>我的相册</span>
						</a>
					</li>
					<li>
						<a href="javascript:M.alert('该功能暂未开通')"> <img src="${imagePath }/mobile/icon_sq.png" class="animated rotateIn"> <span>留言管理</span>
						</a>
					</li>
					<li>
						<a href="javascript:M.alert('该功能暂未开通')"> <img src="${imagePath }/mobile/icon_xq.png" class="animated bounceIn"> <span>推广奖励</span>
						</a>
					</li>
					<li>
						<a href="javascript:M.alert('该功能暂未开通')"> <img src="${imagePath }/mobile/icon_wy.png" class="animated bounceIn"> <span>帮助中心</span>
						</a>
					</li>
					<li>
						<a href="javascript:M.alert('该功能暂未开通')"> <img src="${imagePath }/mobile/icon_fk.png" class="animated rotateIn"> <span>联系客服</span>
						</a>
					</li>
					<li>
						<a href="javascript:M.alert('该功能暂未开通')"> <img src="${imagePath }/mobile/icon_ts.png" class="animated bounceIn"> <span>个人信息</span>
						</a>
					</li>
				</ul>
			</div>

			<div class="footer">
					<a href="javascript:M.alert('该功能暂未开通')">免责声明</a>
					<a href="javascript:M.alert('该功能暂未开通')">关于我们</a>
					<a href="javascript:M.alert('该功能暂未开通')">用户中心</a>
			</div>

			<div class="navside">
				<ul>
					<li class="margin_left animated bounceInLeft">
						<a href="${contextPath}/mobile/homePage?m=${user.userId }">
							<p>
								<i class="fa fa-home"></i>
							</p> <span>微主页</span>
						</a>
					</li>
					<li class="animated bounceInRight">
						<a href="${contextPath}/mobile/personal?m=${user.userId }" class="navside_hover" >
							<p>
								<i class="fa fa-user"></i>
							</p> <span>个人中心</span>
						</a>
					</li>
					<li class="animated bounceInLeft">
						<a href="${contextPath}/mobile/articleList?m=${user.userId }">
							<p>
								<i class="fa fa-th-large"></i>
							</p> <span>微分享</span>
						</a>
					</li>
				</ul>
			</div>
		</div>

		<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
		<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		
	</body>
	<script type="text/javascript">
		var openId = $.cookie("openId");
		var userId = "${user.userId}";
		if(openId != userId){
			window.location.href = "${contextPath}/mobile/homePage?m=${user.userId }";
		}
	</script>

</html>