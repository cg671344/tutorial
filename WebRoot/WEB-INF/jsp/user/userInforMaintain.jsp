<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
					<strong>原密码：</strong>
					<input class="ipt" type="password" id="oldPassword" value="" size="20" />
				</li>
				<li>
					<strong>新密码：</strong>
					<input class="ipt" type="password" id="newPassword" value="" size="20" />
				</li>
				<li>
					<strong>重复密码：</strong>
					<input class="ipt" type="password" id="repeatedPassword" value="" size="20" />
				</li>
				<li>
					<div style="padding-left:10px">
						<input class="btn btn-primary"  onclick="password()" value="修 改" />
					</div>
				</li>
			</ol>
		</form>
	</div>
	<script type="text/javascript" src="<%=path%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/common/common.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/user/userInforMaintain.js"></script>
</body>
</html>
