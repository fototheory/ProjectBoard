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
					<h2 class="p0">Profile Page</h2>
					<c:if test="${not empty status}">
						<font color="red">${status}</font>
					</c:if>
					<form:form method="Post" action="profilepage.do" commandName="profile">
						<table>
							<tr>
								<td>Company:<br /> 
								<FONT color="red"><form:errors path="company" /></FONT></td>
							</tr>

							<tr>
								<td><form:input path="company" type="text" /></td>
							</tr>

							<tr>
								<td>Phone:<br /> 
								<FONT color="red"><form:errors path="phone" /></FONT></td>
							</tr>
							<tr>
								<td><form:input path="phone" type="text" /></td>
							</tr>
							<tr>
								<td>Skills:<br /> 
								<FONT color="red"><form:errors path="skills" id="due"/></FONT></td>
							</tr>
							<tr>
								<td><form:textarea path="skills" type="text" rows="5" cols="40"/></td>
							</tr>
							<tr>
								<td><input type="submit" value="Submit" /></td>
							</tr>
						</table>
					</form:form>
					<c:if test="${not empty successMsg}">
						<font color="red">${successMsg}</font>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="template/footer.jsp" />