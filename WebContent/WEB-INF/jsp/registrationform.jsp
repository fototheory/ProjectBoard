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
								<h2 class="p0">Registration Form</h2>
								<c:if test="${not empty status}">
									<font color="red">${status}</font>
								</c:if>
								<form:form method="Post" action="registrationform.do"
									commandName="registration">
									<table>
										<tr>
											<td>First Name:<br /><FONT color="red">
											<form:errors path="fname" /></FONT></td>
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

										<!-- This displays the various disciplines from the database. -->
										<tr>
											<td>Subject area:<br />
											<FONT color="red"><form:errors path="discipline" required="true" /></FONT></td>
										</tr>
										<tr>
											<td>
												<!-- Display disciplines from the database -->
												<c:forEach items="${disciplines}" var="discipline">
													<form:radiobutton path="discipline" name="discipline" 
														value="${discipline.id}" label="${discipline.name}" required="true" />
													<br />
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>You are a:<br />
											<FONT color="red"><form:errors path="role" /></FONT></td>
										</tr>
										<tr>
											<td>
												<!-- Display roles from the database -->
												<c:forEach items="${roles}" var="role">
													<form:radiobutton path="role" name="userrole" 
														value="${role.id}" label="${role.name}" required="true" />
													<br />
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td><input class="button-2" type="Reset" value=" Clear" /></td>
											<td><input class="button-2" type="submit"  value="Submit" /></td>
										</tr>
									</table>
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