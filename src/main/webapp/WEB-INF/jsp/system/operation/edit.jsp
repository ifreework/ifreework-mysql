<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
(function(){
	var $operationEdit = $("#operationEdit");
$().ready(function(){
	 var bootstrapValidator = $operationEdit.find("#saveForm").bootstrapValidator({
     	message: '数值未通过验证',
     	fields: {
            operationName: {
                validators: {
                    notEmpty: {
                        message: '请填写操作名'
                    }
                }
            },
            pk: {
                validators: {
                    notEmpty: {
                        message: '请选择操作类型'
                    },
                    remote : {
                        message: '该PK值已存在！',
                        async : false,
                        url: '${ contextPath }/system/operation/validate?operationId=${operation.operationId}'
                    }
                }
            },
            operationType: {
                validators: {
                    notEmpty: {
                        message: '请选择操作类型'
                    }
                }
            },
            operationLevel: {
                validators: {
                    notEmpty: {
                        message: '请输入操作等级'
                    }
                }
            }
		}
	}).data('bootstrapValidator');
	
	$operationEdit.find("#operationType").select2({
		placeholder: "操作类型",
		minimumResultsForSearch: Infinity,
        allowClear: true
	});
	
    $operationEdit.find('#remarks').autosize({ append: "\n" });
    
    $operationEdit.find("#btn-save").click(function(){
    	
    	if(bootstrapValidator.validate().isValid()){
    		var data = $operationEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/system/operation/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
    						bootbox.alert("数据保存成功","",function(){
    							bootbox.hideAll();
    							$('#operation').data("dataTable").ajax.reload();
    						});
    					}else{
    						bootbox.alert("数据异常，保存失败");
    					}
    				}
    		};
    		W.ajax(opt);
    	}
    });
});
}());
</script>
<div class="container-content" id="operationEdit">
	<div class="container-body">
	<div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12">
                 <div id="registration-form">
                      <form id="saveForm" method="post" class="form-horizontal">
                         <div class="form-group has-feedback row">
                         	<label class="col-xs-2 control-label">操作名</label>
                         	<div class="col-xs-10">
                         		<span class="input-icon icon-left">
                          			<i class="fa fa-wrench circular"></i>
                                  	<input type="text" class="form-control" name="operationName" placeholder="操作名" value="${operation.operationName }">
                                </span>
                         	</div>
                            
                         </div>
                         <div class="form-group has-feedback row">
                         	<label class="col-xs-2 control-label">PK</label>
                         	<div class="col-xs-10">
                         		<span class="input-icon icon-left">
                          			<i class="fa fa-anchor circular"></i>
                                  	<input type="text" class="form-control" name="pk" placeholder="PK" value="${operation.pk }" >
                                </span>
                         	</div>
                         </div>
                         <div class="form-group has-feedback row">
                         	<label class="col-xs-2 control-label">操作类型</label>
                         	<div class="col-xs-10">
	                          	<select  id="operationType" name="operationType" style="width: 100%;">
	                          		<option></option>
	                          		<option value="visit" ${ operation.operationType == "visit" ? "selected='selected'" : "" }>访问</option>
									<option value="data" ${ operation.operationType == "data" ? "selected='selected'" : "" }>数据</option>
									<option value="button" ${ operation.operationType == "button" ? "selected='selected'" : "" }>按钮</option>
								</select>
                         	</div>
                         </div>
                         
                         <div class="form-group has-feedback row">
                         	<label class="col-xs-2 control-label">操作等级</label>
                         	<div class="col-xs-10">
                         		<span class="input-icon icon-left">
                          			<i class="fa fa-level-up circular"></i>
                                  	<input type="text" class="form-control" name="operationLevel" placeholder="操作等级，数字越大，等级越高" value="${operation.operationLevel }">
                                </span>
                         	</div>
                            
                         </div>
                         
                    	<div class="form-group has-feedback row">
                         	<label class="col-xs-2 control-label">说明</label>
                         	<div class="col-xs-10">
                            	<textarea class="form-control" id="remarks" name="remarks">${operation.remarks }</textarea>
                         	</div>
                        </div>
                        <input type="hidden" name="operationId" value="${operation.operationId }">
                     </form>
         </div>
        </div>
	</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>