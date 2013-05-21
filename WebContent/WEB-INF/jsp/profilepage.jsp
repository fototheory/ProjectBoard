<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="template/header.jsp" />

<script>
 	$(document).ready(function(){
 		$("#projectlist").click(function(e) {
 			e.preventDefault();
 	 		alert('Please complete your profile before going\nto the projects tab.');
 		});
 	});
</script>

<!-- content -->
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="col-3">
				<div class="indent">
					<h2 class="p0">Profile Page</h2>
					<c:if test="${not empty status}">
						<font color="red">${status}</font>
					</c:if>
					<form:form method="Post" action="profilepage.do" commandName="profile">
						<table>
							<tr>
								<td>Company or University Name:<br /> 
								<FONT color="red"><form:errors path="company" /></FONT></td>
							</tr>

							<tr>
								<td><form:input path="company" type="text" required="true"/></td>
							</tr>

							<tr>
								<td>Phone:<br /> 
								<FONT color="red"><form:errors path="phone" /></FONT></td>
							</tr>
							<tr>
								<td><form:input path="phone" type="text" required="true"/></td>
							</tr>
							<tr>
								<td>Skills:<br /> 
								<FONT color="red"><form:errors path="skills" id="due"/></FONT></td>
							</tr>
							<tr>
								<td><form:textarea path="skills" type="text" rows="5" cols="40" required="true"/></td>
							</tr>
							<tr>
							<c:if test="${empty successMsg}">
								<td><input class="button-2" type="submit" value="Submit" /></td>
							</c:if>
							</tr>
						</table>
					</form:form>
					<c:if test="${not empty successMsg}">
						<br />
						<font color="red">${successMsg}</font><br /><br /><br />
						<c:if test="${not empty roleName}">
							<c:choose>
								<c:when test="${fn:toLowerCase(roleName) eq 'sponsor'}">						
									<a class="button-2" href="sponsor/projectForm.do?action=add&id=0" id="addProj">Add Project</a>
								</c:when>
								<c:when test="${fn:toLowerCase(roleName) eq 'lead faculty'}">						
									<a class="button-2" href="leadFac/projectList.do" id="addProj">Project List</a>
								</c:when>
								<c:when test="${fn:toLowerCase(roleName) eq 'negotiating faculty'}">						
									<a class="button-2" href="negFac/projectList.do" id="addProj">Project List</a>
								</c:when>
								<c:when test="${fn:toLowerCase(roleName) eq 'capstone faculty'}">						
									<a class="button-2" href="capFac/projectList.do" id="addProj">Project List</a>
								</c:when>
								<c:when test="${fn:toLowerCase(roleName) eq 'student'}">						
									<a class="button-2" href="student/projectList.do" id="addProj">Project List</a>
								</c:when>
							</c:choose>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="template/footer.jsp" />