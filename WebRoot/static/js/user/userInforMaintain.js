$(document).ready(function(){
});


function submitChangePassword(oldPassword, newPassword) {
	$.ajax({type:"post", 
		url:"changePassword.do", 
		data:{
			oldPassword:oldPassword,
			newPassword:newPassword
		},
		beforeSend : function(XHR){
			common.showLoading(true,null);
			return true;
		},
		dataType:"json", 
		success:function (data){  
            	common.showLoading(false,null);
                if(data.status == "SUCCESS"){  
                    alert("密码修改成功！");  
                }else{  
                    alert("原密码错误！");  
                }  
         },
        error:function(data){
           	common.showLoading(false,null);
            alert("服务异常");  
        }  
	});
}

function password(){
        var oldPassword = $('#oldPassword').val();  
        var newPassword = $('#newPassword').val();
        var repeatedPassword = $('#repeatedPassword').val();
        if(newPassword != repeatedPassword) {
            alert("两次输入的新密码不一致！");  
            return;  
        }  
        if(oldPassword ==""){
        	alert("原密码不能为空！");
        	return;  
        }
        if(newPassword ==""){
        	alert("新密码不能为空！");
        	return;  
        }
        submitChangePassword(oldPassword, newPassword);
}