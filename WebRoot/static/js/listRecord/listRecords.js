
var contextPath = getContextPath();
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
$(document).ready(function () {
	if (globalQueryStartDate != null && globalQueryStartDate != "null" && globalQueryEndDate != null && globalQueryEndDate != "null") {
		$("#startDate").val(globalQueryStartDate);
		$("#endDate").val(globalQueryEndDate);
	}
});
function showDate() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (startDate == "" || endDate == "") {
		alert("\u8bf7\u9009\u62e9\u67e5\u8be2\u7684\u8d77\u59cb\u65e5\u671f\u548c\u7ed3\u675f\u65e5\u671f\uff01");
	}
}
function cancel() {
	$("embed").remove();
}
function checkall() {
	for (var i = 0; i < document.getElementsByName("_chk").length; i++) {
		document.getElementsByName("_chk")[i].checked = document.getElementById("ifAll").checked;
	}
}
function query() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var url = contextPath + "/listRecords.do";
	if (startDate != "" && endDate != "") {
		url = url + "?startDate=" + startDate + "&endDate=" + endDate;
	}
	window.location.href = url;
}
function editZone(id, zone, zoneClass) {
	$("#recordId").val(id);
	$("#zone").val(zone);
	$("#zoneClass").val(zoneClass);
	$("#recordContainer").hide();
	$("#zoneContainer").show();
}
function returnToList() {
	$("#zoneContainer").hide();
	$("#recordContainer").show();
}
function saveZone() {
	var zone = $("#zone").val();
	var zoneClass = $("#zoneClass").val();
	var recordId = $("#recordId").val();
	$("#zoneContainer").hide();
	$("#recordContainer").show();
	$.ajax({type:"post", url:contextPath + "/editRecord.do", data:{recordId:recordId, zone:zone, zoneClass:zoneClass}, beforeSend:function (XHR) {
		common.showLoading(true, null);
		return true;
	}, dataType:"json", success:function (data) {
		common.showLoading(false, null);
		if (data.status == "SUCCESS") {
			alert("\u4fee\u6539\u6210\u529f\uff01");
			if ($("#recordContainer select").get(0)) {
				//需要把参数带过去，重新定位到当前页码
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				url = $("#recordContainer select").get(0).value;//获取带页码的url地址
				if (startDate != "" && endDate != "") {
					url = url + "&startDate=" + startDate + "&endDate=" + endDate;
				}
				window.location.href = url;
			}else{
				query();
			}
		}
	}, error:function (data) {
		common.showLoading(false, null);
		alert("\u670d\u52a1\u5f02\u5e38");
	}});
}

