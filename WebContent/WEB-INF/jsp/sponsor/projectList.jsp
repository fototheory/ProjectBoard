<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
<script type="text/javascript">
	$(document).ready(function(){
		<c:if test="${not empty selectedId}">
		// this will make the second tab by default opened (index starts from 0)
        $('#accordion').accordion({active: <c:out value="${selectedId}"/>}); 
		</c:if>
		
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
		    		href = $("#deleteSubmit").attr('href');
		    		window.location.href = href;
		    	},
		        Cancel: function() {
		        	$( this ).dialog( "close" );
		        }
		      }
		});
		
		$("#dialog-archive-confirm").dialog({
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
		    	"Archive": function() {
		    		href = $("#archiveSubmit").attr('href');
		    		window.location.href = href;
		    	},
		        Cancel: function() {
		        	$( this ).dialog( "close" );
		        }
		      }
		});
		
		$("#deleteSubmit").click(function(e) {
			$( "#dialog-confirm" ).dialog( "open" );
			return false;
		});
		$("#archiveSubmit").click(function(e) {
			$( "#dialog-archive-confirm" ).dialog( "open" );
			return false;
		});
	});
</script>

<div id="dialog-confirm" title="Delete Project?">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>This project will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>

<div id="dialog-archive-confirm" title="Delete Project?">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>This project will be permanently archived and cannot be recovered. Are you sure?</p>
</div>

<body id="page4">
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div class="col-3">
            <div class="indent">
              <c:if test="${not empty status}">
				<font color="red">${status}</font>
			  </c:if>
              <h2>My Projects List</h2>
              <p class="color-4">Each year organizations from the private, government, and non-profit sectors come to NU to assist them in turning ideas into action. Highly talented students work in accordance to the organizations business and technical requirements while also following strict academic requirements..</p>
              <div class="wrapper p2">
              </div>
			  	<h3 style="padding-bottom:10px;"> Available Projects <span style="float:right;"><a class="button-2" href="projectForm.do?action=add&id=0" id="addProj">Add a Project</a></span></h3>
			  	<div id="accordion">
			  	<c:if test="${not empty projectList}">
					<c:forEach items="${projectList}" var="map"> 
					<p>${map.get("Sponsor")} ~ ${map.get("Title")} ~ ${map.get("Due Date")}</p>
					<div>
						<p>						
						<c:forEach items="${map}" var="entry"> 
							<c:if test="${entry.key != 'Sponsor' && entry.key != 'Title' && entry.key != 'Due Date'&& entry.key != 'DispID'}">
							${entry.key}: ${entry.value}<br />
							</c:if>
						</c:forEach>	
						<br />
						<c:choose>
               			<c:when test="${map.get('Status') eq 'New'}">
							<a href="projectList.do?action=edit&id=${map.get('ID')}" style="padding-left:65px;"> | Edit project | </a>	
							<a href="projectList.do?action=delete&id=${map.get('ID')}" style="padding-left:20px;" id="deleteSubmit"> | Delete project | </a>
							<a href="projectList.do?action=archive&id=${map.get('ID')}" style="padding-left:20px;" id="archiveSubmit"> | Archive project | </a>
							<br />
							<a href="projectList.do?action=progress&id=${map.get('ID')}" style="padding-left:75px;"> | View project progress | </a>
							<a href="communication.do" style="padding-left:10px;"> | Discussion Board | </a>						
							<br /><br />
							<a href="projectList.do?action=submit&id=${map.get('ID')}&dispID=${map.get('DispID')}" style="padding-left:130px;"> | Submit this project to Lead Faculty | </a>
						</c:when>	
						<c:otherwise>
							<a href="projectList.do?action=archive&id=${map.get('ID')}" style="padding-left:20px;" id="archiveSubmit"> | Archive project | </a>
							<a href="projectList.do?action=progress&id=${map.get('ID')}" style="padding-left:20px;"> | View project progress | </a>
							<a href="communication.do" style="padding-left:10px;"> | Discussion Board | </a>						
						</c:otherwise>
						</c:choose>			
						</p>
					</div>
					</c:forEach>
				</c:if>
				</div>			   
			 <br />
			 </div>
		  </div>
          <div class="col-4">
            <div class="block-news">
              <h3 class="color-4 p2">My Archived Projects</h3>
               <c:choose>
               <c:when test="${not empty archiveList}">
              	<ul class="list-2">	 
              		<c:forEach items="${archiveList}" var="map">             
	                <li><a href="archivedProjects.do?id=${map.get('ID')}">${map.get("Title")}</a></li>
	                </c:forEach>          
              	</ul>
              	</c:when>
              	<c:otherwise>
              		<ul class="list-2">
              		 <li>No Archived Projects</li>
              		</ul>
              	</c:otherwise>
              </c:choose>
              <br />
               <a class="button-2" href="archivedProjects.do">Previous Years</a>
            </div>
			
		  </div>
          </div>
       </div>
	</section>
	<c:import url="template/footer.jsp" />