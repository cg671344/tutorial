$(document).ready(function(){
});


function settingAlertLimit() {
    var alertLimit = $('#alertLimit').val();
    if(isNaN(alertLimit)){
		alert("必须输入数字!");
    	return;
    }
    var num = new Number(alertLimit);
	if(num >100 || num < 0){
		alert("输入数字需在0-100之间");
		return;
	}    
	$.ajax({type:"post", 
		url : path + "/setting/setAlertLimitSetting.do", 
		data:{
			alertLimit:alertLimit
		},
		beforeSend : function(XHR){
			common.showLoading(true,null);
			return true;
		},
		dataType:"json", 
		success:function (data){  
            	common.showLoading(false,null);
                if(data.status == "SUCCESS"){  
                     alert("参数修改成功！");  
                }
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}

function settingAlertLimitSecondRT() {
    var alertLimit = $('#alertLimitSecondRT').val();
    if(isNaN(alertLimit)){
		alert("必须输入数字!");
    	return;
    }
    var num = new Number(alertLimit);
	if(num >100 || num < 0){
		alert("输入数字需在0-100之间");
		return;
	}    
	$.ajax({type:"post", 
		url : path + "/setting/setAlertLimitSettingSecondRT.do", 
		data:{
			alertLimit:alertLimit
		},
		beforeSend : function(XHR){
			common.showLoading(true,null);
			return true;
		},
		dataType:"json", 
		success:function (data){  
            	common.showLoading(false,null);
                if(data.status == "SUCCESS"){  
                      alert("参数修改成功！");  
                }
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}

function settingCumulativeStartDate() {
    var cumulativeStartDate = $('#cumulativeStartDate').val();
	$.ajax({type:"post", 
		url : path + "/setting/setCumulativeStartDate.do", 
		data:{
			cumulativeStartDate : cumulativeStartDate
		},
		beforeSend : function(XHR){
			common.showLoading(true,null);
			return true;
		},
		dataType:"json", 
		success:function (data){  
            	common.showLoading(false,null);
                if(data.status == "SUCCESS"){  
                    alert("参数修改成功！");  
                }
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}