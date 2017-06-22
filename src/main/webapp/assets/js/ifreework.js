/**
 * 命名空间，防止对象冲突
 */
$.namespace = function() {
	var a = arguments, o = null, i, j, d;
	for (i = 0; i < a.length; i = i + 1) {
		d = a[i].split(".");
		o = window;
		for (j = 0; j < d.length; j = j + 1) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
	return o;
};

$.namespace("W"); // 创建公共命名空间W
W = function() {
	return {
		/**
		 * Array操作
		 */
		jsonArrayToString : function(array) { // 将Array[json]转换成json字符串
			if ($.isArray(array)) {
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
			}

			console.error("%d is not Array!", array);
		},

		arrayRemove : function(array, obj) { // 删除array中的某个对象
			var i = $.inArray(obj, array);
			if (i >= 0) {
				array.splice(i, 1);
			}
		},

		/**
		 * String操作
		 */
		isNull : function(str) {// 判断字符串是否为空字符串
			return str == null || $.trim(str) == "";
		},

		/**
		 * URL操作
		 */
		getContextPath : function() { // 获取项目名
			var pathName = window.document.location.pathname;
			var projectName = pathName.substring(0, pathName.substr(1).indexOf(
					'/') + 1);
			return projectName;
		},
		
		getRequestParamByName : function(name) { // 通过名字获取页面路径中的参数
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		},
		randomColor : function(transparent){//获取随机色码
			transparent = transparent == null ? 1 : transparent;
			var r = Math.floor(Math.random()*256);
            var g = Math.floor(Math.random()*256);
            var b = Math.floor(Math.random()*256);
            return "rgba("+r+"," + g + "," + b + "," + transparent +")";
		},

		/**
		 * AJAX 继承自jquery.ajax(opt)方法，jquery.ajax的API在该方法中均可使用
		 * 数据传输格式json，不建议更换其他类型数据 新增属性：showLoad : boolean 加载过程中是否显示遮罩，默认true
		 */
		ajax : function(opt) {
			var time = $.now();

			if (opt.data == null) {
				opt.data = {};
			}

			opt.data._type = "ajax";
			opt.data._time = time;

			opt.showLoad = opt.showLoad == null ? true : opt.showLoad; // 是否显示加载遮罩，默认为true
			
			var options = $.extend({},opt,{
				type : 'POST',
				dataType : "json",
				beforeSend : function(xhr) {
					if (opt.showLoad) {
						bootbox.load();
					}
					if ($.isFunction(opt.beforeSend)) {// 调用自定义前置事件
						return opt.beforeSend.call(this, xhr);
					}
				},
				success : function(result) {
					if (result.errorType == "userIsNull") {
						bootbox.alert("用户登录超时或尚未登录，请重新登录系统", "", function() {
							window.location.reload();
						});
						return;
					}
					if ($.isFunction(opt.success)) {
						return opt.success.call(this,result);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (textStatus == "timeout") {
						bootbox.alert("网络连接超时，请稍后再试。");
					}
					if (textStatus == "error") {
						bootbox.alert("系统出现异常，请稍后再试。");
					}
					if ($.isFunction(opt.error)) {
						return opt.error.call(this,jqXHR, textStatus, errorThrown);
					}
				},
				complete : function(xhr, ts) {
					if (opt.showLoad) { //如果显示了遮罩，则关闭
						bootbox.unload();
					}
					if ($.isFunction(opt.complete)) {
						return opt.complete.call(this,xhr, ts);
					}
				}
			});
			$.ajax(options);
		},
		
		

		menu : function(options) {
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
	}
}();
/**
 * alert弹出框，load加载遮罩
 */
$.extend(bootbox , {
	alert : function(msg, title, callback) {
		bootbox.dialog({
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
					callback : callback
				}
			}
		});
	},
	confirm : function(msg, title, callback) {
		bootbox.dialog({
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
						if ($.isFunction(callback)) {
							callback.call(this,true);
						}
					}
				},
				cancel : {
					label : "取消",
					className : "btn-default red",
					callback : function() {
						if ($.isFunction(callback)) {
							callback.call(this,false);
						}
					}
				}
			}
		});
	},
	image : function(src, callback) { //image图片预览
		bootbox.dialog({
			message : '<image class="image-body" src="' + src + '"/>',
			className : "modal-darkorange modal-image"
		});
	},
	load : function() { //遮罩加载
		var loadDiv = $(".loading-container",
				window.top.window.document);
		if (loadDiv.length == 0) {
			var html = '<div class="loading-container"><div class="loading-progress"><div class="rotator"><div class="rotator"><div class="rotator colored"><div class="rotator"><div class="rotator colored"><div class="rotator colored"></div><div class="rotator"></div></div><div class="rotator colored"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div></div>';
			$("body", document).append(html);
		} else {
			loadDiv.removeClass("loading-inactive");
		}
	},
	unload : function() {//关闭遮罩
		var loadDiv = $(".loading-container",document);
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

