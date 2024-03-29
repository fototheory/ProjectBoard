<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />


 <!-- include CSS & JS files -->
 <!-- CSS file -->
 <link rel="stylesheet" type="text/css" href="Selectyze.jquery.css" media="screen" />
 <!-- jQuery files -->
 <script type="text/javascript" src="jquery.js"></script>
 <script type="text/javascript" src="Selectyze.jquery.min.js"></script>
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3" style="width:680px;">
            <div class="indent">
              <c:if test="${not empty status}">
				<font color="red">${status}</font>
			  </c:if>
              <h2>Projects</h2>
              <p class="color-4">Each year organizations from the private, government, and non-profit sectors come to NU to assist them in turning ideas into action. Highly talented students work in accordance to the organization’s business and technical requirements while also following strict academic requirements..</p>
              <div class="wrapper p2">
              </div>
			  	<h3 style="padding-bottom:10px;"> Available Projects <span style="float:right;"></span></h3>
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



						 <form id="form" action="#" method="post" enctype="multipart/form-data">
						     <br/><h5>Resulting business and technical project requirements:</h5><br /> 
						      <textarea cols="50" rows="10"></textarea>	  						                   					          
						 </form>						
						<br />
											
						
						<br/>Append discipline's academic requirements for this project :<br/>
						<!-- Display disciplines from the database -->	
						<form:select path="disciplines" items="${disciplines}" />	
						<br/>
							
							<br/> Additional team member ratio: <br/>
					 <select name="my_select" class="selectyze">
						 <option value="value1" selected="selected"> </option>
						 <option value="value2">1</option>
						 <option value="value2">2</option>
						 <option value="value2">3</option>
						 <option value="value2">4</option>
						 <option value="value2">5</option>
					 </select>
						<br/>
						
						<br/> Suggested team size:<br/>
						 <select name="my_select" class="selectyze">
						 <option value="value1" selected="selected"> </option>
						 <option value="value2">1</option>
						 <option value="value2">2</option>
						 <option value="value2">3</option>
						 <option value="value2">4</option>
						 <option value="value2">5</option>
					 </select>
							<br/>
						<br/> Total number of team member hours involvement per week: <br/>
						 <select name="my_select" class="selectyze">
						 <option value="value1" selected="selected"> </option>
						 <option value="value2">15</option>
						 <option value="value2">20</option>
						 <option value="value2">30</option>
						 <option value="value2">40</option>
						 <option value="value2">50</option>
					 </select>
						<br/>
									
						<!-- Basic / Normal HTML Select element -->
					<br/> Assign a Capstone faculty member: <br/>	
					 <select name="my_select" class="selectyze">
						 <option value="value1" selected="selected"> List of Potential Capstone Faculty Members</option>
						 <option value="value2">Dr. Gordon Romney</option>
						 <option value="value2">Mr. Christopher Simpson</option>
						 <option value="value2">Dr. Bhaskar Raj Sinha</option>
						 <option value="value2">Dr. Ronald Gonzales</option>
						 <option value="value2">Mr. Brian J Arnold</option>
					 </select>
						
						
						<br /><br />
						<c:if test="${map.get('Status') eq 'Neg Assigned'}">
						<a href="#" style="padding-left:20px;"> | Publish project | </a>	
						<a href="#" style="padding-left:20px;"> | Assign project to student | </a><br />
						<a href="projectList.do?action=archive&id=${map.get('ID')}" style="padding-left:20px;"> | Archive project | </a>
						<a href="#" style="padding-left:5px;"> | Create a student group| </a>						
						<br /><br />
						<a href="projectprogress.do"> | View project progress | </a>
						<a href="#" style="padding-left:5px;"> | Submit this project to the Capstone Faculty | </a>
						</c:if>				
					</div>
					</c:forEach>
				</c:if>
				</div>			   
			 </div>
		  </div>
          <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 p2">MyArchived<br />Projects</h3>
               <c:choose>
               <c:when test="${not empty archiveList}">
              	<ul class="list-2">	 
              		<c:forEach items="${archiveList}" var="map">             
	                <li><a href="archivedProjects.do?id=${map.get('ID')}">${map.get("Title")}</a></li>
	                </c:forEach>          
              	</ul>
              	</c:when>
              	<c:otherwise>
              		<ul class="list-2">
              		 <li>No Archived Projects</li>
              		</ul>
              	</c:otherwise>
              </c:choose>
              <br />
               <a class="button-2" href="archivedProjects.do">Previous Years</a>
            </div>
			
		  </div>
          </div>
       </div>
	</section>
	
	<script type="text/javascript">
 $(document).ready(function(){
 // simple Selectyze call
$(".selectyze").Selectyze();
 // call with options
$(".selectyze").Selectyze({
 theme:'css3',
 effectOpen:'fade',
effectClose:'slide'
 });
});
</script>


<c:import url="template/footer.jsp" />