<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
(function(){
	var bootstrapValidator,
		$resetPassword = $("#resetPassword");
	$().ready(function(){
		 bootstrapValidator = $resetPassword.find("#saveForm").bootstrapValidator({
		     	message: '数值未通过验证',
		     	fields: {
		     		oldPassWord: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入旧密码'
		                    }
		                }
		            },
		            password: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入新密码'
		                    }
		                }
		            },
		            confirmPassword: {
		                validators: {
		                    notEmpty: {
		                        message: '请重新输入新密码'
		                    },
		                    identical:{
		                    	field: 'password',
		                    	message: '两次密码输入不一致，请重新输入'
		                    }
		                }
		            }
		     	}
			}).data('bootstrapValidator');
		 
		    $("#btn-save").click(function(){
		    	bootstrapValidator.validate();
		    	if(bootstrapValidator.isValid()){
		    		var data = $("#saveForm").serializeJson();
		    		var opt = {
		    				url : "${ contextPath }/system/user/changePwdSave",
		    				data:data,
		    				success:function(param){
		    					if(param.result === SUCCESS){
		    						bootbox.alert("密码重置成功，请重新登录","",function(){
		    							bootbox.hideAll();
		    							location.href = "${contextPath}/logout";
		    						});
		    					}else if(param.result === FAILED){
		    						bootbox.alert(param.msg);
		    					}else {
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
<div class="container-content" id="resetPassword">
	<div class="container-body">
	   	<div class="row">
	           <div class="col-lg-12 col-sm-12 col-xs-12">
	                    <div id="registration-form">
	                         <form id="saveForm" method="post" class="form-horizontal">
	                            <div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">旧密码</label>
	                            	<div class="col-xs-10">
		                            	<input type="password" class="form-control" id="oldPassWord" name="oldPassWord" placeholder="请输入旧密码" >
	                            	</div>
	                            </div>
	                            <div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">新密码</label>
	                            	<div class="col-xs-10">
		                            	<input type="password" class="form-control" id="password" name="password" placeholder="请输入修改后的密码">
	                            	</div>
	                            </div>
	                            <div class="form-group has-feedback row">
	                            	<label class="col-xs-2 control-label">确认密码</label>
	                            	<div class="col-xs-10">
										<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="请重新输入修改后的密码" >
	                            	</div>
	                            </div>
	                            
	                            <input type="hidden" id="userId" name="userId" value="${user.userId }">
	                        </form>
	                    </div>
	                </div>
	   	</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>