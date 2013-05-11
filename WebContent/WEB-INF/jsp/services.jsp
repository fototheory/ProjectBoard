<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="globalHeader.jsp"/>

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
             	             	
             	<iframe src="https://www.google.com/calendar/embed?title=Upcoming%20Capstone%20Presentations&amp;showTitle=0&amp;showDate=0&amp;showTabs=0&amp;showTz=0&amp;mode=AGENDA&amp;height=400&amp;wkst=2&amp;bgcolor=%23FFFFFF&amp;src=nu.capstone.board%40gmail.com&amp;color=%23875509&amp;ctz=America%2FLos_Angeles" style=" border:solid 1px #777 " width="250" height="400" frameborder="0" scrolling="no"></iframe>
             	
          </div>
        </div>
        
      
    </section>
    <!-- footer -->
     <c:import url="globalFooter.jsp" />

  </div>
</div>
</html>
