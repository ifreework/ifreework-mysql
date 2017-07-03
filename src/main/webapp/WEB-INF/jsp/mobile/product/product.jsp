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
<title>产品介绍</title>
</head>
<body>
	<div class="warpe">
		<div class="head">
			<a href="javascript:history.go(-1)" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			产品类型 <a href="${contextPath}/mobile/productType?m=${user.userId}&c=${user.companyId}" class="search"><i
				class="fa fa-reorder"></i> </a>
		</div>
		
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
			</div>

		<div class="title" style="text-align: center;">
			<input type="text" placeholder="搜索标题"
				class="animated bounceIn zz_search"> <i class="fa fa-search"
				style="margin-left: 1rem;"></i>
		</div>

		<c:forEach items="${productList }" var="productType">
		<div class="center">
			<div class="pictitle">
				<span class="picbg"></span> <span>${productType.productTypeName }</span>
				<span class="showmore" data-id="${productType.productTypeId }">查看更多&gt;</span>
			</div>
			<div class="activity">
				<ul>
					<c:forEach items="${ productType.productList}" var="product">
					<li class="animated fadeInDown"><a href="${contextPath }/mobile/productInfo?p=${product.productId}&m=${user.userId}"> <img
							src="${product.image}">
							<div class="activity_txt">
								<p>${product.productName}</p>
								<p>
									<span></span><span class="color_y">￥${product.price }</span>
								</p>
							</div>
					</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		</c:forEach>
	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
<script type="text/javascript">
	$(".fa-search").on("click",function(){
		var q = $(".zz_search").val();
		window.location.href = '${contextPath}/mobile/productList?m=${user.userId}&q=' + q;
	});
	
	$(".showmore").on("click",function(){
		var id = $(this).data("id");
		window.location.href = '${contextPath}/mobile/productList?m=${user.userId}&t=' + id;
	});
</script>
</html>