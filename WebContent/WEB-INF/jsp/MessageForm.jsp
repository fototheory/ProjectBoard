<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="globalHeader.jsp" />

      <div class="row-2">
        <nav>
          <ul class="menu">
            <li><a href="login.html">Home</a></li>
            <li><a href="services.html">Services</a></li>
            <li><a href="resources.html">Resources</a></li>
            <li><a href="projects.html">Projects</a></li>
            <li class="last-item"><a class="active" href="contactus.html">Contact Us</a></li>
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
               Let's partner with other universities to create diverse multidisciplinary teams nationally and internationally. </p> </br>
               </br>
                <c:if test="${not empty msg}">
					<font color="red">${msg}</font><br />
				</c:if>
				
				
               	<form:form method="Post" action="MessageForm.do" commandName="sendMessage">
               	
               	<table border ="3" width="80" >
               	<tr>
               		<td>To: </td>
               		<td><form:input path="recipientAddress" type="text"  size ="65" required="true" />
               		<FONT color="red"><form:errors path="recipientAddress" /></FONT></td>
               		
               		 </tr>
               		
               		
               		
               		<tr>
               		<td>Subject: </td>
               		
               		<td><form:input path="subject" type="text"   size ="65" required="true" />
               		<FONT color="red"><form:errors path="subject" /></FONT></td>
               		</tr>
               		
               		<tr>
               		
               		<td>Message:</td> 
               		
               		
               	
               		<td >
               		
               		
               		<textarea cols= "50" rows ="5" name ="message" > </textarea>
               		
               		
              
               		</tr>
               		
               	
               		
               		
               	
               		<tr>
               		
               		<td>
               		<input class="button-2" type="submit"  onclick ="alert('Are you sure you want to submit this message ?')"   value="Submit" /></td>
              
               
            
               		<td> <input class="button-2" type="Reset" value="Reset" /></td>
               		
               		</tr>
               		</table>
               		
				</form:form>
				
				
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
   
 	<c:import url="globalFooter.jsp" />
 	
 	</div>
</div>
</html>