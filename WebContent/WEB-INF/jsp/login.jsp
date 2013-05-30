<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="globalHeader.jsp"/>

<body id="page5">
<div class="row-2">
					<nav>
						<ul class="menu">
							<li><a class="active" href="login.html">Home</a></li>
							<li><a href="services.html">Services</a></li>
							<li><a href="resources.html">Resources</a></li>
							<li><a href="projects.html">Projects</a></li>
							<li class="last-item"><a href="MessageForm.html">Contact
									Us</a></li>
						</ul>
					</nav>
				</div>
				<div class="row-3">
					<div class="slider-wrapper">
						<div class="slider">
							<ul class="items">
								<li><img src="images/slider-img1.jpg" alt=""> 
									<strong	class="banner" style="margin-top:-60px;"><strong class="b2" style="font-size:75px;">Project Qualcomm</strong> 
									<strong class="b1" style="margin-top:-15px;">Mobile Data Traffic Management </strong> 
									<strong	class="b3">There is a real need for intelligent system selection and effective use of offload between 3G/LTE and Wi-Fi.<br>	</strong>
								</strong>
								</li>
								<li><img src="images/slider-img3.jpg" alt=""> <strong
									class="banner" style="margin-top:-60px;"> <strong class="b2" style="font-size:75px;">Project IMATIS </strong> <strong class="b1" style="margin-top:-15px;">Mobile Nurse Call </strong> <strong
										class="b3"> Nurses receive notifications to their IP phones with IMATIS Mobile Nurse Call.<br>
									</strong>
								</strong></li>
								<li><img src="images/slider-img1.jpg" alt=""> <strong
									class="banner" style="margin-top:-60px;"> <strong class="b2" style="font-size:75px;">Project TopView</strong> <strong class="b1" style="margin-top:-15px;">TopView Mobile Web App. </strong> <strong
										class="b3">Provides access to TopView alarms and all monitored data values from mobile devices.<br>
									</strong>
								</strong></li>
							</ul>
							<a class="prev" href="#">prev</a> <a class="next" href="#">prev</a>
						</div>
					</div>
				</div>
				
			<!-- content -->
			<section id="content">
				<div class="padding">
					<div class="box-bg margin-bot" style="margin-top:-25px;">
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
						<div class="col-3" style="width:590px;">
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
			
		</section>
		<!-- footer -->
			<c:import url="globalFooter.jsp" />


	
 <!--Slider script -->
<script type="text/javascript">Cufon.now();</script>
<script type="text/javascript">
$(function () {
    $('.slider')._TMS({
        prevBu: '.prev',
        nextBu: '.next',
        playBu: '.play',
        duration: 1200,
        easing: 'easeOutQuad',
        preset: 'simpleFade',
        pagination: false,
        slideshow: 3000,
        numStatus: false,
        pauseOnHover: true,
        banners: true,
        waitBannerAnimation: true,
        bannerShow: function (banner) {
            banner.hide().fadeIn(500)
        },
        bannerHide: function (banner) {
            banner.show().fadeOut(500)
        }
    });
})
</script>
</html>
