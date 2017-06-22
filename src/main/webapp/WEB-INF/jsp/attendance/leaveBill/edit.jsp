<%@page import="com.ifreework.common.manager.UserManager"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/dropzone/dropzone.css"></link>
<script src="${ jsPath }/bootstrap/dropzone/dropzone.js"></script>
<script type="text/javascript">
$.namespace("attendance.leaveBill.add");
attendance.leaveBill.edit = function(){
	var attendanceLeaveBillEdit ,
		bootstrapValidator,
		dropzone,
		attachments = "${leaveBill.attachmentId}".split(","),
		DEFAULT_START_CUMPUTE = 8.5, //默认早上计算考勤时间
		DEFAULT_END_CUMPUTE = 17.5; //默认下午计算考勤时间
	function initBootstrapValidator(){
		bootstrapValidator = attendanceLeaveBillEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	            leaveBillname: {
	                validators: {
	                	leaveType: {
	                        message: '请选择你的请假原因'
	                    }
	                }
	            },
	            sDate: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假开始日期'
	                    }
	                }
	            },
	            sTime: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假开始时间'
	                    }
	                }
	            },
	            eDate: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入结束日期'
	                    },
	                    callback : {
	                    	message:"请假结束时间必须大于开始时间，且必须大于1个小时，请重新输入"
	                    }
	                }
	            },
	            eTime: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假结束时间'
	                    }
	                }
	            },
	            leaveCause: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假结束时间'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	}
	
	function initSelect(){
		attendanceLeaveBillEdit.find("#leaveType").select2({
			allowClear: true,
			ajax: {
			    url: "${ contextPath }/system/dictionary/queryDictionaryByCode",
			    delay: 250,
			    dataType: 'json',
				data: {dictionaryTypeId:"attendance"},
			    processResults: function (data, params) {
		               params.page = params.page || 1;
		               for(var i = 0; i < data.length;i++){
		            	   data[i].id =  data[i].dictionaryCode;
		            	   data[i].text =  data[i].dictionaryName;
		               }
		               return {
		                   results: data
		               };
		        },
			    cache: true
			}
		});
	}
	
	function initDate(){
		attendanceLeaveBillEdit.find('#sDate').datepicker({
			autoclose:true
		}).on("changeDate",function(e){
			validateTime();
		});
		
		attendanceLeaveBillEdit.find('#sTime').timepicker({
			maxHours:18,
			minHours:8,
			showMeridian:false,
			showSeconds:true
		}).on("changeTime.timepicker",function(e){
			
			validateTime();
		});
		attendanceLeaveBillEdit.find('#eDate').datepicker({
			autoclose:true
		}).on("changeDate",function(e){
			validateTime();
		});
		
		attendanceLeaveBillEdit.find('#eTime').timepicker({
			maxHours:18,
			minHours:8,
			showMeridian:false,
			showSeconds:true
		}).on("changeTime.timepicker",function(e){
			validateTime();
		});
	}
	
	function initAutosize(){
		attendanceLeaveBillEdit.find('#leaveCause').autosize({ append: "\n" });
	}
	
	//上传附件
	function initDropzone(){
		dropzone = new Dropzone("#attendance-leaveBill-edit #dropzone", {
			url : "${ contextPath }/system/attachment/fileUpload",
			autoProcessQueue : true,
			addRemoveLinks:true,
			maxFilesize:100,
			acceptedFiles:'image/png,image/jpeg,image/jpg,image/gif',
			success:function(file,data){
				file.attachmentId = data.attachmentId;
				attachments.push(data.attachmentId);
				$(file.previewElement).addClass("dz-success");
			},
			removedfile:function(file){
				console.log(file);
				removeFile(file.attachmentId);
				$(file.previewElement).remove();
			}
		});
		attendanceLeaveBillEdit.find(".attachment-delete").on("click",function(e){
			var _li = $(e.target).closest("li"); // 获取点击的a标签
			var attachmentId = $(this).data("attachmentid");
			W.arrayRemove(attachments,attachmentId);
			_li.remove();
		});
	}
	
	function removeFile(attachmentId){
		var opt = {
			url:"${contextPath}/system/attachment/delete",
			data:{
				attachmentId:attachmentId
			},
			success:function(){
				W.arrayRemove(attachments,attachmentId);
			}
		}
		W.ajax(opt);
	}
	
	function initSave(){
	    attendanceLeaveBillEdit.find("#btn-save").click(function(){
	    	bootstrapValidator.validate();
	    	if(bootstrapValidator.isValid()){
	    		var data = attendanceLeaveBillEdit.find("#saveForm").serializeJson();
	    		data.startTime = new Date($("#sDate").val() + " " + $("#sTime").val());
	    		data.endTime = new Date($("#eDate").val() + " " + $("#eTime").val());
	    		data.attachmentId = attachments.toString();
	    		var opt = {
	    				url : "${ contextPath }/attendance/leaveBill/save",
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
	
	//验证结束时间是否大于开始时间
	function validateTime(){
		var sDate = $("#sDate").val(),
			sTime =  $("#sTime").val(),
			eDate = $("#eDate").val(),
			eTime =  $("#eTime").val(),
			startTime, 
			endTime,
			minuteTime; 
		
		if(!W.isNull(sDate) && !W.isNull(sTime) && !W.isNull(eDate) && !W.isNull(eTime)){
			startTime = new Date(sDate + " " + sTime);
			endTime = new Date(eDate + " " + eTime);
			
			minuteTime = endTime.getTime() - startTime.getTime();
			
			if(minuteTime <= 1000*60*60){  //验证结束时间是否大于开始时间一个小时
				bootstrapValidator.updateStatus("eTime", "INVALID", "callback");
				return;
			}
			bootstrapValidator.updateStatus("eTime", "VALID");
			computeLeaveDay(sDate,sTime,eDate,eTime,startTime,endTime);
		}
	}
	
	//计算请假时间
	function computeLeaveDay(sDate,sTime,eDate,eTime,startTime,endTime){
		var minuteTime,  
		tempTime,
		nextStartDay, //开始时间的下一天
		leaveDays,
		nextStartDay = new Date(sDate + " " + sTime);
		
		nextStartDay.setDate(nextStartDay.getDate() + 1);
		nextStartDay.setHours(0,0,0,0);
		
		minuteTime = (endTime.getTime() - nextStartDay.getTime()) / (1000 * 60 * 60 * 24); 
		console.log(minuteTime);
		
		if( minuteTime < 0 ){  //如果小于0，说明请假是在当天
			tempTime = (endTime.getHours() + endTime.getMinutes() / 60 ) - ( startTime.getHours() + startTime.getMinutes() / 60) ;
			leaveDays = tempTime > 4 ? 1 : 0.5 ;
		}else if( minuteTime > 0 ){  //如果是隔天
			tempTime = DEFAULT_END_CUMPUTE - (startTime.getHours() + startTime.getMinutes() / 60); //获取开始日期当天的请假时间
			leaveDays = tempTime > 4 ? 1 : 0.5 ;   
			
			tempTime = (endTime.getHours() + endTime.getMinutes() / 60) - DEFAULT_START_CUMPUTE; //获取结束当天的请假时间
			tempTime = tempTime > 4 ? 1 : 0.5 ;   
			leaveDays += tempTime;
			if(minuteTime >= 1){
				tempTime = parseInt(minuteTime);
				leaveDays += tempTime;
			}
		}
		
		$("#leaveDays").val(leaveDays);
	}
	
	return {
		init:function(){
			attendanceLeaveBillEdit = $("#attendance-leaveBill-edit");
			initBootstrapValidator();
			initSelect();
			initDate();
			initAutosize();
			initDropzone();
			initSave();
			
		}
	}
	
}();

$().ready(function(){
	attendance.leaveBill.edit.init();
});
</script>
<div class="container-content" id="attendance-leaveBill-edit">
	<div class="container-body">
		<div id="registration-form">
			<form id="saveForm" method="post" class="form-horizontal">
		
				<div class="form-title">
						请假人员：<%=UserManager.getUser().getPersonName() %>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假类型</label>
					<div class="col-sm-9">
						<select  id="leaveType" name="leaveType" style="width: 100%;" >
							<c:if test="${ leaveBill.leaveType != null && leaveBill.leaveType != '' }">
							<option value="${ leaveBill.leaveType}" selected="selected">${ leaveBill.dictionary.dictionaryName}</option>
							</c:if>
						</select>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">开始时间</label>
					<div class="col-sm-5">
						<span class="input-icon icon-left">
							<i class="fa fa-calendar"></i>
							<input type="text" class="form-control" name="sDate" id="sDate" readonly="readonly" value='<fmt:formatDate value="${leaveBill.startTime}" pattern="yyyy/MM/dd"/>'>
						</span>
					</div>
					<div class="col-sm-4">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="sTime" id="sTime" readonly="readonly" value='<fmt:formatDate value="${leaveBill.startTime}" pattern="HH:mm:ss"/>'>
						</span>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">结束时间</label>
					<div class="col-sm-5">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="eDate" id="eDate" readonly="readonly" value='<fmt:formatDate value="${leaveBill.endTime}" pattern="yyyy/MM/dd"/>'>
						</span>
					</div>
					<div class="col-sm-4">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="eTime" id="eTime" readonly="readonly" value='<fmt:formatDate value="${leaveBill.endTime}" pattern="HH:mm:ss"/>'>
						</span>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假天数</label>
					<div class="col-sm-9">
						<input type="text" id="leaveDays" name="leaveDays" class="form-control" value="${leaveBill.leaveDays == null ? 1 : leaveBill.leaveDays}" readonly="readonly" >
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假原因</label>
					<div class="col-sm-9">
						   <textarea class="form-control" id="leaveCause" name="leaveCause">${leaveBill.leaveCause }</textarea>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">附件</label>
					<div class="col-sm-9">
						<div id="dropzone" class="dropzone ">
						</div>
					</div>
				</div>
				<div class="form-group has-feedback row">
					<div class="col-sm-2"></div>
					<div class="col-sm-9">
						<div class="attachments-body ">
							<ul>
							<c:forEach items="${leaveBill.attachments }" var="atta">
								<li>
				                    <div class="thumb">
				                        <img src="${contextPath}/system/attachment/download?attachmentId=${atta.attachmentId}" onclick="bootbox.image('${contextPath}/system/attachment/download?attachmentId=${atta.attachmentId}')" />
				                    </div>
				
				                    <div class="detail">
				                    	<span class="name" title="${atta.attachmentName }">${atta.attachmentName }</span>
				                    	<span class="size">(16KB)</span>
				                    	<div class="links">
				                    		<a href="${contextPath }/system/attachment/download?attachmentId=${atta.attachmentId}" target="_blank">下载</a> - 
				                    		<a href="javascript:void(0)" class="attachment-delete" data-attachmentid="${atta.attachmentId}">删除</a>
				                    	</div>
				                    </div>
				                </li>
				           </c:forEach>
							</ul>
						</div>
					</div>
				</div>
				
				<input type="hidden" name="leaveBillId" value="${leaveBill.leaveBillId }">
			</form>
		</div>
		<div class="text-center container-footer">
			<a class="btn btn-sky" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
		</div>
	</div>
</div>