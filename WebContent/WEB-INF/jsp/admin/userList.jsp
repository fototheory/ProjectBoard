<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="template/header.jsp" />
<style type="text/css">
	#inst {color:black; font-size:20px; }
	#proFld p {margin-bottom:2px;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userList").change(function() {
			$.getJSON("displayUser.do", { userId: $(this).val() }, function (result) {
				$.each(result, function(i, field){
					//alert(i+" "+field);
					$("#"+i).val(field);
					if(i=="hasprofile") {
						if(field) {
							$("#proFld").show();
							$("#proQuestion").hide();	
						}
						else {
							$("#proQuestion").show();
							$("#proFld").hide();
						}
					}
				});
			});
		});
		
		$("#profileAdd").change(function() {
			//if yes, open up profile fields
			if($(this).val()=="yes") {
				$("#proFld").show();
			}
			else {
				$("#proFld").hide();
			}
		});
	});
</script>
<!-- content -->
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="indent">
				<h2>Admin Page</h2>
				<c:if test="${not empty status}">
					<font color="red">${status}</font>
				</c:if>
				<p id="inst">Edit User: </p><br />
				<div style="float:left;">
				<form:form method="Post" action="userList.do" commandName="user">
					<form:input path="id" type="hidden" id="id" />
					<table>
						<tr>
							<td>First Name:<br /><FONT color="red">
							<form:errors path="fname" id="fname" /></FONT></td>
						</tr>

						<tr>
							<td><form:input path="fname" type="text" required="true" /></td>
						</tr>

						<tr>
							<td>Last Name:<br /><FONT color="red">
							<form:errors path="lname" /></FONT></td>
						</tr>

						<tr>
							<td><form:input path="lname" type="text" required="true" /></td>
						</tr>
						<tr>
							<td>Password:<br /><FONT color="red">
							<form:errors path="password" /></FONT></td>
						</tr>
						<tr>
							<td><form:input path="password" type="password" required="true" /></td>
						</tr>
						<tr>
							<td>Confirm Password:<br /><FONT color="red">
							<form:errors path="confirmPassword" /></FONT></td>
						</tr>
						<tr>
							<td><form:input path="confirmPassword" type="password" required="true" /></td>
						</tr>
						<tr>
							<td>Email:<br /><FONT color="red">
							<form:errors path="email" /></FONT></td>
						</tr>
						<tr>
							<td><form:input path="email" type="text" required="true" /></td>
						</tr>
						<tr>
							<td>
								<span id="proQuestion" style="display:none;">
									Do you want to add profile for this user?<br />
									<select id="profileAdd">
										<option value="yes">Yes</option>
										<option value="no" selected>No</option>
									</select> 
								</span>
								<div id="proFld" style="display:none;">
									<br />
									<p>Profile: </p>
									<p>
										Company: <br /><FONT color="red"><form:errors path="company" /></FONT>
										<form:input path="company" type="text" />
									</p>
									<p>
										Phone: <br /><FONT color="red"><form:errors path="phone" /></FONT>
										<form:input path="phone" type="text" />
									</p>
									<p>
										Skills: <br /><FONT color="red"><form:errors path="skills" /></FONT>
										<form:input path="skills" type="text" />
									</p>
								</div>
							</td>
						</tr>
						<!-- This displays the various disciplines from the database. -->
						<tr>
							<td>Subject area:<br />
							<FONT color="red"><form:errors path="discipline" required="true" /></FONT></td>
						</tr>
						<tr>
							<td>
								<!-- Display disciplines from the database -->	
								<form:select path="discipline" items="${disciplines}" />														
							</td>
						</tr>
						<tr>
							<td>You are a:<br />
							<FONT color="red"><form:errors path="role" /></FONT></td>
						</tr>
						<tr>
							<td>
								<!-- Display roles from the database -->
								<form:select path="role" items="${roles}" />
							</td>
						</tr>
						<tr>
							<td><input class="button-2" type="Reset" value=" Clear" /></td>
							<td><input class="button-2" type="submit" value="Submit" /></td>
						</tr>
					</table>
				</form:form>
				</div>
				<div style="float:right; margin-right:30px;">
				Select user from the list: <br />
				<select id="userList" size="10" >
					<c:forEach items="${allUsers}" var="user">
						<option value="${user.getId()}">${user.getEmail()}</option><br />
					</c:forEach>
				</select>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="template/footer.jsp" />