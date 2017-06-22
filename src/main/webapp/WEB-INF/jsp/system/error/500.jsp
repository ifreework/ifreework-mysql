<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/error/404.css"></link>
<script type="text/javascript">
	(function(){
		$().ready(function(){
			var InterValObj; //timer变量，控制时间
			var count = 5;
			var curCount;
		
			function SetRemainTime() {
				if (curCount == 0) {
					window.clearInterval(InterValObj);//停止计时器
					window.setTimeout("window.location='http://www.17sucai.com/'", 0);
				} else {
					curCount--;
					document.getElementById("redirect_info").innerHTML = (curCount + "秒后返回首页");
				}
			}
		});
	}());
</script>
<div class="mian">
	<div class="error_div">
        500
    </div>
</div>