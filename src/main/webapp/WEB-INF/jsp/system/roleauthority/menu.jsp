<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
$.namespace("system.roleauthority");
system.roleauthority = function(){
	var systemRoleauthority ,
		tree ;
	
	function initTree(){
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "p", "N": "p" },
				autoCheckTrigger: true
			},
			async: {
				enable: true,
				url:"${ contextPath }/system/roleauthority/queryMenuList",
				autoParam:["id"],
				otherParam:{roleId:"${roleId}"},
				dataFilter: filter
			}
		};
		tree = $.fn.zTree.init(systemRoleauthority.find("#roleAuthorityMenuTree"), setting);
		
	}
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].id = childNodes[i].resource.resourceId;
			childNodes[i].name = childNodes[i].resource.resourceName;
			childNodes[i].isParent = childNodes[i].resource.isLeaf == 1 ? false : true;
			childNodes[i].checked = childNodes[i].authorityPk == null || childNodes[i].authorityPk == "" ? false : true;
		}
		return childNodes;
	}
	
	
	function save(){
		var nodes = tree.getChangeCheckedNodes(),
		
			addNodes = [],
			deleteNodes = [],
			addStr,
			deleteStr;
		for(var i = 0 ; i < nodes.length;i++){
			var obj = {
				roleId : "${roleId}",
				authorityPk: nodes[i].authority.pk
			};
			if(nodes[i].checked){
				addNodes.push(obj);
			}else{
				deleteNodes.push(obj);
			}
		}
		addStr = W.jsonArrayToString(addNodes);
		deleteStr =  W.jsonArrayToString(deleteNodes);
		var opt = {
			url : "${ contextPath }/system/roleauthority/save",
			data : {addStr:addStr,deleteStr:deleteStr},
			success:function(data){
				if(data.result == SUCCESS){
					bootbox.alert("数据保存成功","",function(){
						tree.reAsyncChildNodes(null, "refresh");
					});
				}else{
					bootbox.alert("系统异常，保存失败");
				}
			}
			
		}
		W.ajax(opt);
	}
	
	return {
		init:function(){
			systemRoleauthority = $("#system-roleauthority");
			initTree();
			systemRoleauthority.find("#btn-save").on("click",save);
		}
	}
}();

$().ready(function(){
	system.roleauthority.init();
});
</script>
<div class="container-content" id="system-roleauthority">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="databox databox-shadowed padding-10 no-margin-bottom">
					<ul id="roleAuthorityMenuTree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-info" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>
