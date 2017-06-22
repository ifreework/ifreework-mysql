<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("attendance.leaveBill");

attendance.leaveBill = function(){
	var attendanceLeaveBill,
		dataTable;
	
	function initTable(){
		dataTable = attendanceLeaveBill.find('#leaveBillTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			order:[[1,'desc']],
			ajax:{
				url:"${contextPath}/attendance/leaveBill/query",
				data: function ( d ) {
		      		return $.extend( {}, d, attendanceLeaveBill.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "dictionary.dictionaryName",  
	            name : "leave_type",  
	            title : "请假类型",  
	            defaultContent : "" 
	        }, {  
	            data : "startTime", 
	            name : "start_time",  
	            title : "开始时间",  
	            defaultContent : "" 
	        }, {  
	            data : "endTime", 
	            name : "end_time",  
	            title : "结束时间",  
	            defaultContent : "" 
	        }, {  
	        	data : "leaveDays",  
	            title : "请假天数",  
	            defaultContent : "",
	            orderable:false
	        }, {  
	        	data : "leaveCause",  
	            title : "请假原因",  
	            defaultContent : "",
	            orderable:false
	        }, {  
	        	data : "taskName",  
	            title : "单据状态",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "lastOperationUser",  
	            title : "最后一次操作人",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "attachments",  
	            title : "附件",  
	            defaultContent : "",
	            orderable:false,
	            render:function( data, type, row, meta ){
	            	console.log(data);
	            	var attStr = "";
	            	if(data != null){
	            		
		            	for(var i = 0;i < data.length;i++){
		            		var html = '<a href="javascript:void(0)" class="tab-attachment" data-attachmentid="' + data[i].attachmentId + '">' + data[i].attachmentName + '</a>';
		            		attStr += html;
		            		if(i < data.length - 1 ){
		            			attStr += "<br />";
		            		}
		            	}
	            	}
	            	return attStr;
	            }
	        },{  
	        	title : "操作",  
	        	data : "status",  
	        	defaultContent : "", 
	        	searchable:false,
	        	orderable:false,
	        	className : "text-center"  ,
	        	render:function( data, type, row, meta ){
	        		var html = '<a class="btn btn-view btn-sky btn-xs icon-only" title="查看审批详情" data-leavebillid="' + row.leaveBillId + '" data-person="' + row.user.personName + '" href="javascript:void(0);"><i class="fa fa-info "></i></a>';
	        		if(data == '0'){
	        			html +=  '<a class="btn btn-submit btn-palegreen btn-xs icon-only margin-left-10" title="提报" data-processid="' + row.processId + '" href="javascript:void(0);"><i class="fa  fa-check-square-o "></i></a>' +
	        					 '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-leavebillid="' + row.leaveBillId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
	        			  		 '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-leavebillid="' + row.leaveBillId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>';
	        		}
	        		return html;
	        	}
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	attendanceLeaveBill.find('.tab-attachment').on("click",function(){
	    		var id = $(this).data("attachmentid");
	    		bootbox.image("${contextPath}/system/attachment/download?attachmentId=" + id);
	    	});
	    	
	    	attendanceLeaveBill.find('.btn-view').on("click",function(){
	    		var leaveBillId =  $(this).data("leavebillid");
	    		var person = $(this).data("person");
	    		var url = "${contextPath}/attendance/leaveBill/detail?leaveBillId=" + leaveBillId ;
	    		openDialog(url , person + "的请假申请");
	    	});
	    	
	    	attendanceLeaveBill.find('.btn-submit').on("click",function(){
	    		var id = $(this).data("processid");
	    		saveStartProcess(id);
	    	});
	    	
	    	attendanceLeaveBill.find('.btn-edit').on("click",function(){
	    		var id = $(this).data("leavebillid");
	    		edit(id);
	    	});
	    	
	    	attendanceLeaveBill.find('.btn-delete').on("click",function(){
	    		var id = $(this).data("leavebillid");
	    		deleteData(id);
	    	});
	    });
	}
	
	function openDialog(url,title){
		var dialog = bootbox.dialog({
			title: title,
			width:700,
	        loadUrl: url
	    });
	}
	
	//提报
	function saveStartProcess(id){
		bootbox.confirm("信息提报后将不能进行修改，确定要提交该请假申请吗？","",function(e){
			if(e){
				W.ajax({
					url : "${ contextPath }/attendance/leaveBill/submit",
					data:{ processId : id },
					success:function(param){
						if(param.result === SUCCESS){
							bootbox.alert("数据提报成功","",function(){
								dataTable.ajax.reload();
							});
						}else{
							bootbox.alert("数据异常，提报失败");
						}
					}
				});
			}
		});
	}
	
	//删除
	function deleteData(id){
		bootbox.confirm("确定要删除该请假信息吗？","",function(e){
			if(e){
				W.ajax({
					url : "${ contextPath }/attendance/leaveBill/delete",
					data:{ leaveBillId : id },
					success:function(param){
						if(param.result === SUCCESS){
							bootbox.alert("数据删除成功","",function(){
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
	
	//查询
	function query(){
		dataTable.ajax.reload();
	}
	
	//新增
	function add(){
		system.main.open("${contextPath}/attendance/leaveBill/add","新建请假申请");
	}
	
	//新增
	function edit(id){
		system.main.open("${contextPath}/attendance/leaveBill/edit","修改请假申请",{leaveBillId : id});
	}
	
	return {
		init : function(){
			attendanceLeaveBill = $("#attendance-leaveBill");
			initTable();
			attendanceLeaveBill.find("#query").on("click",query);
			attendanceLeaveBill.find("#add").on("click",add);
		} 
	}
}();

$().ready(function(){
	attendance.leaveBill.init();
});


</script>
<div class="container-content" id="attendance-leaveBill">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
				<label class="col-sm-1 control-label">请假类型</label>
				<div class="col-sm-2">
					<select class="form-control" name="leaveType">
						<option value="">全部</option>
						<option value="sj">事假</option>
						<option value="bj">病假</option>
					</select>
				</div>
				<div class="col-sm-2">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
					<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="leaveBillTab">
		</table>
	</div>
</div>