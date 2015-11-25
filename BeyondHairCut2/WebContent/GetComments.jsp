<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find comments for a barber</title>
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
<a href="Homepage.jsp"><font color="grey" face="Comic sans MS"
	size="3"> HOME </font></a>
	

	<form action="getcomments" method="get">
		<h1><font color="purple" face="Comic sans MS" size="4">Search for comments on our barber ${fn:escapeXml(param.barberId)}</font></h1>
		
		<p>
			<label for="barberId">BarberId</label>
			<input id="barberId" name="barberId" value="${fn:escapeXml(param.barberId)}">
		</p>
		<font color="grey" face="Comic sans MS" size="2"> (Click the SUBMIT)</font>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<h1><font color="purple" face="Comic sans MS" size="4">Matching Comments</font></h1>
	
	<div id="CommentCreate" align="center"><a href="CommentCreate.jsp"><font color="purple" face="Comic sans MS" size="2">Leave My Comment</font></a></div>
	
        <table border="1">
            <tr>
                <th><font color="purple" face="Comic sans MS" size="2">BarberId</font></th>
                <th><font color="purple" face="Comic sans MS" size="2">CommentId</font></th>
                <th><font color="purple" face="Comic sans MS" size="2">Content</font></th>
                <th><font color="purple" face="Comic sans MS" size="2">CustomerId</font></th>
                <th><font color="purple" face="Comic sans MS" size="2">Delete Comment</font></th>
            </tr>
            <c:forEach items="${comments}" var="comment" >
                <tr>
                    <td><c:out value="${comment.getBarberId()}" /></td>
                    <td><c:out value="${comment.getCommentId()}" /></td>
                    <td><c:out value="${comment.getContent()}" /></td>
                    <td><c:out value="${comment.getCustomerId()}" /></td>
                    <td><a href="deletecomment?commentId=<c:out value="${comment.getCommentId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
     <p><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Go Back"></p>  
</body>
</html>