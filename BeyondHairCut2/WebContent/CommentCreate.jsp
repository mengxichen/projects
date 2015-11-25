

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment on Barbers!</title>
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
<h1>Create Comment for Barber</h1>
	<form action="commentcreate" method="post">
		<p>
			<label for="customerid">MyId</label>
			<input id="customerid" name="customerid" value="">
		</p>
		<p>
			<label for="barberId">BarberId</label>
			<input id="barberId" name="barberId" value="">
		</p>
		<p>
			<label for="content">Comment: </label>
			<input id="content" name="content" value="">
		</p>
		
		<p>
			<input type="submit">
		</p>
	</form>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Go Back"></p>
</body>
</html>