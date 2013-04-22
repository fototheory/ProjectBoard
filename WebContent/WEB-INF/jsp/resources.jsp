<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>The Capstone Portal @ NU | Resources</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>jQuery Dropdown Login Freebie | The Finished Box</title>
    <link rel="stylesheet" href="css/style.css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
    <script src="js/login.js"></script>

	
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Light_300.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Semibold_600.font.js" type="text/javascript"></script>
<script src="js/FF-cash.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
</head>
<body id="page3">
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
        <h1> <a class="logo" href="login.html">The Capstone Portal @ NU</a> <strong class="slog">Ideas into action</strong> </h1>
     </div>
      <div class="row-2">
        <nav>
          <ul class="menu">
            <li><a href="login.html">Home</a></li>
            <li><a href="services.html">Services</a></li>
            <li><a class="active"  href="resources.html">Resources</a></li>
            <li><a href="projects.html">Projects</a></li>
            <li class="last-item"><a href="contactus.html">Contact Us</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="indent">
          <h2>Available Resources</h2>
          <div class="wrapper indent-bot">
            <div class="col-3">
              <div class="wrapper">
                <figure class="img-indent4"><img src="images/page3-img1.png" alt="" /></figure>
                <div class="extra-wrap">
                  <h6>Management</h6>
                  Faculty members will have a wide array of resources available such as 1 , 2, 3, etc. </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <figure class="img-indent3"><img src="images/page3-img2.png" alt="" /></figure>
                <div class="extra-wrap">
                  <h6>Planning</h6>
                  Capstone students will have a wide array of resources available such as 1 -Face time with the sponsor- , 2, 3, etc </div>
              </div>
            </div>
          </div>
          <div class="wrapper indent-bot2">
            <div class="col-3">
              <div class="wrapper">
                <figure class="img-indent3"><img src="images/page3-img3.png" alt="" /></figure>
                <div class="extra-wrap">
                  <h6>Monitoring</h6>
                  Capstone students will have a wide array of resources available such as 1 -free linces to software like MS Project , 2, 3, etc </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <figure class="img-indent"><img src="images/page3-img4.png" alt="" /></figure>
                <div class="extra-wrap">
                 <h6>Available software tools</h6>
                  Download ANY Microsoft software! Just email the Academic Computing Director at <a href="#">msotelo@nu.edu</a> and ask him to create an account in the MS DreamSpark site for you. Include your student ID and full name. </div>
              
			  </div>
            </div>
          </div>
          <div class="wrapper p3">
            <div class="col-3">
              <div class="wrapper">
                <figure class="img-indent3"><img src="images/page3-img5.png" alt="" /></figure>
                <div class="extra-wrap">
                  <h6>Exposure</h6>
                  Capstone students will have a wide array of resources available such as 1 -LinkedIn , 2 -Networking Opportunites, 3, etc </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <figure class="img-indent"><img src="images/page3-img6.png" alt="" /></figure>
                <div class="extra-wrap">
                  <h6>Coaching</h6>
                  Capstone students will have a wide array of resources available such as 1 , 2, 3, etc<br>
                  Capstone students will have a wide array of resources available such as 1 , 2, 3, etc </div>
              </div>
            </div>
          </div>
        </div>
        </section>
    <!-- footer -->
    <footer>
      
      <div class="row-bot">
        <div class="aligncenter">
          <p class="p0">Copyright &copy; <a href="#">Domain Name</a> All Rights Reserved</p>
          
          <!-- {%FOOTER_LINK} -->
        </div>
      </div>
    </footer>
  </div>
</div>
</html>