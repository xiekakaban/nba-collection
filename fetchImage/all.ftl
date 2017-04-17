<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../static/js/echarts.js"></script>
    <script src="../static/js/jquery.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<div id="wrapper" width="100%">
		<div class="row">
			<div id="main" class="col-md-8" style="height:900px;"></div>
			<div id="detail" class="col-md-4" style="padding-top:50px;">
				<div id="chart">
					<div id="totleMatchsChar" style="width: 480px;height:400px;"></div>
				</div>
				<div id="information" style="padding-top:20px;">
					<div id="winChart" style="width: 500px;height:400px;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>

var isIgnore = false;
var homeTeam = "";
var awayTeam = "";
var firTeam = "";
var secTeam = "";
var mainChart = echarts.init(document.getElementById("main"));
var totalMatchChart = echarts.init(document.getElementById("totleMatchsChar"));
var winChart = echarts.init(document.getElementById("winChart"));
var XAsixName = [<#list teams as team>"${team}",</#list>];
var YAsixName = [<#list teams as team>"${team}",</#list>];
var data = [<#list teamsDetail as teamDetail>[${teamDetail.xAsix},${teamDetail.yAsix},${teamDetail.allMatchsCount}],</#list>];

$(document).ready(function(){
	initMainChart();
	mainChart.on('click', function (params) {
		firTeam = XAsixName[(params.data)[0]];
		secTeam = YAsixName[(params.data)[1]];
		initDetailChart();
	});
});

function initDetailChart(){
	$.get("/ajax/matchTeam/ignore?firTeam="+firTeam+"&secTeam="+secTeam,function(getData,staus){
		getData = JSON.parse(getData);
		console.log(getData);
		var matchOption = {
			title:{
				text:firTeam+" v.s "+secTeam
			},
			tooltip:{
				formatter: "{a}:{c}<br/>{b}"
			},
			legend: {
                data:[firTeam,secTeam,"分差"]
            },
            xAxis: {
                data: (getData.matchs).map(function(item){return item.happendTime;})
            },
            yAxis: {},
            series: [{
                name: firTeam,
                type: 'bar',
                data: (getData.matchs).map(function(item){return item.firScore})
            },{
            	name: secTeam,
                type: 'bar',
                data: (getData.matchs).map(function(item){return item.secScore})
            },{
			 name: "分差",
			 type:'bar',
			 data:(getData.matchs).map(function(item){return item.firScore-item.secScore})
			}]
	};
	winOption = {
		title : {
			text: 'WIN PANEL',
			subtext: firTeam+" v.s. "+secTeam,
			x:'center'
		},
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			data: [firTeam,secTeam]
		},
		series : [
			{
				type: 'pie',
				radius : '70%',
				center: ['50%', '60%'],
				data:[
					{value:getData.friTeamWin, name:firTeam},
					{value:getData.secTeamWin, name:secTeam},
				],
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}
		]
	};
	totalMatchChart.setOption(matchOption);
	winChart.setOption(winOption);
	});
}

function initMainChart(){
	data = data.map(function(item){
		return [item[0], item[1], item[2] || '-']
	});
	option = {
		title:{
			text:"2014-2016年全部比赛"
		},
		tooltip: {
			trigger:"item",
			position: 'top',
			formatter: function(params, ticket, callback){
				 return XAsixName[params.value[0]]+"v.s"+YAsixName[params.value[1]]+"<br/>"+"一共"+params.value[2]+"场";
			}
		},
		animation: false,
		grid: {
			height: '85%',
			width:'90%',
			x:"5%",
			y:"11%"
		},
		xAxis: {
			type: 'category',
			data: XAsixName,
			axisLabel :{
				interval:0 
			}
		},
		yAxis: {
			type: 'category',
			data: YAsixName,
			axisLabel :{
				interval:0 
			}
		},
		visualMap: {
			min: 0,
			max: 15,
			calculable: true,
			orient: 'horizontal',
			left: 'center',
			top: '5%'
		},
		series: [{
			name: 'All Match',
			type: 'heatmap',
			data: data,
			label: {
				normal: {
					show: true
				}
			},
			itemStyle: {
				emphasis: {
					shadowBlur: 10,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	mainChart.setOption(option);
}


</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</html>