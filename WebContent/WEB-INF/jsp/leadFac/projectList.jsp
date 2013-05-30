<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${not empty selectedOrderedId}">
	// this will make the second tab by default opened (index starts from 0)
    $('#accordion').accordion({active: <c:out value="${selectedOrderedId}"/>}); 
	</c:if>	
});
function submitNegForm(count) {
	 $('#negForm'+count).submit();
}
function submitAcptForm(count) {
	 $('#acptForm'+count).submit();
}
</script>
	
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3" style="width:680px;">
            <div class="indent">
              <c:if test="${not empty status}">
				<font color="red">${status}</font>
			  </c:if>
			  	<h2> My Project List</h2>
			  	<div id="accordion">
			  	<c:if test="${not empty projectList}">
			  		<c:set var="count" value="0" scope="page" />
			  		<c:set var="ackcount" value="0" scope="page" />
					<c:forEach items="${projectList}" var="map"> 
					<c:if test="${map.get('Status') != 'New'}">
					<p>${map.get("Discipline")} ~ ${map.get("Title")} ~ ${map.get("Due Date")}</p>
					<div>
						<p>	
						<h5>Company: </h5>${map.get("company")}<br />	
						<h5>Project sponsor: </h5>${map.get("Sponsor")}<br />
						<h5>Project description:</h5> ${map.get("Description")}<br />
						<h5>Status:</h5> ${map.get("Status")}<br />
						<br />
						<c:choose>
						<c:when test="${map.get('Lead Faculty') == 'Waiting for lead faculty to accept project'}">
						<form action="projectList.do" method="post" id="acptForm${ackcount}">
			              	<input type="hidden" name="projId" value="${map.get('ID')}" />
			              	<input type="hidden" name="statId" value="${map.get('statId')}" />
			              	<input type="hidden" name="leadId" value="${map.get('leadId')}" />
			              	<input type="hidden" name="acptVal" id="acptVal" value="true">
						 	<a href="#" id="acceptProj${ackcount}" onClick="submitAcptForm(${ackcount})">| Acknowledge Project |</a>
						 	<c:set var="count" value="${ackcount + 1}" scope="page"/>
						</form>
						 </c:when>
						 <c:otherwise>
						 <form action="projectList.do" method="post" id="negForm${count}">
						 <input type="hidden" name="projId" value="${map.get('ID')}" />
						 <input type="hidden" name="acptVal" id="acptVal" value="false">
						  Append discipline's academic requirements for this project :<br/>
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
						 <br/> 
						<c:choose>
               			<c:when test="${map.get('Status') eq 'Submitted'}">
               			 <!-- Basic / Normal HTML Select element -->
						 Assign a Negotiating faculty member: <c:if test="${not empty negError && negError eq 'true' && not empty selectedId && selectedId eq map.get('ID')}"><label style="float: none; color: red; padding-left: .5em; vertical-align: top;">Select Negotiating Faculty</label></c:if><br/>	
						 <select name="neg" class="selectyze">
							 <option value="" selected="selected"> List of Potential Negotiating Faculty Members</option>
							 <c:if test="${not empty facUsers}">
								 <c:forEach items="${facUsers}" var="user">
								 <option value="${user.getId()}">${user.getFname()} ${user.getLname()}</option>
	   							 </c:forEach>
							 </c:if>
						 </select>
						 </c:when>
						 <c:otherwise>
						 		<h5>Negotiating Faculty:</h5> ${map.get('Negotiating Faculty')}
						 </c:otherwise>
						 </c:choose>
 						 <br/><br/>
						 <a href="projectprogress.html#" style="padding-left:150px;"> | view project progress | </a><br />
               			 <c:if test="${map.get('Status') eq 'Submitted'}">
						 <a href="#" id="negSubmit${count}" style="margin-top:20px;" onClick="submitNegForm(${count})">| Route this project to the selected negotiating faculty member | </a>
						 </c:if>
						 <c:set var="count" value="${count + 1}" scope="page"/>
						 </form>
						 </c:otherwise>
						 </c:choose>
					</div>
					</c:if>
					</c:forEach>
				</c:if>
				</div>			   
			 	<br />
			 </div>
		  </div>
           <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 p2">My Archived<br />Projects</h3>
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
	<c:import url="template/footer.jsp" />