<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to myPortal!</title>
<style>
body {
    background-color;
    background-repeat: no-repeat;
    background-position: left top;
    margin-top: 0px;
    margin-left: 20px;
    margin-right: 20px;
}

body {background-color:lightblue}
</style>
</head>
<body>
	<h1><font color="darkblue" face="Comic sans MS" size="8">My Portal</font></h1>
	<p align="center"><font color="darkblue" face="Comic sans MS" size="5">
	 Hello ${fn:escapeXml(param.username)}, Welcome back!!</font></p>
	<p align="right"><a href="Homepage.jsp"><button type="button">Sign Out</button></a></p>
	<p>Make An Appointment</p>
	<a href="AppointmentCreate.jsp?username=${fn:escapeXml(param.username)}">
		<button type="button">New Appointment</button>
	</a>

	<br></br>
	<p>Appointment History</p>
	<a href="GetAppointmentHistory.jsp?username=${fn:escapeXml(param.username)}">
	<button type="button">Appointments History</button></a>
<p><font color="darkblue" face="Comic sans MS" size="4">Check out our 
<a href="barbers?username=${fn:escapeXml(param.username)}">Top Barbers</a></font></p>
<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Go Back"></p>
</body>
</html>
