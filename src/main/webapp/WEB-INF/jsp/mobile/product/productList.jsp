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
	<div class="warpe">
		<div class="head head2">
	        <a href="#" class="return" style="color: #5f5e5e;"><i class="fa fa-chevron-left"></i></a>
	        <a href="#" class="search" style="color: #5f5e5e;"><i class="fa fa-search"></i> </a>
	        <input type="text" placeholder="产品名称" class="animated bounceIn zz_search">
	    </div>
	    <div class="main">
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
				<ul>
					<li class="animated fadeInDown"><a href="#"> <img
							src="${imagePath }/mobile/banner.jpg">
							<div class="activity_txt">
								<p>时尚多用女包包</p>
								<p>
									<span></span><span class="color_y">￥1880</span>
								</p>
							</div>
					</a></li>
					<li class="animated fadeInUp"><a href="#"> <img
							src="${imagePath }/mobile/pic4.jpg">
							<div class="activity_txt">
								<p>多功能易拉罐组装产品</p>
								<p>
									<span></span><span class="color_y">￥998</span>
								</p>
							</div>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="footer load_more">
			<div>
				<a href="#">查看更多</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
</html>