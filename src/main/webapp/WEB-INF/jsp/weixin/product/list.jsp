<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("weixin.product")
weixin.product = function(){
	var weixinproduct,//页面对象
		dataTable ;
	
	function initDatable(){
		dataTable = weixinproduct.find('#productTab').DataTable({
			searching : false,//
			lengthChange: false,
			info:false,
			ordering : false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/weixin/product/query",
				data: function ( d ) {
		      		return $.extend( {}, d, weixinproduct.find("#queryForm").serializeJson());
			    }
			},
			
			columns : [{  
	        	data : "productId",  
	        	title : "产品列表",  
	        	defaultContent : "", 
	        	render:function( data, type, row, meta ){
	        		var html = '<div class="message-thumb">' + 
	        						'<img alt="图片未能显示" src="' + row.image + '">'+ 
	        					'</div>' + 
		                        '<div class="message-post"> ' +
					                '<span class="message-title"><a href="javascript:void(0)" class="show-product">' + row.productName + '</a></span>' +
					                '<p class="message-lable">' + 
					                	'<a href="javascript:void(0)">产品类型：' + row.productTypeName + '</a>' + 
					                	'<a href="javascript:void(0)">规格型号：'+ row.specificationModel + '</a>' +
					                	'<a href="javascript:void(0)">售价：'+ row.price + '元</a>' +
					                	'<a href="javascript:void(0)" class="hide">访问(' + row.pageView + ')</a>' +
					                	'<a href="javascript:void(0)" class="btn-edit hide" data-productid="' + row.productId + '">编辑</a>' +
					                	'<a href="javascript:void(0)" class="btn-delete hide" data-productid="' + row.productId + '">删除</a>' +
					                '</p>' +
			                  	'</div>';
	        		return html;
	        	}
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	 weixinproduct.find(".btn-edit").click(function(){
	    		var productId = $(this).data("productid");
	    		system.main.open("${contextPath}/weixin/product/edit","产品编辑",{productId:productId});
	    	 });
	    	 weixinproduct.find(".btn-delete").click(function(){
	    		var productId = $(this).data("productid");
	    		bootbox.confirm("确定要删除该产品吗?","",function(r){
	    				if(r){
	    					W.ajax({
	    						url : "weixin/product/delete",
	    						data:{productId:productId},
	    						success:function(param){
	    							if(param.result === SUCCESS){
	    								bootbox.alert("数据删除成功.","",function(){
	    									dataTable.ajax.reload();
	    								});
	    							}else{
	    								bootbox.alert("数据异常，设置失败");
	    							}
	    						}
	    					});
	    				}
	    		});
	    	 });
	    } );
	}
	
	
	function initAdd(){
		weixinproduct.find("#add").on("click",function(){
			system.main.open("${contextPath}/weixin/product/add","产品发布");
		});
	}
	
	function initSelect2(){
		weixinproduct.find("#productTypeId").select2({
			placeholder: "选择产品分类",
			minimumResultsForSearch: Infinity,
	        allowClear: true,
			ajax: {
			    url: "${ contextPath }/weixin/productType/queryList",
			    delay: 250,
			    minimumResultsForSearch: Infinity,
			    dataType: 'json',
				data: function (params) {
				    return {
				    	isLeaf: "1"
				    };
				},
			    processResults: function (data, params) {
			    	for(var i= 0 ;i < data.length;i++ ){
			    		data[i].id=data[i].productTypeId
			    		data[i].text=data[i].productTypeName
			    	}
	                return {
	                   results: data
	                };
		        },
			    cache: true
			}
		})
	}
	return {
		init:function(){
			weixinproduct = $("#weixin-product");
			initSelect2();
			initDatable();
			weixinproduct.find("#query").click(function(){
				dataTable.ajax.reload();
			});
			initAdd();
		}
	}
}();

$().ready(function(){
	weixin.product.init();
});
</script>
<div class="container-content container-plain" id="weixin-product">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
				<label class="col-sm-1 control-label">产品名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="title" placeholder="产品名称">
				</div>
				<label class="col-sm-2 control-label">产品分类</label>
				<div class="col-sm-3">
					<select class="form-control" name="productTypeId" id="productTypeId">
					</select>
				</div>
				<div class="col-sm-3">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
					<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-bordered table-hover table-message" id="productTab">
		</table>
	</div>
</div>