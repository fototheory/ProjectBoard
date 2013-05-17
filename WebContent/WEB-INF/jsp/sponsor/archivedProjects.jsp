<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <c:if test="${not empty status}">
				<font color="red">${status}</font>
			  </c:if>
              <h2>My archived projects</h2>              
              <div class="wrapper p2">
              </div>
			  	
			  	<h3 style="padding-bottom:10px;"> FY 2012 <span style="float:right;"><a class="button-2" href="projectList.do" >Back to my project list</a></span></h3>

				<div id="accordion">
				 <c:if test="${not empty projectList}">
					<c:forEach items="${projectList}" var="map"> 
					<p>${map.get("Sponsor")} ~ ${map.get("Title")} ~ ${map.get("Due Date")}</p>
					<div>
					<p>						
						<c:forEach items="${map}" var="entry"> 
							<c:if test="${entry.key != 'Sponsor' && entry.key != 'Title' && entry.key != 'Due Date'&& entry.key != 'DispID'}">
							${entry.key}: ${entry.value}<br />
							</c:if>
						</c:forEach>	
						<br />
						
					</p>
				  	</div>
					</c:forEach>
				</c:if>
				</div>  
				<br />
			  </div>
			 </div>
         
          
           
             
              
		  </div>
          
	</section>
	<c:import url="template/footer.jsp" />
	