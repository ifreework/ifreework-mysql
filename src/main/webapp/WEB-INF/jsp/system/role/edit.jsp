<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("system.role.edit");

system.role.edit = function(){
	var bootstrapValidator ,
		systemRoleEdit ;
	
	function initValidator(){
		bootstrapValidator = systemRoleEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	            roleName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写角色名称'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
		
		systemRoleEdit.find('#remarks').autosize({ append: "\n" });
		
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = systemRoleEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/system/role/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
							bootbox.hideAll();
							
							var treeObj = system.role.tree();;
							var roleTreeNode = system.role.treeNode();
							
							if(systemRoleEdit.find("#roleId").val()){
								roleTreeNode.name = systemRoleEdit.find("#roleName").val();
								treeObj.updateNode(roleTreeNode);
							}else{
    							if(roleTreeNode != null){
    								roleTreeNode.isParent = true ;
    							}
	    						treeObj.reAsyncChildNodes(roleTreeNode, "refresh");
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
		init:function(){
			systemRoleEdit = $("#system-role-edit");
			initValidator();
			systemRoleEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	system.role.edit.init();
});
</script>
<div class="container-content" id="system-role-edit">
	<div class="container-body">
	   	<div class="row">
	           <div class="col-lg-12 col-sm-12 col-xs-12">
	                    <div id="registration-form">
	                         <form id="saveForm" method="post" class="form-horizontal">
	                            <div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">角色名称</label>
	                            	<div class="col-xs-10">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-group circular"></i>
		                                    <input type="text" class="form-control" id="roleName" name="roleName" placeholder="角色名称" value="${role.roleName }" >
	                                   	</span>
	                            	</div>
	                            </div>
	                            
	                       		<div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">说明</label>
	                            	<div class="col-xs-10">
	                                       <textarea class="form-control" id="remarks" name="remarks">${role.remarks }</textarea>
	                            	</div>
	                            </div>
	                            
	                            <input type="hidden" id="roleId" name="roleId" value="${role.roleId }">
	                            <input type="hidden" name="parentId" value="${role.parentId }">
	                        </form>
	                    </div>
	                </div>
	   	</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>