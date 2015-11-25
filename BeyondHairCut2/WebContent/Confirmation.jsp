

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thanks for making an appointment with Beyond Hair Cut</title>
<style>
body {

    background-color;
    background-repeat: no-repeat;
    background-position: left top;
    margin-top: 100px;
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
	<h1>
		<font color="grey" face="Comic sans MS" size="5"> Thanks
			${fn:escapeXml(param.username)} for making an appointment with Beyond
			HairCut</font>
	</h1>
	<h2>
		<font color="grey" face="Comic sans MS" size="5"> We look
			forward to see you in our Barbershop!</font>
	</h2>
	<br />
	<br />
	<a href="Homepage.jsp?username=${fn:escapeXml(param.username)}">HOME</a>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	

</body>
</html>