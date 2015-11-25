<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Comment</title>
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
	<h1>${messages.title}</h1>
	<form action="deletecomment" method="post">

		<div
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="commentId">Comment ID</label> <input id="commentId"
				name="commentId" value="${fn:escapeXml(param.commentId)}">
		</div>
		<p>
			<span id="submitButton"
				<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<input type="submit">
			</span>
		</p>
	</form>
	<br />
	<br />

</body>
</html>