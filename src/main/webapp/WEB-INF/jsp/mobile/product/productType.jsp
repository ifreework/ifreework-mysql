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
<title>产品中心</title>

</head>
<body style="background-color: #fff !important;">
	<div class="warpe">
		<div class="head">
			<a href="javascript:history.go(-1)" class="return"><i class="fa fa-chevron-left"></i> 返回</a>
			产品类型 <a href="community_sign.html" class="search"><i
				class="fa fa-reorder"></i> </a>
		</div>
		
		<div class="tabs my_tab sqh_tab xx_tab vertical">
			<c:forEach items="${productTypeList }" var="p" varStatus="ps">
				<a href="javascript:void(0)" hidefocus="true" class="product_type_a ${ps.first ? 'active' : ''} " data-id="${p.productTypeId }">${p.productTypeName }</a> 
			</c:forEach>
		</div>
		
		<div class="main tabs_content">
	        <div class="search_txt">
	            <div class="search_list">
	                <ul id="product_type_content">
	                    <li class="animated rotateIn">
	                        <a href="#" class="all">全部</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">
function loadType(){
	var id = $(".active").data("id");
	var href = "${contextPath}/mobile/productList?m=${userId}&t=" + id;
	
	$(".all").attr("href",href);
	
	M.ajax({
		url:"${contextPath}/mobile/productType/query",
		data : { parentId : id },
		success : function( data ){
			for(var i = 0 ; i < data.length ; i++){
				var html = '<li class="animated rotateIn">\
		                    	<a href="${contextPath}/mobile/productList?m=${userId}&t=[productTypeId]">[productTypeName]</a>\
		                    </li>';
                    html = html.replace("[productTypeId]",data[i].productTypeId).replace("[productTypeName]",data[i].productTypeName);
                $("#product_type_content").append(html);    
			}
		}
	});
}
loadType();

$(".product_type_a").on("click",function(){
	$(".active").removeClass("active");
	$(this).addClass("active");
	$("#product_type_content li:not(:first-child)").remove();
	loadType();
});
</script>

</html>