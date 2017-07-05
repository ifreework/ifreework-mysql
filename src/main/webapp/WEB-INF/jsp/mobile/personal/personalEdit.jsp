<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/mobile/include/head.jsp"%>

<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<meta content="telephone=no" name="format-detection" />
		<title></title>
	</head>

	<body>
		<div class="weui-toptips weui-toptips_warn js_tooltips" ></div>
		<div class="warpe">
		    <div class="head">
		        <a href="javascript:history.go(-1)" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
		        	账户管理
		    </div>
		    <div class="inter_add">
		    	<form id="saveForm" action="">
		        <ul>
		            <li class="animated bounceInLeft" id="imagePath">
		                <a href="javascript:void(0)" >
		                    <span class="puff_left" style="margin-top: 1.5rem;">头像</span>
		                    <span class="puff_right"><img src="${user.imgPath }"> </span>
		                    <i class="fa fa-angle-right" style="margin-top: 1rem;"></i>
		                </a>
		            </li>
		             <li class="animated bounceInLeft" id="weixinImg">
		                <a href="javascript:void(0)" >
		                    <span class="puff_left" style="margin-top: 1.5rem;">微信二维码</span>
		                    <span class="puff_right"><img src="${user.weixinImg }"> </span>
		                    <i class="fa fa-angle-right" style="margin-top: 1rem;"></i>
		                </a>
		            </li>
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">姓名</span>
		                    <span class="puff_right"><input type="text" value="${user.personName }" name="personName" placeholder="请输入您的姓名"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">手机号</span>
		                    <span class="puff_right"><input type="text" value="${user.phone }" name="phone" placeholder="请输入您的手机号"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">微信号</span>
		                    <span class="puff_right"><input type="text" value="${user.weixin }" name="weixin" placeholder="请输入您的微信号"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">邮箱</span>
		                    <span class="puff_right"><input type="text" value="${user.email }" name="email" placeholder="请输入您的邮箱"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		            
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">我的标签</span>
		                    <span class="puff_right"><input type="text" value="${user.label }" name="label" placeholder="如:健康顾问"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		            
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">我的地址</span>
		                    <span class="puff_right"><input type="text" value="${user.deailAddress }" name="deailAddress" placeholder="如:江苏省南京市XXX路XX号"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		             <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">个性签名</span>
		                    <span class="puff_right"><input type="text" value="${user.remarks }" name="remarks" placeholder="个性签名将显示在您的名片中"></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
		        </ul>
		        </form>
		    </div>
		     <div class="inter_add">
		        <ul>
		            <li class="animated bounceInRight">
		                <a href="javascript:void(0)">
		                    <span class="puff_left">VIP截止日期</span>
		                    <span class="puff_right"><fmt:formatDate value="${user.vipEndTime}" pattern="yyyy/MM/dd"/></font></span>
		                    <i class="icon-angle-right"></i>
		                </a>
		            </li>
	            </ul>
		    </div>
		    <div class="id_bth inersest_bth animated bounceIn">
		        <a href="javascript:void(0)" id="save">保存</a>
		    </div>
		</div>

		<script type="text/javascript" src="${jsPath}/mobile/slide_wap.js"></script>
		<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		
	</body>
	<script type="text/javascript">
		var openId = $.cookie("openId");
		var userId = "${user.userId}";
		
		var userImgChange = false;
		var weixinImgChange = false;
		
		if(openId != userId){
			window.location.href = "${contextPath}/mobile/homePage?m=${user.userId }";
		}
		
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${jssdk.appId}', // 必填，公众号的唯一标识
		    timestamp: ${jssdk.timestamp}, // 必填，生成签名的时间戳
		    nonceStr: '${jssdk.nonceStr}', // 必填，生成签名的随机串
		    signature: '${jssdk.signature}',// 必填，签名，见附录1
		    
		    jsApiList: [
		        'checkJsApi',
		        'chooseImage',
		        'previewImage'
		      ]
		});
		wx.ready(function () {
			 var images = {
			     localId: [],
			     serverId: []
			  };
			 
			  $('#imagePath,#weixinImg').on("click",function () {
				  var _that = this;
			    wx.chooseImage({
			    	count: 1, // 默认9
			    	sizeType: ['compressed'],
			      	success: function (res) {
				        wx.getLocalImgData({
				            localId: res.localIds[0], // 图片的localID
				            success: function (res) {
				                var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
				                $(_that).find("img").attr("src",localData);
				            }
				        });
			      	}
			    });
			  });
			  
			  $('#imagePath img,#weixinImg img').on("click",function (event) {
				    event.stopPropagation();
				  	var url = $(this).attr("src");
				    wx.previewImage({
				    	current: url, // 当前显示图片的http链接
				    	urls: [url] // 需要预览的图片http链接列表
				    });
			  });
		});
		
		var bootstrapValidator = $("#saveForm").bootstrapValidator({
			container: '.weui-toptips',
			verbose : false,
			fields: {
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
	            }
	     	}
		}).data('bootstrapValidator');
		
		$("#save").on("click",function(){
			bootstrapValidator.validate();
	    	if(bootstrapValidator.isValid()){
	    		var openId = $.cookie("openId");
	    		var data = $("#saveForm").serializeJson();
	    		data.userId = "${user.userId}";
	    		data.imgPath = $("#imagePath img").attr("src");
	    		data.weixinImg = $("#weixinImg img").attr("src");
	    		M.ajax({
					url : "${ contextPath }/mobile/personal/save",
					data:data,
					success:function(param){
						if(param.result === SUCCESS){
							M.alert("数据保存成功");
						}else{
							M.alert("数据异常，注册失败");
						}
					}
				});
	    	}
		});
		
	</script>

</html>