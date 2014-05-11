<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>上传文件</title>
<link
	href="<%=contextPath%>/static/css/themes/common/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=contextPath%>/static/css/default/common/content.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=contextPath%>/static/css/default/common/Global.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=contextPath%>/static/js/common/common.js"></script>
<script src="<%=contextPath%>/common/lib/jquery/jquery.form.js"></script>
<script>  
    $(document).ready(function(){  
       $('#xlsForm').ajaxForm({  
            url:'<%=contextPath%>/file/xlsUpload.do',  
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
</script>  
</head>
	<body>
		<div class="table_main" align="left" style="padding-top: 10px">
			<form id="xlsForm" method="post" enctype="multipart/form-data">
				<label for="fileInfo" style="font-size: 12px;">请选择数据源（Excel文档）：</label>
				<input id="fileInfo" type="file"
					style="border: 1px solid #ccc; background: #fff;" name="uploadfile" />
				<input id="xlsBut" type="button" value="提交" onclick="dosubmit()" />
			</form>
		</div>
	</body>
</html>
