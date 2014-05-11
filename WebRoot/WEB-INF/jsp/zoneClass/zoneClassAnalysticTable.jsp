<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<html>
	<head>
		<link href="<%=contextPath%>/static/css/default/common/content.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=contextPath%>/static/css/default/common/Global.css"
			rel="stylesheet" type="text/css" />
	</head>
	<body style="overflow:auto; height:100%; width:100%">
		<div class="table_main" align="left" >
			<display:table id="analystic" name="displayDataList" 
				defaultorder="ascending" requestURI="/zoneClassAnalysticTable.do">
				<display:column property="dataType" title="区域" sortable="true"/>
				<display:column property="dipianNumber" title="底片数" sortable="true"/>
				<display:column property="unqualifiedPianziNum" title="不合格数" sortable="true"/>
				<display:column property="unqualifiedDistributeRate" title="不合格分布" sortable="true"
					decorator="com.displaytag.myDisplaytag.tableDecrator.PercentColumnDecrator" />
				<display:column property="zoneQualifiedRate" title="区域合格率" sortable="true"
					decorator="com.displaytag.myDisplaytag.tableDecrator.PercentColumnDecrator" />
			</display:table>
		</div>
	</body>
</html>
