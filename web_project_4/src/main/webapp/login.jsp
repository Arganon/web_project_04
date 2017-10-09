<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>
<body>
<br>

<form method="POST" action = "./rest/login">
	<table>
		<tr>
			<td>Login </td>
			<td>
				<input type = "text" name = "login"/>
			</td>
		</tr>
		
		<tr>
			<td>Password </td>
			<td>
				<input type = "password" name = "password"/>
			</td>
		</tr>

		<tr>
			<td>
				<input type="submit" value = "Submit">
			</td>
		</tr>
	</table>
</form>

<c:set value = "${contextPath}" var = "path"></c:set>
<a href = "<c:url value = "${path}/registration.jsp"></c:url>">Registration</a>
</body>
</html>