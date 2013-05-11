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


	<h2>Submitted User Information</h2>
	
	<h3>
	
	<table>

		<tr>


			<td>Request Type: Forgot Password! <br>
			</td>

		</tr>



		<tr>
			<td>Your Email Address : ${email}</td>
		</tr>



		<tr>

			<td>Please check your email , we already sent your password.<br>
				We thank you!



			</td>
		</tr>

		


	</table>
	
<a class="button-2" href="login.html">Ok</a>

</h3>


</div>
			</div>
		</div>
	</div>
</section>
<c:import url="template/footer.jsp" />