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
				//clear error fields
				$(".formClass").hide();
				//display user form
				$("#userForm").show();				
			});
		});
		
		$("#dialog-confirm").dialog({
			resizable: false,
		    height:210,
		    width:440,
		    modal: true,
		    autoOpen: false,
		    show: {
		        effect: "blind",
		        duration: 1000
		    },
		    hide: {
		        effect: "explode",
		        duration: 1000
		    },
		    buttons: {
		    	"Delete": function() {
		    		$( "#formAction" ).val( "delete" );
		    		$("#userRecForm").submit();
		    	},
		        Cancel: function() {
		        	$( this ).dialog( "close" );
		        }
		      }
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
		
		$("#deleteSubmit").click(function(e) {
			if($(this).val()=="Delete") {
				$( "#dialog-confirm" ).dialog( "open" );
				return false;
			}
			return true;
		});
	});
</script>

<div id="dialog-confirm" title="Delete User?">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>This user will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>

<!-- content -->
<section id="content">
	<div class="padding">
		<div class="wrapper margin-bot">
			<div class="indent">
				<h2><span style="float:left;margin-top:-5px;">System Administrator Page</span><span style="float:right;margin-top:15px;margin-right:250px;"><a class="button-2" href="addUser.do" id="addUser">Add User</a></span></h2>
				<div style="clear:both;"></div>
				<c:if test="${not empty status}">
					<font color="red">${status}</font>
				</c:if>
				<p id="inst">Edit User: </p>
				<c:choose>
               		<c:when test="${not empty error && error eq 'true'}">
               			<div id="userForm" style="float:left;">
               		</c:when>
               		<c:otherwise>
               			<div id="userForm" style="float:left;display:none;">
               		</c:otherwise>				
				</c:choose>
				<form:form method="Post" id="userRecForm" action="userList.do" commandName="user">
					<form:input path="id" type="hidden" id="id" />
					<form:input path="isverified" type="hidden" id="isverified" />
					<form:input path="hasprofile" type="hidden" id="hasprofile" />
					<form:input path="profile" type="hidden" id="profile" />
					<input type="hidden" id="formAction" name="formAction" value="edit" />
					<table>
						<tr>
							<td>First Name:<br /><FONT color="red">
							<span class="formError"><form:errors path="fname" id="fname" /></span></FONT></td>
						</tr>

						<tr>
							<td><form:input path="fname" type="text" required="true" /></td>
						</tr>

						<tr>
							<td>Last Name:<br /><FONT color="red">
							<span class="formError"><form:errors path="lname" /></span></FONT></td>
						</tr>

						<tr>
							<td><form:input path="lname" type="text" required="true" /></td>
						</tr>
						<tr>
							<td>Password:<br /><FONT color="red">
							<span class="formError"><form:errors path="password" /></span></FONT></td>
						</tr>
						<tr>
							<td><form:input path="password" type="password" required="true" /></td>
						</tr>
						<tr>
							<td>Confirm Password:<br /><FONT color="red">
							<span class="formError"><form:errors path="confirmPassword" /></span></FONT></td>
						</tr>
						<tr>
							<td><form:input path="confirmPassword" type="password" required="true" /></td>
						</tr>
						<tr>
							<td>Email:<br /><FONT color="red">
							<span class=formError><form:errors path="email" /></span></FONT></td>
						</tr>
						<tr>
							<td><form:input path="email" type="text" required="true" /></td>
						</tr>
						<tr>
							<td>
							<c:choose>
			               		<c:when test="${not empty error && error eq 'true' && not user.isHasprofile()}">
			               			<span id="proQuestion">
			               		</c:when>
			               		<c:otherwise>
			               			<span id="proQuestion" style="display:none;">
			               		</c:otherwise>				
							</c:choose>
									Do you want to add profile for this user?<br />
									<select name="profileAdd" id="profileAdd">
										<option value="yes">Yes</option>
										<option value="no" selected>No</option>
									</select> 
								</span>
								<c:choose>
			               		<c:when test="${not empty error && error eq 'true' && not user.isHasprofile()}">
			               			<div id="proFld" style="display:none;">
			               		</c:when>
			               		<c:otherwise>
			               			<div id="proFld">
			               		</c:otherwise>				
							</c:choose>
								
									<br />
									<p>Profile: </p>
									<p>
										Company: <br /><FONT color="red"><span class=formError><form:errors path="company" /></span></FONT>
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
							<FONT color="red"><span class=formError><form:errors path="discipline" required="true" /></span></FONT></td>
						</tr>
						<tr>
							<td>
								<!-- Display disciplines from the database -->	
								<form:select path="discipline" items="${disciplines}" />														
							</td>
						</tr>
						<tr>
							<td>You are a:<br />
							<FONT color="red"><span class=formError><form:errors path="role" /></span></FONT></td>
						</tr>
						<tr>
							<td>
								<!-- Display roles from the database -->
								<form:select path="role" items="${roles}" />
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><input class="button-2" type="Reset" value="Clear" />&nbsp;&nbsp;
							<input class="button-2" type="submit" name="action" value="Edit" />&nbsp;&nbsp;
							<input class="button-2" type="submit" name="action"id="deleteSubmit" value="Delete" /></td>
						</tr>
					</table>
				</form:form>
				</div>
				<div style="float:right; margin-right:30px;">
				Select user from the list: <br />
				<select id="userList" size="10">
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