<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("weixin.article")
weixin.article = function(){
	var weixinarticle,//页面对象
		dataTable ;
	
	function initDatable(){
		dataTable = weixinarticle.find('#articleTab').DataTable({
			searching : false,//
			lengthChange: false,
			info:false,
			ordering : false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/weixin/article/query",
				data: function ( d ) {
		      		return $.extend( {}, d, weixinarticle.find("#queryForm").serializeJson());
			    }
			},
			
			columns : [{  
	        	data : "articleId",  
	        	title : "文章列表",  
	        	defaultContent : "", 
	        	render:function( data, type, row, meta ){
	        		var html = '<div class="message-thumb">' + 
	        						'<img alt="图片未能显示" src="' + row.image + '">'+ 
	        					'</div>' + 
		                        '<div class="message-post"> ' +
					                '<span class="message-title"><a href="javascript:void(0)" class="show-article">' + row.title + '</a></span>' +
					                '<p class="message-lable">' + 
					                	'<a href="javascript:void(0)">' + row.articleTypeName + '</a>' + 
					                	'<a href="javascript:void(0)">'+ row.createTime + '</a>' +
					                	'<a href="javascript:void(0)">阅读(' + row.pageView + ')</a>' +
					                	'<a href="javascript:void(0)" class="btn-edit" data-articleid="' + row.articleId + '">编辑</a>' +
					                	'<a href="javascript:void(0)" class="btn-delete" data-articleid="' + row.articleId + '">删除</a>' +
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
	    	 weixinarticle.find(".btn-edit").click(function(){
	    		var articleId = $(this).data("articleid");
	    		system.main.open("${contextPath}/weixin/article/edit","文章编辑",{articleId:articleId});
	    	 });
	    	 weixinarticle.find(".btn-delete").click(function(){
	    		var articleId = $(this).data("articleid");
	    		bootbox.confirm("确定要删除该文章吗?","",function(r){
	    				if(r){
	    					W.ajax({
	    						url : "weixin/article/delete",
	    						data:{articleId:articleId},
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
		weixinarticle.find("#add").on("click",function(){
			system.main.open("${contextPath}/weixin/article/add","文章发布");
		});
	}
	
	function initSelect2(){
		weixinarticle.find("#articleTypeId").select2({
			placeholder: "选择文章分类",
			minimumResultsForSearch: Infinity,
	        allowClear: true,
			ajax: {
			    url: "${ contextPath }/system/dictionary/queryByCodeList",
			    delay: 250,
			    minimumResultsForSearch: Infinity,
			    dataType: 'json',
				data: function (params) {
				    return {
				    	dictionaryTypeId: "weixin", 
				    	code: "article"
				    };
				},
			    processResults: function (data, params) {
			    	for(var i= 0 ;i < data.length;i++ ){
			    		data[i].id=data[i].dictionaryCode
			    		data[i].text=data[i].dictionaryName
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
			weixinarticle = $("#weixin-article");
			initSelect2();
			initDatable();
			weixinarticle.find("#query").click(function(){
				dataTable.ajax.reload();
			});
			initAdd();
		}
	}
}();

$().ready(function(){
	weixin.article.init();
});
</script>
<div class="container-content container-plain" id="weixin-article">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
				<label class="col-sm-1 control-label">标题</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="title" placeholder="标题">
				</div>
				<label class="col-sm-2 control-label">文章分类</label>
				<div class="col-sm-3">
					<select class="form-control" name="articleTypeId" id="articleTypeId">
					</select>
				</div>
				<div class="col-sm-3">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
					<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-bordered table-hover" id="articleTab">
		</table>
	</div>
</div>