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
		<div class="head">
			<a href="javascript:history.go(-1)" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			<a href="javascript:void(0)" class="search"><i class="fa fa-plus"></i> </a>
		</div>
		 
		<div class="nav margin_top album">
			<ul>
				<c:forEach items="${albumImageList }" var="image">
				<li>
					<span class="img">
						<img src="${ image.content }"
								class="animated bounceIn">
					</span>
					<span class="text"><i class="fa fa-minus" style="color: red;" onclick="deleteImage(this,'${image.imageId }')"></i></span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</body>
<script type="text/javascript">

	var openId = $.cookie("openId");
	var userId = "${userId}";
	if(openId != userId){
		$(".fa-plus,.fa-delete,.fa-check").hide();
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
		$(".fa-plus").on("click",function(){
			 wx.chooseImage({
		    	count: 9, // 默认9
		    	sizeType: ['compressed'],
		      	success: function (res) {
		      		for(var i = 0; i < res.localIds.length; i++){
		      			wx.getLocalImgData({
				            localId: res.localIds[i], // 图片的localID
				            success: function (res) {
				               var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
				               M.ajax({
				            	   url:"${contextPath}/mobile/albumImage/add",
				            	   data:{albumId:"${albumId}",content:localData},
				            	   success:function(data){
				            		   if(data.result == SUCCESS){
				            			   data.localData = localData;
					       					createImage(data);
					       				}else{
					       					M.alert("数据异常。")
					       					return ;
					       				}
				            	   }
				               });
				            }
				        });
		      		}
		      	}
			});
		});
		function createImage(data){
			var html = '<li>\
							<span class="img">\
								<img src="[localData]"\
										class="animated bounceIn">\
							</span>\
							<span class="text"><i class="fa fa-minus" style="color: red;" onclick="deleteImage(this,\'[imageId]\')"></i></span>\
						</li>';
				html = html.replace("[localData]",data.localData).replace("[imageId]",data.imageId);
			$(".album ul").prepend(html);
			
		}
		
		function previewImage(){
			$(".nav img").unbind();
			$(".nav img").on("click",function(){
				var url = $(this).attr("url");
				var urls = $(".nav img").attr("url");
				 wx.previewImage({
				    	current: url, // 当前显示图片的http链接
				    	urls: urls // 需要预览的图片http链接列表
				    });
			});
		}
		
	});
	

	
	function deleteImage(that_,id){
		M.confirm("数据删除后将无法恢复，确定要删除该照片吗？",null,function(r){
			if(r){
				M.ajax({
					url:"${contextPath}/mobile/albumImage/delete",
					data:{imageId:id},
					success:function(data){
						if(data.result == SUCCESS){
							M.toast("删除成功");
							$(that_).closest("li").remove();
						}else{
							M.alert("数据异常。")
						}
					}
				});
			}
		});
	}
	
</script>
</html>