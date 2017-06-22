<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.authority");

system.authority = function(){
	var systemAuthority,
		dataTable;
	
	function initTable(){
		dataTable = systemAuthority.find('#authorityTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/system/authority/query",
				data: function ( d ) {
		      		return $.extend( {}, d, systemAuthority.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "pk",  
	            name : "pk",  
	            title : "pk码",  
	            defaultContent : "" 
	        }, {  
	            data : "authorityName",  
	            name : "authority_name",
	            title : "权限名称",  
	            defaultContent : "" 
	        },{  
	        	data : "remarks",  
	        	name : "remarks",
	            title : "说明",  
	            defaultContent : "",
	            orderable:false
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } );
	}
	
	function query(){
		dataTable.ajax.reload();
	}
	
	return {
		init : function(){
			systemAuthority = $("#system-authority");
			initTable();
			systemAuthority.find("#query").on("click",query);
		} 
	}
}();

$().ready(function(){
	system.authority.init();
});

</script>
<div class="container-content" id="system-authority">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				 <div class="has-feedback row">
				<label class="col-sm-1 control-label">pk码</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="pk" placeholder="pk码">
				</div>
				<label class="col-sm-1 control-label">权限名称</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="authorityName" placeholder="权限名称">
				</div>
				<div class="col-sm-1">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="authorityTab">
		</table>
	</div>
</div>