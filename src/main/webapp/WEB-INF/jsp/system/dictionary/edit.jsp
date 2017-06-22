<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.dictionary.edit");
system.dictionary.edit = function(){
	var systemDictionaryEdit ,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = systemDictionaryEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		dictionaryCode: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写字典编码'
	                    }
	                }
	            },
	            dictionaryName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写字典名称'
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
		 
		 systemDictionaryEdit.find('#remarks').autosize({ append: "\n" });
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = systemDictionaryEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/system/dictionary/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
							bootbox.hideAll();
							
							console.log(system.dictionary);
							var treeNode = system.dictionary.treeNode();
							var treeObj = system.dictionary.tree();
							
							if(systemDictionaryEdit.find("#dictionaryId").val()){
								treeNode.name = systemDictionaryEdit.find("#dictionaryName").val();
								treeObj.updateNode(treeNode);
							}else{
    							if(treeNode != null){
    								treeNode.isParent = true ;
    							}
	    						treeObj.reAsyncChildNodes(treeNode, "refresh");
							}
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
			systemDictionaryEdit = $("#system-dictionary-edit");
			initValidator();
			systemDictionaryEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	system.dictionary.edit.init();
});
</script>
<div class="container-content" id="system-dictionary-edit">
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="widget flat radius-bordered">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">字典编码</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-user-md circular"></i>
	                                    <input type="text" class="form-control" name="dictionaryCode" placeholder="字典编码" value="${dictionary.dictionaryCode }">
                                   	</span>
                            	</div>
                               
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">字典名称</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-group circular"></i>
	                                    <input type="text" class="form-control" id="dictionaryName" name="dictionaryName" placeholder="字典名称" value="${dictionary.dictionaryName }" >
                                   	</span>
                            	</div>
                            </div>
                            
                       		<div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">其他</label>
                            	<div class="col-xs-10">
                                       <textarea class="form-control" id="describe" name="describe">${dictionary.describe }</textarea>
                            	</div>
                            </div>
                            
                            <input type="hidden" id="dictionaryId" name="dictionaryId" value="${dictionary.dictionaryId }">
                            <input type="hidden" id="dictionaryTypeId" name="dictionaryTypeId" value="${dictionary.dictionaryTypeId }">
                            <input type="hidden" name="parentId" value="${dictionary.parentId }">
                        </form>
                    </div>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>