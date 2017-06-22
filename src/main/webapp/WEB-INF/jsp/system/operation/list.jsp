<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
	(function(){
		var $operation = $("#operation");
		$().ready(function(){
			var dataTable = $operation.find('#operationTab').DataTable({
					searching:false,//
					lengthChange: false,
					serverSide:true, //是否启用服务器模式
					pageLength: 100 ,
					autoWidth: false,
					orderMulti: false,
					ajax:{
						url:"${contextPath}/system/operation/query",
						data: function ( d ) {
							      		return $.extend( {}, d, $operation.find("#queryForm").serializeJson());
						    }
					},
					columns : [{  
						data : "operationName",  
			            name : "operation_name", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
			            title : "操作名",  
			            defaultContent : "" 
			        }, {  
			        	data : "pk",  
			            name : "pk", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
			            title : "PK",  
			            defaultContent : "" 
			        },{  
			        	data : "operationType",  
			        	name : "operation_type",
			        	title : "操作类型",  
			        	defaultContent : ""
			        },{  
			        	data : "operationLevel",  
			        	name : "operation_level",
			        	title : "操作等级",  
			        	defaultContent : ""
			        }, {  
			        	data : "remarks",  
			        	title : "说明",  
			        	defaultContent : "", 
			        	searchable:false,
			        	orderable:false
			        }, {  
			        	title : "",  
			        	defaultContent : "", 
			        	searchable:false,
			        	orderable:false,
			        	className : "text-center"  ,
			        	render:function( data, type, row, meta ){
			        		return '<a class="btn btn-edit btn-info btn-xs icon-only" title="修改" data-operationid="' + row.operationId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
			        		'<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-operationid="' + row.operationId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>';
			        	}
			        }]
		    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
		    	bootbox.load();
		    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
		    	bootbox.unload();
		    } ).on( 'draw.dt', function () {
		    	$operation.find("#operationTab .btn-edit").click(function(){
		    		var id = $(this).data("operationid");
		    		bootbox.dialog({
		    			id:"operationEditDialog",
		    			title: "修改操作",
		    			width:700,
		    			loadData:{ operationId:id },
		    			loadUrl: "${contextPath}/system/operation/update"
		    		});
		    	});
		    	$operation.find("#operationTab .btn-delete").click(function(){
		    		var id = $(this).data("operationid");
		    		bootbox.confirm("确定要删除该数据吗？","",function(e){
		    			if(e){
		    				var opt = {
		    					url : "${ contextPath }/system/operation/delete",
		    					data:{ operationId:id },
		    					success:function(param){
		    						if(param.result === SUCCESS){
		    							dataTable.ajax.reload();
		    						}else{
		    							bootbox.alert("数据异常，保存失败");
		    						}
		    					}
		    				};
		    				ajax(opt);
		    			}
		    		});
		    	});
		    } );
				
			$operation.data("dataTable",dataTable);
			
		    $operation.find("#query").click(function(){
		    	dataTable.ajax.reload();
		    });
		    
		    $operation.find("#add").click(function(){
		    	var dialog = bootbox.dialog({
		    		id:"operationAddDialog",
		    		title: "新增操作",
		    		width:700,
		    		loadUrl: "${contextPath}/system/operation/add"
		    	});
		    });
		});

	}());
</script>
<div class="container-content" id="operation">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
					<label class="col-sm-1 control-label">PK</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="pk" placeholder="PK">
					</div>
					<div class="col-sm-8">
						<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
						<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover" id="operationTab">
		</table>
	</div>
</div>
