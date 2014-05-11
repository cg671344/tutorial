<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<html>
<%
	String path = request.getContextPath();
%>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/login/css/lanrenzhijia.css" media="all"/>
</head>
<body style="overflow-x:hidden">
	<div class="theme-popbod dform">
		<form class="theme-signin" name="loginform" action="" method="post">
			<ol>
				<li>
					<strong>底片数：</strong>
					<input class="ipt" type="text" style="text-align:right;width:120px" readonly id="oldPassword" value="${pianziNumberTotal}" size="20" />
				</li>
				<li>
					<strong>不合格数：</strong>
					<input class="ipt" type="text" style="text-align:right;width:120px" readonly  id="newPassword" value="${fanxiuNumberTotal}" size="20" />
				</li>
				<li>
					<strong>合格率：</strong>
					<input class="ipt" type="text" style="text-align:right;width:120px"  readonly id="repeatedPassword" value="${rate}" size="20" />%
				</li>
			</ol>
		</form>
	</div>
	<script type="text/javascript" src="<%=path%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
	<script src="<%=path%>/static/js/common/common.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/user/userInforMaintain.js"></script>
</body>
</html>
