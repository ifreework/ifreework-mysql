<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/dropzone/dropzone.css"></link>
<script src="${ jsPath }/bootstrap/dropzone/dropzone.js"></script>
<script type="text/javascript">
$.namespace("system.workFlow.add");
system.workFlow.add = function(){
	var dropzone;
	function initDropzone(){
		dropzone = new Dropzone("#system-workFlow-add #dropzone", {
			url : "${ contextPath }/system/workFlow/saveDeploye",
			autoProcessQueue : true,
			addRemoveLinks:true,
			maxFilesize:100,
			acceptedFiles:'.zip',
			success:function(file,data){
				$(file.previewElement).addClass("dz-success");
				setTimeout(bootbox.hideAll(),2000);
			}
		});
	}
	
	return {
		init : function(){
			initDropzone();
		}
	}
}();

$().ready(function(){
	system.workFlow.add.init();
});
</script>

<div class="container-content" id="system-workFlow-add">
	<div class="container-body">
		<div class="col12">
			<div id="dropzone" class="dropzone ">
			</div>
		</div>
	</div>
</div>