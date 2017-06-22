<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
</script>
<div class="container-content" id="attendance-leaveBill">
	<div class="container-body">
		<div class="row">
	        <div class="col-lg-12 col-sm-12 col-xs-12">
	       		<table class="table table-detail">
					 <tbody>
	                     <tr>
	                         <th style="width: 100px;">
	                                                 请假类型
	                         </th>
	                         <td>
	                         	${leaveBill.dictionary.dictionaryName }
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                                                 开始时间
	                         </th>
	                         <td>
	                         	<fmt:formatDate value="${leaveBill.startTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                                                 结束时间
	                         </th>
	                         <td>
	                         	<fmt:formatDate value="${leaveBill.endTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                                                 合计
	                         </th>
	                         <td>
	                         	${leaveBill.leaveDays }
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                                                  请假原因
	                         </th>
	                         <td>
	                         	${leaveBill.leaveCause }
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                         	审批历史
	                         </th>
	                         <td>
                         		<table class="table">
                         			<tbody>
                         			<c:forEach items="${historyTaskList }" var="ht">
                         			<tr>
                         				<td><fmt:formatDate value="${ht.endTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                         				<td>${ht.taskUser }</td>
                         				<td>${ht.taskName }${ht.status }</td>
                         				<td>${ht.comment }</td>
                         			</tr>
                         			</c:forEach>
                         			</tbody>
                         		</table>
	                         	
	                         </td>
	                     </tr>
	                     <tr>
	                         <th>
	                                                  流程图
	                         </th>
	                         <td>
	                         	<img alt="" src="${contextPath }/attendance/leaveBill/downloadImage?processId=${leaveBill.processId }" style="max-width: 500px;">
	                         </td>
	                     </tr>
	                 </tbody>
	       		</table>
			</div>
		</div>
	
	</div>
</div>
