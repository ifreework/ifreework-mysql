<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
$.namespace("weixin.productType");

weixin.productType = function(){
	var weixinProductType,
		dataTable;
	
	function initTable(){
		dataTable = weixinProductType.find('#productTypeTab').DataTable({
			searching : false,//
			lengthChange: false,
			info:false,
			ordering : false,
			serverSide:true, //是否启用服务器模式
			paging : false,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/weixin/productType/query",
				data: function ( d ) {
		      		return {parentId:'0'};
			    }
			},
			columns : [{  
		            data : "productTypeName",  
		            title : "产品类型名称",  
		            defaultContent : "" ,
		            render:function( data, type, row, meta ){
		        		return ( '<i class="fa fa-plus-square-o row-details" data-id="' + row.productTypeId  + '" data-depth="0" parent="' + row.parentId + '" style="' + (row.isLeaf == '0' ? '' : 'display:none' ) + '"></i>' ) + '<span class="margin-left-10">' + data +'</span>';
		        	}
		        },  {  
		        	data : "remark",  
		        	title : "说明",  
		        	defaultContent : ""
		        }, {  
		        	title : "",  
		        	defaultContent : "", 
		        	className : "text-center"  ,
		        	render:function( data, type, row, meta ){
		        		return '<a class="btn btn-add btn-sky btn-xs icon-only" title="新增" data-producttypeid="' + row.productTypeId + '" href="javascript:void(0);"><i class="fa fa-plus "></i></a>' +
		        			   '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-producttypeid="' + row.productTypeId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
		        			   '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-producttypeid="' + row.productTypeId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>';
		        	}
		        }
		    ]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	 weixinProductType.find("#productTypeTab .row-details").click(function(){ //显示隐藏切换按钮
	    		 toggleTr(this);
	    	 });
	    	 
	 		 weixinProductType.find(".btn-add").on("click",function(e){
	 			 addChild(this);
			 });
	 		 weixinProductType.find(".btn-edit").on("click",function(e){
	 			 editNode(this);
			 });
	 		 weixinProductType.find(".btn-delete").on("click",function(e){
	 			 deleteNode(this);
			 });
	    } );
	}
	
	//展开与隐藏子节点
	function toggleTr(e){
		var id = $(e).data("id"),//操作的ID
			depth = $(e).data("depth") + 1, //节点的深度
			tr = $(e).closest("tr"),
			children = weixinProductType.find("#productTypeTab .row-details[parent='" + id + "']").closest("tr");
		
		console.log(children);
		
		if($(e).hasClass("fa-plus-square-o")){  //如果节点为隐藏状态，则展开节点
			$(e).removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
			
			if(children.length == 0){//如果没有子节点，则加载子节点，否则直接显示子节点
				loadChildren(id,tr,depth);
			}else{
				children.show();
			}
			
		}else{
			$(e).removeClass("fa-minus-square-o").addClass("fa-plus-square-o");
			hideChildren(children);
		}
	}
	
	
	function loadChildren(id,tr,depth){
		var opt = {
			url : "${contextPath}/weixin/productType/query",
			data:{parentId:id},
			success:function(param){
				var html = "";
				for(var i = 0; i < param.data.length;i++){
					var row = param.data[i];
					html = html +  "<tr>" +
						"<td style='padding-left:" + ( depth * 20 + 10) + "px'>" + ( '<i class="fa fa-plus-square-o row-details" data-id="' + row.productTypeId  + '" data-depth="'+  depth +'" parent="' + row.parentId + '" style="' + (row.isLeaf == '0' ? '' : 'display:none' ) + '"></i>' ) + '<span class="margin-left-' + (row.isLeaf == '0' ? '10' : '20' ) + '">' + row.productTypeName + "</span></td>" + 
						"<td>" + row.remark + "</td>" + 
						"<td class='text-center'>" + 
	        			   		 '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-producttypeid="' + row.productTypeId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
	        			   		 '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-producttypeid="' + row.productTypeId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>' + 
	        			   		 "</td>" + 
						"</tr>";
				}
				
				tr.after(html);
				
				weixinProductType.find("#productTypeTab .row-details").unbind().on("click",function(){
		    		toggleTr(this);
		    	});
		 		 weixinProductType.find(".btn-add").unbind().on("click",function(e){
		 			 addChild(this);
				 });
		 		 weixinProductType.find(".btn-edit").unbind().on("click",function(e){
		 			 editNode(this);
				 });
		 		 weixinProductType.find(".btn-delete").unbind().on("click",function(e){
		 			 deleteNode(this);
				 });
			}
		};
		W.ajax(opt);
	}
	
	
	//点击隐藏的时候，除了隐藏子元素之外，同步递归隐藏孙子，穷孙子元素
	function hideChildren(e){
		e.each(function(index,event){
			$(event).hide(0,function(){
				$(event).find(".row-details").removeClass("fa-minus-square-o").addClass("fa-plus-square-o");
				var id = $(event).find(".row-details").data("id");
				id = id== null ? "0" : id;
				var children = $("#productTypeTab .row-details[parent='" + id + "']").closest("tr");
				hideChildren(children);
			});
		});
	}
	
	
	function addChild(e){
		var productTypeId = $(e).data("producttypeid");
		openDialog("${contextPath}/weixin/productType/add?productTypeId=" + productTypeId);
	}
	
	function editNode(e){
		var productTypeId = $(e).data("producttypeid");
		openDialog("${contextPath}/weixin/productType/edit?productTypeId=" + productTypeId);
	}
	
	function deleteNode(e){
		var productTypeId = $(e).data("producttypeid");
		bootbox.confirm("确定要删除该产品类型吗？","",function(r){
			if(r){
				W.ajax({
					url : "${contextPath}/weixin/productType/delete",
					data: {productTypeId:productTypeId},
					success:function(result){
						if(SUCCESS == result.result){
							bootbox.alert("删除成功","提示",function(){
								dataTable.ajax.reload();
							});
						}else{
							bootbox.alert("数据异常，删除失败");
						}
					}
				});
			}
		});
	}
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			title: "产品类型",
			width:700,
			loadUrl: url
		});
	}
	
	function addRoot(){
		openDialog("${contextPath}/weixin/productType/add");
	}
	
	return {
		init : function(){
			weixinProductType = $("#weixin-productType");
			initTable();
			weixinProductType.find("#addRoot").on("click",addRoot);
		}
	}
}();



$().ready(function(){
	weixin.productType.init();
});

</script>
<div class="container-content container-plain" id="weixin-productType">
	<div class="container-body">
		<div id="edit-title" style="display: ${companyId == null ? 'block' :'none' }">
			<div class="text-center margin-20">您尚未创建自己的工作室，先创建工作室后再进行操作。</div>
		</div>
		<div class="table-toolbar" style="display: ${companyId == null ? 'none' :'block' }">
	    	<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
					<div class="col-sm-12">
						<a id="addRoot" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加根节点</a>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover" id="productTypeTab" style=" ${companyId == null ? 'display:none' :'' }">
		</table>
	</div>
</div>
