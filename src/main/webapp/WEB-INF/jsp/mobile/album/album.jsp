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
			我的相册
			<a href="javascript:void(0)" class="search"><i class="fa fa-plus"></i> </a>
		</div>
		
		<div class="nav margin_top album">
			<ul>
				<c:forEach items="${albumList }" var="album">
				<li data-id="${album.albumId }">
					<a href="${contextPath}/mobile/albumImage?m=${userId}&a=${album.albumId}">
						<span class="img">
							<img src="${ album.albumImage == null ?  imagePath.concat('/mobile/pic-none.png') : album.albumImage.content }"
									class="animated bounceIn">
						</span>
					</a>
					<span class="text"><font>${album.title }</font><i class="fa fa-edit" onclick="edit(this)"></i><i class="fa fa-minus" style="color: red;" onclick="deleteAlbum(this)"></i></span>
					<span class="input" style="display: none;"><input type="text" value="${album.title }"><i class="fa fa-check" onclick="save(this)"></i></span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
<script type="text/javascript">

	var openId = $.cookie("openId");
	var userId = "${userId}";
	if(openId != userId){
		$(".fa-plus,.fa-edit,.fa-delete,.fa-check").hide();
	}
	
	$(".fa-plus").on("click",function(){
		M.ajax({
			url:"${contextPath}/mobile/album/add",
			success:function(data){
				if(data.result == SUCCESS){
					createAlbum(data.album);
				}else{
					M.alert("数据异常。")
				}
			}
		});
	});
	
	function createAlbum(album){
		var html = '<li data-id="[albumId]">\
						<a href="${contextPath}/mobile/albumImage?m=${userId}&a=[albumId]">\
							<span class="img">\
								<img src="${imagePath }/mobile/pic-none.png"\
										class="animated bounceIn">\
							</span>\
						</a>\
						<span class="text" style="display: none;">新建相册<i class="fa fa-edit" onclick="edit(this)></i><i class="fa fa-minus" style="color: red;" onclick="deleteAlbum(this)"></i></span>\
						<span class="input" ><input type="text" value="新建相册"><i class="fa fa-check" onclick="save(this)"></i></span>\
					</li>';
			html = html.replace("[albumId]",album.albumId).replace("[albumId]",album.albumId);
		$(".album ul").prepend(html);
	}
	
	
	function edit(that_){
		$(that_).closest("span").hide();
		$(that_).closest("li").find(".input").show();
		$(that_).closest("li").find("input").focus();
	}
	
	function save(that_){
		var val = $(that_).closest("li").find("input").val();
		var id = $(that_).closest("li").data("id");
		if(!M.isNull(val)){
			M.ajax({
				url:"${contextPath}/mobile/album/edit",
				data:{albumId:id,title:val},
				success:function(data){
					if(data.result == SUCCESS){
						M.toast("保存成功");
						$(that_).closest("span").hide();
						$(that_).closest("li").find(".text").show();
						$(that_).closest("li").find(".text font").html(val);
					}else{
						M.alert("数据异常，保存失败。")
					}
				}
			});
		}else{
			M.alert("请输入相册名称。")
		}
	}
	
	function deleteAlbum(that_){
		M.confirm("数据删除后将无法恢复，确定要删除该相册吗？",null,function(r){
			if(r){
				var id = $(that_).closest("li").data("id");
				M.ajax({
					url:"${contextPath}/mobile/album/delete",
					data:{albumId:id},
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