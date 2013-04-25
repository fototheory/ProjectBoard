<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
<title>The Capstone Portal @ NU | Registration</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css"	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"	media="screen">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Light_300.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Semibold_600.font.js" type="text/javascript"></script>
<script src="js/FF-cash.js" type="text/javascript"></script>

<style>
input,label {
	line-height: 1.5em;
}
</style>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>


<title>jQuery UI Dialog - Basic modal</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
	$(function() {
		$("#dialog-modal").dialog({
			height : 140,
			modal : true
		});
	});
</script>

<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
</head>
<body id="page4">
	<!-- header -->
	<div class="bg">
		<div class="main">
			<header>
				<div class="row-1">
					<h1>
						<a class="logo" href="index.html">The Capstone Portal @ NU</a> 
							<strong	class="slog">Ideas into action</strong>
					</h1>
				</div>
				<div class="row-2">
					<nav>
						<ul class="menu">
							<li><a href="login.html">Home</a></li>
							<li><a href="services.html">Services</a></li>
							<li><a href="resources.html">Resources</a></li>
							<li><a href="projects.html">Projects</a></li>
							<li class="last-item"><a href="contactus.html">Contact Us</a></li>
						</ul>
					</nav>
				</div>
			</header>

			<!-- content -->
			<section id="content">
				<div class="padding">
					<div class="wrapper margin-bot">
						<div class="col-3">
							<div class="indent">
								<h2 class="p0">Registration Form</h2>
								<c:if test="${not empty status}">
									<font color="red">${status}</font>
								</c:if>
								<form:form method="Post" action="registrationform.do"
									commandName="registration">
									<table>
										<tr>
											<td>First Name:<br /><FONT color="red">
											<form:errors path="fname" /></FONT></td>
										</tr>

										<tr>
											<td><form:input path="fname" type="text" required="true" /></td>
										</tr>

										<tr>
											<td>Last Name:<br /><FONT color="red">
											<form:errors path="lname" /></FONT></td>
										</tr>

										<tr>
											<td><form:input path="lname" type="text" required="true" /></td>
										</tr>
										<tr>
											<td>Password:<br /><FONT color="red">
											<form:errors path="password" /></FONT></td>
										</tr>
										<tr>
											<td><form:input path="password" type="password" required="true" /></td>
										</tr>
										<tr>
											<td>Confirm Password:<br /><FONT color="red">
											<form:errors path="confirmPassword" /></FONT></td>
										</tr>
										<tr>
											<td><form:input path="confirmPassword" type="password" required="true" /></td>
										</tr>
										<tr>
											<td>Email:<br /><FONT color="red">
											<form:errors path="email" /></FONT></td>
										</tr>
										<tr>
											<td><form:input path="email" type="text" required="true" /></td>
										</tr>

										<!-- This displays the various disciplines from the database. -->
										<tr>
											<td>Subject area:<br />
											<FONT color="red"><form:errors path="discipline" required="true" /></FONT></td>
										</tr>
										<tr>
											<td>
												<!-- Display disciplines from the database -->
												<c:forEach items="${disciplines}" var="discipline">
													<form:radiobutton path="discipline" name="discipline" 
														value="${discipline.id}" label="${discipline.name}" required="true" />
													<br />
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>You are a:<br />
											<FONT color="red"><form:errors path="role" /></FONT></td>
										</tr>
										<tr>
											<td>
												<!-- Display roles from the database -->
												<c:forEach items="${roles}" var="role">
													<form:radiobutton path="role" name="userrole" 
														value="${role.id}" label="${role.name}" required="true" />
													<br />
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td><input type="submit" value="Submit" /></td>
										</tr>
									</table>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>

<!-- footer -->
<footer>

	<div class="row-bot">
		<div class="aligncenter">
			<p class="p0">
				Copyright &copy; <a href="#">Domain Name</a> All Rights Reserved
			</p>

			<!-- {%FOOTER_LINK} -->
		</div>
	</div>
</footer>

</html>
