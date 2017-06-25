<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/summernote/summernote.css"></link>
<script src="${ jsPath }/bootstrap/editors/summernote/summernote.js"></script>


<script type="text/javascript">
$.namespace("weixin.article.edit");
weixin.article.edit = function(){
	var weixinArticleEdit ,
		dropzone,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = weixinArticleEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		title: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写文章标题'
	                    }
	                }
	            },
	            articleTypeId: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择文章分类'
	                    }
	                }
	            },
	            tel: {
	                validators: {
	                    phone:{
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
		 
		 weixinArticleEdit.find('#remarks').autosize({ append: "\n" });
	}
	
	
	function initSelect2(){
		weixinArticleEdit.find("#articleTypeId").select2({
			placeholder: "选择文章分类",
	        allowClear: true,
			ajax: {
			    url: "${ contextPath }/system/dictionary/queryByCodeList",
			    delay: 250,
			    minimumResultsForSearch: Infinity,
			    dataType: 'json',
				data: function (params) {
				    return {
				    	dictionaryTypeId: "weixin", 
				    	code: "article"
				    };
				},
			    processResults: function (data, params) {
			    	for(var i= 0 ;i < data.length;i++ ){
			    		data[i].id=data[i].dictionaryCode
			    		data[i].text=data[i].dictionaryName
			    	}
	                return {
	                   results: data
	                };
		        },
			    cache: true
			}
		})
	}
	
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var content = weixinArticleEdit.find("#summernote").summernote("code");
    		
    		var data = weixinArticleEdit.find("#saveForm").serializeJson();
    		data.content = content;
    		var opt = {
    				url : "${ contextPath }/weixin/article/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
    						bootbox.alert("文章发布成功。","",function(){
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
			id:"weixinArticleEditDialog",
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
						weixinArticleEdit.find("#img-upload").html('<img alt="图片加载失败" src="' + file.data + '" style="width: 50px;">');
						weixinArticleEdit.find("#image").val(file.data);
					}
				}
			}
		});
	}
	
	return {
		init: function(){
			weixinArticleEdit = $("#weixin-article-edit");
			initValidator();
			initSelect2();
			weixinArticleEdit.find('#summernote').summernote({ minHeight: 300 });
			weixinArticleEdit.find("#img-upload").on("click",openDialog);
			weixinArticleEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	weixin.article.edit.init();
});
</script>
<div class="container-content no-margin" id="weixin-article-edit">
	<div class="container-title">
		<span class="icon"><i class="fa fa-book"></i></span>
         <span class="text">文章发布</span>
    </div>
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">标题</label>
                            	<div class="col-xs-11">
	                                    <input type="text" class="form-control" name="title" placeholder="文章标题" value="${article.title }">
                            	</div>
                               
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">分类</label>
                            	<div class="col-xs-11">
	                            	<select class="form-control" name="articleTypeId" id="articleTypeId">
	                            	<c:if test="${article.articleTypeId != null && article.articleTypeId != '' }">
	                            	<option value="${article.articleTypeId}">${article.articleTypeName}</option>
	                            	</c:if>
	                            	</select>
                            	</div>
                               
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">配图</label>
                            	<div class="col-xs-11">
                            		<span id="img-upload" style="cursor: pointer;">
                            		<c:if test="${article.image == null || article.image == '' }">
                            			图片上传
                            		</c:if>
                            		<c:if test="${article.image != null && article.image != '' }">
                            			<img alt="图片加载失败" src="${article.image}" style="width: 50px;">
                            		</c:if>
                            		</span>
									<input type="hidden" name="image" id="image">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label"></label>
                            	<div class="col-xs-11">
                            		<div id="summernote">${article.content }</div>
                            	</div>
                            </div>
                            
                            <input type="hidden" id="articleId" name="articleId" value="${article.articleId }">
                        </form>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>