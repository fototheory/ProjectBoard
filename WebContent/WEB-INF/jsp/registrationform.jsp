<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

<form:form method="Post" action="registrationform.do" commandName="registration">
<table>
<tr>
<td>First Name:<FONT color="red"><form:errors
path="fname" /></FONT></td>
</tr>

<tr>
<td><form:input path="fname" /></td>
</tr>

<tr>
<td>Last Name:<FONT color="red"><form:errors
path="lname" /></FONT></td>
</tr>

<tr>
<td><form:input path="lname" /></td>
</tr>
<tr>
<td>Password:<FONT color="red"><form:errors
path="password" /></FONT></td>
</tr>
<tr>
<td><form:password path="password" /></td>
</tr>
<tr>
<td>Confirm Password:<FONT color="red"><form:errors
path="confirmPassword" /></FONT></td>
</tr>
<tr>
<td><form:password path="confirmPassword" /></td>
</tr>
<tr>
<td>Email:<FONT color="red"><form:errors path="email" /></FONT></td>
</tr>
<tr>
<td><form:input path="email" /></td>
</tr>
<tr>
<td><input type="submit" value="Submit" /></td>
</tr>
</table>
</form:form>



</body>
</html>