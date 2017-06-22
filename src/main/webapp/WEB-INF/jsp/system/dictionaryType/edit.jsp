<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.dictionaryType.edit");
system.dictionaryType.edit = function(){
	var systemDictionaryTypeEdit ,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = systemDictionaryTypeEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		dictionaryTypeId: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写类型编码'
	                    }
	                }
	            },
	            dictionaryTypeName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写类型名称'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
		 
		 systemDictionaryTypeEdit.find('#describe').autosize({ append: "\n" });
	}
	
	function save(){
		var url;
		if("${dictionaryType.dictionaryTypeId}" == ""){
			url =  "${ contextPath }/system/dictionaryType/addSave";
		}else{
			url =  "${ contextPath }/system/dictionaryType/editSave";
		}
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = systemDictionaryTypeEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url :url,
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
							bootbox.hideAll();
							var treeObj = system.dictionary.tree();
	    					treeObj.reAsyncChildNodes(null, "refresh");
    					}else{
    						bootbox.alert("数据异常，保存失败");
    					}
    				}
    		};
    		W.ajax(opt);
    	}
	}
	
	return {
		init: function(){
			systemDictionaryTypeEdit = $("#system-dictionaryType-edit");
			initValidator();
			systemDictionaryTypeEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	system.dictionaryType.edit.init();
});
</script>
<div class="container-content" id="system-dictionaryType-edit">
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="widget flat radius-bordered">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">类型编码</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-user-md circular"></i>
	                                    <input type="text" class="form-control" name="dictionaryTypeId" placeholder="类型编码" value="${dictionaryType.dictionaryTypeId }" ${dictionaryType.dictionaryTypeId==null ? "" : 'readonly="readonly"' }>
                                   	</span>
                            	</div>
                               
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">类型名称</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-group circular"></i>
	                                    <input type="text" class="form-control" id="dictionaryTypeName" name="dictionaryTypeName" placeholder="类型名称" value="${dictionaryType.dictionaryTypeName }" >
                                   	</span>
                            	</div>
                            </div>
                            
                       		<div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">描述</label>
                            	<div class="col-xs-10">
                                       <textarea class="form-control" id="describe" name="describe">${dictionaryType.describe }</textarea>
                            	</div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>