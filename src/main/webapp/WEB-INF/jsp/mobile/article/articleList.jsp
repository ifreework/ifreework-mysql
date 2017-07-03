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
<title>我的优荐</title>

<script>
	var loading = false; //属否数据正在加载
	$(document).ready(function() {
		//控制tab选项滚动
		var elm2 = $('.xx_tab');
		var startPos = $(elm2).offset().top;
		$.event.add(window, "scroll", function() {
			var p = $(window).scrollTop(),scrollHeight = $(document).height(),windowHeight = $(this).height(),positionValue = (p + windowHeight) - scrollHeight;

			/**
			if(p < 0 && !loading){ //下拉刷新
				var start = $(".swiper-slide-active .page");
				start.val(1);
				reload();
			}
			**/
			
			if(positionValue > 0 && !loading){ //上拉加载
				var noMore = $(".swiper-slide-active ul .no-more");
				if(noMore.length == 0){
					var start = $(".swiper-slide-active .page");
					start.val(parseInt(start.val()) + 1);
					reload();
				}
			}
			
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
			<a href="javascript:history.go(-1)" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			我的优荐
			<a href="Information_search.html" class="search"><i class="fa fa-search"></i> </a>
		</div>

		<div class="tabs my_tab sqh_tab xx_tab">
			<a href="javascript:void(0)" hidefocus="true" class="active" data-id="">头条</a> 
			<c:forEach items="${ dList }" var="d" varStatus="dStatus">
			<a href="javascript:void(0)" hidefocus="true" data-id="${d.dictionaryCode }">${d.dictionaryName }</a> 
			</c:forEach>
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
			<c:forEach items="${ dList }" var="d" varStatus="dStatus">
				<div class="swiper-slide">
					<div class="content-slide">
						<div class="interest_list">
							<ul>
							</ul>
							<input type="hidden" class="page" value="1">
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>

		<div class="footer">
			<a href="#">免责声明</a> <a href="#">关于我们</a> <a href="#">用户中心</a>
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
	<script type="text/javascript"
		src="${jsPath }/mobile/idangerous.swiper.min.js"></script>
	<script type="text/javascript">
		var openId = $.cookie("openId");
		var userId = "${user.userId}";
		if(openId != userId){
			$(".navside").hide();
		}
		
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
			
			var li = $(".swiper-slide-active ul li");
			if(li.length == 0){
				reload();
			}
		})
		$(".tabs a").click(function(e) {
			e.preventDefault()
		})
		
		var length = 10; //每页显示条数
		function reload(){
			loading = true;
			var typeId = $(".active").data("id");
			var start = $(".swiper-slide-active .page").val();
			if(start == 1){
				$(".swiper-slide-active ul").empty();
			}
			M.ajax({
				url:'${ contextPath }/mobile/articleList/query',
				data:{start:(start - 1)*length,length:length,articleTypeId:typeId},
				async:false,
				success:function(data){
					createElement(data);
					loading = false;
				}
			});
		}
		
		function createElement(data){
			var html = '<li class="animated bounceInRight">\
							<a href="${contextPath }/mobile/articleInfo?m=${user.userId}&p=[ARTICLEID]">\
								<img src="[IMAGE]">\
								<div class="list_r">\
									<p>\
										<span>[TITLE]</span>\
									</p>\
									<p class="fin_p">\
										<span class="info">\
											<span class="info_user">${user.personName }</span>\
											<span>[CREATETIME]</span>\
										</span>\
										<span class="page_view right">\
											阅读([PAGEVIEW])\
										</span>\
									</p>\
								</div>\
						</a></li>';
						
			for(var i = 0; i < data.data.length; i++){
				var article = data.data[i];
				var li = html.replace("[ARTICLEID]",article.articleId)
							 .replace("[IMAGE]",article.image)
							 .replace("[TITLE]",article.title)
							 .replace("[CREATETIME]",article.createTime)
							 .replace("[PAGEVIEW]",article.pageView);
				$(".swiper-slide-active ul").append(li);
			}
			if(data.data.length < length){
				var li = '<li class="no-more"><div style="text-align: center;">没有更多数据了</div></li>';
				$(".swiper-slide-active ul").append(li);
			}
		}
		reload();
		
	</script>
</body>
</html>
