/*
 * 该JS中包涵一些常用的公共方法
 */

//自定义W命名空间,为防止与页面中一些自定义变量冲突
var W = {
	historyArray : [],// 历史记录
	history : function() { // 返回上一页
		if (W.historyArray.length == 0) { // 如果没有历史记录，则不返回
			return;
		}

		if (W.historyArray.length == 1) { // 如果只打开了一个界面，则返回首页
			W.historyArray.pop();
			var nowTime = new Date().getTime();
			bootbox.load();
			var url = W.getContextPath();
			$("#page-body").load(url, {
				_time : nowTime
			}, function() {
				bootbox.unload();
			});
			return;
		}
		W.historyArray.pop();
		var urlObj = W.historyArray[W.historyArray.length - 1];
		urlObj.data = W._initData(urlObj.data);
		$("#page-body").load(urlObj.url, urlObj.data, function() {
			bootbox.unload();
		});
		return;
	},
	getContextPath : function() { // 获取项目名
		var pathName = window.document.location.pathname;
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return projectName;
	},
	
	jsonArrayToString : function(array) { // 将array转换成json对象

		var str = "[";
		for (var i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				str += JSON.stringify(array[i]);
			} else {
				str += JSON.stringify(array[i]) + ",";
			}
		}
		str += "]";
		return str;

	},
	
	arrayRemove : function(array,obj){ //删除array中的某个对象
		for(var i = 0; i < array.length ; i++){
			if(array[i] == obj){
				array.splice(i,1);
			}
		}
	},
	
	openPage : function(url, data) {// 在body中打开新的页面
		var nowTime = new Date().getTime();
		bootbox.load();
		data = W._initData(data);
		
		$("#page-body").load(url, data, function(response,status,xhr) {
			bootbox.unload();
			W.historyArray.push({
				url : url,
				data : data
			});
			if("error" == status){
				$("#page-body").html(response);
			}
			if("timeout" == status){
				bootbox.alert("请求超时，请检查网络后重新进行连接。");
			}
		});
	},
	
	open : function(url, text,data){  //打开新页面，url：页面地址 text:页面名称 data:附加参数
		W.addBreadcrumbLi(url, text,data);
		W.openPage(url,data);
	},
	
	addBreadcrumbLi : function(url,text,data) { //添加页面标头
		$("#ul-breadcrumb li.active").removeClass("active");
		var _li = $("<li class='active'></li>");

		_a = $('<a href="javascript:void(0)" >' + text + '</a>');
		_a.data("url", url);
		_a.data("data", data);
		_li.append(_a);
		$("#ul-breadcrumb").append(_li);
		$("#ul-breadcrumb li a").unbind("click").on("click",W.breadcrumbLiClick);
	},
	
	breadcrumbLiClick:function(t){//表头点击事件
		var url = $(this).data("url");
		if(url != null && url != ""){
			$("#ul-breadcrumb li.active").removeClass("active");
			$(t.target).closest("li").addClass("active");
			
			var data = $(this).data("data");
			W.removeBreadcrumbLi($(this).parents("li"));
			W.openPage(url,data);
		}
	},
	
	removeBreadcrumbLi:function(li){ //移除页面标头
		li.nextAll().remove();
	},

	
	isNull:function(str) {//判断字符串是否为空字符串
		return str == null || str == "";
	},
	getRequestParamByName:function(name){ //通过名字获取页面路径中的参数
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	
	ajax:function(opt){ //ajax
		var time = new Date().getTime();
		if (opt.data == null) {
			opt.data = {
				_type : "ajax",
				_time : time
			};
		} else {
			opt.data._type = "ajax";
			opt.data._time = time;
		}
		
		opt.showLoad = opt.showLoad = null ? true : opt.showLoad; //是否显示加载遮罩，默认为true

		var opts = {
			type : 'POST',
			dataType : "json",
			beforeSend : function(xhr) {
				if(opt.showLoad){
					bootbox.load();
				}
				if(opt.beforeSend){
					return opt.beforeSend(xhr);
				}
			},
			success:function(result){
				if(opt.result == "ERROR"){
					if(result.errorType == "userIsNull"){
						bootbox.alert("用户登录超时或尚未登录，请重新登录系统","",function(){
							window.location.reload();
						});
					}
				}else{
					if(opt.success){
						return opt.success(result);
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if (textStatus == "timeout") {
					bootbox.alert("网络连接超时，请稍后再试。");
				}
				if (textStatus == "error") {
					bootbox.alert("系统出现异常，请稍后再试。");
				}
				if(opt.error){
					return opt.error(jqXHR, textStatus, errorThrown);
				}
			},
			complete : function(xhr, ts) {
				if(opt.showLoad){
					bootbox.unload();
				}
				if(opt.complete){
					return opt.complete(xhr, ts);
				}
			}
		};
		var o = $.extend(opts, opt);
		$.ajax(o);
	},
	
	_initData : function(data) {
		var nowTime = new Date().getTime();
		if (data == null) {
			data = {};
		}
		data._time = nowTime;
		data._type = "windowOpen";
		return data;
	}
};



/**
 * canvas图片压缩
 * 
 * @param {[Object]}
 *            opt [配置参数]{src:文件路径，或者为base64编码图片， type:是否预览，1/0， width:压缩后宽度，
 *            height:压缩后高度}
 * @param {[Function]}
 *            cbk [回调函数]
 * @return {图片base64编码后的字符串};
 */
function writeDom(opt, cbk) {
	var _opt = $.extend({
		sx : 0,
		sy : 0,
		swidth : 0,
		sheight : 0,
		x : 0,
		y : 0,
		width : 0,
		height : 0
	}, opt), _cbk = cbk;
	$('#_img,#_canvas,#img-c').remove();
	$('body')
			.append(
					$('<canvas id="_canvas" style="display: none;"></canvas><img id="img-c" src="" style="display:none;"/><img id="_img" src="" style="width:300px;"/>'));
	_image = new Image();
	_image.src = _opt.src || "";
	$('#img-c').attr('src', _opt.src)[0].onload = function() {
		var _this = $(this);
		var _canvas = document.getElementById('_canvas');
		_canvas.width = _opt.width;
		_canvas.height = _opt.height;
		var _context = _canvas.getContext('2d');
		_context.drawImage(_image, _opt.sx, _opt.sy, _opt.swidth, _opt.sheight,
				_opt.x, _opt.y, opt.width, opt.height);
		if (_opt.type) {
			$('#img').attr('src', _canvas.toDataURL('image/jpeg'));
		}
		;
		//		
		_cbk(_canvas.toDataURL('image/jpeg'));
		$('#_img,#_canvas,#img-c').hide();

	};
}

/**
 * alert弹出框，load加载遮罩
 */
$
		.extend(
				bootbox,
				{
					alert : function(msg, title, fn) {
						window.top.window.bootbox
								.dialog({
									message : '<div class="row"><div class="col-md-12"><span class="alert-text">'
											+ msg + '</span></div></div>',
									title : '<i class="typcn typcn-info-outline"></i>&nbsp;&nbsp;'
											+ (title == null || title == "" ? "提示"
													: title),
									className : "modal-darkorange modal-alert",
									closeButton : false,
									buttons : {
										success : {
											label : "确定",
											className : "btn-default",
											callback : fn
										}
									}
								});
					},
					confirm : function(msg, title, fn) {
						window.top.window.bootbox
								.dialog({
									message : '<div class="row"><div class="col-md-12"><span class="confirm-text">'
											+ msg + '</span></div></div>',
									title : '<i class="typcn typcn-info-outline"></i>&nbsp;&nbsp;'
											+ (title == null || title == "" ? "提示"
													: title),
									className : "modal-darkorange modal-confirm",
									closeButton : false,
									buttons : {
										success : {
											label : "确定",
											className : "btn-default green",
											callback : function() {
												fn(true);
											}
										},
										cancel : {
											label : "取消",
											className : "btn-default red",
											callback : function() {
												fn(false);
											}
										}
									}
								});
					},
					load : function() {
						var loadDiv = $(".loading-container",
								window.top.window.document);
						if (loadDiv.length == 0) {
							var html = '<div class="loading-container"><div class="loading-progress"><div class="rotator"><div class="rotator"><div class="rotator colored"><div class="rotator"><div class="rotator colored"><div class="rotator colored"></div><div class="rotator"></div></div><div class="rotator colored"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div></div>';
							$("body", window.top.window.document).append(html);
						} else {
							loadDiv.removeClass("loading-inactive");
						}
					},
					unload : function() {
						var loadDiv = $(".loading-container",
								window.top.window.document);
						loadDiv.addClass("loading-inactive");
					}
				});

/**
 * 添加jquery组件，将form序列成json返回
 * 
 * @param $
 */
(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
})(jQuery);

/**
 * 添加menu组件
 * 
 * @param $
 */
W.menu = function(options) {
	var template_ul = '<ul class="dropdown-menu dropdown-palegreen blue custom-menu" style="dispaly:none;"></ul>', template_li = '<li></li>', template_a = '<a href="javascript:void(0)" tabindex="-1"></a>', template_i = '<i class="dropdown-icon"></i>', template_text = '<span class="text-span"></span>', _opt, _ul, _li, _a, _i, _text, _item;

	var opt = {
		id : null, // menu id
		className : null, // 自定义menu样式
		items : [], // menu显示的行{id,icon,className,text}
		onclick : null
	// 点击事件 function(event,id),
	};

	_opt = $.extend(opt, options);

	_ul = $(template_ul);
	if (_opt.id != null) {
		_ul.attr("id", _opt.id);
	}
	addClass(_opt.className, _ul);

	if (_opt.items != null) {
		for (var i = 0; i < _opt.items.length; i++) {
			_item = _opt.items[i];
			if ((typeof _item) === "string") {
				_li = $(template_li).addClass(_item);
			} else {
				_li = $(template_li);
				_a = $(template_a).data("id", _item.id);
				addClass(_item.className, _a);

				if (_item.icon != null) {
					_i = $(template_i);
					addClass(_item.icon, _i);
					_a.append(_i);
				}
				_text = $(template_text).html(_item.text);
				_a.append(_text);
				_li.append(_a);

			}
			_ul.append(_li);
		}
	}

	$("body").append(_ul);

	$("body").click(function() {
		_ul.hide();
	});

	_ul.find("a").click(function() {
		var _id = $(this).data("id");
		if (typeof _opt.onclick === "function") {
			_opt.onclick(_id);
		}
	});

	function addClass(className, e) { // 解析CLASS类并添加到元素中，多个class name通过“ ”分割
		if (className != null) {
			var _classes = className.split(" ");
			for (var i = 0; i < _classes.length; i++) {
				e.addClass(_classes[i]);
			}
		}
	}

	this.show = function(x, y) {// 显示Menu菜单，x:横向定位，y，纵向定位
		_ul.css({
			left : x,
			top : y
		});
		_ul.show();
	}

}
