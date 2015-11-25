<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Barbers</title>

<style>
body {background-color;
	background-repeat: no-repeat;
	background-position: left top;
	margin-top: 20px;
	margin-left: 20px;
	margin-right: 20px;
}

p {
	word-spacing: 5px;
}

body {
	background-color: lightblue
}
</style>
</head>
<body>
<p align="right"><a href="Homepage.jsp"><font color="grey" face="Comic sans MS"
	size="3"> HOME </font></a></p>
<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Return"></p>
<h1><font color="darkblue" face="Comic sans MS" size="8">
Here are our Barbers!</font>
</h1>
<p>
<img src="barbers.jpg" width="600" height = "550" align="left">
</p>	
	<form action="barbers" method="post"></form>
	<table border="2">
		<tr>
			<th>No.</th>
			<th>FirstName</th>
			<th>About</th>
			<th>Rating</th>
			<th>Choose me</th>
			<th>Comments from Customers</th>
		</tr>
		<c:forEach items="${barbers}" var="barber">
			<tr>
				<td><c:out value="${barber.getBarberId()}" /></td>
				<td><c:out value="${barber.getFirstName()}" /></td>
				<td><c:out value="${barber.getAbout()}" /></td>
				<td><c:out value="${barber.getRating()}" /></td>
				<td>Choose <a href="AppointmentCreate.jsp?username=${fn:escapeXml(param.username)}"><c:out
							value="${barber.getFirstName()}" /> </a> </td>
				<td>For<a href="GetComments.jsp?barberId=${barber.getBarberId()}">
				 ${barber.getFirstName()}</a></td>
						

			</tr>
		</c:forEach>
	</table>


</body>
</html>