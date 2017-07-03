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
</body>
<script type="text/javascript">
	var openId = "${openId}";
	$.cookie("openId",openId);
	var f = "${f}";
	f = decodeURI(f);
	window.location.href = f;
</script>
</html>
