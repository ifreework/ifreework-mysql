<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
$.namespace("system.dictionary");

system.dictionary = function(){
	var systemDictionary,
		tree,
		treeNode,
		nodeId ,
		menu;
	
	//初始化menu菜单
	function initMenu(){
		menu = new W.menu({
			id : "dictionaryMenu", //menu id
			items :[{
				id:"add",
				icon:"fa fa-plus green",
				text:"新增字典"
			},{
				id:"update",
				icon:"fa fa-edit blue",
				text:"修改字典"
			}], 
			onclick:function(id){
				if(id == "add"){
					var url = "";
					if(treeNode == null){
						url = "${ contextPath }/system/dictionaryType/add";
					}else{
						url = "${ contextPath }/system/dictionary/add?parentId=" + treeNode.id + "&dictionaryTypeId=" + treeNode.dictionaryTypeId;
					}
					
					openDialog(url);
				}else if(id == "update"){
					if(nodeId == null ){
						bootbox.alert("请选择您要修改的数据字典");
						return;
					}else if(nodeId == "0" ){
						openDialog("${ contextPath }/system/dictionaryType/update?treeId=" + treeNode.dictionaryTypeId);
					}else{
						openDialog("${ contextPath }/system/dictionary/update?treeId=" + nodeId);
					}
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
				url:"${ contextPath }/system/dictionary/query",
				autoParam:["id","dictionaryTypeId"],
				dataFilter: filter
			},
			edit:{
				enable: true,
				showRemoveBtn:false,
				showRenameBtn:false
			},
			callback: {
				onRightClick: function(event, id, node) {
					console.log(node);
					treeNode = node;
					nodeId = node == null ? "0" : node.id;
					menu.show(event.clientX,event.clientY);
				}
			}
		};
		tree = $.fn.zTree.init(systemDictionary.find("#dictionaryTree"), setting);
	}
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			if(childNodes[i].dictionaryId == null){ //说明字典类型节点
				childNodes[i].id = "0";
				childNodes[i].name = childNodes[i].dictionaryTypeName;
				childNodes[i].isParent = true;
			}else{
				childNodes[i].id = childNodes[i].dictionaryId;
				childNodes[i].name = childNodes[i].dictionaryName;
				childNodes[i].isParent = childNodes[i].isLeaf == 1 ? false : true;
			}
		}
		return childNodes;
	}
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			id:"dictionaryDialog",
			title: "添加数据字典",
			width:700,
	        loadUrl: url
	    });
	}
	
	
	function deleteNode(){
		var opt = {
				url : "${ contextPath }/system/dictionary/delete",
				data:{dictionaryId:nodeId},
				success:function(param){
					if(param.result === SUCCESS){
						bootbox.alert("数据删除成功","",function(){
							tree.removeNode(dictionaryTreeNode);
						});
					}else{
						bootbox.alert("数据异常，删除失败");
					}
				}
		};
		W.ajax(opt);
	}
	
	
	function updateNode(dictionaryId,parentId){
		var opt = {
				url : "${ contextPath }/system/dictionary/save",
				data:{dictionaryId:dictionaryId,parentId:parentId},
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
			systemDictionary = $("#system-dictionary");
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
	system.dictionary.init();
});
</script>
<div class="container-content" id="system-dictionary">
	<div class="container-body">
		<div class="row">
		    <div class="col-xs-12 col-md-12">
		        <div class="widget">
		            <div class="widget-body">
	                   	<ul id="dictionaryTree" class="ztree"></ul>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>
