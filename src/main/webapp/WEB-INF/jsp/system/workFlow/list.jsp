<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("system.workFlow");

system.workFlow = function(){
	var systemWorkFlow,
		dialog,
		dataTable;
	
	function initTable(){
		dataTable = systemWorkFlow.find('#workFlowTab').DataTable({
			searching:false,//
			lengthChange: false,
			autoWidth: false,
	    });
	}
	
	
	function add(){
		dialog = bootbox.dialog({
			id:"workFlowDialog",
			title: "上传流程附件",
			width:700,
	        loadUrl: "${contextPath}/system/workFlow/add"
	    });
	}
	
	return {
		init : function(){
			systemWorkFlow = $("#system-workFlow");
			initTable();
			systemWorkFlow.find("#add").on("click",add);
		} 
	}
}();

$().ready(function(){
	system.workFlow.init();
});


</script>
<div class="container-content" id="system-workFlow">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
					<div class="col-sm-12">
						<a id="add" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i>添加</a>
					</div>
			   	</div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="workFlowTab">
			<thead>
				<tr>
					<th>ID</th>
					<th>流程名称</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ deployments }" var="deployment">
				<tr>
					<td>${deployment.id }</td>
					<td>${deployment.name }</td>
					<td><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
					<td></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>