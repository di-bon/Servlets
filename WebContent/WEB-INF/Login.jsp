<!-- Francesco Di Bon 5BIA 28-4-2022 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='css/bootstrapcss/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<script src='js/jquery-3.6.0.min.js' type='text/javascript'></script>
	<script src='js/bootstrapjs/bootstrap.bundle.min.js' type='text/javascript'></script>
	<title>Login</title>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function  () {
			// function to show password
		})
	</script>

	<form action="/Servlets/Loginator" method="post">
		<div>
			<h2>Login</h2>
		</div>
		<div class="form-group">
			<label for="username">Username</label>
		    <input type="username" class="form-control" id="username" name="username" aria-describedby="usernameHelp" placeholder="Enter username" required>
		    <small id="usernameHelp" class="form-text text-muted">We'll never share your username with anyone else.</small>
		</div>
		<div class="form-group">
		    <label for="password">Password</label>
		    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
		</div>
		<div class="form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		<input type="hidden" name="source" value="login.jsp" />
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</body>
</html>