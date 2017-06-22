<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
bootbox.alert("用户登录超时或尚未登录，请重新登录系统","",function(){
	window.location.reload();
});
</script>
