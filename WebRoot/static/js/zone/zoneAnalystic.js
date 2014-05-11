/***********全局变量开始**********/
var hostAllocationXml = null;
var resUsedAnalysticxml = null;
var chartSuffix = "_chart";
var decimalScale = 2;
var displayNum = 1;
/***********全局变量结束**********/

$(document).ready(function(){
	//getPieData();
	getMsColumnData(jsonStringData);
});

function query(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	getZoneAnalysticJsonData(startDate, endDate);
	$("#tableFrame").attr("src", function() {
		return "zoneAnalysticTable.do?startDate=" + startDate + "&endDate="+endDate;
	});
}

function getMsColumnData(json) {
		var categories = [];
		var distributeRatePoint = [];
		var distributeRatePointText = [];
		var distributeRatePointTip = [];
		var zoneQualifiedRatePoint = [];
		var zoneQualifiedRatePointText = [];
		var zoneQualifiedRatePointTip = [];
		for (var i in json) {
			categories.push(json[i].dataType);
			distributeRatePoint.push(json[i].unqualifiedDistributeRate);
			distributeRatePointText.push(json[i].unqualifiedDistributeRate);
			distributeRatePointTip.push(json[i].unqualifiedDistributeRate);
			zoneQualifiedRatePoint.push(json[i].zoneQualifiedRate);
			zoneQualifiedRatePointText.push(json[i].zoneQualifiedRate);
			zoneQualifiedRatePointTip.push(json[i].zoneQualifiedRate);
		}
	resUsedAnalysticxml = generateResUsedAnalysticXml(categories, distributeRatePoint,
		"不合格分布", distributeRatePointText, distributeRatePointTip, zoneQualifiedRatePoint, "区域合格率",
		zoneQualifiedRatePointText, zoneQualifiedRatePointTip); 
		drawChart_ResUsedAnalysticxml();
}

function getZoneAnalysticJsonData(startDate, endDate) {
	$.ajax({type:"GET", 
		url:"getZoneAnalysticJsonData.do", 
		data:{
			startDate:startDate, 
			endDate:endDate
		},
		dataType:"json", 
		success:function (json) {
 			getMsColumnData(json);
	}});
}

function _fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, webPath) {
	var myChart = new FusionCharts(webPath + "/common/lib/fusionChartsFree/Charts/" + chartType + ".swf", widgetId + chartSuffix, width, height, "0", "1");
	myChart.addParam("wmode","Opaque");	
	myChart.setDataXML(xml);
	myChart.render(widgetId);
}

function drawChart_hostAllocation() {
	var chartType = "Pie2D";
	/**
	 * 该处不能使用百分比的布局，会造成重绘时计算的组件大小不准确
	*/
	var width = "360px";
	var height = "360px";
	var widgetId = "hostAllocationChartContainer";
	var xml = "";
	if (hostAllocationXml) {
		xml = hostAllocationXml;
	}
	_fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, path);
}

function drawChart_ResUsedAnalysticxml() {
	var actualWidth = document.getElementById("zoneClsssChartContainer").offsetWidth - 40;
	var width = actualWidth;
	var height = "330";
	var widgetId = "zoneClsssChartContainer";
	var chartType = "FCF_MSColumn2D";
	var xml = "";
	if (resUsedAnalysticxml) {
		xml = resUsedAnalysticxml;
	}
	_fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix,
			path);
}

function generateResUsedAnalysticXml(resUsageCategories, cpuUsagePoints,
		cpuUsageLabel, cpuText, cpuTip, memUsagePoints, memUsageLabel,
		memoryText, memoryTip, diskUsagePoints, diskUsageLabel, diskText,
		diskTip) {
	var xml = "<graph xaxisname='' yaxisname='' hovercapbg='DEDEBE' hovercapborder='889E6D' rotateNames='0' numberSuffix='%' "
			+"animation='1' yAxisMaxValue='100' numdivlines='9' divLineColor='CCCCCC' divLineAlpha='80' "
			+"showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC' caption='' subcaption='' "
			+" borderColor='FFFFFF' baseFontSize='11' baseFontColor='666666' canvasBorderColor='C0C0C0' canvasBorderThickness='1' decimalPrecision='1' showShadow='0'"
			+">"
			+ "<categories>"
			+ _setChartCategory(resUsageCategories)
			+ "</categories>"
			+ "<dataset seriesName='"
			+ cpuUsageLabel
			+ "' "
			+ "color='2277DD' plotBorderThickness='1' plotBorderColor='BDEF42' showvalues='1'>" // 上部
			+ _setChartData(cpuUsagePoints, cpuText, cpuTip)
			+ "</dataset>"
			+ "<dataset seriesName='"
			+ memUsageLabel
			+ "' "
			+ "color='FF8811' plotBorderThickness='1' plotBorderColor='EFCE6B' showvalues='1'>" // 中间
			+ _setChartData(memUsagePoints, memoryText, memoryTip)
			+ "</dataset>"
			+ "<styles>"
			+ "<definition>"
			+ "<style name='captionFont' type='font' size='15' color='7D8892' />"
			+ "</definition>"
			+ "<application><apply toObject='caption' styles='captionfont' />"
			+ "</application>" + "</styles>" + "</graph>";
	return xml;
}

// 用于柱线图 输入必须为Array
function _setChartData(dataPoints, textArray, tipArray) {
	var xml = " ";
	if (dataPoints && dataPoints.length > 0) {
		for (var j = 0; j < dataPoints.length; j++) {
			// value*100生成百分比格式的数据
			var value = dataPoints[j] * 100;
			var text = textArray[j];
			var tip = 'tip';
			if (tipArray.length > 0)
				tip = tipArray[j];
			if (value == "-1")
				xml = xml + "<set value='0' /> ";
			else {
				xml = xml + "<set value='" + value.toFixed(2) + "' /> ";
			}
		}
	}
	for (var k = 0; k < displayNum - dataPoints.length; k++)
		xml = xml + "<set diplayValue='' value='0' showValue='0'/> ";
	return xml;
}

function _setChartCategory(categoryPoints) {
	var xml = " ";
	if (categoryPoints && categoryPoints.length > 0) {
		for (var j = 0; j < categoryPoints.length; j++) {
			xml = xml + "<category   name='" + categoryPoints[j] + "' /> ";
		}
	}
	for (var k = 0; k < displayNum - categoryPoints.length; k++)
		xml = xml + "<category   name='' /> ";
	return xml;
}