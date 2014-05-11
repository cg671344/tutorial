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
	<body style="overflow:auto; height:90%; width:100%">
		<div class="table_main" align="left">
			<display:table id="listrecord" name="rs.results" export="false"
				pagesize="${rs.pagesize}" requestURI="/hankouTable.do" sort="list"
				uid="element" style="true" partialList="true" size="rs.total"
				decorator="com.displaytag.myDisplaytag.tableDecrator.TableWitdhDecrator"
				defaultsort="8" defaultorder="ascending"
				excludedParams="_chk" class="data_grid">
				<display:column title="焊工号" property="hangongNo" sortable="true"
					headerClass="w4%" />
				<display:column title="制造号" property="zhizaoNo" sortable="true"
					maxLength="8" headerClass="w5%" />
				<display:column title="焊口编号" property="hankouNo" sortable="true"
					headerClass="w4%" />
				<display:column title="等轴图号" property="chartNo" sortable="true"
					headerClass="w6%" maxLength="10"/>
				<display:column title="报告编号" property="reportNo" sortable="true"
					headerClass="w4%" />
				<display:column title="片子数量" property="pianziNumber" sortable="true"
					headerClass="w4%" />
				<display:column title="返修数量" property="fanxiuNumber" sortable="true"
					headerClass="w4%" />
				<display:column title="检验日期" property="jianyanDate" sortable="true" 
					maxLength="8" headerClass="w5%" />	
				<display:column title="检验结果" property="jianyanResult"
					sortable="true" headerClass="w4%" />
				<display:column title="规格1" property="guige1" sortable="true"
					maxLength="6" headerClass="w3%" />
				<display:column title="规格2" property="guige2" sortable="true"
					maxLength="6" headerClass="w3%" />
				<display:column title="RCCM级别" property="rccmLevel" sortable="true"
					headerClass="w5%" />
				<display:column title="焊缝类型" property="hanfengType" sortable="true"
					headerClass="w4%" />
				<display:column title="委托单位" property="weituoUnit" sortable="true"
					headerClass="w4%" />
				<!--title="区域" property="zone" sortable="true"
				title="大区域分类" property="zoneClass" sortable="true" -->
			</display:table>
		</div>
		<script type="text/javascript"
			src="<%=contextPath%>/static/js/common/common.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/static/js/common/displaytag.js"></script>
	</body>
</html>
