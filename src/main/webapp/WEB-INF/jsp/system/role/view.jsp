<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
$.namespace("system.role");
system.role = function(){
	var sysRole,
		tree,
		treeNode,
		nodeId ,
		menu;
	
	function initMenu(){
		menu = new W.menu({
			id : "sysRoleMenu", //menu id
			items :[{
				id:"authorization",
				icon:"fa fa-pagelines sky",
				text:"角色授权"
			},{
				id:"add",
				icon:"fa fa-plus green",
				text:"新增角色"
			},{
				id:"update",
				icon:"fa fa-edit blue",
				text:"修改角色"
			},{
				id:"delete",
				icon:"fa fa-trash-o red",
				text:"删除角色"
			}], 
			onclick:function(id){
				if(id == "authorization"){
					system.main.open("${ contextPath }/system/role/authorization?treeId=" + nodeId,"角色授权",{});
				}else if(id == "add"){
					openDialog("${ contextPath }/system/role/add?treeId=" + nodeId);
				}else if(id == "update"){
					if(nodeId == null || nodeId == "0"){
						bootbox.alert("请选择您要修改的角色");
						return;
					}
					openDialog("${ contextPath }/system/role/update?treeId=" + nodeId);
				}else if(id == "delete"){
					if(nodeId == null || nodeId == "0"){
						bootbox.alert("请选择您要删除的角色");
						return;
					}
					var message = treeNode.isParent ? "该角色删除后，其相关的子角色都会被删除，确定要删除该角色吗？" : "确定要删除该角色吗？"
					bootbox.confirm(message,"",function(e){
						if(e){
							deleteNode(nodeId);
						}
					});
				}
			}
		});
	}
	
	function initTree(){
		var setting = {
			async: {
				enable: true,
				url:"${ contextPath }/system/role/query",
				autoParam:["id"],
				dataFilter: filter
			},
			callback: {
				onRightClick: function(event, id, node) {
					treeNode = node;
					nodeId = node == null ? "0" : node.id
					menu.show(event.clientX,event.clientY);
				}
			},
			onDrop:function(event, treeId, treeNodes, targetNode, moveType){
					if(moveType == "inner"){//更换父节点
						updateNode(treeNodes[0].id,targetNode.id)
					}
				}
			};
			
			tree = $.fn.zTree.init(sysRole.find("#roleTree"), setting);
	}
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].id = childNodes[i].roleId;
			childNodes[i].name = childNodes[i].roleName;
			childNodes[i].isParent = childNodes[i].isLeaf == 1 ? false : true;
		}
		return childNodes;
	}
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			id:"systemRoleDialog",
			title: "角色编辑",
			width:700,
			loadUrl: url
		});
	}
	
	//删除节点
	function deleteNode(roleId){
		var opt = {
			url : "${ contextPath }/system/role/delete",
			data:{roleId:roleId},
			success:function(param){
				if(param.result === SUCCESS){
					bootbox.alert("数据删除成功","",function(){
						tree.removeNode(treeNode);
					});
				}else{
					bootbox.alert("数据异常，删除失败");
				}
			}
		};
		W.ajax(opt);
	}
	
	//节点拖拽后，修改父节点
	function updateNode(departmentId,parentId){
		var opt = {
			url : "${ contextPath }/system/department/save",
			data:{departmentId:departmentId,parentId:parentId},
			success:function(param){
				if(param.result === SUCCESS){
					tree.removeNode(treeNode);
				}else{
					bootbox.alert("数据异常，删除失败");
				}
			}
		};
		W.ajax(opt);
	}
	
	return {
		init : function(){
			sysRole = $("#system-role");
			initMenu();
			initTree();
		},
		tree : function(){
			return tree;
		},
		treeNode : function(){
			return treeNode;
		}
	}
}();

$().ready(function(){
	system.role.init();
});
</script>
<div class="container-content" id="system-role">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-body">
						<ul id="roleTree" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
