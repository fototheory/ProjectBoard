<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />
<script>
  $(function() {
    $( "#slider" ).slider({
      value:0,
      min: 0,
      max: 100,
      step: 25,
      slide: function( event, ui ) {
        $( "#amount" ).val( ui.value + "%");
      }
    });
    $( "#amount" ).val( $( "#slider" ).slider( "value" ) + "%" );
  });
  </script>
    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="indent">
          <h2>Project Status</h2>
					<p>
				  <label for="amount">Tasks progress (25% increments per user):</label>
				  <input type="text" id="amount" style="border: 0; color: #003C78; font-weight: bold;" />
				</p>
				<div id="slider"></div>
			<br />	
          <div class="wrapper indent-bot">
            <div class="col-3">
              <div class="wrapper">
                <div class="extra-wrap">
				<h5> Lead faculty member:</h5> 				
					Dr. Pradip Peter Dey<br />
					Professor<br />
					School of Engineering and Technology<br />
					Computer Science, Information and Media Systems<br />
					Technology and Health Sciences Center<br />
					pdey@nu.edu<br />
					(858) 309-3421 <br />
				  </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <div class="extra-wrap">
                  <h6>Tasks: </h6>
				  <ul class="list-2">
				   <li>&nbsp; &nbsp; &nbsp; Receive the project = success/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Assign Academic requirements = success/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Assign negociating faculty member = success/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Some other task = success/unsuccessfully</li>
				  </ul>  
				  </div>
			  </div>
            </div>
          </div>
		  		  
		  <div class="wrapper indent-bot">
            <div class="col-3">
              <div class="wrapper">
                <div class="extra-wrap">
				<h5> Assigned negotiating faculty member:</h5> 				
						Mr. James Jaurez <br />
						Assistant Professor <br />
						School of Engineering and Technology<br />
						Computer Science, Information and Media Systems<br />
						Technology and Health Sciences Center<br />
						jjaurez@nu.edu<br />
						(858) 309-3458 <br /> 
				  </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <div class="extra-wrap">
                  <h6>Tasks: </h6>
				  <ul class="list-2">
				   <li>&nbsp; &nbsp; &nbsp; Receive the project = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Gather requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Rewrite requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Post requirements = successfully/unsuccessfully</li>
				  </ul>  


				
				  </div>
				  
              </div>
            </div>
          </div>
		  
		  
		   <div class="wrapper indent-bot">
            <div class="col-3">
              <div class="wrapper">
                <div class="extra-wrap">
				<h5> Assigned capstone faculty member:</h5> 				
						Dr. Mudasser Fraz Wyne<br />
						Professor<br />
						School of Engineering and Technology<br />
						Computer Science, Information and Media Systems<br />
						Technology and Health Sciences Center<br />
						mwyne@nu.edu<br />
						(858) 309-3433<br />
				  </div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <div class="extra-wrap">
                  <h6>Tasks: </h6>
				  <ul class="list-2">
				   <li>&nbsp; &nbsp; &nbsp; Receive the project = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Gather requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Rewrite requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Post requirements = successfully/unsuccessfully</li>
				  </ul>   
				  </div>
              </div>
            </div>
          </div>
		  
		  <div class="wrapper indent-bot">
            <div class="col-3">
              <div class="wrapper">
                <div class="extra-wrap">
				<h5> Assigned student team:</h5> 				
						Jaqui Sosa , jjj.g.sss@gmail.com <br />
						Michael Asfaw, masfaw70@gmail.com <br />
						Yumiko Iwai, yumboo.i@gmail.com<br />
						Darryl Thierry, darryl.thierry@gmail.com<br />
				</div>
              </div>
            </div>
            <div class="col-4">
              <div class="wrapper">
                <div class="extra-wrap">
                  <h6>Tasks: </h6>
				  <ul class="list-2">
				   <li>&nbsp; &nbsp; &nbsp; Receive the project = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Gather all requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Validated/verified requirements = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Create a prototype = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Validated/verified prototype = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Tested the prototype = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Develop the application = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Validated/verified application = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Tested the application = successfully/unsuccessfully</li>
				   <li>&nbsp; &nbsp; &nbsp; Submit resulting docs, prototype, and application = successfully/unsuccessfully</li>
				  </ul>   
				  </div>
              </div>
            </div>
          </div>		  
        </div>
      </div>
    </section>
	<c:import url="template/footer.jsp" />