<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
<a href = "login.jsp">Sign in</a><br>
<p>Registration form:</p>
<form method="POST" action="./rest/registration" >
	<table>
		<tr>
			<td>Login </td>
			<td>
				<input type = "text" name = "login"/>
			</td>
			<td>
				<c:set value="${loginEr}" var="lgnEr"></c:set>
				${lgnEr}
			</td>
		</tr>

		<tr>
			<td>Password </td>
			<td>
				<input type = "password" name = "password"/>
			</td>
			<td>
				<c:set value="${passwordEr}" var="pswrdEr"></c:set>
				${pswrdEr}
			</td>
		</tr>

		<tr>
			<td>Repeat password </td>
			<td>
				<input type = "password" name = "repeatPassword"/>
			</td>
			<td>
				<c:set value="${repeatPasswordEr}" var="rptPswrdEr"></c:set>
				${rptPswrdEr}
			</td>
			
		</tr>

		<tr>
			<td>First name </td>
			<td>
				<input type = "text" name = "firstName"/>
			</td>
			<td>
				<c:set value="${firstNameEr}" var="frstNmEr"></c:set>
				${frstNmEr}
			</td>
		</tr>

		<tr>
			<td>Middle name </td>
			<td>
				<input type = "text" name = "middleName"/>
			</td>
			<td>
				<c:set value="${middleNameEr}" var="mdlNmEr"></c:set>
				${mdlNmEr}
			</td>
		</tr>
		
		<tr>
			<td>Last name </td>
			<td>
				<input type = "text" name = "lastName"/>
			</td>
			<td>
				<c:set value="${lastNameEr}" var="lstNmEr"></c:set>
				${lstNmEr}
			</td>
			
		</tr>

		<tr>
			<td>Email </td>
			<td>
				<input type = "text" name = "email"/>
			</td>
			<td>
				<c:set value="${emailEr}" var="emlNmEr"></c:set>
				${emlNmEr}
			</td>
		</tr>

		<tr>
			<td>Telephone </td>
			<td>
				<input type = "text" name = "telephone"/>
			</td>
			<td>
				<c:set value="${telephoneEr}" var="tlphnNmEr"></c:set>
				${tlphnNmEr}
			</td>
		</tr>

		<tr>
			<td>
				<br><input type="submit" value = "Submit">
			</td>
		</tr>
	</table>
</form>
</body>
</html>