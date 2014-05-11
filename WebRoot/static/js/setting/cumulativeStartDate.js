$(document).ready(function(){
});


function setting() {
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
                    alert("警戒值修改成功！");  
                }
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}