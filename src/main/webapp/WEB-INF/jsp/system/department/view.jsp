<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
$.namespace("system.department");

system.department = function(){
	var systemDepartment,
		tree,
		treeNode,
		nodeId ,
		menu;
	
	//初始化menu菜单
	function initMenu(){
		menu = new W.menu({
			id : "departmentMenu", //menu id
			items :[{
				id:"add",
				icon:"fa fa-plus green",
				text:"新增部门"
			},{
				id:"update",
				icon:"fa fa-edit blue",
				text:"修改部门"
			},{
				id:"delete",
				icon:"fa fa-trash-o red",
				text:"删除部门"
			}], 
			onclick:function(id){
				if(id == "add"){
					openDialog("${ contextPath }/system/department/add?treeId=" + nodeId);
				}else if(id == "update"){
					if(nodeId == null || nodeId == "0"){
						bootbox.alert("请选择您要修改的部门");
						return;
					}
					openDialog("${ contextPath }/system/department/update?treeId=" + nodeId);
				}else if(id == "delete"){
					if(nodeId == null || nodeId == "0"){
						bootbox.alert("请选择您要删除的部门");
						return;
					}
					var message = treeNode.isParent ? "该部门删除后，其相关的子部门都会被删除，确定要删除该部门吗？" : "确定要删除该部门吗？"
					bootbox.confirm(message,"",function(e){
						if(e){
							deleteNode();
						}
					});
				}
			}
		});
	}
	
	//初始化tree
	function initTree(){
		var setting = {
			view: {
				dblClickExpand: false
			},
			async: {
				enable: true,
				url:"${ contextPath }/system/department/query",
				autoParam:["id"],
				dataFilter: filter
			},
			edit:{
				enable: true,
				showRemoveBtn:false,
				showRenameBtn:false
			},
			callback: {
				onRightClick: function(event, id, node) {
					treeNode = node;
					nodeId = node == null ? "0" : node.id;
					menu.show(event.clientX,event.clientY);
				},
				onDrop:function(event, treeId, treeNodes, targetNode, moveType){
					if(moveType == "inner"){//更换父节点
						updateNode(treeNodes[0].id,targetNode.id)
					}
				}
			}
		};
		tree = $.fn.zTree.init(systemDepartment.find("#departmentTree"), setting);
	}
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].id = childNodes[i].departmentId;
			childNodes[i].name = childNodes[i].departmentName;
			childNodes[i].isParent = childNodes[i].isLeaf == 1 ? false : true;
		}
		return childNodes;
	}
	
	
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			id:"departmentDialog",
			title: "部门编辑",
			width:700,
	        loadUrl: url
	    });
	}
	
	function deleteNode(){
		var opt = {
				url : "${ contextPath }/system/department/delete",
				data:{departmentId:nodeId},
				success:function(param){
					if(param.result === SUCCESS){
						bootbox.alert("数据删除成功","",function(){
							tree.removeNode(departmentTreeNode);
						});
					}else{
						bootbox.alert("数据异常，删除失败");
					}
				}
		};
		W.ajax(opt);
	}
	
	
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
			systemDepartment = $("#system-department");
			initMenu();
			initTree();
		},
		tree : function(){
			return tree;
		} ,
		treeNode : function(){
			return treeNode;
		}
	};
}();

$().ready(function(){
	system.department.init();
});
</script>
<div class="container-content" id="system-department">
	<div class="container-body">
		<div class="row">
		    <div class="col-xs-12 col-md-12">
		        <div class="widget">
		            <div class="widget-body">
	                   	<ul id="departmentTree" class="ztree"></ul>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>
