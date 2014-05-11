<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
String path = request.getContextPath();
%>
<head>
	<script type="text/javascript">
		var path = "<%=path%>";
	</script>
	<link href="<%=path%>/static/css/default/common/content.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=path%>/static/css/default/common/Global.css"/>
<style type="text/css">
.btn {
	position: relative;
	cursor: pointer;
	display: inline-block;
	vertical-align: middle;
	font-size: 12px;
	font-weight: bold;
	height: 27px;
	line-height: 27px;
	min-width: 20px;
	padding: 0 12px;
	text-align: center;
	text-decoration: none;
	border-radius: 2px;
	border: 1px solid #ddd;
	color: #666;
	background-color: #f5f5f5;
	background: -webkit-linear-gradient(top, #F5F5F5, #F1F1F1);
	background: -moz-linear-gradient(top, #F5F5F5, #F1F1F1);
	background: linear-gradient(top, #F5F5F5, #F1F1F1);
}
input.btn {
	height: 29px;
}
.btn:hover {
	border-color:#c6c6c6;
	color:#333;
	background-color:#f8f8f8;
	background:-webkit-linear-gradient(top, #f8f8f8, #f1f1f1);
	background:-moz-linear-gradient(top, #f8f8f8, #f1f1f1);
	background:linear-gradient(top, #f8f8f8, #f1f1f1);
	box-shadow:#ddd 0 1px 1px 0;
}
.btn:active, .btn.btn-active {
	box-shadow:#ddd 0 1px 2px 0 inset;
	border-color:#c6c6c6;
}
.btn:focus {
	border-color:#4d90fe;
	outline:none
}
.btn-primary {
	border-color: #3079ED;
	color: #F3F7FC;
	background-color: #4D90FE;
	background: -webkit-linear-gradient(top, #4D90FE, #4787ED);
	background: -moz-linear-gradient(top, #4D90FE, #4787ED);
	background: linear-gradient(top, #4D90FE, #4787ED);
}
.btn-primary:hover {
	border-color:#2F5BB7;
	color:#fff;
	background-color: #4D90FE;
	background: -webkit-linear-gradient(top, #4D90FE, #357AE8);
	background: -moz-linear-gradient(top, #4D90FE, #357AE8);
	background: linear-gradient(top, #4D90FE, #357AE8);
}
.btn-primary:active, .btn-primary.btn-active {
	box-shadow:#2176D3 0 1px 2px 0 inset;
	border-color: #3079ED;
}
.btn-primary:focus {
	border-color:#4d90fe;
	outline:none
}
</style>
</head>
	<body style="overflow-x: hidden">
		<div class="table_main">
			<table border=0>
				<col align="left" width />
				<col align="left" />
				<tr>

					<td style="font-size: 12px;">
						周合格率警戒值：
					</td>
					<td>
						<input class="ipt" type="text"
							style="width: 150px; text-align: right" id="alertLimit"
							value="${alertLimit}" size="10" />
						%
						<input type="button" onclick="settingAlertLimit()" value="修改" />
					</td>
				<tr>
				<tr>
					<div>
					<td style="font-size: 12px;">
						周返修合格率警戒值：
					</td>
					<td>
						<input class="ipt" type="text" 
							style="width: 150px; text-align: right" id="alertLimitSecondRT"
							value="${alertLimitSecondRT}" size="10" />
						%
						<input type="button" onclick="settingAlertLimitSecondRT()"
							value="修改" />
					</td>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						累计合格率起始时间
					</td>
					<td>
						<input id="cumulativeStartDate" type="text" readonly style="width:150px;text-align: right"
							value="${cumulativeStartDate}" onClick="WdatePicker()" />
						<img onclick="WdatePicker({el:'cumulativeStartDate'})"
							src="<%=path%>/common/lib/My97DatePicker/skin/datePicker.gif"
							width="12" height="22"
							style="cursor: pointer; vertical-align: middle;" />
						<input type="button" onclick="settingCumulativeStartDate()" value="修改" />
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript"
			src="<%=path%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/common/lib/My97DatePicker/WdatePicker.js"></script>
		<script src="<%=path%>/static/js/common/common.js"></script>
		<script type="text/javascript"
			src="<%=path%>/static/js/setting/alertLimit.js"></script>
	</body>
</html>
