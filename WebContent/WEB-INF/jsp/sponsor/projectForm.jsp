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
	.text-form { width:135px; text-align:right; margin-right:5px; }
	</style>
	<script>
 	$(document).ready(function(){
 		$(function() {
 		    $( "#due" ).datepicker();
 		 });
 		
 		$.validator.addMethod("DateFormat", function(value,element) {
 	        return value.match(/^(0[1-9]|1[012])[- //.](0[1-9]|[12][0-9]|3[01])[- //.](19|20)\d\d$/);
 	            },
 	                "Please enter a date in the format mm/dd/yyyy"
 	    );
 		$("#linkSubmit").click(function() {
 			$("#projectForm").validate({
 				rules: {
 					title: "required",
 					desc: "required",
					due: {
		                required: true,
		                DateFormat: true
		            },
 				},
 				messages: {
 					title: "Title is required",
 					desc: "Project Description is required"
 				}
 			});
 			$(this).parents('form').submit();
 		});    	
 		$("#linkClear").click(function() {
 			$("#title").val("");
 			$("#desc").val("");
 			$("#due").val("");
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
                <form:hidden id="disp" path="disp" />
                <form:hidden id="action" path="action" />
                <fieldset>
                  <label><span class="text-form">Title:*</span></label>
                    <form:input id="title" path="title" type="text" required="true" />                  
	                  <div class="wrapper">
	                    <label><span class="text-form">Project description:*</span></label>
	                    <form:textarea id="desc" path="desc"></form:textarea>
	                  </div>
	                  <label><span class="text-form">Due Date:*</span></label>
                    	<form:input id="due" path="due" type="text" required="true" />  (mm/dd/yyyy)
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
          </div>
		  <div class="col-4">         
		  <div class="block-news">
              
            </div>
			 
          </div>
        </div>
        </div>
	</section>
	<c:import url="template/footer.jsp" />