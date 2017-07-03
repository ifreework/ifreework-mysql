<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/mobile/include/head.jsp"%>


<html lang="en">  
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>产品列表</title>
</head>
<body>
	<div class="warpe">
		<div class="head head2">
	        <a href="#" class="return" style="color: #5f5e5e;"><i class="fa fa-chevron-left"></i></a>
	        <a href="#" class="search" style="color: #5f5e5e;"><i class="fa fa-search"></i> </a>
	        <input type="text" placeholder="产品名称" class="animated bounceIn zz_search" value="${ pd.q }">
	    </div>
	    <div class="main" style="display: none;">
	        <div class="search_txt">
	            <div class="search_t clear_border">
		                        热门产品类型
	            </div>
	            <div class="search_list">
	                <ul>
	                    <li class="animated rotateIn">
	                        <a href="#">标签1</a>
	                    </li>
	                    <li class="animated fadeInRight">
	                        <a href="#">标签2</a>
	                    </li>
	                    <li class="animated bounceIn">
	                        <a href="#">标签3</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	    
		<div class="center">
			<div class="activity">
				<c:if test="${fn:length(productList) == 0}">
					<div style="text-align: center;">
						没有查询到相关的产品信息
					</div>
				</c:if>
				
				<ul>
					<c:forEach items="${productList }" var="p">
					<li class="animated fadeInDown"><a href="${contextPath }/mobile/productInfo?p=${p.productId}&m=${user.userId}"> <img
							src="${p.image}">
							<div class="activity_txt">
								<p>${p.productName}</p>
								<p>
									<span></span><span class="color_y">￥${p.price }</span>
								</p>
							</div>
					</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="footer load_more" style="display: none;">
			<div>
				<a href="#">查看更多</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
<script type="text/javascript">
$(".search").on("click",function(){
	var q = $(".zz_search").val();
	window.location.href = '${contextPath}/mobile/productList?m=${user.userId}&q=' + q;
});

</script>
</html>