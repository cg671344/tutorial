<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<html>
<body>
登录！！！

<c:forEach items="${users}" var="user">
<div><c:out value='${user.username}'></c:out></div>
	
</c:forEach>
</body>
</html>