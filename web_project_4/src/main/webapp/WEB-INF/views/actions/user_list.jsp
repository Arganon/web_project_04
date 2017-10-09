<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<style>
table, tr, td
{
border:2px solid black;
padding: 3px;
background: #efedea;
}

</style>

</head>

</head>
<body>
 	<table>
			<tr>
				<td>ID</td> <td>First Name</td>  <td>Middle Name</td> 
				<td>Last Name</td> <td>Email</td>  <td>Login</td> <td>User role</td>
				<td>Change role</td> <td>Delete user</td>
			</tr>
			<c:forEach items="${userList}" var="user" >
				<tr>
					<td>${user.id}</td>
					<td>${user.firstName}</td>
					<td>${user.middleName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.login}</td>
					<td>${user.userRole}</td>
					<td> </td>
					<td> </td>
				</tr>
			</c:forEach>
			
	</table>
</body>
</html>