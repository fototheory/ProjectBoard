<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:import url="template/header.jsp" />

<script type="text/javascript" src="../js/jCollapsible.js"></script>
<script type="text/javascript" src="../js/jCollapsible.min.js"></script>
<script type="text/javascript" src="../js/cufon-yui.js"></script>
<script type='text/javascript'>
$(document).ready(function(){
    $('#example').collapsible({xoffset:'-10',yoffset:'5',defaulthide: false});
    $('#example').collapsible({xoffset:'-30'});
	$('#example2').collapsible({xoffset:'-20', defaulthide: false, imagehide: '../images/arrow-down.png', imageshow: '../images/arrow-right.png'});
	$('#example3').collapsible();
	$('#comments').collapsible({xoffset:'-20',yoffset:'50', imagehide: '../images/arrow-down.png', imageshow: '../images/arrow-right.png', defaulthide: false});
});
</script>

<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
<style>
	/* The following styles are used only for this page - the actual plugin styles are in slidernav.css */
	.row-2 { margin-left: -21px;}
	#content {margin-left: -1px;}
	a { text-decoration: none; }
	p { font-size: 18px; line-height: 24px; margin: 0 0 30px; }
	input { margin: 0; padding: 0; }	
	ul, ol { padding-left: 20px; line-height: 18px; }
	.biglist { font-size: 110%; font-weight: bold; background: #fff; padding: 10px 40px; margin: 0; }
	.jcollapsible { font-size: 125%; font-weight: bold; }
	#comments { padding: 0; margin: 0; }
	#comments li { float: left; padding: 10px 0 0; }
	.comment { background: #fff; padding: 10px; display: block; overflow: hidden; }
	.comment-author { float: left; width: 50px; font-size: 10px; text-align: center; margin: 0 10px 10px 0; }
	.comment-body, .comment-body p { font-size: 12px; line-height: 18px; margin: 0; }	
</style>

    <!-- content -->
    <section id="content">
      <div class="padding">
        <div class="wrapper margin-bot">
          <div id="content">          	
          	<h3 style="padding-bottom:10px;">Discussion Board<span style="float:right;"><a class="button-2" href="projectList.do" >Back to my project list</a></span></h3>
			<div style="clear:both;"></div>
	<br /><br />
	<ul id="comments">
		<li>
			<div class="comment">
				<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
				<div class="comment-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
					</p>
				</div>
			</div>
			<ul class="children">
				<li>
					<div class="comment">
						<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
						<div class="comment-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
							</p>
						</div>
					</div>
					<ul class="children">
						<li>
							<div class="comment">
								<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
								<div class="comment-body">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
									</p>
								</div>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<div class="comment">
				<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
				<div class="comment-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
					</p>
				</div>
			</div>
		</li>
		<li>
			<div class="comment">
				<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
				<div class="comment-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
					</p>
				</div>
			</div>
			<ul class="children">
				<li>
					<div class="comment">
						<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
						<div class="comment-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
							</p>
						</div>
					</div>
					<ul class="children">
						<li>
							<div class="comment">
								<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
								<div class="comment-body">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
									</p>
								</div>
							</div>
						</li>
						<li>
							<div class="comment">
								<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
								<div class="comment-body">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
									</p>
								</div>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<div class="comment">
				<div class="comment-author"><img src="../images/gravatar.gif" /><a href="/">Author</a></div>
				<div class="comment-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
					</p>
				</div>
			</div>
		</li>
	</ul>
	<div style="clear:both;"></div>
	<br /><br /><br />
</div>

        </div>
      </div>
    </section>
<script type="text/javascript">Cufon.now();</script>
<c:import url="template/footer.jsp" />
</body>
</html>
