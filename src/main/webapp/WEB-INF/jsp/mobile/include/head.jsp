<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>

<%@ page import="com.ifreework.common.constant.Constant" %>
<!DOCTYPE html>

<link href="${cssPath}/weui/weui.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/mobile/main.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/style.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/mobile/shake.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/animate.min.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/idangerous.swiper.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ jsPath }/jquery/jquery.cookie.js"></script>
<script src="${ jsPath }/bootstrap/validation/bootstrapValidator.js"></script>
<script src="${ jsPath }/bootstrap/validation/i18n/zh_CN.js"></script>
<script type="text/javascript" src="${jsPath }/mobile/wo.js"></script>
<script type="text/javascript" src="${jsPath }/mobile.js"></script>
<script> 
	var SUCCESS = "<%=Constant.SUCCESS %>"; //前后台统一成功标志
	var ERROR = "<%=Constant.ERROR %>";  //前后台错误标志
	var FAILED = "<%=Constant.FAILED %>"; //前后台失败标志
</script>