<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	User page.
	<p>
	<form method = "POST" action = "./rest/tourList">
	  	<input type="submit" value = "Go to Tour List">
	</form>
	<p>
	<form method = "POST" action="./rest/usersTours">
		<input type="submit" value = "Go to Users Tours">
	</form>
	<p>
  	<form method = "POST" action = "./rest/logout">
  		<input type="submit" value = "Logout">
  	</form>
  		
</body>
</html>