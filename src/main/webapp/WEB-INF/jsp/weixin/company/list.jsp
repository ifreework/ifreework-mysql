<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("weixin.company");

weixin.company = function(){
	var weixinCompany,
		dataTable;
	
	function initTable(){
		dataTable = weixinCompany.find('#companyTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			order:[[0,'desc']],
			ajax:{
				url:"${contextPath}/weixin/company/query",
				data: function ( d ) {
		      		return $.extend( {}, d, weixinCompany.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "companyName",  
	            name : "COMPANY_NAME",  
	            title : "公司/品牌名",  
	            defaultContent : "" 
	        }, {  
	            data : "contactUser",  
	            title : "联系人",  
	            defaultContent : "" ,
	            orderable:false
	        },{  
	        	data : "phone",  
	            title : "电话",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "address",  
	            title : "公司地址",  
	            defaultContent : "",
	            orderable:false
	        }, {  
	        	title : "",  
	        	defaultContent : "", 
	        	className : "text-center"  ,
	        	render:function( data, type, row, meta ){
	        		return '<a class="btn btn-add btn-sky btn-xs icon-only" title="新增" href="javascript:void(0);"><i class="fa fa-plus"></i></a>' +
	        			   '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-companyid="' + row.companyId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
	        			   '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-companyid="' + row.companyId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>';
	        	}
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	bootbox.unload();
	    }).on( 'draw.dt', function () {
	    	weixinCompany.find(".btn-edit").on("click",edit);
	    	weixinCompany.find(".btn-delete").on("click",deleteRow);
	    } );
	}
	
	function add(e){
		system.main.open("${contextPath}/weixin/company/add","新增");
	}
	
	function edit(e){
		var companyId = $(this).data("companyid");
		system.main.open("${contextPath}/weixin/company/edit","修改",{companyId:companyId});
	}
	
	function deleteRow(e){
		var companyId = $(this).data("companyid");
		bootbox.confirm("确定要删除该公司吗？","",function(r){
			if(r){
				W.ajax({
					url : "${contextPath}/weixin/company/delete",
					data: {companyId:companyId},
					success:function(result){
						if(SUCCESS == result.result){
							bootbox.alert("删除成功","提示",function(){
								dataTable.ajax.reload();
							});
						}else{
							bootbox.alert("数据异常，删除失败");
						}
					}
				});
			}
		});
	}
	
	function query(){
		dataTable.ajax.reload();
	}
	
	return {
		init : function(){
			weixinCompany = $("#weixin-company");
			initTable();
			weixinCompany.find("#query").on("click",query);
			weixinCompany.find("#add").on("click",add);
		} 
	}
}();

$().ready(function(){
	weixin.company.init();
});


</script>
<div class="container-content" id="weixin-company">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
					<label class="col-sm-2 control-label">公司/品牌</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="companyName" placeholder="公司/品牌">
					</div>
					<div class="col-sm-8">
						<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i>查询</a>
						<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i>新增</a>
					</div>
			    </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="companyTab">
		</table>
	</div>
</div>