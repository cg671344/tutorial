/***********全局变量开始**********/
var weekAnalysticxml = null;
var chartSuffix = "_chart";
var decimalScale = 2;
var displayNum = 1;
/***********全局变量结束**********/

$(document).ready(function(){
	weekAnalysticxml = generateResUsedAnalysticXml(weekJsonDataString, weekSumJsonDataString);
	drawChart_WeekAnalysticxml();
});

function query(){
	var selectValue = $("#yearSelect").val();
	window.location.href = path+ "/monthAnalysticSecondRT.do?year=" + selectValue;
}
function _fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, webPath) {
	var myChart = new FusionCharts(webPath + "/common/lib/fusionChartsFree/Charts/" + chartType + ".swf", widgetId + chartSuffix, width, height, "0", "1");
	myChart.addParam("wmode","Opaque");
	myChart.setDataXML(xml);
	myChart.render(widgetId);
}

function drawChart_WeekAnalysticxml() {
	var actualWidth = document.getElementById("chartContainer").offsetWidth - 40;
	var width = "650";
	var height = "400";
	var widgetId = "chartContainer";
	var chartType = "FCF_MSColumn2DLineDY";
	var xml = "";
	if (weekAnalysticxml) {
		xml = weekAnalysticxml;
	}
	_fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix,
			path);
}

function generateResUsedAnalysticXml(weekJson, weekSumJson) {
	var nameArray = [];
	var valueArray = [];
	var hoverText = [];
	var categories ="";
	var data = "";
	var data3 = "";
	for (var i in weekJson) {
		categories = categories + '<category name="'+ weekJson[i].month + '"/>';
		var qulifiedRate = new Number(weekJson[i].qulifiedRate*100);
		var dipianNumber = new Number(weekJson[i].dipianNumber);
		var hangkouNumber = new Number(weekJson[i].hangkouNumber);
		if(dipianNumber != 0){
			data = data +'<set value="'
				+ qulifiedRate.toFixed(2)
				+'" />';
				
			data3 = data3 + '<set value="'
				+ hangkouNumber
				+'" />';	
		}
	}
	var data2 = "";
	for (var i in weekSumJson) {
		var qulifiedRate = new Number(weekSumJson[i].qulifiedRate*100);
		var dipianNumber = new Number(weekJson[i].dipianNumber);
		if(dipianNumber != 0){
			data2 = data2 +'<set value="'
				+ qulifiedRate.toFixed(2)
				+'" />';
		}
	}
	var xml = '<graph caption="'
			+ year + '年'
			+ '合格率趋势图" subcaption="月度统计" xAxisName="月份" PYAxisName="" SYAxisName="合格率" showNames="1" SYAxisMinValue="80" SYAxisMaxValue="100"  formatNumberScale="0" showValues="0" showAlternateHGridColor="1" color="1D8BD1" AlternateHGridColor="ff5904" divLineColor="ff5904" divLineAlpha="20" alternateHGridAlpha="5"'
			+' formatNumber="0" decimalPrecision="2" baseFontSize="11" baseFontColor="666666" canvasBorderColor="C0C0C0" canvasBorderThickness="1">'
			+'<categories>'
			+ categories
			+'</categories>'
			+ '<dataset seriesName="焊口数" color="00CC00" showValues = "0" >'
			+ data3
			+ '</dataset>'
			+ '<dataset seriesName="月合格率" color="FF0000" anchorBorderColor="FF0000" anchorBgColor="FF0000" parentYAxis="S">'
			+ data
			+ '</dataset>'
			+ '<dataset seriesName="累计合格率" color="0000FF" anchorBorderColor="0000FF" anchorBgColor="0000FF" parentYAxis="S">'
			+ data2
			+ '</dataset>'
			+ '</graph>'; 
	return xml;
}