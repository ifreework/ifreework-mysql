$.namespace("system.main"); // 创建命名空间，格式层级等于页面请求目录

system.main = function(){
	var historyArray = [], // 用于记录用户请求路径
		historyBreadcrumbArray = [], //用于记录用户请求路径对应的标头
		indexPath = "system/index";// 首页地址
		
	
	/**
	 * 当页面大小发生改变时，修改PageBody的高度
	 */
	function changePageBodyHeight() {
		var bodyHeight = $("body").height();
		console.log("Body Height:" + bodyHeight);
		$("#page-body").css({
			minHeight : bodyHeight - 85
		});
	}
	
	/**
	 * 初始化用户下拉框点击事件
	 */
	function initUserDropDown() {
		$("#user-manager ul.dropdown-menu").click(function(t) {
			var _a = $(t.target).closest("a"), // 获取点击的a标签
			id = _a.data("id");
			
			if (id === "user-img") {
				openUserImg();
			} else if (id === "user-edit") {
				var username = _a.data("username");
				openUserEdit(username);
			} else if (id === "password-reset") {
				openResetPwd();
			} else if (id === "logout") {
				location.href = "logout";
			}

		});
	}
	
	// 打开用户编辑界面
	function openUserEdit(username){
		var url = "system/user/edit";
		system.main.open(url,"用户编辑",{username:username});
	}
	
	
	//打开编辑头像界面
	function openUserImg(){
		return bootbox.dialog({
			id:"userImgDialog",
			title: "头像设置",
			width:700,
	        loadUrl: "main/userChangeImg"
	    });
	}
	
	//打开修改密码界面
	function openResetPwd(){
		return bootbox.dialog({
			id:"userImgDialog",
			title: "密码重置",
			width:700,
	        loadUrl: "system/user/changePwd"
	    });
	}
	
	/**
	 * 初始化菜单切换按钮
	 */
	function initiateSideMenuCompact() {
		// 菜单模式切换按钮
		$("#sidebar-collapse").on("click",function() {
			if(!$("#sidebar").is(":visible")){
				$("#sidebar").toggleClass("hide");
			}
			
			$("#sidebar").toggleClass("menu-compact");  //
			$(".sidebar-collapse").toggleClass("active");
			
			compact = $("#sidebar").hasClass("menu-compact");
			
			if(compact){
				$("#sidebar .open > .submenu").removeClass("open");
			}
		});
	}
	
	/**
	 * 初始化菜单
	 */
	function initiateSideMenu(){
		var compact = $("#sidebar").hasClass("menu-compact");// 菜单是否为compact模式
		
		$(".sidebar-menu").on("click",function(t) {
			var _a = $(t.target).closest("a"),// 获取点击的a标签
			ul = _a.closest("ul"),
			u,
			r,
			f;
			if (_a && _a.length != 0) {
				if (!_a.hasClass("menu-dropdown")){
					var url = _a.data("url");
					if(!W.isNull(url)){
						$(".sidebar-menu").find("li.active").removeClass("active");
						_a.closest("li").addClass("active");
						openPage(url,{},function(){
							resetBreadcrumb(_a);
						});
					}
				} else {// 如果点击的a标签含有下拉菜单，则显示下拉菜单
					if (r = _a.next().get(0), !$(r).is(":visible")) {
						if (f = $(r.parentNode).closest("ul"), compact && f.hasClass("sidebar-menu")) return;
						f.find("> .open > .submenu").each(function() {
							this == r || $(this.parentNode).hasClass("active") || $(this).slideUp(200).parent().removeClass("open")
						})
					}
				} 
				return compact && $(r.parentNode.parentNode).hasClass("sidebar-menu") ? !1 : ($(r).slideToggle(200).parent().toggleClass("open"), !1)
			}
		});
	}

	
	// 重置页面标头导航
	function resetBreadcrumb(e) { 
		var parentsLi, // 所有的父级节点
		parentA, // 父级a节点
		aText, //每个a标签中的文字
		_li,  //新建<li></li>
		_a;  //新建<a></a>
		
		
		$("#ul-breadcrumb").empty(); //初始化标头

		parentsLi = e.parents("li");  //获取所有父级节点

		$.each(parentsLi, function(index, element) { //倒叙
			parentA = $(element).find("> a");
			aText = parentA.find(" > .menu-text");

			_li = $("<li></li>");
			
			if(index == 0){ //如果是最后一个标签，添加激活标志
				_li.addClass("active");
			}
			
			if (parentsLi.length - 1 == index) { // 如果是第一个标签，添加icon
				_li.append('<i class="fa fa-home"></i>');
			}

			_a = $('<a href="javascript:void(0)" >' + aText.text() + '</a>');

			if (!W.isNull(parentA.data("url"))) { //如果请求路径不为空，则添加请求地址
				_a.data("url", parentA.data("url"));
			}
			
			_li.append(_a);
			
			$("#ul-breadcrumb").prepend(_li);
			
		});
		
		historyBreadcrumbArrayPush();
		
		$("#ul-breadcrumb li a").unbind("click").on("click",breadcrumbLiClick);
	}
	
	
	//添加页面表头
	function addBreadcrumbLi(url,text,data) { 
		var _li,
			_a;
		$("#ul-breadcrumb li.active").removeClass("active");
		_li = $("<li class='active'></li>");

		_a = $('<a href="javascript:void(0)" >' + text + '</a>');
		_a.data("url", url);
		_a.data("data", data);
		_li.append(_a);
		
		$("#ul-breadcrumb").append(_li);
		
		historyBreadcrumbArrayPush();
		
		$("#ul-breadcrumb li a").unbind("click").on("click",breadcrumbLiClick);
	}
	
	
	//首页标头
	function indexBreadcrumbLi() { 
		var _li,
			_a;
		
		$("#ul-breadcrumb").empty(); //初始化标头
		
		_li = $("<li></li>");
		_li.addClass("active");
		_li.append('<i class="fa fa-home"></i>');

		_a = $('<a href="javascript:void(0)" >首页</a>');
		_a.data("url",indexPath);
		_li.append(_a);
		
		$("#ul-breadcrumb").prepend(_li);
	
		historyBreadcrumbArrayPush();
		$("#ul-breadcrumb li a").unbind("click").on("click",breadcrumbLiClick);
	}
	
	function historyBreadcrumbArrayPush(){
		var array = [];
		$("#ul-breadcrumb").find("li").each(function(index,element){
			var cloneElement = $(element).clone();
			cloneElement.find("a").data($(element).find("a").data());
			array.push(cloneElement);
		});
		historyBreadcrumbArray.push(array);
	}
	
	function breadcrumbLiClick(t){//表头点击事件
		var url = $(this).data("url");
		if(url != null && url != ""){
			$("#ul-breadcrumb li.active").removeClass("active");
			$(t.target).closest("li").addClass("active");
			
			var data = $(this).data("data");
			
			$(this).parents("li").nextAll().remove();
			
			historyBreadcrumbArrayPush();
			openPage(url,data);
		}
	}
	
	
	/**
	 * 在page-body中打开新的页面
	 * url:String 请求地址
	 * data:jsonObject 请求参数 
	 * callBack:function(response,status,xhr) 页面加载成功后回调函数，response 返回结果,status 状态 xhr
	 * 
	 */
	function openPage(url, data, callBack) {
		var time = $.now();

		if (data == null) {
			data = {};
		}

		data._type = "windowOpen";
		data._time = time;
		
		bootbox.load();
		
		$("#page-body").load(url, data, function(response,status,xhr) {
			bootbox.unload();
			
			historyArray.push({ //保存请求历史纪录
				url : url,
				data : data
			});
			
			if("error" == status){
				$("#page-body").html(response);
			}
			if("timeout" == status){
				bootbox.alert("请求超时，请检查网络后重新进行连接。");
			}
			if ($.isFunction(callBack)) {
				return callBack.call(this,response,status,xhr);
			}
		});
	}
	
	
	/**
	 * 初始化页面右上角三个按钮操作
	 */
	function initBreadcrumbsButtons(){
		$("#fullscreen-toggler").on("click",function() {//全屏
			var n = document.documentElement;
			$("body").hasClass("full-screen") ? ($("body").removeClass("full-screen"), $("#fullscreen-toggler").removeClass("active"), document.exitFullscreen ? document.exitFullscreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitExitFullscreen && document.webkitExitFullscreen()) : ($("body").addClass("full-screen"), $("#fullscreen-toggler").addClass("active"), n.requestFullscreen ? n.requestFullscreen() : n.mozRequestFullScreen ? n.mozRequestFullScreen() : n.webkitRequestFullscreen ? n.webkitRequestFullscreen() : n.msRequestFullscreen && n.msRequestFullscreen())
		});
		$("#refresh-toggler").on("click",function() {//刷新
			if (historyArray.length == 0) { 
				window.location.reload(true);
				return;
			}
			var urlObj = historyArray[historyArray.length - 1];
			urlObj.data = urlObj.data== null ? {} : urlObj.data;
			openPage(urlObj.url, urlObj.data);
			return;
		});
		
		$("#backspace-toggler").on("click",function() {//返回
			history();
		});
		
	}
	
	/**
	 * 返回操作
	 */
	function history() { 
		if (historyArray.length == 0) { // 如果没有历史记录，则不进行任何操作
			return;
		}
		if (historyArray.length == 1) { // 如果只打开了一个界面，则返回首页
			historyArray = [];
			historyBreadcrumbArray = [];
			indexBreadcrumbLi();
			openPage(indexPath);
			return ;
		}
		historyArray.pop();
		historyBreadcrumbArray.pop();
		var urlObj = historyArray[historyArray.length - 1];
		
		urlObj.data = urlObj.data== null ? {} : urlObj.data;
		openPage(urlObj.url, urlObj.data,function(response,status,xhr){
			$("#ul-breadcrumb").empty();
			var array = historyBreadcrumbArray[historyBreadcrumbArray.length - 1];
			for(var i = 0 ; i < array.length ; i++){
				$("#ul-breadcrumb").append(array[i]);
			}
			$("#ul-breadcrumb li a").unbind("click").on("click",breadcrumbLiClick);

		});
		return;
	}
	
	return {
		init:function(){
			initiateSideMenuCompact();
			initiateSideMenu();
			changePageBodyHeight();
			initUserDropDown();
			initBreadcrumbsButtons();
			$(window).resize(changePageBodyHeight);
		},
		
		/**
		 * 返回上一页
		 */
		history : function() { 
			history();
		},
		
		/**
		 * 在pagebody中加载页面内容
		 * @param url 页面路径
		 * @param title 标题
		 * @param data 请求数据 json格式
		 * @param callback 回掉函数
		 */
		open:function(url,title,data,callback){
			openPage(url,data,function(response,status,xhr){
				addBreadcrumbLi(url,title,data);
				if ($.isFunction(callback)) {
					callback.call(this,response,status,xhr)
				}
			});
		} 
		
	}
}();

$().ready(function(){
	system.main.init();
});