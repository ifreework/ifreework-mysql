<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.department.edit");
system.department.edit = function(){
	var systemDepartmentEdit ,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = systemDepartmentEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		departmentNo: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写部门编号'
	                    }
	                }
	            },
	            departmentName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写部门名称'
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
		 
		 systemDepartmentEdit.find('#remarks').autosize({ append: "\n" });
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = systemDepartmentEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/system/department/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
							bootbox.hideAll();
							
							console.log(system.department);
							var treeNode = system.department.treeNode();
							var treeObj = system.department.tree();
							
							if(systemDepartmentEdit.find("#departmentId").val()){
								treeNode.name = systemDepartmentEdit.find("#departmentName").val();
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
			systemDepartmentEdit = $("#system-department-edit");
			initValidator();
			systemDepartmentEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	system.department.edit.init();
});
</script>
<div class="container-content" id="system-department-edit">
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="widget flat radius-bordered">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">部门编号</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-user-md circular"></i>
	                                    <input type="text" class="form-control" name="departmentNo" placeholder="部门编号" value="${department.departmentNo }">
                                   	</span>
                            	</div>
                               
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">部门名称</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-group circular"></i>
	                                    <input type="text" class="form-control" id="departmentName" name="departmentName" placeholder="部门名称" value="${department.departmentName }" >
                                   	</span>
                            	</div>
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">联系电话</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-phone circular"></i>
	                                    <input type="text" class="form-control" name="tel" placeholder="联系电话" value="${department.tel }" >
                                   	</span>
                            	</div>
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">地址</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-building-o circular"></i>
	                                   <input type="text" class="form-control" name="address" placeholder="地址" value="${department.address }" >
                                   	</span>
                            	</div>
                            </div>
                            
                       		<div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">其他</label>
                            	<div class="col-xs-10">
                                       <textarea class="form-control" id="remarks" name="remarks">${department.remarks }</textarea>
                            	</div>
                            </div>
                            
                            <input type="hidden" id="departmentId" name="departmentId" value="${department.departmentId }">
                            <input type="hidden" name="parentId" value="${department.parentId }">
                        </form>
                    </div>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>