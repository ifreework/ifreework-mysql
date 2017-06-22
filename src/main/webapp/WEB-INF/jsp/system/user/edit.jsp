<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.user.edit");
system.user.edit = function(){
	var systemUserEdit ,
		bootstrapValidator;
	function initBootstrapValidator(){
		bootstrapValidator = systemUserEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	            username: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写您的用户名'
	                    },
						remote : {
							message: '该用户名已经存在！',
							url: '${ contextPath }/system/user/validate'
						}
	                }
	            },
	            personName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写您的姓名'
	                    }
	                }
	            },
	            email: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写您的电子邮箱地址'
	                    },
	                    emailAddress:{
	                    }
	                }
	            },
	            phone: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写您的电话号码'
	                    },
	                    phone:{
	                    	country: 'CN'
	                    }
	                }
	            },
	            provinceId: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择您的省份'
	                    }
	                }
	            },
	            municipalityId: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择您的市区'
	                    }
	                }
	            },
	            countyId: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择您的区县'
	                    }
	                }
	            },
	            birthday: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择您的出生日期'
	                    },
	                    date: {
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	}
	
	function initSelect(){
		systemUserEdit.find("#provinceId").select2({
			placeholder: "省",
	        allowClear: true,
			ajax: {
			    url: "${ contextPath }/db/province",
			    delay: 250,
			    dataType: 'json',
				data: function (params) {
				    return {
				    	provinceName: params.term, 
				        page: params.page
				    };
				},
			    processResults: function (data, params) {
		               params.page = params.page || 1;
		               return {
		                   results: data
		               };
		        },
			    cache: true
			}
		}).on("change",function(e){
			systemUserEdit.find("#municipalityId").val(null).trigger("change");
			systemUserEdit.find("#municipalityId").prop("disabled", systemUserEdit.find("#provinceId").val() == null || systemUserEdit.find("#provinceId").val() =="" );//启动市区选择
		});
		
		systemUserEdit.find("#municipalityId").select2({
			placeholder: "市",
	        allowClear: true,
	        disabled : ${user.userId == null || user.userId == ""},
			ajax: {
			    url: "${ contextPath }/db/municipality",
			    delay: 250,
			    dataType: 'json',
				data: function (params) {
					var provinceId = systemUserEdit.find("#provinceId").val();
				    return {
				    	provinceId: provinceId,
				    	municipalityName: params.term, // search term
				        page: params.page
				    };
				},
			    processResults: function (data, params) {
		               params.page = params.page || 1;
		               return {
		                   results: data
		               };
		        },
			    cache: true
			}
		}).on("change",function(e){
			systemUserEdit.find("#countyId").val(null).trigger("change");
			systemUserEdit.find("#countyId").prop("disabled", systemUserEdit.find("#municipalityId").val() == null || systemUserEdit.find("#municipalityId").val() =="" );//启动市区选择
		});
		
		systemUserEdit.find("#countyId").select2({
			placeholder: "区县",
	        allowClear: true,
	        disabled : ${user.userId == null || user.userId == ""},
			ajax: {
			    url: "${ contextPath }/db/county",
			    delay: 250,
			    dataType: 'json',
				data: function (params) {
					var municipalityId = systemUserEdit.find("#municipalityId").val();
				    return {
				    	municipalityId: municipalityId,
				    	countyName: params.term, // search term
				        page: params.page
				    };
				},
			    processResults: function (data, params) {
		               params.page = params.page || 1;
		               return {
		                   results: data
		               };
		        },
			    cache: true
			}
		});
	}
	
	function initDate(){
		systemUserEdit.find('#birthday').datepicker({
			autoclose:true
		}).on("changeDate",function(){
			bootstrapValidator.updateStatus("birthday", 'NOT_VALIDATED').validateField("birthday");
		});
	}
	
	function initAutosize(){
		systemUserEdit.find('#remarks').autosize({ append: "\n" });
	}
	
	function initSave(){
	    systemUserEdit.find("#btn-save").click(function(){
	    	bootstrapValidator.validate();
	    	if(bootstrapValidator.isValid()){
	    		var data = systemUserEdit.find("#saveForm").serializeJson();
	    		var opt = {
	    				url : "${ contextPath }/system/user/save",
	    				data:data,
	    				success:function(param){
	    					if(param.result === SUCCESS){
	    						bootbox.alert("数据保存成功","",function(){
	    							system.main.history();
	    						});
	    					}else{
	    						bootbox.alert("数据异常，保存失败");
	    					}
	    				}
	    		};
	    		W.ajax(opt);
	    	}
	    });
	}
	return {
		init:function(){
			systemUserEdit = $("#system-user-edit");
			initBootstrapValidator();
			initSelect();
			initDate();
			initAutosize();
			initSave();
		}
	}
	
}();

