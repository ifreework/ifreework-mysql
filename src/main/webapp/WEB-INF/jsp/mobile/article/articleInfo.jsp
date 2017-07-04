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
<title>${article.title }</title>
</head>
<body>
<div class="warpe">
    <div class="head head2">
        <a href="javascript:history.go(-1)" class="return" style="color: #5f5e5e;"><i class="fa fa-chevron-left"></i></a>
        <a href="${contextPath}/mobile/homePage?m=${user.userId}" id="sc_bth" class="search" style="color: #5f5e5e;"><i class="fa fa-home"></i> </a>
    </div>
    <div class="main">
        <div class="finance">
            <ul>
                <li class="animated fadeIn clear_border">
                    <a>
                        <p class="fin_title">${article.title }</p>
                        <p>
                        	<span class="puff_left info_user" data-id="${ user.userId }">${user.personName }</span>
                        	<span class="puff_left time"><fmt:formatDate value="${article.createTime}" pattern="MM-dd hh:mm"/></span>
                        	<span class="puff_right changeToMine" id="changeToMine">修改成我的</span> 
                        </p>
                        <div class="fin_txt">
                        	${article.content }
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    
    <div class="main small_card">
		<div class="small_card_img">
				<a href="#"><img src="${imagePath }/mobile/pic8.jpg"
						alt="" /></a>
		</div>
		
		<div class="small_card_info">
			<div class="user_img">
				<img alt="用户未上传头像" src="${user.imgPath }">
			</div>
			<div class="user_info">
				<span class="username">${user.personName }</span>
				<span><label><i class="fa fa-envelope"></i></label> ${user.email }</span>
			</div>
			<div class="work_room">
				<span><i class="fa fa-home"></i></span>
				<span>工作室</span>
			</div>
			<div class="card_option">
				<a href="tel:${user.phone }"><i class="fa fa-phone"></i>一键拨号</a>
				<a href="#"><i class="fa fa-comments-o"></i>添加好友</a>
				<a href="#">我的工作室</a>
			</div>
		</div>
	</div>
	
	<div class="footer">
		<a href="javascript:M.alert('该功能暂未开通')">免责声明</a> <a href="javascript:M.alert('该功能暂未开通')">关于我们</a> <a href="javascript:M.alert('该功能暂未开通')">用户中心</a>
	</div>
</div>

</body>
<script type="text/javascript">
	var openId = $.cookie("openId");
	var userId = "${user.userId}";
	if(openId == userId){
		$("#changeToMine").hide();
	}
	
	$("#changeToMine").on("click",function(){
		var p = M.getRequestParamByName("p");
		var forward = window.location.href;
		forward = forward.substring(0,forward.indexOf("?"));
		
		forward += "?p=" + p;
		var f = encodeURI(forward);
		
		window.location.href = "${contextPath}/mobile/changeToMine?f=" + f;
	});
	
	$(".info_user").on("click",function(){
		window.location.href = "${contextPath}/mobile/homePage?m=${user.userId}";
	});
</script>
</html>