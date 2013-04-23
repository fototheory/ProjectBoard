<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registration success</title>
</head>
<body>

	<h2>Welcome <c:out value="${registration.fname}" /> <c:out value="${registration.lname}" />. Thank you for registering.</h2>
	<h3>You should receive an email from the system administrator when your account is ready.</h3>
	<table>
		<tr>
			<td>User Name :</td>
			<td><c:out value="${registration.email}" /></td>

		</tr>

		<tr>
			<td>Password :</td>
			<td><c:out value="${registration.password}" /></td>
		</tr>

		<tr>
			<td>Role :</td>
			<td><c:out value="${role}" /></td>
		</tr>

		<tr>
			<td>Discipline :</td>
			<td><c:out value="${discipline}" /></td>
		</tr>


	</table>

</body>
</html>