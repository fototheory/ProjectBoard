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
<script type="text/javascript" src="js/tms_presets.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script src="js/FF-cash.js" type="text/javascript"></script>

<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->

</head>

<body id="page1">
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
										<span><a href="forgotpassword.html#">Forgot Password ?</a></span>
										
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
				<div class="row-2">
					<nav>
						<ul class="menu">
							<li><a class="active" href="login.html">Home</a></li>
							<li><a href="services.html">Services</a></li>
							<li><a href="resources.html">Resources</a></li>
							<li><a href="projects.html">Projects</a></li>
							<li class="last-item"><a href="contactus.html">Contact
									Us</a></li>
						</ul>
					</nav>
				</div>
				<div class="row-3">
					<div class="slider-wrapper">
						<div class="slider">
							<ul class="items">
								<li><img src="images/slider-img1.jpg" alt=""> 
									<strong	class="banner"> <strong class="b2">Qualcomm</strong> 
									<strong class="b1">Mobile Data Traffic Management </strong> 
									<strong	class="b3">There is a real need for intelligent system selection and effective use of offload between 3G/LTE and Wi-Fi.<br>	</strong>
								</strong>
								</li>
								<li><img src="images/slider-img2.jpg" alt=""> <strong
									class="banner"> <strong class="b2">IMATIS </strong> <strong class="b1">Mobile Nurse Call </strong> <strong
										class="b3"> Nurses receive notifications to their IP phones with IMATIS Mobile Nurse Call.<br>
									</strong>
								</strong></li>
								<li><img src="images/slider-img3.jpg" alt=""> <strong
									class="banner"> <strong class="b2">TopView</strong> <strong class="b1">TopView Mobile Web App. </strong> <strong
										class="b3">Provides access to TopView alarms and all monitored data values from mobile devices.<br>
									</strong>
								</strong></li>
							</ul>
							<a class="prev" href="#">prev</a> <a class="next" href="#">prev</a>
						</div>
					</div>
				</div>
			</header>

			<!-- content -->
			<section id="content">
				<div class="padding">
					<div class="box-bg margin-bot">
						<div class="wrapper">
							<div class="col-1">
								<div class="box first">
									<div class="pad">
										<div class="wrapper indent-bot">
											<strong class="numb img-indent2">01</strong>
											<div class="extra-wrap">
												<h3 class="color-1">
													<strong>Post</strong>a Project
												</h3>
											</div>
										</div>
										<div class="wrapper">
											<div class="extra-wrap">As a project sponsor, we
												welcome you to post a project for our students to undertake
												for you.</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-1">
								<div class="box second">
									<div class="pad">
										<div class="wrapper indent-bot">
											<strong class="numb img-indent2">02</strong>
											<div class="extra-wrap">
												<h3 class="color-2">
													<strong>Manage</strong>a Project
												</h3>
											</div>
										</div>
										<div class="wrapper">
											
											<div class="extra-wrap">As a faculty member, you will
												be able to manage the flow of past and on-going projects.</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-2">
								<div class="box third">
									<div class="pad">
										<div class="wrapper indent-bot">
											<strong class="numb img-indent2">03</strong>
											<div class="extra-wrap">
												<h3 class="color-3">
													<strong>Choose</strong>a Project
												</h3>
											</div>
										</div>
										<div class="wrapper">
											
											<div class="extra-wrap">As a capstone student, you can
												request participating in a variety of meaningful projects.</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="wrapper">
						<div class="col-3">
							<div class="indent">
								<h2>About us</h2>
										<p class="color-4 p1">The NU Capstone Project Board is a web-based application that will streamline and enhance the current capability and process of Masters levels capstone projects by allowing the faculty to define academic, business and technical requirements. The board will facilitate the interactions between the project sponsor, National University (NU) faculty members, and students. The board will also serve as a platform to allows any student group grab a defined project and to archive finalized projects to give students exposure during and after a project completion.</p>
								<div class="wrapper"></div>
							</div>
						</div>
						<div class="col-2">
							<div class="indent">
								<h2>Our Mission</h2>
								<p class="color-4 p1">The Capstone Project Board @ NU aims
									to serve as a centralized platform for faculty members and
									students to acquire, track, and finalize capstone projects from
									beginning to end while also proving on-going exposure to older
									projects.</p>
								<div class="wrapper"></div>
							</div>
						</div>
					</div>
				</div>
		</div>
		</section>
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
	</div>
	</div>
	<script type="text/javascript">
		Cufon.now();
	</script>
	<script type="text/javascript">
		$(function() {
			$('.slider')._TMS({
				prevBu : '.prev',
				nextBu : '.next',
				playBu : '.play',
				duration : 600,
				easing : 'easeOutQuad',
				preset : 'simpleFade',
				pagination : false,
				slideshow : 3000,
				numStatus : false,
				pauseOnHover : true,
				banners : true,
				waitBannerAnimation : false,
				bannerShow : function(banner) {
					banner.hide().fadeIn(300)
				},
				bannerHide : function(banner) {
					banner.show().fadeOut(300)
				}
			});
		})
	</script>
</html>
