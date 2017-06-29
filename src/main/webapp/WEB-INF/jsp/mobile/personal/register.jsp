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
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<meta content="telephone=no" name="format-detection" />
		<title>快速建站-</title>
		<link rel="stylesheet" href="${cssPath}/weui/weui.css" />
		<link href="${cssPath}/mobile/main.css" rel="stylesheet" type="text/css">
		<link href="${cssPath}/mobile/style.css" rel="stylesheet" type="text/css">
		<link href="${cssPath}/mobile/shake.css" rel="stylesheet" type="text/css">
		<link href="${cssPath}/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${cssPath}/animate.min.css" rel="stylesheet" type="text/css">
		<link href="${cssPath}/mobile/idangerous.swiper.css" rel="stylesheet" type="text/css">

		<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
		<script src="${ jsPath }/bootstrap/validation/bootstrapValidator.js"></script>
		<script src="${ jsPath }/bootstrap/validation/i18n/zh_CN.js"></script>
		<script type="text/javascript" src="${jsPath }/mobile/wo.js"></script>
	</head>

	<body>
		<div class="warpe" id="register">

			<div class="head">
				<a href="javascript:history.go(-1)" class="return"><i class="fa fa-angle-left"></i> </a> 开通工作室
			</div>

			<div class="comnav">
				<div class="bannerside">
					<a href="#"><img src="${imagePath}/mobile/pic2.jpg"></a>
				</div>
			</div>

			<div class="main">
				<div class="part part2 margin_top" style="margin-bottom: 0rem;">
					<form id="saveForm" action="">
					<ul>
						<li class="animated fadeInRight">
							<select name="companyId" >
								<option value="">请选择工作室</option>
								<c:forEach items="${companys }" var="company">
								<option value="${company.companyId }">${company.companyName }</option>
								</c:forEach>
							</select>
							<label class="puff_left">公司：</label>
							<div class="yzmdiv">
								<i class="fa fa-angle-right"></i>
							</div>
						</li>
						<li class="animated fadeInRight"><input type="text" placeholder="请输入姓名" name="personName"> <label class="puff_left">姓名：</label>
						</li>
						<li class="animated fadeInRight"><input type="text" placeholder="请输入微信号" name="weixin"> <label class="puff_left">微信号：</label>
						</li>
						<li class="animated fadeInLeft"><input type="text" placeholder="请输入手机号码" name="phone"> <label class="puff_left">手机号码：</label>
						</li>
						<li class="animated fadeInLeft"><input type="text" placeholder="请输入邮箱" name="email"> <label class="puff_left">邮箱：</label>
						</li>
						<li class=" animated fadeInRight"><input type="text" placeholder="" name="validateCode">
							<div class="yzmdiv">
								<a href="#">获取验证码</a>
							</div> <label class="puff_left">验证码：</label></li>
					</ul>
					</form>
				</div>
				<div class="id_bth inersest_bth animated bounceIn" style="margin-top: 1rem;">
		                <a href="javascript:void(0)" id="register-button">注册</a>
		            </div>
			</div>
		</div>

	</body>
	<script type="text/javascript">
	var bootstrapValidator = $("#saveForm").bootstrapValidator().data('bootstrapValidator');
	bootstrapValidator.addField("personName",{
        notEmpty: {
            message: '请填写您的姓名'
        }
    });
	$("#register-button").on("click",function(){
//		bootstrapValidator.validate();
	});
	</script>
</html>