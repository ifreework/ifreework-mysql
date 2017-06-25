<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/main/userImg.css"></link>
<script src="${ jsPath }/bootstrap/dropzone/dropzone.js"></script>
<script type="text/javascript">
$.namespace("weixin.article.img");

weixin.article.img = function() {
	var weixinArticleImg, dropzone,imgFile = {};

	// 初始化上传组件
	function initDropzone() {
		dropzone = new Dropzone("#weixin-article-img #dropzone", {
			url: "${ contextPath }/",
			acceptedFiles: ".jpg,.png",
			autoProcessQueue: false,
			maxFiles:1,
			maxFilesize:1,
			addedfile: function(file) {
				console.log();
				imgFile = file;
				readData(file);
			}
		});
	}

	function readData(file){
		 var reader;
		 reader = new FileReader;
	     reader.onload = function(e){
	    	 var scale = 1;
	    	 var img = $("<img>").load(function(){
	    		 var w = this.width;
				 var h = this.height;
				 scale = loadImgScale(w,h); //获取图片缩放比例
				 
				 writeDom({
					 src:this.src, 
					 width:w*scale,
					 height:h*scale,
					 swidth:w,
					 sheight:h
					},
					function(data){
		    			afterAddImg(file,data,{width:w*scale,height:h*scale});
		    	 	}
				);
				 
				$(this).remove();
	    	});
	    	img.attr("src",this.result).hide();
	     } 
	     return reader.readAsDataURL(file);
	}
	
	// 修改图片尺寸，设置宽度最大不超过550，最小不超过380
	function loadImgScale(w, h) {
		var scale = 1;
		if (w / h >= 550 / 380) {
			scale = 550 / w;
		} else {
			scale = 380 / h;
		}
		return scale;
	}

	/**
	 * canvas图片压缩
	 * 
	 * @param {[Object]}
	 *            opt [配置参数]{
	 *	 			src:文件路径，或者为base64编码图片，
	 *	 			type:是否预览，1/0， 
	 *	 			width:压缩后宽度，
	 *              height:压缩后高度,
	 *				swidth:原始宽度
	 *				sheight:原始高度
	 *				}
	 * @param {[Function]}
	 *            cbk [回调函数]  压缩知行完成后执行
	 * @return {图片base64编码后的字符串};
	 */
	function writeDom(opt, cbk) {
		var _opt = $.extend({ //初始化配置参数
				sx: 0,
				sy: 0,
				swidth: 0,
				sheight: 0,
				x: 0,
				y: 0,
				width: 0,
				height: 0
			},opt),  
			
			_cbk = cbk; //回调函数
		
		
		$('#_img,#_canvas,#img-c').remove(); //清除之前创建的对象
			
		$('body').append($('<canvas id="_canvas" style="display: none;"></canvas>\
							<img id="img-c" src="" style="display:none;"/>\
							<img id="_img" src="" style="width:300px;"/>'));
		_image = new Image();
		
		_image.src = _opt.src || "";
		
		$('#img-c').attr('src', _opt.src)[0].onload = function() {
			var _this = $(this);
			var _canvas = document.getElementById('_canvas');
			_canvas.width = _opt.width;
			_canvas.height = _opt.height;
			var _context = _canvas.getContext('2d');
			_context.drawImage(_image, _opt.sx, _opt.sy, _opt.swidth, _opt.sheight, _opt.x, _opt.y, opt.width, opt.height);
			
			if (_opt.type) {
				$('#img').attr('src', _canvas.toDataURL('image/jpeg'));
			};
			//		
			_cbk(_canvas.toDataURL('image/jpeg'));
			$('#_img,#_canvas,#img-c').hide();

		};
	}
	 
	 
	function afterAddImg(file, data, opt) {
		weixinArticleImg.find(".dropzone-upload-text").hide();
		
		var div = $("<div>").addClass("dropzone-img-view")
					.append("<div class='dropzone-view-back modal-background'>");
		div.find(".dropzone-view-back").append("<img>").append("<div class='dropzone-modal'>");
		div.find(".dropzone-modal").append("<div class='center'>").append("<div class='top'>").append("<div class='bottom'>").append("<div class='left'>").append("<div class='right'>");
		weixinArticleImg.find("#dropzone").append(div);
		
		var pos = initImg(opt);
		div.find("img").attr("src", data).css(pos);
		
		pos.marginTop = (0 - opt.height);
		div.find(".dropzone-modal").css(opt);
		div.find(".dropzone-modal").css(pos);

		var modalOpt = initModalDivPosition(opt);
		
		div.find(".top,.bottom").css({
			width: opt.width,
			height: modalOpt.tHeight
		});
		div.find(".left,.right").css({
			width: modalOpt.lWidth,
			height: 200,
			top: modalOpt.cTop
		});
		div.find(".center").css({
			top: modalOpt.cTop,
			left: modalOpt.cLeft
		});
		
		initPreview();

		div.find(".center").mousedown(function(event) {
			var x = event.pageX,
			y = event.pageY;
			var mdiv = $(".dropzone-modal"),
			cDiv = $(this),
			lDiv = mdiv.find(".left"),
			tDiv = mdiv.find(".top"),
			bDiv = mdiv.find(".bottom"),
			rDiv = mdiv.find(".right");
			var cTop = cDiv.position().top,
			cLeft = cDiv.position().left;

			$(this).mousemove(function(e) {
				var moveX = e.pageX - x,
				moveY = e.pageY - y;
				var aTop = cTop + moveY,
				aLeft = cLeft + moveX;
				aTop = aTop < 0 ? 0 : aTop > (opt.height - 200) ? (opt.height - 200) : aTop;
				aLeft = aLeft < 0 ? 0 : aLeft > (opt.width - 200) ? (opt.width - 200) : aLeft;
				cDiv.css({
					top: aTop,
					left: aLeft
				});

				tDiv.css({
					height: aTop
				});
				bDiv.css({
					height: (opt.height - 200 - aTop)
				});
				lDiv.css({
					top: aTop,
					width: (aLeft)
				});
				rDiv.css({
					top: aTop,
					width: (opt.width - 200 - aLeft)
				});
			});
		});
		$(document).mouseup(function(event) {
			initPreview();
		});
		initPreview();
		weixinArticleImg.find(".img-preview").addClass("modal-background");
	}
	
	// 初始化图片位置
	function initImg(opt) {
		return {
			marginLeft: (550 - opt.width) / 2,
			marginTop: (380 - opt.height) / 2
		};
	}

	//初始化遮罩
	function initModalDivPosition(opt) {
		var o = {},
		w = (opt.width - 200) / 2,
		h = (opt.height - 200) / 2;
		o.cTop = h;
		o.cLeft = w;
		o.tHeight = o.bHeight = h;
		o.lWidth = o.rWidth = w;
		return o;
	}

	//生成裁剪后的图片
	function initPreview() {
		var _center = $(".dropzone-img-view").find(".center"),
		_top = _center.position().top,
		_left = _center.position().left,
		_img = $(".dropzone-img-view").find("img"),
		_src = _img.attr("src");
		_center.unbind("mousemove");

		writeDom({
			src: _src,
			width: 200,
			height: 200,
			swidth: 200,
			sheight: 200,
			sx: _left,
			sy: _top
		},
		
		function(data100) {
			if (weixinArticleImg.find(".img-preview").find("img").length == 0) {
				weixinArticleImg.find(".img-preview").append("<img class='preview100'><img class='preview50'><img class='preview30'>");
			}
			weixinArticleImg.find(".img-preview").find("img").attr("src", data100);
		});
	}



	return {
		init:function(){
			weixinArticleImg = $("#weixin-article-img");
			initDropzone();
		},
		getImage:function(){//获取裁剪后的图像
			var data = weixinArticleImg.find(".preview100").attr("src");
			imgFile.data = data;
			return imgFile;
		}
	}
} ();

$().ready(function(){
	weixin.article.img.init();
});
</script>
<div class="container-content no-margin" id="weixin-article-img">
	<div class="container-body no-padding">
		<div class="row dropzone-panel">
			<div class="col9">
				<div id="dropzone" class="dropzone-body ">
					<div class="dropzone-upload-text dz-default dz-message">
						<span>选择图片</span>
					</div>
				</div>
			</div>
			<div class="col3">
				<div class="img-list">
					<div class="img-preview"></div>
				</div>
			</div>
		</div>
	</div>
</div>