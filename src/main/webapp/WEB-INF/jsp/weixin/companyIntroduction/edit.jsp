<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/summernote/summernote.css"></link>
<script src="${ jsPath }/bootstrap/editors/summernote/summernote.js"></script>


<script type="text/javascript">
$.namespace("weixin.companyIntroduction.edit");
weixin.companyIntroduction.edit = function(){
	var weixinCompanyIntroductionEdit ,
		dropzone,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = weixinCompanyIntroductionEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		title: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写文章标题'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var content = weixinCompanyIntroductionEdit.find("#summernote").summernote("code");
    		
    		var data = weixinCompanyIntroductionEdit.find("#saveForm").serializeJson();
    		data.content = content;
    		var opt = {
    				url : "${ contextPath }/weixin/companyIntroduction/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
    						bootbox.alert("发布成功。","",function(){
    							system.main.history();
    						});
    					}else{
    						bootbox.alert("数据异常，保存失败");
    					}
    				}
    		};
    		W.ajax(opt);
    	}
	}
	
	function openDialog(){
		var dialog = bootbox.dialog({
			id:"weixinCompanyIntroductionEditDialog",
			title: "图像上传",
			width:700,
			loadUrl: "${contextPath}/weixin/article/img",
			buttons : {
				success : {
					label : "确定",
					className : "btn-default",
					callback : function(){
						var file = weixin.article.img.getImage();
						console.log(file);
						weixinCompanyIntroductionEdit.find("#img-upload").html('<img alt="图片加载失败" src="' + file.data + '">');
						weixinCompanyIntroductionEdit.find("#image").val(file.data);
					}
				}
			}
		});
	}
	
	return {
		init: function(){
			weixinCompanyIntroductionEdit = $("#weixin-companyIntroduction-edit");
			initValidator();
			weixinCompanyIntroductionEdit.find('#summernote').summernote({ minHeight: 300 });
			weixinCompanyIntroductionEdit.find("#img-upload").on("click",openDialog);
			weixinCompanyIntroductionEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	weixin.companyIntroduction.edit.init();
});
</script>
<div class="container-content no-margin" id="weixin-companyIntroduction-edit">
	<div class="container-title">
		<span class="icon"><i class="fa fa-book"></i></span>
         <span class="text">添加公司介绍</span>
    </div>
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">标题</label>
                            	<div class="col-xs-11">
	                                    <input type="text" class="form-control" name="title" placeholder="文章标题" value="${companyIntroduction.title }">
                            	</div>
                               
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label no-padding">配图</label>
                            	<div class="col-xs-11">
                            		<span id="img-upload" style="cursor: pointer;">
                            		<c:if test="${companyIntroduction.image == null || companyIntroduction.image == '' }">
                            			图片上传
                            		</c:if>
                            		<c:if test="${companyIntroduction.image != null && companyIntroduction.image != '' }">
                            			<img alt="图片加载失败" src="${companyIntroduction.image}" style="width: 50px;">
                            		</c:if>
                            		</span>
									<input type="hidden" name="image" id="image" value="${companyIntroduction.image}">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label"></label>
                            	<div class="col-xs-11">
                            		<div id="summernote">${companyIntroduction.content }</div>
                            	</div>
                            </div>
                            
                            <input type="hidden" id="companyIntroductionId" name="companyIntroductionId" value="${companyIntroduction.companyIntroductionId }">
                        </form>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>