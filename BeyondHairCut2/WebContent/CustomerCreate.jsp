<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a customer</title>

<!--Requirement jQuery-->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	
<!--Load Script and Stylesheet -->
<script type="text/javascript" src="jquery.simple-dtpicker.js"></script>
<link type="text/css" href="jquery.simple-dtpicker.css" rel="stylesheet" />
<!---->

<style>
body {background-color;
	background-repeat: no-repeat;
	background-position: left top;
	margin-top: 0px;
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
<a href="Homepage.jsp"><font color="grey" face="Comic sans MS"
	size="3"> HOME </font></a>
<body>
	<h1>Sign Up</h1>
	<p>Already have an account?
	<a href="SignIn.jsp"><button type="button">Sign in</button></a></p>
	<p style="color: blue">
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<form action="customercreate" method="post">
		<p>
			<label for="userName">UserName</label> <input id="userName"
				name="userName" value="">

			<label for="password">Password</label> <input id="password"
				name="password" value="">
		</p>
		<p>
			<label for="firstName">FirstName</label> <input id="firstName"
				name="firstName" value="">

			<label for="lastName">LastName</label> <input id="lastName"
				name="lastName" value="">
		</p>
		
			<!-- pick a gender -->
		<h4>Gender:</h4>
		<label><input type="checkbox" value="female" name="gender">
			Female</label> <label><input type="checkbox" value="male"
			name="gender"> Male</label> 
		
		<h4>Birthday: </h4>
		<!-- <label for="dob">date of birth</label> -->
		<input type="text" id="dob" name="dob" value="">

<script type="text/javascript">
			$(function(){
				$('*[name=dob]').appendDtpicker({
					"inline": true,
					"dateOnly": true,
					"dateFormat": "YYYY-MM-DD"
				});
			});
		</script>
		<h4>Address: </h4>
		<p>
			<label for="street">Street</label>
			<input id="street" name="street" value="">
		
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		
			<label for="zip">ZipCode</label>
			<input id="zip" name="zip" value="">
		
			<label for="country">Country</label>
			<input id="country" name="country" value="">
		</p>
		
		<p>
			<input type="submit">
		</p>
	</form>
 	
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="jquery.simple-dtpicker.js"></script>	
<p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Go Back"></p>
 
</body>
</html>