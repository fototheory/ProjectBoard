<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
<title>The Capstone Portal @ NU</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../css/style.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>	
<link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/layout.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/adminpage.css" type="text/css" media="screen">
<script type="text/javascript" src="../js/jquery-1.6.min.js"></script>
<script src="../js/cufon-yui.js" type="text/javascript"></script>
<script src="../js/cufon-replace.js" type="text/javascript"></script>
<script src="../js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="../js/Open_Sans_Light_300.font.js" type="text/javascript"></script>
<script src="../js/Open_Sans_Semibold_600.font.js" type="text/javascript"></script>
<script src="../js/FF-cash.js" type="text/javascript"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->
  <script>
  $(function() {
    $( "#accordion" ).accordion({
      heightStyle: "content"
    });
  });
  </script>
  <style>
  	/* Logout Button */
	#LogoutButton { 
	    display:inline-block;
	    float:right;
	    /*background:#003C78 url(../images/buttonbg.png) repeat-x; */
	    background:#fff;
	    border:1px solid #003C78; 
	    border-radius:3px;
	    -moz-border-radius:3px;
	    position:relative;
	    z-index:30;
	    cursor:pointer;
	    text-decoration: none;
	}
	
	/* Logout Button Text */
	#LogoutButton span {
	    color:#445058; 
	    font-size:14px; 
	    font-weight:bold; 
	    text-shadow:1px 1px #fff; 
	    padding:5px 24px 9px 15px;
	    /*background:url(../images/loginArrow.png) no-repeat 53px 7px;*/
	    display:block
	}
  </style>

<!--[if lt IE 9]>
<script type="text/javascript" src="../js/html5.js"></script>
<link rel="stylesheet" href="../css/ie.css" type="text/css" media="screen">
<![endif]-->
</head>
<body id="page4">
<!-- header -->
<div class="bg">
  <div class="main">
    <header>	
	<div id="bar">
        <div id="container">
        </div>
        <c:if test="${not empty sessionUserInfo}">
			<b><font color="#003c78">Welcome, ${sessionUserInfo.getFname()} ${sessionUserInfo.getLname()}</font></b>
			<!-- Login Starts Here -->
			<div id="loginContainer">
				<a href="adminpage.do?logout=yes" id="LogoutButton"><span>Logout</span><em></em></a>
				<div style="clear: both"></div>			
			</div>
			<!-- Login Ends Here -->

		</c:if>
    </div>
	
      <div class="row-1">
        <h1> <a class="logo" href="login.html">The Capstone Portal @ NU</a> <strong class="slog">Ideas into action</strong> </h1>
      </div>
      <div class="row-2">
        </nav>
      </div>
    </header>