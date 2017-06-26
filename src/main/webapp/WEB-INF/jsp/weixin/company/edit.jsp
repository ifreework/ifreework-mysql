<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>


<script type="text/javascript">
$.namespace("weixin.company.edit");
weixin.company.edit = function(){
	var weixinCompanyEdit ,
		dropzone,
		bootstrapValidator ;
	
	function initValidator(){
		 bootstrapValidator = weixinCompanyEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	     		companyName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写公司名称'
	                    }
	                }
	            },
	            phone: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写联系电话'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	}
	
	function save(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = weixinCompanyEdit.find("#saveForm").serializeJson();
    		var opt = {
    				url : "${ contextPath }/weixin/company/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
    						bootbox.alert("公司创建成功。","",function(){
    							system.main.refresh();
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
			id:"weixinCompanyEditDialog",
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
						weixinCompanyEdit.find("#img-upload").html('<img alt="图片加载失败" src="' + file.data + '">');
						weixinCompanyEdit.find("#logo").val(file.data);
					}
				}
			}
		});
	}
	
	function showBody(){
		weixinCompanyEdit.find("#edit-title").hide();
		weixinCompanyEdit.find("#edit-body").show();
		weixinCompanyEdit.find("#edit-footer").show();
	}
	return {
		init: function(){
			weixinCompanyEdit = $("#weixin-company-edit");
			initValidator();
			weixinCompanyEdit.find("#edit-title").on("click",showBody);
			weixinCompanyEdit.find("#img-upload").on("click",openDialog);
			weixinCompanyEdit.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	weixin.company.edit.init();
});
</script>
<div class="container-content no-margin" id="weixin-company-edit">
	<div class="container-title">
		<span class="icon"><i class="fa fa-book"></i></span>
         <span class="text">工作室编辑</span>
    </div>
	<div class="container-body">
		<div id="edit-title" style="display: ${company.companyId == null ? 'block' :'none' }">
			<div class="text-center margin-20">您尚未创建自己的工作室，请点击下方按钮创建属于自己的工作室。</div>
			<div class="text-center">
				<a id="create-company" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i>创建工作室</a>
			</div>
		</div>
		
   		<div class="row" id="edit-body" style="display: ${company.companyId == null ? 'none' :'block' }">
           <div class="col-lg-12 col-sm-12 col-xs-12">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">工作室名称</label>
                            	<div class="col-xs-10">
	                                    <input type="text" class="form-control" name="companyName" placeholder="工作室名称" value="${company.companyName }">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">工作室地址</label>
                            	<div class="col-xs-10">
	                                    <input type="text" class="form-control" name="address" placeholder="工作室地址" value="${company.address }">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">联系人</label>
                            	<div class="col-xs-10">
	                                    <input type="text" class="form-control" name="contactUser" placeholder="联系人" value="${company.contactUser }">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">联系电话</label>
                            	<div class="col-xs-10">
	                                    <input type="text" class="form-control" name="phone" placeholder="联系电话" value="${company.phone }">
                            	</div>
                            </div>
                            
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label no-padding">工作室LOGO</label>
                            	<div class="col-xs-10">
                            		<span id="img-upload" style="cursor: pointer;">
                            		<c:if test="${company.logo == null || company.logo == '' }">
                            			图片上传
                            		</c:if>
                            		<c:if test="${company.logo != null && company.logo != '' }">
                            			<img alt="图片加载失败" src="${company.logo}">
                            		</c:if>
                            		</span>
									<input type="hidden" name="logo" id="logo">
                            	</div>
                            </div>
                            <input type="hidden" id="companyId" name="companyId" value="${company.companyId }">
                        </form>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer" id="edit-footer" style="display: ${company.companyId == null ? 'none' :'block' }">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>