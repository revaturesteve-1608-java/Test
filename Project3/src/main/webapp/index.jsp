<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature StackOverFlow</title>
<%@ include file="headers.jsp"%>
</head>
<body data-ng-app="routingApp">

<%@ include file="index_navbar.jsp"%>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="resources/imgs/emily-higgins.jpg" alt="Revature">
			<div class="carousel-caption" data-ng-controller="indexCtrl">
				<h3>StackOverFlow</h3>
				<button type="submit" class="btn btn-primary" data-ng-click="homePage()">Log in</button>
				<br />
			</div>
		</div>

		<div class="item">
			<img src="resources/imgs/chris-olney.jpg" alt="Chris Olney">
			<div class="carousel-caption" data-ng-controller="indexCtrl">
				<h3>StackOverFlow</h3>
				<button type="submit" class="btn btn-primary" data-ng-click="homePage()">Log in</button>
				<br />
			</div>
		</div>

		<div class="item">
			<img src="resources/imgs/robert-rolle-business-development-manager.jpg" alt="robert-rolle">
			<div class="carousel-caption" data-ng-controller="indexCtrl">
				<h3>StackOverFlow</h3>
				<button type="submit" class="btn btn-primary" data-ng-click="homePage()">Log in</button>
				<br />
			</div>
		</div>
	</div>

	<!-- Left and right controls -->
	<a class="left carousel-control" href="#myCarousel" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#myCarousel" role="button"
		data-slide="next"> <span
		class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div>
</body>
</html>