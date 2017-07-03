<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/mobile/include/head.jsp"%>
<!DOCTYPE html>

<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<meta content="telephone=no" name="format-detection" />
		<title>快速建站-之麻开门</title>
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
						<li class="animated fadeInRight form-group"> <input type="text" class="form-control" placeholder="请输入姓名" name="personName"> <label class="puff_left">姓名：</label>
						</li>
						<li class="animated fadeInRight"><input type="text" placeholder="请输入微信号" name="weixin"> <label class="puff_left">微信号：</label>
						</li>
						<li class="animated fadeInLeft"><input type="text" placeholder="请输入手机号码" name="phone"> <label class="puff_left">手机号码：</label>
						</li>
						<!-- 
						<li class="animated fadeInLeft"><input type="text" placeholder="请输入邮箱" name="email" id="email"> <label class="puff_left">邮箱：</label>
						</li>
						<li class=" animated fadeInRight"><input type="text" placeholder="" name="validateCode" id="validateCode">
							<div class="yzmdiv">
								<a href="#" id="identifyingCodeBtn">获取验证码<span id="identifyingCodeTime"></span></a>
							</div> <label class="puff_left">验证码：</label></li>
						-->
						<li class="animated fadeInLeft" style="text-align: center;"><label style="position: inherit;"><input type="checkbox" value="checked" id="checkReg" name="checkReg" style="display: inline-block; width: 1.2rem;height: 1.2rem;">我已阅读并同意<a href="#">《之麻开门服务协议》</a></label>
						</li>
					</ul>
					</form>
				</div>
				<div class="id_bth inersest_bth animated bounceIn" style="margin-top: 1rem;">
		        	<a href="javascript:void(0)" id="register-button">注册</a>
		        </div>
			</div>
		</div>
		<div class="weui-toptips weui-toptips_warn js_tooltips" ></div>
		
	</body>
	<script type="text/javascript">
	
	var weixin = "${user.weixin}";
	if(weixin != ""){
		var html = '<div style="text-align: center;padding: 2rem;font-size: 1.6rem;">您已注册该系统，稍后页面将自动跳转...</div>'
		$("body").html(html);
		setTimeout(function(){
			forward();
		},2000);
	}
	
	var bootstrapValidator = $("#saveForm").bootstrapValidator({
		container: '.weui-toptips',
		verbose : false,
		fields: {
			 companyId: {
                validators: {
                    notEmpty: {
                        message: '请选择您的公司或者品牌'
                    }
                }
            },
            personName: {
                validators: {
                    notEmpty: {
                        message: '请填写您的姓名'
                    }
                }
            },
            weixin: {
                validators: {
                    notEmpty: {
                        message: '请选择您的微信帐号'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '请填写您的电话号码'
                    },
                    phone:{
                    	country: 'CN'
                    }
                }
            },
            email: {
                validators: {
                    emailAddress:{
                    }
                }
            },
            /**
            validateCode: {
                validators: {
                    notEmpty: {
                        message: '请填写您收到的验证码'
                    }
                }
            },
            **/
            checkReg: {
                validators: {
                    notEmpty: {
                        message: '请先阅读同意之麻开门服务协议'
                    }
                }
            }
     	}
	}).data('bootstrapValidator');
	
	
	var identifyingCode;
	$("#identifyingCodeBtn").on("click",function(){
		bootstrapValidator.validateField("email");
		if(bootstrapValidator.isValidField("email")){
			M.ajax({
				url : "${ contextPath }/mobile/register/identifyingCode",
				data:{email:$("#email").val()},
				success:function(param){
					identifyingCode = param.identifyingCode;
					M.alert("验证码已经发送到您的邮箱，请注意查收");
				}
			});
		}
	});
	
	$("#register-button").on("click",function(){
		/**
		var validateCode = $("#validateCode").val();
		if( validateCode != identifyingCode ){
			M.alert("验证码输入错误，请重新输入");
			return;
		}
		**/
		bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var openId = $.cookie("openId");
    		var data = $("#saveForm").serializeJson();
    		data.userId = openId;
    		M.ajax({
				url : "${ contextPath }/mobile/register/save",
				data:data,
				success:function(param){
					if(param.result === SUCCESS){
						M.alert("注册成功",null,function(){
							forward();
						});
					}else{
						M.alert("数据异常，注册失败");
					}
				}
			});
    	}
	});
	
	function forward(){
		var openId = $.cookie("openId");
		var f = M.getRequestParamByName("f");
		if(f == null || f== ""){
			f = "${contextPath}/mobile/personal?m=" + openId;
		}else{
			f = decodeURI(f);
		}
		window.location.href = f;
	}
	</script>
</html>