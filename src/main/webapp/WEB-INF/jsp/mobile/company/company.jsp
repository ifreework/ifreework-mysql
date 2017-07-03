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
<title>${user.company.companyName  }介绍</title>
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
				<a href="#">${user.company.companyName }介绍<i
					class=" icon-angle-down"></i>
				</a>
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
		</div>

		<div class="content">
			<div class="content-slide">
				<div class="interest_list">
					<ul>
						<c:forEach items="${list }" var="c">
							<li class="animated bounceInRight"><a
								href="${contextPath }/mobile/companyInfo?m=${user.userId}&p=${c.introductionId}"> 
								<img src="${c.image }">
									<div class="list_r">
										<p>
											<span>【关于${user.company.companyName }】${c.title }</span>
										</p>
										<p class="fin_p">
											<span class="info">
												<span class="info_user">${user.personName }</span>
												<span><fmt:formatDate value="${c.createTime}" pattern="MM-dd hh:mm"/></span>
											</span>
											<span class="page_view right">
												阅读(${c.pageView })
											</span>
										</p>
									</div>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

		<div class="footer">
			<a href="javascript:M.alert('该功能暂未开通')">免责声明</a>
			<a href="javascript:M.alert('该功能暂未开通')">关于我们</a>
			<a href="javascript:M.alert('该功能暂未开通')">用户中心</a>
		</div>
	</div>

	<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
</body>
</html>