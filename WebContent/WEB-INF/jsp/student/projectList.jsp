<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
	<c:if test="${not empty sessionUserInfo}">
	<font color="red">${sessionUserInfo}</font><br /><br />
	</c:if>
	<form method="post" action="index.do">
	<input type="submit" value="View Project List" name="projectList"></input>
	</form>
	<br />
	<form method="post" action="index.do">
	<input type="submit" value="Logout" name="logout"></input>
	</form>
	<br />
</body>
</html>