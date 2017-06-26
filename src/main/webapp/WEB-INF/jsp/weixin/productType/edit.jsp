<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("weixin.productType.edit");

weixin.productType.edit = function(){
	var bootstrapValidator ,
		weixinProductTypeEdit ;
	
	function initValidator(){
		bootstrapValidator = weixinProductTypeEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	            productTypeName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写产品类型名称'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
		
		weixinProductTypeEdit.find('#remark').autosize({ append: "\n" });
		
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = weixinProductTypeEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/weixin/productType/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
							bootbox.hideAll();
							system.main.refresh();
    					}else{
    						bootbox.alert("数据异常，保存失败");
    					}
    				}
    		};
    		W.ajax(opt);
    	}
	}
	
	return {
		init:function(){
			weixinProductTypeEdit = $("#weixin-productType-edit");
			initValidator();
			weixinProductTypeEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	weixin.productType.edit.init();
});
</script>
<div class="container-content container-plain " id="weixin-productType-edit">
	<div class="container-body">
	   	<div class="row">
	           <div class="col-lg-12 col-sm-12 col-xs-12">
	                    <div id="registration-form">
	                         <form id="saveForm" method="post" class="form-horizontal">
	                            <div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">产品类型</label>
	                            	<div class="col-xs-10">
		                            	<input type="text" class="form-control" id="productTypeName" name="productTypeName" placeholder="产品类型" value="${productType.productTypeName }" >
	                            	</div>
	                            </div>
	                            
	                       		<div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">说明</label>
	                            	<div class="col-xs-10">
	                                	<textarea class="form-control" id="remark" name="remark">${productType.remark }</textarea>
	                            	</div>
	                            </div>
	                            
	                            <input type="hidden" id="productTypeId" name="productTypeId" value="${productType.productTypeId }">
	                            <input type="hidden" name="parentId" value="${productType.parentId }">
	                        </form>
	                    </div>
	                </div>
	   	</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>