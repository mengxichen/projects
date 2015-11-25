<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Appointment History</title>
<style>
body {
    background-color;
    background-repeat: no-repeat;
    background-position: left top;
    margin-top: 0px;
    margin-left: 20px;
    margin-right: 20px;
}
p { 
    word-spacing: 5px;
}
body {background-color:lightblue}
</style>
</head>
<body>
	<p align="left">
		<a href="Portal.jsp?username=${fn:escapeXml(param.username)}">
			<button type="button">Go Back To My Portal</button>
		</a>
	</p>
	<form action=getappointmenhistory method="get">
		<h1>My Appointment History</h1>
		<p>
			<label for="username">UserName</label> <input id="username"
				name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit"> <br />
			<br />
			<br /> <span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Appointments</h1>
	<table border="1">
		<tr>
			<th>UserName</th>
			<th>StoreId</th>
			<th>Date</th>
			<th>BarberId</th>
			<th>Style</th>
			
		</tr>
		<c:forEach items="${appointments}" var="appointment">
			<tr>
				<td><c:out value="${fn:escapeXml(param.username)}" /></td>

				<td><a
					href="getstoreinformation?storeid=<c:out value="${appointment.getStoreId()}"/>">Store</a></td>
				<td><c:out value="${appointment.getDate()}" /></td>
				<td><a
					href="getbarberinformation?barberid=<c:out value="${appointment.getBarberId()}"/>">Barber</a></td>
				<td><c:out value="${appointment.getStyle()}" /></td>
				

			</tr>
		</c:forEach>
	</table>
</body>
</html>