<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("system.role.authorization");

system.role.authorization = function(){
	var systemRoleAuthorization ;
		
	return {
		init : function(){
			systemRoleAuthorization = $("#system-role-authorization");
			systemRoleAuthorization.find("#tabMenu").load("${contextPath}/system/roleauthority/menu",{roleId:"${roleId}"});
		}
	}
}();

$().ready(function(){
	system.role.authorization.init();
});
</script>
<div class="container-content" id="system-role-authorization">
	<div class="container-body">
		<div class="row">
			<div class="col-lg-12 col-sm-12 col-xs-12">
				<div class="tabbable">
					
					<ul class="nav nav-tabs" id="myTab">
						<li class="active col-sm-4 no-padding">
							<a data-toggle="tab" href="#tabMenu">
								访问授权
							</a>
						</li>

						<li class="col-sm-4 no-padding">
							<a data-toggle="tab" href="#data">
								数据授权
							</a>
						</li>

						<li class="col-sm-4 no-padding">
							<a data-toggle="tab" href="#button">
								按钮授权
							</a>
						</li>
					</ul>

					<div class="tab-content">
						<div id="tabMenu" class="tab-pane in active">
						</div>

						<div id="data" class="tab-pane">
						</div>
						
						<div id="button" class="tab-pane">
						</div>
					</div>
				</div>
				<div class="horizontal-space"></div>

				
			</div>
		</div>

	</div>
</div>