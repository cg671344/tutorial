 $(document).ready(function(){  
    $('#xlsForm').ajaxForm({  
         url: contextPath + '/file/xlsUpload.do',  
         type:'post',  
         dataType: 'json', 
         beforeSubmit: function (a, f, o) {//提交前的处理
         	common.showLoading(true,null);
         	return true;
         },
         success:function (data){  
         	common.showLoading(false,null);
             if(data.status == "SUCCESS"){  
                 alert("文件提交成功！");  
             }else{  
                 alert(decodeURIComponent(data.message));  
             }  
         },  
         error:function(data){
         	common.showLoading(false,null);
             alert("服务异常");  
         }  
     });  
 });  
 function dosubmit(){  
     var fileName = $('#fileInfo').val();  
     if('' == fileName) {  
         alert("请选择上传文件");  
         return;  
     }  
     var bingIndex = fileName.lastIndexOf('.');  
     if(-1 == bingIndex) {  
         alert("上传文件格式错误");  
         return;  
     }  
     var suffix = fileName.substring(bingIndex + 1, fileName.length);  
     if('xls' == suffix || 'xlsx'==suffix) {  
         $('#xlsForm').submit();  
     } else {  
         alert("上传文件必须是以“xls”或“xlsx”为后缀的Excel文件");  
     }  
    } 