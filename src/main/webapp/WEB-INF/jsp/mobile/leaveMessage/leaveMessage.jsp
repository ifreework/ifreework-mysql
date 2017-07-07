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
<title>之麻开门</title>
</head>
<body>
	<div class="weui-toptips weui-toptips_warn js_tooltips" ></div>
	<div class="warpe leave_message">
		<div class="head">
			<a href="javascript:history.go(-1)" class="return"><i
				class="fa fa-chevron-left"></i> 返回</a> 给我留言
		</div>
		
		<div class="pro_t">
	        <p><a href="${contextPath }/mobile/homePage?m=${user.userId}"><img alt="未上传头像" src="${user.imgPath }"></a></p>
	        <p><span>${user.personName }</span></p>
	        <p>${user.company.companyName }</p>
	    </div>
		
		<div class="tabs my_tab pro_tab">
	        <a href="#" hidefocus="true" class="active" style="width: 50%;">给我留言</a>
	        <a href="#" hidefocus="true" style="width: 50%;">留言列表</a>
	    </div>
	    
		<div class="swiper-container">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide">
	                <div class="content-slide">
	                	<form id="saveForm" action="">
                        <div class="edit_t">
							<textarea rows="8" placeholder="描述一下您的问题吧" name="content"></textarea>
						</div>
						<div class="part part2">
					        <ul>
					            <li>
					                <input type="text" placeholder="您的姓名" name="leaveUser">
					                <label class="puff_left">姓名：</label>
					            </li>
					            <li>
					                <input type="text" placeholder="您的电话" name="leavePhone">
					                <label class="puff_left">电话：</label>
					            </li>
					        </ul>
					    </div>
					    <div class="id_bth inersest_bth animated bounceIn">
					        <a href="javascript:void(0)" id="save">提交</a>
					    </div>
					    <input type="hidden" name="userId" value="${user.userId }">
					    </form>
	                </div>
	            </div>
	            
	            <div class="swiper-slide">
	                <div class="content-slide" style="background: #fff;margin-top: 0;">
	                	<c:forEach items="${leaveMessageList }" var="l">
	                    <div class="postall" style="padding: 1rem;">
			                <div class="post_t">
			                    <img src="${imagePath }/mobile/banner.jpg">
			                    <span>${l.leaveUser }</span>
			                    <label class="puff_right"><fmt:formatDate value="${l.createTime}" pattern="MM-dd hh:mm"/></label>
			                </div>
			                <div class="post_m">
			                    <p class="animated fadeInRight">${l.content}</p>
			                    <span class="span_delete animated fadeInDown"><a href="javascript:void(0)" class="fa-delete" data-id="${l.leaveMessageId}">删除</a> </span>
			                </div>
				        </div>
				        </c:forEach>
				        <c:if test="${fn:length(leaveMessageList) == 0}">
							<div style="text-align: center;">
								暂时没有留言信息
							</div>
						</c:if>
	                </div>
	            </div>
	        </div>
	    </div>
		
	</div>
</body>
	<script type="text/javascript" src="${jsPath}/mobile/common.js"></script>
	<script type="text/javascript"
		src="${jsPath }/mobile/idangerous.swiper.min.js"></script>
<script type="text/javascript">
    var tabsSwiper = new Swiper('.swiper-container',{
        speed:500,
        onSlideChangeStart: function(){
            $(".tabs .active").removeClass('active');
            $(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
        }
    });
    $(".tabs a").on('touchstart mousedown',function(e){
        e.preventDefault()
        $(".tabs .active").removeClass('active')
        $(this).addClass('active')
        tabsSwiper.slideTo( $(this).index() )
    })
    $(".tabs a").click(function(e){
        e.preventDefault()
    })
    
    var openId = $.cookie("openId");
	var userId = "${user.userId}";
	
	var userImgChange = false;
	var weixinImgChange = false;
	
	if(openId != userId){
		$(".fa-delete").hide();
	}
    
	var bootstrapValidator = $("#saveForm").bootstrapValidator({
		container: '.weui-toptips',
		verbose : false,
		fields: {
            content: {
                validators: {
                    notEmpty: {
                        message: '请填写留言内容'
                    }
                }
            },
            leaveUser: {
                validators: {
                    notEmpty: {
                        message: '请输入您的姓名'
                    }
                }
            },
            leavePhone: {
                validators: {
                    notEmpty: {
                        message: '请填写您的电话号码'
                    },
                    phone:{
                    	country: 'CN'
                    }
                }
            }
     	}
	}).data('bootstrapValidator');
    
	$("#save").on("click",function(){
		bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = $("#saveForm").serializeJson();
    		M.ajax({
				url : "${ contextPath }/mobile/leaveMessage/add",
				data:data,
				success:function(param){
					if(param.result === SUCCESS){
						M.alert("提交成功",null,function(){
							window.location.reload();
						});
					}else{
						M.alert("数据异常，提交失败");
					}
				}
			});
    	}
	});
	
	$(".fa-delete").on("click",function(){
		var id = $(this).data("id");
		var postall = $(this).closest(".postall");
		M.confirm("确定要删除该留言吗？",null,function(r){
			if(r){
				M.ajax({
					url : "${ contextPath }/mobile/leaveMessage/delete",
					data:{leaveMessageId:id},
					success:function(param){
						if(param.result === SUCCESS){
							postall.remove();
							M.alert("删除成功");
						}else{
							M.alert("数据异常，删除失败");
						}
					}
				});
			}
		});
    		
	});
</script>

</html>