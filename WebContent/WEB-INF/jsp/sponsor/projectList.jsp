<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
              <h2>Projects</h2>
              <p class="color-4">Each year organizations from the private, government, and non-profit sectors come to NU to assist them in turning ideas into action. Highly talented students work in accordance to the organization’s business and technical requirements while also following strict academic requirements..</p>
              <div class="wrapper p2">
              </div>
			  	<h3 style="padding-bottom:10px;"> Available Projects <span style="float:right;"><a class="button-2" href="projectForm.do?action=add&id=0" id="addProj">Add Project</a></span></h3>
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
						<c:if test="${map.get('Status') eq 'New'}">
						<a href="projectList.do?action=edit&id=${map.get('ID')}" style="padding-left:65px;"> | Edit project | </a>	
						<a href="projectList.do?action=delete&id=${map.get('ID')}" style="padding-left:20px;"> | Delete project | </a>
						<a href="projectList.do?action=archive&id=${map.get('ID')}" style="padding-left:20px;"> | Archive project | </a>
						<br /><br />
						<a href="projectList.do?action=progress&id=${map.get('ID')}" style="padding-left:10px;"> | View project progress | </a>
						<a href="projectList.do?action=submit&id=${map.get('ID')}&dispID=${map.get('DispID')}" style="padding-left:15px;"> | Submit this project to Lead Faculty | </a>
						</c:if>				
						</p>
					</div>
					</c:forEach>
				</c:if>
				</div>			   
			 <br />
			 </div>
		  </div>
          <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 p2">Archived Projects</h3>
               <c:choose>
               <c:when test="${not empty archiveList}">
              	<ul class="list-2">	 
              		<c:forEach items="${archiveList}" var="map">             
	                <li><a href="#">${map.get("Title")}</a></li>
	                </c:forEach>          
              	</ul>
              	</c:when>
              	<c:otherwise>
              		<ul class="list-2">
              		 <li>No Archived Project</li>
              		</ul>
              	</c:otherwise>
              </c:choose>
            </div>
			 <a class="button-2" href="archivedProjects.do">Previous Years</a>
		  </div>
          </div>
       </div>
	</section>
	<c:import url="template/footer.jsp" />