<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="globalHeader.jsp"/>

<body id="page4">
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
   
   <!--Accordion script -->
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
  
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <h2>Projects</h2>
              <p class="color-4">Each year organizations from the private, government, and non-profit sectors come to NU to assist them in turning ideas into action. Highly talented students work in accordance to the organizations business and technical requirements while also following strict academic requirements..</p>
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
                <li><a href="#">Project Title 9</a></li></ul></br>
             <a class="button-2" href="#">Previous Years</a> </div>
              </ul>
              
            </div>
			 
          </div>
        </div>
</section>
   <!-- footer -->
   <c:import url="globalFooter.jsp"/>
  </div>
</div>
</html>
