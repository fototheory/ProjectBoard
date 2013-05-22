<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
	<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
	<style type="text/css">
	form { width: 700px; }
	label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
	form input[type=text], textarea { font: 13px Arial,Helvetica,sans-serif; width:300px; }
	#due {width:150px;}
	#dispError {padding-left:140px;}
	.text-form { width:150px; text-align:right; margin-right:10px; }
	</style>
	<script>
 	$(document).ready(function(){
 		var datePattern = /^(0[1-9]|1[012])[- //.](0[1-9]|[12][0-9]|3[01])[- //.](19|20)\d\d$/;
 		$(function() {
 		    $( "#due" ).datepicker();
 		 });
 		
 		$.validator.addMethod("DateFormat", function(value,element) {
 	        		return value.match(datePattern);
 	            },
 	                "Please enter a date in the format mm/dd/yyyy"
 	    );
 		
       	$.validator.addMethod("dateCheck", function(value,element) {
 	        		if(value.match(datePattern)) 
 	        		{
	 	        		var dueDate = new Date(value);
	 	        		var todayDate = new Date();
	 	        		if (dueDate > todayDate) {
	 	        		  return true;
	 	        		}
 	        		}
 	        		return false;
 	            },
 	                "Due date is less than current date"
 	    );
 	       
 		$("#linkSubmit").click(function() {
 			$("#projectForm").validate({
 				rules: {
 					title: "required",
 					desc: "required",
					due: {
		                required: true,
		                DateFormat: true,
		                dateCheck: true
		            },
		            disp: "required"
 				},
 				messages: {
 					title: "Title is required",
 					desc: "Project Description is required",
 					due: {
 						required: "Due Date is required"
 					},
 					disp: "Discipline is required"
 				},
 				errorPlacement: function(error, element) {
				     if (element.attr("name") == "disp") {
				    	//alert(element.attr("name"));
				    	//$("#dispError").append(error); 
				    	error.appendTo($('#dispError'));
				     }
				     else if (element.attr("name") == "due") {
					    	//alert(element.attr("name"));
					    	//$("#dispError").append(error); 
					    	error.appendTo($('#dueError'));
					 }
				     else {
				    	 error.insertAfter(element);
				     }
 				}
 			});
 			$(this).parents('form').submit();
 		}); 
 		
 		$("#linkClear").click(function() {
 			$("#title").val("");
 			$("#desc").val("");
 			$("#due").val("");
 			$('input[name="disp"]').prop('checked', false);
 		});    	
  	});
  	</script> 	
  	
 	<!-- Dialog box -->
	<script>
	$(function() {
		$("#dialog-modal").dialog({
			height : 150,
			modal : true
		});
	});
	</script>	
  	
	<!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <h2 class="p0"><c:if test="${not empty action}">${fn:toUpperCase(fn:substring(action, 0, 1))}${fn:toLowerCase(fn:substring(action, 1,fn:length(action)))}</c:if> project form</h2>
              <form:form modelAttribute="ProjData" method="POST" action="projectForm.do" id="projectForm">
                <form:hidden id="projectId" path="projectId" />
                <form:hidden id="sponsorId" path="sponsorId" />
                <form:hidden id="action" path="action" />
                <fieldset>
                  <label><span class="text-form">Title:*</span></label>
                    <form:input id="title" path="title" type="text" required="true" /> <c:if test="${not empty errors && errors.containsKey('title')}"><label class="error">${errors.get("title")}</label></c:if>                
	                
	                  <div class="wrapper"> </br>
	                    <label><span class="text-form">Project description:*</span></label>
	                    <form:textarea id="desc" path="desc"></form:textarea> <c:if test="${not empty errors && errors.containsKey('desc')}"><label class="error">${errors.get("desc")}</label></c:if>   
	                  </div>
	                  
	                  <div class="wrapper"> </br>
	                  <label><span class="text-form">Due Date:*</span></label>
                    	<form:input id="due" path="due" type="text" required="true" /> &nbsp;&nbsp; <span class="error" id="dueError"></span><c:if test="${not empty errors && errors.containsKey('due')}"><label class="error">${errors.get("due")}</label></c:if>
                      </div>
                      <div class="wrapper"> </br>
                      <label><span class="text-form">Discipline:*</span></label>
                      <!-- Display disciplines from the database -->
						<c:forEach items="${disciplines}" var="discipline">
							<form:radiobutton path="disp" id="disp" value="${discipline.id}" label="${discipline.name}" required="true" />
							<br />
						</c:forEach>
						<span class="error" id="dispError"></span>
						<c:if test="${not empty errors && errors.containsKey('disp')}"><label class="error">${errors.get("disp")}</label></c:if>
					  </div> 
	                  <div class="wrapper" style="padding:10px 0; color:#003c78;">*Required Fields</div>
	                  <div class="buttons" style="text-align:left; padding-left:275px;"> <a class="button-2" href="#" id="linkClear">Clear</a> 
	                  <a class="button-2" href="#" id="linkSubmit">
	                  <c:choose>
	      				<c:when test="${not empty action && action eq 'edit'}">
	      					Submit Edited Project
	      				</c:when>
	      				<c:otherwise>
	      					Send to my project list
	      				</c:otherwise>
	      			  </c:choose>
	                  </a> </div>
	            </fieldset>
              </form:form>
            </div>            
						<!-- Dialog box 
						<div id="dialog-modal" title="Dialog box">
							<p>Hi. You'll need to sign in as a sponsor to be able to post a project.</p>
						</div>-->
          </div>
		  
        </div>
        </div>
	</section>
	<c:import url="template/footer.jsp" />