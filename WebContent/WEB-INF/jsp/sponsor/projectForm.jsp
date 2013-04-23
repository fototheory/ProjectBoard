<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
	<!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <h2 class="p0"><c:if test="${not empty action}">${fn:toUpperCase(fn:substring(action, 0, 1))}${fn:toLowerCase(fn:substring(action, 1,fn:length(action)))}</c:if> project form</h2>
              <form:form modelAttribute="ProjData" method="POST" action="projectForm.do" id="projectForm">
                <form:hidden id="projectId" path="projectId" />
                 <form:hidden id="action" path="action" />
                <fieldset>
                  <label><span class="text-form">Title:</span>
                    <form:input id="title" path="title" type="text" required="true" />
                  </label>
                  <div class="wrapper">
                    <div class="text-form">Project <br /> description:</div>
                    <form:textarea id="desc" path="desc"></form:textarea>
                  </div>
                  <div class="buttons"> <a class="button-2" href="#">Clear</a> <a class="button-2" href="#" onClick="$(this).parents('form').submit();">Send to my project list</a> </div>
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