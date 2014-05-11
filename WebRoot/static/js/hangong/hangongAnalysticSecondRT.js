/***********全局变量开始**********/
var hostAllocationXml = null;
var resUsedAnalysticxml = null;
var chartSuffix = "_chart";
var decimalScale = 2;
var displayNum = 6;
/***********全局变量结束**********/

$(document).ready(function(){
	if (globalQueryStartDate != null && globalQueryStartDate != "null" 
			&& globalQueryEndDate != null && globalQueryEndDate != "null") {
		$("#startDate").val(globalQueryStartDate);
		$("#endDate").val(globalQueryEndDate);

	}
	if(globalQueryHangongNo!=null && globalQueryHangongNo!="null"){
		$("#hangongNo").val(globalQueryHangongNo);
	}
	if(globalQueryHangongName!=null && globalQueryHangongName!="null"){
		$("#hangongName").val(decodeURIComponent(globalQueryHangongName));
	}
})

function choose(){
	var queryByHangongChecked = $("input[name=chooseRadio]:eq(0)").attr("checked");
	if(queryByHangongChecked){
		$("#hangongQueryContainer").show();
		$("#chartNoQueryContainer").hide();
	}else{
		$("#chartNoQueryContainer").show();
		$("#hangongQueryContainer").hide();
	}	
}

function query() {
	var hangongNo = $.trim($("#hangongNo").val());
	var hangongName = $.trim($("#hangongName").val());
 	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var url = contextPath + "/hangongAnalysticSecondRT.do";
	var param = "";
	var map={};
	if (startDate != "" && endDate != "") {
		map["startDate"] = startDate;
		map["endDate"] = endDate;
	}
	if(hangongNo!=""){
		map["hangongNo"] = hangongNo;
	}
	if(hangongName != ""){
		map["hangongName"] =  encodeURIComponent(encodeURIComponent(hangongName));
	}
	
	var paramNumber = 0;
	for(key in map){ 
		paramNumber = paramNumber + 1; 
  		param = param +  key + "=" + map[key] + "&";
	}
	if(paramNumber > 0){
		param = param.substr(0, param.length - 1);
		url = url + "?" + param;
	}
	window.location.href = url;
}

function hiddenHangongTable(hangongNo){
	$("#hangongTableContainer").hide(); 
	var url = contextPath + "/hankouTableSecondRT.do";
	url = url + "?hangongNo=" + hangongNo;
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (startDate != "" && endDate != "") {
		url = url + "&startDate=" + startDate + "&endDate=" + endDate;
	}
	$("#hankouIframe").attr("src",url);	
	$("#hankouIframeContainer").show();
}

function showHangongTable(){
	$("#hangongTableContainer").show();
	$("#hankouIframeContainer").hide();
}
