<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.text.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" href="css/lanrenzhijia.css" media="all"/>
<script src="js/jquery.min.js"></script>
<script src="js/login.js"></script>
</head>
<body>
		<div id="mask">
			<div class="theme-buy">
				<br />
				<a class="btn btn-primary btn-large theme-login" href="javascript:;">登录</a>
			</div>
			<div id="loginDiv" class="theme-popover">
				<div class="theme-poptit">
					<a href="javascript:;" title="关闭" onclick="cancelLoginDiv()"
						class="close">×</a>
					<h3>
						请输入用户名和密码
					</h3>
				</div>
				<div class="theme-popbod dform">
					<form class="theme-signin" name="loginform" action="" method="post">
						<ol>
							<li>
								<strong>用户名：</strong>
								<input class="ipt" type="text" name="log" value="" size="20" />
							</li>
							<li>
								<strong>密码：</strong>
								<input class="ipt" type="password" name="pwd" value="" size="20" />
							</li>
							<li>
								<input class="btn btn-primary" type="submit" name="submit"
									value=" 登 录 " />
								<input class="btn btn-primary" type="button" name="register"
									onclick="showRegister()" value=" 注 册 " />
							</li>
						</ol>
					</form>
				</div>
			</div>
			<div id="registerDiv" class="registerDiv_theme-popover">
				<div class="theme-poptit">
					<a href="javascript:;" title="关闭" onclick="cancelRegisterDiv()"
						class="close">×</a>
					<h3>
						请输入用户资料
					</h3>
				</div>
				<div class="theme-popbod dform">
					<form action="<%=request.getContextPath()%>/register.html"
						method="post" class="theme-signin" name="loginform">
						<ol>
							<li>
								<strong>用户名：</strong>
								<input class="ipt" type="text" name="username" value=""
									size="20" />
							</li>
							<li>
								<strong>密码：</strong>
								<input class="ipt" type="password" name="password" value=""
									size="20" />
							</li>
							<li>
								<strong>密码：</strong>
								<input class="ipt" type="password" name="password" value=""
									size="20" />
							</li>
							<li>
								<input class="btn btn-primary" type="submit" name="submit"
									value=" 确定 " />
								<input class="btn btn-primary" type="button" name="register"
									onclick="cancelRegisterDiv()" value=" 取消 " />
							</li>
						</ol>
					</form>
				</div>
			</div>
			<div class="theme-popover-mask"></div>
		</div>
	</body>
</html>
