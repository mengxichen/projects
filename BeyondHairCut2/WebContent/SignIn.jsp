

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BEYOND A HAIRCUT</title>
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

<a href="Homepage.jsp"><img src="BeyondHairCut.png" width="600" height = "150" align="top"></a>

<body>
<h1><font color="grey" face="Comic sans MS"> SIGN IN</font></h1>
<section class="loginform cf">
<form action="homepage" method="post">
	<ul>
	<li><label for="userName">UserName</label>
	<input id="userName" name="userName" value="" placeholder="username (0~9,a~z)" required></li>
	<li><label for="password">Password</label>
	<input id= "password     " name="password" value="" type="password" name="password" placeholder="password" required></li>
	
		
	<li><input type="submit" value= "login"></li>
	</ul>	
</form>
</section>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
<p><font color="blue" face="Comic sans MS"> Haven't sign up yet? <a href="CustomerCreate.jsp">SIGN UP</a></font></p>



<br/><br/>

</body>
</html>