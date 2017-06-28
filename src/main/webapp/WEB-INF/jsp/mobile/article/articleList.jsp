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
<title>${user.personName }的优荐</title>
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
<script>
	$(document).ready(function() {
		//控制tab选项滚动
		var elm2 = $('.xx_tab');
		var startPos = $(elm2).offset().top;
		$.event.add(window, "scroll", function() {
			var p = $(window).scrollTop();
			$(elm2).css('position', ((p) > startPos) ? 'fixed' : 'static');
			$(elm2).css('width', ((p) > startPos) ? '100%' : '');
			$(elm2).css('top', ((p) > startPos) ? '0rem' : '');
			$(elm2).css('opacity', ((p) > startPos) ? '0.95' : '');
		});
	});
</script>


</head>
<body>
	<div class="warpe">
		<div class="head">
			${user.personName }的优荐
			<a href="Information_search.html" class="search"><i class="fa fa-search"></i> </a>
		</div>

		<div class="tabs my_tab sqh_tab xx_tab">
			<a href="#" hidefocus="true" class="active">头条</a> 
			<a href="#" hidefocus="true">事业</a> 
			<a href="#" hidefocus="true">产品</a> 
			<a href="#" hidefocus="true">亲子</a> 
			<a href="#" hidefocus="true">养生</a>
			<a href="#" hidefocus="true">妆扮</a>
			<a href="#" hidefocus="true">资讯</a>
			<a href="#" hidefocus="true">励志</a>
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
		
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="main">
							<div class="finance">
								<ul>
									<li class="animated fadeInRight"><a
										href="Information_xq2.html">
											<p>【必看】这些钱可以省了！总理力督，国务院开出减税降费清单</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-05 23:58</span>
											</p>
									</a></li>
									<li class="animated fadeInLeft"><a
										href="Information_xq2.html">
											<p>"空中巴士"巴铁试验车北戴河测试 可载300人</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-03 18:55</span>
											</p>
									</a></li>
									<li class="animated fadeInRight"><a
										href="Information_xq2.html">
											<p>【必看】这些钱可以省了！总理力督，国务院开出减税降费清单</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-05 23:58</span>
											</p>
									</a></li>
									<li class="animated fadeInLeft"><a
										href="Information_xq2.html">
											<p>"空中巴士"巴铁试验车北戴河测试 可载300人</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-03 18:55</span>
											</p>
									</a></li>
									<li class="animated fadeInRight"><a
										href="Information_xq2.html">
											<p>【必看】这些钱可以省了！总理力督，国务院开出减税降费清单</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-05 23:58</span>
											</p>
									</a></li>
									<li class="animated fadeInLeft"><a
										href="Information_xq2.html">
											<p>"空中巴士"巴铁试验车北戴河测试 可载300人</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-03 18:55</span>
											</p>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="main">暂无内容</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="main">
							<div class="finance">
								<ul>
									<li class="animated fadeInRight"><a href="#">
											<p>【必看】这些钱可以省了！总理力督，国务院开出减税降费清单</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-05 23:58</span>
											</p>
											<p>
												<label> <i class="icon-bookmark-empty"></i> 标签1
												</label><label> <i class="icon-bookmark-empty"></i> 标签2
												</label>
											</p>
									</a></li>
									<li class="animated fadeInLeft"><a href="#">
											<p>"空中巴士"巴铁试验车北戴河测试 可载300人</p>
											<p>
												<span class="puff_left">来自凤凰社区街道办</span><span
													class="puff_right">08-03 18:55</span>
											</p>
											<p>
												<label> <i class="icon-bookmark-empty"></i> 标签1
												</label><label> <i class="icon-bookmark-empty"></i> 标签2
												</label>
											</p>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
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
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="main">暂无内容</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="main">暂无内容</div>
					</div>
				</div>
			</div>
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
	<script type="text/javascript"
		src="${jsPath }/mobile/idangerous.swiper.min.js"></script>
	<script type="text/javascript">
		var tabsSwiper = new Swiper('.swiper-container', {
			speed : 500,
			onSlideChangeStart : function() {
				$(".tabs .active").removeClass('active');
				$(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
			}
		});
		$(".tabs a").on('touchstart mousedown', function(e) {
			e.preventDefault()
			$(".tabs .active").removeClass('active')
			$(this).addClass('active')
			tabsSwiper.slideTo($(this).index())
		})
		$(".tabs a").click(function(e) {
			e.preventDefault()
		})
	</script>
</body>
</html>
