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


$.namespace("M"); // 创建公共命名空间M
M = function() {
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
		 * 该方法主要用于手机端加载数据
		 * MAJAX 继承自jquery.ajax(opt)方法，jquery.ajax的API在该方法中均可使用
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
						M.load();
					}
					if ($.isFunction(opt.beforeSend)) {// 调用自定义前置事件
						return opt.beforeSend.call(this, xhr);
					}
				},
				success : function(result) {
					if ($.isFunction(opt.success)) {
						return opt.success.call(this,result);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if ($.isFunction(opt.error)) {
						return opt.error.call(this,jqXHR, textStatus, errorThrown);
					}
				},
				complete : function(xhr, ts) {
					if (opt.showLoad) { //如果显示了遮罩，则关闭
					M.unload();
					}
					if ($.isFunction(opt.complete)) {
						return opt.complete.call(this,xhr, ts);
					}
				}
			});
			$.ajax(options);
		},
		load: function() { //移动端加载遮罩
			var mloadId = "loadingToast",
				html = "<div id='loadingToast' style='display:none;'>\
					        <div class='weui-mask_transparent'></div>\
					        <div class = 'weui-toast'>\
								<i class = 'weui-loading weui-icon_toast' > </i>\
								<p class = 'weui-toast__content' > 数据加载中 </p>\
							</div>\
						</div>";
			var loadTask = $("#" + mloadId);

			if(loadTask.get(0) == null) {
				loadTask = $(html);
				$("body").append(loadTask);
			}
			if(loadTask.css('display') != 'none') return;
			loadTask.fadeIn(100);
		},
		unload: function() { //移动端加载遮罩
			$("#loadingToast").fadeOut(100);
		},
		
		alert : function(message,title,callback){
			var html = '<div class="js_dialog" id="alertDialog" style="display: none;">\
				            <div class="weui-mask"></div>\
				            <div class="weui-dialog">\
				                <div class="weui-dialog__bd">{message}</div>\
				                <div class="weui-dialog__ft">\
				                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>\
				                </div>\
				            </div>\
				        </div>',
				        titleHtml = '<div class="weui-dialog__hd"><strong class="weui-dialog__title">{title}</strong></div>';
			
			html = html.replace("{message}",message);
			
			$("body").append(html);
			
			if(title != null ){
				titleHtml = titleHtml.replace("{title}",title);
				$("#alertDialog .weui-dialog").prepend(titleHtml);
			}
			$("#alertDialog").fadeIn(100);
			$("#alertDialog .weui-dialog__btn").on("click",function(){
				$("#alertDialog").fadeOut(100);
				$("#alertDialog").remove();
				if ($.isFunction(callback)) {
					callback.call(this);
				}
			});
		},
		
		confirm : function(message,title,callback){
			var html = '<div class="js_dialog" id="confirmDialog" style="display: none;">\
				            <div class="weui-mask"></div>\
				            <div class="weui-dialog">\
				                <div class="weui-dialog__bd">{message}</div>\
				                <div class="weui-dialog__ft">\
									<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default">取消</a>\
					                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>\
				                </div>\
				            </div>\
				        </div>',
				        titleHtml = '<div class="weui-dialog__hd"><strong class="weui-dialog__title">{title}</strong></div>';
			
			html = html.replace("{message}",message);
			
			$("body").append(html);
			
			if(title != null ){
				titleHtml = titleHtml.replace("{title}",title);
				$("#confirmDialog .weui-dialog").prepend(titleHtml);
			}
			$("#confirmDialog").fadeIn(100);
			$("#confirmDialog .weui-dialog__btn").on("click",function(){
				$("#confirmDialog").fadeOut(100);
				$("#confirmDialog").remove();
				if ($.isFunction(callback)) {
					callback.call(this,$(this).hasClass("weui-dialog__btn_primary") );
				}
			});
		},
		
		/**
		 * 消息提示
		 * message:消息内容，必填
		 * flag：消息提示ICON，可选 success,error,默认success
		 * showTime:消息显示时间 默认3000毫秒
		 * callback:消息隐藏时回调函数
		 */
		toast : function(message,flag,showTime,callback){
			var html = '<div id="toast_tt" style="display: none;">\
					        <div class="weui-mask_transparent"></div>\
					        <div class="weui-toast">\
					            <i class="{icon} weui-icon_toast"></i>\
					            <p class="weui-toast__content">${message}</p>\
					        </div>\
					    </div>',
					    flag = flag ? flag : "success",
					    		showTime = showTime ? showTime : 3000;		
			
			html = html.replace("{message}",message);
			
			if(flag == "success"){
				html = html.replace("{icon}","weui-icon-success-no-circle");
			}else{
				html = html.replace("{icon}","weui-icon-warn-no-circle");
			}
			
			$("body").append(html);
			
			$("#toast_tt").fadeIn(100);
			
			setTimeout(function () {
				$("#toast_tt").fadeOut(100);
				$("#toast_tt").remove();
				if ($.isFunction(callback)) {
					callback.call(this );
				}
            }, showTime);
		}
	}
}();

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
