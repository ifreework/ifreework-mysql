<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/dropzone/dropzone.css"></link>
<script src="${ jsPath }/bootstrap/dropzone/dropzone.js"></script>
<script type="text/javascript">

$.namespace("system.attachment");

system.attachment = function(){
	var dropzone;
	function initDropzone(){
		dropzone = new Dropzone("#system-attachment #dropzone", {
			url : "${ contextPath }/system/attachment/fileUpload",
			autoProcessQueue : true,
			addRemoveLinks:true,
			maxFilesize:100,
			success:function(file,data){
				console.log(file);
				console.log(data);
				file.attachmentId = data.attachmentId;
				
				$(file.previewElement).addClass("dz-success");
			},
			removedfile:function(file){
				console.log(file);
				var opt = {
					url:"${contextPath}//system/attachment/delete",
					data:{
						attachmentId:file.attachmentId
					},
					success:function(){
						$(file.previewElement).remove();
					}
				}
				W.ajax(opt);
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
	system.attachment.init();
});
</script>

<div class="container-content" id="system-attachment">
	<div class="container-body">
			<div class="col12">
				<div id="dropzone" class="dropzone ">
				</div>
		</div>
		<div class="text-center container-footer">
			<a class="btn btn-info" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
		</div>
	</div>
</div>