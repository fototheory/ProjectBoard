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
					<c:if test="${not empty status}">
						<font color="red">${status}</font>
					</c:if>
					<h2 class="p0">Password Retrieval Form</h2>


<h4>
					<form:form method="Post" action="forgotpassword.do"
						commandName="forgotpassword">
						
						<table >
							
								<td><font color="grey">Your e-mail Address:</font></td> &nbsp
								
								<td> <input type="email" name="email" placeholder="Please enter your email address here"  size ="40"> </td>
  
							</tr>
							
							
							<tr>
								<td><input class="button-2" type="submit"
									value="Recover My Password" /></td>
			</tr>
							<tr>
							<td><input class="button-2" type="reset"
									value=" Clear " /></td>
							
							
							</tr>
									
						</table>
					</form:form>
					
					</h4>
				</div>
			</div>
		</div>
	</div>
</section>




<c:import url="globalFooter.jsp"/>