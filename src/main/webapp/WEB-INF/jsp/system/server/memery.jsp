<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<script src="${jsPath }/websocket/sockjs.js"></script>
<script src="${jsPath }/websocket/stomp.js"></script>

<script type="text/javascript">
	$.namespace("system.memery");

	system.memery = function() {
		var systemMemery, websocket, stompClient, memeryChart, labels;

		function connect() {
			websocket = new SockJS("${contextPath}/websocket");
			stompClient = Stomp.over(websocket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/memeryScale', function(data) { //订阅消息
					console.log(new Date());
					console.log(data);
					var resultList = $.parseJSON(data.body);
					memeryChart.data.labels = resultList.labels;
					
					for(var i = 0; i < resultList.dataSet.length; i++){
						memeryChart.data.datasets[i].data = resultList.dataSet[i];
					}
					memeryChart.update();
				});

			});
		}

		function initChart() {
			W.ajax({
				url : "${contextPath}/system/memery/load",
				success:function(result){
					
					var datasets = [];
					for(var i = 0;i < result.dataSet.length ; i++){
						var borderColor = W.randomColor();
						var pointBorderColor = W.randomColor();
						var lineOpt = {
							label : result.names[i],
							fill : false,
							lineTension : 0.1,
							borderColor : borderColor,
							borderCapStyle : 'butt',
							borderDash : [],
							borderDashOffset : 0.0,
							pointBorderColor : pointBorderColor,
							pointBorderWidth : 3,
							pointHoverRadius : 5,
							pointHoverBackgroundColor : pointBorderColor,
							pointHoverBorderWidth : 3,
							pointRadius : 1,
							pointHitRadius : 10,
							data : result.dataSet[i],
							spanGaps : false	
						};
						datasets.push(lineOpt);
					}
					var ctx = systemMemery.find("#memeryChart");
					memeryChart = new Chart(ctx, {
						type : 'line',
						data : {
							labels : result.labels,
							datasets : datasets
						},
					    options: {
					        scales: {
					            yAxes: [{
					                ticks: {
					                	max: 100,
					                    min: 0,
					                    stepSize: 10
					                }
					            }]
					        }
					    }
					});
					
					connect();
				}
			});
		}

		return {
			init : function(){
				systemMemery = $("#system-memery");
				initChart();
			}
		};
	}();
	
	$().ready(function(){
		system.memery.init();
	});
</script>
<div class="container-content" id="system-memery">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-header ">
			            <i class="widget-icon typcn typcn-chart-line sky"></i>
			            <span class="widget-caption">服务器内存使用监控（%）</span>
					</div>
					<div class="widget-body" style="height: 500px;">
						<canvas id="memeryChart" width="0" height="0"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

