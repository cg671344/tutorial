/***********全局变量开始**********/
var hostAllocationXml = null;
var resUsedAnalysticxml = null;
var chartSuffix = "_chart";
var decimalScale = 2;
/***********全局变量结束**********/

$(document).ready(function(){
	getPieData();
	getMsColumnData();
});

function getMsColumnData() {
	$.ajax({type:"GET", url:path + "/getMsColumnData.do", dataType:"json", success:function (json) {
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
	}});
}

function getPieData() {
	$.ajax({type:"GET", url:path + "/getPieData.do", dataType:"json", success:function (json) {
		var labelArray = [];
		var valueArray = [];
		for (var i in json) {
			labelArray.push(json[i].label);
			valueArray.push(json[i].value);
		}
		hostAllocationXml = generatePysicalhostAllocationXml(labelArray, valueArray);
		drawChart_hostAllocation();
	}});
}

function _fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, webPath) {
	FusionCharts.setCurrentRenderer("javascript");
	var charRefer = FusionCharts(widgetId + chartSuffix);
	if (charRefer != null) {
		charRefer.dispose();
	}
	var myChart = new FusionCharts(webPath + "/common/lib/fusionCharts/xt/" + chartType + ".swf", widgetId + chartSuffix, width, height, "0", "1");
	myChart.addParam("wmode","Opaque");
	myChart.setXMLData(xml);
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
	//document.getElementById("hostAllocationChartTitle").innerHTML = "\u4e3b\u673a\u4f7f\u7528\u7edf\u8ba1";
	_fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, path);
}

function drawChart_ResUsedAnalysticxml() {
	var width = "600px";
	var height = "330px";
	var widgetId = "zoneClsssChartContainer";
	/* " 不同的表现方式MSColumn3D MSColumn2D"MSArea MSColumn2D MSBar2D */
	var chartType = "MSColumn2D";
	var xml = "";
	if (resUsedAnalysticxml) {
		xml = resUsedAnalysticxml;
	}
	_fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix,
			path);
}


function generatePysicalhostAllocationXml(labelArray, valueArray) {
	/**
	 * 如果出现异常数据，返回空串
	 */
	if (labelArray.length != valueArray.length) {
		return "";
	}
	var xmlDataValue = "";
	for (i = 0; i < labelArray.length; i++) {
		xmlDataValue = xmlDataValue + "<set value='" + valueArray[i] + "' label='" + labelArray[i] + "' />";
	}
	var xml = "<chart  formatNumberScale='0' decimals='0' showShadow='0' showPercentageInLabel='1' use3DLighting='0'  showPlotBorder='0' plotBorderAlpha='0' " 
		+ "pieRadius='100'   slicingDistance ='8'  bgColor='FFFFFF' " 
		+ "borderColor='FFFFFF' enableSmartLabels='0'    manageLabelOverflow='1' numberSuffix='\u53f0' bgAlpha='30,100' " 
		+ "bgAngle='45' startingAngle='175'  smartLineColor='7D8892' smartLineThickness='2'>" 
		+ xmlDataValue 
		+ "<styles>" + "<definition>" + "<style name='CaptionFont' type='FONT' face='Verdana' size='15' color='7D8892' bold='1' />" 
		+ "<style name='LabelFont' type='FONT' color='7D8892' bold='1'/>" + "</definition>" + "<application>" 
		+ "<apply toObject='DATALABELS' styles='LabelFont' />" + "<apply toObject='CAPTION' styles='CaptionFont' />" 
		+ "</application>" + "</styles>" + "</chart>";
	return xml;
}

function generateResUsedAnalysticXml(resUsageCategories, cpuUsagePoints,
		cpuUsageLabel, cpuText, cpuTip, memUsagePoints, memUsageLabel,
		memoryText, memoryTip, diskUsagePoints, diskUsageLabel, diskText,
		diskTip) {
	// yAxisMaxValue='100' yAxisMinValue='0'
	var xml = "<chart palette='1' plotGradientColor='' decimals='2' yAxisValueDecimals='5' yAxisMaxValue='100' yAxisMinValue='0' caption='"
			+ ""
			+ "' "
			+ "showLegend='1' showLabels='1' showvalues='0' numberSuffix='%' connectNullData='0' bgColor='FFFFFF' borderColor='FFFFFF' "
			/*
			 * + "showCanvasBg='1' canvasbgColor='C0C0C0' canvasBgAlpha='20'
			 * canvasBorderColor='C0C0C0' showAlternateHGridColor='0'
			 * canvasBorderAlpha='80' canvasBorderThickness='1' "
			 */+ "showAlternateVGridColor='1' AlternateVGridColor='e1f5ff' divLineColor='e1f5ff' vdivLineColor='e1f5ff' "
			+ "baseFontColor='666666' canvasBorderThickness='1' showPlotBorder='0' plotBorderThickness='0' canvaspadding='8' "
			+ "plotFillAlpha='100' drawAnchors='1' showCanvasBg='1' canvasBgAlpha='100' canvasBorderColor='C0C0C0' canvasBorderAlpha='80' "
			+ "numDivLines='4' adjustDiv='true'>"
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
			+ "</application>" + "</styles>" + "</chart>";
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
				// xml = xml + "<set displayValue='" + text + "' value='" +
				// value + "' toolText='" + tip + "' /> ";
				xml = xml + "<set displayValue='" + value.toFixed(2)
						+ "' value='" + value.toFixed(2) + "' toolText='"
						+ value.toFixed(2) + "' /> ";
			}
			// xml = xml + "<set displayValue='50/100 15%' value='" + value + "'
			// /> ";
		}
	}
	return xml;
}

function _setChartCategory(categoryPoints) {
	var xml = " ";
	if (categoryPoints && categoryPoints.length > 0) {
		for (var j = 0; j < categoryPoints.length; j++) {
			xml = xml + "<category   label='" + categoryPoints[j] + "' /> ";
		}
	}
	return xml;
}