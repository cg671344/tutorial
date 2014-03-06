<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<body>
<form action="<%=request.getContextPath()%>/register.html" method="post">
	username *<input name="username" id="username" type="text"/>
	<br><br>
	password *<input name="password" id="password" type="password"/>
	<br>
	<br>
	actual name <input name="name" id="name" type="text"/>
	<br>
	<input type="submit" id="submit"/ value="register">
</form>
</html>