<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<c:import url="template/header.jsp" />
<style type="text/css">
	#inst {color:black; font-size:20px; }
</style>
<!-- content -->
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="col-3">
				<div class="indent">
					<h2>Admin Page</h2>
					<c:if test="${not empty status}">
						<font color="red">${status}</font>
					</c:if>
					<p id="inst">Select operation you want to perform: </p><br />
					<a class="button-2" href="adminpage.do">Verify User</a><br /><br /><br />
					<a class="button-2" href="userList.do">User List</a><br /><br /><br />
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="template/footer.jsp" />