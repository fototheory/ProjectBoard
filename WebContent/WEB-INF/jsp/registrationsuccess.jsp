<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:import url="template/header.jsp" />
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="col-3">
				<div class="indent">

					<h2>
						Welcome <c:out value="${registration.fname}" />	<c:out value="${registration.lname}" />.
					</h2>
					<h6>
						Thank you for registering with the NU Capstone Project Board.<br /> 
						<br /> Account Details:<br />
						<table>
							<tr>
								<td>User Name :</td>
								<td><c:out value="${registration.email}" /></td>

							</tr>

							<tr>
								<td>Password :</td>
								<td>(on file)</td>
							</tr>

							<tr>
								<td>Role :</td>
								<td><c:out value="${role}" /></td>
							</tr>

							<tr>
								<td>Discipline :</td>
								<td><c:out value="${discipline}" /></td>
							</tr>
						</table>
						<br />
						You should receive an email within 24hrs informing you that your account is ready.<br />
						<c:if test="${not empty successMsg}">
							<br />${successMsg}<br />
						</c:if>
						<br />
						<a class="button-2" href="login.html">Ok</a>
					</h6>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="template/footer.jsp" />