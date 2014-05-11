<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="org.displaytag.decorator.TableDecorator"%>
<%@page import="com.cgtest.registration.model.Record"%>
<%
String path = request.getContextPath();
%>

<html>
	<head>
		<link rel="stylesheet"
			href="<%=path%>/common/themes/mac/jquery-ui-1.8.18.custom.css"></link>
		<script type="text/javascript">
			var path = "<%=path%>";
		</script>
		<title>资产统计页面</title>
	</head>
	<body class="unieap aclome" style="overflow: auto">
		<fieldset id="resAllocationFieldSet">
			<legend>
				底片分布
			</legend>
			<div id="hostAllocationChartContainer"></div>
		</fieldset>
				<fieldset id="resAllocationFieldSet">
			<legend>
				责任区分析
			</legend>
			<div id="zoneClsss"></div>
			<div id="zoneClsssChartContainer"></div>
		</fieldset>
		
		<display:table name="test">
		  <display:column property="id" title="ID" />
		  <display:column property="name" />
		  <display:column property="email" />
		  <display:column property="status" />
		  <display:column property="description" title="Comments"/>
		</display:table>
		
	<script type="text/javascript"
		src="<%=path%>/common/lib/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" 
		src="<%=path%>/common/lib/jquery/jquery-ui-1.8.14.js"></script>
	<script type="text/javascript"
		src="<%=path%>/common/lib/jquery/jquery.mtz.monthpicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/common/lib/fusionCharts/xt/FusionCharts.js"></script>
	<script type="text/javascript" 
		src="<%=path%>/static/js/analytic.js"></script>
		</body>
</html>
