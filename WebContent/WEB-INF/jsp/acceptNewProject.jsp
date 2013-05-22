<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
<script>
 	$(document).ready(function(){
 		$("#acceptProj").click(function() {
 			$("#acptVal").val("true");
 			$(this).parents('form').submit();
 		}); 
 		
 		$("#denyProj").click(function() {
 			$("#acptVal").val("true");
 			$(this).parents('form').submit();
 		}); 		
  	});
</script>
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <c:if test="${not empty status}">
				<font color="red">${status}</font>
			  </c:if>
              <h2><c:if test="${not empty title}">${title} </c:if></h2>
              <p class="color-4"><c:if test="${not empty body}">${body}</c:if></p>
              <div class="wrapper p2">
              </div>
              <c:choose>
	   				<c:when test="${not empty result}">
	   					${result}
	   				</c:when>
	   				<c:otherwise>
		              <form action="acceptNewProject.do" method="post">
		              	<input type="hidden" name="projId" value="<c:if test='${not empty projId}'>${projId}</c:if>" />
		              	<input type="hidden" name="statId" value="<c:if test='${not empty statId}'>${statId}</c:if>" />
		              	<input type="hidden" name="leadId" value="<c:if test='${not empty leadId}'>${leadId}</c:if>" />
		              	<input type="hidden" name="acptVal" id="acptVal" value="">
					 	<a class="button-2" href="#" id="acceptProj">Acknowledge</a>
					 	<a class="button-2" href="#" id="denyProj">Deny</a>
					  </form>
					 </c:otherwise>
			 </c:choose>
			 <br />
			 </div>
		  </div>
       </div>
	</section>
	<c:import url="template/footer.jsp" />