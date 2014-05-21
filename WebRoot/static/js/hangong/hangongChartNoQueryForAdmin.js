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
	var url = contextPath + "/hangongChartNoQueryForAdmin.do";
	if (chartNo != "" && hankouNo != "") {
		url = url + "?chartNo=" + chartNo + "&hankouNo=" + hankouNo;
	}else if(chartNo == "" && hankouNo != "") {
		url = url + "?hankouNo=" + hankouNo;
	}else if(chartNo != "" && hankouNo == "") {
		url = url + "?chartNo=" + chartNo;
	}
	window.location.href = url;
}

function editZone(id, zone, zoneClass){
	$("#recordId").val(id);
	$("#zone").val(zone);
	$("#zoneClass").val(zoneClass);
	$("#recordContainer").hide();
	$("#zoneContainer").show();
}

function returnToList(){
	$("#zoneContainer").hide();
	$("#recordContainer").show();
}

function saveZone(){
	var zone = $("#zone").val();
	var zoneClass = $("#zoneClass").val();
	var recordId = $("#recordId").val();
	$("#zoneContainer").hide();
	$("#recordContainer").show();
	$.ajax({type:"post", 
		url : contextPath + "/editRecord.do", 
		data:{
			recordId :recordId,
			zone:zone,
			zoneClass:zoneClass
		},
		beforeSend : function(XHR){
			common.showLoading(true,null);
			return true;
		},
		dataType:"json", 
		success:function (data){  
            	common.showLoading(false,null);
                if(data.status == "SUCCESS"){  
                    alert("修改成功！");
                    //有分页的显示则把页码带过去，否则不带页码
                    if ($("#recordContainer select").get(0)) {
						var chartNo = $("#chartNo").val();
						var hankouNo = $("#hankouNo").val();
						url = $("#recordContainer select").get(0).value;//获取带页码的url地址
						if (chartNo != "" && hankouNo != "") {
							url = url + "&chartNo=" + chartNo + "&hankouNo=" + hankouNo;
						}else if(chartNo == "" && hankouNo != "") {
							url = url + "&hankouNo=" + hankouNo;
						}else if(chartNo != "" && hankouNo == "") {
							url = url + "&chartNo=" + chartNo;
						}
						window.location.href = url;
					}else{
						query();
					}
                }
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}


