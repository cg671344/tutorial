<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="com.cgtest.biz.pojo.MsColumnData"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	List<MsColumnData> list = (List<MsColumnData>) request.getAttribute("displayDataList");
	JSONArray jsonData = JSONArray.fromObject(list);
	String jsonStringData = jsonData.toString();
%>
<html>
	<head>
		<link href="<%=path%>/static/css/default/common/content.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=path%>/static/css/default/common/Global.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet"
			href="<%=path%>/common/themes/mac/jquery-ui-1.8.18.custom.css"></link>
		<script type="text/javascript">
			var path = "<%=path%>";
			var jsonStringData = eval('(' + '<%=jsonStringData%>' +')');
		</script>
		<title>资产统计页面</title>
	</head>
	<body style="overflow: auto">
		<div class="table_main" align="left">
			<div style="padding: 5px; font-size: 12px;">
				<label for="startDate">
					查询起始日期：
				</label>
				<input id="startDate" type="text" readonly value=""
					onclick="WdatePicker()" />
				<img onclick="WdatePicker({el:'startDate'})"
					src="common/lib/My97DatePicker/skin/datePicker.gif" width="12"
					height="22" style="cursor: pointer; vertical-align: middle;" />
				<label for="endDate" style="padding-left: 10px">
					查询结束日期：
				</label>
				<input id="endDate" type="text" readonly onclick="WdatePicker()" />
				<img onclick="WdatePicker({el:'endDate'})"
					src="common/lib/My97DatePicker/skin/datePicker.gif" width="12"
					height="22" style="cursor: pointer; vertical-align: middle;" />
				<input type="button" value="查询" onclick="query()" />
			</div>
			<div align="left">
				<fieldset id="resAllocationFieldSet">
					<legend>
						责任区分析
					</legend>
					<div id="zoneClsssChartContainer" align="center"></div>
				</fieldset>
			</div>
			<div align="left" style="padding-top:10px">
				<fieldset id="sourceData">
					<legend>
						数据来源
					</legend>
					<iframe id="tableFrame" scrolling="auto" frameborder="0" 
						src="<%=contextPath%>/zoneClassAnalysticTableSecondRT.do" style="width: 100%; height:400px"></iframe>
				</fieldset>
			</div>
		</div>
		<script type="text/javascript"
			src="<%=path%>/common/lib/jquery/jquery-1.5.1.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/common/lib/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=path%>/common/lib/fusionChartsFree/JSClass/FusionCharts.js"></script>
		<script type="text/javascript"
			src="<%=path%>/static/js/zoneClass/zoneClassAnalysticSecondRT.js"></script>
	</body>
</html>
