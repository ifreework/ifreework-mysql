<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title><%=SysTemConfigManager.get(Config.SYSTEM_NAME) %></title>
<link rel="shortcut icon" href="${ imagePath }/system/favicon.ico"
	type="image/x-icon" />

<!-- bootstrap核心样式 -->	
<link rel="stylesheet" href="${ cssPath }/bootstrap.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/ifreework.min.css"></link>

<!-- icon字体样式 -->
<link rel="stylesheet" href="${ cssPath }/font-awesome.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/weather-icons.min.css"  />
<link rel="stylesheet" href="${ cssPath }/typicons.min.css"  />

<!-- jquery,bootstrap插件样式 -->
<link rel="stylesheet" href="${ cssPath }/select2/select2.css"></link>
<link rel="stylesheet" href="${ cssPath }/datepicker/bootstrap-datepicker.css"  />
<link rel="stylesheet" href="${ cssPath }/dataTables.bootstrap.css"  />

<!-- 自定义核心样式 -->
<link rel="stylesheet" href="${ cssPath }/base.css"  />
<link rel="stylesheet" href="${ cssPath }/main/style.css"  />

<!-- jquery,bootstrap核心 -->
<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ jsPath }/jquery/jquery.cookie.js"></script>

<script src="${ jsPath }/bootstrap/bootstrap.min.js"></script>

<!-- jquery,bootstrap插件 -->
<!-- form校验 -->
<script src="${ jsPath }/bootstrap/validation/bootstrapValidator.js"></script>
<script src="${ jsPath }/bootstrap/validation/i18n/zh_CN.js"></script>

<!-- 日期控件 -->
<script src="${ jsPath }/bootstrap/datetime/bootstrap-datepicker.js"></script>
<script src="${ jsPath }/bootstrap/datetime/bootstrap-timepicker.js"></script>

<script src="${ jsPath }/bootstrap/select2/select2.js"></script>
<script src="${ jsPath }/bootstrap/textarea/jquery.autosize.js"></script>
<script src="${ jsPath }/bootstrap/datatable/jquery.dataTables.js"></script>
<script src="${ jsPath }/bootstrap/datatable/dataTables.bootstrap.js"></script>
<script src="${ jsPath }/bootstrap/bootbox/bootbox.js"></script>
<script type="text/javascript"
	src="${ jsPath }/bootstrap/charts/chartjs/Chart.bundle.js"></script>
	
	
<!-- 部分自定义方法 -->
<script src="${ jsPath }/ifreework.js"></script>

<!-- main界面js -->
<script src="${ jsPath }/main/main.js"></script>

<script>
	var SUCCESS = "<%=Constant.SUCCESS %>"; //前后台统一成功标志
	var ERROR = "<%=Constant.ERROR %>";  //前后台错误标志
	var FAILED = "<%=Constant.FAILED %>"; //前后台失败标志
	
	$().ready(function(){
		$("#page-body").load("${contextPath}/monitor/message/business");
	});
</script>

<body class="body-skin2" >
	<!-- 页面导航 navbar -->
	<%@ include file="/WEB-INF/jsp/system/main/include/top.jsp"%>
	
	
	
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
    	<div class="page-container">
    	
	    	<!-- 页面左边导航 sidebar -->
	    	<!-- Page Sidebar -->
       		<div class="page-sidebar" id="sidebar">
				<!-- Sidebar Menu -->
                <ul class="nav sidebar-menu">
                
                	
                	<!--Dashboard-->
                    <li>
                        <a href="index.html">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 首页 </span>
                        </a>
                    </li>
	                <c:forEach items="${ menuList }" var="menu1">
	                <li>
	                	<a href="javascript:void(0)" class="${fn:length(menu1.children) > 0 ? 'menu-dropdown' : '' }" data-url='<c:if test="${menu1.resourceUrl != null && menu1.resourceUrl != '' }">${contextPath }${menu1.resourceUrl }</c:if>'>
	                		<i class="menu-icon ${menu1.iconCls }"></i>
	                		<span class="menu-text">${menu1.resourceName }</span>
	                		<c:if test="${fn:length(menu1.children) > 0 }">
	                			<i class="menu-expand"></i>
	                		</c:if>
	                	</a>
	                	<c:if test="${fn:length(menu1.children) > 0 }">
	                	<ul class="submenu">
	                		 <c:forEach items="${ menu1.children }" var="menu2">
	                		 <li>
	                			<a href="javascript:void(0)" class="${fn:length(menu2.children) > 0 ? 'menu-dropdown' : '' }" data-url='<c:if test="${menu2.resourceUrl != null && menu2.resourceUrl != '' }">${contextPath }${menu2.resourceUrl }</c:if>'>
	                		 		<span class="menu-text">${menu2.resourceName }</span>
	                		 		<c:if test="${fn:length(menu2.children) > 0 }">
			                			<i class="menu-expand"></i>
			                		</c:if>
	                		 	</a>
	                		 	<c:if test="${fn:length(menu2.children) > 0 }">
	                		 	
	                		 	 <ul class="submenu">
	                		 	 	 <c:forEach items="${ menu2.children }" var="menu3">
	                		 	 	 <li>
	                		 	 	 	<a href="javascript:void(0)" class="${fn:length(menu3.children) > 0 ? 'menu-dropdown' : '' }" data-url='<c:if test="${menu3.resourceUrl != null && menu3.resourceUrl != '' }">${contextPath }${menu3.resourceUrl }</c:if>'>
	                                        <i class="menu-icon ${menu3.iconCls }"></i>
	                                        <span class="menu-text">${menu3.resourceName }</span>
	                                    </a>
	                		 	 	 </li>
	                		 	 	 </c:forEach>
	                		 	 </ul>
	                		 	</c:if>
	                		 </li>
	                		 </c:forEach>
	                	</ul>
	                	</c:if>
					</li>
					</c:forEach>
                
                </ul>
                <!-- /Sidebar Menu -->
           </div>
    		<!-- /Page Sidebar -->
    	
    	    <!-- Page Content -->
            <div class="page-content">
            
				<!-- Page Breadcrumb -->
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb" id="ul-breadcrumb">
                        <li class="active">
                            <i class="fa fa-home"></i>
                            <a href="javascript:void(0)">首页</a>
                        </li>
                    </ul>
                    <div class="breadcrumbs-buttons">
                        <a class="backspace" id="backspace-toggler" href="javascript:void(0);" title="返回上一页">
                            <i class="fa  fa-reply"></i>
                        </a>
                        <a class="refresh" id="refresh-toggler" href="javascript:void(0);" title="刷新">
                            <i class="glyphicon glyphicon-refresh"></i>
                        </a>
                        <a class="fullscreen" id="fullscreen-toggler" href="javascript:void(0);" title="全屏">
                            <i class="glyphicon glyphicon-fullscreen"></i>
                        </a>
                    </div>
                </div>
                <!-- /Page Breadcrumb -->
                
                <!-- Page Body -->
                <div class="page-body" id="page-body">
                	
                </div>
    		</div>
    		<!-- /Page Content -->
    		
    	</div>
		<!-- /Page Container -->
	</div>
	<!-- /Main Container -->
</body>
</html>
