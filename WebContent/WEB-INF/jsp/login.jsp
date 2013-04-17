<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset=utf-8 />
	<title>Capstone Project Board</title>
</head>
<body>
<c:if test="${not empty status}">
	<font color="red">${status}</font><br /><br />
</c:if>
<form:form modelAttribute="loginUser" method="POST" action="login.do" id="userdetailsid" >		
	<fieldset>
		<legend>Login Form</legend>
		<ol>
			<li>
				<label for=name>Email</label>
				<form:input path="email"  type="text" required="true" />
			</li>
			<li>
				<label for=name>Password</label>
			<form:input path="password" type="password" required="true" />
			</li>
		</ol>
	</fieldset>
	<fieldset>
		<button type=submit>Login</button>
	</fieldset>
</form:form>
</body>
</html>