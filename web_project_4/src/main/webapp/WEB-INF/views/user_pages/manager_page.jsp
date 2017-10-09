<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 Manager page.
	<p>
	<form method = "POST" action = "./rest/tourList">
	  	<input type="submit" value = "Go to Tour List">
	</form>
	<p>
	<form method = "POST" action = "./rest/discount">
		Set discount for regular users:
		<p>
		<select name="discount">
			<option value="0.9">10 %</option>
 			<option value="0.95">5 %</option>
 			<option selected value="1.0">0 %</option>
		</select>
  		<input type="submit" value = "Set discount">
  	</form>	
  	
	<form method = "POST" action = "./rest/logout">
  		<input type="submit" value = "Logout">
  	</form>	
  	<p>
</body>
</html>