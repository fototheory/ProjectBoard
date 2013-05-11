<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>The Capstone Portal @ NU</title>
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>jQuery Dropdown Login Freebie | The Finished Box</title>
<link rel="stylesheet" href="css/style.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
<script src="js/login.js"></script>

<link rel="stylesheet" href="css/reset.css" type="text/css"	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"	media="screen">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Light_300.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Semibold_600.font.js" type="text/javascript"></script>
<script type="text/javascript" src="js/tms-0.3.js"></script>
<script src="js/FF-cash.js" type="text/javascript"></script>
<script type="text/javascript" src="js/tms_presets.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>

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
				<div id="bar">
					<div id="container">
						<!-- Login Starts Here -->
						<c:if test="${not empty status}">
							<font color="red">${status}</font>
						</c:if>
						<div id="loginContainer">
							<a href="#" id="loginButton"><span>Login</span><em></em></a>
							<div style="clear: both"></div>
							<div id="loginBox">
								<div id="loginForm">
									<form:form modelAttribute="loginUser" method="POST"	action="login.do" id="userdetailsid">
										<fieldset id="body">
											<fieldset>
												<label for="email">Email Address</label>
												<form:input id="email" path="email" type="text" required="true" />
											</fieldset>
											<fieldset>
												<label for="password">Password</label>
												<form:input id="password" path="password" type="password" required="true" />
											</fieldset>
											<input type="submit" id="login" value="Sign in" />
										</fieldset>
										<span><a href="registrationform.html#">Sign up</a></span>
									</form:form>
								</div>
							</div>
						</div>
						
						<!-- Login Ends Here -->
					</div>
				</div>
				<div class="row-1">
					<h1>
						<a class="logo" href="login.html">The Capstone Portal @ NU</a> <strong
							class="slog">Ideas into action</strong>
					</h1>
				</div>
				
			</header>
</html>
