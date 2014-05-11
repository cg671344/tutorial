<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String contextPath = request.getContextPath();
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
			var globalQueryStartDate = '<%=startDate%>';
			alert(globalQueryStartDate);
			var globalQueryEndDate = '<%=endDate%>';
			$(document).ready(function(){
				if(globalQueryStartDate != null && globalQueryEndDate != null){
					debugger;
					$("#startDate").val(globalQueryStartDate);
					$("#endDate").val(globalQueryEndDate);
				}
			     $('a.click').click(function(){ 
			          $('embed').remove(); 
			          $('body').append('<embed src="' + contextPath+'/static/audio/4031.wav" autostart="true" hidden="true" loop="loop"/>');
			     });
			      
			    $('a.hover').mouseover(function(){   
			          $('embed').remove(); 
			          $('body').append('<embed src="' + contextPath+'/static/audio/4031.wav" autostart="true" hidden="true" loop="10"/>');  
			     }); 
			});
		</script>
	</head>
	<body>
		<p><a href="#" class="click">爬到点击后发声提示</a></p>
        <p><a href="#" class="hover">鼠标在上面发声提示</a></p>
		<input type="button" value="查询" onclick="cancel()" />
	</body>
</html>