<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Make An Appointment</title>

<!--Requirement jQuery-->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<!--Load Script and Stylesheet -->
<script type="text/javascript" src="jquery.simple-dtpicker.js"></script>
<link type="text/css" href="jquery.simple-dtpicker.css" rel="stylesheet" />
<!---->

<style type="text/css">
body {
	background-color: #fefefe;
	padding-left: 2%;
	padding-bottom: 100px;
	color: #101010;
}

footer {
	font-size: small;
	position: fixed;
	right: 5px;
	bottom: 5px;
}

a:link, a:visited {
	color: #0000ee;
}

pre {
	background-color: #eeeeee;
	margin-left: 1%;
	margin-right: 2%;
	padding: 2% 2% 2% 5%;
}

p {
	font-size: 0.9rem;
}

ul {
	font-size: 0.9rem;
}

hr {
	border: 2px solid #eeeeee;
	margin: 2% 0% 2% -3%;
}

h3 {
	border-bottom: 2px solid #eeeeee;
	margin: 2rem 0 2rem -1%;
	padding-left: 1%;
	padding-bottom: 0.1em;
}

h4 {
	border-bottom: 1px solid #eeeeee;
	margin-top: 2rem;
	margin-left: -1%;
	padding-left: 1%;
	padding-bottom: 0.1em;
}
h5 {
	border-bottom: 1px solid #eeeeee;
	margin-top: 2rem;
	margin-left: -1%;
	padding-left: 1%;
	padding-bottom: 0.1em;
}



body {
	background-color: lightblue
}
</style>

</head>
<body>
<a href="Homepage.jsp"><font color="grey" face="Comic sans MS"
	size="3"> HOME </font></a>
	<h1>Make An Appointment</h1>
	<p><font color="red" face="Comic sans MS"
	size="3">
		<span id="successMessage"><b>${messages.success}</b></span>
	</font></p>
	<form action="appointmentcreate" method="post">
		<p>
			<label for="userName">UserName</label> <input id="userName"
				name="userName" value="${fn:escapeXml(param.username)}"
				placeholder="username" required>
		</p>

		<!-- DATEPicker and Timepicker -->
		<label for="datetime">Date and Time</label> <input input id="datetime"
			type="text" name="datetime" value="">

		<script type="text/javascript">
			$(function() {
				$('*[name=datetime]').appendDtpicker();
			});
		</script>
		
		<!-- pick a barber -->
		<p>
			<label for="barberid">Which Barber? </label> <input id="barberid"
				name="barberid" value="" placeholder="barber's ID" required>
			<a href="barbers?username=${fn:escapeXml(param.username)}"><button
					type="button">Check out all the barbers!</button></a>

		</p>
		
		<!-- pick a location -->
		<h2>Select the barber store location:</h2>
		<label><input type="radio" value="1" name="storeid">
			Seattle</label> <label><input type="radio" value="2"
			name="storeid"> Kirkland</label> <label><input
			type="radio" value="3" name="storeid"> Bellevue</label>



		<!--<meta charset="utf-8">  -->
		<!-- choose a hairstyle -->
		<h3>Select your favorite hair style:</h3>
		<h4>Men's hair style:</h4>
		<label><input type="radio" value="Bowl Cut" name="style">
			Bowl Cut</label> <label><input type="radio" value="Butch Cut"
			name="style"> Butch Cut</label> <label><input type="radio"
			value="Buzz Cut" name="style"> Buzz Cut</label> <label><input
			type="radio" value="Caeser Cut" name="style"> Caeser Cut</label> <label><input
			type="radio" value="Comb Over" name="style"> Comb Over</label> <label><input
			type="radio" value="Crew Cut" name="style"> Crew Cut</label> <label><input
			type="radio" value="Duckass" name="style"> Duckass</label> <label><input
			type="radio" value="Flat Top" name="style"> Flat Top</label> <label><input
			type="radio" value="High Top" name="style"> High Top</label> <br>
		<h4>Women's hair style:</h4>
		<label><input type="radio" value="Bob Cut" name="style">
			Bob Cut</label> <label><input type="radio" value="Bouffant"
			name="style"> Bouffant</label> <label><input type="radio"
			value="Long Straight" name="style"> Long Straight</label> <label><input
			type="radio" value="Long Wave" name="style"> Long Wave</label> <label><input
			type="radio" value="Mullet" name="style"> Mullet</label> <label><input
			type="radio" value="Pony Hair" name="style"> Pony Hair</label> <label><input
			type="radio" value="Short Straight" name="style"> Short
			Straight</label> <label><input type="radio" value="Short Wave"
			name="style"> Short Wave</label> <br>

		<p>
			<input type="submit">
		</p>
	</form>
	
	
	<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Return"></p>
	

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="jquery.simple-dtpicker.js"></script>


</body>
</html>