<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<!-- resources http://jqueryui.com/datepicker/#inline -->

<html lang="en">
<head>
<title>The Capstone Portal @ NU | Services</title>

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


<title>jQuery UI Datepicker - Display inline</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="css/style.css" />
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>


<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
</head>
<body id="page2">
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
					<form:form modelAttribute="loginUser" method="POST"	action="login.do" id="userdetailsid">
						<fieldset id="body">
							<fieldset>
								<label for="email">Email Address</label>
								<form:input path="email" type="text" required="true" />
							</fieldset>
							<fieldset>
								<label for="password">Password</label>
								<form:input path="password" type="password" required="true" />
							</fieldset>
							<input type="submit" id="login" value="Sign in" />
						</fieldset>
						<span><a href="registrationform.html#">Sign up</a></span>
					</form:form>
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
            <li><a class="active" href="services.html">Services</a></li>
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
        <div class="wrapper p4">
          <div class="col-3">
            <div class="indent">
              <h2>Services we offer</h2>
            
               <p class="p1">The services we currently offer extend only to casptone students enrolled in a Master of Science in Computer Science within the School of Engineering, Technology and Media at NU. As the NU Capstone Project evolves and matures, it will welcome the participation of the rest of the school departments as shown below and in more detail <a href = "http://www.nu.edu/OurPrograms.cfm" >here</a>.</p>
				<ul class="list-2">
                  <li><strong>School of Engineering, Technology and Media</strong></li>
                  <li>-Applied Engineering</li>
                  <li>-Computer Science, Information and Media Systems</li> <br>
                  <li><strong>School of Business and Management</strong></li>
                  <li>-Accounting and Finance</li>
                  <li>-Leadership and Human Resource Management</li>
                  <li>-Marketing and Management</li><br>
                  <li><strong>School of Education</strong></li>
                  <li>-Department of Special Education</li>
                  <li>-Department of Teacher Education</li>
                  <li>-Educational Administration and School Counseling/Psychology</li> <br>
                  <li><strong>School of Health and Human Services</strong></li>
                  <li>-Department of Community Health</li>
                  <li>-Department of Health Sciences</li>
                  <li>-Department of Nursing</li>
                </ul>
				
			    </div>
          </div>
          <div class="col-4">
              <h3 class="color-4 p2">Presentations Coming up:</h3>
              <div class="wrapper indent-bot">

			  </div>

             	<div id="datepicker"></div>


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
