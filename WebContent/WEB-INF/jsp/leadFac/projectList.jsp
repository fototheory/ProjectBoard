<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
	<script type="text/javascript">
	 $(document).ready(function(){
 		$("#negSubmit").click(function() {
 			$(this).parents('form').submit();
 		}); 
	 		
		 // simple Selectyze call
		$(".selectyze").Selectyze();
		 // call with options
		$(".selectyze").Selectyze({
		 theme:'css3',
		 effectOpen:'fade',
		effectClose:'slide'
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
			  	<h2> My Project List</h2>
			  	<div id="accordion">
			  	<c:if test="${not empty projectList}">
					<c:forEach items="${projectList}" var="map"> 
					<c:if test="${map.get('Status') != 'New'}">
					<p>${map.get("Discipline")} ~ ${map.get("Title")} ~ ${map.get("Due Date")}</p>
					<div>
						<p>	
						<form action="projectList.do" method="post">
						<input type="hidden" name="projId" value="${map.get('ID')}" />
						<h5>Company:</h5> ${map.get("company")}<br />	
						<h5>Project sponsor:</h5> ${map.get("Sponsor")}<br />
						<h5>Project description:</h5> ${map.get("Description")}<br />
						<br />
						Append discipline's academic requirements for this project :<br/>
					 	<select name="my_select" class="selectyze">
							<option value="value1" selected="selected"> </option>
							<option value="value2">Applied Engineering</option>
							<option value="value2">Computer Science, Information and Media Systems</option>
						</select>
						<br/>
						<br/> Additional team member ratio: <br/>
						 <select name="my_select" class="selectyze">
							 <option value="value1" selected="selected"> </option>
							 <option value="value2">1</option>
							 <option value="value2">2</option>
							 <option value="value2">3</option>
							 <option value="value2">4</option>
							 <option value="value2">5</option>
						 </select>
							<br/>							
							<br/> Suggested team size:<br/>
							 <select name="my_select" class="selectyze">
							 <option value="value1" selected="selected"> </option>
							 <option value="value2">1</option>
							 <option value="value2">2</option>
							 <option value="value2">3</option>
							 <option value="value2">4</option>
							 <option value="value2">5</option>
						 </select>
							<br/>
							<br/> Total number of team member hours involvement per week: <br/>
						 <select name="my_select" class="selectyze">
							 <option value="value1" selected="selected"> </option>
							 <option value="value2">15</option>
							 <option value="value2">20</option>
							 <option value="value2">30</option>
							 <option value="value2">40</option>
							 <option value="value2">50</option>
						 </select>
						 <br/>
						 <!-- Basic / Normal HTML Select element -->
						 <br/> Assign a Negotiating faculty member: <br/>	
						 
						 <select name="neg" class="selectyze">
							 <option value="" selected="selected"> List of Potential Negotiating Faculty Members</option>
							 <c:if test="${not empty facUsers}">
								 <c:forEach items="${facUsers}" var="user">
								 <option value="${user.getId()}">${user.getFname()} ${user.getLname()}</option>
	   							 </c:forEach>
							 </c:if>
						 </select>
 						 <br/> <br/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
						 <a href="#">| Edit project |</a> &nbsp;  &nbsp; &nbsp; &nbsp; 
						 <a href="projectprogress.html#">| view project progress | </a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						 <a href="#" id="negSubmit">| Route this project to the selected negotiating faculty member | </a>
						 </form>
					</div>
					</c:if>
					</c:forEach>
				</c:if>
				</div>			   
			 <br />
			 </div>
		  </div>
          </div>
    	</div>
	</section>
	<c:import url="template/footer.jsp" />