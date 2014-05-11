<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="com.cgtest.biz.pojo.MonthData"%>
<%@page import="com.cgtest.biz.pojo.MonthCumulativeData"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%
	//request数据转换成json数据->chart绘图使用
	List<MonthData> list = (List<MonthData>) request.getAttribute("monthDataList");
	JSONArray weekjsonData = JSONArray.fromObject(list);
	String weekJsonDataString = weekjsonData.toString();
	
	List<MonthCumulativeData> sumlist = (List<MonthCumulativeData>) request.getAttribute("monthSumDataList");
	JSONArray weekSumjsonData = JSONArray.fromObject(sumlist);
	String weekSumJsonDataString = weekSumjsonData.toString();
	
	String weekAlertLimit = (String)request.getAttribute("weekAlertLimit");
	Integer year = (Integer)request.getAttribute("year");
%>
<html>
	<head>
		<link href="<%=contextPath%>/static/css/default/common/content.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=contextPath%>/static/css/default/common/Global.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet"
			href="<%=contextPath%>/common/themes/mac/jquery-ui-1.8.18.custom.css"></link>
		<script type="text/javascript">
			var path = "<%=contextPath%>";
			var weekJsonDataString = eval('(' + '<%=weekJsonDataString%>' +')');
			var weekSumJsonDataString = eval('(' + '<%=weekSumJsonDataString%>' +')');
			var weekAlertLimit = '<%=weekAlertLimit%>';
			var year = '<%=year%>';
		</script>
		<title>资产统计页面</title>
	</head>
	<body style="overflow: auto"><br />
		<div class="table_main" align="left">
			<div style="padding-top:10px">
				<label>年份：</label>
				<select id="yearSelect" style="width:200px" value="${year}">
					<c:forEach begin="2009" end="2050" step="1" var="i">
						<c:choose>
							<c:when test="${i == year}">
								<option selected>
									${i}
								</option>
							</c:when>
							<c:otherwise>
								<option>
									${i}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<input value="查询" type="button" onclick="query()"/>  
			</div>
			<div align="left" style="width:100%">
				<fieldset id="resAllocationFieldSet">
					<legend>
						数据分析
					</legend>
					<div id="chartContainer" align="center" style="width:100%"></div>
				</fieldset>
			</div>
			<div align="left" style="padding-top:10px">
				<fieldset id="sourceData">
					<legend>
						数据来源
					</legend>
					<div class="table_main" align="left">
						<display:table id="analystic" name="monthDataList"
							defaultorder="ascending" > 
							<display:column property="month" title="月份" group="1"/>
							<display:column property="startDate" title="起始时间"/>
							<display:column property="endDate"	title="终止时间"/>
							<display:column property="dipianNumber" title="底片总数"/>
							<display:column property="fanxiuNumber"	title="返修总数"/>
							<display:column property="qulifiedRate" title="月一次合格率"
								decorator="com.displaytag.myDisplaytag.tableDecrator.PercentColumnDecrator" />
						</display:table>
					</div>
				</fieldset>
			</div>
		</div>
		<script type="text/javascript"
			src="<%=contextPath%>/common/lib/jquery/jquery-1.5.1.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/common/lib/fusionChartsFree/JSClass/FusionCharts.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/static/js/month/monthAnalystic.js"></script>
	</body>
</html>
