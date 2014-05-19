<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="org.displaytag.decorator.TableDecorator"%>
<%@page import="com.cgtest.registration.model.Record"%>
<%
	String chartNo = request.getParameter("chartNo");
	String hankouNo = request.getParameter("hankouNo");
%>
<html>
	<head>
		<link
			href="<%=contextPath%>/static/css/default/common/content.css"
			rel="stylesheet" type="text/css" />
		<link
			href="<%=contextPath%>/static/css/default/common/Global.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var globalQuerychartNo = '<%=chartNo%>';
			var globalQueryhankouNo = '<%=hankouNo%>';
		</script>
		<jsp:scriptlet>
         request.setAttribute("dyndecorator", new TableDecorator()
    	 {
            public String addRowClass()
            {
                return ((Record)getCurrentRowObject()).getPianziNumber() > 0 ? "good" : "bad";
            }
			public String addRowId()
			{
			return "myrow" + evaluate("id");
			}
        });
       	org.displaytag.decorator.CheckboxTableDecorator decorator = 
       				new org.displaytag.decorator.CheckboxTableDecorator();
        decorator.setId("id");
        decorator.setFieldName("_chk");
        pageContext.setAttribute("dyndecorator", decorator);
        </jsp:scriptlet>
	</head>
<body>
		<div id="recordContainer" class="table_main" align="left"
			style="padding-top: 10px">
			<div style="padding: 5px; font-size: 12px;">
				<label for="chartNo">
					等轴图号：
				</label>
				<input id="chartNo" type="text" value="${chartNo}" />
				<label for="hankouNo" style="padding-left:10px">
					焊口号：
				</label>
				<input id="hankouNo" type="text" value="${hankouNo}"/>
				<input type="button" value="查询" onclick="query()" />
			</div>
			<input type="button" value="删除记录"
				onclick="deleteChecked('<%=contextPath%>/hangongChartNoQueryForAdmin.do?id=')" />
			<display:table id="listrecord" name="rs.results" export="false"
				pagesize="${rs.pagesize}" requestURI="/hangongChartNoQueryForAdmin.do" sort="list"
				uid="element" style="true" partialList="true" size="rs.total"
				decorator="com.displaytag.myDisplaytag.tableDecrator.TableWitdhDecrator"
				excludedParams="_chk" class="data_grid">
				<display:column headerClass="w2%">
					<input name="_chk" type="checkbox" value="${element.id}" />
				</display:column>
				<display:column title="制造号" sortable="true" headerClass="w4%"
					maxLength="8"><a style="cursor: pointer;" 
						onclick="editZone('${element.id}','${element.zone}','${element.zoneClass}')">${element.zhizaoNo}</a></display:column>
				<display:column title="焊口编号" property="hankouNo" sortable="true"
					headerClass="w4%" />
				<display:column title="等轴图号" property="chartNo" sortable="true"
					headerClass="w7%" maxLength="14" />
				<display:column title="报告编号" property="reportNo" sortable="true"
					headerClass="w4%" maxLength="8" />
				<display:column title="片子数" property="pianziNumber" sortable="true"
					headerClass="w3%" />
				<display:column title="返修数" property="fanxiuNumber" sortable="true"
					headerClass="w3%" />
				<display:column title="检验日期" property="jianyanDate" sortable="true"
					maxLength="8" headerClass="w4%" />
				<display:column title="结果" property="jianyanResult" sortable="true"
					headerClass="w3%" />
				<display:column title="规格1" property="guige1" sortable="true"
					maxLength="6" headerClass="w3%" />
				<display:column title="规格2" property="guige2" sortable="true"
					maxLength="6" headerClass="w3%" />
				<display:column title="RCCM级别" property="rccmLevel" sortable="true"
					headerClass="w5%" />
				<display:column title="焊工号" property="hangongNo" sortable="true"
					headerClass="w3%" />
				<display:column title="焊缝类型" property="hanfengType" sortable="true"
					headerClass="w4%" />
				<display:column title="委托单位" property="weituoUnit" sortable="true"
					headerClass="w4%" />
				<display:column title="细化区域" property="zone" sortable="true"
					headerClass="w4%" />
				<display:column title="责任区" property="zoneClass" sortable="true"
					headerClass="w3%" />
			</display:table>
		</div>
		<div id="zoneContainer" class="table_main" style="display:none;padding:10px 0px 0px 10px">
			<input id="recordId" type="hidden" value="" />
			<table border=0>
				<col align="left" width="100" />
				<col align="left" />
				<tr>
					<td style="font-size: 12px;height:20px">
						细化区域：
					<br /></td>
					<td>
						<input class="ipt" type="text" 
							style="width: 150px; text-align: right" id="zone"
							value="" size="10" />
					<br /></td>
				</tr>
				<tr>
					<td style="font-size: 12px;height:20px">
						责任区：
					<br /></td>
					<td>
						<input class="ipt" type="text"
							style="width: 150px; text-align: right" id="zoneClass"
							value="" size="10" />
					<br /></td>
				<tr>
				<tr align="center" style="font-size: 12px;height:20px">
					<td colspan="2">
						<input type="button" onclick="saveZone()" value="修改" />
						<input type="button" onclick="returnToList()" value="返回" />
					<br /></td>
				</tr>
			</table>
		</div>
		<script type="text/javascript"
			src="<%=contextPath%>/static/js/common/common.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/static/js/common/displaytag.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/hangong/hangongChartNoQueryForAdmin.js"></script> 
	</body>
</html>