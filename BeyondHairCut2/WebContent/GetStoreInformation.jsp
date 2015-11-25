<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get store information for a storeId</title>
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
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>Country</th>
            </tr>
                <tr>
                    <td><c:out value="${address.getStreet()}" /></td>
                    <td><c:out value="${address.getCity()}" /></td>
                    <td><c:out value="${address.getState()}" /></td>
                    <td><c:out value="${address.getZip()}" /></td>
                    <td><c:out value="${address.getCountry()}" /></td>
                </tr>
       </table>
	<p align="left"><a href="GetAppointmentHistory.jsp"><button type="button">Back</button></a></p>
</body>
</html>