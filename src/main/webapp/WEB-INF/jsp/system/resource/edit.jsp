<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script src="${jsPath}/bootstrap/fuelux/wizard/wizard-custom.js"></script>
<script type="text/javascript">
$.namespace("system.resource.edit");

system.resource.edit = function(){
	var systemResourceEdit,
		bootstrapValidator;
	
	function initValidator(){
		 bootstrapValidator = systemResourceEdit.find("#saveForm").bootstrapValidator({
			fields: {
				resourceName: {
					validators: {
						notEmpty: {
							message: '请填写资源名称'
						}
					}
				},
				pk: {
					validators: {
						notEmpty: {
							message: '请选择资源类型'
						},
						remote : {
							message: '该资源编码已存在！',
							async: false,
							url: '${ contextPath }/system/resource/validate?resourceId=${resource.resourceId}'
						}
					}
				},
				resourceType: {
					validators: {
						notEmpty: {
							message: '请选择资源类型'
						}
					}
				}
			}
		}).data('bootstrapValidator');
	}
	
	function initWizard(){
		systemResourceEdit.find('#resourceWizard').wizard().on('changed', function (e) {
			resizeDrag();
		}).on('change', function (e) {
			bootstrapValidator.validate();
			return bootstrapValidator.isValid();
		}).on('finished', function (e) {
			saveData();
		});
	}
	
	function initButton(){
		//点击选中或者取消事件
		systemResourceEdit.find(".nav a").on("click",function(e){
			var _li = $(this).closest("li");
			console.log(_li);
			if(_li.hasClass("selected")){
				_li.removeClass("selected");
			}else{
				_li.addClass("selected");
			}
		});
		
		systemResourceEdit.find("#fromButton").on("click",function(){
			var lis = systemResourceEdit.find("#dragFrom").find("li.selected");
			changeLi(lis);
			systemResourceEdit.find("#dragTo").append(lis);
			resizeDrag();
		});
		
		systemResourceEdit.find("#toButton").on("click",function(){
			var lis = systemResourceEdit.find("#dragTo").find("li.selected");
			changeLi(lis);
			systemResourceEdit.find("#dragFrom").append(lis);
			resizeDrag();
		});
	}
	
	//在拖拽的时候，对选中的li标签进行处理
	function changeLi(lis){
		lis.removeClass("selected");
		lis.each(function(index,e){
			var li = $(e);
			if(li.attr("changed")){
				li.removeAttr("changed");
			}else{
				li.attr("changed","changed");
			}
		});
	}
	
	
	//重置左右div的高度,并使按钮剧中
	function resizeDrag(){
		var fromHeight = systemResourceEdit.find(".drag-from .widget-body").css("height","").height();
		var toHeight = systemResourceEdit.find(".drag-to .widget-body").css("height","").height();
		
		console.log("fromHeight:" + fromHeight );
		console.log("toHeight:" + toHeight );
		if(fromHeight >= toHeight ){
			systemResourceEdit.find(".drag-to .widget-body").height(fromHeight);
		}else{
			systemResourceEdit.find(".drag-from .widget-body").height(toHeight);
		}
		
		systemResourceEdit.find("#drag-button").css({
			marginTop:((fromHeight >= toHeight ? fromHeight : toHeight) - 35)/2
		});
	}
	
	
	//获取有过拖拽的li
	function getAddArray(){
		var changeArray = [];
		var addLis = systemResourceEdit.find("#dragTo li[changed='changed']");
		addLis.each(function(index,e){
			var li = $(e);
			var addObj = {
				operationId:li.data("operationid")
			};
			changeArray.push(JSON.stringify(addObj));
		});
		console.log(changeArray);
		return "[" +  changeArray.toString() + "]";
	}
	
	
	//获取有过拖拽的li
	function getDeleteArray(){
		var changeArray = [];
		var deleteLis = systemResourceEdit.find("#dragFrom li[changed='changed']");
		deleteLis.each(function(index,e){
			var li = $(e);
			var deleteObj = {
				operationId:li.data("operationid")
			};
			changeArray.push(JSON.stringify(deleteObj));
		});
		console.log(changeArray);
		return "[" +  changeArray.toString() + "]";
	}
	
	//保存页面中的数据
	function saveData(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = systemResourceEdit.find("#saveForm").serializeJson();
    		data.addArray = getAddArray();
    		data.deleteArray = getDeleteArray();
    		var opt = {
    				url : "${ contextPath }/system/resource/save",
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
	}
	
	return {
		init : function(){
			systemResourceEdit = $("#system-resource-edit");
			initValidator();
			initWizard();
			initButton();
		}
	}
}();

$().ready(function(){
	system.resource.edit.init();
});
</script>

<div class="container-content" id="system-resource-edit">
	<div class="container-body">
		<div class="row">
			<div class="col-lg-12 col-sm-12 col-xs-12">
				<div id="resourceWizard" class="wizard" data-target="#resourceWizard-steps">
					<ul class="steps">
						<li data-target="#resourceWizardstep1" class="active"><span class="step">1</span>编辑资源信息<span class="chevron"></span></li>
						<li data-target="#resourceWizardstep2"><span class="step">2</span>设置相关操作<span class="chevron"></span></li>
					</ul>
				</div>
				<div class="step-content" id="resourceWizard-steps">
					<div class="step-pane active" id="resourceWizardstep1">
						<div id="registration-form">
							<form id="saveForm" method="post" class="form-horizontal">
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">资源名称</label>
									<div class="col-sm-9">
										<span class="input-icon icon-left">
											<i class="fa fa-user circular"></i>
											<input type="text" class="form-control" name="resourceName" placeholder="资源名称" value="${resource.resourceName }">
										</span>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">PK</label>
									<div class="col-sm-9">
										<span class="input-icon icon-left">
											<i class="fa fa-anchor circular"></i>
											<input type="text" class="form-control" name="pk" placeholder="PK" value="${resource.pk }" >
										</span>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">资源类型</label>
									<div class="col-sm-9">
										<select  id="resourceType" name="resourceType" style="width: 100%;">
											<option></option>
											<option value="M" ${ resource.resourceType == "M" ? "selected='selected'" : "" }>M</option>
											<option value="A" ${ resource.resourceType == "A" ? "selected='selected'" : "" }>A</option>
											<option value="B" ${ resource.resourceType == "B" ? "selected='selected'" : "" }>B</option>
										</select>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">资源路径</label>
									<div class="col-sm-9">
										<span class="input-icon icon-left">
											<i class="fa fa-link circular"></i>
											<input type="text" class="form-control" name="resourceUrl" placeholder="资源路径" value="${resource.resourceUrl }" >
										</span>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">资源标签</label>
									<div class="col-sm-9">
										<span class="input-icon icon-left">
											<i class="fa fa-bookmark-o circular"></i>
											<input type="text" class="form-control" name="resourceFlag" placeholder="资源标签" value="${resource.resourceFlag }" >
										</span>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">图标</label>
									<div class="col-sm-9">
										<span class="input-icon icon-left">
											<i class="fa fa-pagelines circular"></i>
											<input type="text" class="form-control" name="iconCls" placeholder="图标" value="${resource.iconCls }" >
										</span>
									</div>
								</div>
								
								<div class="form-group has-feedback row">
									<label class="col-sm-2 control-label">说明</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="remarks" name="remarks">${resource.remarks }</textarea>
									</div>
								</div>
								<input type="hidden" name="resourceId" value="${resource.resourceId }">
								<input type="hidden" name="parentId" value="${resource.parentId }">
							</form>
						</div>
					</div>
					<div class="step-pane" id="resourceWizardstep2">
						<div class="row">
							<div class="col-xs-2">
							</div>
							<div class="col-xs-3">
								<div class="widget dragbox drag-from">
									<div class="widget-header"></div>
									<div class="widget-body">
										<ul class="nav" id="dragFrom">
											<c:forEach items="${notHasOpts }" var="opt">
											<li data-operationid="${opt.operationId }">
												<a href="javascript:void(0)">
													<span> ${opt.pk } </span><span>(${opt.operationType })</span>
												</a>
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-xs-1" class="drag-center">
								<div id="drag-button">
									<div class="text-center">
										<a href="javascript:void(0);" class="btn btn-default  btn-circle btn-xs" id="fromButton"><i class="fa fa-angle-double-right"></i></a>
									</div>
									<div class="text-center margin-top-10">
										<a href="javascript:void(0);" class="btn btn-default  btn-circle btn-xs" id="toButton"><i class="fa fa-angle-double-left"></i></a>
									</div>
								</div>
							</div>
							<div class="col-xs-3">
								
								<div class="widget dragbox drag-to">
									<div class="widget-header"></div>
									<div class="widget-body">
										<ul class="nav" id="dragTo">
											<c:forEach items="${hasOpts }" var="opt">
											<li data-operationid="${opt.operationId }">
												<a href="javascript:void(0)">
													<span> ${opt.pk } </span><span>(${opt.operationType })</span>
												</a>
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="actions actions-footer text-center" id="resourceWizard-actions">
					<div class="btn-group">
						<span>
						<button type="button" class="btn btn-info btn-prev no-border"> <i class="fa fa-angle-left"></i>上一步</button>
						</span>
						<span>
						<button type="button" class="btn btn-info btn-next no-border" data-last="保存">下一步<i class="fa fa-angle-right"></i></button>
						</span>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>
