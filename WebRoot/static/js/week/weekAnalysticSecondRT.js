/***********全局变量开始**********/
var weekAnalysticxml = null;
var chartSuffix = "_chart";
var decimalScale = 2;
var displayNum = 1;
/***********全局变量结束**********/

$(document).ready(function(){
	weekAnalysticxml = generateResUsedAnalysticXml(weekJsonDataString, weekSumJsonDataString);
	drawChart_WeekAnalysticxml();
	if(toAlert == 'true'){
		$('body').append('<embed src="' + contextPath+'/static/audio/4031.wav" autostart="true" hidden="true"/>');
	}
});

function query(){
	var selectValue = $("#yearSelect").val();
	window.location.href = path+ "/weekAnalysticSecondRT.do?year=" + selectValue;
}
function _fusionChartsDraw(chartType, xml, width, height, widgetId, chartSuffix, webPath) {
	var myChart = new FusionCharts(webPath + "/common/lib/fusionChartsFree/Charts/" + chartType + ".swf", widgetId + chartSuffix, width, height, "0", "1");
	myChart.addParam("wmode","Opaque");
	myChart.setDataXML(xml);
	myChart.render(widgetId);
}

function drawChart_WeekAnalysticxml() {
	var actualWidth = document.getElementById("chartContainer").offsetWidth - 40;
	var width = actualWidth;
	var height = "400";
	var widgetId = "chartContainer";
	var chartType = "FCF_MSLine";
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
	var displayEndWeekSeq = -1;//
	for (var i= weekJson.length-1 ; i>=0; i--) {
		categories = '<category name="'+ weekJson[i].weekSeq + '"/>' +  categories;
		var num = new Number(weekJson[i].qulifiedRate*100);
		var dipianNumber = new Number(weekJson[i].dipianNumber);
		var weekSeq = new Number(weekJson[i].weekSeq);
		if(dipianNumber !=0 && displayEndWeekSeq == -1){
			displayEndWeekSeq = i;
		}
		if(weekSeq <= currentWeek && i <= displayEndWeekSeq){
						data = '<set value="'
				+ num.toFixed(2)
				+'" />' + data;
			}
	}
	
	var data2 = "";
	var weekSumIndex = 1;
	for (var i in weekSumJson) {
		var num = new Number(weekSumJson[i].qulifiedRate*100);
		var dipianNumber = new Number(weekJson[i].dipianNumber);
		if(weekSumIndex <= currentWeek && i<= displayEndWeekSeq){
			data2 = data2 +'<set value="'
				+ num.toFixed(2)
				+'" />';
		}
		weekSumIndex = weekSumIndex+1;
	}
	var xml = '<graph caption="'
			+ year + '年'
			+ '合格率趋势图" subcaption="周统计" xAxisName="周" yAxisMinValue="80" yAxisMaxValue="100" yAxisName="合格率" decimalPrecision="1" formatNumberScale="0" numberSuffix="%" showNames="1" showValues="0" showAlternateHGridColor="1" color="1D8BD1" AlternateHGridColor="ff5904" divLineColor="ff5904" divLineAlpha="20" alternateHGridAlpha="5"'
			+'  baseFontSize="11" baseFontColor="666666" canvasBorderColor="C0C0C0" canvasBorderThickness="1" rotateNames="0">'
			//'<graph caption="Daily Visits" subcaption="(from 8/6/2006 to 8/12/2006)" hovercapbg="FFECAA" hovercapborder="F47E00" formatNumberScale="0" decimalPrecision="0" showvalues="0" numdivlines="3" numVdivlines="0" yaxisminvalue="1000" yaxismaxvalue="1800" rotateNames="0">'
			+'<categories>'
			+categories
			+'</categories>'
			+ '<dataset seriesName="周合格率" color="FF0000" anchorBorderColor="F1683C" anchorBgColor="F1683C">'
			+ data
			+ '</dataset>'
			+ '<dataset seriesName="累计合格率" color="0000FF" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">'
			+ data2
			+ '</dataset>'			
			+ '<trendlines>'
      		+'<line value="'
      		+ weekAlertLimit
      		+'" color="FF8811" thickness="1" />'
  			+'</trendlines>'
			+ '</graph>'; 
			//F1683C 2277DD 88CC33 FF8811 FF0000 0000FF
	return xml;
}