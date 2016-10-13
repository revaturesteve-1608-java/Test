<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/updateTempInfo.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-1.12.3.js"
	type="text/javascript"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel='stylesheet prefetch'
	href='http://netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css'>
<link rel='stylesheet prefetch'
	href='http://netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.min.css'>
<link rel='stylesheet prefetch'
	href='http://netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css'>
<link rel='stylesheet prefetch'
	href='http://netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.min.css'>

<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-10644958-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>



</head>

<style type="text/css">
.ta-toolbar {
	background-color: #F0F0F0;
	padding: 10px 10px 5px;
	margin-left: 0px; /* Override bootstrap */
	border: 1px solid #EEE;
}

.ta-toolbar .btn-group {
	margin-bottom: 5px;
}

textarea.ta-bind {
	width: 100%;
}

.ta-editor {
	padding: 10px;
	background-color: #FFF;
	border: 1px solid #EEE;
	min-height: 300px;
	height: auto;
	overflow: auto;
	font-family: inherit;
	font-size: 100%;
}

#singleReply {
	margin-top: 2%;
}

#thumbsUp {
	margin-left: 10%;
}

input {
	margin: .5em 0;
}

#container {
	width: 750px;
	margin: 1em auto;
}

#container>div {
	margin: auto 1em;
}

#code {
	float: left;
	width: 50%;
}

#html {
	float: right;
	width: 50%;
}

#code2 {
	margin-right: 1em;
}

#html2 {
	margin-left: 1em;
}

#main textarea {
	width: 100%;
	height: 10em;
}

#html2 textarea {
	float: right;
}

#options {
	clear: both;
}

#divstyles {
	width: 50%;
}

#preview {
	padding-bottom: 3em;
}

#footer {
	border-top: 1px dotted #000;
}

#footer p {
	text-align: center;
	font-size: 75%;
}
</style>
<body data-ng-app="posts">
	<div class="col-sm-2 sidenav">
		<p>test left side</p>
	</div>

	<div class="container-fluid text-center">

		<div class="col-sm-8 text-left">

			<div id="allPosts" data-ng-controller="postsCtrl">
				<div id="newPost">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-8">
									<div>
										<p id="theUsername">Username</p>
									</div>
								</div>
								<div class="col-md-4">
									<button type="button" class="btn btn-default"
										aria-label="Right Align">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									</button>
									<button type="button" class="btn btn-default"
										aria-label="Right Align">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</button>
								</div>
							</div>
						</li>
						<li class="list-group-item">
							<div id=title>
								<input class="form-group" id="postContent" type="text"
									placeholder="Title" ng-model="postTitle">
							</div>

							<div ng-app="textAngularTest" ng-controller="wysiwygeditor">
								<h3>Create Post</h3>

								<div text-angular="text-angular" name="htmlcontent"
									ng-model="htmlcontent" ta-disabled='disabled'
									ta-text-editor-class="clearfix border-around"
									ta-html-editor-class="border-around"></div>





								<hr>

								<button id="submit" type="button" class="btn btn-primary"
									ng-click="addPost(postTitle, postContent)">Submit</button>
								<button type="button" class="btn btn-primary"
									ng-click="htmlcontent = emptyhtml">Reset</button>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target=".bs-example-modal-lg">Codeify</button>
							</div>
						</li>
					</ul>
				</div>




			</div>
			<div id="allPosts" data-ng-controller="fileUpload">
				<form action="/fileUpload" method="post">
					<div id="container">

						<div id="main">
							<div id="code">
								<div id="code2">
									<strong>Source code:</strong><br />
									<textarea name="code" rows="10" cols="40">{{ code }}</textarea>
								</div>
							</div>
							<div id="html">
								<div id="html2">
									<strong>HTML:</strong><br />
									<textarea id="html" name="html" rows="10" cols="40"
										readonly="readonly"
										onClick="javascript:this.form.html.focus();this.form.html.select();">{{ html }}</textarea>
								</div>
							</div>
						</div>
						<div id="options">
							<p>
								Language: <select name="lexer"> {% for item in lexers
									%}
									<option {% if item.0== lexer %}selected="selected"
										{% endif %}value="{{ item.0 }}">{{ item.1 }}</option> {%
									endfor %}
								</select> Style: <select name="style"> {% for item in styles %}
									<option {% if item== style %}selected="selected"
										{% endif %}value="{{ item }}">{{ item }}</option> {% endfor %}
								</select> <input name="linenos" id="linenos" type="checkbox" value="1"
									{% if linenos %}checked="checked" {% endif %} /><label
									for="linenos">Line numbers</label> <br /> CSS: <input
									type="text" id="divstyles" name="divstyles"
									value="{{ divstyles|escape }}" /> <input type="submit"
									value="Highlight!" />
							</p>
						</div>
						<div id="preview">
							<p>
								<strong>Preview:</strong>
							</p>
							{% autoescape false %} {{ html }} {% endautoescape %}
						</div>
						<div id="footer">
							<p>
								<a href="/api">API</a>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-sm-2 sidenav">
			<div class="well">
				<p>test</p>
			</div>
			<div class="well">
				<p>test</p>
			</div>
		</div>
	</div>


	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content"></div>
		</div>
	</div>

	<!-- Scripts for AngularJS -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0/angular.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.18/angular-ui-router.js"></script>
	<script type="text/javascript" src="resources/js/posts.js"></script>

	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.2.4/angular.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.2.4/angular-sanitize.min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/textAngular/1.1.2/textAngular.min.js'></script>
	<script>
		angular.module("textAngularTest", [ 'textAngular' ]);
		function wysiwygeditor($scope) {
			$scope.orightml = '<p><i><b>What would you like to ask your colleagues?</b></i></p>';
			$scope.htmlcontent = $scope.orightml;
			$scope.emptyhtml = '';
			$scope.disabled = false;
			console.log($scope.htmlcontent);
		};
	</script>
	<script>
		$('#myModal').on('shown.bs.modal', function() {
			$('#myInput').focus()
		})
	</script>



</body>
</html>