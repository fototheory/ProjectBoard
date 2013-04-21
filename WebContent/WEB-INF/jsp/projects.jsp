<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<!--SOurces: http://jqueryui.com/accordion/#no-auto-height -->
 
<html lang="en">
<head>
<title>The Capstone Portal @ NU | Projects</title>
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

 <title>jQuery UI Accordion - No auto height</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <script>
  $(function() {
    $( "#accordion" ).accordion({
      heightStyle: "content"
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
            <li><a href="services.html">Services</a></li>
            <li><a href="resources.html">Resources</a></li>
            <li><a class="active" href="projects.html">Projects</a></li>
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
              <h2>Projects</h2>
              <p class="color-4">Each year organizations from the private, government, and non-profit sectors come to NU to assist them in turning ideas into action. Highly talented students work in accordance to the organization’s business and technical requirements while also following strict academic requirements..</p>
              <div class="wrapper p2">
              </div>
			  
		<h3> Available Projects</h3>
				<div id="accordion">
				  <p>Sponsor name ~ Project name ~ Delivery date  </p>
				  <div>
					<p>
					Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description. </br> </br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  	</a> &nbsp <a href="#">| request to participate in this project | </a>
					</p>
				  </div>
				  <p>Sponsor name ~ Project name ~ Delivery date  </p>
				  <div>
					<p>
					Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description. </br> </br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  	</a> &nbsp <a href="#">| request to participate in this project | </a>
					</p>
				  </div>
				  <p>Sponsor name ~ Project name ~ Delivery date  </p>
				  <div>
					<p>
					Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description. </br> </br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  	</a> &nbsp <a href="#">| request to participate in this project | </a>
					</p>
				  </div>
				  <p>Sponsor name ~ Project name ~ Delivery date  </p>
				  <div>
					<p>
					Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description. </br> </br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  	</a> &nbsp <a href="#">| request to participate in this project | </a>
					</p>
				  </div>
				  <p>Sponsor name ~ Project name ~ Delivery date  </p>
				  <div>
					<p>
					Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description. </br> </br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  	</a> &nbsp <a href="#">| request to participate in this project | </a>
					</p>
				 </div>
		</div>  
			</br>
			  </div>
			 </div>
          <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 p2">Archived Projects</h3>
              <ul class="list-2">
                <li><a href="#">Project Title 1 </a></li>
                <li><a href="#">Project Title 2</a></li>
                <li><a href="#">Project Title 3</a></li>
                <li><a href="#">Project Title 4</a></li>
                <li><a href="#">Project Title 5</a></li>
                <li><a href="#">Project Title 6</a></li>
                <li><a href="#">Project Title 7</a></li>
                <li><a href="#">Project Title 8</a></li>
                <li><a href="#">Project Title 9</a></li>
              </ul>
            </div>
			 <a class="button-2" href="#">Previous Years</a> </div>
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
