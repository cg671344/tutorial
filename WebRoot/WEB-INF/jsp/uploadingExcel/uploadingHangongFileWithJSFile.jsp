<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String contextPath = request.getContextPath();
%>
<head>
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

<script type="text/javascript">  
	var contextPath  = "<%=contextPath%>";
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
		<script type="text/javascript" src="<%=contextPath%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/common/common.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/common/lib/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/uploading/uploadingHangongFile.js"></script>
	</body>
</html>
