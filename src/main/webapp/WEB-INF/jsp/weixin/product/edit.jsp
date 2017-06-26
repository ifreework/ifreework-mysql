<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/summernote/summernote.css"></link>
<script src="${ jsPath }/bootstrap/editors/summernote/summernote.js"></script>


<script type="text/javascript">
$.namespace("weixin.product.edit");
weixin.product.edit = function(){
	var weixinProductEdit ,
		dropzone,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = weixinProductEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		productName: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入产品名称'
	                    }
	                }
	            },
	            productTypeId: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择产品分类'
	                    }
	                }
	            },
	            price: {
	                validators: {
	                	numeric: {
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
		 
		 weixinProductEdit.find('#remarks').autosize({ append: "\n" });
	}
	
	
	function initSelect2(){
		weixinProductEdit.find("#productTypeId").select2({
			placeholder: "选择产品分类",
			minimumResultsForSearch: Infinity,
	        allowClear: true,
			ajax: {
			    url: "${ contextPath }/weixin/productType/queryList",
			    delay: 250,
			    minimumResultsForSearch: Infinity,
			    dataType: 'json',
				data: function (params) {
				    return {
				    	isLeaf: "1"
				    };
				},
			    processResults: function (data, params) {
			    	for(var i= 0 ;i < data.length;i++ ){
			    		data[i].id=data[i].productTypeId
			    		data[i].text=data[i].productTypeName
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
    		var content = weixinProductEdit.find("#summernote").summernote("code");
    		
    		var data = weixinProductEdit.find("#saveForm").serializeJson();
    		data.content = content;
    		var opt = {
    				url : "${ contextPath }/weixin/product/save",
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
			id:"weixinProductEditDialog",
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
						weixinProductEdit.find("#img-upload").html('<img alt="图片加载失败" src="' + file.data + '">');
						weixinProductEdit.find("#image").val(file.data);
					}
				}
			}
		});
	}
	
	return {
		init: function(){
			weixinProductEdit = $("#weixin-product-edit");
			initValidator();
			initSelect2();
			weixinProductEdit.find('#summernote').summernote({ minHeight: 300 });
			weixinProductEdit.find("#img-upload").on("click",openDialog);
			weixinProductEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	weixin.product.edit.init();
});
</script>
<div class="container-content no-margin" id="weixin-product-edit">
	<div class="container-title">
		<span class="icon"><i class="fa fa-book"></i></span>
         <span class="text">文章发布</span>
    </div>
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">产品名称</label>
                            	<div class="col-xs-11">
	                                    <input type="text" class="form-control" name="productName" placeholder="产品名称" value="${product.productName }">
                            	</div>
                               
                            </div>
                            
                             <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">产品分类</label>
                            	<div class="col-xs-11">
	                            	<select  name="productTypeId" id="productTypeId" style="width: 100%;">
	                            	<c:if test="${article.articleTypeId != null && article.articleTypeId != '' }">
	                            	<option value="${article.articleTypeId}">${article.articleTypeName}</option>
	                            	</c:if>
	                            	</select>
                            	</div>
                               
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">规格型号</label>
                            	<div class="col-xs-11">
	                                    <input type="text" class="form-control" name="specificationModel" placeholder="规格型号" value="${product.specificationModel }">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">产品价格(元)</label>
                            	<div class="col-xs-11">
	                                    <input type="text" class="form-control" name="price" placeholder="产品价格" value="${product.price }">
                            	</div>
                               
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label">配图</label>
                            	<div class="col-xs-11">
                            		<span id="img-upload" style="cursor: pointer;">
                            		<c:if test="${product.image == null || product.image == '' }">
                            			图片上传
                            		</c:if>
                            		<c:if test="${product.image != null && product.image != '' }">
                            			<img alt="图片加载失败" src="${product.image}" style="width: 50px;">
                            		</c:if>
                            		</span>
									<input type="hidden" name="image" id="image">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-1 control-label"></label>
                            	<div class="col-xs-11">
                            		<div id="summernote">${product.content }</div>
                            	</div>
                            </div>
                            <input type="hidden" id="productId" name="productId" value="${product.productId }">
                        </form>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>