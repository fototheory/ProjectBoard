<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="template/header.jsp" />

<script>
 	$(document).ready(function(){
 		$("#userList").change(function() {
 	 		//Populate the text fields
 			var id = $("#userList option:selected").val();
			$("#email").val($("#userList option:selected").text());
			$("#fname").val($("#fname"+id).val());
			$("#lname").val($("#lname"+id).val());
 		});
 	});
 	
 	$(window).load(function() {
 		//Select the first item in the list
		$('select option:nth-child(1)').prop('selected', 'selected');
 		//Populate the text fields
		var id = $("#userList option:selected").val();
		$("#email").val($("#userList option:selected").text());
		$("#fname").val($("#fname"+id).val());
		$("#lname").val($("#lname"+id).val());
	 	});
</script>


<!-- content -->
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="col-3">
				<div class="indent">
					<h2 class="p0">Admin Page</h2>
					<c:if test="${not empty status}">
						<font color="red">${status}</font>
					</c:if>
					<div>List of users needing verification:</div>
					<form:form method="Post" action="adminpage.do" commandName="user">
							<c:forEach items="${userList}" var="user">
								<form:hidden path="fname" id="fname${user.id}" value="${user.fname}"/>
								<form:hidden path="lname" id="lname${user.id}" value="${user.lname}"/>
							</c:forEach>
						<select id="userList" size="10" >
							<c:forEach items="${userList}" var="user">
								<option value="${user.id}">${user.email}</option><br />
							</c:forEach>
						</select>
						<table>
							<tr>
								<td>
									First Name:<br />
								</td>
								<td>
									Last Name:<br />
								</td>
							</tr>
							<tr>
								<td>
									<form:input path="fname" type="text" readonly="true"/>
								</td>
								<td>
									<form:input path="lname" type="text" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td>
									Email Address:<br />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<form:input path="email" type="text" size="30" readonly="true"/>
								</td>
							</tr>
						</table>
						
						<input class="button-2" type="submit" value="Verify User"/>
						<input class="button-2" type="submit" formmethod="get" value="Refresh List"/>
									
					</form:form>
					<c:if test="${not empty successMsg}">
						<br />
						<font color="red">${successMsg}</font>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="template/footer.jsp" />