<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="com.cgtest.biz.pojo.MsColumnData"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String hangongNo = request.getParameter("hangongNo");
	String hangongName = request.getParameter("hangongName");
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
			var globalQueryStartDate = '<%=startDate%>';
			var globalQueryEndDate = '<%=endDate%>';
			var globalQueryHangongNo =  '<%=hangongNo%>';
			var globalQueryHangongName =  '<%=hangongName%>';
		</script>
		<title>资产统计页面</title>
	</head>
	<body style="overflow: auto">
		<div class="table_main" id="hangongTableContainer" align="left">
			<div style="padding: 10px 0px 5px; font-size: 12px;">	 
				<div id="hangongQueryContainer" style="float:left">		
					<label for="hangongNo">
						焊工号：
					</label>
					<input id="hangongNo" type="text" value=""/>
					<label for="hangongName">
						焊工姓名：
					</label>
					<input id="hangongName" type="text" value=""/>
					<label for="startDate">
						起始日期：
					</label>
					<input id="startDate" type="text" readonly value="${startDate}" onClick="WdatePicker()"/>
					<img onclick="WdatePicker({el:'startDate'})"
						src="common/lib/My97DatePicker/skin/datePicker.gif"
						width="12" height="22"
						style="cursor: pointer; vertical-align: middle;" />
					<label for="endDate" style="padding-left:10px">
						结束日期：
					</label>
					<input id="endDate" type="text" value="${endDate}" readonly onClick="WdatePicker()"/>
					<img onclick="WdatePicker({el:'endDate'})"
						src="common/lib/My97DatePicker/skin/datePicker.gif"
						width="12" height="22"
						style="cursor: pointer; vertical-align: middle;" />
				</div>	
				
				<div id="chartNoQueryContainer" style="float:left;display:none">
					<label for="chartNo">
						等轴图号：
					</label>
					<input id="chartNo" type="text"  value=""/>
					<label for="hankouNo">
						焊口号：
					</label>
					<input id="hankouNo" type="text"   value=""/>
				</div>
				<div style="float: left">
					<input type="button" value="查询" onclick="query()" />
				</div>
			</div>
			<div align="left">
				<div style="padding-top:10px">
					<display:table id="hangongTable" name="rs.results" defaultsort="6" defaultorder="ascending"
						requestURI="/hangongAnalystic.do" sort="page" export="false" >
						<display:column property="dataType" title="焊工号" sortable="true" />
						<display:column property="hangongName" title="姓名" sortable="true" />
						<display:column property="hangkouNumber" title="焊口数" sortable="true" />
						<display:column property="unqualifiedhangkouNumber" title="不合格焊口数" sortable="true" />
						<display:column property="dipianNumber" title="底片数"  sortable="true" />
						<display:column property="unqualifiedPianziNum" title="不合格底片数"  sortable="true" />
						<display:column property="unqualifiedDistributeRate"
							title="不合格分布"  sortable="true" 
							decorator="com.displaytag.myDisplaytag.tableDecrator.PercentColumnDecrator" />
						<display:column property="zoneQualifiedRate" title="焊工合格率"  sortable="true" 
							decorator="com.displaytag.myDisplaytag.tableDecrator.PercentColumnDecrator" />
						<display:column title="详细">
							<a
								onclick="hiddenHangongTable('<%=((MsColumnData) pageContext
									.getAttribute("hangongTable"))
									.getDataType()%>')"
								style="cursor: pointer;">详细</a>
						</display:column>
					</display:table>
				</div>
			</div>
		</div>
		<div id="hankouIframeContainer" style="display:none;height:600px; width:100%;padding-top:10px">
			<input type="button" value="返回" style="float:right;margin-right:20px" onclick="showHangongTable()" />
			<iframe id="hankouIframe" src="<%=path%>/hankouTable.do" style="height:100%; width:100%;border:0"></iframe>
		</div>
		<script type="text/javascript"
			src="<%=path%>/common/lib/jquery/jquery-1.5.1.js"></script>
		<script type="text/javascript"
			src="<%=path%>/common/lib/jquery/jquery-ui-1.8.14.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/common/lib/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=path%>/common/lib/fusionCharts/xt/FusionCharts.js"></script>
		<script type="text/javascript"
			src="<%=path%>/static/js/hangong/hangongAnalystic.js"></script>
	</body>
</html>
