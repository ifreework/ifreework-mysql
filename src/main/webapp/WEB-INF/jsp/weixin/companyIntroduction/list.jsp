<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("weixin.companyIntroduction")
weixin.companyIntroduction = function(){
	var weixincompanyIntroduction,//页面对象
		dataTable ;
	
	function initDatable(){
		dataTable = weixincompanyIntroduction.find('#companyIntroductionTab').DataTable({
			searching : false,//
			lengthChange: false,
			info:false,
			ordering : false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/weixin/companyIntroduction/query",
				data: function ( d ) {
		      		return $.extend( {companyId:'${company.companyId}'}, d, weixincompanyIntroduction.find("#queryForm").serializeJson());
			    }
			},
			
			columns : [{  
	        	data : "introductionId",  
	        	title : "文章列表",  
	        	defaultContent : "", 
	        	render:function( data, type, row, meta ){
	        		var html = '<div class="message-thumb">' + 
	        						'<img alt="图片未能显示" src="' + row.image + '">'+ 
	        					'</div>' + 
		                        '<div class="message-post"> ' +
					                '<span class="message-title"><a href="javascript:void(0)" class="show-companyIntroduction">' + row.title + '</a></span>' +
					                '<p class="message-lable">' + 
					                	'<a href="javascript:void(0)">阅读(' + row.pageView + ')</a>' +
					                	'<a href="javascript:void(0)" class="btn-edit" data-introductionid="' + row.introductionId + '">编辑</a>' +
					                	'<a href="javascript:void(0)" class="btn-delete" data-introductionid="' + row.introductionId + '">删除</a>' +
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
	    	 weixincompanyIntroduction.find(".btn-edit").click(function(){
	    		var introductionId = $(this).data("introductionid");
	    		system.main.open("${contextPath}/weixin/companyIntroduction/edit","文章编辑",{introductionId:introductionId});
	    	 });
	    	 weixincompanyIntroduction.find(".btn-delete").click(function(){
	    		var introductionId = $(this).data("introductionid");
	    		bootbox.confirm("确定要删除该文章吗?","",function(r){
	    				if(r){
	    					W.ajax({
	    						url : "weixin/companyIntroduction/delete",
	    						data:{introductionId:introductionId},
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
		weixincompanyIntroduction.find("#add").on("click",function(){
			system.main.open("${contextPath}/weixin/companyIntroduction/add","文章发布");
		});
	}
	
	function initSelect2(){
		weixincompanyIntroduction.find("#companyIntroductionTypeId").select2({
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
				    	code: "companyIntroduction"
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
			weixincompanyIntroduction = $("#weixin-companyIntroduction");
			initSelect2();
			initDatable();
			weixincompanyIntroduction.find("#query").click(function(){
				dataTable.ajax.reload();
			});
			initAdd();
		}
	}
}();

$().ready(function(){
	weixin.companyIntroduction.init();
});
</script>
<div class="container-content container-plain" id="weixin-companyIntroduction">
	<div class="container-body">
		<div id="edit-title" style="display: ${company == null ? 'block' :'none' }">
			<div class="text-center margin-20">您尚未创建自己的工作室，先创建工作室后再进行操作。</div>
		</div>
		<div class="table-toolbar" style="display: ${company == null ? 'none' :'block' }">
			<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加公司介绍</a>
		</div>
		<table class="table table-bordered table-hover" id="companyIntroductionTab" style=" ${company == null ? 'display:none' :'' }">
		</table>
	</div>
</div>