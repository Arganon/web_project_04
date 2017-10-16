<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
table, tr, td
{
border:2px solid black;
padding: 3px;
background: #efedea;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>
	Your tours:
	<p>
	 	<table>
			<tr>
				<td>ID</td> <td>Country name</td>  <td>City name</td> 
				<td>Tour Duration</td> <td>Tour state</td>  <td>Tour type</td> <td>Accommodation</td>
				<!-- <td>Price per Night</td> --> <td>Transport Type</td><td>Price</td>
			</tr>
			<c:forEach items="${userTourList}" var="tour" >
				<tr>
					<td align="center">${tour.id}</td>
					<td>${tour.country}</td>
					<td>${tour.city}</td>
					<td align="center">${tour.tourDuration}</td>
					<td>${tour.tourState}</td>
					<td>${tour.tourType}</td>
				    <td>${tour.accommodation.accommodationName}</td>
					<td>${tour.transport.generalTransportType}</td>
					<td align="center">${tour.price}</td>
				</tr>
			</c:forEach>
		</table>
		<p>
		<c:set value="${error}" var="er"></c:set>
			${er}
		<br>
		<form method = "POST" action = "./rest/tourList">
			<input type="submit" value = "Go to tour list">
		</form>
		<p>
		<form method = "POST" action = "./rest/usersPageRedirect">
			<input type="submit" value = "Go to user page">
		</form>
		<p>
		<form method = "POST" action = "./rest/logout">
			<input type="submit" value = "Logout">
		</form>
		
</body>
</html>