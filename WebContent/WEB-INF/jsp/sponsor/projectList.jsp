<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sponsor - Project List</title>
</head>
<body>
<c:if test="${not empty sessionUserInfo}">
	<font color="red">${sessionUserInfo.getFname()} ${sessionUserInfo.getLname()}</font><br /><br />
</c:if>
<c:if test="${not empty projectList}">
<c:forEach items="${projectList}" var="map">   
	<c:forEach items="${map}" var="entry"> 
		<p>${entry.key}: ${entry.value}</p>
	</c:forEach>
	<hr />
</c:forEach>
</c:if>
</body>
</html>