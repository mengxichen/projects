
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top Hair Styles</title>
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
body {background-color:pink}
</style>
</head>
<body>
<form action="barbers" method="post"></form>
	<table border="2">
		<tr>
			<th>Hair Style</th>
			<th>Gender</th>
			<th>Picture</th>
			<th>Price</th>
			<th>Estimate Time</th>
		</tr>
		<c:forEach items="${hairstyles}" var="hairstyle">
			<tr>
				<td><c:out value="${hairstyle.getStyle()}" /></td>
				<td><c:out value="${hairstyle.getGender()}" /></td>
				<td><c:out value="${hairstyle.getPicture()}" /></td>
				<td><c:out value="${hairstyle.getPrice()}" /></td>
				<td><c:out value="${hairstyle.getDuration()}" /></td>

			</tr>
		</c:forEach>
	</table>
<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Return"></p>

</body>
</html>