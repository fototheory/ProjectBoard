<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="globalHeader.jsp"/>

<body id="page5">

      <div class="row-2">
        <nav>
          <ul class="menu">
            <li><a href="login.html">Home</a></li>
            <li><a href="services.html">Services</a></li>
            <li><a href="resources.html">Resources</a></li>
            <li><a href="projects.html">Projects</a></li>
            <li class="last-item"><a class="active" href="MessageForm.html">Contact Us</a></li>
          </ul>
        </nav>
      </div>
    
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <h2 class="p0">Let's keep in touch</h2>
              
              <p class="text-1">There is a lot more we can do together<br>
               Let's partner with other universities to create diverse multidisciplinary teams nationally and internationally. </p>
            </div>
          </div>
		  <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 indent-bot2">Contact Info</h3>
              <dl class="contact p3">
                <dd><span>Address:</span>11255 North Torrey Pines Road</dd>
                <dd><span>City:</span>La Jolla</dd>
				<dd><span>E-mail:</span><a href="MessageForm.html#">webmaster.nuproactivegrads@gmail.com</a></dd>
              </dl>
              <h3 class="color-4 indent-bot2">Follow Us</h3>
              <ul class="list-services">
                <li class="item-1"><a href="https://www.facebook.com/">Facebook</a></li>
                <li class="item-2"><a href="https://twitter.com/">Twitter</a></li>
                <li class="item-3"><a href="http://www.linkedin.com/">LinkedIn</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>

<!-- footer -->
 	<c:import url="globalFooter.jsp"/>
 	
 	</div>
</div>
</html>