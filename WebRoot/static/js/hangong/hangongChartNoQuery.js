var contextPath = getContextPath();
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
$(document).ready(function () {
	if(globalQuerychartNo!=null && globalQuerychartNo!="null"){
		$("#chartNo").val(globalQuerychartNo);
	}
	if(globalQueryhankouNo!=null && globalQueryhankouNo!="null"){
		$("#hankouNo").val(globalQueryhankouNo);
	}
});

function checkall() {
	for (var i = 0; i < document.getElementsByName("_chk").length; i++) {
		document.getElementsByName("_chk")[i].checked = document.getElementById("ifAll").checked;
	}
}
function query() {
	var chartNo = $("#chartNo").val();
	var hankouNo = $("#hankouNo").val();
	var url = contextPath + "/hangongChartNoQuery.do";
	if (chartNo != "" && hankouNo != "") {
		url = url + "?chartNo=" + chartNo + "&hankouNo=" + hankouNo;
	}else if(chartNo == "" && hankouNo != "") {
		url = url + "?hankouNo=" + hankouNo;
	}else if(chartNo != "" && hankouNo == "") {
		url = url + "?chartNo=" + chartNo;
	}
	window.location.href = url;
}

