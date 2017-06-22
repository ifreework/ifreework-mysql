<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.user")
system.user = function(){
	var systemUser,//页面对象
		dataTable ;
	
	function initDatable(){
		dataTable = systemUser.find('#userTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			order:[[0,'asc']],
			pageLength: 100 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/system/user/query",
				data: function ( d ) {
		      		return $.extend( {}, d, systemUser.find("#queryForm").serializeJson());
			    }
			},
			
			columns : [{  
	            data : "userNo",  
	            name : "user_no", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
	            title : "编号",  
	            defaultContent : "" 
	        }, {  
	            data : "username",  
	            name : "username", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
	            title : "用户名",  
	            defaultContent : "" 
	        },{  
	        	data : "personName",  
	        	name : "person_name",
	            title : "姓名",  
	            defaultContent : ""
	        }, {  
	        	data : "email",  
	            title : "电子邮箱",  
	            defaultContent : "", 
	            searchable:false,
	            orderable:false
	        }, {  
	        	data : "phone",  
	        	title : "联系电话",  
	        	defaultContent : "", 
	        	searchable:false,
	            orderable:false
	        }, {  
	        	data : "deailAddress",  
	        	title : "家庭住址",  
	        	defaultContent : "", 
	        	searchable:false,
	        	orderable:false,
	        	render:function( data, type, row, meta ){
	        		var provinceName = row.province.provinceName == null ? "" : row.province.provinceName,
	        			municipalityName = row.municipality.municipalityName == null ? "" : row.municipality.municipalityName,
	        			countyName = row.county.countyName == null ? "" : row.county.countyName;	
	        		return provinceName + " " + municipalityName + " " +  countyName + " " + data;
	        	}
	        },{  
	        	data : "status",  
	        	title : "是否启用",  
	        	defaultContent : "", 
	        	searchable:false,
	        	orderable:false,
	        	className : "text-center",
	        	render:function( data, type, row, meta ){
	        		return ' <label class="no-margin-bottom "> ' +
		                	   '<input class="checkbox-slider toggle colored-blue status-enable" type="checkbox" data-userid="' + row.userId + '" ' + (data == 1 ? 'checked="checked"' : '') + '>' +
		                	   '<span class="text"></span>' + 
	            		   '</label>';
	        	}
	        },{  
	        	title : "",  
	        	defaultContent : "", 
	        	searchable:false,
	        	orderable:false,
	        	className : "text-center"  ,
	        	render:function( data, type, row, meta ){
	        		return '<a class="btn btn-edit btn-info btn-xs icon-only" title="修改" data-userid="' + row.userId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
	        			   '<a class="btn btn-reset btn-warning btn-xs icon-only margin-left-10" title="重置密码" data-userid="' + row.userId + '" href="javascript:void(0);"><i class="fa fa-eraser "></i></a>' + 
	        			   '<a class="btn btn-role btn-sky btn-xs icon-only margin-left-10" title="添加角色" data-userid="' + row.userId + '" href="javascript:void(0);"><i class="fa  fa-female "></i></a>';
	        	}
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	 systemUser.find(".status-enable").change(function(){
	    		 changeStatus(this);
	    	 });
	    	 
	    	 systemUser.find(".btn-edit").click(function(){
	    		 var userId = $(this).data("userid");
	    		 system.main.open("${contextPath}/system/user/edit","编辑用户",{userId:userId});
	    	 });
	    	 
	    	 systemUser.find(".btn-reset").click(function(){
	    		 resetPwd(this);
	    	 });
	    	 systemUser.find(".btn-role").click(function(){
	    		 var userId = $(this).data("userid");
	    		 system.main.open("${contextPath}/system/userrole","添加角色",{userId:userId});
	    	 });
	    } );
	}
	
	
	function initAdd(){
		systemUser.find("#add").on("click",function(){
			system.main.open("${contextPath}/system/user/add","新建用户");
		});
	}
	
	//用户启用或者停用
	function changeStatus(e){
		var userId = $(e).data("userid"),
			data = {userId:userId,status:e.checked ? 1 : 0},
		    opt = {
				url : "${contextPath}/system/user/save",
				data:data,
				success:function(param){
					if(param.result === SUCCESS){
					}else{
						bootbox.alert("数据异常，设置失败");
					}
				}
		};
		W.ajax(opt);
	}


	//用户启用或者停用
	function resetPwd(e){
		var userId = $(e).data("userid"),
			data = {userId:userId},
		    opt = {
				url : "system/user/resetPwd",
				data:data,
				success:function(param){
					if(param.result === SUCCESS){
						bootbox.alert("密码重置成功");
					}else{
						bootbox.alert("数据异常，设置失败");
					}
				}
		};
		
		bootbox.confirm("确定要重置该用户的密码吗?","",function(r){
			if(r){
				W.ajax(opt);
			}
		});
		
	}
	return {
		init:function(){
			systemUser = $("#system-user");
			initDatable();
			systemUser.find("#query").click(function(){
				dataTable.ajax.reload();
			});
			initAdd();
		}
	}
}();

$().ready(function(){
	system.user.init();
});
</script>
<div class="container-content" id="system-user">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				 <div class="has-feedback row">
				<label class="col-sm-1 control-label">姓名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="username" placeholder="姓名/用户名">
				</div>
				<div class="col-sm-8">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
					<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="userTab">
		</table>
	</div>
</div>