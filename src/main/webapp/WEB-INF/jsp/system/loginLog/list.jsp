<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("system.loginLog");

system.loginLog = function(){
	var systemLoginLog,
		dataTable;
	
	function initTable(){
		dataTable = systemLoginLog.find('#loginLogTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			order:[[5,'desc']],
			ajax:{
				url:"${contextPath}/system/loginLog/query",
				data: function ( d ) {
		      		return $.extend( {}, d, systemLoginLog.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "username",  
	            name : "username",  
	            title : "用户名",  
	            defaultContent : "" 
	        }, {  
	            data : "ip",  
	            title : "IP",  
	            defaultContent : "" ,
	            orderable:false
	        },{  
	        	data : "browser",  
	            title : "浏览器",  
	            defaultContent : "",
	            orderable:false,
	            render:function( data, type, row, meta ){
	            	return data + " (" + row.browserVersion + ")"
	            }
	        },{  
	        	data : "deviceType",  
	            title : "设备类型",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "osInfo",  
	            title : "操作系统",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "loginTime",  
	        	name : "LOGIN_TIME",
	            title : "登录时间",  
	            defaultContent : ""
	        },{  
	        	data : "logoutTime",  
	        	name : "LOGOUT_TIME",
	            title : "退出时间",  
	            defaultContent : ""
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
			systemLoginLog = $("#system-loginLog");
			initTable();
			systemLoginLog.find("#query").on("click",query);
		} 
	}
}();

$().ready(function(){
	system.loginLog.init();
});


</script>
<div class="container-content" id="system-loginLog">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				 <div class="has-feedback row">
				<label class="col-sm-1 control-label">用户名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="username" placeholder="用户名">
				</div>
				<div class="col-sm-1">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="loginLogTab">
		</table>
	</div>
</div>