$().ready(function(){
	system.user.edit.init();
});
</script>
<div class="container-content" id="system-user-edit">
	<div class="container-body">
		<div id="registration-form">
			<form id="saveForm" method="post" class="form-horizontal">
		
				<div class="form-title">
						帐号：${user.username}
				</div>
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-9">
						<span class="input-icon icon-left">
						<i class="fa fa-user circular"></i>
						 <input type="text" class="form-control" name="personName" placeholder="姓名" value="${user.personName }">
						</span>
					</div>
				   
				</div>
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-9">
						<span class="input-icon icon-left">
						<i class="fa fa-envelope circular"></i>
						 <input type="text" class="form-control" name="email" placeholder="邮箱" value="${user.email }" >
						</span>
					</div>
				</div>
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-9">
						<span class="input-icon icon-left">
						<i class="fa fa-phone circular"></i>
						 <input type="text" class="form-control" name="phone" placeholder="联系电话" value="${user.phone }" >
						</span>
					</div>
				</div>
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">家庭住址</label>
					<div class="col-sm-3">
					<select  id="provinceId" name="provinceId" style="width: 100%;" >
						<c:if test="${ user.provinceId != null && user.provinceId != '' }">
						<option value="${ user.provinceId}" selected="selected">${ user.province.provinceName}</option>
						</c:if>
				</select>
					</div>
					<div class="col-sm-3">
					<select  id="municipalityId" name="municipalityId" style="width: 100%;" >
					<c:if test="${ user.municipalityId != null && user.municipalityId != '' }">
					<option value="${ user.municipalityId}" selected="selected">${ user.municipality.municipalityName}</option>
					</c:if>
				</select>
					</div>
					<div class="col-sm-3">
					<select  id="countyId" name="countyId" style="width: 100%;" >
					<c:if test="${ user.countyId != null && user.countyId != '' }">
					<option value="${ user.countyId}" selected="selected">${ user.county.countyName}</option>
					</c:if>
				</select>
					</div>
				</div>
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-9">
						<span class="input-icon icon-left">
						<i class="fa fa-building-o circular"></i>
						<input type="text" class="form-control" name="deailAddress" placeholder="详细地址" value="${user.deailAddress }" >
						</span>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group has-feedback row">
							<label class="col-sm-4 control-label">出生日期</label>
							<div class="col-sm-8">
							<span class="input-icon icon-left">
								 <input type="text" class="form-control date-picker" name="birthday" id="birthday" placeholder="出生日期" value='<fmt:formatDate value="${user.birthday}" pattern="yyyy/MM/dd"/>'>
								 <i class="fa fa-calendar"></i>
							 </span>
							</div>
						</div>
						
					</div>
					  
					<div class="col-sm-5">
						<div class="form-group has-feedback row">
							<label class="col-sm-4 control-label">毕业学校</label>
							<div class="col-sm-8">
							<span class="input-icon icon-left">
								 <input type="text" class="form-control" name="school" placeholder="请填写毕业院校" value="${user.school }">
								 <i class="fa fa-hospital-o circular"></i>
							 </span>
							</div>
						</div>
					</div>  	
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">个人说明</label>
					<div class="col-sm-9">
						   <textarea class="form-control" id="remarks" name="remarks">${user.remarks }</textarea>
					</div>
				</div>
				
		
				<input type="hidden" name="userId" value="${user.userId }">
			</form>
		</div>
		<div class="text-center container-footer">
			<a class="btn btn-sky" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
		</div>
	</div>
</div>