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

	<form method = "POST" action="./rest/changeTourState">
 		<table>
			<tr>
				<td>ID</td> <td>Country name</td>  <td>City name</td> 
				<td>Tour Duration</td> <td>Tour state</td>  <td>Tour type</td> <td>Accommodation</td>
				<td>Transport Type</td><td>Price</td> <td>Choose</td>
			</tr>
			<c:forEach items="${tourList}" var="tour" >
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
					<td>
						<select name="${tour.id}">
							<option value="HOT">Hot</option>
	    						<option value="REGULAR">Regular</option>
	    						<option selected value="STAY">Stay</option>
						</select>
					</td>
				</tr>			
			</c:forEach>		
			<c:set var="tours" value="${tourList}" scope="session"/>
		</table>
			<p>
			<input type="submit" value = "Make Changes">
		</form>
	<p>
	<form method = "POST" action="./rest/usersPageRedirect">
		<input type="submit" value = "Go to user page">
	</form>
	
</body>
</html>